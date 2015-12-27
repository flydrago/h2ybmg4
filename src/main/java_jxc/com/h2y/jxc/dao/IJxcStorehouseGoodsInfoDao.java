package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.StorehouseGoodsInfo;

/**
 * @author hwttnet
 * version:1.2
 * time:2015-07-06
 * email:info@hwttnet.com
 */
@Component
public interface IJxcStorehouseGoodsInfoDao{

	/**
	 * add
	 */
	public void add(StorehouseGoodsInfo storehouseGoodsInfo);
	
	/**
	 * update
	 */
	public void update(StorehouseGoodsInfo storehouseGoodsInfo);
	
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
	public StorehouseGoodsInfo get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<StorehouseGoodsInfo> getList(StorehouseGoodsInfo storehouseGoodsInfo);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<StorehouseGoodsInfo> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	/**
	 *	根据 仓库ID、商品ID 获取该商品仓储信息
	 * @param paraMap
	 * @return
	 */
	public StorehouseGoodsInfo getGoodsInfo(Map<String,Object> paraMap);
}