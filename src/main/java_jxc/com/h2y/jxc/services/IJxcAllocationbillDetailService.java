package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import com.h2y.jxc.entity.JxcAllocationbillDetail;

/**
 * 仓库调拨单  单据商品明细 service
 * @author hwttnet
 * version:1.2
 * time:2015-07-10
 * email:info@hwttnet.com
 * Service Interface
 */
public interface IJxcAllocationbillDetailService{
	
	public void add(JxcAllocationbillDetail jxcAllocationbillDetail);
	
	public void delete(long id);
	
	public void update(JxcAllocationbillDetail jxcAllocationbillDetail);

	public JxcAllocationbillDetail get(long id);
	
	public List<JxcAllocationbillDetail> getList(JxcAllocationbillDetail jxcAllocationbillDetail);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<JxcAllocationbillDetail> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	/**
	 * 根据单据编码  获取单据商品信息
	 * @param billNo
	 * @return
	 */
	public List<Map<String, Object>> getBillGoods(String billNo);
}
