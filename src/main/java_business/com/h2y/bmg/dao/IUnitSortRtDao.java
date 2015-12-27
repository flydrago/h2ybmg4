package com.h2y.bmg.dao;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.UnitSortRt;

/**
 * UnitSortRtDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-05-04
 * email:info@hwttnet.com
 */
@Component
public interface IUnitSortRtDao{

	/**
	 * add
	 */
	public void add(UnitSortRt unitSortRt);

	/**
	 * update
	 */
	public void update(UnitSortRt unitSortRt);

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
	public UnitSortRt get(long id);


	/**
	 * getList
	 * @return
	 */
	public List<UnitSortRt> getList(UnitSortRt unitSortRt);


	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<UnitSortRt> getListPage(Map<String,Object> map);

	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	//删除原来的关联数据
	public int deleteSortRt(UnitSortRt unitSortRt);

	//获取经营范围
	public List<Map<String,Object>> getSortList(Map<String,Object> map);

}