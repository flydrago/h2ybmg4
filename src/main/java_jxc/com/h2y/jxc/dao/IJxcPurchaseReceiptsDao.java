package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.JxcPurchaseReceipts;

/**
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */
@Component
public interface IJxcPurchaseReceiptsDao{

	/**
	 * add
	 */
	public void add(JxcPurchaseReceipts jxcPurchaseReceipts);
	
	/**
	 * update
	 */
	public void update(JxcPurchaseReceipts jxcPurchaseReceipts);
	
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
	public JxcPurchaseReceipts get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<JxcPurchaseReceipts> getList(JxcPurchaseReceipts jxcPurchaseReceipts);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<JxcPurchaseReceipts> getListPage(Map<String,Object> map);
	
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
	 * 获取单据总数
	 * @param map
	 * @return
	 */
	public Long getListRows(Map<String, Object> map);

	/**
	 * 根据billNo 获取单据信息
	 * @param billNo
	 * @return
	 */
	public JxcPurchaseReceipts getBillByBillNo(String billNo);

}