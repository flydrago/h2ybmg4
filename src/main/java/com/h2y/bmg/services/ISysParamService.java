package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.SysParam;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2014-12-05
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ISysParamService{
	
	public void add(SysParam sysParam);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(SysParam sysParam);

	public SysParam get(long id);
	
	/**
	 * 得到列表数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getGirdData(HttpServletRequest request,long unitId);
	
	/**
	 * 得到参数类型树数据
	 * @return
	 */
	public String getParamTypeTreeData();
	
	
	/**
	 * 是否有相同编码
	 * @param op
	 * @param sysParam
	 * @return
	 */
	public boolean isHasSameCode(String op,SysParam sysParam);
	
	
	public void save(String op,long unitId,SysParam sysParam);
	
	/**
	 * 根据编码的得到对应的参数（对外开放）
	 * @param unitId 单位Id
	 * @param typeCode 类型编码
	 * @param code 编码
	 * @return
	 */
	public SysParam getSysParamByCode(long unitId,String typeCode,String code);
	
	
	/**
	 * 根据类型编码的得到对应参数的列表（对外开放）
	 * @param unitId 单位Id
	 * @param typeCode 类型编码
	 * @return
	 */
	public List<SysParam> getSysParamListByTypeCode(long unitId,String typeCode);
	
}
