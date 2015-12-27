package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 项目名称：h2yorsos  
 * 类名称：IOrderGoodsRtDao  
 * 类描述：订单商品关联数据库操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月15日 上午9:36:10  
 * 修改人：侯飞龙
 * 修改时间：2015年4月15日 上午9:36:10  
 * 修改备注：  
 * @version
 */
@Component
public interface IOrderGoodsRtDao{
	
	/**
	 * 根据订单编码，得到对应的商品列表
	 * @param orderNo 订单编码
	 * @return
	 */
	public List<Map<String,Object>> getListByOrderNo(String orderNo);
	
	/**
	 * 根据订单编码，得到对应的商品历史列表
	 * @param orderNo 订单编码
	 * @return
	 */
	public List<Map<String,Object>> getHisListByOrderNo(String orderNo);
}