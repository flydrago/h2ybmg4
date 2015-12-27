package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.JxcOverflow;

/**
 * 进销存 报溢单 Dao
 * @author hwttnet
 * version:1.2
 * time:2015-07-17
 */
@Component
public interface IJxcOverflowDao{

	/**
	 * add
	 */
	public void add(JxcOverflow jxcOverflow);
	
	/**
	 * update
	 */
	public void update(JxcOverflow jxcOverflow);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public JxcOverflow get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<JxcOverflow> getList(JxcOverflow jxcOverflow);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<JxcOverflow> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	/**
	 * 报溢单 获取表格数据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getListMap(Map<String, Object> map);

	/**
	 * 报溢单 获取单据总数
	 * @param map
	 * @return
	 */
	public Long getListRows(Map<String, Object> map);
}