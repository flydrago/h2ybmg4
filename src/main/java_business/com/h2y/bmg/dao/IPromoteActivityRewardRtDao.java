package com.h2y.bmg.dao;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.PromoteActivityRewardRt;

/**
 * PromoteActivityRewardRtDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-08-17
 * email:info@hwttnet.com
 */
@Component
public interface IPromoteActivityRewardRtDao{

	/**
	 * add
	 */
	public int add(PromoteActivityRewardRt promoteActivityRewardRt);
	
	/**
	 * update
	 */
	public int update(PromoteActivityRewardRt promoteActivityRewardRt);
	
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
	public PromoteActivityRewardRt get(long id);
	
	//根据活动id获取
	public PromoteActivityRewardRt getByPromoteId(long promoteId);
	
	
	/**
	 * 获取列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	
	public long getListRows(Map<String,Object> map);
	
	
	/**
	 * 判断该数据是否已经维护
	 * @param map
	 * @return
	 */
	public long getSameDataList(Map<String,Object> map);
	
	

}