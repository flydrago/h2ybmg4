package com.h2y.img.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.img.model.ImgUsage;

/**
 * ImgUsageDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-10-11
 * email:info@hwttnet.com
 */
@Component
public interface IImgUsageDao{

	/**
	 * add
	 */
	public void add(ImgUsage imgUsage);
	
	/**
	 * update
	 */
	public void update(ImgUsage imgUsage);
	
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
	public ImgUsage get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<ImgUsage> getList(ImgUsage imgUsage);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<ImgUsage> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	public List<Map<String, Object>> getListMap(Map<String, Object> map);

	public long getListRows(Map<String, Object> map);
}