package com.h2y.bmg.services;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IPromoteActivityDao;
import com.h2y.bmg.dao.IShareHrefDataRtDao;
import com.h2y.bmg.entity.ShareHrefDataRt;
import com.h2y.util.JSONUtil;

/**
 * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-08-18
 * email:info@hwttnet.com
 */
@Service("shareHrefDataRtService")
public class ShareHrefDataRtServiceImpl implements IShareHrefDataRtService{


	@Autowired
	protected IShareHrefDataRtDao shareHrefDataRtDao;


	@Autowired
	protected IPromoteActivityDao promoteActivityDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param shareHrefDataRt
	 *
	 */
	public void add(ShareHrefDataRt shareHrefDataRt) {
		// TODO Auto-generated method stub

		shareHrefDataRtDao.add(shareHrefDataRt);
	}


	public int delete(long id) {
		// TODO Auto-generated method stub
		return shareHrefDataRtDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	shareHrefDataRtDao.deleteByIds(ids);
	//}

	public void update(ShareHrefDataRt shareHrefDataRt) {
		// TODO Auto-generated method stub
		shareHrefDataRtDao.update(shareHrefDataRt);
	}

	public ShareHrefDataRt get(long id) {
		// TODO Auto-generated method stub
		return shareHrefDataRtDao.get(id);
	}


	/**
	 * 获取分享链接  所关联活动列表
	 */
	public Map<String, Object> getGridData(HttpServletRequest request) {
		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String hrefId = request.getParameter("hrefId");
		String ifQuery = request.getParameter("ifQuery");
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("hrefId", hrefId);
		map.put("ifQuery", ifQuery);
		List<Map<String,Object>> dataList = shareHrefDataRtDao.getListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", shareHrefDataRtDao.getListRows(map));
		return gridData;
	}

	/**
	 * 获取活动列表
	 * @param request
	 * @return
	 */
	public Map<String, Object> getPromoteList(HttpServletRequest request,long unitId) {

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
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("unitId", unitId);
		map.put("ifQuery", ifQuery);
		List<Map<String,Object>> dataList = promoteActivityDao.getPromoteList(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", promoteActivityDao.getPromoteListRows(map));
		return gridData;
	}


	/**
	 * 保存
	 * @param request
	 * @param hrefId
	 */
	public ShareHrefDataRt save(HttpServletRequest request,String hrefId,String str1) {

		String promoteData = request.getParameter("promoteData");

		List<Map<String,Object>> list = JSONUtil.jsonToListMap(promoteData);

		if (list.isEmpty() || list.get(0).isEmpty()) {
			return null;
		}

		String dataId = list.get(0).get("dataId")+"";
		ShareHrefDataRt shareHrefDataRt = new ShareHrefDataRt();
		shareHrefDataRt.setDataId(Long.valueOf(dataId));
		shareHrefDataRt.setHrefId(Long.valueOf(hrefId));
		shareHrefDataRt.setStr1(str1);//关联类型 promote：推广活动、vote：投票
		shareHrefDataRtDao.add(shareHrefDataRt);

		return shareHrefDataRt;
	}


	/**
	 * 获取关联投票列表
	 */
	public Map<String, Object> getVoteList(HttpServletRequest request,
			long unitId) {
		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String hrefId = request.getParameter("hrefId");
		String ifQuery = request.getParameter("ifQuery");
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("hrefId", hrefId);
		map.put("ifQuery", ifQuery);
		List<Map<String,Object>> dataList = shareHrefDataRtDao.getVoteList(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", shareHrefDataRtDao.getVoteListRows(map));
		return gridData;
	}


	/**
	 * 获取投票选择列表
	 */
	public Map<String, Object> getVoteSelectList(HttpServletRequest request,
			long unitId) {
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
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("unitId", unitId);
		map.put("ifQuery", ifQuery);
		List<Map<String,Object>> dataList = shareHrefDataRtDao.getVoteSelectList(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", shareHrefDataRtDao.getVoteSelectListRows(map));
		return gridData;
	}


}