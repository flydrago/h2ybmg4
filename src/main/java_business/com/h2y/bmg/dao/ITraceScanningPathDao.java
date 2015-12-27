package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.TraceScanningPath;

/**
 * TraceScanningPathDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 */
@Component
public interface ITraceScanningPathDao{

	/**
	 * add
	 */
	public void add(TraceScanningPath traceScanningPath);
	
	/**
	 * update
	 */
	public void update(TraceScanningPath traceScanningPath);
	
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
	public TraceScanningPath get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<TraceScanningPath> getList(TraceScanningPath traceScanningPath);
	
	/**
	 * getList
	 * @return
	 */
	public List<Map<String,Object>> getListData(Long parentId);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<TraceScanningPath> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	public void addBatch(List<TraceScanningPath> fileDataList);
}