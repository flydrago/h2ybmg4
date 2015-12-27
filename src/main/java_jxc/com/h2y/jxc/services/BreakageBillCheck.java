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
import com.h2y.jxc.dao.IJxcProfitandlossDetailDao;
import com.h2y.jxc.dao.IJxcStorehouseDao;
import com.h2y.jxc.dao.IJxcSysUserDao;
import com.h2y.jxc.entity.JxcBillCheckParams;
import com.h2y.jxc.entity.JxcBreakage;
import com.h2y.jxc.entity.JxcProfitandlossDetail;
import com.h2y.jxc.entity.Storehouse;
import com.h2y.jxc.entity.SysUser;
import com.h2y.util.Arith;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.StringUtil;

/**
 *	报损单 单据校验 
 * @author jyd-yfb-02
 */
@Service("breakageBillCheck")
public class BreakageBillCheck implements IBillCheckService{

	@Autowired
	private IJxcStorehouseDao jxcStorehouseDao;
	
	@Autowired
	private IJxcSysUserDao jxcSysUserDao;
	
	@Autowired
	private IGoodsPriceDao goodsPriceDao;
	
	@Autowired
	private IJxcProfitandlossDetailDao jxcProfitandlossDetailDao;
	
	public JxcBillCheckParams billCheck(Map<String, Object> reqMap) {
			//单据审核返回对象
			JxcBillCheckParams checkParams = new JxcBillCheckParams();
			checkParams.setResultFlag(0);
			checkParams.setResultMsg("单据参数有误，请修改后再保存！");
			
			//验证通过返回的单据对象
			JxcBreakage breakageBill = new JxcBreakage();
			
			//仓库盘点单 参数map
			Map<String,Object> paraMap = JSONUtil.getMap(reqMap.get(SInvokeKeys.postData.value())+"");
			
			String billNo = StringUtil.objectToString(paraMap.get("billNo"));
			String billCustomNo = StringUtil.objectToString(paraMap.get("billCustomNo"));
			String breakageDate = StringUtil.objectToString(paraMap.get("breakageDate"));
			String storageId = StringUtil.objectToString(paraMap.get("storageId"));
			String brokerId = StringUtil.objectToString(paraMap.get("brokerId"));
			String notes = StringUtil.objectToString(paraMap.get("notes"));
			String excursus = StringUtil.objectToString(paraMap.get("excursus"));
			String reviseMark = StringUtil.objectToString(paraMap.get("reviseMark"));
			
			//单据编号
			breakageBill.setBillNo(billNo);
			
			//自定义单据编号
			breakageBill.setBillCustomNo(billCustomNo);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				//盘点日期
				if(breakageDate != null && !"".equals(breakageDate)){
					Date breakageTime = sdf.parse(breakageDate);
					breakageBill.setBreakageDate(breakageTime);
				}
			} catch (ParseException e) {
				System.err.println("字符串 转 日期出错");
				e.printStackTrace();
			}
			
			//盘点仓库
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
				breakageBill.setStorageId(storageIdL);
				breakageBill.setStorage(storehouse.getStoreName());
			}
			
			//经手人
			if(brokerId != null && !"".equals(brokerId)){
				Long brokerIdL = Long.valueOf(brokerId);
				SysUser broker = jxcSysUserDao.get(brokerIdL);
				if(broker == null){
					checkParams.setResultMsg("单据保存失败，原因：经手人信息错误！");
					return checkParams;
				}else{
					breakageBill.setBrokerId(brokerIdL);
					breakageBill.setBroker(broker.getUserName());
				}
			}
			
			//备注
			breakageBill.setNotes(notes);
			//附记
			breakageBill.setExcursus(excursus);
			//补单标记
			if(reviseMark == "1"){
				breakageBill.setReviseMark(1);
			}else{
				breakageBill.setReviseMark(0);
			}
			
			//整单合计报损数量
			Integer billBreakageCount = 0;
			//整单合计报损金额
			Double billBreakageAmount = 0.00;
			
			/**单据商品明细处理**/
			//单据 明细对象
			JxcProfitandlossDetail billDetail;
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
					billDetail = new JxcProfitandlossDetail();
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
						billDetail.setStorageId(storageIdL);
						billDetail.setStorage(storehouse.getStoreName());
						//商品信息
						billDetail.setGoodsId(goodsId);
						billDetail.setGoodsNickname(goodsDetail.getGoodsNickName());
						billDetail.setGoodsNumber(goodsDetail.getGoodsNumber());
						
						//商品成本单价
						String costSinglePrice = StringUtil.objectToString(goodsMap.get("costSinglePrice"));		
						if(costSinglePrice == null || "".equals(costSinglePrice)){
							billDetail.setGoodsCostPrice(0.00);	
						}else{
							Double costSinglePriceD = Double.valueOf(costSinglePrice);
							billDetail.setGoodsCostPrice(costSinglePriceD);	
						}
						
						//商品报损数量
						String goodsBreakageCount = StringUtil.objectToString(goodsMap.get("goodsBreakageCount"));	
						if(goodsBreakageCount == null || "".equals(goodsBreakageCount)){
							billDetail.setPlCount(0);
							billBreakageCount += 0;		//整单库存数量累加
						}else{
							Integer goodsBreakageCountI = Integer.valueOf(goodsBreakageCount);
							billDetail.setPlCount(goodsBreakageCountI);
							billBreakageCount += goodsBreakageCountI;		//整单报损数量累加
						}
						
						//商品报损总额
						Double goodsBreakageAmount = Arith.mul(billDetail.getGoodsCostPrice(), Double.valueOf(billDetail.getPlCount()));
						billDetail.setPlAmount(goodsBreakageAmount);
						billBreakageAmount += goodsBreakageAmount;			//整单报损金额累加
						
						//商品零售单价
						String retailSinglePrice = StringUtil.objectToString(goodsMap.get("retailSinglePrice"));
						if(retailSinglePrice == null || "".equals(retailSinglePrice)){
							billDetail.setGoodsRetailPrice(0.00);
						}else {
							Double retailSinglePriceD = Double.valueOf(retailSinglePrice);
							billDetail.setGoodsRetailPrice(retailSinglePriceD);
						}
						
						//商品零售总额
						Double goodsRetailAmount = Arith.mul(billDetail.getGoodsRetailPrice(), Double.valueOf(billDetail.getPlCount()));
						billDetail.setRetailAmount(goodsRetailAmount);
						
						//商品是否为赠品
						String goodsGiftMark = StringUtil.objectToString(goodsMap.get("goodsGiftMark"));
						if("1".equals(goodsGiftMark)){
							billDetail.setGiftMark(1);
						}else{
							billDetail.setGiftMark(0);
						}
						
						//明细创建时间
						billDetail.setCreateDate(DateUtil.getSystemTime());
						
						//备注信息
						billDetail.setNotes(goodsMap.get("goodsNotes")+"");
						
						//保存明细
						jxcProfitandlossDetailDao.add(billDetail);
					}
				}
			}
			/**单据明细处理完毕**/
			
			//整单库存数量
			breakageBill.setBreakageCount(billBreakageCount);
			//整单盘点数量
			breakageBill.setBreakageAmount(billBreakageAmount);
			
			//制单人
			String billMakerId = StringUtil.objectToString(paraMap.get("billMakerId"));
			if(billMakerId != null && !"".equals(billMakerId)){
				SysUser billMaker = jxcSysUserDao.get(Long.valueOf(billMakerId));
				breakageBill.setBillMaker(billMaker.getUserName());
			}
			
			//制单日期
			breakageBill.setCreateDate(DateUtil.getSystemTime());
			
			//单据校验通过
			checkParams.setResultFlag(1);
			checkParams.setResultMsg("单据校验通过");
			checkParams.setBill(breakageBill);
			
			return checkParams;
	}

}
