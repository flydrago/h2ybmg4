package com.h2y.bmg.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.dao.IPromoteActivityRewardRtDao;
import com.h2y.bmg.entity.PromoteActivityRewardRt;

/**
 * 
 * 推广活动奖励维护
 * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2015-08-17
 * email:info@hwttnet.com
 */
@Service("promoteActivityRewardRtService")
public class PromoteActivityRewardRtServiceImpl implements IPromoteActivityRewardRtService{


	@Autowired
	protected IPromoteActivityRewardRtDao promoteActivityRewardRtDao;


	public void add(PromoteActivityRewardRt promoteActivityRewardRt) {

		promoteActivityRewardRtDao.add(promoteActivityRewardRt);
	}


	public void delete(long id) {
		promoteActivityRewardRtDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	promoteActivityRewardRtDao.deleteByIds(ids);
	//}

	public void update(PromoteActivityRewardRt promoteActivityRewardRt) {
		promoteActivityRewardRtDao.update(promoteActivityRewardRt);
	}

	public PromoteActivityRewardRt get(long id) {
		return promoteActivityRewardRtDao.get(id);
	}


	/**
	 * 根据活动id获取
	 */
	public PromoteActivityRewardRt getByPromoteId(long promoteId) {
		return promoteActivityRewardRtDao.getByPromoteId(promoteId);
	}


	/**
	 * 获取奖励列表
	 */
	public Map<String, Object> getGridData(HttpServletRequest request) {
		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String promoteId = request.getParameter("promoteId");
		if (sortname!=null && !sortname.equals("")) {
			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("promoteId", promoteId);
		List<Map<String,Object>> dataList = promoteActivityRewardRtDao.getListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", promoteActivityRewardRtDao.getListRows(map));
		return gridData;
	}



	/**
	 * 保存
	 */
	@Transactional(rollbackFor = Exception.class)
	public void save(HttpServletRequest request, String op,
			PromoteActivityRewardRt promoteActivityRewardRt) {

		if("add".equals(op)){
			promoteActivityRewardRtDao.add(promoteActivityRewardRt);
		}else{
			PromoteActivityRewardRt promoteActivityRewardRt2 = promoteActivityRewardRtDao.get(promoteActivityRewardRt.getId());
			promoteActivityRewardRt.setPromoteId(promoteActivityRewardRt2.getPromoteId());
			// 优惠券或者商品
			if(promoteActivityRewardRt.getDataType() == 0 || promoteActivityRewardRt.getDataType() == 1){
				promoteActivityRewardRt.setDouble1(0.0);
			}else{//达人币、达人豆、储值 时 优惠券和商品信息字段设置为空
				promoteActivityRewardRt.setStr1("");
				promoteActivityRewardRt.setBigint1(0);
				promoteActivityRewardRt.setStr2("");
				promoteActivityRewardRt.setInt1(0);
				promoteActivityRewardRt.setInt2(0);
			}
			promoteActivityRewardRtDao.update(promoteActivityRewardRt);
		}

	}


	/**
	 * 判断该数据是否已经维护
	 */
	public long getSameDataList(Map<String, Object> map) {
		return promoteActivityRewardRtDao.getSameDataList(map);
	}



}