package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.WechatActivityPrize;

/**
 * 类描述：微活动奖项数据库操作接口   
 * 作者：侯飞龙
 * 时间：2014年12月17日上午11:37:10
 * 邮件：1162040314@qq.com
 */
@Component
public interface IWechatActivityPrizeDao{

	/**
	 * add
	 */
	public void add(WechatActivityPrize wechatActivityPrize);
	
	/**
	 * update
	 */
	public void update(WechatActivityPrize wechatActivityPrize);
	
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
	public WechatActivityPrize get(long id);
	
	/**
	 * 批量添加
	 * @param list
	 */
	public void addBatch(List<WechatActivityPrize> list);
	
	
	/**
	 * 根据acitivityId，删除对应的奖项
	 * @param map
	 * key1:activityId value1:活动Id
	 * key2:filterIdList value2:删除过滤Id，null时不做判断
	 */
	public void deleteByActivityId(Map<String,Object> map);
	
	
	/**
	 * 根据活动Id，得到商品列表
	 * @param activityId 活动Id
	 * @return
	 */
	public List<Map<String,Object>> getPrizeListByActivityId(long activityId);
	
}