package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IScheduleTaskDao;
@Service("scheduleTaskService")
public class ScheduleTaskServiceImpl implements IScheduleTaskService {

	



		@Autowired
		protected IScheduleTaskDao scheduleTaskDao;


		public Map<String, Object> getGridData(HttpServletRequest request) {
			Map<String,Object> gridData = new HashMap<String, Object>();
			//分页
			String page = request.getParameter("page");
			String pagesize = request.getParameter("pagesize");
			//排序
			String sortname = request.getParameter("sortname");
			String sortorder = request.getParameter("sortorder");
			if (sortname!=null && !sortname.equals("")) {
				sortname = sortname.toLowerCase();}
			//查询
			String ifQuery = request.getParameter("ifQuery");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("page",Integer.parseInt(page));
			map.put("pagesize",Integer.parseInt(pagesize));
			map.put("sortorder", sortorder);
			map.put("sortname", sortname);
			map.put("ifQuery", ifQuery);
			List<Map<String,Object>> dataList = scheduleTaskDao.getListMap(map);
			if (dataList==null) {
				dataList = new ArrayList<Map<String,Object>>();
			}
			gridData.put("Rows", dataList);
			gridData.put("Total", scheduleTaskDao.getListRows(map));
			return gridData;
		}

}
