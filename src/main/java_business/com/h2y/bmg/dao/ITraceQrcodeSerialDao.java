package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.TraceQrcodeSerial;

/**
 * TraceQrcodeSerialDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 */
@Component
public interface ITraceQrcodeSerialDao{

	/**
	 * add
	 */
	public void add(TraceQrcodeSerial traceQrcodeSerial);
	
	/**
	 * update
	 */
	public void update(TraceQrcodeSerial traceQrcodeSerial);
	
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
	public TraceQrcodeSerial get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<TraceQrcodeSerial> getList(TraceQrcodeSerial traceQrcodeSerial);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<TraceQrcodeSerial> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);
	
	
	public void patchAdd(List<TraceQrcodeSerial> traceQrcodeSerialList);

	public List<TraceQrcodeSerial> getListData(Map<String, Object> dataMap);

	public List<Map<String, Object>> getListByPage(Map<String, Object> map);
}