package com.h2y.jxc.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.basic.WbsKeys.SInvokeKeys;
import com.h2y.bmg.entity.SysUser;
import com.h2y.jxc.basic.JxcKeys.BillAuditKeys;
import com.h2y.jxc.basic.JxcKeys.BillAuditLogKeys;
import com.h2y.jxc.basic.JxcKeys.StorageKeys;
import com.h2y.jxc.dao.IJxcBreakageDao;
import com.h2y.jxc.dao.IJxcProfitandlossDetailDao;
import com.h2y.jxc.entity.JxcBreakage;
import com.h2y.jxc.entity.JxcProfitandlossDetail;
import com.h2y.jxc.util.DataResponseUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.StringUtil;

/**
 *	进销存 报损单 单据审核接口 
 * @author jyd-yfb-02
 */
@Service("breakageBillAudit")
public class JxcBreakageBillAudit implements IJxcBillAuditService{

	@Autowired
	private IJxcStorehouseService jxcStorehouseService;
	
	@Autowired
	private IJxcBreakageDao jxcBreakageDao;
	
	@Autowired
	private IJxcProfitandlossDetailDao jxcProfitandlossDetailDao;
	
	@Autowired
	private IJxcAuditLogService jxcAuditLogService;
	
	public Map<String, Object> billAudit(Map<String, Object> reqMap) {
		Map<String,Object> paraMap = JSONUtil.getMap(reqMap.get(SInvokeKeys.postData.value())+"");
		//请求参数
		Long billId = Long.valueOf(StringUtil.objectToString(paraMap.get("billId")));		//单据ID
		int checkStatus = Integer.valueOf(StringUtil.objectToString(paraMap.get("checkStatus")));		//审核状态
		String op = StringUtil.objectToString(paraMap.get("op"));		//操作类型
		SysUser operator = (SysUser) reqMap.get("operator");
		
		//获取将要审核的单据
		JxcBreakage tmpBill = jxcBreakageDao.get(billId);
		
		if(op.equals("audit")){			//审核
			if(checkStatus == 1){	//如果单据审核通过
				//获取 审核的单据 中包含的 商品信息
				Map<String,Object> argMap = new HashMap<String, Object>();
				argMap.put("billNo", tmpBill.getBillNo());
				List<JxcProfitandlossDetail> billGoodsList = jxcProfitandlossDetailDao.getBillGoodsDetailList(argMap);
				//将单据中的商品信息转化成 仓库库存的变更
				Long storageId = tmpBill.getStorageId();	//单据 - 仓库ID
				Long brokerId = tmpBill.getBrokerId();		//单据 - 经手人ID
				
				Map<String,Object> transMap = new HashMap<String, Object>();
				transMap.put("billGoodsList", billGoodsList);
				transMap.put("storageId", storageId);
				transMap.put("brokerId", brokerId);
				transMap.put("storeOp", StorageKeys.breakage.value());
				Map<String,Object> resMap = jxcStorehouseService.profitAndLossTransStorage(transMap);
				
				int resultFlag = Integer.valueOf(resMap.get("resultFlag")+"");
				
				//设置单据审核状态
				if(resultFlag == 1){	//通过审核
					tmpBill.setCheckStatus(BillAuditKeys.approved.value());
				}else{	//未通过审核
					tmpBill.setCheckStatus(BillAuditKeys.notapproved.value());
				}
				
				//更新单据 审核状态
				jxcBreakageDao.update(tmpBill);
				
				/**记录 审核日志**/
				Map<String,Object> auditLogParaMap = new HashMap<String, Object>();
				auditLogParaMap.put("bill", tmpBill);
				auditLogParaMap.put("operator", operator);
				auditLogParaMap.put("auditStage", BillAuditLogKeys.firstAuditStage.value());
				auditLogParaMap.put("auditProcess", BillAuditLogKeys.firstAudit.value());
				jxcAuditLogService.recordBillAuditLog(auditLogParaMap);
				
				return DataResponseUtil.getResultData(reqMap, resultFlag, resMap.get("resultMsg")+"");
			}else{
				tmpBill.setCheckStatus(checkStatus);
				//更新单据 审核状态
				jxcBreakageDao.update(tmpBill);
				
				/**记录 审核日志**/
				Map<String,Object> auditLogParaMap = new HashMap<String, Object>();
				auditLogParaMap.put("bill", tmpBill);
				auditLogParaMap.put("operator", operator);
				auditLogParaMap.put("auditStage", BillAuditLogKeys.firstAuditStage.value());
				auditLogParaMap.put("auditProcess", BillAuditLogKeys.firstAudit.value());
				jxcAuditLogService.recordBillAuditLog(auditLogParaMap);
				
				return DataResponseUtil.getResultData(reqMap, 1, "单据审核完成。");
			}
		}else{
			return DataResponseUtil.getResultData(reqMap, 0, "其他操作类型");
		}
	}

}
