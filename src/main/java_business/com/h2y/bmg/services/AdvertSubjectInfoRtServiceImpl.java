package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IAdvertSubjectInfoRtDao;
import com.h2y.bmg.dao.ICommonActivityDao;
import com.h2y.bmg.dao.ICouponsDao;
import com.h2y.bmg.entity.AdvertSubjectInfoRt;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.img.service.IImgStorageSelectFilter;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.MatcherUtil;

/**
 * 项目名称：h2ybmg2  
 * 类名称：AdvertSubjectServiceImpl  
 * 类描述：广告主题业务操作接口实现类  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月8日 上午9:38:56  
 * 修改人：侯飞龙
 * 修改时间：2015年4月8日 上午9:38:56  
 * 修改备注：  
 * @version
 */
@Service("advertSubjectInfoRtService")
public class AdvertSubjectInfoRtServiceImpl implements IAdvertSubjectInfoRtService,ICouponsSelectFilter,IImgStorageSelectFilter{

	@Autowired
	protected IAdvertSubjectInfoRtDao advertSubjectInfoRtDao;
	
	@Autowired
	protected ICommonActivityDao commonActivityDao;

	@Autowired
	protected ICouponsDao couponsDao;
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,SysUnits sysUnits){
		
		Map<String,Object> gridData = new HashMap<String, Object>();
		
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		String subjectId = request.getParameter("subjectId");
		String dataType = request.getParameter("dataType");

		String prefix = dataType.equals("activity")?"ca.":"c.";
		
		if (sortname!=null && !sortname.equals("")) {
			sortname = prefix+sortname.toLowerCase();
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("ifQuery", ifQuery);
		map.put("subjectId", Long.valueOf(subjectId));
		map.put("dataType", dataType);
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		long totalRows = 0;
		if (dataType.equals("activity")) {//促销活动
			
			dataList = advertSubjectInfoRtDao.getListMap(map);
			totalRows = advertSubjectInfoRtDao.getListRows(map);
		}else if (dataType.equals("coupons")) {//优惠劵
			
			dataList = advertSubjectInfoRtDao.getCouponsListMap(map);
			totalRows = advertSubjectInfoRtDao.getCouponsListRows(map);
		}else {//优惠劵
			
			dataList = advertSubjectInfoRtDao.getImgListMap(map);
			totalRows = advertSubjectInfoRtDao.getImgListRows(map);
		}
		
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}

	public void save(HttpServletRequest request) {
		
		String dataListJson = request.getParameter("dataList");
		String subjectId = request.getParameter("subjectId");

		List<Map<String,Object>> dataList = JSONUtil.jsonToListMap(dataListJson);
		
		List<AdvertSubjectInfoRt> advertSubjectInfoRts = new ArrayList<AdvertSubjectInfoRt>();
		for (Map<String, Object> map : dataList) {
			
			String dataType = map.get("dataType")+"";
			String data1 = map.get("data1")==null?null:map.get("data1")+"";
			String data2 = map.get("data2")==null?null:map.get("data2")+"";
			String data3 = map.get("data3")==null?null:map.get("data3")+"";
			
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("data1", data1);
			params.put("data2", data2);
			params.put("data3", data3);
			params.put("dataType", dataType);
			params.put("subjectId", subjectId);
			
			if (advertSubjectInfoRtDao.getListRowsBySubjectId(params)>0) {
				continue;
			}
			AdvertSubjectInfoRt advertSubjectInfoRt = new AdvertSubjectInfoRt();
			advertSubjectInfoRt.setCreateDate(DateUtil.getSystemTime());
			advertSubjectInfoRt.setSubjectId(Long.valueOf(subjectId));
			advertSubjectInfoRt.setStatus(0);
			advertSubjectInfoRt.setDataType(dataType);
			advertSubjectInfoRt.setData1(data1);
			advertSubjectInfoRt.setData2(data2);
			advertSubjectInfoRt.setData3(data3);
			advertSubjectInfoRts.add(advertSubjectInfoRt);
		}
		
		if (!advertSubjectInfoRts.isEmpty()) {
			advertSubjectInfoRtDao.addBatch(advertSubjectInfoRts);
		}
	}

	public Map<String, Object> getActivityGridData(HttpServletRequest request,
			SysUnits sysUnits) {
			
		Map<String,Object> gridData = new HashMap<String, Object>();
		
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		String subjectId = request.getParameter("subjectId");
		String dataType = request.getParameter("dataType");
		String activityType = request.getParameter("activityType");
		
		
		if (sortname!=null && !sortname.equals("")) {
			sortname = "ads."+sortname.toLowerCase();
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("unitId", sysUnits.getId());
		map.put("unitType", sysUnits.getUnitType());
		map.put("zoneCode", sysUnits.getZoneCode()+"%");
		map.put("ifQuery", ifQuery);
		map.put("dataType", activityType);
		
		Map<String,Object> activityParams = new HashMap<String, Object>();
		activityParams.put("subjectId", subjectId);
		activityParams.put("dataType", dataType);
		List<String> activityIdList = advertSubjectInfoRtDao.getData1ListBySubjectId(activityParams);
		if (null!=activityIdList && !activityIdList.isEmpty()) {
			map.put("activityIdList", activityIdList);
		}
		List<Map<String,Object>> dataList = commonActivityDao.getSelectListMap(map);
		long totalRows = commonActivityDao.getSelectListRows(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}

	public List<String> getCouponsCodeFilterList(HttpServletRequest request,
			long unitId) {
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");//数据类型
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("subjectId", data1);
		params.put("dataType", data2);
		List<String> couponsCodeList = advertSubjectInfoRtDao.getData2ListBySubjectId(params);
		
		return couponsCodeList;
	}

	public List<Long> getSelectedImgStorageList(HttpServletRequest request,
			long unitId) {
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");//数据类型
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("subjectId", data1);
		params.put("dataType", data2);
		List<String> imgList = advertSubjectInfoRtDao.getData1ListBySubjectId(params);
		
		List<Long> imgIdList = new ArrayList<Long>();
		for (String imgId : imgList) {
			if (MatcherUtil.checkNumber(imgId)) {
				imgIdList.add(Long.valueOf(imgId));
			}
		}
		return imgIdList;
	}

}