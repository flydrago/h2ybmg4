package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.SysUnits;

/**
 * SysUnitsDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2014-10-19
 * email:info@hwttnet.com
 */
@Component
public interface ISysUnitsDao{

	/**
	 * add
	 */
	public void add(SysUnits sysUnits);

	/**
	 * update
	 */
	public void update(SysUnits sysUnits);

	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public SysUnits get(long id);


	/**
	 * 得到单位数列表
	 * @return
	 */
	public List<Map<String,Object>> getUnitTreeData();


	/**
	 *   树
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getUnitTreeDataByUnitId(Map<String,Object> map);

	/**
	 * 省级代理审核旗舰店
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getUnitTreeDataByProvinceId(Map<String,Object> map);

	/**
	 * 得到单位分页列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getUnitListMap(Map<String,Object> map);

	/**
	 * 得到单位列表总数
	 * @param map
	 * @return
	 */
	public long getUnitListRows(Map<String,Object> map);


	/**
	 * 得到为审核的公司列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getUnCheckListMap(Map<String,Object> map);


	/**
	 * 得到为审核的公司列表
	 * @param map
	 * @return
	 */
	public long getUnCheckListRows(Map<String,Object> map);

	/**
	 * 得到审核通过单位的列表
	 * @return
	 */
	public List<Map<String,Object>> getPassUnitList();

	/**
	 * 得到子级区域列表
	 * @param map
	 * key1:flag value1:first:一级列表，other:子级列表
	 * key2:pcode value2:父级编码
	 * @return
	 */
	public List<Map<String,Object>> getChildZoneList(Map<String,Object> map);

	/**
	 * 根据Pid，得到子级区域列表
	 * @param pid
	 * @return
	 */
	public List<Map<String,Object>> getChildZoneListByPid(long pid);

	/**
	 * 根据Code，得到前缀
	 * @param code
	 * @return
	 */
	public String getZonePrefixByCode(String code);


	/**
	 * 得到区号的代理
	 * key:zoneCode value:区域编码
	 * key:id value:null时，不做判断
	 * @return
	 */
	public long getUnitRowsByZoneCode(Map<String,Object> map);

	/**
	 * 判断域名是否重复
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getUnitRowsBySame(Map<String,Object> map);

	/**
	 * 获取注册过单位信息的地域列表
	 * @param paraMap
	 * @return
	 */
	public List<Map<String,Object>> getRegisteredZoneList(Map<String, Object> paraMap);

	/**
	 * 获取所有的注册过单位信息的地域列表
	 * @param paraMap
	 * @return
	 */
	public List<Map<String,Object>> getAllRegisteredZoneList(Map<String, Object> paraMap);

	/**
	 * 根据zoneCode获取
	 * @param map
	 * @return
	 */
	public SysUnits getUnitByZoneCode(Map<String,Object> map);

	/**
	 * 获取省级代理的 区域
	 * @param paraMap
	 * @return
	 */
	public List<Map<String, Object>> getProvincialAgentZoneList(
			Map<String, Object> paraMap);
	
	/**
	 * 获取市级代理的 区域
	 * @param paraMap
	 * @return
	 */
	public List<Map<String, Object>> getMunicipalAgentZoneList(
			Map<String, Object> paraMap);

	/**
	 * 获取县级代理的 区域
	 * @param paraMap
	 * @return
	 */
	public List<Map<String, Object>> getCountyAgentZoneList(
			Map<String, Object> paraMap);

	public List<Map<String, Object>> getAreaList(Map<String, Object> paraMap);
}