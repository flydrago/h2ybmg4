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
import com.h2y.jxc.dao.IJxcBillDetailDao;
import com.h2y.jxc.dao.IJxcPurchaseReceiptsDao;
import com.h2y.jxc.entity.JxcBillDetail;
import com.h2y.jxc.entity.JxcPurchaseReceipts;
import com.h2y.jxc.util.DataResponseUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.StringUtil;

/**
 * 采购入库单 审核
 * @author jyd-yfb-02
 */
@Service("purchaseReceiptsBillAudit")
public class JxcPurchaseReceiptsBillAudit implements IJxcBillAuditService{

	@Autowired
	private IJxcPurchaseReceiptsDao jxcPurchaseReceiptsDao;
	
	@Autowired
	private IJxcBillDetailDao jxcBillDetailDao;
	
	@Autowired
	private IJxcStorehouseService jxcStorehouseService;
	
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
		JxcPurchaseReceipts tmpBill = jxcPurchaseReceiptsDao.get(billId);
		
		if(checkStatus == 1){	//如果单据审核通过
			
			//获取 审核的单据 中包含的 商品信息
			Map<String,Object> argMap = new HashMap<String, Object>();
			argMap.put("billNo", tmpBill.getBillNo());
			List<JxcBillDetail> billGoodsList = jxcBillDetailDao.getBillGoodsDetailList(argMap);
			//将单据中的商品信息转化成 仓库 库存的变更
			Long storageId = tmpBill.getStorageId();	//单据 - 仓库ID
			Long brokerId = tmpBill.getBrokerId();		//单据 - 经手人ID
			
			Map<String,Object> RtransSMap = new HashMap<String, Object>();
			RtransSMap.put("billGoodsList", billGoodsList);
			RtransSMap.put("storageId", storageId);
			RtransSMap.put("brokerId", brokerId);
			if(tmpBill.getBillStatus() == 0){	//如果单据是采购单
				RtransSMap.put("storeOp", StorageKeys.inStorage.value());	//库存加减（出入库操作）
			}else if(tmpBill.getBillStatus() == 1){		//如果单据是冲账单
				RtransSMap.put("storeOp", StorageKeys.exStorage.value());
			}
			
			Map<String,Object> resMap = jxcStorehouseService.receiptsTransStorage(RtransSMap);

			//单据转化库存成功标志  （1：成功   0：失败）
			int resultFlag = Integer.valueOf(resMap.get("resultFlag")+"");
				
			//设置单据审核状态
			if(resultFlag == 1){	//通过审核
				if(tmpBill.getBillStatus() == 0){	//单据是采购单
					tmpBill.setCheckStatus(BillAuditKeys.approved.value());
				}else if(tmpBill.getBillStatus() == 1){ //单据是 冲账单
					//更新冲账单的 审核状态 为 已红冲
					tmpBill.setCheckStatus(BillAuditKeys.strikeBalance.value());
					//更新冲账单对应的 原采购单状态为 已红冲
					JxcPurchaseReceipts originalBill = jxcPurchaseReceiptsDao.get(tmpBill.getData1());
					originalBill.setCheckStatus(BillAuditKeys.strikeBalance.value());
					jxcPurchaseReceiptsDao.update(originalBill);
				}
			}else{	//未通过审核
				tmpBill.setCheckStatus(BillAuditKeys.notapproved.value());
			}
				
			//更新单据 审核状态
			jxcPurchaseReceiptsDao.update(tmpBill);
				
			if(op.equals("audit")){			//审核
				/**记录单据办理流程**/
				Map<String,Object> auditLogParaMap = new HashMap<String, Object>();
				auditLogParaMap.put("bill", tmpBill);
				auditLogParaMap.put("operator", operator);
				auditLogParaMap.put("auditStage", BillAuditLogKeys.firstAuditStage.value());
				auditLogParaMap.put("auditProcess", BillAuditLogKeys.firstAudit.value());
				jxcAuditLogService.recordBillAuditLog(auditLogParaMap);
				
			}else if(op.equals("strikeBalance")){	//冲账
				/**记录单据办理流程**/
				//冲账单审核记录
				Map<String,Object> auditLogParaMap = new HashMap<String, Object>();
				auditLogParaMap.put("bill", tmpBill);
				auditLogParaMap.put("operator", operator);
				auditLogParaMap.put("auditStage", BillAuditLogKeys.strikeBalanceStage.value());
				auditLogParaMap.put("auditProcess", BillAuditLogKeys.strikeBalance.value());
				jxcAuditLogService.recordBillAuditLog(auditLogParaMap);
				
				//冲账单对应的原单 审核记录
				Map<String,Object> auditLogParaMapOriginal = new HashMap<String, Object>();
				auditLogParaMapOriginal.put("bill", jxcPurchaseReceiptsDao.get(tmpBill.getData1()));	//原单
				auditLogParaMapOriginal.put("operator", operator);
				auditLogParaMapOriginal.put("auditStage", BillAuditLogKeys.strikeBalanceStage.value());
				auditLogParaMapOriginal.put("auditProcess", BillAuditLogKeys.strikeBalance.value());
				jxcAuditLogService.recordBillAuditLog(auditLogParaMapOriginal);
			}
			return DataResponseUtil.getResultData(reqMap, resultFlag, resMap.get("resultMsg")+"");
		}else{
			tmpBill.setCheckStatus(checkStatus);
			//更新单据 审核状态
			jxcPurchaseReceiptsDao.update(tmpBill);
			
			/**记录单据办理流程**/
			Map<String,Object> auditLogParaMap = new HashMap<String, Object>();
			auditLogParaMap.put("bill", tmpBill);
			auditLogParaMap.put("operator", operator);
			auditLogParaMap.put("auditStage", BillAuditLogKeys.firstAuditStage.value());
			auditLogParaMap.put("auditProcess", BillAuditLogKeys.firstAudit.value());
			jxcAuditLogService.recordBillAuditLog(auditLogParaMap);
			
			return DataResponseUtil.getResultData(reqMap, 1, "单据审核完成。");
		}
	}
}
