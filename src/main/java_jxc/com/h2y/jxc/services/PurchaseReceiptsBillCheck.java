package com.h2y.jxc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.basic.WbsKeys.SInvokeKeys;
import com.h2y.bmg.dao.IGoodsPriceDao;
import com.h2y.bmg.entity.GoodsPrice;
import com.h2y.jxc.dao.IJxcBillDetailDao;
import com.h2y.jxc.dao.IJxcContactsAccountDao;
import com.h2y.jxc.dao.IJxcContactsUnitsDao;
import com.h2y.jxc.dao.IJxcStorehouseDao;
import com.h2y.jxc.dao.IJxcSysUserDao;
import com.h2y.jxc.entity.JxcBillCheckParams;
import com.h2y.jxc.entity.JxcBillDetail;
import com.h2y.jxc.entity.JxcContactsAccount;
import com.h2y.jxc.entity.JxcContactsUnits;
import com.h2y.jxc.entity.JxcPurchaseReceipts;
import com.h2y.jxc.entity.Storehouse;
import com.h2y.jxc.entity.SysUser;
import com.h2y.util.Arith;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.StringUtil;

/**
 * 采购入库单 单据校验
 * @author jyd-yfb-02
 *
 */
@Service("purchaseReceiptsBillCheck")
public class PurchaseReceiptsBillCheck implements IBillCheckService{
	
	@Autowired
	private IJxcContactsUnitsDao jxcContactsUnitsDao;

	@Autowired
	private IJxcStorehouseDao jxcStorehouseDao;
	
	@Autowired
	private IJxcSysUserDao jxcSysUserDao;
	
	@Autowired
	private IJxcContactsAccountDao jxcContactsAccountDao;
	
	@Autowired
	private IGoodsPriceDao goodsPriceDao;
	
	@Autowired
	private IJxcBillDetailDao jxcBillDetailDao;
	
	public JxcBillCheckParams billCheck(Map<String, Object> reqMap){
		//单据审核返回对象
		JxcBillCheckParams checkParams = new JxcBillCheckParams();
		checkParams.setResultFlag(0);
		checkParams.setResultMsg("单据参数有误，请修改后再保存！");
		
		//验证通过返回的单据对象
		JxcPurchaseReceipts jxcPurchaseReceipts = new JxcPurchaseReceipts();
		
		//采购入库单参数map
		Map<String,Object> paraMap = JSONUtil.getMap(reqMap.get(SInvokeKeys.postData.value())+"");
		
		String billNo = StringUtil.objectToString(paraMap.get("billNo"));
		String billCustomNo = StringUtil.objectToString(paraMap.get("billCustomNo"));
		String paymentDate = StringUtil.objectToString(paraMap.get("paymentDate"));
		String receiptsDate = StringUtil.objectToString(paraMap.get("receiptsDate"));
		String supplierId =  StringUtil.objectToString(paraMap.get("supplierId"));
		String storageId = StringUtil.objectToString(paraMap.get("storageId"));
		String brokerId = StringUtil.objectToString(paraMap.get("brokerId"));
		String paymentAccountId = StringUtil.objectToString(paraMap.get("paymentAccountId"));
		String currentPayment =StringUtil.objectToString(paraMap.get("currentPayment"));
		String notes = StringUtil.objectToString(paraMap.get("notes"));
		String reviseMark = StringUtil.objectToString(paraMap.get("reviseMark"));
		
		//单据编号
		jxcPurchaseReceipts.setBillNo(billNo);
		
		//自定义单据编号
		jxcPurchaseReceipts.setBillCustomNo(billCustomNo);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			//付款日期
			if(paymentDate != null && !"".equals(paymentDate)){
				Date paymentTime = sdf.parse(paymentDate);
				jxcPurchaseReceipts.setPaymentDate(paymentTime);
			}

			//入库日期
			if(receiptsDate != null && !"".equals(receiptsDate)){
				Date receiptsTime = sdf.parse(receiptsDate);
				jxcPurchaseReceipts.setReceiptsDate(receiptsTime);
			}
		} catch (ParseException e) {
			System.err.println("字符串 转 日期出错");
			e.printStackTrace();
		}
		
		
		//供应商
		if(supplierId == null || "".equals(supplierId)){
			checkParams.setResultMsg("单据保存失败，原因：供应商ID不能为空！");
			return checkParams;
		}
		Long supplierIdL = Long.valueOf(supplierId);	
		
		JxcContactsUnits supplier = jxcContactsUnitsDao.get(supplierIdL);
		if(supplier == null){
			checkParams.setResultMsg("单据保存失败，原因：供应商信息错误！");
			return checkParams;
		}else{
			jxcPurchaseReceipts.setSupplierId(supplierIdL);
			jxcPurchaseReceipts.setSupplier(supplier.getUnitsName());
		}
		
		
		//入货仓库
		if(storageId == null || "".equals(storageId)){
			checkParams.setResultMsg("单据保存失败，原因：仓库ID不能为空！");
			return checkParams;
		}
		
		Long storageIdL = Long.valueOf(storageId);
		
		Storehouse storehouse = jxcStorehouseDao.get(storageIdL);
		if(storehouse == null){
			checkParams.setResultMsg("单据保存失败，原因：仓库信息错误！");
			return checkParams;
		}else{
			jxcPurchaseReceipts.setStorageId(storageIdL);
			jxcPurchaseReceipts.setStorage(storehouse.getStoreName());
		}
		
		//经手人
		if(brokerId != null && !"".equals(brokerId)){
			Long brokerIdL = Long.valueOf(brokerId);
			
			SysUser broker = jxcSysUserDao.get(brokerIdL);
			if(broker == null){
				checkParams.setResultMsg("单据保存失败，原因：经手人信息错误！");
				return checkParams;
			}else{
				jxcPurchaseReceipts.setBrokerId(brokerIdL);
				jxcPurchaseReceipts.setBroker(broker.getUserName());
			}
		}
		
		//付款账户
		if(paymentAccountId != null && !"".equals(paymentAccountId)){
			Long paymentAccountIdL = Long.valueOf(paymentAccountId);
			
			JxcContactsAccount contactsAccount = jxcContactsAccountDao.get(paymentAccountIdL);
			if(contactsAccount == null){
				checkParams.setResultMsg("单据保存失败，原因：经手人信息错误！");
				return checkParams;
			}else{
				jxcPurchaseReceipts.setPaymentAccountId(paymentAccountIdL);
				jxcPurchaseReceipts.setPaymentAccount(contactsAccount.getAccountName());
			}
		}
		
		//本次付款金额
		if(currentPayment != null && !"".equals(currentPayment)){
			Double currentPaymentD = Double.valueOf(currentPayment);
			jxcPurchaseReceipts.setCurrentPayment(currentPaymentD);
		}
		
		//备注
		jxcPurchaseReceipts.setNotes(notes);
		
		//补单标记
		if(reviseMark == "1"){
			jxcPurchaseReceipts.setReviseMark(1);
		}else{
			jxcPurchaseReceipts.setReviseMark(0);
		}
	
		//整单合计金额
		Double billTotalAmount = 0.00;
		
		/**采购入库单明细处理**/
		//采购入库单据明细对象
		JxcBillDetail billDetail;
		//获取单据明细（商品）列表
		String billGoodsDetailJson = StringUtil.objectToString(paraMap.get("billGoodsList"));
		
		if(billGoodsDetailJson == null || "".equals(billGoodsDetailJson)){
			checkParams.setResultMsg("单据保存失败，原因：商品信息为空！");
			return checkParams;
		}
		
		List<Map<String,Object>> billGoodsList = JSONUtil.jsonToListMap(billGoodsDetailJson);
		if(billGoodsList == null || billGoodsList.isEmpty()){
			checkParams.setResultMsg("单据保存失败，原因：商品明细为空！");
			return checkParams;
		}else{
			for(Map<String,Object> goodsMap : billGoodsList){
				//init 单据明细对象
				billDetail = new JxcBillDetail();
				//商品信息
				Long goodsId = Long.valueOf(goodsMap.get("goodsId")+"");
				GoodsPrice goodsDetail = goodsPriceDao.get(goodsId);
				if(goodsDetail == null){
					checkParams.setResultMsg("单据保存失败，原因：商品不存在！");
					return checkParams;
				}else{
					//单据编号
					billDetail.setBillNo(billNo);
					//仓库信息
					billDetail.setStorehouseId(storageIdL);
					billDetail.setStorehouseName(storehouse.getStoreName());
					//商品信息
					billDetail.setGoodsId(goodsId);
					billDetail.setGoodsNickName(goodsDetail.getGoodsNickName());
					billDetail.setGoodsNumber(goodsDetail.getGoodsNumber());
					
					//商品进价（单价）
					String singlePrice = StringUtil.objectToString(goodsMap.get("singlePrice"));		
					if(singlePrice == null || "".equals(singlePrice)){
						billDetail.setSinglePrice(0.00);	
					}else{
						Double singlePriceD = Double.valueOf(singlePrice);
						billDetail.setSinglePrice(singlePriceD);	
					}
					
					//商品进货数量
					String goodsCount = StringUtil.objectToString(goodsMap.get("goodsCount"));	
					if(goodsCount == null || "".equals(goodsCount)){
						billDetail.setGoodsCount(0);
					}else{
						Integer goodsCountI = Integer.valueOf(goodsCount);
						billDetail.setGoodsCount(goodsCountI);
					}
					
					//该类商品 总价
					Double totalAmount = Arith.mul(billDetail.getSinglePrice(), Double.valueOf(billDetail.getGoodsCount())); 
					billDetail.setTotalAmount(totalAmount);
					
					//明细创建时间
					billDetail.setCreateDate(DateUtil.getSystemTime());
					
					//备注信息
					billDetail.setNotes(goodsMap.get("goodsNotes")+"");
					
					//保存明细
					jxcBillDetailDao.add(billDetail);
					
					//整单总金额累加
					billTotalAmount = Arith.add(billTotalAmount,totalAmount);
				}
			}
		}
		/**单据明细处理完毕**/
		
		//整单合计金额
		jxcPurchaseReceipts.setTotalAmount(billTotalAmount);
		
		//整单折扣
		String discount = StringUtil.objectToString(paraMap.get("discount"));
		if(discount == null || "".equals(discount)){
			jxcPurchaseReceipts.setDiscount(100.00);
		}else{
			Double discountD = Double.valueOf(discount);
			jxcPurchaseReceipts.setDiscount(discountD);
		}
		
		//整单优惠
		
		Double discountAmount = Arith.mul(billTotalAmount, Arith.div(Arith.sub(100d, jxcPurchaseReceipts.getDiscount()), 100d));
		jxcPurchaseReceipts.setDiscountAmount(discountAmount);
		
		//应付金额
		Double payableAmount = Arith.mul(billTotalAmount, Arith.div(jxcPurchaseReceipts.getDiscount(), 100d));
		jxcPurchaseReceipts.setPayableAmount(payableAmount);
		
		//制单人
		String billMakerId = StringUtil.objectToString(paraMap.get("billMakerId"));
		if(billMakerId != null && !"".equals(billMakerId)){
			SysUser billMaker = jxcSysUserDao.get(Long.valueOf(billMakerId));
			jxcPurchaseReceipts.setBillMaker(billMaker.getUserName());
		}
		
		//制单日期
		jxcPurchaseReceipts.setCreateDate(DateUtil.getSystemTime());
		
		//单据校验通过
		checkParams.setResultFlag(1);
		checkParams.setResultMsg("单据校验通过");
		checkParams.setBill(jxcPurchaseReceipts);
		return checkParams;
	}

}
