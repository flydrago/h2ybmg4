package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.SysLog;

/**
 * SysLogDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2014-10-28
 * email:info@hwttnet.com
 */
@Component
public interface ISysLogDao{

	/**
	 * add
	 */
	public void add(SysLog sysLog);
	
	/**
	 * update
	 */
	public void update(SysLog sysLog);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public SysLog get(long id);
	
	
	/**
	 * 得到日志分页列表
	 * @param map
	 * key1:unitId value1:单位Id
	 * key2:ifQuery value2:是否查询
	 * key3:sortname、sortorder value3:排序字段、排序方式
	 * key4:page、pagesize value4:页数、页显示最大条数
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 得到日志总条数
	 * @param map
	 * key1:unitId value1:单位Id
	 * key2:ifQuery value2:是否查询
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
	
}