package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.TraceCirculateRecord;
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
public interface ITraceCirculateRecordService{
	
	public void add(TraceCirculateRecord traceCirculateRecord);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(TraceCirculateRecord traceCirculateRecord);

	public TraceCirculateRecord get(long id);
	
	public List<TraceCirculateRecord> getList(TraceCirculateRecord traceCirculateRecord);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<TraceCirculateRecord> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	public void addPatch(List<TraceQrcodeSerial> traceQrcodeSerialList, Map<String,Object> map);

	public List<TraceCirculateRecord> getListData(Map<String, Object> map);

	public void updateNewRecord(Map<String, Object> dataMap1);

	public  Map<String, Object> addRecord(Map<String, Object> reqMap);

	public  Map<String, Object> getRecordList(Map<String, Object> reqMap);

	public Map<String, Object> getGridData(HttpServletRequest request);
}
