package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.jxc.entity.JxcOverflow;

/**
 * 进销存 报溢单  Service 接口
 * @author hwttnet
 * version:1.2
 * time:2015-07-17
 */
public interface IJxcOverflowService{
	
	public void add(JxcOverflow jxcOverflow);
	
	public void delete(long id);
	
	public void update(JxcOverflow jxcOverflow);

	public JxcOverflow get(long id);
	
	public List<JxcOverflow> getList(JxcOverflow jxcOverflow);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<JxcOverflow> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	/**
	 * 
	 * @param request
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request);

	/**
	 * 
	 * @param reqMap
	 * @return
	 */
	public Map<String,Object> save(Map<String, Object> reqMap);
}
