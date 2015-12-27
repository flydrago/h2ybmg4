package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.SysDeptUser;
import com.h2y.bmg.entity.SysUser;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2014-10-19
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ISysUserService{

	public void add(SysUser sysUser);

	public void delete(long id);

	public void update(SysUser sysUser);

	public SysUser get(long id);

	/**
	 * 得到gird列表
	 * @param request 访问对象
	 * @param deptId 部门Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long deptId);


	/**
	 * 保存
	 * @param sysUser
	 * @param op
	 */
	public void save(SysUser sysUser,SysDeptUser sysDeptUser,String op);

	/**
	 * 判断是否有重复的账户
	 * @param sysUser
	 * @return
	 */
	public boolean isHasSameAcount(SysUser sysUser,String op);


	/**
	 * 判断用户移除的部门是不是最后一个部门
	 * @param userId 用户Id
	 * @return
	 */
	public boolean isLastDeptForUser(long userId);


	/**
	 * 得到登陆验证用户
	 * @param unitId 单位Id
	 * @param account 账户
	 * @param password 密码
	 * @return 有则返回对象，反之为null
	 */
	public SysUser getLoginCheckUser(long unitId,String account,String password);


	/**
	 * 根据域名获取公司id
	 * @param account
	 * @return
	 */
	public Long getUnitByDoMain(String account);

	/**
	 * 移动人员
	 * @param requst 访问对象
	 * @param op 操作类型,in:移入人员、out:移除人员
	 */
	public void move(HttpServletRequest request,String op);

	/**
	 * 根据用户Id，得到部门信息
	 * @param userId
	 */
	public String getDeptInfoByUserId(long userId);
}
