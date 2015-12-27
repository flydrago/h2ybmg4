package com.h2y.bmg.dao;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.SysShopFile;

/**
 * SysShopFileDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-06-15
 * email:info@hwttnet.com
 */
@Component
public interface ISysShopFileDao{

	/**
	 * add
	 */
	public void add(SysShopFile sysShopFile);

	/**
	 * update
	 */
	public void update(SysShopFile sysShopFile);

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
	public SysShopFile get(long id);


	/**
	 * getList
	 * @return
	 */
	public List<SysShopFile> getList(SysShopFile sysShopFile);


	/**
	 * 获取图片列表
	 * @param shopId
	 * @return
	 */
	public List<Map<String,Object>> getListByShopId(long shopId);


	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<SysShopFile> getListPage(Map<String,Object> map);

	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);


	/**
	 * 批量添加图片信息
	 * @param list
	 */
	public void addBatch(List<SysShopFile> list);


	/**
	 * 根据shopId 删除门店图片
	 * @param shopId
	 * @return
	 */
	public int updateByShopId(long shopId);

}