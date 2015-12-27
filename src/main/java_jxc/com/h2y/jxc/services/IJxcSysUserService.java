package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import com.h2y.jxc.entity.SysUser;

/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface IJxcSysUserService{
	
	public void add(SysUser sysUser);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(SysUser sysUser);

	public SysUser get(long id);
	
	public List<SysUser> getList(SysUser sysUser);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<SysUser> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);
}
