package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.JxcBreakage;

/**
 * 进销存 报损单Dao
 * @author hwttnet
 * version:1.2
 * time:2015-07-17
 */
@Component
public interface IJxcBreakageDao{

	/**
	 * add
	 */
	public void add(JxcBreakage jxcBreakage);
	
	/**
	 * update
	 */
	public void update(JxcBreakage jxcBreakage);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public JxcBreakage get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<JxcBreakage> getList(JxcBreakage jxcBreakage);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<JxcBreakage> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	/**
	 * 进销存 分页获取报损单数据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getListMap(Map<String, Object> map);

	/**
	 * 进销存 获取报损单总数
	 * @param map
	 * @return
	 */
	public Long getListRows(Map<String, Object> map);
}