package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.StorehouseSubarea;
/**
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */
@Component
public interface IJxcStorehouseSubareaDao{

	/**
	 * add
	 */
	public void add(StorehouseSubarea storehouseSubarea);
	
	/**
	 * update
	 */
	public void update(StorehouseSubarea storehouseSubarea);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	
	/**
	 * get
	 * @return
	 */
	public StorehouseSubarea get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<StorehouseSubarea> getList(StorehouseSubarea storehouseSubarea);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<StorehouseSubarea> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);
}