package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 项目名称：h2ybmg2  
 * 类名称：IMMsgHisDao  
 * 类描述：推送消息数据库操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年7月28日 上午9:28:26  
 * 修改人：侯飞龙
 * 修改时间：2015年7月28日 上午9:28:26  
 * 修改备注：  
 * @version
 */
@Component
public interface IMMsgHisDao{
	
	
	/**
	 * 得到配送端推送消息列表
	 * @param map
	 * {datasourceType:数据源类型（订单信息 order 、小达快报 news）,
	 * datasourceId:数据源ID（订单编号、小达快报ID）,
	 * page:页码,
	 * pagesize:页显示最大记录数,
	 * sortname:排序字段,
	 * sortorder:排序方式}
	 * @return
	 */
	public List<Map<String,Object>> getDeliveryListMap(Map<String,Object> map);
	
	/**
	 * 得到配送端推送消息行数
	 * @param map
	 * {datasourceType:数据源类型（订单信息 order 、小达快报 news）,
	 * datasourceId:数据源ID（订单编号、小达快报ID）,
	 * page:页码,
	 * pagesize:页显示最大记录数,
	 * sortname:排序字段,
	 * sortorder:排序方式}
	 * @return
	 */
	public long getDeliveryListRows(Map<String,Object> map);
	
	
	
	/**
	 * 得到客户端推送消息列表
	 * @param map
	 * {datasourceType:数据源类型（订单信息 order 、小达快报 news）,
	 * datasourceId:数据源ID（订单编号、小达快报ID）,
	 * page:页码,
	 * pagesize:页显示最大记录数,
	 * sortname:排序字段,
	 * sortorder:排序方式}
	 * @return
	 */
	public List<Map<String,Object>> getAppListMap(Map<String,Object> map);
	
	/**
	 * 得到客户端推送消息行数
	 * @param map
	 * {datasourceType:数据源类型（订单信息 order 、小达快报 news）,
	 * datasourceId:数据源ID（订单编号、小达快报ID）,
	 * page:页码,
	 * pagesize:页显示最大记录数,
	 * sortname:排序字段,
	 * sortorder:排序方式}
	 * @return
	 */
	public long getAppListRows(Map<String,Object> map);
	
	
	/**
	 * 得到PC推送消息列表
	 * @param map
	 * {datasourceType:数据源类型（订单信息 order 、小达快报 news）,
	 * datasourceId:数据源ID（订单编号、小达快报ID）,
	 * page:页码,
	 * pagesize:页显示最大记录数,
	 * sortname:排序字段,
	 * sortorder:排序方式}
	 * @return
	 */
	public List<Map<String,Object>> getPcListMap(Map<String,Object> map);
	
	/**
	 * 得到PC推送消息行数
	 * @param map
	 * {datasourceType:数据源类型（订单信息 order 、小达快报 news）,
	 * datasourceId:数据源ID（订单编号、小达快报ID）,
	 * page:页码,
	 * pagesize:页显示最大记录数,
	 * sortname:排序字段,
	 * sortorder:排序方式}
	 * @return
	 */
	public long getPcListRows(Map<String,Object> map);
}