package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.Storehouse;

/**
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */
@Component
public interface IJxcStorehouseDao{

	/**
	 * add
	 */
	public void add(Storehouse storehouse);
	
	/**
	 * update
	 */
	public void update(Storehouse storehouse);
	
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
	public Storehouse get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<Storehouse> getList(Storehouse storehouse);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<Storehouse> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	/**
	 * 分页查询仓库信息
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getListMap(Map<String, Object> map);

	/**
	 * 查询满足条件的仓库数目
	 * @param map
	 * @return
	 */
	public Long getListRows(Map<String, Object> map);
}