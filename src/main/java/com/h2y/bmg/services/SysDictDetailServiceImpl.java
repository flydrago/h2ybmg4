package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ISysDictDetailDao;
import com.h2y.bmg.dao.ISysDictMainDao;
import com.h2y.bmg.entity.SysDictDetail;
import com.h2y.bmg.entity.SysDictMain;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2014-10-26
 * email:info@hwttnet.com
 */
@Service("sysDictDetailService")
public class SysDictDetailServiceImpl implements ISysDictDetailService{


	@Autowired
	protected ISysDictDetailDao sysDictDetailDao;
	
	@Autowired
	protected ISysDictMainDao sysDictMainDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param sysDictDetail
	 *
	 */
	public void add(SysDictDetail sysDictDetail) {
		// TODO Auto-generated method stub

		sysDictDetailDao.add(sysDictDetail);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		sysDictDetailDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	sysDictDetailDao.deleteByIds(ids);
	//}

	public void update(SysDictDetail sysDictDetail) {
		// TODO Auto-generated method stub
		sysDictDetailDao.update(sysDictDetail);
	}

	public SysDictDetail get(long id) {
		// TODO Auto-generated method stub
		return sysDictDetailDao.get(id);
	}
	
	/**
	 * 得到表格列表
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId){
		
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		long dictMainId = Long.valueOf(request.getParameter("dictMainId"));
		SysDictMain sysDictMain = sysDictMainDao.get(dictMainId);

		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sortname", sortname);
		map.put("sortorder", sortorder);
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("unitId", unitId);
		map.put("dictMainId", Long.valueOf(dictMainId));

		Map<String,Object> gridData = new HashMap<String, Object>();

		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		long totalRows = 0;
		
		if (null!=sysDictMain) {
			
			if (sysDictMain.getIsExtends()==0) {//不继承
				
				dataList = sysDictDetailDao.getListMap(map);
				totalRows = sysDictDetailDao.getListRows(map);
			}else {//继承
				
				dataList = sysDictDetailDao.getExtendsListMap(map);
				totalRows = sysDictDetailDao.getExtendsListRows(map);
			}
		}
		
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}
	
	
	/**
	 * 是否有相同编码
	 * @param sysDictDetail
	 * @param op
	 * @return
	 */
	public boolean isHasSameCode(SysDictDetail sysDictDetail,String op){
		
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("op", op);
		params.put("dictMainId", sysDictDetail.getDictMainId());
		params.put("unitId", sysDictDetail.getUnitId());
		params.put("code", sysDictDetail.getCode());
		if (op.equals("modify")) {
			params.put("id", sysDictDetail.getId());
		}
		
		return sysDictDetailDao.getRowsByCode(params)>0;
	}
}