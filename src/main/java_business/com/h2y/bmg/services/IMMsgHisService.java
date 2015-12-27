package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目名称：h2ybmg2  
 * 类名称：IMMsgHisService  
 * 类描述：  消息业务操作接口
 * 创建人：侯飞龙  
 * 创建时间：2015年7月28日 上午9:51:07  
 * 修改人：侯飞龙
 * 修改时间：2015年7月28日 上午9:51:07  
 * 修改备注：  
 * @version
 */
public interface IMMsgHisService{
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request);
	
}
