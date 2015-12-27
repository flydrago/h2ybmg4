package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import com.h2y.jxc.entity.JxcCheckbillDetail;



/**
 * 进销存  仓库盘点单  单据商品明细 Service
 * @author hwttnet
 * version:1.2
 * time:2015-07-14
 */
public interface IJxcCheckbillDetailService{
	
	public void add(JxcCheckbillDetail jxcCheckbillDetail);
	
	public void delete(long id);
	
	public void update(JxcCheckbillDetail jxcCheckbillDetail);

	public JxcCheckbillDetail get(long id);
	
	public List<JxcCheckbillDetail> getList(JxcCheckbillDetail jxcCheckbillDetail);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<JxcCheckbillDetail> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	/**
	 * 进销存  仓库盘点单  查询单据商品明细接口  
	 * @param billNo
	 * @return
	 */
	public List<Map<String, Object>> getBillGoods(String billNo);
}
