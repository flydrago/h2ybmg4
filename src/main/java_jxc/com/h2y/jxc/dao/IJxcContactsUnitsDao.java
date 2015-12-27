package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.JxcContactsUnits;

/**
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */
@Component
public interface IJxcContactsUnitsDao{

	/**
	 * add
	 */
	public void add(JxcContactsUnits jxcContactsUnits);
	
	/**
	 * update
	 */
	public void update(JxcContactsUnits jxcContactsUnits);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * deleteByIds
	 */
	//public void deleteByIds(List<long> ids);
	
	/**
	 * get
	 * @return
	 */
	public JxcContactsUnits get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<JxcContactsUnits> getList(JxcContactsUnits jxcContactsUnits);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<JxcContactsUnits> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	/**
	 * 获取分页查询数据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getListMap(Map<String, Object> map);

	/**
	 * 获取总数据
	 * @param map
	 * @return
	 */
	public Long getListRows(Map<String, Object> map);
}