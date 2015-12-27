package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.TraceNumberSection;
import com.h2y.bmg.entity.TraceQrcodeSerial;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ITraceQrcodeSerialService{
	
	public void add(TraceQrcodeSerial traceQrcodeSerial);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(TraceQrcodeSerial traceQrcodeSerial);

	public TraceQrcodeSerial get(long id);
	
	public List<TraceQrcodeSerial> getList(TraceQrcodeSerial traceQrcodeSerial);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<TraceQrcodeSerial> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	public void save(HttpServletRequest request, SysUnits sysUnits,SysUser sysUser,TraceNumberSection traceNumberSection);

	public List<TraceQrcodeSerial> getListData(Map<String, Object> dataMap);

	public Map<String,Object> getGridData(HttpServletRequest request);
	
}
