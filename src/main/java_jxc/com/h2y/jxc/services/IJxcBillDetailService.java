package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import com.h2y.jxc.entity.JxcBillDetail;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface IJxcBillDetailService{
	
	public void add(JxcBillDetail jxcBillDetail);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(JxcBillDetail jxcBillDetail);

	public JxcBillDetail get(long id);
	
	public List<JxcBillDetail> getList(JxcBillDetail jxcBillDetail);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<JxcBillDetail> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	/**
	 * 根据单据编号 获取单据包含的商品信息
	 * @param billNo
	 * @return
	 */
	public List<Map<String, Object>> getBillGoods(String billNo);
}
