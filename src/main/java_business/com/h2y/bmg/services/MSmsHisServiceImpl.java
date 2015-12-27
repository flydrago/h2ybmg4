package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IMSmsHisDao;

/**
 * 
 *   
 * 项目名称：h2ybmg2  
 * 类名称：MSmsHisServiceImpl  
 * 类描述：  短信发送记录查看服务接口实现类
 * 创建人：李剑 
 * 创建时间：2015年9月21日 上午11:42:02  
 * 修改人：李剑
 * 修改时间：2015年9月21日 上午11:42:02  
 * 修改备注：  如果你看到这个，那么说明你现在已经在负责我以前的项目了。我感到非常抱歉。愿上帝保佑你。
 * @version
 */
@Service("mSmsHisService")
public class MSmsHisServiceImpl implements IMSmsHisService{


	@Autowired
	protected IMSmsHisDao mSmsHisDao;
	
	
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

		List<Map<String,Object>> dataList = mSmsHisDao.getListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", mSmsHisDao.getListRows(map));
		return gridData;
	}
}