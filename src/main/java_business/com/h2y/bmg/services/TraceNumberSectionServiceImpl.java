package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ITraceNumberSectionDao;
import com.h2y.bmg.dao.ITraceQrcodeSerialDao;
import com.h2y.bmg.entity.SysDictDetail;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.TraceNumberSection;
import com.h2y.bmg.entity.TraceQrcodeSerial;
import com.h2y.bmg.util.SysBaseUtil.DictClumn;
import com.h2y.bmg.util.SysBaseUtil.DictOrderBy;
import com.h2y.dict.ComparatorSysDictDetail;
import com.h2y.dict.DictUtil;
import com.h2y.util.DateUtil;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-06-30
 * email:info@hwttnet.com
 */
@Service("traceNumberSectionService")
public class TraceNumberSectionServiceImpl implements ITraceNumberSectionService{


	@Autowired
	protected ITraceNumberSectionDao traceNumberSectionDao;
	
	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param traceNumberSection
	 *
	 */
	public void add(TraceNumberSection traceNumberSection) {
		// TODO Auto-generated method stub

		traceNumberSectionDao.add(traceNumberSection);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		traceNumberSectionDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	traceNumberSectionDao.deleteByIds(ids);
	//}

	public void update(TraceNumberSection traceNumberSection) {
		// TODO Auto-generated method stub
		traceNumberSectionDao.update(traceNumberSection);
	}

	public TraceNumberSection get(Long id) {
		// TODO Auto-generated method stub
		return traceNumberSectionDao.get(id);
	}


	public List<TraceNumberSection> getList(TraceNumberSection traceNumberSection){
		List<TraceNumberSection> list = traceNumberSectionDao.getList(traceNumberSection);
		//
		//traceNumberSection = null;

		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<TraceNumberSection> getListPage(Map<String,Object> map){
		//map.put("aaa", new TraceNumberSection());
		return traceNumberSectionDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return traceNumberSectionDao.getRows(map);
	}


	public Map<String,Object> getGridData(HttpServletRequest request) {
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		Integer ifReceive = request.getParameter("ifReceive") == null ? null : Integer.parseInt(request.getParameter("ifReceive"));
		Integer ifEnable = request.getParameter("ifEnable") == null ? null : Integer.parseInt(request.getParameter("ifEnable"));
		Integer ifCreate = request.getParameter("ifCreate") == null ? null : Integer.parseInt(request.getParameter("ifCreate"));
		Long parentId = request.getParameter("parentId") == null || request.getParameter("parentId") == ""? null : Long.valueOf(request.getParameter("parentId"));
		Long toId = request.getParameter("toId") == null  || request.getParameter("toId") == "" ? null : Long.valueOf(request.getParameter("toId"));
		Integer spec = request.getParameter("spec") == null || request.getParameter("spec") == ""? null : Integer.valueOf(request.getParameter("spec"));
		String ifQuery = request.getParameter("ifQuery");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("ifReceive", ifReceive);
		map.put("ifEnable", ifEnable);
		map.put("ifCreate", ifCreate);
		map.put("parentId", parentId);
		map.put("toId", toId);
		map.put("spec", spec);
		map.put("ifQuery", ifQuery);
		
		List<Map<String,Object>> dataList = traceNumberSectionDao.getListByPage(map);
		long totalRows = traceNumberSectionDao.getRows(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		Map<String,Object> gridData = new HashMap<String, Object>();
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}

	public List<TraceNumberSection> getListData(HttpServletRequest request) {
		
		Integer ifReceive = request.getParameter("ifReceive") == null ? null : Integer.parseInt(request.getParameter("ifReceive"));
		Integer ifEnable = request.getParameter("ifEnable") == null ? null : Integer.parseInt(request.getParameter("ifEnable"));
		Integer ifCreate = request.getParameter("ifCreate") == null ? null : Integer.parseInt(request.getParameter("ifCreate"));
		Long parentId = request.getParameter("parentId") == "" ? -8888L : Long.valueOf(request.getParameter("parentId"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ifReceive", ifReceive);
		map.put("ifEnable", ifEnable);
		map.put("ifCreate", ifCreate);
		map.put("parentId", parentId);
		
		List<TraceNumberSection> dataList = traceNumberSectionDao.getListData(map);
		return dataList;
		
	}
	
	
	public Map<String,Object> getSelectProvinceGridData(HttpServletRequest request){
		
		String unitType = request.getParameter("unitType");
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("unitType", unitType);
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		List<Map<String,Object>> dataList = traceNumberSectionDao.getSelectProvinceListMap(map);
		
		long totalRows = traceNumberSectionDao.getSelectProvinceRows(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		Map<String,Object> gridData = new HashMap<String, Object>();
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}


	public Map<String, Object> getSelectProviderGridData(HttpServletRequest request) {
		
		String parentId = request.getParameter("parentId");
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", parentId);
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		List<Map<String,Object>> dataList = traceNumberSectionDao.getSelectProviderListMap(map);
		
		long totalRows = traceNumberSectionDao.getSelectProviderRows(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		Map<String,Object> gridData = new HashMap<String, Object>();
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}


	public void save(HttpServletRequest request, SysUnits sysUnits,
			SysUser sysUser) {
		// TODO Auto-generated method stub
		
	}

}