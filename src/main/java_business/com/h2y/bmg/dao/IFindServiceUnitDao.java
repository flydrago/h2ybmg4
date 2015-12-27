package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.FindServiceUnit;

/**
 * FindServiceUnitDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-03-05
 * email:info@hwttnet.com
 */
@Component
public interface IFindServiceUnitDao{

	/**
	 * add
	 */
	public void add(FindServiceUnit findServiceUnit);

	/**
	 * update
	 */
	public int update(FindServiceUnit findServiceUnit);

	/**
	 * delete
	 */
	public int deleteById(long id);

	/**
	 * deleteByIds
	 */
	//public void deleteByIds(List<long> ids);

	/**
	 * get
	 * @return
	 */
	public FindServiceUnit get(long id);

	/**
	 * 得到单位服务数列表
	 * @param map
	 * {unitId:单位Id}
	 * @return
	 */
	public List<Map<String,Object>> getUnitServiceTreeList(Map<String,Object> map);

	/**
	 * 根据单位Id，删除服务单位关联信息
	 * @param unitId 单位Id
	 */
	public int deleteByUnitId(Map<String,Object> map);

	/**
	 * 批量添加服务单位关联信息
	 * @param list
	 */
	public void addBatch(List<FindServiceUnit> list);


	/**
	 * 查询当前单位公共服务列表
	 * @param unitId
	 * @return
	 */
	public List<Map<String, Object>> getUnitServiceList(Map<String,Object> map);

	/**
	 * 获取记录总数
	 * @param map
	 * @return
	 */
	public long getUnitServiceListRows(Map<String,Object> map);

	/**
	 * 公共服务选择列表
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getUnitServiceSelectList(Map<String,Object> map);

	/**
	 * 公共服务选择列表总数
	 * @param map
	 * @return
	 */
	public long getUnitServiceSelectListRows(Map<String,Object> map);


	/**
	 * 获取当前公司服务id
	 * @param unitId
	 * @return
	 */
	public List<Long> getUnitServiceIdList(Map<String,Object> map);

}