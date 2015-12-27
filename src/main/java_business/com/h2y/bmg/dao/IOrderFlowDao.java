package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 项目名称：h2yorsos  
 * 类名称：IOrderFlowDao  
 * 类描述：订单流向数据库操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月15日 上午9:33:26  
 * 修改人：侯飞龙
 * 修改时间：2015年4月15日 上午9:33:26  
 * 修改备注：  
 * @version
 */
@Component
public interface IOrderFlowDao{

	/**
	 * 根据订单编码，得到订单的跟踪列表
	 * @param orderNo 订单编码
	 * @return
	 */
	public List<Map<String,Object>> getListByOrderNo(String orderNo);
	
	/**
	 * 根据订单编码，得到订单的历史跟踪列表
	 * @param orderNo 订单编码
	 * @return
	 */
	public List<Map<String,Object>> getHisListByOrderNo(String orderNo);
}