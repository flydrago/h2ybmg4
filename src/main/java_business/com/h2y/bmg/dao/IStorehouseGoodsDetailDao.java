package com.h2y.bmg.dao;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.StorehouseGoodsDetail;

/**
 * 类描述：仓库历史数据库操作接口   
 * 作者：侯飞龙
 * 时间：2015年1月31日下午2:33:03
 * 邮件：1162040314@qq.com
 */
@Component
public interface IStorehouseGoodsDetailDao{

	/**
	 * add
	 */
	public void add(StorehouseGoodsDetail storehouseGoodsDetail);
	
	/**
	 * update
	 */
	public void update(StorehouseGoodsDetail storehouseGoodsDetail);
	
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
	public StorehouseGoodsDetail get(long id);
	
}