package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.SysParam;

/**
 * SysParamDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2014-12-05
 * email:info@hwttnet.com
 */
@Component
public interface ISysParamDao{

	/**
	 * add
	 */
	public void add(SysParam sysParam);
	
	/**
	 * update
	 */
	public void update(SysParam sysParam);
	
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
	public SysParam get(long id);
	
	/**
	 * 得到列表
	 * @param map
	 * key1:unitId value1:单位Id
	 * key2:typeId value2:类型Id
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	public long getListRows(Map<String,Object> map);
	
	/**
	 * 根据类型Id和单位Id，得到对应的记录数
	 * @param map
	 * key1:unitId value1:单位Id
	 * key2:typeId value2:类型Id
	 * key3:paramsCode value3:参数编码
	 * key4:id value4:null时 不做判断
	 * @return
	 */
	public long getRowsByTypeIdAndCode(Map<String,Object> map);
	
	/**
	 * 批量添加
	 * @param list
	 */
	public void addBatch(List<SysParam> list);
	
	/**
	 * 根据类型Id和编码得到对应的列表
	 * @param map
	 * key1:unitId vlaue1:单位Id
	 * key2:typeId value2:类型Id
	 * key3:paramsCode value3:编码
	 * @return
	 */
	public SysParam getByTypeIdAndCode(Map<String,Object> map);
	
	
	/**
	 * 根据类型Id和编码得到对应的列表
	 * @param map
	 * key1:unitId vlaue1:单位Id
	 * key2:typeId value2:类型Id
	 * @return
	 */
	public List<SysParam> getListByTypeId(Map<String,Object> map);
}