package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.FindActivity;

/**
 * FindActivityDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2014-12-10
 * email:info@hwttnet.com
 */
@Component
public interface IFindActivityDao{

	/**
	 * add
	 */
	public void add(FindActivity findActivity);

	/**
	 * update
	 */
	public int update(FindActivity findActivity);

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
	public FindActivity get(long id);


	/**
	 * 得到分页列表
	 * @param map
	 * key1:unitId value1:单位Id
	 * key2:ifQuery value2:查询条件 null时：不做判断
	 * key3:sortname、sortorder value3:排序字段、排序方式
	 * key4:page、pagesize value4:页码、页显示最大记录数
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);


	/**
	 * 得到列表总数
	 * @param map
	 * key1:unitId value1:单位Id
	 * key2:ifQuery value2:查询条件 null时：不做判断
	 * @return
	 */
	public long getListRows(Map<String,Object> map);



}