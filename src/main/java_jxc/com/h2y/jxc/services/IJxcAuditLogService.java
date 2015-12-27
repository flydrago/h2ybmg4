package com.h2y.jxc.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.jxc.entity.JxcAuditLog;

/**
 * 进销存 审核日志 Service
 * @author hwttnet
 * version:1.2
 * time:2015-08-03
 * 
 * Service Interface
 */
public interface IJxcAuditLogService{
	
	public void add(JxcAuditLog jxcAuditLog);
	
	public void delete(long id);
	
	public void update(JxcAuditLog jxcAuditLog);

	public JxcAuditLog get(long id);
	
	public List<JxcAuditLog> getList(JxcAuditLog jxcAuditLog);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<JxcAuditLog> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map);

	/**
	 * 查询单据审核日志
	 * @param request
	 * @return
	 */
	public Map<String,Object> getBillAuditLogGridData(HttpServletRequest request);
	
	/**
	 * 记录单据办理流程
	 */
	public void recordBillAuditLog(Map<String,Object> paraMap);
}
