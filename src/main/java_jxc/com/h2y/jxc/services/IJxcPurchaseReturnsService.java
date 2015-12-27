package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.jxc.entity.JxcPurchaseReturns;

/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */
public interface IJxcPurchaseReturnsService{
	
	public void add(JxcPurchaseReturns jxcPurchaseReturns);
	
	public void delete(long id);
	
	public void update(JxcPurchaseReturns jxcPurchaseReturns);

	public JxcPurchaseReturns get(long id);
	
	public List<JxcPurchaseReturns> getList(JxcPurchaseReturns jxcPurchaseReturns);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<JxcPurchaseReturns> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	/**
	 * 获取采购退货单  表格数据
	 * @param request
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request);

	/**
	 * 采购退货单  保存单据
	 * @param reqMap
	 * @return
	 */
	public Map<String,Object> outStorageSave(Map<String, Object> reqMap);
}
