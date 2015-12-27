package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.JxcCheck;

/**
 * 仓库调拨单  Dao
 * @author hwttnet
 * version:1.2
 * time:2015-07-13
 */

@Component
public interface IJxcCheckDao{

	/**
	 * add
	 */
	public void add(JxcCheck jxcCheck);
	
	/**
	 * update
	 */
	public void update(JxcCheck jxcCheck);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * deleteByIds
	 */
	
	/**
	 * get
	 * @return
	 */
	public JxcCheck get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<JxcCheck> getList(JxcCheck jxcCheck);
	

	/**
	 * getListPage
	 * page,pagesize,key
	 * @return
	 */
	public List<JxcCheck> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	/**
	 * 仓库盘点单  获取列表数据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getListMap(Map<String, Object> map);

	/**
	 * 仓库盘点单  获取单据总数
	 * @param map
	 * @return
	 */
	public Long getListRows(Map<String, Object> map);
}