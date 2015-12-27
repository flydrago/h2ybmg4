package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.TraceNumberSection;
import com.h2y.bmg.entity.TraceQrcodeSerial;

/**
 * TraceNumberSectionDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-06-30
 * email:info@hwttnet.com
 */
@Component
public interface ITraceNumberSectionDao{

	/**
	 * add
	 */
	public void add(TraceNumberSection traceNumberSection);
	
	/**
	 * update
	 */
	public void update(TraceNumberSection traceNumberSection);
	
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
	public TraceNumberSection get(Long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<TraceNumberSection> getList(TraceNumberSection traceNumberSection);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<TraceNumberSection> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	public List<Map<String, Object>> getListByPage(Map<String, Object> map);
	public List<TraceNumberSection> getListData(Map<String, Object> map);

	public List<Map<String, Object>> getSelectProvinceListMap(Map<String, Object> map);

	public long getSelectProvinceRows(Map<String, Object> map);

	public List<Map<String, Object>> getSelectProviderListMap(Map<String, Object> map);

	public long getSelectProviderRows(Map<String, Object> map);
}