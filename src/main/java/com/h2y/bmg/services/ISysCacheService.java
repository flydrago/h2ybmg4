package com.h2y.bmg.services;

import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;


/**
 * 系统缓存接口
 */
public interface ISysCacheService {

	/**
	 * 更新用户缓存信息（登陆成功调用）
	 * @param session
	 * @param unitId
	 * @param sysUser
	 */
	public void updateUserCachInfo(String sessionId,long unitId,SysUser sysUser);
	
	
	/**
	 * 清除用户缓存信息
	 * @param session
	 */
	public void clearUserCachInfo(String sessionId);
	
	
	/**
	 * 用户是否登陆，如果已经登录，则更新有效时间
	 * @param session
	 * @return
	 */
	public boolean isUserLogin(String sessionId);
	
	
	/**
	 * 得到登陆用户单位Id
	 * @return
	 */
	public long getLoginUnitId(String sessionId);
	
	/**
	 * 得到登陆用户Id
	 * @return
	 */
	public long getLoginUserId(String sessionId);
	
	
    /**
     * 得到登录用户对象
     * @return
     */
	public SysUser getLoginUser(String sessionId);
	
	
    /**
     * 得到登录单位对象
     * @return
     */
	public SysUnits getLoginUnit(String sessionId);
	
    /**
     * 得到当前系统角色Id
     * @return
     */
	public long getLoginSysRoleId(String sessionId);
	
	
	/**
	 * 调试缓存信息
	 * @param session
	 */
	public void debugCacheInfo(String sessionId);
}
