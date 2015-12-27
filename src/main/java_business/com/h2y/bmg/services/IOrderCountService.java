package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.SysUnits;

/**
 * 项目名称：h2ybmg2  
 * 类名称：IOrderService  
 * 类描述：订单汇总数据库操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年5月5日 下午2:06:50  
 * 修改人：侯飞龙
 * 修改时间：2015年5月5日 下午2:06:50  
 * 修改备注：  
 * @version
 */
public interface IOrderCountService {

	/**
	 * 获取表格数据
	 * @param request
	 * @param sysUnits 单位id
	 * @return
	 */
	public Map<String, Object> getGridData(HttpServletRequest request,SysUnits sysUnits);
	
	
	/**
	 * 获取表格数据
	 * @param request
	 * @param sysUnits 单位id
	 * @return
	 */
	public Map<String, Object> getTodayGridData(HttpServletRequest request,SysUnits sysUnits);


	public Map<String,Object> getAnalysesData(HttpServletRequest request, SysUnits loginUnit);
}
