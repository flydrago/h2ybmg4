package com.h2y.bmg.dao;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.PromoteActivity;


/**
 * PromoteActivityDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-08-13
 * email:info@hwttnet.com
 */
@Component
public interface IPromoteActivityDao{

	/**
	 * add
	 */
	public int add(PromoteActivity promoteActivity);
	
	/**
	 * update
	 */
	public int update(PromoteActivity promoteActivity);
	
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
	public PromoteActivity get(long id);
	
	/**
	 * 获取列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	
	public long getListRows(Map<String,Object> map);
	

	/**
	 * 查看活动人员列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getUserRtList(Map<String,Object> map);
	
	
	public long getUserRtListRows(Map<String,Object> map);
	
	/**
	 * 获取活动列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getPromoteList(Map<String,Object> map);
	
	
	public long getPromoteListRows(Map<String,Object> map);
	
	
	/**
	 * 获取推广活动选择列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getSelectListMap(Map<String,Object> map);
	
	public long getSelectListRows(Map<String,Object> map);
}