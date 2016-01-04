package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.basic.WbsKeys.SInvokeKeys;
import com.h2y.bmg.dao.ISysDeptUserDao;
import com.h2y.bmg.dao.ISysUnitsDao;
import com.h2y.bmg.dao.ISysUserDao;
import com.h2y.bmg.entity.BaseResult;
import com.h2y.bmg.entity.SysDepartment;
import com.h2y.bmg.entity.SysDeptUser;
import com.h2y.bmg.entity.SysDictDetail;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.util.SysBaseUtil.SmsContentTemplate;
import com.h2y.dict.DictUtil;
import com.h2y.security.MD5Util;
import com.h2y.util.DataRequestUtil;
import com.h2y.util.DateUtil;
import com.h2y.util.FreeMarkerUtil;

/**
 * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2014-10-19
 * email:info@hwttnet.com
 */
@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService{

	private static Logger logger = Logger.getLogger(SysUserServiceImpl.class);

	@Autowired
	protected ISysUserDao sysUserDao;

	@Autowired
	protected ISysDeptUserService sysDeptUserService;

	@Autowired
	protected ISysDepartmentService sysDepartmentService;

	@Autowired
	protected ISysDeptUserDao sysDeptUserDao;

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected ISysUnitsDao sysUnitsDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param sysUser
	 *
	 */
	public void add(SysUser sysUser) {
		// TODO Auto-generated method stub

		sysUserDao.add(sysUser);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		sysUserDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	sysUserDao.deleteByIds(ids);
	//}

	public void update(SysUser sysUser) {
		// TODO Auto-generated method stub
		sysUserDao.update(sysUser);
	}

	public SysUser get(long id) {
		// TODO Auto-generated method stub
		return sysUserDao.get(id);
	}



	/**
	 * 得到gird列表
	 * @param request 访问对象
	 * @param deptId 部门Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long deptId){
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");


		String isCascade = request.getParameter("isCascade");
		String deptCode = request.getParameter("deptCode");
		String userName = request.getParameter("userName");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");

		if (sortname!=null && !sortname.equals("")) {

			if (sortname.equals("ACCOUNT")) {
				sortname = "u.account";
			}else if (sortname.equals("USER_NAME")) {
				sortname = "u.user_name";
			}else if (sortname.equals("USER_CORD")) {
				sortname = "u.user_cord";
			}else if (sortname.equals("MOBILE")) {
				sortname = "u.mobile";
			}else if (sortname.equals("EMAIL")) {
				sortname = "u.email";
			}else if (sortname.equals("DU_ORD")) {
				sortname = "du.du_ord";
			}else if (sortname.equals("IF_LOCK")) {
				sortname = "u.if_lock";
			}else if (sortname.equals("USER_LEVEL")) {
				sortname = "du.user_level";
			}else {
				sortname = "du.du_ord";
			}
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sortname", sortname);
		map.put("sortorder", sortorder);
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("deptId",deptId);

		if (deptCode.contains("_")) {
			map.put("deptCode", deptCode+"%");
		}else{
			map.put("deptCode", deptCode+"_%");
		}
		map.put("isCascade", isCascade);
		if (StringUtils.isNotBlank(userName)) {
			map.put("userName", "%"+userName+"%");
		}
		if (StringUtils.isNotBlank(mobile)) {
			map.put("mobile", "%"+mobile+"%");
		}

		if (StringUtils.isNotBlank(email)) {
			map.put("email", "%"+email+"%");
		}


		Map<String,Object> gridData = new HashMap<String, Object>();

		List<Map<String,Object>> dataList = sysUserDao.getListMap(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", sysUserDao.getListRows(map));
		return gridData;
	}


	/**
	 * 保存
	 * @param sysUser
	 * @param op
	 */
	public void save(SysUser sysUser,SysDeptUser sysDeptUser,String op){

		if (op.equals("add")) {

			//String pwd = (int)(Math.random()*1000000)+"";
			String pwd = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));

			sysUser.setPassword(MD5Util.getMD5("123456"));
			sysUserDao.add(sysUser);

			sysDeptUser.setUserId(sysUser.getId());
			SysDepartment sysDepartment = sysDepartmentService.get(sysDeptUser.getDeptId());
			sysDeptUser.setDeptCode(sysDepartment.getDeptCode());
			sysDeptUserService.add(sysDeptUser);

		}else {

			SysUser sysUser2 = sysUserDao.get(sysUser.getId());
			sysUser2.setAccount(sysUser.getAccount());
			sysUser2.setEmail(sysUser.getEmail());
			sysUser2.setIfLock(sysUser.getIfLock());
			sysUser2.setReverse1(sysUser.getReverse1());
			sysUser2.setReverse2(sysUser.getReverse2());
			sysUser2.setReverse3(sysUser.getReverse3());
			sysUser2.setUserName(sysUser.getUserName());
			sysUser2.setUserCord(sysUser.getUserCord());
			sysUser2.setUpdateDate(DateUtil.getSystemTime());
			sysUser2.setMobile(sysUser.getMobile());
			sysUserDao.update(sysUser2);

			SysDeptUser sysDeptUser2 = sysDeptUserService.get(sysDeptUser.getId());
			sysDeptUser2.setDuOrd(sysDeptUser.getDuOrd());
			sysDeptUser2.setUserLevel(sysDeptUser.getUserLevel());
			sysDeptUserService.update(sysDeptUser2);
		}
	}


	/**
	 * 判断是否有重复的账户
	 * @param sysUser
	 * @return
	 */
	public boolean isHasSameAcount(SysUser sysUser,String op){

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("account", sysUser.getAccount());
		map.put("unitId", sysUser.getUnitId());
		if (op.equals("modify")) {
			map.put("id", sysUser.getId());
		}
		return sysUserDao.getSameAcountRows(map)>0;
	}




	/**
	 * 判断用户移除的部门是不是最后一个部门
	 * @param userId 用户Id
	 * @return
	 */
	public boolean isLastDeptForUser(long userId){

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);

		return sysUserDao.getDeptUserRowsByUserId(map)<=1;
	}


	/**
	 * 得到登陆验证用户
	 * @param unitId 单位Id
	 * @param account 账户
	 * @param password 密码
	 * @return 有则返回对象，反之为null
	 */
	public SysUser getLoginCheckUser(long unitId,String account,String password){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("unitId", unitId);
		params.put("account", account.split("@")[0]);
		//params.put("password", MD5Util.getMD5(password));

		List<SysUser> sysUserList = sysUserDao.getLoginCheckUserList(params);

		if (sysUserList!=null && !sysUserList.isEmpty()) {
			return sysUserList.get(0);
		}else {
			return null;
		}
	}


	public Long getUnitByDoMain(String account){		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("unitDomain", account.split("@")[1]);
		List<Map<String,Object>> list = this.sysUnitsDao.getUnitRowsBySame(params);
		if(null != list && list.size() > 0){
			return (Long) list.get(0).get("id");
		}
		return 0L;
	}


	/**
	 * 移动人员
	 * @param requst 访问对象
	 * @param op 操作类型,in:移入人员、out:移除人员
	 */
	public void move(HttpServletRequest request,String op){

		if (op.equals("in")) {

			String deptId = request.getParameter("deptId");
			String deptCode = request.getParameter("deptCode");
			String userIds = request.getParameter("userIds");
			String [] userId_arrray = userIds.split(",");
			if(userId_arrray.length==0){
				return;
			}

			//删除已有的用户
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("deptId", deptId);
			params.put("userIds", userIds);
			sysDeptUserDao.deleteByDeptIdAndUserIds(params);
			
			
			SysDepartment sysDepartment = sysDepartmentService.get(Long.valueOf(deptId));
			SysUnits sysUnits = sysUnitsDao.get(sysDepartment.getUnitId());

			//添加人员
			List<SysDeptUser> sysDeptUsers = new ArrayList<SysDeptUser>();
			for (String userId : userId_arrray) {

				SysDeptUser sysDeptUser = new SysDeptUser();
				sysDeptUser.setDeptCode(deptCode);
				sysDeptUser.setDeptId(Long.valueOf(deptId));
				sysDeptUser.setDuOrd(0l);
				sysDeptUser.setUserId(Long.valueOf(userId));
				sysDeptUser.setUserLevel(0l);
				sysDeptUsers.add(sysDeptUser);
				
				SysUser sysUser = sysUserDao.get(Long.valueOf(userId));
				
				//更新象过河人员对接
				updateUserXgh(sysUnits, sysDepartment, sysUser);
			}
			sysDeptUserDao.addBatch(sysDeptUsers);
			
			
			
		}else{
			
			String deptUserId = request.getParameter("deptUserId");
			SysDeptUser sysDeptUser = sysDeptUserDao.get(Long.valueOf(deptUserId));
			sysDeptUserDao.deleteById(Long.valueOf(deptUserId));
			
			List<SysDeptUser> deptUserList = sysDeptUserDao.getListByUserId(sysDeptUser.getUserId());
			SysDeptUser sysDeptUser2 = deptUserList.get(0);
			//部门
			SysDepartment sysDepartment = sysDepartmentService.get(sysDeptUser2.getDeptId());
			SysUnits sysUnits = sysUnitsDao.get(sysDepartment.getUnitId());
			SysUser sysUser = sysUserDao.get(sysDeptUser2.getUserId());
			
			//更新象过河人员对接
			updateUserXgh(sysUnits, sysDepartment, sysUser);
		}
	}


	/**
	 * 根据用户Id，得到部门信息
	 * @param userId
	 */
	public String getDeptInfoByUserId(long userId){

		String deptInfo = "";
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		List<Map<String,Object>> deptInfos = sysUserDao.getDeptInfoByUserId(map);

		if (deptInfos!=null) {

			for (Map<String, Object> map2 : deptInfos) {

				if (deptInfo.equals("")) {
					deptInfo = map2.get("dept_name")+"";
				}else {
					deptInfo += "，"+map2.get("dept_name");
				}
			}
		}
		return deptInfo;
	}
	
	
	public void updateUserXgh(SysUnits sysUnits,SysDepartment sysDepartment,SysUser sysUser){
		
		if (null==sysUnits.getS3ucode() || "".equals(sysUnits.getS3ucode()) || null==sysDepartment.getXghdcode() || "".equals(sysDepartment.getXghdcode())) {
			
			logger.error("人员象过河更新失败！信息为：单位或部门的象过河编码为空！");
			return ;
		}
		
		Map<String,Object> postData = new HashMap<String, Object>();
		postData.put("unitid", sysUnits.getId());
		postData.put("unitcode", sysUnits.getUnitCode());
		postData.put("xghucode", sysUnits.getS3ucode());
		postData.put("deptid", sysDepartment.getId());
		postData.put("xghdcode", sysDepartment.getXghdcode());
		
		postData.put("xghusercode", null==sysUser.getXghusercode()?"":sysUser.getXghusercode());
		postData.put("userid", sysUser.getId());
		postData.put("username", sysUser.getUserName());
		postData.put("account", sysUser.getAccount());
		postData.put("password", null==sysUser.getXghusercode()?"123456":"");
		postData.put("islock", sysUser.getIfLock());
		postData.put("ord", sysUser.getUserOrd());
		
		//象过河wbs方法名
		postData.put("method", null==sysUser.getXghusercode() || "".equals(sysUser.getXghusercode())?"addUser":"updateUser");
		
		BaseResult baseResult = DataRequestUtil.getCommonRequestData("xgh/common.htm", postData);
		
		
		if (baseResult.getResultFlag()==1) {
			
			logger.debug("人员象过河更新返回信息为："+baseResult.getResultData());
			@SuppressWarnings("unchecked")
			Map<String,Object> resultData = (Map<String, Object>) baseResult.getResultData();
			if (null!=resultData.get("xghusercode")) {
				sysUser.setXghusercode(resultData.get("xghusercode")+"");
			}
			
			if (null!=resultData.get("xghcreatedate")) {
				sysUser.setXghcreatedate(DateUtil.toDate(resultData.get("xghcreatedate")+"", DateUtil.YYYY_MM_DD_HH_MM_SS));
			}
			sysUserDao.update(sysUser);
		}else {
			
			logger.error("人员象过河更新失败！信息为："+baseResult.getResultMsg());
		}
	}
}