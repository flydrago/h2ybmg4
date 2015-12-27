package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.SysUser;

/**
 * SysUserDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2014-10-19
 * email:info@hwttnet.com
 */
@Component
public interface ISysUserDao{

	/**
	 * add
	 */
	public void add(SysUser sysUser);
	
	/**
	 * update
	 */
	public void update(SysUser sysUser);
	
	/**
	 * delete
	 */
	public void deleteById(long id);
	
	/**
	 * get
	 * @return
	 */
	public SysUser get(long id);
	
	
	/**
	 * 得到用户分页列表
	 * @param map
	 * key1:deptId value1:部门Id
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	
	/**
	 * 得到用户总数
	 * @param map
	 * key1:deptId value1:部门Id
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
	
	
	
	/**
	 * 得到相同账号的行数（用于判断重复账号）
	 * @param map
	 * key:#{account},#{unitId},#{id} value:#{账户},#{单位Id},#{主键，null时，不做判断}
	 * @return
	 */
	public long getSameAcountRows(Map<String,Object> map);
	
	
	/**
	 * 根据用户Id，得到用户部门关联表的记录数（用于判断移除用户）
	 * @param map
	 * @return
	 */
	public long getDeptUserRowsByUserId(Map<String,Object> map);
	
	/**
	 * 得到选择窗口中的用户列表
	 * @param map
	 * key1:unitId value1:单位Id
	 * key1:operator value1:操作符（级联：like,反之：=）
	 * key1:deptCode value1:部门编码
	 * @return
	 */
	public List<Map<String,Object>> getSelectDialogUserList(Map<String,Object> map);
	
	
	/**
	 * 得到登陆验证的列表
	 * @param map
	 * key1:unitId value1:单位Id
	 * key2:account value2:账户
	 * key3:password value3:密码
	 * @return
	 */
	public List<SysUser> getLoginCheckUserList(Map<String,Object> map);
	
	/**
	 * 根据用户Id，得到部门信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getDeptInfoByUserId(Map<String,Object> map);
	
	
	/**
	 * 根据账号和单位id，获取用户信息
	 * @param map
	 * {unitId:单位Id,
	 * account:用户账号}
	 * @return
	 */
	public SysUser getUserByAccountAndUnitId(Map<String,Object> map);
}