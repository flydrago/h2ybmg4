package com.h2y.img.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.img.model.ImgStorage;

/**
 * ImgStorageDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-10-11
 * email:info@hwttnet.com
 */
@Component
public interface IImgStorageDao{

	/**
	 * add
	 */
	public void add(ImgStorage imgStorage);
	
	/**
	 * update
	 */
	public void update(ImgStorage imgStorage);
	
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
	public ImgStorage get(long id);
	
	/**
	 * 得到列表
	 * @param params
	 * {moduleCode:模块编码,
	 * usageCode:用途编码}
	 * @return
	 */
	public List<Map<String,Object>> getSelectListMap(Map<String,Object> params);
	
	/**
	 * 得到总数
	 * @param params
	 * {moduleCode:模块编码,
	 * usageCode:用途编码}
	 * @return 
	 */  
	public long getSelectListRows(Map<String,Object> map);

	/**
	 * 获取图片库表格数据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getListMap(Map<String, Object> map);

	/**
	 * 获取图片库图片总数
	 * @param map
	 * @return
	 */
	public long getListRows(Map<String, Object> map);
}