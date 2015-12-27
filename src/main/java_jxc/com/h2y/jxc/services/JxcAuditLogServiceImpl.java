package com.h2y.jxc.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.entity.SysUser;
import com.h2y.jxc.basic.BaseBill;
import com.h2y.jxc.dao.IJxcAuditLogDao;
import com.h2y.jxc.entity.JxcAuditLog;
import com.h2y.util.DateUtil;

/**
  * 进销存  单据审核日志 ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-08-03
 */
@Service("jxcAuditLogService")
public class JxcAuditLogServiceImpl implements IJxcAuditLogService{


	@Autowired
	protected IJxcAuditLogDao jxcAuditLogDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * @param jxcAuditLog
	 */
	public void add(JxcAuditLog jxcAuditLog) {
		jxcAuditLogDao.add(jxcAuditLog);
	}


	public void delete(long id) {
		jxcAuditLogDao.deleteById(id);
	}

	public void update(JxcAuditLog jxcAuditLog) {
		jxcAuditLogDao.update(jxcAuditLog);
	}

	public JxcAuditLog get(long id) {
		return jxcAuditLogDao.get(id);
	}

	public List<JxcAuditLog> getList(JxcAuditLog jxcAuditLog){
		List<JxcAuditLog> list = jxcAuditLogDao.getList(jxcAuditLog);
		return list;
	}

	/**
	 * getListPage
	 * @return
	 */
	public List<JxcAuditLog> getListPage(Map<String,Object> map){
		return jxcAuditLogDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){
		return jxcAuditLogDao.getRows(map);
	}


	/**
	 * 查询单据审核日志
	 */
	public Map<String, Object> getBillAuditLogGridData(HttpServletRequest request) {

		Map<String,Object> gridMap = new HashMap<String, Object>();
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		String billNo = request.getParameter("billNo");
		
		
		Map<String,Object> paraMap = new HashMap<String, Object>();
		paraMap.put("page", Integer.parseInt(page));
		paraMap.put("pagesize", Integer.parseInt(pagesize));
		paraMap.put("sortorder", sortorder);
		paraMap.put("sortname", sortname);
		paraMap.put("ifQuery", ifQuery);
		paraMap.put("billNo", billNo);
		
		
		List<Map<String, Object>> dataList = jxcAuditLogDao.getBillAuditLog(paraMap);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridMap.put("Rows", dataList);
		gridMap.put("Total", jxcAuditLogDao.getBillAuditLogRows(paraMap));
		
		return gridMap;
	}


	/**
	 * 记录单据办理流程
	 */
	public void recordBillAuditLog(Map<String, Object> paraMap) {
		JxcAuditLog auditLog = new JxcAuditLog();
		BaseBill tmpBill = (BaseBill) paraMap.get("bill");
		SysUser operator = (SysUser) paraMap.get("operator");
		
		auditLog.setBillNo(tmpBill.getBillNo());
		auditLog.setAuditProcess(paraMap.get("auditProcess")+"");
		auditLog.setAuditStage(Integer.valueOf(paraMap.get("auditStage")+""));
		auditLog.setCreateDate(DateUtil.getSystemTime());
		auditLog.setHandlerId(operator.getId());
		auditLog.setHandler(operator.getUserName());
		
		jxcAuditLogDao.add(auditLog);
	}
}