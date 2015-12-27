package com.h2y.img.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.img.model.ImgUsage;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-10-11
 * email:info@hwttnet.com
 * 
 * Service Interface
 */


public interface IImgUsageService{
	
	public void add(ImgUsage imgUsage);
	
	public void delete(long id);
	
	public void update(ImgUsage imgUsage);

	public ImgUsage get(long id);
	
	public List<ImgUsage> getList(ImgUsage imgUsage);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<ImgUsage> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	/**
	 * 获取模块下的用途列表
	 * @param request
	 * @param moduleCode
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request);

	/**
	 * 保存 图片 用途 信息
	 * @param reqMap
	 * @return
	 */
	public Map<String,Object> saveUsage(Map<String, Object> reqMap);
}
