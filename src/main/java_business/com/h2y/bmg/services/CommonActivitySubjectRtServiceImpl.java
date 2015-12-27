package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.dao.ICommonActivitySubjectRtDao;
import com.h2y.bmg.dao.ICommonSubjectDao;
import com.h2y.bmg.entity.CommonActivitySubjectRt;

/**
 * 类描述：活动主题关联业务类接口实现类   
 * 作者：侯飞龙
 * 时间：2015年1月7日上午9:59:28
 * 邮件：1162040314@qq.com
 */
@Service("commonActivitySubjectService")
public class CommonActivitySubjectRtServiceImpl implements ICommonActivitySubjectRtService{
	
	@Autowired
	protected ICommonSubjectDao commonSubjectDao;
	
	@Autowired
	protected ICommonActivitySubjectRtDao commonActivitySubjectDao;

	public Map<String, Object> getGridData(HttpServletRequest request,
			long unitId) {

		Map<String,Object> gridData = new HashMap<String, Object>();
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String activityId = request.getParameter("activityId");
		if (sortname!=null && !sortname.equals("")) {
			if (sortname.equals("ORD")) {
				sortname = "cas."+sortname.toLowerCase();
			}else {
				sortname = "cs."+sortname.toLowerCase();
			}
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("unitId", unitId);
		map.put("activityId", Long.valueOf(activityId));
		List<Map<String,Object>> dataList = commonActivitySubjectDao.getListMap(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", dataList.size());
		return gridData;
	}

	@Transactional(rollbackFor=Exception.class)
	public void save(HttpServletRequest request) {
		
		long activityId = Long.valueOf(request.getParameter("activityId"));
		String subjectId = request.getParameter("subjectId");
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("activityId", activityId);
		params.put("subjectId", subjectId);
		commonActivitySubjectDao.deleteByActivityIdAndSubjectId(params);
		
		CommonActivitySubjectRt commonActivitySubject = new CommonActivitySubjectRt();
		commonActivitySubject.setActivityId(activityId);
		commonActivitySubject.setSubjectId(Long.valueOf(subjectId));
		commonActivitySubjectDao.add(commonActivitySubject);
	}
}