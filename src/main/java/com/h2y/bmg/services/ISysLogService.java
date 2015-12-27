package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.SysLog;
import com.h2y.bmg.entity.SysUser;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2014-10-28
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ISysLogService{
	
	public void add(SysLog sysLog);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(SysLog sysLog);

	public SysLog get(long id);
	
	/**
	 * 添加日志
	 * @param request 访问对象
	 * @param sysUser 操作用户
	 * @param moduleName 模块名称
	 * @param opType 操作类型
	 * @param opResult 操作结果
	 * @param memo 操作备注
	 */
	public void addLog(HttpServletRequest request,SysUser sysUser,
			String moduleName,String opType,String opResult,String memo);
	
	
	/**
	 * 添加日志
	 * @param request 访问对象
	 * @param sysUser 操作用户
	 * @param moduleName 模块名称
	 * @param opType 操作类型
	 * @param opResult 操作结果
	 * @param memo 操作备注
	 * @param businessId 业务Id，多个用逗点分割
	 * @param tableName 业务表
	 */
	public void addLog(HttpServletRequest request,SysUser sysUser,
			String moduleName,String opType,String opResult,String memo,String businessId,String tableName);
	
	/**
	 * 得到列表
	 * @param request
	 * @param unitId
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId);
}
