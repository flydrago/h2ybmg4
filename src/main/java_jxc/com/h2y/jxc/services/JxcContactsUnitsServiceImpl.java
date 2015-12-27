package com.h2y.jxc.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.jxc.dao.IJxcContactsUnitsDao;
import com.h2y.jxc.entity.JxcContactsUnits;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-06-25
 * email:info@hwttnet.com
 */
@Service("jxcContactsUnitsService")
public class JxcContactsUnitsServiceImpl implements IJxcContactsUnitsService{


	@Autowired
	protected IJxcContactsUnitsDao jxcContactsUnitsDao;

	public void add(JxcContactsUnits jxcContactsUnits) {
		jxcContactsUnitsDao.add(jxcContactsUnits);
	}


	public void delete(long id) {
		jxcContactsUnitsDao.deleteById(id);
	}

	public void update(JxcContactsUnits jxcContactsUnits) {
		jxcContactsUnitsDao.update(jxcContactsUnits);
	}

	public JxcContactsUnits get(long id) {
		return jxcContactsUnitsDao.get(id);
	}


	public List<JxcContactsUnits> getList(JxcContactsUnits jxcContactsUnits){
		List<JxcContactsUnits> list = jxcContactsUnitsDao.getList(jxcContactsUnits);

		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<JxcContactsUnits> getListPage(Map<String,Object> map){
		return jxcContactsUnitsDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return jxcContactsUnitsDao.getRows(map);
	}


	/**
	 * 获取供应商列表（供选择）
	 */
	public Map<String, Object> getSelectGridData(HttpServletRequest request,String contactUnitsType) {
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
		map.put("unitsType", contactUnitsType);
		List<Map<String, Object>> dataList = jxcContactsUnitsDao.getListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridMap.put("Rows", dataList);
		gridMap.put("Total", jxcContactsUnitsDao.getListRows(map));
		
		return gridMap;
	}
}