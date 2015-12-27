package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.Zone;

/**
 * ZoneDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2014-11-04
 * email:info@hwttnet.com
 */
@Component
public interface IZoneDao{

	/**
	 * add
	 */
	public void add(Zone zone);
	
	/**
	 * update
	 */
	public void update(Zone zone);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public Zone get(long id);
	
	
	public Zone getZoneByCode(String code);
	
	
	/**
	 * 根据父级Id，得到tree列表
	 * @param map
	 * key1:pid value1:父级Id（0：为顶级）
	 * key2:unitId value2:单位Id
	 * key3:code value3:当前单位区域编码
	 * @return
	 */
	public List<Map<String,Object>> getTreeList(Map<String,Object> map);
	
	
	public List<Zone> getList(Map<String,Object> map);
	
	
	/**
	 * 根据父级Id，得到grid列表
	 * @param map
	 * key1:pid value1:父级Id（0：为顶级）
	 * key2:unitId value2:单位Id
	 * key3:code value3:当前单位区域编码
	 * @return
	 */
	public List<Map<String,Object>> getGridList(Map<String,Object> map);
	
	
	/**
	 * 得到当前位置名称
	 * @param map
	 * @return
	 */
	public String getCurrentName(Map<String,Object> map);
	
	
	/**
	 * 根据父级Id，得到下面最大子级的编码
	 * @param pid 父级Id
	 * @return
	 */
	public String getMaxCodeByPid(String pid);
	
	/**
	 * 根据父级Id，得到子级的行数
	 * @param pid
	 * @return
	 */
	public long getChildRowsByPid(String pid);
	
	
	public Zone getZoneCodeByName(String zoneName);
	
	
	public List<Map<String, Object>> getAll(Map<String, Object> map);
	
	public List<Map<String, Object>> getAllSize(Map<String, Object> map);
}