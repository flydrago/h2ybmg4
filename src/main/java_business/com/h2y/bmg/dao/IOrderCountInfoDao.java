package com.h2y.bmg.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.OrderCountInfo;


/**
 * 项目名称：h2yorsos  
 * 类名称：IOrderCountInfoDao  
 * 类描述：订单统计信息数据库操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年8月6日 上午10:39:21  
 * 修改人：侯飞龙
 * 修改时间：2015年8月6日 上午10:39:21  
 * 修改备注：  
 * @version
 */
@Component
public interface IOrderCountInfoDao{

	/**
	 * get
	 * @return
	 */
	public OrderCountInfo get(long id);
	
	
	/**
	 * 根据统计id，得到统计的详细信息
	 * @param countId 统计id
	 * @return
	 */
	public List<OrderCountInfo> getListByCountId(long countId);
	
}