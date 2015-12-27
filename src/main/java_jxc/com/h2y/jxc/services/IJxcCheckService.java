package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.jxc.entity.JxcCheck;



/**
 * 仓库盘点单  Service
 * @author hwttnet
 * version:1.2
 * time:2015-07-13
 */
public interface IJxcCheckService{
	
	public void add(JxcCheck jxcCheck);
	
	public void delete(long id);
	
	public void update(JxcCheck jxcCheck);

	public JxcCheck get(long id);
	
	public List<JxcCheck> getList(JxcCheck jxcCheck);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<JxcCheck> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	/**
	 * 仓库盘点单 获取表格数据
	 * @param request
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request);

	/**
	 * 仓库盘点单  保存新单
	 * @param reqMap
	 * @return
	 */
	public Map<String,Object> save(Map<String, Object> reqMap);
}
