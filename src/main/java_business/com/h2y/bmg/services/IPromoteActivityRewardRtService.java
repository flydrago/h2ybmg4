package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.PromoteActivityRewardRt;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-08-17
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface IPromoteActivityRewardRtService{
	
	public void add(PromoteActivityRewardRt promoteActivityRewardRt);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(PromoteActivityRewardRt promoteActivityRewardRt);

	public PromoteActivityRewardRt get(long id);
	
	/**
	 * 根据活动id获取
	 * @param promoteId
	 * @return
	 */
	public PromoteActivityRewardRt getByPromoteId(long promoteId);
	
	
	/**
	 * 获取奖励列表
	 * @param request
	 * @param unitId
	 * @return
	 */
	public Map<String, Object> getGridData(HttpServletRequest request);
	
	
	public void save(HttpServletRequest request,String op,PromoteActivityRewardRt promoteActivityRewardRt);
	
	
	/**
	 * 判断该数据是否已经维护
	 * @param map
	 * @return
	 */
	public long getSameDataList(Map<String,Object> map);
	
}
