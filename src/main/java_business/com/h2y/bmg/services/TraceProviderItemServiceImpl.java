package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ITraceProviderItemDao;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.TraceProviderItem;
import com.h2y.util.DateUtil;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 */
@Service("traceProviderItemService")
public class TraceProviderItemServiceImpl implements ITraceProviderItemService{


	@Autowired
	protected ITraceProviderItemDao traceProviderItemDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param traceProviderItem
	 *
	 */
	public void add(TraceProviderItem traceProviderItem) {
		// TODO Auto-generated method stub

		traceProviderItemDao.add(traceProviderItem);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		traceProviderItemDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	traceProviderItemDao.deleteByIds(ids);
	//}

	public void update(TraceProviderItem traceProviderItem) {
		// TODO Auto-generated method stub
		traceProviderItemDao.update(traceProviderItem);
	}

	public TraceProviderItem get(long id) {
		// TODO Auto-generated method stub
		return traceProviderItemDao.get(id);
	}


	public List<TraceProviderItem> getList(TraceProviderItem traceProviderItem){
		List<TraceProviderItem> list = traceProviderItemDao.getList(traceProviderItem);
		//
		//traceProviderItem = null;

		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<TraceProviderItem> getListPage(Map<String,Object> map){
		//map.put("aaa", new TraceProviderItem());
		return traceProviderItemDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return traceProviderItemDao.getRows(map);
	}


	public Map<String, Object> getGridData(HttpServletRequest request) {
		
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		Integer ifEnable = request.getParameter("ifEnable") == null ? null : Integer.parseInt(request.getParameter("ifEnable"));
		Long parentId = request.getParameter("parentId") == null ? null : Long.valueOf(request.getParameter("parentId"));
		String ifQuery = request.getParameter("ifQuery");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("ifEnable", ifEnable);
		map.put("parentId", parentId);
		map.put("ifQuery", ifQuery);
		
		List<Map<String,Object>> dataList = traceProviderItemDao.getListByPage(map);
		long totalRows = traceProviderItemDao.getRows(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		Map<String,Object> gridData = new HashMap<String, Object>();
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}


	public void save(HttpServletRequest request, String op, SysUser sysUser,SysUnits sysUnits,
			TraceProviderItem traceProviderItem) {
		
		String userAccount = sysUser.getAccount();
		String userName = sysUser.getUserName();
		Long userId = sysUser.getId();
		
		if(op.equals("add")){
			Long unitId = sysUnits.getId();
			String zoneCode = sysUnits.getZoneCode();
			String unitShortName = sysUnits.getShortName();
			
			traceProviderItem.setZoneCode(zoneCode);
			traceProviderItem.setParentId(unitId);
			traceProviderItem.setData1(unitShortName);
			traceProviderItem.setOptUserId(userId);
			traceProviderItem.setOptUserAccount(userAccount);
			traceProviderItem.setOptUserName(userName);
			traceProviderItem.setCreateDate(DateUtil.getSystemTime());
			traceProviderItem.setIfEnable(1);
			traceProviderItemDao.add(traceProviderItem);
		}else{
			
			TraceProviderItem traceProviderItem2 = traceProviderItemDao.get(traceProviderItem.getId());
			traceProviderItem.setZoneCode(traceProviderItem2.getZoneCode());
			traceProviderItem.setParentId(traceProviderItem2.getParentId());
			traceProviderItem.setData1(traceProviderItem2.getData1());
			traceProviderItem.setOptUserId(userId);
			traceProviderItem.setOptUserAccount(userAccount);
			traceProviderItem.setOptUserName(userName);
			traceProviderItem.setCreateDate(traceProviderItem2.getCreateDate());
			traceProviderItem.setUpdateDate(DateUtil.getSystemTime());
			traceProviderItem.setIfEnable(traceProviderItem2.getIfEnable());
			traceProviderItem.setIfEnable(traceProviderItem2.getIfEnable());
			traceProviderItemDao.update(traceProviderItem);
		}
	}


	public void updateStatusByIds(Map<String, Object> dataMap) {
		traceProviderItemDao.updateStatusByIds(dataMap);
	}
}