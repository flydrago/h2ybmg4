package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.JxcSalesOutbound;

/**
 * @author hwttnet
 * version:1.2
 * time:2015-07-08
 * email:info@hwttnet.com
 */
@Component
public interface IJxcSalesOutboundDao{

	/**
	 * add
	 */
	public void add(JxcSalesOutbound jxcSalesOutbound);
	
	/**
	 * update
	 */
	public void update(JxcSalesOutbound jxcSalesOutbound);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public JxcSalesOutbound get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<JxcSalesOutbound> getList(JxcSalesOutbound jxcSalesOutbound);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<JxcSalesOutbound> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	/**
	 * 分页获取数据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getListMap(Map<String, Object> map);

	/**
	 * 获取记录总数
	 * @param map
	 * @return
	 */
	public Long getListRows(Map<String, Object> map);
}