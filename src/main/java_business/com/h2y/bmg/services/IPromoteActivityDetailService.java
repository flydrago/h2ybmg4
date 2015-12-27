package com.h2y.bmg.services;



import com.h2y.bmg.entity.PromoteActivityDetail;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-08-13
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface IPromoteActivityDetailService{
	
	public void add(PromoteActivityDetail promoteActivityDetail);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(PromoteActivityDetail promoteActivityDetail);

	public PromoteActivityDetail get(long id);
	
	/**
	 * 根据活动id获取活动明细
	 * @param promoteId
	 * @return
	 */
	public PromoteActivityDetail getByPromoteId(long promoteId);

}
