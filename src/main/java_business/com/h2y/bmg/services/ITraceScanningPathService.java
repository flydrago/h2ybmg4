package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import com.h2y.bmg.entity.TraceScanningPath;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ITraceScanningPathService{
	
	public void add(TraceScanningPath traceScanningPath);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(TraceScanningPath traceScanningPath);

	public TraceScanningPath get(long id);
	
	public List<TraceScanningPath> getList(TraceScanningPath traceScanningPath);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<TraceScanningPath> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);
	
	public List<Map<String,Object>> getList(long parentId);
}
