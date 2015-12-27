package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.JxcAllocationbillDetail;

/**
 * 仓库调拨单 单据商品明细 Dao
 * @author hwttnet
 * version:1.2
 * time:2015-07-10
 * email:info@hwttnet.com
 */
@Component
public interface IJxcAllocationbillDetailDao{

	/**
	 * add
	 */
	public void add(JxcAllocationbillDetail jxcAllocationbillDetail);
	
	/**
	 * update
	 */
	public void update(JxcAllocationbillDetail jxcAllocationbillDetail);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public JxcAllocationbillDetail get(long id);
	
	/**
	 * getList
	 * @return
	 */
	public List<JxcAllocationbillDetail> getList(JxcAllocationbillDetail jxcAllocationbillDetail);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<JxcAllocationbillDetail> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	/**
	 * 获取 仓库调拨单  单据商品详细
	 * @param paraMap
	 * @return
	 */
	public List<Map<String,Object>> getBillGoodsList(Map<String, Object> paraMap);

	/**
	 * 获取 仓库调拨单  单据商品明细
	 * @param argMap
	 * @return
	 */
	public List<JxcAllocationbillDetail> getBillGoodsDetailList(Map<String, Object> argMap);
}