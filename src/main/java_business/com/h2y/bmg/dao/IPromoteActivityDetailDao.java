package com.h2y.bmg.dao;


import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.PromoteActivityDetail;

/**
 * PromoteActivityDetailDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-08-13
 * email:info@hwttnet.com
 */
@Component
public interface IPromoteActivityDetailDao{

	/**
	 * add
	 */
	public int add(PromoteActivityDetail promoteActivityDetail);
	
	/**
	 * update
	 */
	public int update(PromoteActivityDetail promoteActivityDetail);
	
	/**
	 * delete
	 */
	public int deleteById(long id);

	/**
	 * deleteByIds
	 */
	//public void deleteByIds(List<long> ids);
	
	/**
	 * get
	 * @return
	 */
	public PromoteActivityDetail get(long id);
	
	
	/**
	 * 根据活动id获取活动明细
	 * @param promoteId
	 * @return
	 */
	public PromoteActivityDetail getByPromoteId(long promoteId);
	
	
	
}