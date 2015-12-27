package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;

/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2014-10-19
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ISysUnitsService{

	public void add(SysUnits sysUnits);

	public void delete(long id);

	public void update(SysUnits sysUnits);

	public SysUnits get(long id);

	/**
	 * 得到grid列表
	 * @param request
	 * @param parentId
	 * @return
	 */
	public Map<String,Object> getGridData(long unitId,HttpServletRequest request,long parentId);


	/**
	 * 得到注册单位树数据
	 * @return
	 */
	public List<Map<String,Object>> getUnitTreeData();


	/**
	 * 获取树
	 * @param unitId
	 * @return
	 */
	public List<Map<String,Object>> getUnitTreeDataByUnitId(long unitId);

	/**
	 * 省级代理审核旗舰店
	 * @param unitId
	 * @return
	 */
	public List<Map<String,Object>> getUnitTreeDataByProvinceId(long unitId);

	/**
	 * 审核单位
	 * @param request
	 * @param sysUnits
	 */
	public void checkUnit(HttpServletRequest request,SysUnits sysUnits,SysUser loginSysUser);

	/**
	 * 得到区域列表
	 * @param request
	 * @param code
	 */
	public List<Map<String,Object>> getChildZoneList(HttpServletRequest request,String code);



	/**
	 * 得到一级区域列表
	 * @param request
	 * @param code
	 */
	public List<Map<String,Object>> getFirstZoneList();


	/**
	 * 根据pid，得到下拉框列表
	 * @param Pid
	 */
	public List<Map<String,Object>> getChildZoneListByPid(long Pid);


	/**
	 * 是否可以保存
	 * @param op
	 * @param sysUnits
	 * @return
	 */
	public boolean isHasSameZoneCode(String op,SysUnits sysUnits);

	/**
	 * 判断域名是否重复
	 * @param op
	 * @param sysUnits
	 * @return
	 */
	public boolean isHasSame(String op,SysUnits sysUnits,Map<String,Object> map);

	/**
	 * 保存单位详细
	 * @param request 访问对象
	 * @param op 操作类型（add:添加、modify:修改）
	 * @param sysUnits 单位对象
	 * @param dictPath 存储路径
	 */
	public void saveUnitFile(HttpServletRequest request,String op,SysUnits sysUnits,String dictPath);


	/**
	 * 得到单位文件类型列表
	 * @return
	 */
	public List<Map<String,Object>> getUnitFileTypeList();

	/**
	 * 根据父code获取区域列表
	 * @param pcode
	 * @return
	 */
	public List<Map<String,Object>> getZoneListByPCode(String pcode);

	/**
	 * 获取配送员选择区域列表
	 * @param sysUnits
	 * @return
	 */
	public List<Map<String,Object>> getZoneListForDelivery(Map<String,Object> map);

	/**
	 * 根据zoneCode 获取区域代理商
	 * @param zoneCode
	 * @return
	 */
	public SysUnits getUnitByZoneCode(String zoneCode);

	/**
	 * 选择区域时，获取选中区域的从属区域列表
	 * @param request
	 * @return
	 */
	public List<Map<String,Object>> getZoneListOnChange(HttpServletRequest request);

	public List<Map<String, Object>> getAreaList(Map<String,Object> map);
	
}
