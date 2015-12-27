package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.TraceCirculateRecord;

/**
 * TraceCirculateRecordDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 */
@Component
public interface ITraceCirculateRecordDao{

	/**
	 * add
	 */
	public void add(TraceCirculateRecord traceCirculateRecord);
	
	/**
	 * update
	 */
	public void update(TraceCirculateRecord traceCirculateRecord);
	
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
	public TraceCirculateRecord get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<TraceCirculateRecord> getList(TraceCirculateRecord traceCirculateRecord);
	
	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<TraceCirculateRecord> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	public void addPatch(List<TraceCirculateRecord> traceCirculateRecordList);

	public List<TraceCirculateRecord> getListData(Map<String, Object> map);

	public void updateNewRecord(Map<String, Object> map);

	public List<Map<String, Object>> getListDataByPage(Map<String, Object> map);
}