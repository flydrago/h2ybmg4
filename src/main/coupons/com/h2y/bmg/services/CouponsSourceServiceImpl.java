package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ICouponsDao;
import com.h2y.bmg.dao.ICouponsSourceDao;
import com.h2y.bmg.dao.ICouponsSourceRtDao;
import com.h2y.bmg.dao.ISysUnitsDao;
import com.h2y.bmg.entity.Coupons;
import com.h2y.bmg.entity.CouponsSource;
import com.h2y.bmg.entity.CouponsSourceRt;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.entity.PushMsg;
import com.h2y.entity.PushToData;
import com.h2y.util.DateUtil;
import com.h2y.util.FreeMarkerUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.PushUtil;
import com.h2y.util.PushUtil.PushLoginType;
import com.h2y.util.PushUtil.PushMsgType;

/**
 * 项目名称：h2ybmg2  
 * 类名称：CouponsSourceServiceImpl  
 * 类描述：优惠券来源业务操作接口实现类  
 * 创建人：侯飞龙  
 * 创建时间：2015年7月14日 下午5:52:00  
 * 修改人：侯飞龙
 * 修改时间：2015年7月14日 下午5:52:00  
 * 修改备注：  
 * @version
 */
@Service("couponsSourceService")
public class CouponsSourceServiceImpl implements ICouponsSourceService{

	@Autowired
	protected ICouponsSourceDao couponsSourceDao;

	@Autowired
	protected ICouponsDao couponsDao;

	@Autowired
	protected ICouponsSourceRtDao couponsSourceRtDao;

	@Autowired
	protected ISysUnitsDao sysUnitsDao;

	public Map<String, Object> getGridData(HttpServletRequest request,
			long unitId) {

		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
			if (sortname.equals("source_code_name")) {
				sortname = "cs.source_code";
			}else {
				sortname = "cs."+sortname;
			}
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
		List<Map<String,Object>> dataList = couponsSourceDao.getListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}

		gridData.put("Rows", dataList);
		gridData.put("Total", couponsSourceDao.getListRows(map));
		return gridData;
	}

	public void save(HttpServletRequest request,String op,CouponsSource couponsSource,SysUnits sysUnits) {

		couponsSource.setUnitId(sysUnits.getId());
		couponsSource.setUnitType(sysUnits.getUnitType());
		couponsSource.setZoneCode(sysUnits.getZoneCode());
		if (op.equals("add")) {

			couponsSource.setCreateDate(DateUtil.getSystemTime());
			couponsSource.setStatus(0);
			couponsSourceDao.add(couponsSource);
		}else {

			CouponsSource couponsSource2 = couponsSourceDao.get(couponsSource.getId());
			couponsSource.setCreateDate(couponsSource2.getCreateDate());
			couponsSource.setStatus(couponsSource2.getStatus());
			couponsSourceDao.update(couponsSource);
		}
	}

	public Map<String, Object> getCouponsGridData(HttpServletRequest request,long unitId,
			long sourceId) {

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
		map.put("sourceId", sourceId);
		map.put("ifQuery", ifQuery);
		map.put("unitId", unitId);
		map.put("isStart", isStart);
		List<Map<String,Object>> dataList = couponsDao.getListMapBySourceId(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}

		gridData.put("Rows", dataList);
		gridData.put("Total", couponsDao.getListRowsBySourceId(map));
		return gridData;
	}

	public void addCoupons(HttpServletRequest request,long sourceId) {

		String couponsData = request.getParameter("couponsData");

		List<Map<String,Object>> list = JSONUtil.jsonToListMap(couponsData);

		if (null==list || list.isEmpty()) {
			return;
		}

		List<CouponsSourceRt> rtList = new ArrayList<CouponsSourceRt>();

		List<String> couponsCodeList = new ArrayList<String>();

		for (Map<String,Object> map : list) {

			String couponsCode = map.get("couponsCode")+"";
			String couponsId = map.get("couponsId")+"";

			CouponsSourceRt couponsSourceRt = new CouponsSourceRt();
			couponsSourceRt.setCouponsCode(couponsCode);
			couponsSourceRt.setCouponsId(Long.valueOf(couponsId));
			couponsSourceRt.setCreateDate(DateUtil.getSystemTime());
			couponsSourceRt.setSourceId(sourceId);
			couponsSourceRt.setStatus(0);
			rtList.add(couponsSourceRt);
			couponsCodeList.add(couponsCode);
		}


		//删除要添加的优惠劵
		Map<String,Object> updateParams = new HashMap<String, Object>();
		updateParams.put("sourceId", sourceId);	
		updateParams.put("couponsCodeList", couponsCodeList);
		updateParams.put("status", -1);
		couponsSourceRtDao.updateStatusBySourceId(updateParams);

		//添加优惠劵
		couponsSourceRtDao.addBatch(rtList);
	}

	public void removeCoupons(HttpServletRequest request) {

		String ids = request.getParameter("ids");
		String ids_array [] = ids.split(",");
		List<Long> idList = new ArrayList<Long>();

		for (String idStr : ids_array) {
			idList.add(Long.valueOf(idStr));
		}

		Map<String,Object> params = new HashMap<String, Object>();
		params.put("ids", idList);
		params.put("status", -1);
		couponsSourceRtDao.updateStatusByIds(params);
	}


	/**
	 * 优惠券消息推送
	 */
	public void sendCouponsMsg(HttpServletRequest request, long couponsId) {

		Coupons coupons = couponsDao.get(couponsId);

		String describtion = PushMsgType.coupons.text;
		//SysDictDetail sysDictDetail = DictUtil.getDetailByCode(1, "pushMsgDescModel", "coupons");
		//if (null!=sysDictDetail && null!=sysDictDetail.getValue() && !"".equals(sysDictDetail.getValue())) {
		//	describtion = sysDictDetail.getValue();
		//}

		Map<String,Object> descMap = new HashMap<String, Object>();
		descMap.put("coupons", coupons);
		describtion = FreeMarkerUtil.getContentFromStringTemplate(descMap, describtion);

		String body = PushUtil.getBody(PushMsgType.coupons.value,1);

		PushMsg pushMsg = new PushMsg();
		PushToData toData = new PushToData();

		SysUnits sysUnits = sysUnitsDao.get(coupons.getUnitId());
		String tag = "";//推送区域
		if(!"1".equals(sysUnits.getZoneCode())){
			tag = sysUnits.getZoneCode();
			toData.setIsAll(0);
		}else{
			toData.setIsAll(1);
		}


		pushMsg.setTitle(PushMsgType.coupons.title);
		pushMsg.setDescribtion(describtion);
		pushMsg.setBody(body);
		pushMsg.setCreateDate(DateUtil.getSystemTime());		
		pushMsg.setTag(tag);
		pushMsg.setMto(tag);
		pushMsg.setDatasourceId(coupons.getCouponsCode());
		pushMsg.setDatasourceType(PushMsgType.coupons.value);
		pushMsg.setSendDate(DateUtil.getSystemTime());


		toData.setTag(tag);//推送区域
		toData.setCreateDate(DateUtil.getSystemTime());															
		toData.setDatasourceId(coupons.getCouponsCode());
		toData.setDatasourceType(PushMsgType.coupons.value);
		toData.setMto(tag);
		toData.setSendDate(DateUtil.getSystemTime());


		//安卓推送
		pushMsg.setType(Integer.valueOf(PushLoginType.androidApp.value));
		toData.setLoginType(PushLoginType.androidApp.value);
		PushUtil.doPush(pushMsg, toData);

		//ios推送
		pushMsg.setType(Integer.valueOf(PushLoginType.iosApp.value));
		toData.setLoginType(PushLoginType.iosApp.value);
		PushUtil.doPush(pushMsg, toData);


	}



}