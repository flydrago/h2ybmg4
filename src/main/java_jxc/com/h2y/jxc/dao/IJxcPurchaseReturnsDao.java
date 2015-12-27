package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.JxcPurchaseReturns;

/**
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */
@Component
public interface IJxcPurchaseReturnsDao{

	/**
	 * add
	 */
	public void add(JxcPurchaseReturns jxcPurchaseReturns);
	
	/**
	 * update
	 */
	public void update(JxcPurchaseReturns jxcPurchaseReturns);
	
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
	public JxcPurchaseReturns get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<JxcPurchaseReturns> getList(JxcPurchaseReturns jxcPurchaseReturns);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<JxcPurchaseReturns> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	/**
	 * 分页查询单据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getListMap(Map<String, Object> map);

	/**
	 * 查询单据总数
	 * @param map
	 * @return
	 */
	public Long getListRows(Map<String, Object> map);
}