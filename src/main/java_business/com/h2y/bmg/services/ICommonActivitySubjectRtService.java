package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 类描述：活动主题关联业务类接口
 * 作者：侯飞龙
 * 时间：2015年1月7日上午9:59:01
 * 邮件：1162040314@qq.com
 */
public interface ICommonActivitySubjectRtService {
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId);
	
	/**
	 * 保存操作
	 * @param request 访问对象
	 */
	public void save(HttpServletRequest request);
	
}
