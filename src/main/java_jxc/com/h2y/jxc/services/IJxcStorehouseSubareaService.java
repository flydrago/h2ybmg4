package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import com.h2y.jxc.entity.StorehouseSubarea;
/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface IJxcStorehouseSubareaService{
	
	public void add(StorehouseSubarea storehouseSubarea);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(StorehouseSubarea storehouseSubarea);

	public StorehouseSubarea get(long id);
	
	public List<StorehouseSubarea> getList(StorehouseSubarea storehouseSubarea);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<StorehouseSubarea> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);
}
