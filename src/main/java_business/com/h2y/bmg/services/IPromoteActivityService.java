package com.h2y.bmg.services;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.PromoteActivity;
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
public interface IPromoteActivityService{
	
	
	public Map<String, Object> getGridData(HttpServletRequest request,long unitId);
	
	public void add(PromoteActivity promoteActivity);
	
	/**
	 * 保存
	 * @param promoteActivity
	 * @return
	 */
	public void save(HttpServletRequest request,String op,PromoteActivity promoteActivity,PromoteActivityDetail promoteActivityDetail,String dictPath);
	
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(PromoteActivity promoteActivity);

	public PromoteActivity get(long id);
	
	/**
	 * 查看活动人员列表
	 */
	public Map<String, Object> getUserRtList(HttpServletRequest request);
	
	
	public Map<String, Object> getSelectGridData(HttpServletRequest request,long unitId);
}
