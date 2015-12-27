package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.AdvertColumnSubjectRt;


public interface IAdvertColumnSubjectRtService{
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId);
	
	/**
	 * 得到时间冲突表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getDateCrossGridData(HttpServletRequest request,long unitId);
	
	/**
	 * 广告栏位主题关联保存
	 * @param request 访问对象
	 * @param op 操作类型
	 * @param advertColumnSubjectRt 广告栏位主题关联对象
	 */
	public void save(HttpServletRequest request,String op,AdvertColumnSubjectRt advertColumnSubjectRt);
	
}
