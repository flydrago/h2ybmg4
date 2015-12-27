package com.h2y.jxc.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h2y.jxc.basic.JxcKeys.StorageKeys;
import com.h2y.jxc.dao.IJxcStorehouseDao;
import com.h2y.jxc.dao.IJxcStorehouseGoodsDetailDao;
import com.h2y.jxc.dao.IJxcStorehouseGoodsInfoDao;
import com.h2y.jxc.entity.JxcAllocationbillDetail;
import com.h2y.jxc.entity.JxcBillDetail;
import com.h2y.jxc.entity.JxcProfitandlossDetail;
import com.h2y.jxc.entity.Storehouse;
import com.h2y.jxc.entity.StorehouseGoodsInfo;
import com.h2y.util.DateUtil;

/**
  * 仓库 Service Impl
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */
@Service("jxcStorehouseService")
public class JxcStorehouseServiceImpl implements IJxcStorehouseService{


	@Autowired
	protected IJxcStorehouseDao storehouseDao;
	
	@Autowired
	protected IJxcStorehouseGoodsInfoDao storehouseGoodsInfoDao;
	
	@Autowired
	protected IJxcStorehouseGoodsDetailDao storehouseGoodsDetailDao;
	
	/**
	 * @param storehouse
	 */
	public void add(Storehouse storehouse) {
		storehouseDao.add(storehouse);
	}

	public void delete(long id) {
		storehouseDao.deleteById(id);
	}

	public void update(Storehouse storehouse) {
		storehouseDao.update(storehouse);
	}

	public Storehouse get(long id) {
		return storehouseDao.get(id);
	}

	public List<Storehouse> getList(Storehouse storehouse){
		List<Storehouse> list = storehouseDao.getList(storehouse);
		return list;
	}

	/**
	 * getListPage
	 * @return
	 */
	public List<Storehouse> getListPage(Map<String,Object> map){
		return storehouseDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){
		return storehouseDao.getRows(map);
	}

	/**
	 * 分页查询仓库信息 （供添加单据时选择用）
	 */
	public Map<String, Object> getSelectGridData(HttpServletRequest request) {
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
		List<Map<String, Object>> dataList = storehouseDao.getListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridMap.put("Rows", dataList);
		gridMap.put("Total", storehouseDao.getListRows(map));
		
		return gridMap;
	}

	/**
	 * 将单据中的商品信息转化成 仓库 库存的变更
	 */
	@Transactional(rollbackFor=Exception.class)
	public Map<String,Object> receiptsTransStorage(Map<String,Object> paraMap) {
		//结果Map
		Map<String,Object> resultMap = new HashMap<String, Object>();	
		resultMap.put("resultFlag", 0);
		
		//请求参数
		@SuppressWarnings("unchecked")
		List<JxcBillDetail> billGoodsList = (List<JxcBillDetail>) paraMap.get("billGoodsList");
		Long storageId = Long.valueOf(paraMap.get("storageId")+"");
		Long brokerId = Long.valueOf(paraMap.get("brokerId")+"");
		String storeOp = paraMap.get("storeOp")+"";
		
		
		//需更新的库存信息列表及日志信息列表（防止出现库存已更新，但单据却审核不通过问题）
		List<StorehouseGoodsInfo> storeGoodsInfoList = new ArrayList<StorehouseGoodsInfo>();
		List<Map<String,Object>> logMapList = new ArrayList<Map<String,Object>>();
		
		//根据 仓库ID - 商品ID 更新库存信息 并  记录库存变更日志
		for(JxcBillDetail billDetail : billGoodsList){
			//参数Map
			Map<String,Object> argsMap = new HashMap<String, Object>();	//查询商品库存信息map
			Map<String,Object> logsMap = new HashMap<String, Object>();	//新增 商品库存变更日志 map

			argsMap.put("storageId", storageId);
			argsMap.put("goodsId", billDetail.getGoodsId());
			// 该商品库存信息（库存更新前）
			StorehouseGoodsInfo shGoodsInfo =  storehouseGoodsInfoDao.getGoodsInfo(argsMap);
			
			int previousCount = 0;	//该商品 原库存量
			int laterCount = 0;	//该商品 最新库存总量（加上单据数量）
			
			if("0".equals(storeOp)){		//入库操作
				if(shGoodsInfo == null){	//如果 商品库存信息 不存在
					shGoodsInfo = new StorehouseGoodsInfo();
					laterCount = billDetail.getGoodsCount();
					
					shGoodsInfo.setStorehouseId(storageId);
					shGoodsInfo.setGoodsPriceId(billDetail.getGoodsId());
					shGoodsInfo.setGoodsCount(laterCount);
				}else{
					previousCount = shGoodsInfo.getGoodsCount();
					laterCount = previousCount+billDetail.getGoodsCount();
					
					shGoodsInfo.setGoodsCount(laterCount);
				}
			}else if("1".equals(storeOp)){		//出库操作
				if(shGoodsInfo == null){	//如果商品库存信息  不存在
					resultMap.put("resultMsg", "单据未能通过审核，原因：商品库存信息不存在，无法出库");
					return resultMap;
				}else{	//如果商品库存信息 存在
					previousCount = shGoodsInfo.getGoodsCount();
					laterCount = previousCount - billDetail.getGoodsCount();
					
					if(laterCount < 0){		//该商品库存不足  无法出库
						resultMap.put("resultMsg", "单据未能通过审核，原因：商品库存不足，无法出库。【"+billDetail.getGoodsNickName()+"】目前存量："+previousCount);
						return resultMap;
					}else{	//如果库存充足 ， 设置出库后的库存
						shGoodsInfo.setGoodsCount(laterCount);
					}
				}
			}
			
			//更新商品库存信息
			//库存信息变更添加到 库存信息列表，统一更新
			//storehouseGoodsInfoDao.update(shGoodsInfo);
			storeGoodsInfoList.add(shGoodsInfo);
			
			//记录商品库存 变动日志
			logsMap.put("storehouseId", storageId);	//仓库ID
			logsMap.put("goodsPriceId", billDetail.getGoodsId());	//商品ID
			logsMap.put("goodsCount", billDetail.getGoodsCount());	//商品数目
			logsMap.put("type", StorageKeys.exStorage.value());		//库存变更类型（0：入库 1：出库）
			logsMap.put("status", 0);	//仓库商品状态（0：正常 -1：作废）
			logsMap.put("createDate",DateUtil.getSystemTime());
			logsMap.put("createUserId",brokerId);
			logsMap.put("billNo", billDetail.getBillNo());
			logsMap.put("billStatus", 1);
			logsMap.put("previousCount", previousCount);
			logsMap.put("afterCount", laterCount);
			logsMap.put("memo", billDetail.getNotes());
			
			//日志添加到日志列表，统一更新
			//storehouseGoodsDetailDao.addDetail(logsMap);
			logMapList.add(logsMap);
		}
		//遍历 商品库存信息，更新
		for (StorehouseGoodsInfo tmpInfo : storeGoodsInfoList) {
			if(tmpInfo.getId() == 0){	//如果商品库存信息不存在，则添加库存信息
				storehouseGoodsInfoDao.add(tmpInfo);
			}else{	//如果商品库存信息存在，则更新其库存信息
				storehouseGoodsInfoDao.update(tmpInfo);
			}
		}
		//记录日志
		for (Map<String, Object> map : logMapList) {
			storehouseGoodsDetailDao.addDetail(map);
		}
		
		resultMap.put("resultFlag", 1);
		resultMap.put("resultMsg", "单据审核通过，且已转化库存");
		return resultMap;
	}


	/**
	 * 仓库调拨单  单据转化库存
	 */
	@Transactional(rollbackFor=Exception.class)
	public Map<String, Object> allocationTransStorage(Map<String, Object> paraMap) {
		//结果Map
		Map<String,Object> resultMap = new HashMap<String, Object>();	
		resultMap.put("resultFlag", 0);
				
		//请求参数
		@SuppressWarnings("unchecked")
		List<JxcAllocationbillDetail> billGoodsList = (List<JxcAllocationbillDetail>) paraMap.get("billGoodsList");
		Long exStorageId = Long.valueOf(paraMap.get("exStorageId")+"");
		Long inStorageId = Long.valueOf(paraMap.get("inStorageId")+"");
		Long brokerId = Long.valueOf(paraMap.get("brokerId")+"");
		
		
		//需更新的库存信息列表及日志信息列表（防止出现库存已更新，但单据却审核不通过问题）
		List<StorehouseGoodsInfo> storeGoodsInfoList = new ArrayList<StorehouseGoodsInfo>();
		List<Map<String,Object>> logMapList = new ArrayList<Map<String,Object>>();
		
		//根据 仓库ID - 商品ID 更新库存信息 并  记录库存变更日志
		for(JxcAllocationbillDetail billDetail : billGoodsList){
			/**调出仓库  商品业务处理**/
			//参数Map
			Map<String,Object> argsMap = new HashMap<String, Object>();	//查询商品库存信息map

			argsMap.put("storageId", exStorageId);
			argsMap.put("goodsId", billDetail.getGoodsId());
			// 该商品库存信息（调出仓库 库存更新前）
			StorehouseGoodsInfo exShGoodsInfo =  storehouseGoodsInfoDao.getGoodsInfo(argsMap);
			
			int exPreviousCount = 0;	//调出仓库 该商品 原库存量
			int exLaterCount = 0;	//调出仓库 该商品 最新库存总量（加上单据数量）

			if(exShGoodsInfo == null){	//如果商品库存信息  不存在
				resultMap.put("resultMsg", "单据未能通过审核，原因：商品库存信息不存在，无法进行调拨。");
				return resultMap;
			}else{	//如果商品库存信息 存在
				exPreviousCount = exShGoodsInfo.getGoodsCount();
				exLaterCount = exPreviousCount - billDetail.getGoodsCount();
						
				if(exLaterCount < 0){		//该商品库存不足  无法出库
					resultMap.put("resultMsg", "单据未能通过审核，原因：商品库存不足，无法进行调拨。【"+billDetail.getGoodsNickname()+"】目前存量："+exPreviousCount);
					return resultMap;
				}else{	//如果库存充足 ， 设置出库后的库存
					exShGoodsInfo.setGoodsCount(exLaterCount);
				}
			}
			
			/**调入仓库  商品业务处理**/
			argsMap.clear();
			argsMap.put("storageId", inStorageId);
			argsMap.put("goodsId", billDetail.getGoodsId());
			
			int inLaterCount = 0;		//调入仓库 商品 原库存量
			int inPreviousCount = 0;		//调入仓库  库存变更后 商品库存量
			
			//该商品的库存信息 （调入仓库  库存更新前）
			StorehouseGoodsInfo inShGoodsInfo = storehouseGoodsInfoDao.getGoodsInfo(argsMap);
			if(inShGoodsInfo == null){		//如果调入仓库中  未包含此商品信息
				inShGoodsInfo = new StorehouseGoodsInfo();
				inLaterCount = billDetail.getGoodsCount();
				inShGoodsInfo.setStorehouseId(inStorageId);
				inShGoodsInfo.setGoodsPriceId(billDetail.getGoodsId());
				inShGoodsInfo.setGoodsCount(inLaterCount);
			}else{		//如果调入仓库中  包含此商品信息
				inPreviousCount = inShGoodsInfo.getGoodsCount(); 
				inLaterCount = inPreviousCount + billDetail.getGoodsCount();
				inShGoodsInfo.setGoodsCount(inLaterCount);
			}
			
			//更新调出仓库 商品库存信息
			//storehouseGoodsInfoDao.update(exShGoodsInfo);
			storeGoodsInfoList.add(exShGoodsInfo);
			//更新调入仓库  商品库存信息
			//storehouseGoodsInfoDao.update(inShGoodsInfo);
			storeGoodsInfoList.add(inShGoodsInfo);

			
			//记录商品库存 变动日志 （调出仓库）
			Map<String,Object> exLogsMap = new HashMap<String, Object>();	//记录 商品库存变更日志 map

			exLogsMap.put("storehouseId", exStorageId);	//仓库ID
			exLogsMap.put("goodsPriceId", billDetail.getGoodsId());	//商品ID
			exLogsMap.put("goodsCount", billDetail.getGoodsCount());	//商品数目
			exLogsMap.put("type", StorageKeys.exStorage.value());		//库存变更类型（0：入库 1：出库）
			exLogsMap.put("status", 0);	//仓库商品状态（0：正常 -1：作废）
			exLogsMap.put("createDate",DateUtil.getSystemTime());
			exLogsMap.put("createUserId",brokerId);
			exLogsMap.put("billNo", billDetail.getBillNo());
			exLogsMap.put("billStatus", 1);
			exLogsMap.put("previousCount", exPreviousCount);
			exLogsMap.put("afterCount", exLaterCount);
			exLogsMap.put("memo", billDetail.getNotes());
			
			//storehouseGoodsDetailDao.addDetail(logsMap);
			logMapList.add(exLogsMap);
			
			//记录商品库存 变动日志 （调入仓库）
			Map<String,Object> inLogsMap = new HashMap<String, Object>();	//记录 商品库存变更日志 map

			inLogsMap.put("storehouseId", inStorageId);	//仓库ID
			inLogsMap.put("goodsPriceId", billDetail.getGoodsId());	//商品ID
			inLogsMap.put("goodsCount", billDetail.getGoodsCount());	//商品数目
			inLogsMap.put("type", StorageKeys.inStorage.value());		//库存变更类型（0：入库 1：出库）
			inLogsMap.put("status", 0);	//仓库商品状态（0：正常 -1：作废）
			inLogsMap.put("createDate",DateUtil.getSystemTime());
			inLogsMap.put("createUserId",brokerId);
			inLogsMap.put("billNo", billDetail.getBillNo());
			inLogsMap.put("billStatus", 1);
			inLogsMap.put("previousCount", inPreviousCount);
			inLogsMap.put("afterCount", inLaterCount);
			inLogsMap.put("memo", billDetail.getNotes());
			
			//storehouseGoodsDetailDao.addDetail(logsMap);
			logMapList.add(inLogsMap);
		}

		//遍历 商品库存信息，更新
		for (StorehouseGoodsInfo tmpInfo : storeGoodsInfoList) {
			if(tmpInfo.getId() == 0){	//如果库存信息不存在，则添加
				storehouseGoodsInfoDao.add(tmpInfo);
			}else{	//若库存信息存在，则更新
				storehouseGoodsInfoDao.update(tmpInfo);
			}
		}
		
		//记录日志
		for (Map<String, Object> map : logMapList) {
			storehouseGoodsDetailDao.addDetail(map);
		}
		
		resultMap.put("resultFlag", 1);
		resultMap.put("resultMsg", "单据审核通过，且已转化库存");
		return resultMap;
	}
	
	/**
	 * 报损单&报溢单 转化库存变更
	 */
	@Transactional(rollbackFor=Exception.class)
	public Map<String,Object> profitAndLossTransStorage(Map<String,Object> paraMap){
				//结果Map
				Map<String,Object> resultMap = new HashMap<String, Object>();	
				resultMap.put("resultFlag", 0);
				
				//请求参数
				@SuppressWarnings("unchecked")
				List<JxcProfitandlossDetail> billGoodsList = (List<JxcProfitandlossDetail>) paraMap.get("billGoodsList");
				Long storageId = Long.valueOf(paraMap.get("storageId")+"");
				Long brokerId = Long.valueOf(paraMap.get("brokerId")+"");
				String storeOp = paraMap.get("storeOp")+"";	//库存变更操作类型
				
				
				//需更新的库存信息列表及日志信息列表（防止出现库存已更新，但单据却审核不通过问题）
				List<StorehouseGoodsInfo> storeGoodsInfoList = new ArrayList<StorehouseGoodsInfo>();
				List<Map<String,Object>> logMapList = new ArrayList<Map<String,Object>>();
				
				//根据 仓库ID - 商品ID 更新库存信息 并  记录库存变更日志
				for(JxcProfitandlossDetail billDetail : billGoodsList){
					//参数Map
					Map<String,Object> argsMap = new HashMap<String, Object>();	//查询商品库存信息map
					Map<String,Object> logsMap = new HashMap<String, Object>();	//新增 商品库存变更日志 map

					argsMap.put("storageId", storageId);
					argsMap.put("goodsId", billDetail.getGoodsId());
					// 该商品库存信息（库存更新前）
					StorehouseGoodsInfo shGoodsInfo =  storehouseGoodsInfoDao.getGoodsInfo(argsMap);
					
					int previousCount = 0;	//该商品 原库存量
					int laterCount = 0;	//该商品 最新库存总量（加上单据数量）
					
					if("2".equals(storeOp)){		//报溢单 库存增加
						if(shGoodsInfo == null){	//如果 商品库存信息 不存在
							shGoodsInfo = new StorehouseGoodsInfo();
							laterCount = billDetail.getPlCount();
							
							shGoodsInfo.setStorehouseId(storageId);
							shGoodsInfo.setGoodsPriceId(billDetail.getGoodsId());
							shGoodsInfo.setGoodsCount(laterCount);
						}else{	//如果 商品库存信息 存在
							previousCount = shGoodsInfo.getGoodsCount();
							laterCount = previousCount+billDetail.getPlCount();
							shGoodsInfo.setGoodsCount(laterCount);
						}
					}else if("3".equals(storeOp)){		//报损单  库存减少
						if(shGoodsInfo == null){	//如果商品库存信息  不存在
							resultMap.put("resultMsg", "单据未能通过审核，原因：报损商品库存信息不存在");
							return resultMap;
						}else{	//如果商品库存信息 存在
							previousCount = shGoodsInfo.getGoodsCount();
							laterCount = previousCount - billDetail.getPlCount();
							
							if(laterCount < 0){		//该商品库存不足  无法进行报损
								resultMap.put("resultMsg", "单据未能通过审核，原因：报损数量超过商品库存数量。【"+billDetail.getGoodsNickname()+"】目前存量："+previousCount);
								return resultMap;
							}else{	//如果库存充足 ， 设置报损后的库存
								shGoodsInfo.setGoodsCount(laterCount);
							}
						}
					}
					
					//更新商品库存信息
					//storehouseGoodsInfoDao.update(shGoodsInfo);
					storeGoodsInfoList.add(shGoodsInfo);
					
					//记录商品库存 变动日志
					logsMap.put("storehouseId", storageId);	//仓库ID
					logsMap.put("goodsPriceId", billDetail.getGoodsId());	//商品ID
					logsMap.put("goodsCount", billDetail.getPlCount());	//商品数目
					logsMap.put("type", StorageKeys.overflow.value());		//库存变更类型（0：入库 1：出库 2：报溢  3： 报损）
					logsMap.put("status", 0);	//仓库商品状态（0：正常 -1：作废）
					logsMap.put("createDate",DateUtil.getSystemTime());
					logsMap.put("createUserId",brokerId);
					logsMap.put("billNo", billDetail.getBillNo());
					logsMap.put("billStatus", 1);
					logsMap.put("previousCount", previousCount);
					logsMap.put("afterCount", laterCount);
					logsMap.put("memo", billDetail.getNotes());
					
					//storehouseGoodsDetailDao.addDetail(logsMap);
					logMapList.add(logsMap);
					
				}
				
				//遍历 商品库存信息，更新
				for (StorehouseGoodsInfo tmpInfo : storeGoodsInfoList) {
					if(tmpInfo.getId() == 0){	//新的库存信息  新增
						storehouseGoodsInfoDao.add(tmpInfo);
					}else{	//已有库存信息  更新
						storehouseGoodsInfoDao.update(tmpInfo);
					}
				}
				//记录日志
				for (Map<String, Object> map : logMapList) {
					storehouseGoodsDetailDao.addDetail(map);
				}
				
				resultMap.put("resultFlag", 1);
				resultMap.put("resultMsg", "单据审核通过，且已转化库存");
				return resultMap;
	}
}