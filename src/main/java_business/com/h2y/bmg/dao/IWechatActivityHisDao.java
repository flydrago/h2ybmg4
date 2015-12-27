package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.WechatActivityHis;

/**
 * 类描述：活动参与历史数据库操作接口   
 * 作者：侯飞龙
 * 时间：2014年12月19日上午10:44:47
 * 邮件：1162040314@qq.com
 */
@Component
public interface IWechatActivityHisDao{

	/**
	 * add
	 */
	public void add(WechatActivityHis wechatActivityHis);
	
	/**
	 * update
	 */
	public void update(WechatActivityHis wechatActivityHis);
	
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
	public WechatActivityHis get(long id);
	
	
	/**
	 * 得到活动列表
	 * @param map
	 * key1:activityId value1:活动Id
	 * key2:ifQuery value2:查询条件
	 * key3:sortname、sortorder value3:排序字段、排序方式
	 * key4:page、pagesize value4:当前页码、页页显示最大记录数
	 * @return
	 */
	public List<Map<String,Object>> getHitUserListMap(Map<String,Object> map);
	
	
	/**
	 * 得到活动总数
	 * @param map
	 * key2:activityId value2:活动Id
	 * key3:ifQuery value3:查询条件
	 * @return
	 */
	public long getHitUserListRows(Map<String,Object> map);
}