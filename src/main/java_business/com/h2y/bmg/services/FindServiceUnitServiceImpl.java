package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.dao.IFindServiceUnitDao;
import com.h2y.bmg.dao.ISysUnitsDao;
import com.h2y.bmg.entity.FindServiceUnit;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
 * 类描述：发现服务
 * 作者：侯飞龙
 * 时间：2015年1月7日上午9:59:28
 * 邮件：1162040314@qq.com
 */
@Service("findServiceUnitService")
public class FindServiceUnitServiceImpl implements IFindServiceUnitService{

	@Autowired
	protected IFindServiceUnitDao findServiceUnitDao;

	@Autowired
	protected ISysUnitsDao sysUnitsDao;



	/**
	 * 保存  私有服务
	 */
	@Transactional(rollbackFor=Exception.class)
	public void save(HttpServletRequest request) {

		String unitId = request.getParameter("unitId");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("unitId", unitId);
		params.put("serviceType", 1);
		//根据service_type获取该公司服务id  0：公共服务（无需分配） 1：私有服务（可分配）
		List<Long> serviceIdList = findServiceUnitDao.getUnitServiceIdList(params);
		if(null != serviceIdList && !serviceIdList.isEmpty() ){
			params.put("serviceIdList", serviceIdList);
		}

		//删除关联
		findServiceUnitDao.deleteByUnitId(params);

		String serviceIds = request.getParameter("serviceIds");

		if (null!=serviceIds && !"".equals(serviceIds)) {

			String [] serviceId_array = serviceIds.split(",");

			List<FindServiceUnit> list = new ArrayList<FindServiceUnit>();

			if (serviceId_array.length>0) {

				for (String serviceId : serviceId_array) {

					FindServiceUnit findServiceUnit = new FindServiceUnit();
					findServiceUnit.setCreateDate(DateUtil.getSystemTime());
					findServiceUnit.setServiceId(Long.valueOf(serviceId));
					findServiceUnit.setUnitId(Long.valueOf(unitId));
					SysUnits sysUnits = sysUnitsDao.get(Long.valueOf(unitId));
					findServiceUnit.setZoneCode(sysUnits.getZoneCode());
					list.add(findServiceUnit);
				}
				//批量添加关联
				findServiceUnitDao.addBatch(list);
			}
		}

	}


	@Transactional(rollbackFor=Exception.class)
	public int delete(FindServiceUnit findServiceUnit) {

		return findServiceUnitDao.deleteById(findServiceUnit.getId());

	}



	public List<Map<String, Object>> getUnitTreeList(long unitId) {

		Map<String,Object> params = new HashMap<String, Object>();
		params.put("unitId", unitId);
		List<Map<String,Object>> treeList = findServiceUnitDao.getUnitServiceTreeList(params);
		if (null==treeList) {
			treeList = new ArrayList<Map<String,Object>>();
		}

		Map<String,Object> root =  new HashMap<String, Object>();
		root.put("id", 0);
		root.put("text", "发现服务");
		treeList.add(root);
		return treeList;
	}


	/**
	 * 获取当前单位公共服务列表
	 */
	public Map<String, Object> getUnitServiceList(HttpServletRequest request,long unitId) {


		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		if (sortname!=null && !sortname.equals("")) {

			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("unitId", unitId);


		List<Map<String,Object>> dataList = findServiceUnitDao.getUnitServiceList(map);

		if (null == dataList || dataList.isEmpty()) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", findServiceUnitDao.getUnitServiceListRows(map));
		return gridData;

	}


	/**
	 * 公共服务选择列表
	 */
	public Map<String, Object> getUnitServiceSelectList(
			HttpServletRequest request, long unitId) {
		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		if (sortname!=null && !sortname.equals("")) {

			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("unitId", unitId);
		//服务类型：0：公共服务（无需分配） 1：私有服务（可分配）
		map.put("serviceType", 0);

		//当前公司服务id 列表
		List<Long> serviceIdList = findServiceUnitDao.getUnitServiceIdList(map);
		if(null != serviceIdList && !serviceIdList.isEmpty()){
			map.put("serviceIdList", serviceIdList);
		}


		List<Map<String,Object>> dataList = findServiceUnitDao.getUnitServiceSelectList(map);

		if (null == dataList || dataList.isEmpty()) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", findServiceUnitDao.getUnitServiceSelectListRows(map));
		return gridData;
	}

	/**
	 * 修改
	 * @param findServiceUnit
	 * @return
	 */
	public int update(FindServiceUnit findServiceUnit){
		return findServiceUnitDao.update(findServiceUnit);
	}


	/**
	 * 保存  公共服务
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveUnitService(HttpServletRequest request) {

		//单位id
		String unitId = request.getParameter("unitId");
		//选中服务id
		String serviceIds = request.getParameter("serviceIds");

		if (null!=serviceIds && !"".equals(serviceIds)) {

			List<Map<String,Object>> idsList = JSONUtil.jsonToListMap(serviceIds);

			List<FindServiceUnit> list = new ArrayList<FindServiceUnit>();

			if (idsList.size()>0) {
				for (Map<String,Object> map : idsList) {
					FindServiceUnit findServiceUnit = new FindServiceUnit();
					findServiceUnit.setCreateDate(DateUtil.getSystemTime());
					findServiceUnit.setServiceId(Long.valueOf(map.get("serviceId")+""));
					findServiceUnit.setUnitId(Long.valueOf(unitId));
					SysUnits sysUnits = sysUnitsDao.get(Long.valueOf(unitId));
					findServiceUnit.setZoneCode(sysUnits.getZoneCode());
					list.add(findServiceUnit);
				}
				//批量添加关联
				findServiceUnitDao.addBatch(list);
			}
		}

	}


}