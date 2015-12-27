package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import com.h2y.jxc.entity.SysUser;

/**
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */
@Component
public interface IJxcSysUserDao{

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
	 * deleteByIds
	 */
	//public void deleteByIds(List<long> ids);
	
	/**
	 * get
	 * @return
	 */
	public SysUser get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<SysUser> getList(SysUser sysUser);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<SysUser> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);
}