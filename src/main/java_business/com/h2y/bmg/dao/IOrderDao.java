package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.Order;

/**
 * 项目名称：h2yorsos  
 * 类名称：IOrderDao  
 * 类描述：订单数据库操作  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月15日 上午9:30:02  
 * 修改人：侯飞龙
 * 修改时间：2015年4月15日 上午9:30:02  
 * 修改备注：  
 * @version
 */
@Component
public interface IOrderDao{
	
	/**
	 * 得到订单列表
	 * @param map
	 * {unitId:单位id,
	 * ifQuery:查询条件,
	 * sortname:排序字段,
	 * sortorder:排序方式,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 得到订单总数量
	 * @param map
	 * {unitId:单位id,
	 * ifQuery:查询条件}
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
	
	/**
	 * 根据订单编码，得到对应订单的数量
	 * @param orderNo 订单编码
	 * @return
	 */
	public long getRowsByOrderNo(String orderNo);
	
	/**
	 * 根据订单编码，得到对应的历史订单的数量
	 * @param orderNo 订单编码
	 * @return
	 */
	public long getHisRowsByOrderNo(String orderNo);
	
	/**
	 * 根据订单编码得到订单信息
	 * @param orderNo 订单编码
	 * @return
	 */
	public Map<String,Object> getByOrderNo(String orderNo);
	
	/**
	 * 根据订单编码得到订单信息
	 * @param orderNo 订单编码
	 * @return
	 */
	public Map<String,Object> getHisByOrderNo(String orderNo);
	
	/**
	 * 得到当前单位的汇总列表
	 * @param map
	 * {unitId:当前单位id,
	 * startTime:开始时间,
	 * endTime:截止时间}
	 * @return
	 */
	public List<Order> getTodayCountList(Map<String,Object> map);

	/**
	 * 获取订单汇总数据以作分析
	 * @param paraMap
	 * @return
	 */
	public List<Order> getOrderAnalysesData(Map<String, Object> paraMap);

	/**
	 * 查询订单中的单位列表
	 * @param paraMap
	 * @return
	 */
	public List<Map<String,Object>> getOrderAnalysesUnitInfoList(Map<String, Object> paraMap);
}