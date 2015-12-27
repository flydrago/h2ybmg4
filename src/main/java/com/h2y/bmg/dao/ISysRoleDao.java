package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.SysRole;

/**
 * SysRoleDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2014-10-20
 * email:info@hwttnet.com
 */
@Component
public interface ISysRoleDao{

	/**
	 * add
	 */
	public void add(SysRole sysRole);
	
	/**
	 * update
	 */
	public void update(SysRole sysRole);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * deleteByIds
	 */
	//public void deleteByIds(List<long> ids);
	
	/**
	 * get
	 * @return
	 */
	public SysRole get(long id);
	
	/**
	 * 得到角色的分页列表
	 * @param map
	 * key:unitId value:单位Id
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	
	/**
	 * 得到角色的总行数
	 * @param map
	 * key:unitId value:单位Id
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
	
	/**
	 * 得到相同编码的行数
	 * @param map
	 * key1:roleCode value1:角色编码
	 * key2:unitId value2:单位Id
	 * key3:id value3:主键Id，null时不做判断
	 * @return
	 */
	public long getSameCodeRows(Map<String,Object> map);
	
	
	/**
	 * 得到角色数列表
	 * @param map 
	 * key:unitId value:单位Id
	 * @return
	 */
	public List<Map<String,Object>> getMenuRoleTreeList(Map<String,Object> map);
	
	
	/**
	 * 得到用户角色树数据
	 * @param map
	 * key1:unitId value1:单位Id
	 * @return
	 */
	public List<Map<String,Object>> getUserRoleTreeList(Map<String,Object> map);
	
	
	/**
	 * 得到角色所具有菜单分页列表（须加权限控制）
	 * @param map
	 * key:unitId value:单位Id
	 * key:roleId value:角色Id
	 * key:pId value:父级菜单Id
	 * key:sysRoleId value:系统角色Id
	 * key:page,pagesize value:null时不做判断
	 * @return
	 */
	public List<Map<String,Object>> getRoleGridMenuList(Map<String,Object> map);
	
	/**
	 * 得到角色所具有菜单列表总数（须加权限控制）
	 * @param map
	 * key:unitId value:单位Id
	 * key:roleId value:角色Id
	 * key:pId value:父级菜单Id
	 * key:sysRoleId value:系统角色Id
	 * @return
	 */
	public long getRoleGridMenuRows(Map<String,Object> map);
	
	
	/**
	 * 得到角色所具有的按钮列表
	 * @param map
   	 * key:unitId value:单位Id
	 * key:roleId value:角色Id
	 * key:menuId value:菜单Id
	 * key:sysRoleId value:系统角色Id
	 * @return
	 */
	public List<Map<String,Object>> getRoleButtonMenuList(Map<String,Object> map);
	
	
	/**
	 * 得到角色用户分页列表
	 * @param map
	 * key1:roleId value1:角色Id
	 * key2:unitId value2:单位Id
	 * key3:userName value3:模糊查询，null：不做判断
	 * @return
	 */
	public List<Map<String,Object>> getRoleUserList(Map<String,Object> map);
	
	
	/**
	 * 得到角色用户列表总行数
	 * @param map
	 * key1:roleId value1:角色Id
	 * key2:unitId value2:单位Id
	 * key3:userName value3:模糊查询，null：不做判断
	 * @return
	 */
	public long getRoleUserRows(Map<String,Object> map);
	
	
	/**
	 * 得到单位角色列表
	 * @return
	 */
	public List<Map<String,Object>> getUnitRoleList();
}