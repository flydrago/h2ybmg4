package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.jxc.entity.JxcAllocation;

/**
 * 进销存 调拨单 service 
 * @author hwttnet
 * version:1.2
 * time:2015-07-09
 * email:info@hwttnet.com
 */
public interface IJxcAllocationService{
	
	public void add(JxcAllocation jxcAllocation);
	
	public void delete(long id);
	
	public void update(JxcAllocation jxcAllocation);

	public JxcAllocation get(long id);
	
	public List<JxcAllocation> getList(JxcAllocation jxcAllocation);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<JxcAllocation> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	/**
	 * 仓储调拨单  获取列表数据
	 * @param request
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request);

	/**
	 * 仓储调拨单  新单保存接口
	 * @param reqMap
	 * @return
	 */
	public Map<String,Object> save(Map<String, Object> reqMap);
}
