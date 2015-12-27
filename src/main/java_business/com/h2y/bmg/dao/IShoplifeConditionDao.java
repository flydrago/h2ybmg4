package com.h2y.bmg.dao;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.ShoplifeCondition;


/**
 * ShoplifeConditionDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-06-29
 * email:info@hwttnet.com
 */
@Component
public interface IShoplifeConditionDao{

	/**
	 * add
	 */
	public void add(ShoplifeCondition shoplifeCondition);

	/**
	 * update
	 */
	public void update(ShoplifeCondition shoplifeCondition);

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
	public ShoplifeCondition get(long id);


	/**
	 * getList
	 * @return
	 */
	public List<ShoplifeCondition> getList(ShoplifeCondition shoplifeCondition);


	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<ShoplifeCondition> getListPage(Map<String,Object> map);

	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	/**
	 * 获取列表树
	 * @return
	 */
	public List<Map<String,Object>> getAllTreeList();

	/**
	 * 根据id获取子列表
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getChildTreeListById(long id);

	/**
	 * 得到子级下拉框数据
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getChildSelectListById(long id);


	public List<Map<String,Object>> getListMap(Map<String,Object> map);

	public Long getListRows(Map<String,Object> map);

	public Long getChildRowsById(long id);

	/**
	 * 判断是否重复添加
	 * @param shoplifeCondition
	 * @return
	 */
	public List<Map<String,Object>> getSameList(ShoplifeCondition shoplifeCondition);


}