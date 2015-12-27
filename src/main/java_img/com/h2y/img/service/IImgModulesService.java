package com.h2y.img.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.img.model.ImgModules;

/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-10-11
 * email:info@hwttnet.com
 * 
 * Service Interface
 */

public interface IImgModulesService{
	
	public void add(ImgModules imgModules);
	
	public void delete(long id);
	
	public void update(ImgModules imgModules);

	public ImgModules get(long id);
	
	public List<ImgModules> getList(ImgModules imgModules);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<ImgModules> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	/**
	 * 获取模块列表数据
	 * @param request
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request);

	/**
	 * 保存模块信息
	 * @param reqMap
	 * @return
	 */
	public Map<String,Object> saveModule(Map<String, Object> reqMap);

	/**
	 * 删除模块信息
	 * @param moduleId
	 * @return
	 */
	public Map<String,Object> deleteModule(long moduleId);

}
