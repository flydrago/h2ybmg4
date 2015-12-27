package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.JxcCheckbillDetail;

/**
 * 进销存  仓库盘点单  单据商品明细Dao
 * @author hwttnet
 * version:1.2
 * time:2015-07-14
 */
@Component
public interface IJxcCheckbillDetailDao{

	/**
	 * add
	 */
	public void add(JxcCheckbillDetail jxcCheckbillDetail);
	
	/**
	 * update
	 */
	public void update(JxcCheckbillDetail jxcCheckbillDetail);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public JxcCheckbillDetail get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<JxcCheckbillDetail> getList(JxcCheckbillDetail jxcCheckbillDetail);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<JxcCheckbillDetail> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	/**
	 * 根据 单据编号 获取单据商品明细
	 * @param paraMap
	 * @return
	 */
	public List<Map<String, Object>> getBillGoodsList(
			Map<String, Object> paraMap);
}