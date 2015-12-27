package com.h2y.jxc.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.entity.SysUser;
import com.h2y.jxc.basic.JxcKeys.BillAuditLogKeys;
import com.h2y.jxc.dao.IJxcBillDetailDao;
import com.h2y.jxc.dao.IJxcPurchaseReceiptsDao;
import com.h2y.jxc.entity.JxcBillCheckParams;
import com.h2y.jxc.entity.JxcBillDetail;
import com.h2y.jxc.entity.JxcPurchaseReceipts;
import com.h2y.jxc.util.DataResponseUtil;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
  * 进销存  采购入库单 ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */
@Service("jxcPurchaseReceiptsService")
public class JxcPurchaseReceiptsServiceImpl implements IJxcPurchaseReceiptsService{


	@Autowired
	protected IJxcPurchaseReceiptsDao jxcPurchaseReceiptsDao;

	@Autowired
	protected IBillCheckService purchaseReceiptsBillCheck;
	
	@Autowired
	protected IJxcBillDetailDao jxcBillDetailDao;
	
	@Autowired
	protected IJxcStorehouseService jxcStorehouseService;
	
	@Autowired
	protected IJxcAuditLogService jxcAuditLogService;
	
	@Autowired
	protected IJxcBillAuditService purchaseReceiptsBillAudit;
	
	/**
	 * @param jxcPurchaseReceipts
	 */
	public void add(JxcPurchaseReceipts jxcPurchaseReceipts) {
		jxcPurchaseReceiptsDao.add(jxcPurchaseReceipts);
	}


	public void delete(long id) {
		jxcPurchaseReceiptsDao.deleteById(id);
	}

	public void update(JxcPurchaseReceipts jxcPurchaseReceipts) {
		jxcPurchaseReceiptsDao.update(jxcPurchaseReceipts);
	}

	public JxcPurchaseReceipts get(long id) {
		return jxcPurchaseReceiptsDao.get(id);
	}


	public List<JxcPurchaseReceipts> getList(JxcPurchaseReceipts jxcPurchaseReceipts){
		List<JxcPurchaseReceipts> list = jxcPurchaseReceiptsDao.getList(jxcPurchaseReceipts);

		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<JxcPurchaseReceipts> getListPage(Map<String,Object> map){
		return jxcPurchaseReceiptsDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return jxcPurchaseReceiptsDao.getRows(map);
	}


	/**
	 * 采购入库单保存
	 */
	public Map<String, Object> inStorageSave(Map<String, Object> reqMap) {
		//单据校验
		JxcBillCheckParams billCheckParams = purchaseReceiptsBillCheck.billCheck(reqMap);
		if(billCheckParams.getResultFlag() == 1){
			//校验成功 ， 保存单据信息 
			JxcPurchaseReceipts prBill= (JxcPurchaseReceipts) billCheckParams.getBill();
			jxcPurchaseReceiptsDao.add(prBill);
			
			/**记录单据办理过程**/
			Map<String,Object> paraMap = new HashMap<String, Object>();
			paraMap.put("bill", prBill);
			paraMap.put("operator", (SysUser)reqMap.get("operator"));
			paraMap.put("auditStage", BillAuditLogKeys.pendingAuditStage.value());
			paraMap.put("auditProcess", BillAuditLogKeys.pendingAudit.value());
			jxcAuditLogService.recordBillAuditLog(paraMap);
			
			return DataResponseUtil.getResultData(reqMap, billCheckParams.getResultFlag(), billCheckParams.getResultMsg(),prBill);
		}else{
			//校验失败
			return DataResponseUtil.getResultData(reqMap, billCheckParams.getResultFlag(), billCheckParams.getResultMsg());
		}
	}


	/**
	 * 获取采购单 表格数据
	 */
	public Map<String, Object> getGridData(HttpServletRequest request) {
		Map<String,Object> gridMap = new HashMap<String, Object>();
		
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		
		/*if (sortname != null && !sortname.equals("")) {

			if (sortname.equals("TYPE_NAME")) {
				sortname = "gt.type_name";
			} else if (sortname.equals("GOODS_UNIT_NAME")) {
				sortname = "g.goods_unit";
			} else {
				sortname = "g." + sortname.toLowerCase();
			}
		}*/

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("ifQuery", ifQuery);
		List<Map<String, Object>> dataList = jxcPurchaseReceiptsDao.getListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridMap.put("Rows", dataList);
		gridMap.put("Total", jxcPurchaseReceiptsDao.getListRows(map));
		
		return gridMap;
	}


	/**
	 * 获取单据 商品明细
	 */
	public List<Map<String, Object>> getBillGoods(String billNo) {
		Map<String,Object> paraMap = new HashMap<String, Object>();
		paraMap.put("billNo", billNo);
		List<Map<String,Object>> billGoodsList = jxcBillDetailDao.getBillGoodsList(paraMap);
		return billGoodsList;
	}


	/**
	 * 采购入库单 单据冲账操作
	 */
	@Transactional(rollbackFor=Exception.class)
	public Map<String, Object> billStrikeBalance(Map<String,Object> postMap) {
		//原采购入库单 单据信息
		Long billId = Long.valueOf(postMap.get("billId")+"");
		SysUser billMaker = (SysUser) postMap.get("billMaker");
		JxcPurchaseReceipts originalBill = jxcPurchaseReceiptsDao.get(billId);
		
		/**据 原采购入库单 生成一张 冲账单**/
		JxcPurchaseReceipts czBill = new JxcPurchaseReceipts(); //冲账单
		//冲账单基本属性
		czBill.setBillNo("CGRKCZ-"+originalBill.getBillNo().substring(6, 25));
		czBill.setSupplierId(originalBill.getSupplierId());
		czBill.setSupplier(originalBill.getSupplier());
		czBill.setStorageId(originalBill.getStorageId());
		czBill.setStorage(originalBill.getStorage());
		czBill.setBrokerId(originalBill.getBrokerId());
		czBill.setBroker(originalBill.getBroker());
		czBill.setPaymentAccountId(originalBill.getPaymentAccountId());
		czBill.setPaymentAccount(originalBill.getPaymentAccount());
		czBill.setCurrentPayment(originalBill.getCurrentPayment());
		czBill.setPaymentDate(originalBill.getPaymentDate());
		czBill.setNotes("该单据被[采购入库单"+originalBill.getBillNo()+"]冲账");
		czBill.setTotalAmount(originalBill.getTotalAmount());
		czBill.setDiscount(originalBill.getDiscount());
		czBill.setPayableAmount(originalBill.getPayableAmount());
		czBill.setDiscountAmount(originalBill.getDiscountAmount());
		czBill.setBillMaker(billMaker.getUserName());
		czBill.setCreateDate(DateUtil.getSystemTime());
		//冲账单 对应 的原采购单
		czBill.setData1(originalBill.getId());
		//冲账单标识
		czBill.setBillStatus(1);
		//保存冲账单（保存后会返回冲账单billId）
		jxcPurchaseReceiptsDao.add(czBill);
		//保存后再根据billNo获取 单据信息
		czBill = jxcPurchaseReceiptsDao.getBillByBillNo(czBill.getBillNo());
		//获取 原单 商品信息
		Map<String,Object> argMap = new HashMap<String, Object>();
		argMap.put("billNo", originalBill.getBillNo());
		List<JxcBillDetail> billDetailList = jxcBillDetailDao.getBillGoodsDetailList(argMap);
		//将 原单 商品信息 转化为 冲账单 商品信息
		for(JxcBillDetail goodDetail : billDetailList){
			JxcBillDetail czGoodDetail = new JxcBillDetail();
			
			czGoodDetail.setBillNo(czBill.getBillNo());
			czGoodDetail.setStorehouseId(goodDetail.getStorehouseId());
			czGoodDetail.setStorehouseName(goodDetail.getStorehouseName());
			czGoodDetail.setGoodsId(goodDetail.getGoodsId());
			czGoodDetail.setGoodsNickName(goodDetail.getGoodsNickName());
			czGoodDetail.setGoodsNumber(goodDetail.getGoodsNumber());
			czGoodDetail.setSinglePrice(goodDetail.getSinglePrice());
			czGoodDetail.setGoodsCount(goodDetail.getGoodsCount());
			czGoodDetail.setTotalAmount(goodDetail.getTotalAmount());
			czGoodDetail.setCreateDate(DateUtil.getSystemTime());
			czGoodDetail.setNotes(goodDetail.getNotes());
			czGoodDetail.setData1(goodDetail.getData1());
			czGoodDetail.setData2(goodDetail.getData2());
			czGoodDetail.setData3(goodDetail.getData3());
			czGoodDetail.setData4(goodDetail.getData4());
			czGoodDetail.setData5(goodDetail.getData5());
			czGoodDetail.setData6(goodDetail.getData6());
			czGoodDetail.setData7(goodDetail.getData7());
			//添加冲账单 商品详细信息
			jxcBillDetailDao.add(czGoodDetail);
		}
		
		
		/**审核冲账单 转化库存**/
		
		//构造审核参数
		Map<String,Object> postData = new HashMap<String, Object>();
		postData.put("billId", czBill.getId());
		postData.put("checkStatus", 1);
		postData.put("op", "strikeBalance");
		Map<String,Object> reqMap = new HashMap<String,Object>();
		reqMap.put("postData", JSONUtil.getJson(postData));
		reqMap.put("operator", billMaker);
		
		//审核冲账单
		Map<String,Object> auditResult = purchaseReceiptsBillAudit.billAudit(reqMap);
		
		return auditResult;
	}
}