package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.jxc.entity.JxcBreakage;

/**
 * 进销存 报损单 Service
 * @author hwttnet
 * version:1.2
 * time:2015-07-17
 * email:info@hwttnet.com
 */
public interface IJxcBreakageService{
	
	public void add(JxcBreakage jxcBreakage);
	
	public void delete(long id);
	
	public void update(JxcBreakage jxcBreakage);

	public JxcBreakage get(long id);
	
	public List<JxcBreakage> getList(JxcBreakage jxcBreakage);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<JxcBreakage> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	/**
	 * 报损单 获取表格数据
	 * @param request
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request);

	/**
	 * 报损单 新单保存接口
	 * @param reqMap
	 * @return
	 */
	public Map<String,Object> save(Map<String, Object> reqMap);
}
