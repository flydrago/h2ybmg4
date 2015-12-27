package com.h2y.bmg.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.CommonActivityGoodsRtHis;

/**
 * 类描述：活动商品历史数据库操作接口   
 * 作者：侯飞龙
 * 时间：2015年2月6日下午4:23:44
 * 邮件：1162040314@qq.com
 */
@Component
public interface ICommonActivityGoodsRtHisDao{

	/**
	 * add
	 */
	public void add(CommonActivityGoodsRtHis commonActivityGoodsRtHis);

	/**
	 * update
	 */
	public void update(CommonActivityGoodsRtHis commonActivityGoodsRtHis);

	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public CommonActivityGoodsRtHis get(long id);


	/**
	 * 根据dataId 修改商品开始时间和结束时间
	 * @param map
	 */
	public int updateByDataId(Map<String,Object> map);


	/**
	 * 根据dataId 修改定额商品价钱
	 * @param map
	 */
	public int updateFixedPriceByDataId(Map<String,Object> map);

}