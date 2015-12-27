package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.SysDictDetail;

/**
 * SysDictDetailDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2014-10-26
 * email:info@hwttnet.com
 */
@Component
public interface ISysDictDetailDao{

	/**
	 * add
	 */
	public void add(SysDictDetail sysDictDetail);
	
	/**
	 * update
	 */
	public void update(SysDictDetail sysDictDetail);
	
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
	public SysDictDetail get(long id);
	
	
	/**
	 * 得到分页列表
	 * @param map
	 * key1:dictMainId value:主表Id
	 * key2:unitId value:单位Id
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 得到总行数
	 * @param map
	 * key1:dictMainId value:主表Id
	 * key2:unitId value:单位Id
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
	
	/**
	 * 根据编码，得到行数
	 * @param map
	 * key1:dictMainId value1:主表Id
	 * key2:code value2:编码
	 * key3:unitId value3:单位Id
	 * key4:id value4:主键Id，null时，不做判断
	 * @return
	 */
	public long getRowsByCode(Map<String,Object> map);
	
	/**
	 * 得到所有的字典详细列表
	 * @return
	 */
	public List<SysDictDetail> getAllList();
	
	
	/**
	 * 根据主表编码，得到详细列表（包括平台公司的详细数据）
	 * @param map
	 * key:unitId value:单位Id
	 * key:mainCode value:主表编码
	 * @return
	 */
	public List<SysDictDetail> getDetailListByMainCode(Map<String,Object> map);
	
	
	/**
	 * 根据主表编码，得到详细列表（包括平台公司的详细数据）
	 * @param map
	 * key:unitId value:单位Id
	 * key:mainCode value:主表编码
	 * key:code value:详细编码
	 * @return
	 */
	public SysDictDetail getDetailByMainCode(Map<String,Object> map);
	
	
	/**
	 * 得到可继承的字典详细列表
	 * @param map
	 * {unitId:单位Id,
	 * dictMainId:字典主表Id,
	 * sortname:排序字段,
	 * sortorder:排序方式,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getExtendsListMap(Map<String,Object> map);
	
	/**
	 * 得到可继承的字典详细列表
	 * @param map
	 * {unitId:单位Id,
	 * dictMainId:字典主表Id,
	 * sortname:排序字段,
	 * sortorder:排序方式,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public long getExtendsListRows(Map<String,Object> map);
}