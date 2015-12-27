package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.JxcProfitandlossDetail;

/**
 * 进销存 报损单&报溢单 商品详情 Dao
 * @author hwttnet
 * version:1.2
 * time:2015-07-17
 */
@Component
public interface IJxcProfitandlossDetailDao{

	/**
	 * add
	 */
	public void add(JxcProfitandlossDetail jxcProfitandlossDetail);
	
	/**
	 * update
	 */
	public void update(JxcProfitandlossDetail jxcProfitandlossDetail);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public JxcProfitandlossDetail get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<JxcProfitandlossDetail> getList(JxcProfitandlossDetail jxcProfitandlossDetail);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<JxcProfitandlossDetail> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	/**
	 * 进销存  报损单&报溢单 获取商品信息
	 * @param paraMap
	 * @return
	 */
	public List<Map<String, Object>> getBillGoodsList(Map<String, Object> paraMap);

	/**
	 * 进销存 报损单&报溢单 获取商品信息
	 * @param argMap
	 * @return
	 */
	public List<JxcProfitandlossDetail> getBillGoodsDetailList(Map<String, Object> argMap);
}