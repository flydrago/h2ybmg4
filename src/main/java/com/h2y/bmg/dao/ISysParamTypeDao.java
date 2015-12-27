package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.SysParamType;

/**
 * SysParamTypeDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * version:1.2
 * time:2014-12-05
 * email:info@hwttnet.com
 */
@Component
public interface ISysParamTypeDao{

	/**
	 * add
	 */
	public void add(SysParamType sysParamType);
	
	/**
	 * update
	 */
	public void update(SysParamType sysParamType);
	
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
	public SysParamType get(long id);
	
	
	/**
	 * 得到参数类型分页列表
	 * @param map
	 * key1:ifQuery value1:查询条件，null时不做判断
	 * key2:sortname,sortorder value2:排序字段、排序方式
	 * key3:page,pagesize value3:页码、页显示最大记录数
	 * @return 参数类型分页列表
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 得到参数类型总数
	 * @param map
	 * key1:ifQuery value1:查询条件，null时不做判断
	 * @return 参数类型总数
	 */ 
	public long getListRows(Map<String,Object> map);
	
	/**
	 * 根据编码的此编码的行数（用于判断重复编码）
	 * @param map
	 * key1:typeCode value1:编码
	 * key2:id value2:id为null时，不做判断
	 * @return
	 */
	public long getRowsByCode(Map<String,Object> map);
	
	
	/**
	 * 得到参数类型树数据列表
	 * @return
	 */
	public List<Map<String,Object>> getTreeList();
	
	/**
	 * 根据编码得到对应的参数类型
	 * @param code 编码
	 * @return
	 */
	public SysParamType getParamTypeByCode(String code);
}