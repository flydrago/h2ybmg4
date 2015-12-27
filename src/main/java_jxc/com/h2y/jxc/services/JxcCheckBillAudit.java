package com.h2y.jxc.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.basic.WbsKeys.SInvokeKeys;
import com.h2y.bmg.entity.SysUser;
import com.h2y.jxc.basic.JxcKeys.BillAuditLogKeys;
import com.h2y.jxc.dao.IJxcCheckDao;
import com.h2y.jxc.entity.JxcCheck;
import com.h2y.jxc.util.DataResponseUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.StringUtil;

@Service("checkBillAudit")
public class JxcCheckBillAudit implements IJxcBillAuditService{

	@Autowired
	private IJxcCheckDao jxcCheckDao;
	
	@Autowired
	private IJxcAuditLogService jxcAuditLogService;
	
	/**
	 * 仓库盘点单  单据审核接口
	 */
	public Map<String, Object> billAudit(Map<String, Object> reqMap) {
		Map<String,Object> paraMap = JSONUtil.getMap(reqMap.get(SInvokeKeys.postData.value()));
		
		//请求参数
		Long billId = Long.valueOf(StringUtil.objectToString(paraMap.get("billId")));		//单据ID
		int checkStatus = Integer.valueOf(StringUtil.objectToString(paraMap.get("checkStatus")));		//单据审核状态
		String op = StringUtil.objectToString(paraMap.get("op"));		//单据操作类型
		SysUser operator = (SysUser) reqMap.get("operator");
		
		//获取将要审核的单据
		JxcCheck checkBill  = jxcCheckDao.get(billId); 
		
		if("audit".equals(op)){	//审核
			if(checkStatus == 1){	//如果单据审核通过
				checkBill.setCheckStatus(checkStatus);
				jxcCheckDao.update(checkBill);
				
				/**记录单据办理流程**/
				Map<String,Object> auditLogParaMap = new HashMap<String, Object>();
				auditLogParaMap.put("bill", checkBill);
				auditLogParaMap.put("operator", operator);
				auditLogParaMap.put("auditStage", BillAuditLogKeys.firstAuditStage.value());
				auditLogParaMap.put("auditProcess", BillAuditLogKeys.firstAudit.value());
				jxcAuditLogService.recordBillAuditLog(auditLogParaMap);
				
				return DataResponseUtil.getResultData(reqMap, 1, "单据审核完成");
			}else{	//单据 审核不通过 （管理员不通过）
				checkBill.setCheckStatus(checkStatus);
				jxcCheckDao.update(checkBill);
				
				/**记录单据办理流程**/
				Map<String,Object> auditLogParaMap = new HashMap<String, Object>();
				auditLogParaMap.put("bill", checkBill);
				auditLogParaMap.put("operator", operator);
				auditLogParaMap.put("auditStage", BillAuditLogKeys.firstAuditStage.value());
				auditLogParaMap.put("auditProcess", BillAuditLogKeys.firstAudit.value());
				jxcAuditLogService.recordBillAuditLog(auditLogParaMap);
				
				return DataResponseUtil.getResultData(reqMap, 1, "单据审核完成");
			}
		}else{
			return DataResponseUtil.getResultData(reqMap, 0, "其他操作类型");
		}
	}
}
