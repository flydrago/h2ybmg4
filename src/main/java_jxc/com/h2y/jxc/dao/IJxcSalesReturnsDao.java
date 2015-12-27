package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.JxcSalesReturns;

/**
 * 销售退货单 Dao
 * @author hwttnet
 * version:1.2
 * time:2015-07-21
 */
@Component
public interface IJxcSalesReturnsDao{

	/**
	 * add
	 */
	public void add(JxcSalesReturns jxcSalesReturns);
	
	/**
	 * update
	 */
	public void update(JxcSalesReturns jxcSalesReturns);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public JxcSalesReturns get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<JxcSalesReturns> getList(JxcSalesReturns jxcSalesReturns);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<JxcSalesReturns> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	/**
	 * 销售退货单  分页获取表格数据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getListMap(Map<String, Object> map);

	/**
	 * 销售退货单  获取单据总数
	 * @param map
	 * @return
	 */
	public Long getListRows(Map<String, Object> map);
}