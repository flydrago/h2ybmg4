package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.SysQueryItem;

/**
 * SysQueryItemDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2014-10-27
 * email:info@hwttnet.com
 */
@Component
public interface ISysQueryItemDao{

	/**
	 * add
	 */
	public void add(SysQueryItem sysQueryItem);
	
	/**
	 * update
	 */
	public void update(SysQueryItem sysQueryItem);
	
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
	public SysQueryItem get(long id);
	

	/**
	 * 得到列表
	 * @param map
	 * key:#{joinId},#{joinType} value:关联主表Id,关联类型（menu:菜单、query:类型维护主表）
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 批量添加
	 * @param list
	 */
	public void addBatch(List<SysQueryItem> list);
	
	/**
	 * 删除
	 * @param map
	 * key:#{joinId},#{joinType} value:关联主表Id,关联类型（menu:菜单、query:类型维护主表）
	 */
	public void deleteByJoinType(Map<String,Object> map);
	
	
	/**
	 * 得到列表
	 * @param map
	 * key:#{joinId},#{joinType} value:关联主表Id,关联类型（menu:菜单、query:类型维护主表）
	 * @return
	 */
	public List<SysQueryItem> getListByJoinType(Map<String,Object> map);
	
	
	
}