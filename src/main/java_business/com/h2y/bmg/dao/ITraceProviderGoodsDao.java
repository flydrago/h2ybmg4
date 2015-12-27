package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.TraceProviderGoods;

/**
 * TraceProviderGoodsDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 */
@Component
public interface ITraceProviderGoodsDao{

	/**
	 * add
	 */
	public void add(TraceProviderGoods traceProviderGoods);
	
	/**
	 * update
	 */
	public void update(TraceProviderGoods traceProviderGoods);
	
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
	public TraceProviderGoods get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<TraceProviderGoods> getList(TraceProviderGoods traceProviderGoods);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<TraceProviderGoods> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	public List<Map<String, Object>> getListByPage(Map<String, Object> map);

	public List<Map<String, Object>> getProviderTreeList(Map<String, Object> params);

	public List<TraceProviderGoods> getGoodsIdList(Map<String, Object> map);

	public int updateStatueByIds(Map<String, Object> dataMap);
}