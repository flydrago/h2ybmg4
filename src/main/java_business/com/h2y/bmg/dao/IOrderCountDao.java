package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.OrderCount;


/**
 * 项目名称：h2yorsos  
 * 类名称：IOrderCountDao  
 * 类描述：订单统计数据库操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年8月6日 上午10:36:48  
 * 修改人：侯飞龙
 * 修改时间：2015年8月6日 上午10:36:48  
 * 修改备注：  
 * @version
 */
@Component
public interface IOrderCountDao{

	/**
	 * get
	 * @return
	 */
	public OrderCount get(long id);
	
	/**
	 * 得到统计列表
	 * @param map
	 * {startDay:开始日期,
	 * endDay:截止日期,
	 * unitId:当前单位id,
	 * unitType:当前单位类型,
	 * zoneCode:当前单位区域编码,
	 * unitName:单位名称（模糊查询）
	 * }
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
}