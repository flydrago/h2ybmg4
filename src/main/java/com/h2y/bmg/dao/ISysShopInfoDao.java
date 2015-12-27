package com.h2y.bmg.dao;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.SysShopInfo;

/**
 * SysShopInfoDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-06-16
 * email:info@hwttnet.com
 */
@Component
public interface ISysShopInfoDao{

	/**
	 * add
	 */
	public void add(SysShopInfo sysShopInfo);

	/**
	 * update
	 */
	public void update(SysShopInfo sysShopInfo);

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
	public SysShopInfo get(long id);


	/**
	 * 根据shopId 获取
	 * @param id
	 * @return
	 */
	public SysShopInfo getByShopId(long id);

	/**
	 * getList
	 * @return
	 */
	public List<SysShopInfo> getList(SysShopInfo sysShopInfo);


	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<SysShopInfo> getListPage(Map<String,Object> map);

	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);
}