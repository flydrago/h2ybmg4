package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ITraceProviderGoodsDao;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.TraceProviderGoods;
import com.h2y.util.DateUtil;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 */
@Service("traceProviderGoodsService")
public class TraceProviderGoodsServiceImpl implements ITraceProviderGoodsService{


	@Autowired
	protected ITraceProviderGoodsDao traceProviderGoodsDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param traceProviderGoods
	 *
	 */
	public void add(TraceProviderGoods traceProviderGoods) {
		// TODO Auto-generated method stub

		traceProviderGoodsDao.add(traceProviderGoods);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		traceProviderGoodsDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	traceProviderGoodsDao.deleteByIds(ids);
	//}

	public void update(TraceProviderGoods traceProviderGoods) {
		// TODO Auto-generated method stub
		traceProviderGoodsDao.update(traceProviderGoods);
	}

	public TraceProviderGoods get(long id) {
		// TODO Auto-generated method stub
		return traceProviderGoodsDao.get(id);
	}


	public List<TraceProviderGoods> getList(TraceProviderGoods traceProviderGoods){
		List<TraceProviderGoods> list = traceProviderGoodsDao.getList(traceProviderGoods);
		//
		//traceProviderGoods = null;

		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<TraceProviderGoods> getListPage(Map<String,Object> map){
		//map.put("aaa", new TraceProviderGoods());
		return traceProviderGoodsDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return traceProviderGoodsDao.getRows(map);
	}


	public Map<String, Object> getGridData(HttpServletRequest request) {
		
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String parentId = request.getParameter("parentId");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("parentId", parentId);
				
		List<Map<String,Object>> dataList = traceProviderGoodsDao.getListByPage(map);
		long totalRows = traceProviderGoodsDao.getRows(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		Map<String,Object> gridData = new HashMap<String, Object>();
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}


	public Map<String, Object> getSelectProviderGoodsGridData(HttpServletRequest request) {
		
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
				
		List<Map<String,Object>> dataList = traceProviderGoodsDao.getListByPage(map);
		long totalRows = traceProviderGoodsDao.getRows(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		Map<String,Object> gridData = new HashMap<String, Object>();
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}


	public void add(String[] goodsNames, String[] goodsIds, Long parentId,SysUser sysUser ) {
		int size = goodsNames.length;
		for(int i = 0; i < size; i++){
			TraceProviderGoods traceProviderGoods = new TraceProviderGoods();
			traceProviderGoods.setGoodsId(Long.valueOf(goodsIds[i]));
			traceProviderGoods.setGoodsName(goodsNames[i]);
			traceProviderGoods.setProvideId(parentId);
			traceProviderGoods.setIfEnable(1);
			traceProviderGoods.setOptUserId(sysUser.getId());
			traceProviderGoods.setOptUserAccount(sysUser.getAccount());
			traceProviderGoods.setOptUserName(sysUser.getUserName());
			traceProviderGoods.setStartDate(DateUtil.getSystemTime());
			traceProviderGoods.setEndDate(DateUtil.toDate("999-12-31"));
			traceProviderGoodsDao.add(traceProviderGoods);
		}
	}
}