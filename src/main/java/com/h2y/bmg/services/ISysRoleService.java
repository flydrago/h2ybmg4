package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.entity.SysRole;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2014-10-20
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ISysRoleService{
	
	public void add(SysRole sysRole);
	
	public void delete(long id);
	
	public void update(SysRole sysRole);

	public SysRole get(long id);
	
	/**
	 * 得到gird列表
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @param sysRoleId 系统角色Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId,long sysRoleId,long userId);
	
	
	/**
	 * 是否有相同编码
	 * @param sysRole
	 * @return
	 */
	public boolean isHasSameCode(SysRole sysRole,String op);
	
	/**
	 * 根据单位Id，得到角色Id
	 * @param unitId 单位Id
	 * @param isHasSysRole 是否包含系统内置角色
	 * @return
	 */
	public List<Map<String,Object>> getRoleTreeData(String op,long unitId,long userId);
	
	
	/**
	 * 保存权限
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @param roleId 角色Id
	 */
	@Transactional(rollbackFor=Exception.class)
	public void savePrivilege(HttpServletRequest request,long unitId,long roleId);
	
	/**
	 * 保存角色用户
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @param roleId 角色Id
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveRoleUser(HttpServletRequest request,long unitId,long roleId);
	
	
	
	/**
	 * 移除角色用户
	 * @param request 访问对象
	 */
	public void removeRoleUser(HttpServletRequest request);
}
