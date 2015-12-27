package com.h2y.bmg.services;

import com.h2y.bmg.entity.SysDeptUser;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2014-10-19
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ISysDeptUserService{
	
	public void add(SysDeptUser sysDeptUser);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(SysDeptUser sysDeptUser);

	public SysDeptUser get(long id);
	
	/**
	 * 根据用户Id，删除用户部门关联
	 * @param userId
	 */
	public void deleteByUserId(long userId);
}
