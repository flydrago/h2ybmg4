package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IGoodsTypeDao;
import com.h2y.bmg.entity.GoodsType;
import com.h2y.util.DateUtil;

/**
 * 类描述：商品类型的业务操作接口
 * 作者：侯飞龙
 * 时间：2015年1月7日上午9:59:28
 * 邮件：1162040314@qq.com
 */
@Service("goodsTypeService")
public class GoodsTypeServiceImpl implements IGoodsTypeService{

	@Autowired
	protected IGoodsTypeDao goodsTypeDao;

	/**
	 * 获取商品类型列表
	 */
	public Map<String, Object> getGridData(HttpServletRequest request,long unitId) {

		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		//排序字段
		if (sortname!=null && !sortname.equals("")) {			
			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("id", unitId);
		List<Map<String,Object>> dataList = goodsTypeDao.getListMap(map);

		if (null == dataList || dataList.isEmpty()) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", dataList!=null?dataList.size():0);
		return gridData;
	}


	/**
	 * 保存
	 */
	public void save(String op, GoodsType goodsType) {

		if (op.equals("add")) {

			goodsType.setCreateDate(DateUtil.getSystemTime());
			goodsType.setTypeCode("");
			goodsTypeDao.add(goodsType);
		}else {

			GoodsType goodsType2 = goodsTypeDao.get(goodsType.getId());
			goodsType.setCreateDate(goodsType2.getCreateDate());
			goodsType.setTypeCode(goodsType2.getTypeCode());
			goodsTypeDao.update(goodsType);
		}
	}

}