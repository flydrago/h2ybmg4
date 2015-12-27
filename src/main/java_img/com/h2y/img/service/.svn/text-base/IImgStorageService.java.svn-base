package com.h2y.img.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-10-11
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface IImgStorageService{
	
	/**
	 * 删除图片信息
	 * @param id
	 */
	public void delete(long id);
	
	/**
	 * 得到选择表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getSelectGridData(HttpServletRequest request,long unitId);

	
	/**
	 * 获取 图片库 表格数据
	 * @param reqMap
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request);


	/**
	 * 保存图片信息
	 * @param reqMap
	 * @return
	 */
	public Map<String,Object> saveImg(Map<String, Object> reqMap);
}
