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
import com.h2y.jxc.dao.IJxcAllocationbillDetailDao;
import com.h2y.jxc.dao.IJxcStorehouseDao;
import com.h2y.jxc.dao.IJxcSysUserDao;
import com.h2y.jxc.entity.JxcAllocation;
import com.h2y.jxc.entity.JxcAllocationbillDetail;
import com.h2y.jxc.entity.JxcBillCheckParams;
import com.h2y.jxc.entity.Storehouse;
import com.h2y.jxc.entity.SysUser;
import com.h2y.util.Arith;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.StringUtil;

/**
 * 仓储调拨单  单据校验
 * @author jyd-yfb-02
 *
 */
@Service("allocationBillCheck")
public class AllocationBillCheck implements IBillCheckService{

	@Autowired
	private IJxcStorehouseDao jxcStorehouseDao;
	
	@Autowired
	private IJxcSysUserDao jxcSysUserDao;
	
	@Autowired
	private IGoodsPriceDao goodsPriceDao;
	
	@Autowired
	private IJxcAllocationbillDetailDao jxcAllocationbillDetailDao;
	
		public JxcBillCheckParams billCheck(Map<String, Object> reqMap) {
				//单据审核返回对象
				JxcBillCheckParams checkParams = new JxcBillCheckParams();
				checkParams.setResultFlag(0);
				checkParams.setResultMsg("单据参数有误，请修改后再保存！");
				
				//验证通过返回的单据对象
				JxcAllocation allocateBill = new JxcAllocation();
				
				//调拨单参数map
				Map<String,Object> paraMap = JSONUtil.getMap(reqMap.get(SInvokeKeys.postData.value())+"");
				
				String billNo = StringUtil.objectToString(paraMap.get("billNo"));
				String billCustomNo = StringUtil.objectToString(paraMap.get("billCustomNo"));
				
				String exStorageId = StringUtil.objectToString(paraMap.get("exStorageId"));
				String inStorageId = StringUtil.objectToString(paraMap.get("inStorageId"));
				String brokerId = StringUtil.objectToString(paraMap.get("brokerId"));

				String allocateDate = StringUtil.objectToString(paraMap.get("allocateDate"));
				String notes = StringUtil.objectToString(paraMap.get("notes"));
				String reviseMark = StringUtil.objectToString(paraMap.get("reviseMark"));
				
				//单据编号
				allocateBill.setBillNo(billNo);
				
				//自定义单据编号
				allocateBill.setBillCustomNo(billCustomNo);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				try {
					//调拨日期
					if(allocateDate != null && !"".equals(allocateDate)){
						Date allocateTime = sdf.parse(allocateDate);
						allocateBill.setAllocateDate(allocateTime);
					}
				} catch (ParseException e) {
					System.err.println("字符串 转 日期出错");
					e.printStackTrace();
				}
				
				//调出仓库
				if(exStorageId == null || "".equals(exStorageId)){
					checkParams.setResultMsg("单据保存失败，原因：调出仓库ID不能为空！");
					return checkParams;
				}
				
				Long exStorageIdL = Long.valueOf(exStorageId);
				
				Storehouse exStorehouse = jxcStorehouseDao.get(exStorageIdL);
				if(exStorehouse == null){
					checkParams.setResultMsg("单据保存失败，原因：调出仓库信息错误！");
					return checkParams;
				}else{
					allocateBill.setExportStorageId(exStorageIdL);
					allocateBill.setStorageExport(exStorehouse.getStoreName());
				}
				
				//调入仓库
				if(inStorageId == null || "".equals(inStorageId)){
					checkParams.setResultMsg("单据保存失败，原因：调入仓库ID不能为空！");
					return checkParams;
				}
				
				Long inStorageIdL = Long.valueOf(inStorageId);
				
				Storehouse inStorehouse = jxcStorehouseDao.get(inStorageIdL);
				if(inStorehouse == null){
					checkParams.setResultMsg("单据保存失败，原因：调入仓库信息错误！");
					return checkParams;
				}else{
					allocateBill.setImportStorageId(inStorageIdL);
					allocateBill.setStorageImport(inStorehouse.getStoreName());
				}
				
				//经手人
				if(brokerId != null && !"".equals(brokerId)){
					Long brokerIdL = Long.valueOf(brokerId);
					
					SysUser broker = jxcSysUserDao.get(brokerIdL);
					if(broker == null){
						checkParams.setResultMsg("单据保存失败，原因：经手人信息错误！");
						return checkParams;
					}else{
						allocateBill.setBrokerId(brokerIdL);
						allocateBill.setBroker(broker.getUserName());
					}
				}
				
				//备注
				allocateBill.setNotes(notes);
				
				//补单标记
				if(reviseMark == "1"){
					allocateBill.setReviseMark(1);
				}else{
					allocateBill.setReviseMark(0);
				}
			
				//整单合计金额
				Double billTotalAmount = 0.00;
				
				//整单合计差额
				Double billTotalDifference = 0.00;
				
				/**调拨单明细处理**/
				//调拨单据 明细对象
				JxcAllocationbillDetail billDetail;
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
						billDetail = new JxcAllocationbillDetail();
						//商品信息
						Long goodsId = Long.valueOf(goodsMap.get("goodsId")+"");
						GoodsPrice goodsDetail = goodsPriceDao.get(goodsId);
						if(goodsDetail == null){
							checkParams.setResultMsg("单据保存失败，原因：商品不存在！");
							return checkParams;
						}else{
							//单据编号
							billDetail.setBillNo(billNo);

							//调出仓库信息
							billDetail.setExportStorageId(exStorageIdL);
							billDetail.setExportStorage(exStorehouse.getStoreName());
							
							//调入仓库信息
							billDetail.setImportStorageId(inStorageIdL);
							billDetail.setImportStorage(inStorehouse.getStoreName());
							
							//商品信息
							billDetail.setGoodsId(goodsId);
							billDetail.setGoodsNickname(goodsDetail.getGoodsNickName());
							billDetail.setGoodsNumber(goodsDetail.getGoodsNumber());
							
							//商品 成本原价
							String costSinglePrice = StringUtil.objectToString(goodsMap.get("costSinglePrice"));		
							if(costSinglePrice == null || "".equals(costSinglePrice)){
								billDetail.setCostSinglePrice(0.00);	
							}else{
								Double singlePriceD = Double.valueOf(costSinglePrice);
								billDetail.setCostSinglePrice(singlePriceD);	
							}
							
							//商品 调拨单价
							String allocateSinglePrice = StringUtil.objectToString(goodsMap.get("allocateSinglePrice"));
							if(allocateSinglePrice == null || "".equals(allocateSinglePrice)){
								billDetail.setCostSinglePrice(0.00);	
							}else{
								Double allocateSinglePriceD = Double.valueOf(allocateSinglePrice);
								billDetail.setAllocateSinglePrice(allocateSinglePriceD);	
							}
							
							//商品数量
							String goodsCount = StringUtil.objectToString(goodsMap.get("goodsCount"));	
							if(goodsCount == null || "".equals(goodsCount)){
								billDetail.setGoodsCount(0);
							}else{
								Integer goodsCountI = Integer.valueOf(goodsCount);
								billDetail.setGoodsCount(goodsCountI);
							}
							
							//该类商品 总差额
							Double totalDifference = Arith.mul(Arith.sub(billDetail.getAllocateSinglePrice(), billDetail.getCostSinglePrice()), Double.valueOf(billDetail.getGoodsCount()));
							billDetail.setTotalDifference(totalDifference);
							
							//该类商品 总额
							Double totalAmount = Arith.mul(billDetail.getAllocateSinglePrice(),Double.valueOf(billDetail.getGoodsCount())); 
							billDetail.setTotalAmount(totalAmount);
							
							//明细创建时间
							billDetail.setCreateDate(DateUtil.getSystemTime());
							
							//备注信息
							billDetail.setNotes(goodsMap.get("goodsNotes")+"");
							
							//保存明细
							jxcAllocationbillDetailDao.add(billDetail);
							
							//整单总金额累加
							billTotalAmount = Arith.add(billTotalAmount,totalAmount);
							
							//整单总差额累加
							billTotalDifference = Arith.add(billTotalDifference, totalDifference);
						}
					}
				}
				/**单据明细处理完毕**/
				
				//整单合计金额
				allocateBill.setTotalAmount(billTotalAmount);
				
				//整单 合计差额
				allocateBill.setTotalDifference(billTotalDifference);
				
				//制单人
				String billMakerId = StringUtil.objectToString(paraMap.get("billMakerId"));
				if(billMakerId != null && !"".equals(billMakerId)){
					SysUser billMaker = jxcSysUserDao.get(Long.valueOf(billMakerId));
					allocateBill.setBillMaker(billMaker.getUserName());
				}
				
				//制单日期
				allocateBill.setCreateDate(DateUtil.getSystemTime());
				
				//单据校验通过
				checkParams.setResultFlag(1);
				checkParams.setResultMsg("单据校验通过");
				checkParams.setBill(allocateBill);
				return checkParams;
		}

}
