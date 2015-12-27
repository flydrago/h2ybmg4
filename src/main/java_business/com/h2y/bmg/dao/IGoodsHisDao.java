package com.h2y.bmg.dao;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.GoodsHis;

/**
 * 类描述：商品历史数据库接口   
 * 作者：侯飞龙
 * 时间：2015年1月26日下午3:04:30
 * 邮件：1162040314@qq.com
 */
@Component
public interface IGoodsHisDao{

	/**
	 * add
	 */
	public void add(GoodsHis goodsHis);
	
	/**
	 * update
	 */
	public void update(GoodsHis goodsHis);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public GoodsHis get(long id);
}