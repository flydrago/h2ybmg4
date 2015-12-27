package com.h2y.bmg.services;

import com.h2y.bmg.entity.SysPrivilegeList;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2014-10-20
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ISysPrivilegeListService{
	
	public void add(SysPrivilegeList sysPrivilegeList);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(SysPrivilegeList sysPrivilegeList);

	public SysPrivilegeList get(long id);
}
