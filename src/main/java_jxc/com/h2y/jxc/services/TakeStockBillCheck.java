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
import com.h2y.jxc.dao.IJxcCheckbillDetailDao;
import com.h2y.jxc.dao.IJxcStorehouseDao;
import com.h2y.jxc.dao.IJxcSysUserDao;
import com.h2y.jxc.entity.JxcBillCheckParams;
import com.h2y.jxc.entity.JxcCheck;
import com.h2y.jxc.entity.JxcCheckbillDetail;
import com.h2y.jxc.entity.Storehouse;
import com.h2y.jxc.entity.SysUser;
import com.h2y.util.Arith;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.StringUtil;

@Service("takeStockBillCheck")
public class TakeStockBillCheck implements IBillCheckService{

	@Autowired
	private IJxcStorehouseDao jxcStorehouseDao;
	
	@Autowired
	private IJxcSysUserDao jxcSysUserDao;
	
	@Autowired
	private IGoodsPriceDao goodsPriceDao;
	
	@Autowired
	private IJxcCheckbillDetailDao jxcCheckbillDetailDao;
	
	/**
	 * 仓库盘点单   单据校验
	 */
	public JxcBillCheckParams billCheck(Map<String, Object> reqMap) {
		//单据审核返回对象
				JxcBillCheckParams checkParams = new JxcBillCheckParams();
				checkParams.setResultFlag(0);
				checkParams.setResultMsg("单据参数有误，请修改后再保存！");
				
				//验证通过返回的单据对象
				JxcCheck takeStockBill = new JxcCheck();
				
				//仓库盘点单 参数map
				Map<String,Object> paraMap = JSONUtil.getMap(reqMap.get(SInvokeKeys.postData.value())+"");
				
				String billNo = StringUtil.objectToString(paraMap.get("billNo"));
				String billCustomNo = StringUtil.objectToString(paraMap.get("billCustomNo"));
				String takeStockDate = StringUtil.objectToString(paraMap.get("takeStockDate"));
				String storageId = StringUtil.objectToString(paraMap.get("storageId"));
				String brokerId = StringUtil.objectToString(paraMap.get("brokerId"));
				String notes = StringUtil.objectToString(paraMap.get("notes"));
				
				//单据编号
				takeStockBill.setBillNo(billNo);
				
				//自定义单据编号
				takeStockBill.setBillCustomNo(billCustomNo);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				try {
					//盘点日期
					if(takeStockDate != null && !"".equals(takeStockDate)){
						Date takeStockTime = sdf.parse(takeStockDate);
						takeStockBill.setCheckDate(takeStockTime);
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
					takeStockBill.setStorageId(storageIdL);
					takeStockBill.setStorage(storehouse.getStoreName());
				}
				
				//经手人
				if(brokerId != null && !"".equals(brokerId)){
					Long brokerIdL = Long.valueOf(brokerId);
					SysUser broker = jxcSysUserDao.get(brokerIdL);
					if(broker == null){
						checkParams.setResultMsg("单据保存失败，原因：经手人信息错误！");
						return checkParams;
					}else{
						takeStockBill.setBrokerId(brokerIdL);
						takeStockBill.setBroker(broker.getUserName());
					}
				}
				
				//备注
				takeStockBill.setNotes(notes);
				
				//整单合计库存数量
				Integer billInventoryCount = 0;
				//整单合计盘点数量
				Integer billTakeStockCount = 0;
				//整单合计盈亏数量
				Integer billBreakevenCount = 0;
				//整单合计盈亏金额
				Double billBreakevenAmount = 0.00;
				
				/**单据商品明细处理**/
				//单据 明细对象
				JxcCheckbillDetail billDetail;
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
						billDetail = new JxcCheckbillDetail();
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
								billDetail.setCostSinglePrice(0.00);	
							}else{
								Double costSinglePriceD = Double.valueOf(costSinglePrice);
								billDetail.setCostSinglePrice(costSinglePriceD);	
							}
							
							//商品库存数量
							String goodsInventoryCount = StringUtil.objectToString(goodsMap.get("goodsInventoryCount"));	
							if(goodsInventoryCount == null || "".equals(goodsInventoryCount)){
								billDetail.setInventoryCount(0);
								billInventoryCount += 0;		//整单库存数量累加
							}else{
								Integer goodsInventoryCountI = Integer.valueOf(goodsInventoryCount);
								billDetail.setInventoryCount(goodsInventoryCountI);
								billInventoryCount += goodsInventoryCountI;		//整单库存数量累加
							}
							
							//商品盘点数量
							String goodsTakeStockCount = StringUtil.objectToString(goodsMap.get("goodsTakeStockCount"));	
							if(goodsTakeStockCount == null || "".equals(goodsTakeStockCount)){
								billDetail.setCheckCount(0);
								billTakeStockCount += 0;		//整单盘点数量累加
							}else{
								Integer goodsTakeStockCountI = Integer.valueOf(goodsTakeStockCount);
								billDetail.setCheckCount(goodsTakeStockCountI);
								billTakeStockCount += goodsTakeStockCountI; 		//整单盘点数量累加
							}
							
							//商品盈亏数量
							Integer goodsBreakevenCount = billDetail.getCheckCount() - billDetail.getInventoryCount();
							billDetail.setBreakevenCount(goodsBreakevenCount);
							billBreakevenCount += goodsBreakevenCount;				//整单盈亏数量累加
							
							//商品盈亏总额
							Double goodsBreakevenAmount = Arith.mul(billDetail.getCostSinglePrice(), Double.valueOf(goodsBreakevenCount));
							billDetail.setBreakevenAmount(goodsBreakevenAmount);
							billBreakevenAmount += goodsBreakevenAmount;			//整单盈亏金额累加
							
							//明细创建时间
							billDetail.setCreateDate(DateUtil.getSystemTime());
							
							//备注信息
							billDetail.setNotes(goodsMap.get("goodsNotes")+"");
							
							//保存明细
							jxcCheckbillDetailDao.add(billDetail);
						}
					}
				}
				/**单据明细处理完毕**/
				
				//整单库存数量
				takeStockBill.setStockCount(billInventoryCount);
				//整单盘点数量
				takeStockBill.setCheckCount(billTakeStockCount);
				//整单盈亏数量
				takeStockBill.setBreakevenCount(billBreakevenCount);
				//整单盈亏金额
				takeStockBill.setBreakevenAmount(billBreakevenAmount);
				
				//制单人
				String billMakerId = StringUtil.objectToString(paraMap.get("billMakerId"));
				if(billMakerId != null && !"".equals(billMakerId)){
					SysUser billMaker = jxcSysUserDao.get(Long.valueOf(billMakerId));
					takeStockBill.setBillMaker(billMaker.getUserName());
				}
				
				//制单日期
				takeStockBill.setCreateDate(DateUtil.getSystemTime());
				
				//单据校验通过
				checkParams.setResultFlag(1);
				checkParams.setResultMsg("单据校验通过");
				checkParams.setBill(takeStockBill);
				return checkParams;
	}

}
