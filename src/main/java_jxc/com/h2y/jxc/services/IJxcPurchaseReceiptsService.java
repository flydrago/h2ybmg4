package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.jxc.entity.JxcPurchaseReceipts;

/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface IJxcPurchaseReceiptsService{
	
	public void add(JxcPurchaseReceipts jxcPurchaseReceipts);
	
	public void delete(long id);
	
	public void update(JxcPurchaseReceipts jxcPurchaseReceipts);

	public JxcPurchaseReceipts get(long id);
	
	public List<JxcPurchaseReceipts> getList(JxcPurchaseReceipts jxcPurchaseReceipts);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<JxcPurchaseReceipts> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);
	
	/**
	 * 采购入库单保存
	 * @param reqMap
	 * @return
	 */
	public Map<String,Object> inStorageSave(Map<String,Object> reqMap);

	/**
	 * 获取 采购单 表格数据
	 * @param request
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request);

	/**
	 * 获取 采购单 商品列表
	 * @param billNo
	 * @return
	 */
	public List<Map<String, Object>> getBillGoods(String billNo);

	/**
	 * 采购入库单  单据冲账
	 * @param request
	 * @return
	 */
	public Map<String,Object> billStrikeBalance(Map<String,Object> postMap);

}
