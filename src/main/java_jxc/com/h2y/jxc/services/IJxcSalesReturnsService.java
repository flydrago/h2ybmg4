package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.jxc.entity.JxcSalesReturns;

/**
 * 进销存 销售退货单 Service
 * @author hwttnet
 * version:1.2
 * time:2015-07-21
 * email:info@hwttnet.com
 */
public interface IJxcSalesReturnsService{
	
	public void add(JxcSalesReturns jxcSalesReturns);
	
	public void delete(long id);
	
	public void update(JxcSalesReturns jxcSalesReturns);

	public JxcSalesReturns get(long id);
	
	public List<JxcSalesReturns> getList(JxcSalesReturns jxcSalesReturns);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<JxcSalesReturns> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	/**
	 * 销售退货单  分页获取表格数据
	 * @param request
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request);

	/**
	 * 销售退货单  新单保存接口
	 * @param reqMap
	 * @return
	 */
	public Map<String,Object> save(Map<String, Object> reqMap);
}
