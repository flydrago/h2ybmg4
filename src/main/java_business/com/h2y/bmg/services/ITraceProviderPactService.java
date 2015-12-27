package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.TraceProviderPact;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ITraceProviderPactService{
	
	public void add(TraceProviderPact traceProviderPact);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(TraceProviderPact traceProviderPact);

	public TraceProviderPact get(long id);
	
	public List<TraceProviderPact> getList(TraceProviderPact traceProviderPact);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<TraceProviderPact> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	public Map<String,Object> getGridData(HttpServletRequest request);

	public void save(HttpServletRequest request,SysUser sysUser);
}
