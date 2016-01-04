package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IOrderDao;
import com.h2y.bmg.dao.IOrderFlowDao;
import com.h2y.bmg.dao.IOrderGoodsRtDao;
import com.h2y.bmg.entity.SysUnits;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	protected IOrderDao orderDao;
	
	@Autowired
	protected IOrderFlowDao orderFlowDao;
	
	@Autowired
	protected IOrderGoodsRtDao orderGoodsRtDao;

	public Map<String, Object> getGridData(HttpServletRequest request,SysUnits sysUnits) {
		
		Map<String,Object> gridData = new HashMap<String, Object>();
		
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		if (sortname!=null && !sortname.equals("")) {
			sortname = "o."+sortname.toLowerCase();
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("ifQuery", ifQuery);
		map.put("unitId", sysUnits.getId());
		map.put("unitType", sysUnits.getUnitType());
		map.put("zoneCode", sysUnits.getZoneCode()+"%");
		map.put("page", Integer.parseInt(request.getParameter("page")));
		map.put("pagesize", Integer.parseInt(request.getParameter("pagesize")));
		
		List<Map<String,Object>> dataList = orderDao.getListMap(map);
		long totalRows = orderDao.getListRows(map);
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}

	public Map<String, Object> getGoodsGridData(String orderNo) {
		
		Map<String,Object> gridData = new HashMap<String, Object>();
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		
		if (orderDao.getRowsByOrderNo(orderNo)>0) {
			dataList = orderGoodsRtDao.getListByOrderNo(orderNo);
		}else {
			dataList = orderGoodsRtDao.getHisListByOrderNo(orderNo);
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", dataList.size());
		return gridData;
	}

	public Map<String, Object> getFlowGridData(String orderNo) {
		
		Map<String,Object> gridData = new HashMap<String, Object>();
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		
		if (orderDao.getRowsByOrderNo(orderNo)>0) {
			dataList = orderFlowDao.getListByOrderNo(orderNo);
		}else {
			dataList = orderFlowDao.getHisListByOrderNo(orderNo);
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", dataList.size());
		return gridData;
	}

	public long getWaitOrderRows(long unitId) {
		
		return orderDao.getWaitOrderRows(unitId);
	}
	
}
