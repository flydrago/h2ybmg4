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
import com.h2y.jxc.entity.JxcSalesReturns;
import com.h2y.jxc.entity.Storehouse;
import com.h2y.jxc.entity.SysUser;
import com.h2y.util.Arith;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.StringUtil;

/**
 * 销售退货单 单据校验
 * @author jyd-yfb-02
 */
@Service("salesoutReturnsBillCheck")
public class SaleoutReturnsBillCheck implements IBillCheckService{

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
		JxcSalesReturns returnsBill = new JxcSalesReturns();
		
		//销售退货单参数map
		Map<String,Object> paraMap = JSONUtil.getMap(reqMap.get(SInvokeKeys.postData.value()));
		
		String billNo = StringUtil.objectToString(paraMap.get("billNo"));
		String billCustomNo = StringUtil.objectToString(paraMap.get("billCustomNo"));
		String returnsDate = StringUtil.objectToString(paraMap.get("returnsDate"));
		String refundDate = StringUtil.objectToString(paraMap.get("refundDate"));
		String customerId =  StringUtil.objectToString(paraMap.get("customerId"));
		String storageId = StringUtil.objectToString(paraMap.get("storageId"));
		String brokerId = StringUtil.objectToString(paraMap.get("brokerId"));
		String refundAccountId = StringUtil.objectToString(paraMap.get("refundAccountId"));
		String currentRefund =StringUtil.objectToString(paraMap.get("currentRefund"));
		String notes = StringUtil.objectToString(paraMap.get("notes"));
		String reviseMark = StringUtil.objectToString(paraMap.get("reviseMark"));
		
		//单据编号
		returnsBill.setBillNo(billNo);
		
		//自定义单据编号
		returnsBill.setBillCustomNo(billCustomNo);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			//退款日期
			if(refundDate != null && !"".equals(refundDate)){
				Date refundTime = sdf.parse(refundDate);
				returnsBill.setRefundDate(refundTime);
			}

			//退货日期
			if(returnsDate != null && !"".equals(returnsDate)){
				Date returnsTime = sdf.parse(returnsDate);
				returnsBill.setReturnsDate(returnsTime);
			}
		} catch (ParseException e) {
			System.err.println("字符串 转 日期出错");
			e.printStackTrace();
		}
		
		
		//客户
		if(customerId == null || "".equals(customerId)){
			checkParams.setResultMsg("单据保存失败，原因：客户ID不能为空！");
			return checkParams;
		}
		Long customerIdL = Long.valueOf(customerId);	
		
		JxcContactsUnits customer = jxcContactsUnitsDao.get(customerIdL);
		if(customer == null){
			checkParams.setResultMsg("单据保存失败，原因：客户信息错误！");
			return checkParams;
		}else{
			returnsBill.setCustomerId(customerIdL);
			returnsBill.setCustomer(customer.getUnitsName());
		}
		
		
		//退货仓库
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
			returnsBill.setStorageId(storageIdL);
			returnsBill.setStorage(storehouse.getStoreName());
		}
		
		//经手人
		if(brokerId != null && !"".equals(brokerId)){
			Long brokerIdL = Long.valueOf(brokerId);
			
			SysUser broker = jxcSysUserDao.get(brokerIdL);
			if(broker == null){
				checkParams.setResultMsg("单据保存失败，原因：经手人信息错误！");
				return checkParams;
			}else{
				returnsBill.setBrokerId(brokerIdL);
				returnsBill.setBroker(broker.getUserName());
			}
		}
		
		//收款账户
		if(refundAccountId != null && !"".equals(refundAccountId)){
			Long refundAccountIdL = Long.valueOf(refundAccountId);
			JxcContactsAccount contactsAccount = jxcContactsAccountDao.get(refundAccountIdL);
			if(contactsAccount == null){
				checkParams.setResultMsg("单据保存失败，原因：收款账户信息错误！");
				return checkParams;
			}else{
				returnsBill.setRefundAccountId(refundAccountIdL);
				returnsBill.setRefundAccount(contactsAccount.getAccountName());
			}
		}
		
		//本次收款金额
		if(currentRefund != null && !"".equals(currentRefund)){
			Double currentRefundD = Double.valueOf(currentRefund);
			returnsBill.setCurrentRefund(currentRefundD);
		}
		
		//备注
		returnsBill.setNotes(notes);
		
		//补单标记
		if(reviseMark == "1"){
			returnsBill.setReviseMark(1);
		}else{
			returnsBill.setReviseMark(0);
		}
	
		//整单合计金额
		Double billTotalAmount = 0.00;
		
		/**销售退货单明细处理**/
		//销售退货单据明细对象
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
					
					//退货单价（单价）
					String singlePrice = StringUtil.objectToString(goodsMap.get("singlePrice"));		
					if(singlePrice == null || "".equals(singlePrice)){
						billDetail.setSinglePrice(0.00);	
					}else{
						Double singlePriceD = Double.valueOf(singlePrice);
						billDetail.setSinglePrice(singlePriceD);	
					}
					
					//商品退货数量
					String goodsCount = StringUtil.objectToString(goodsMap.get("goodsCount"));	
					if(goodsCount == null || "".equals(goodsCount)){
						billDetail.setGoodsCount(0);
					}else{
						Integer goodsCountI = Integer.valueOf(goodsCount);
						billDetail.setGoodsCount(goodsCountI);
					}
					
					//该类商品 总额
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
		returnsBill.setTotalAmount(billTotalAmount);
		
		//整单折扣
		String discount = StringUtil.objectToString(paraMap.get("discount"));
		if(discount == null || "".equals(discount)){
			returnsBill.setDiscount(100.00);
		}else{
			Double discountD = Double.valueOf(discount);
			returnsBill.setDiscount(discountD);
		}
		
		//整单优惠
		Double discountAmount = Arith.mul(billTotalAmount, Arith.div(Arith.sub(100d, returnsBill.getDiscount()), 100d));
		returnsBill.setDiscountAmount(discountAmount);
		
		//应收金额
		Double refundableAmount = Arith.mul(billTotalAmount, Arith.div(returnsBill.getDiscount(), 100d));
		returnsBill.setRefundableAmount(refundableAmount);
		
		//制单人
		String billMakerId = StringUtil.objectToString(paraMap.get("billMakerId"));
		if(billMakerId != null && !"".equals(billMakerId)){
			SysUser billMaker = jxcSysUserDao.get(Long.valueOf(billMakerId));
			returnsBill.setBillMaker(billMaker.getUserName());
		}
		
		//制单日期
		returnsBill.setCreateDate(DateUtil.getSystemTime());
		
		//单据校验通过
		checkParams.setResultFlag(1);
		checkParams.setResultMsg("单据校验通过");
		checkParams.setBill(returnsBill);
		return checkParams;
	}

}
