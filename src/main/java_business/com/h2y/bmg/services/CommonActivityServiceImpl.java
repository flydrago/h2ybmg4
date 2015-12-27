package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.dao.ICommonActivityDao;
import com.h2y.bmg.dao.ICommonActivityGoodsRtDao;
import com.h2y.bmg.dao.ICommonActivityGoodsRtHisDao;
import com.h2y.bmg.entity.CommonActivity;
import com.h2y.util.DateUtil;

/**
 * 类描述：活动（热销活动等）业务类接口实现类   
 * 作者：侯飞龙
 * 时间：2015年1月7日上午9:59:28
 * 邮件：1162040314@qq.com
 */
@Service("commonActivityService")
public class CommonActivityServiceImpl implements ICommonActivityService{

	@Autowired
	protected ICommonActivityDao commonActivityDao;

	@Autowired
	protected ICommonActivityGoodsRtDao commonActivityGoodsDao;

	@Autowired
	protected ICommonActivityGoodsRtHisDao commonActivityGoodsHisDao;

	public Map<String, Object> getGridData(HttpServletRequest request,
			long unitId) {

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
		List<Map<String,Object>> dataList = commonActivityDao.getListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", commonActivityDao.getListRows(map));
		return gridData;
	}


	/**
	 * 保存活动
	 */
	@Transactional(rollbackFor = Exception.class)
	public void save(String op, CommonActivity commonActivity) {

		if (op.equals("add")) {

			commonActivity.setCreateDate(DateUtil.getSystemTime());
			commonActivityDao.add(commonActivity);
		}else {

			CommonActivity commonActivity2 = commonActivityDao.get(commonActivity.getId());
			commonActivity.setUserId(commonActivity2.getUserId());
			commonActivity.setUnitId(commonActivity2.getUnitId());
			commonActivity.setCreateDate(commonActivity2.getCreateDate());
			commonActivityDao.update(commonActivity);
		}
	}

	/**
	 * 得到活动树数据
	 * @param unitId 单位Id
	 * @return
	 */
	public List<Map<String,Object>> getTreeList(long unitId,int dataType){

		Map<String,Object> params = new HashMap<String, Object>();
		params.put("unitId", unitId);
		params.put("dataType", dataType);
		if(1 == dataType){
			params.put("dataFlag", "dataSubject");
		}else{
			params.put("dataFlag", "dataActivity");
			params.put("dataType", 1);
		}
		List<Map<String,Object>> activityList = commonActivityDao.getTreeList(params);
		if (activityList==null) {
			activityList = new ArrayList<Map<String,Object>>();
		}

		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put("id", 0);
		dataMap.put("text", "促销活动");
		activityList.add(dataMap);

		return activityList;
	}


	public List<Map<String,Object>> getActivityTypeList(){

//		String [] code_array = {"index","other"};
//		String [] text_array = {"主页","其他"};
//
//		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
//		for (int i = 0; i < code_array.length; i++) {
//			Map<String,Object> dataMap = new HashMap<String, Object>();
//			dataMap.put("value", code_array[i]);
//			dataMap.put("text", text_array[i]);
//			dataMap.put("dataType", "-1");
//			dataList.add(dataMap);
//		}
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put("id", 0);
		dataMap.put("text", "促销活动");
		return dataList;
	}


	/**
	 * 判断秒杀活动是否有时间冲突 
	 */
	public Long getDateCrossListRows(Map<String,Object> map) {
		return commonActivityDao.getDateCrossListRows(map);
	}

}