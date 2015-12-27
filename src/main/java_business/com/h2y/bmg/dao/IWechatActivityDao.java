package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.WechatActivity;

/**
 * 类描述：微活动数据库操作接口   
 * 作者：侯飞龙
 * 时间：2014年12月17日上午11:33:49
 * 邮件：1162040314@qq.com
 */
@Component
public interface IWechatActivityDao{

	/**
	 * add
	 */
	public void add(WechatActivity wechatActivity);
	
	/**
	 * update
	 */
	public void update(WechatActivity wechatActivity);
	
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
	public WechatActivity get(long id);
	
	/**
	 * 得到活动列表
	 * @param map
	 * key1:unitId value1:单位Id
	 * key2:activityType value2:活动类型（wheel:大转盘、card:刮刮卡）
	 * key3:ifQuery value3:查询条件
	 * key4:sortname、sortorder value4:排序字段、排序方式
	 * key5:page、pagesize value5:当前页码、页页显示最大记录数
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	
	/**
	 * 得到活动总数
	 * @param map
	 * key1:unitId value1:单位Id
	 * key2:activityType value2:活动类型（wheel:大转盘、card:刮刮卡）
	 * key3:ifQuery value3:查询条件
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
	
}