package com.h2y.bmg.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.GoodsInfo;

/**
 * 类描述：商品详细   
 * 作者：侯飞龙
 * 时间：2015年1月26日下午2:45:04
 * 邮件：1162040314@qq.com
 */
@Component
public interface IGoodsInfoDao{

	/**
	 * add
	 */
	public void add(GoodsInfo goodsInfo);
	
	/**
	 * update
	 */
	public void update(GoodsInfo goodsInfo);
	
	/**
	 * delete
	 */
	public void deleteById(long id);
	
	
	/**
	 * get
	 * @return
	 */
	public GoodsInfo get(long id);
	
	/**
	 * 根据版本号和商品Id，得到对应的商品详细
	 * @param map
	 * {goodsId:数据Id（dataType为0时为商品Id、1：定价商品Id）,
	 * version:版本号（dataType为0时为商品版本号、1：定价商品版本号）,
	 * dataType:0：商品、1：定价商品详细}
	 * @return
	 */
	public GoodsInfo getByVersionAndGoodsId(Map<String,Object> map);
	
	/**
	 * 根据商品id，删除商品信息
	 * @param goodsId
	 */
	public void deleteByGoodsId(long goodsId);
	
}