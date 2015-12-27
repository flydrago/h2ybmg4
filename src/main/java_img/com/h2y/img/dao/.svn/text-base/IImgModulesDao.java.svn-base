package com.h2y.img.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.img.model.ImgModules;

/**
 * ImgModulesDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-10-11
 */
@Component
public interface IImgModulesDao{

	/**
	 * add
	 */
	public void add(ImgModules imgModules);
	
	/**
	 * update
	 */
	public void update(ImgModules imgModules);
	
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
	public ImgModules get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<ImgModules> getList(ImgModules imgModules);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<ImgModules> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	/**
	 * 分页获取 图片类型 列表数据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getListMap(Map<String, Object> map);

	/**
	 * 获取 图片类型 总数
	 * @param map
	 * @return
	 */
	public long getListRows(Map<String, Object> map);

}