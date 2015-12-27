package com.h2y.bmg.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ISysPrivilegeListDao;
import com.h2y.bmg.dao.ISysUnitsDao;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.util.SysBaseUtil;
import com.h2y.bmg.util.SysBaseUtil.MemcachedKeyPrefix;
import com.h2y.memcached.MemcachedUtil;
import com.h2y.util.MatcherUtil;

/**
 * 系统缓存接口实现类
 */
@Service("sysCacheService")
public class SysCacheServiceImpl implements ISysCacheService{

	private final static Logger logger = Logger.getLogger(SysCacheServiceImpl.class);

	@Autowired
	protected ISysUserService sysUserService;

	@Autowired
	protected ISysUnitsDao sysUnitsDao;

	@Autowired
	protected ISysPrivilegeListDao sysPrivilegeListDao;

	//memcache缓存
	private MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();

	/**
	 * 更新用户缓存信息
	 * @param request
	 * @param unitId
	 * @param sysUser
	 */
	public void  updateUserCachInfo(String sessionId, long unitId,
			SysUser sysUser) {
		
		
		String loginSessionIdKey = getLoginSessionIdKey(sysUser.getId()+"");
		Object oldSessionId = memcachedUtil.get(loginSessionIdKey);
		
		//清空旧的sessionId存储的信息
		clearUserCachInfo(oldSessionId+"");
		//清空登陆用户的 SessionId信息
		memcachedUtil.delete(loginSessionIdKey);

		//存储当前sessionId
		memcachedUtil.add(loginSessionIdKey, sessionId, SysBaseUtil.EXPIRY_TIME);

		SysUnits sysUnits = sysUnitsDao.get(unitId);
		long sysRoleId = unitId==1 ? -1l : sysPrivilegeListDao.getSysRoleIdByUnitId(unitId);
		
		//更新单位Id
		memcachedUtil.add(getLoginUnitIdKey(sessionId), unitId, SysBaseUtil.EXPIRY_TIME);

		//更新单位
		memcachedUtil.add(getLoginUnitKey(sessionId), sysUnits, SysBaseUtil.EXPIRY_TIME);

		//更新用户Id
		memcachedUtil.add(getLoginUserIdKey(sessionId), sysUser.getId(), SysBaseUtil.EXPIRY_TIME);

		//更新用户对象
		memcachedUtil.add(getLoginUserKey(sessionId), sysUser, SysBaseUtil.EXPIRY_TIME);

		//更新系统角色Id
		memcachedUtil.add(getLoginSysRoleIdKey(sessionId), sysRoleId, SysBaseUtil.EXPIRY_TIME);
	}

	/**
	 * 清除缓存
	 */
	public void clearUserCachInfo(String sessionId) {

		//本地缓存
		//清除当前用户对应的sessionId
		memcachedUtil.delete(getLoginSessionIdKey(getLoginUserId(sessionId)+""));
		
		memcachedUtil.delete(getLoginUnitKey(sessionId));
		memcachedUtil.delete(getLoginUnitIdKey(sessionId));
		memcachedUtil.delete(getLoginUserKey(sessionId));
		memcachedUtil.delete(getLoginUserIdKey(sessionId));
		memcachedUtil.delete(getLoginSysRoleIdKey(sessionId));
	}

	/**
	 * 用户是否登陆，如果已经登录，则更新有效时间
	 * @param sessionId
	 * @return
	 */
	public boolean isUserLogin(String sessionId) {

		//本地缓存

		Object unitId = memcachedUtil.get(getLoginUnitIdKey(sessionId));
		Object unit = memcachedUtil.get(getLoginUnitKey(sessionId));
		Object userId = memcachedUtil.get(getLoginUserIdKey(sessionId));
		Object user = memcachedUtil.get(getLoginUserKey(sessionId));
		Object sysRoleId = memcachedUtil.get(getLoginSysRoleIdKey(sessionId));
		Object sesssionId = memcachedUtil.get(getLoginSessionIdKey(userId+""));
		
		if (unitId==null || userId==null || user==null || sysRoleId==null || unit==null || sesssionId==null) {
			return false;
		}else {
			
			memcachedUtil.update(getLoginUnitIdKey(sessionId), unitId, SysBaseUtil.EXPIRY_TIME);
			memcachedUtil.update(getLoginUnitKey(sessionId), unit, SysBaseUtil.EXPIRY_TIME);
			memcachedUtil.update(getLoginUserIdKey(sessionId), userId, SysBaseUtil.EXPIRY_TIME);
			memcachedUtil.update(getLoginUserKey(sessionId), user, SysBaseUtil.EXPIRY_TIME);
			memcachedUtil.update(getLoginSysRoleIdKey(sessionId), sysRoleId, SysBaseUtil.EXPIRY_TIME);
			memcachedUtil.update(getLoginSessionIdKey(userId+""), sesssionId, SysBaseUtil.EXPIRY_TIME);
		}
		//打印缓存信息
		//debugCacheInfo(sessionId);
		return true;
	}


	/**
	 * 得到单位Id
	 */
	public long getLoginUnitId(String sessionId) {

		String unitIdStr = memcachedUtil.get(getLoginUnitIdKey(sessionId))+"";
		return MatcherUtil.checkNumber(unitIdStr)?Long.valueOf(unitIdStr):0;
	}

	/**
	 * 得到用户Id
	 */
	public long getLoginUserId(String sessionId) {
		
		String userIdStr = memcachedUtil.get(getLoginUserIdKey(sessionId))+"";
		return MatcherUtil.checkNumber(userIdStr)?Long.valueOf(userIdStr):0;
	}

	public SysUser getLoginUser(String sessionId) {

		SysUser sysUser = null;
		Object obj = null;
		obj = memcachedUtil.get(getLoginUserKey(sessionId));
		if (obj instanceof SysUser) {
			sysUser = (SysUser)obj;
		}
		return sysUser;
	}

	public SysUnits getLoginUnit(String sessionId) {

		SysUnits sysUnits = null;
		Object obj = null;
		//本地缓存
		obj = memcachedUtil.get(getLoginUnitKey(sessionId));
		if (obj instanceof SysUnits) {
			sysUnits = (SysUnits)obj;
		}
		return sysUnits;
	}

	public long getLoginSysRoleId(String sessionId) {
		//本地缓存
		String roleIdStr = memcachedUtil.get(getLoginSysRoleIdKey(sessionId))+"";
		return MatcherUtil.checkNumber(roleIdStr)?Long.valueOf(roleIdStr):0;
	}


	/**
	 * 调试缓存信息
	 * @param sessionId
	 */
	public void debugCacheInfo(String sessionId){

		logger.info("用户Id:"+getLoginUserId(sessionId));
		logger.info("用户信息："+getLoginUser(sessionId));
		logger.info("单位Id:"+getLoginUnitId(sessionId));
		logger.info("单位信息："+getLoginUnit(sessionId));
		logger.info("当前单位角色Id："+getLoginSysRoleId(sessionId));
		logger.info("sessionId："+getLoginSysRoleId(sessionId));
	}



	private String getLoginUserIdKey(String sessionId){

		return sessionId+MemcachedKeyPrefix.USER_ID;
	}

	private String getLoginUnitIdKey(String sessionId){

		return sessionId+MemcachedKeyPrefix.UNIT_ID;
	}

	private String getLoginUserKey(String sessionId){

		return sessionId+MemcachedKeyPrefix.USER;
	}


	private String getLoginSysRoleIdKey(String sessionId){

		return sessionId+MemcachedKeyPrefix.SYS_ROLE_ID;
	}

	private String getLoginUnitKey(String sessionId){

		return sessionId+MemcachedKeyPrefix.UNIT;
	}
	
	private String getLoginSessionIdKey(String userId){

		return "USER_ID_"+userId;
	}
}
