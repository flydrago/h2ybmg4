package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.SysUnits;

/**
 * 项目名称：h2ybmg2  
 * 类名称：IAdvertSubjectInfoService  
 * 类描述：广告主题信息业务操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月8日 上午9:38:20  
 * 修改人：侯飞龙
 * 修改时间：2015年4月8日 上午9:38:20  
 * 修改备注：  
 * @version
 */
public interface IAdvertSubjectInfoRtService{
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,SysUnits sysUnits);
	
	/**
	 * 广告主题信息保存
	 * @param request 访问对象
	 */
	public void save(HttpServletRequest request);
	
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getActivityGridData(HttpServletRequest request,SysUnits sysUnits);
	
}
