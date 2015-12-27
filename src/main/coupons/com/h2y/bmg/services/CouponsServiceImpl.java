package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ICouponsDao;
import com.h2y.bmg.dao.ICouponsDetailDao;
import com.h2y.bmg.dao.ICouponsSourceRtDao;
import com.h2y.bmg.entity.Coupons;
import com.h2y.bmg.entity.CouponsDetail;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.spring.IocUtil;
import com.h2y.util.DateUtil;

/**
 * 项目名称：h2ybmg2  
 * 类名称：CouponsServiceImpl  
 * 类描述：  优惠券业务操作接口实现类
 * 创建人：侯飞龙  
 * 创建时间：2015年7月3日 下午3:40:38  
 * 修改人：侯飞龙
 * 修改时间：2015年7月3日 下午3:40:38  
 * 修改备注：  
 * @version
 */
@Service("couponsService")
public class CouponsServiceImpl implements ICouponsService{

	@Autowired
	protected ICouponsDao couponsDao;

	@Autowired
	protected ICouponsDetailDao couponsDetailDao;

	@Autowired
	protected ICouponsSourceRtDao couponsSourceRtDao;

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
		String ifQuery = request.getParameter("ifQuery");
		String isStart = request.getParameter("isStart");

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("unitId", unitId);
		map.put("ifQuery", ifQuery);
		map.put("isStart", isStart);

		List<Map<String,Object>> dataList = couponsDao.getListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}

		gridData.put("Rows", dataList);
		gridData.put("Total", couponsDao.getListRows(map));
		return gridData;
	}

	public void save(HttpServletRequest request, String op, Coupons coupons,
			CouponsDetail couponsDetail, SysUnits sysUnits) {

		coupons.setUnitId(sysUnits.getId());
		coupons.setUnitType(sysUnits.getUnitType());
		coupons.setZoneCode(sysUnits.getZoneCode());

		if (op.equals("add")) {

			Map<String,Object> map = new HashMap<String, Object>();
			map.put("date", String.format("%tY%<tm%<td", DateUtil.getSystemTime())+"%");
			String couponsCode = "";

			String code = couponsDao.getMaxCodeByDate(map);
			if (null==code) {
				couponsCode = String.format("%tY%<tm%<td%04d", DateUtil.getSystemTime(), 1);
			}else {
				couponsCode = Long.valueOf(code)+1+""; 
			}
			coupons.setCouponsCode(couponsCode);
			coupons.setCreateDate(DateUtil.getSystemTime());
			coupons.setStatus(0);
			//领取数量
			coupons.setClaimedCount(0L);
			couponsDao.add(coupons);//优惠券

			//优惠券详细
			couponsDetail.setCouponsCode(coupons.getCouponsCode());
			couponsDetail.setCouponsId(coupons.getId());
			couponsDetailDao.add(couponsDetail);
		}else {

			//优惠券
			Coupons coupons2 = couponsDao.get(coupons.getId());
			if(null !=coupons2.getClaimedCount() && coupons2.getClaimedCount() > 0){//已被认领的优惠券不能修改部分数据
				coupons2.setStartDate(coupons.getStartDate());
				coupons2.setEndDate(coupons.getEndDate());
				coupons2.setMemo(coupons.getMemo());
				couponsDao.update(coupons2);
				//优惠券详细
				CouponsDetail couponsDetail2 = couponsDetailDao.getByCouponsCode(coupons2.getCouponsCode());
				couponsDetail2.setCouponsCode(coupons2.getCouponsCode());
				couponsDetail2.setCouponsId(coupons2.getId());
				couponsDetail2.setCouponsRule(couponsDetail.getCouponsRule());
				couponsDetailDao.update(couponsDetail2);
			}else{
				coupons.setCreateDate(coupons2.getCreateDate());
				coupons.setStatus(coupons2.getStatus());
				coupons.setCouponsCode(coupons2.getCouponsCode());
				coupons.setClaimedCount(coupons2.getClaimedCount());
				couponsDao.update(coupons);
				//优惠券详细
				CouponsDetail couponsDetail2 = couponsDetailDao.getByCouponsCode(coupons.getCouponsCode());
				couponsDetail2.setCouponsCode(coupons.getCouponsCode());
				couponsDetail2.setCouponsId(coupons.getId());
				couponsDetail2.setCouponsRule(couponsDetail.getCouponsRule());
				couponsDetailDao.update(couponsDetail2);
			}





		}
	}

	public Map<String, Object> getClaimGridData(HttpServletRequest request) {
		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String couponsCode = request.getParameter("couponsCode");
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
			if (sortname.equals("real_name") || sortname.equals("nick_name")) {
				sortname = "mu."+sortname;
			}else if (sortname.equals("source_code_name")) {
				sortname = "cu.source_code";
			}else {
				sortname = "cu."+sortname;
			}
		}
		String ifQuery = request.getParameter("ifQuery");

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("ifQuery", ifQuery);
		map.put("couponsCode", couponsCode);
		List<Map<String,Object>> dataList = couponsDao.getClaimListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}

		gridData.put("Rows", dataList);
		gridData.put("Total", couponsDao.getClaimListRows(map));
		return gridData;
	}

	public Map<String, Object> getSelectGridData(HttpServletRequest request,
			long unitId) {
		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}
		String ifQuery = request.getParameter("ifQuery");
		String filterBean = request.getParameter("filterBean");
		String isStart = request.getParameter("isStart");
		
		List<String> couponsCodeList = null;
		//获取当前模块已经选择过得优惠劵列表
		if (filterBean.equals("default")) {
			String sourceId = request.getParameter("sourceId");
			couponsCodeList = couponsSourceRtDao.getCouponsCodeListBySourceId(Long.valueOf(sourceId));
		}else {
			
			ICouponsSelectFilter couponsSelect = IocUtil.getBean(filterBean);
			couponsCodeList = couponsSelect.getCouponsCodeFilterList(request, unitId);
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("unitId", unitId);
		map.put("isStart", isStart);
		map.put("ifQuery", ifQuery);
		if (null!=couponsCodeList && !couponsCodeList.isEmpty()) {
			map.put("couponsCodeList", couponsCodeList);
		}

		List<Map<String,Object>> dataList = couponsDao.getListMap(map);



		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}

		gridData.put("Rows", dataList);
		gridData.put("Total", couponsDao.getListRows(map));
		return gridData;
	}
	
	
	/**
	 * 得到窗口选择表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String, Object> getSelectDialogList(HttpServletRequest request,
			long unitId) {
		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}
		String ifQuery = request.getParameter("ifQuery");


		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("unitId", unitId);
		map.put("ifQuery", ifQuery);

		List<Map<String,Object>> dataList = couponsDao.getListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}

		gridData.put("Rows", dataList);
		gridData.put("Total", couponsDao.getListRows(map));
		return gridData;
	}
}