package com.h2y.bmg.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.GoodsMarkRt;

/**
 * 类描述：标签详细关联数据库操作接口 作者：侯飞龙 时间：2015年1月26日上午11:37:38 邮件：1162040314@qq.com
 */
@Component
public interface IGoodsMarkRtDao {

	/**
	 * add
	 */
	public void add(GoodsMarkRt goodsMarkRt);

	/**
	 * update
	 */
	public void update(GoodsMarkRt goodsMarkRt);

	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * 
	 * @return
	 */
	public GoodsMarkRt get(long id);

	/**
	 * 根据商品Id，删除标签关联
	 * 
	 * @param goodsId
	 *            商品Id
	 */
	public void deleteByGoodsId(long goodsId);

	/**
	 * 批量添加详细
	 * 
	 * @param list
	 */
	public void addBatch(List<GoodsMarkRt> list);

}