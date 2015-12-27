package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import com.h2y.jxc.entity.JxcProfitandlossDetail;

/**
 * 进销存 报损单&报溢单 单据商品详情 Service
 * @author hwttnet
 * version:1.2
 * time:2015-07-17
 * email:info@hwttnet.com
 */
public interface IJxcProfitandlossDetailService{
	
	public void add(JxcProfitandlossDetail jxcProfitandlossDetail);
	
	public void delete(long id);
	
	public void update(JxcProfitandlossDetail jxcProfitandlossDetail);

	public JxcProfitandlossDetail get(long id);
	
	public List<JxcProfitandlossDetail> getList(JxcProfitandlossDetail jxcProfitandlossDetail);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<JxcProfitandlossDetail> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	/**
	 *	报溢单&报损单  查询单据商品信息
	 * @param billNo
	 * @return
	 */
	public List<Map<String, Object>> getBillGoods(String billNo);
}
