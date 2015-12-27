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
import com.h2y.jxc.dao.IJxcSalesOutboundDao;
import com.h2y.jxc.entity.JxcBillDetail;
import com.h2y.jxc.entity.JxcSalesOutbound;
import com.h2y.jxc.util.DataResponseUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.StringUtil;

/**
 * 销售出库单 审核
 * @author jyd-yfb-02
 */
@Service("saleoutBillAudit")
public class JxcSaleoutBillAudit implements IJxcBillAuditService{

	@Autowired
	private IJxcSalesOutboundDao jxcSalesOutboundDao;
	
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
		JxcSalesOutbound tmpBill = jxcSalesOutboundDao.get(billId);
		
		if(op.equals("audit")){			//审核
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
				RtransSMap.put("storeOp", StorageKeys.exStorage.value());
				Map<String,Object> resMap = jxcStorehouseService.receiptsTransStorage(RtransSMap);
				
				int resultFlag = Integer.valueOf(resMap.get("resultFlag")+"");
				
				//设置单据审核状态
				if(resultFlag == 0){	//未通过审核
					tmpBill.setCheckStatus(BillAuditKeys.notapproved.value());
				}else{	//通过审核
					tmpBill.setCheckStatus(BillAuditKeys.approved.value());
				}
				
				//更新单据 审核状态
				jxcSalesOutboundDao.update(tmpBill);
				
				/**记录单据办理流程**/
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
				jxcSalesOutboundDao.update(tmpBill);
				
				/**记录单据办理流程**/
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
