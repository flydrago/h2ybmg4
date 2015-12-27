package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.jxc.entity.JxcSalesOutbound;

/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-07-08
 * email:info@hwttnet.com
 */
public interface IJxcSalesOutboundService{
	
	public void add(JxcSalesOutbound jxcSalesOutbound);
	
	public void delete(long id);

	public void update(JxcSalesOutbound jxcSalesOutbound);

	public JxcSalesOutbound get(long id);
	
	public List<JxcSalesOutbound> getList(JxcSalesOutbound jxcSalesOutbound);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<JxcSalesOutbound> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	/**
	 * 销售出库单  获取表格数据
	 * @param request
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request);

	/**
	 * 销售出库单  新单保存接口
	 * @param reqMap
	 * @return
	 */
	public Map<String,Object> outStorageSave(Map<String, Object> reqMap);
}
