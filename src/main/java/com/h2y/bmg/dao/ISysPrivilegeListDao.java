package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.SysPrivilegeList;

/**
 * SysPrivilegeListDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2014-10-20
 * email:info@hwttnet.com
 */
@Component
public interface ISysPrivilegeListDao{

	/**
	 * add
	 */
	public void add(SysPrivilegeList sysPrivilegeList);

	/**
	 * update
	 */
	public void update(SysPrivilegeList sysPrivilegeList);

	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * 批量删除，Id用户逗点分割
	 * @param Ids
	 */
	public void deleteByIds(List<Long> Ids);

	/**
	 * get
	 * @return
	 */
	public SysPrivilegeList get(long id);


	/**
	 * 批量添加
	 * @param list
	 */
	public void addBatch(List<SysPrivilegeList> list);


	/**
	 * 删除权限
	 * @param map
	 * key: master value:主权限（role,）
	 * key: masterValue value:对应主权限Id
	 * key: access value: 
	 * key: accessValueList value:
	 */
	public void deleteByAccessList(Map<String,Object> map);

	/**
	 * 删除权限
	 * @param map
	 * key: master value:主权限（role,）
	 * key: masterValueList value:集合
	 * key: access value: 
	 * key: accessValueList value: 
	 */
	public void deleteByMasterList(Map<String,Object> map);

	/**
	 * 得到用户角色Id集合
	 * @param userId 用户Id
	 */
	public List<Long> getRoleIdsByUserId(long userId);

	/**
	 * 得到系统的角色Id
	 * @param unitId
	 * @return
	 */
	public Long getSysRoleIdByUnitId(long unitId);


	/**
	 * 根据公司id获取
	 * @param unitId
	 * @return
	 */
	public SysPrivilegeList getPrivilegeByUnitId(Map<String,Object> map);


}