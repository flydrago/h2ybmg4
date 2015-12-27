package com.h2y.bmg.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IOrderCountDao;
import com.h2y.bmg.dao.IOrderCountInfoDao;
import com.h2y.bmg.dao.IOrderDao;
import com.h2y.bmg.entity.Order;
import com.h2y.bmg.entity.OrderCountInfo;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.util.Arith;
import com.h2y.util.DateUtil;
import com.h2y.util.StringUtil;

/**
 * 项目名称：h2ybmg2  
 * 类名称：OrderCountServiceImpl  
 * 类描述：  订单汇总业务接口实现类
 * 创建人：侯飞龙  
 * 创建时间：2015年8月7日 下午2:07:59  
 * 修改人：侯飞龙
 * 修改时间：2015年8月7日 下午2:07:59  
 * 修改备注：  
 * @version
 */
@Service("orderCountService")
public class OrderCountServiceImpl implements IOrderCountService {
	
	@Autowired
	protected IOrderCountDao orderCountDao;
	
	@Autowired
	protected IOrderCountInfoDao orderCountInfoDao;
	
	@Autowired
	protected IOrderDao orderDao;

	public Map<String, Object> getGridData(HttpServletRequest request,SysUnits sysUnits) {
		
		Map<String,Object> gridData = new HashMap<String, Object>();
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("unitId", sysUnits.getId());
		map.put("unitType", sysUnits.getUnitType());
		map.put("zoneCode", sysUnits.getZoneCode()+"%");
		
		String startDay_str = request.getParameter("startDay");
		String endDay_str = request.getParameter("endDay");
		String unitName = request.getParameter("unitName");
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		if (null!=startDay_str && !startDay_str.equals("") && null!=endDay_str && !endDay_str.equals("")) {
			
			map.put("startDay", DateUtil.toDate(startDay_str, DateUtil.YYYY_MM_DD));
			map.put("endDay", DateUtil.toDate(endDay_str, DateUtil.YYYY_MM_DD));
			if (null!=unitName && !"".equals(unitName)) {
				
				map.put("unitName", "%"+unitName+"%");
			}
			dataList = orderCountDao.getListMap(map);
		}
		
		if (null!=dataList && !dataList.isEmpty()) {
			
			for (Map<String, Object> countData : dataList) {
				
				List<OrderCountInfo> infoList = orderCountInfoDao.getListByCountId(Long.valueOf(countData.get("ID")+""));
				
				countData.put("AVG_AMOUNT", Arith.div(null==countData.get("GOODS_AMOUNT")?0d:Double.valueOf(countData.get("GOODS_AMOUNT")+""), null==countData.get("ORDER_COUNT")?0d:Double.valueOf(countData.get("ORDER_COUNT")+"")));
				if (null!=infoList && !infoList.isEmpty()) {
					
					for (OrderCountInfo orderCountInfo : infoList) {
						
						int infoType = orderCountInfo.getInfoType();
						countData.put("ORDER_COUNT_"+infoType, orderCountInfo.getOrderCount());
						countData.put("REAL_AMOUNT_"+infoType, orderCountInfo.getRealAmount());
						countData.put("GOODS_AMOUNT_"+infoType, orderCountInfo.getGoodsAmount());
						countData.put("COIN_AMOUNT_"+infoType, orderCountInfo.getCoinAmount());
						countData.put("GOODS_TRANSPORT_AMOUNT_"+infoType, orderCountInfo.getGoodsTransportAmount());
						countData.put("COUPONS_AMOUNT_"+infoType, orderCountInfo.getCouponsAmount());
						countData.put("AVG_AMOUNT_"+infoType, Arith.div(orderCountInfo.getGoodsAmount(), Double.valueOf(orderCountInfo.getOrderCount())));
					}
				}
			}
		}
		long totalRows = null==dataList?0:dataList.size();
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}

	public Map<String, Object> getTodayGridData(HttpServletRequest request,
			SysUnits sysUnits) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("unitId", sysUnits.getId());
		
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		List<Order> orderList = orderDao.getTodayCountList(map);
		
		List<OrderCountInfo> dataList = new ArrayList<OrderCountInfo>();
		
		////汇总方式 0：有效订单、1：在线支付、2：线下支付、3：电话外卖、4：拒收订单
		OrderCountInfo orderCountInfo = new OrderCountInfo();//总订单
		orderCountInfo.setInfoType(-1);
		
		OrderCountInfo valiCountInfo = new OrderCountInfo();//有效订单
		valiCountInfo.setInfoType(0);
		
		OrderCountInfo jushouCountInfo = new OrderCountInfo();//拒收订单
		jushouCountInfo.setInfoType(4);
		
		OrderCountInfo onlineCountInfo = new OrderCountInfo();//线上订单
		onlineCountInfo.setInfoType(1);
		
		OrderCountInfo offlineCountInfo = new OrderCountInfo();//线下订单
		offlineCountInfo.setInfoType(2);
		
		if (null!=orderList && !orderList.isEmpty()) {
				
			for (Order order : orderList) {
				
				setOrderCountInfo(orderCountInfo, order);
				if (order.getDeliveryerStatus() == 12) {//有效订单
					
					setOrderCountInfo(valiCountInfo, order);
				}else if (order.getDeliveryerStatus() == -11) {//拒收订单
					
					setOrderCountInfo(jushouCountInfo, order);
				}
				
				if (order.getPayType() == 0) {//线下支付
					
					setOrderCountInfo(offlineCountInfo, order);
				}else if (order.getPayType() == 1) {
					
					setOrderCountInfo(onlineCountInfo, order);
				}
			}	
		}
		
		dataList.add(orderCountInfo);
		dataList.add(valiCountInfo);
		dataList.add(jushouCountInfo);
		dataList.add(onlineCountInfo);
		dataList.add(offlineCountInfo);
		
		Map<String,Object> countData = new HashMap<String, Object>();
		countData.put("Rows", dataList);
		countData.put("Total", null!=dataList?dataList.size():0);
		return countData;
	}
	
	
	private void setOrderCountInfo(OrderCountInfo orderCountInfo,Order order){
		
		orderCountInfo.setOrderCount(orderCountInfo.getOrderCount()+1);
		orderCountInfo.setCoinAmount(Arith.add(order.getCoinAmount(), orderCountInfo.getCoinAmount()));
		orderCountInfo.setCouponsAmount(Arith.add(order.getCouponsAmount(), orderCountInfo.getCouponsAmount()));
		orderCountInfo.setGoodsAmount(Arith.add(order.getGoodsAmount(), orderCountInfo.getGoodsAmount()));
		orderCountInfo.setGoodsTransportAmount(Arith.add(order.getGoodsTransportAmount(), orderCountInfo.getGoodsTransportAmount()));
		orderCountInfo.setRealAmount(Arith.add(order.getRealAmount(), orderCountInfo.getRealAmount()));
	}

	/**
	 * 获取订单分析数据
	 */
	public Map<String, Object> getAnalysesData(HttpServletRequest request,SysUnits loginUnit) {
		
		//结果集
		Map<String,Object> gridData = new HashMap<String, Object>();
		
		//订单分析数据总集
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		
		//分析订单的 条件
		String longitude = request.getParameter("longitude").trim();	//经度
		String latitude = request.getParameter("latitude").trim();		//纬度
		String radius = request.getParameter("radius").trim();			//范围半径
		
		//时间条件
		Date today = DateUtil.getToday();					//今天 时间
		Date tomorrow = DateUtil.getTomorrow(today);		//明天 时间
		Date lastWeek = DateUtil.getLastWeek(today);		//上一周 时刻
		Date lastMonth = DateUtil.getLastMonth(today);		//上一月 时刻
		Date lastQuarter = DateUtil.getLastQuarter(today);	//前三月 时刻
		Date halfYear = DateUtil.getHalfYear(today);		//半年之前 时刻
		Date nineMonthEarlier = DateUtil.getNineMonthEarlier(today);	//九个月之前 时刻
		Date lastYear = DateUtil.getLastYear(today);		//一年之前 时刻
		
		//单位信息条件
		String unitName = request.getParameter("unitName");
		if(unitName != null && !"".equals(unitName)){
			unitName = "%"+unitName+"%";
		}
		long unitId = loginUnit.getId();
		String zoneCode = loginUnit.getZoneCode()+"%";
		int unitType = loginUnit.getUnitType();
		
		
		Map<String,Object> paraMap = new HashMap<String, Object>();
		paraMap.put("longitude", longitude);
		paraMap.put("latitude", latitude);
		paraMap.put("radius", radius);
		
		paraMap.put("unitName", unitName);
		paraMap.put("unitId", unitId);
		paraMap.put("zoneCode", zoneCode);
		paraMap.put("unitType", unitType);
		
		paraMap.put("endDate", tomorrow);
		
		//当天的订单分析数据
		paraMap.put("startDate", today);
		List<Map<String,Object>> todayData = analysesData(paraMap, "今天");
		if(!todayData.isEmpty()){
			dataList.addAll(todayData);
		}
		
		//一周的订单分析数据
		paraMap.put("startDate", lastWeek);
		List<Map<String,Object>> weekData = analysesData(paraMap, "一周之内");
		if(!weekData.isEmpty()){
			dataList.addAll(weekData);
		}
		
		
		//一个月的订单分析数据
		paraMap.put("startDate", lastMonth);
		List<Map<String,Object>> monthData = analysesData(paraMap, "一个月之内");
		if(!monthData.isEmpty()){
			dataList.addAll(monthData);
		}
		
		
		//三个月的订单分析数据
		paraMap.put("startDate", lastQuarter);
		List<Map<String,Object>> quarterData = analysesData(paraMap, "三个月之内");
		if(!quarterData.isEmpty()){
			dataList.addAll(quarterData);
		}
		
		//半年之内的订单分析数据
		paraMap.put("startDate", halfYear);
		List<Map<String,Object>> halfYearData = analysesData(paraMap, "半年之内");
		if(!halfYearData.isEmpty()){
			dataList.addAll(halfYearData);
		}

		//九个月之内的订单分析数据
		paraMap.put("startDate", nineMonthEarlier);
		List<Map<String,Object>> nineMonthData = analysesData(paraMap, "九个月之内");
		if(!nineMonthData.isEmpty()){
			dataList.addAll(nineMonthData);
		}

		//一年之内的订单分析数据
		paraMap.put("startDate", lastYear);
		List<Map<String,Object>> oneYearData = analysesData(paraMap, "一年之内");
		if(!oneYearData.isEmpty()){
			dataList.addAll(oneYearData);
		}

		int rowsCount = dataList.isEmpty()?0:dataList.size();
		
		gridData.put("Rows", dataList);
		gridData.put("Total", rowsCount);
		
		return gridData;
	}
	
	private List<Map<String,Object>> analysesData(Map<String,Object> paraMap,String orderDay){
		//结果List
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		
		/**查询符合条件的 单位**/
		List<Map<String,Object>> unitInfoList = orderDao.getOrderAnalysesUnitInfoList(paraMap);
		
		for(Map<String,Object> tmpInfo : unitInfoList){
			String shortName = StringUtil.objectToString(tmpInfo.get("shortName"));		//单位简称
			Object idObj = tmpInfo.get("unitID");
			Long unitID = Long.valueOf( idObj == null ? "0":StringUtil.objectToString(idObj));	//单位ID
			
			paraMap.put("unitIdOption", unitID);	//根据单位查询各单位的订单
			
			/**查询符合条件的订单数据**/
			List<Order> orderList = orderDao.getOrderAnalysesData(paraMap);

			
			/**分析订单数据**/
			if(!orderList.isEmpty()){
				//分析数据详情
				
				//所有订单
				int orderCount = orderList.size();	//所有订单量
				double goodsAmount = 0.0;		//商品金额
				double avgAmount = 0.0;		//客单价（商品金额/订单总数）
				double realAmount = 0.0;		//实付金额总数
				double coinAmount = 0.0;			//达人币总数
				double goodsTransportAmount = 0.0;	//订单总运费
				double couponsAmount = 0.0;			//优惠券总额
				//有效订单
				int orderCount0 = 0;	//所有订单量
				double goodsAmount0 = 0.0;		//商品金额
				double avgAmount0 = 0.0;		//客单价（商品金额/订单总数）
				double realAmount0 = 0.0;		//实付金额总数
				double coinAmount0 = 0.0;			//达人币总数
				double goodsTransportAmount0 = 0.0;	//订单总运费
				double couponsAmount0 = 0.0;			//优惠券总额
				//拒收订单
				int orderCount4 = 0;	//所有订单量
				double goodsAmount4 = 0.0;		//商品金额
				double avgAmount4 = 0.0;		//客单价（商品金额/订单总数）
				double realAmount4 = 0.0;		//实付金额总数
				double coinAmount4 = 0.0;			//达人币总数
				double goodsTransportAmount4 = 0.0;	//订单总运费
				double couponsAmount4 = 0.0;			//优惠券总额
				//线下订单
				int orderCount2 = 0;	//所有订单量
				double goodsAmount2 = 0.0;		//商品金额
				double avgAmount2 = 0.0;		//客单价（商品金额/订单总数）
				double realAmount2 = 0.0;		//实付金额总数
				double coinAmount2 = 0.0;			//达人币总数
				double goodsTransportAmount2 = 0.0;	//订单总运费
				double couponsAmount2 = 0.0;			//优惠券总额
				//线上订单
				int orderCount1 = 0;	//所有订单量
				double goodsAmount1 = 0.0;		//商品金额
				double avgAmount1 = 0.0;		//客单价（商品金额/订单总数）
				double realAmount1 = 0.0;		//实付金额总数
				double coinAmount1 = 0.0;			//达人币总数
				double goodsTransportAmount1 = 0.0;	//订单总运费
				double couponsAmount1 = 0.0;			//优惠券总额
				
				for(Order tmpOrder : orderList){
					
					//所有订单
					goodsAmount = Arith.add(goodsAmount, tmpOrder.getGoodsAmount());
					realAmount = Arith.add(realAmount, tmpOrder.getRealAmount());
					coinAmount = Arith.add(coinAmount, tmpOrder.getVipCoin());
					goodsTransportAmount = Arith.add(goodsTransportAmount, tmpOrder.getGoodsTransportAmount());
					couponsAmount = Arith.add(couponsAmount, tmpOrder.getCouponsAmount());
					
					//有效订单
					if(tmpOrder.getDeliveryerStatus() == 12){
						
						orderCount0++;
						goodsAmount0 = Arith.add(goodsAmount0, tmpOrder.getGoodsAmount());
						realAmount0 = Arith.add(realAmount0, tmpOrder.getRealAmount());
						coinAmount0 = Arith.add(coinAmount0, tmpOrder.getVipCoin());
						goodsTransportAmount0 = Arith.add(goodsTransportAmount0, tmpOrder.getGoodsTransportAmount());
						couponsAmount0 = Arith.add(couponsAmount0, tmpOrder.getCouponsAmount());
						
					}
					
					//拒收订单
					if(tmpOrder.getDeliveryerStatus() == -11){
						
						orderCount4++;
						goodsAmount4 = Arith.add(goodsAmount4, tmpOrder.getGoodsAmount());
						realAmount4 = Arith.add(realAmount4, tmpOrder.getRealAmount());
						coinAmount4 = Arith.add(coinAmount4, tmpOrder.getVipCoin());
						goodsTransportAmount4 = Arith.add(goodsTransportAmount4, tmpOrder.getGoodsTransportAmount());
						couponsAmount4 = Arith.add(couponsAmount4, tmpOrder.getCouponsAmount());
						
					}
					
					//线下订单
					if(tmpOrder.getPayType() == 0){
						
						orderCount2++;
						goodsAmount2 = Arith.add(goodsAmount2, tmpOrder.getGoodsAmount());
						realAmount2 = Arith.add(realAmount2, tmpOrder.getRealAmount());
						coinAmount2 = Arith.add(coinAmount2, tmpOrder.getVipCoin());
						goodsTransportAmount2 = Arith.add(goodsTransportAmount2, tmpOrder.getGoodsTransportAmount());
						couponsAmount2 = Arith.add(couponsAmount2, tmpOrder.getCouponsAmount());
						
					}
					
					//线上订单
					if(tmpOrder.getPayType() == 1){
						
						orderCount1++;
						goodsAmount1 = Arith.add(goodsAmount1, tmpOrder.getGoodsAmount());
						realAmount1 = Arith.add(realAmount1, tmpOrder.getRealAmount());
						coinAmount1 = Arith.add(coinAmount1, tmpOrder.getVipCoin());
						goodsTransportAmount1 = Arith.add(goodsTransportAmount1, tmpOrder.getGoodsTransportAmount());
						couponsAmount1 = Arith.add(couponsAmount1, tmpOrder.getCouponsAmount());
						
					}
				}
				
				if(orderCount != 0){
					avgAmount = Arith.div(goodsAmount, orderCount+0.0, 2);
				}else {
					avgAmount = 0;
				}
				if(orderCount0 != 0){
					avgAmount0 = Arith.div(goodsAmount0, orderCount0+0.0, 2);
				}else{
					avgAmount0 = 0;
				}
				if(orderCount4 !=0){
					avgAmount4 = Arith.div(goodsAmount4, orderCount4+0.0, 2);
				}else{
					avgAmount4 = 0;
				}
				if(orderCount2 !=0){
					avgAmount2 = Arith.div(goodsAmount2, orderCount2+0.0, 2);
				}else{
					avgAmount2 = 0;
				}
				if(orderCount1 !=0){
					avgAmount1 = Arith.div(goodsAmount1, orderCount1+0.0, 2);
				}else{
					avgAmount1 = 0;
				}
				
				Map<String,Object> analysisData = new HashMap<String, Object>();
				
				//基本数据
				analysisData.put("ORDER_DAY", orderDay);		//数据统计的时间
				analysisData.put("UNIT_SHORT_NAME", shortName);	//单位简称
				//所有订单
				analysisData.put("ORDER_COUNT", orderCount);	//所有订单量
				analysisData.put("GOODS_AMOUNT", goodsAmount);	//商品总金额
				analysisData.put("REAL_AMOUNT", realAmount);	//实付总金额
				analysisData.put("COIN_AMOUNT", coinAmount);	//达人币总量
				analysisData.put("GOODS_TRANSPORT_AMOUNT", goodsTransportAmount);	//总配送费
				analysisData.put("COUPONS_AMOUNT", couponsAmount);	//优惠券总额
				analysisData.put("AVG_AMOUNT", avgAmount);	//客单价
				//有效订单
				analysisData.put("ORDER_COUNT_0", orderCount0);	//所有订单量
				analysisData.put("GOODS_AMOUNT_0", goodsAmount0);	//商品总金额
				analysisData.put("REAL_AMOUNT_0", realAmount0);	//实付总金额
				analysisData.put("COIN_AMOUNT_0", coinAmount0);	//达人币总量
				analysisData.put("GOODS_TRANSPORT_AMOUNT_0", goodsTransportAmount0);	//总配送费
				analysisData.put("COUPONS_AMOUNT_0", couponsAmount0);	//优惠券总额
				analysisData.put("AVG_AMOUNT_0", avgAmount0);	//客单价
				//拒收订单
				analysisData.put("ORDER_COUNT_4", orderCount4);	//所有订单量
				analysisData.put("GOODS_AMOUNT_4", goodsAmount4);	//商品总金额
				analysisData.put("REAL_AMOUNT_4", realAmount4);	//实付总金额
				analysisData.put("COIN_AMOUNT_4", coinAmount4);	//达人币总量
				analysisData.put("GOODS_TRANSPORT_AMOUNT_4", goodsTransportAmount4);	//总配送费
				analysisData.put("COUPONS_AMOUNT_4", couponsAmount4);	//优惠券总额
				analysisData.put("AVG_AMOUNT_4", avgAmount4);	//客单价
				//线下订单
				analysisData.put("ORDER_COUNT_2", orderCount2);	//所有订单量
				analysisData.put("GOODS_AMOUNT_2", goodsAmount2);	//商品总金额
				analysisData.put("REAL_AMOUNT_2", realAmount2);	//实付总金额
				analysisData.put("COIN_AMOUNT_2", coinAmount2);	//达人币总量
				analysisData.put("GOODS_TRANSPORT_AMOUNT_2", goodsTransportAmount2);	//总配送费
				analysisData.put("COUPONS_AMOUNT_2", couponsAmount2);	//优惠券总额
				analysisData.put("AVG_AMOUNT_2", avgAmount2);	//客单价
				//线上订单
				analysisData.put("ORDER_COUNT_1", orderCount1);	//所有订单量
				analysisData.put("GOODS_AMOUNT_1", goodsAmount1);	//商品总金额
				analysisData.put("REAL_AMOUNT_1", realAmount1);	//实付总金额
				analysisData.put("COIN_AMOUNT_1", coinAmount1);	//达人币总量
				analysisData.put("GOODS_TRANSPORT_AMOUNT_1", goodsTransportAmount1);	//总配送费
				analysisData.put("COUPONS_AMOUNT_1", couponsAmount1);	//优惠券总额
				analysisData.put("AVG_AMOUNT_1", avgAmount1);	//客单价
				
				resultList.add(analysisData);
			}
			
		}
		
		
		return resultList;
	} 
}

