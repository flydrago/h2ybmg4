package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ISysParamTypeDao;
import com.h2y.bmg.entity.SysParamType;

/**
 * 系统参数类型Service类
 */
@Service("sysParamTypeService")
public class SysParamTypeServiceImpl implements ISysParamTypeService{


	@Autowired
	protected ISysParamTypeDao sysParamTypeDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param sysParamType
	 *
	 */
	public void add(SysParamType sysParamType) {
		// TODO Auto-generated method stub

		sysParamTypeDao.add(sysParamType);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		sysParamTypeDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	sysParamTypeDao.deleteByIds(ids);
	//}

	public void update(SysParamType sysParamType) {
		// TODO Auto-generated method stub
		sysParamTypeDao.update(sysParamType);
	}

	public SysParamType get(long id) {
		// TODO Auto-generated method stub
		return sysParamTypeDao.get(id);
	}

	public Map<String,Object> getGirdData(HttpServletRequest request){

		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");

		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortname", sortname);
		map.put("sortorder", sortorder);
		map.put("ifQuery", ifQuery);
		
		List<Map<String,Object>> dataList = sysParamTypeDao.getListMap(map);
		long totalRows = sysParamTypeDao.getListRows(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}
	
	/**
	 * 是否有相同编码
	 * @param op
	 * @param sysParamType
	 * @return
	 */
	public boolean isHasSameCode(String op,SysParamType sysParamType){
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("typeCode", sysParamType.getTypeCode());
		
		if (op.equals("modify")) {
			map.put("id", sysParamType.getId());
		}
		
		return sysParamTypeDao.getRowsByCode(map)>0;
	}
}