package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.StorehouseGoodsDetail;

/**
 * @author hwttnet
 * version:1.2
 * time:2015-07-06
 * email:info@hwttnet.com
 */
@Component
public interface IJxcStorehouseGoodsDetailDao{

	/**
	 * add
	 */
	public void add(StorehouseGoodsDetail storehouseGoodsDetail);
	
	/**
	 * update
	 */
	public void update(StorehouseGoodsDetail storehouseGoodsDetail);
	
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
	public StorehouseGoodsDetail get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<StorehouseGoodsDetail> getList(StorehouseGoodsDetail storehouseGoodsDetail);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<StorehouseGoodsDetail> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	/**
	 * 	增加 仓库变更日志
	 * @param logsMap
	 */
	public void addDetail(Map<String, Object> logsMap);
}