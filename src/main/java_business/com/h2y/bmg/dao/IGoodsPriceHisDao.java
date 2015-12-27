package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.GoodsPriceHis;

/**
 * 类描述：商品定价历史数据库操作接口   
 * 作者：侯飞龙
 * 时间：2015年1月28日下午1:17:04
 * 邮件：1162040314@qq.com
 */
@Component
public interface IGoodsPriceHisDao{

	/**
	 * add
	 */
	public void add(GoodsPriceHis goodsPriceHis);
	
	/**
	 * update
	 */
	public void update(GoodsPriceHis goodsPriceHis);
	
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
	public GoodsPriceHis get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<GoodsPriceHis> getList(GoodsPriceHis goodsPriceHis);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<GoodsPriceHis> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);
}