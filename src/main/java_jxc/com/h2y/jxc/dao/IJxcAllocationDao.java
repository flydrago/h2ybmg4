package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.JxcAllocation;

/**
 * 仓储调拨单  Dao
 * @author hwttnet
 * version:1.2
 * time:2015-07-09
 * email:info@hwttnet.com
 */
@Component
public interface IJxcAllocationDao{

	/**
	 * add
	 */
	public void add(JxcAllocation jxcAllocation);
	
	/**
	 * update
	 */
	public void update(JxcAllocation jxcAllocation);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public JxcAllocation get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<JxcAllocation> getList(JxcAllocation jxcAllocation);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<JxcAllocation> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	/**
	 * 仓库调拨单  分页获取表格数据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getListMap(Map<String, Object> map);

	/**
	 * 仓库调拨单  获取单据总数
	 * @param map
	 * @return
	 */
	public Long getListRows(Map<String, Object> map);
}