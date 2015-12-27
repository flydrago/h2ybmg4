package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.FindActivityGoods;

/**
 * FindActivityGoodsDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * email:info@hwttnet.com
 */
@Component
public interface IFindActivityGoodsDao{

	/**
	 * add
	 */
	public void add(FindActivityGoods findActivityGoods);

	/**
	 * update
	 */
	public void update(FindActivityGoods findActivityGoods);

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
	public FindActivityGoods get(long id);


	/**
	 * 根据活动Id，删除活动商品关联
	 * @param activityId 活动Id
	 */
	public void deleteByActivityId(long activityId);


	/**
	 * 批量添加
	 * @param list 关联对象列表
	 */
	public void addBatch(List<FindActivityGoods> list);


	/**
	 * 根据活动Id，得到活动商品关联信息
	 * @param activityId
	 * @return
	 */
	public List<Map<String,Object>> getGoodsListActivityId(long activityId);
}