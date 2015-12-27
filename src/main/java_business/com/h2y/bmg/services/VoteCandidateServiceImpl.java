package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.h2y.bmg.dao.IVoteCandidateDao;
import com.h2y.bmg.dao.IVoteItemDao;

/*

 * @ClassName: VoteCandidateServiceImpl

 * @Description: 投票候选人业务实现类

 * @author 李剑

 * @date 2015年8月31日 下午4:02:30
 */
@Service("voteCandidateService")
public class VoteCandidateServiceImpl implements IVoteCandidateService{


	@Autowired
	protected IVoteCandidateDao voteCandidateDao;
	@Autowired
	protected IVoteItemDao voteItemDao;


	public Map<String, Object> getGridData(HttpServletRequest request,long unitId) {
		Map<String,Object> gridData = new HashMap<String, Object>();
		//分页
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String subId = request.getParameter("subId");
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
		map.put("unitId", unitId);
		map.put("subId", subId);
		List<Map<String,Object>> dataList = voteCandidateDao.getListMap(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", voteCandidateDao.getListRows(map));
		return gridData;
	}
	
	
	
	public Map<String, Object> getVoteItemsGridData(HttpServletRequest request,long unitId) {
		Map<String,Object> gridData = new HashMap<String, Object>();
		//分页
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String candidateId = request.getParameter("candidateId");
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
		map.put("unitId", unitId);
		map.put("candidateId", candidateId);
		List<Map<String,Object>> dataList = voteItemDao.getListMap(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", voteItemDao.getListRows(map));
		return gridData;
	}


	public void delete(long id) {

	}
}
