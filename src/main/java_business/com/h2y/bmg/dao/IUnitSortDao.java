package com.h2y.bmg.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.UnitSort;

/**
 * 
 * @author sunfj
 *
 */
@Component
public interface IUnitSortDao{

	/**
	 * add
	 */
	public void add(UnitSort unitSort);

	/**
	 * update
	 */
	public void update(UnitSort unitSort);

	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * deleteByIds
	 */
	public void deleteByIds(List<Long> ids);

	/**
	 * get
	 * @return
	 */
	public UnitSort get(long id);


	/**
	 * getList
	 * @return
	 */
	public List<UnitSort> getList(UnitSort unitSort);




	//获取列表
	public List<Map<String,Object>> getListMap(Map<String,Object> map);

	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);


	// 获取类型树
	public List<Map<String,Object>> getAllTreeList();


	//获取树列表
	public List<Map<String,Object>> getChildTreeListById(long id);

	//获取列表
	public List<Map<String,Object>> getChildSelectListById(long id);

	//根据code获取列表 判断code是否重复
	public List<Map<String, Object>> getUnitSortByCode(UnitSort unitSort);

	//根据parentId获取子列表
	public List<Map<String,Object>> getRowsByParentId(long parentId);

	//获取选择窗口
	public List<Map<String,Object>> getListMapById(Map<String,Object> map);

}