package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ITransportFeeDao;
import com.h2y.bmg.entity.TransportFee;
import com.mysql.fabric.xmlrpc.base.Array;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-02-02
 * email:info@hwttnet.com
 */
@Service("transportFeeService")
public class TransportFeeServiceImpl implements ITransportFeeService{


	@Autowired
	protected ITransportFeeDao transportFeeDao;

	public void add(TransportFee transportFee) {

		transportFeeDao.add(transportFee);
	}
 


	public void update(TransportFee transportFee) {
		transportFeeDao.update(transportFee);
	}

	public TransportFee get(long id) {
		return transportFeeDao.get(id);
	}


	public Map<String, Object> getGridData(HttpServletRequest request,long unid) {
		Map<String,Object> gridMap = new HashMap<String, Object>();
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		gridMap.put("page", Integer.parseInt(page));
		gridMap.put("pagesize", Integer.parseInt(pagesize));
		gridMap.put("unid", unid);
		List<Map<String, Object>> gridList = transportFeeDao.getGridData(gridMap);
		if(null == gridList){
			gridList = new ArrayList<Map<String,Object>>();
		}
		gridMap.clear();
		gridMap.put("Rows", gridList);
		gridMap.put("Total", transportFeeDao.getGridDataSize(gridMap));
		return gridMap;	
	}


	 
}