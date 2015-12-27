package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import com.h2y.bmg.entity.ScheduleTask;

public interface IScheduleTaskDao {
	
	public ScheduleTask get();
	
	/**
	 * 获取列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 获取列表总数
	 * @param map
	 * @return
	 */
	public long getListRows(Map<String,Object> map);

}
