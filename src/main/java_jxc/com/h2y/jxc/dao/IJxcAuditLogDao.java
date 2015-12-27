package com.h2y.jxc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.jxc.entity.JxcAuditLog;
/**
 * 进销存 单据审核日志 DAO
 * @author hwttnet
 * version:1.2
 * time:2015-08-03
 */
@Component
public interface IJxcAuditLogDao{

	/**
	 * add
	 */
	public void add(JxcAuditLog jxcAuditLog);
	
	/**
	 * update
	 */
	public void update(JxcAuditLog jxcAuditLog);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public JxcAuditLog get(long id);
	
	/**
	 * getList
	 * @return
	 */
	public List<JxcAuditLog> getList(JxcAuditLog jxcAuditLog);
	

	/**
	 * getListPage
	 * page,pagesize,key
	 * @return
	 */
	public List<JxcAuditLog> getListPage(Map<String,Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String,Object> map);

	/**
	 * 获取单据办理日志
	 * @param paraMap
	 * @return
	 */
	public List<Map<String,Object>> getBillAuditLog(Map<String, Object> paraMap);

	/**
	 * 获取单据办理日志 总数
	 * @param paraMap
	 * @return
	 */
	public Long getBillAuditLogRows(Map<String, Object> paraMap);
}