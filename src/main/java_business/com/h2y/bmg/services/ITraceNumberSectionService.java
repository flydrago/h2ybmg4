package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.TraceNumberSection;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-06-30
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ITraceNumberSectionService{
	
	public void add(TraceNumberSection traceNumberSection);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(TraceNumberSection traceNumberSection);

	public TraceNumberSection get(Long id);
	
	public List<TraceNumberSection> getList(TraceNumberSection traceNumberSection);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<TraceNumberSection> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	public Map<String,Object> getGridData(HttpServletRequest request);

	public List<TraceNumberSection> getListData(HttpServletRequest request);
	public Map<String,Object> getSelectProvinceGridData(HttpServletRequest request);
	public Map<String,Object> getSelectProviderGridData(HttpServletRequest request);

	public void save(HttpServletRequest request,SysUnits sysUnits,SysUser sysUser);

}
