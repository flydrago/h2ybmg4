package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.JxcBillDetail;

/**
 * JxcBillDetailDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */
@Component
public interface IJxcBillDetailDao{

	/**
	 * add
	 */
	public void add(JxcBillDetail jxcBillDetail);
	
	/**
	 * update
	 */
	public void update(JxcBillDetail jxcBillDetail);
	
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
	public JxcBillDetail get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<JxcBillDetail> getList(JxcBillDetail jxcBillDetail);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<JxcBillDetail> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);
	
	/**
	 * 查询单据商品列表（返回值为 map List）
	 * @param paraMap
	 * @return
	 */
	public List<Map<String, Object>> getBillGoodsList(Map<String, Object> paraMap);
	
	/**
	 * 查询单据商品列表（返回值为 对象List）
	 */
	public List<JxcBillDetail> getBillGoodsDetailList(Map<String,Object> paraMap);
}
