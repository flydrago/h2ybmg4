package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.TraceProviderItem;

/**
 * TraceProviderItemDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 */
@Component
public interface ITraceProviderItemDao{

	/**
	 * add
	 */
	public void add(TraceProviderItem traceProviderItem);
	
	/**
	 * update
	 */
	public void update(TraceProviderItem traceProviderItem);
	
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
	public TraceProviderItem get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<TraceProviderItem> getList(TraceProviderItem traceProviderItem);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<TraceProviderItem> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	public List<Map<String, Object>> getListByPage(Map<String, Object> map);

	public void updateStatusByIds(Map<String, Object> dataMap);
}