package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.TraceProviderItem;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ITraceProviderItemService{
	
	public void add(TraceProviderItem traceProviderItem);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(TraceProviderItem traceProviderItem);

	public TraceProviderItem get(long id);
	
	public List<TraceProviderItem> getList(TraceProviderItem traceProviderItem);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<TraceProviderItem> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	public Map<String,Object> getGridData(HttpServletRequest request);

	public void save(HttpServletRequest request, String op, SysUser sysUser,
			SysUnits sysUnits,TraceProviderItem traceProviderItem);

	public void updateStatusByIds(Map<String, Object> dataMap);

}
