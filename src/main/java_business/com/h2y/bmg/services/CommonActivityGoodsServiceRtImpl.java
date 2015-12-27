package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ICommonActivityDao;
import com.h2y.bmg.dao.ICommonActivityGoodsRtDao;
import com.h2y.bmg.dao.ICommonActivityGoodsRtHisDao;
import com.h2y.bmg.dao.IGoodsDao;
import com.h2y.bmg.dao.IGoodsTypeDao;
import com.h2y.bmg.entity.CommonActivityGoodsRt;
import com.h2y.bmg.entity.CommonActivityGoodsRtHis;
import com.h2y.util.DateUtil;

/**
 * 类描述：一般活动与商品业务操作接口实现类   
 * 作者：侯飞龙
 * 时间：2015年1月7日上午10:04:17
 * 邮件：1162040314@qq.com
 */
@Service("commonActivityGoodsService")
public class CommonActivityGoodsServiceRtImpl implements ICommonActivityGoodsRtService{


	@Autowired
	protected ICommonActivityGoodsRtDao commonActivityGoodsDao;

	@Autowired
	protected ICommonActivityGoodsRtHisDao commonActivityGoodsHisDao;

	@Autowired
	protected IGoodsDao goodsDao;

	@Autowired
	protected IGoodsTypeDao goodsTypeDao;

	@Autowired
	protected ICommonActivityDao commonActivityDao;

	public Map<String,Object> getGridData(HttpServletRequest request,long unitId){

		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String dataId = request.getParameter("dataId");
		String dataType = request.getParameter("dataType");
		String ifQuery = request.getParameter("ifQuery");

		if (sortname!=null && !sortname.equals("")) {

			if (sortname.equals("GOODS_NAME")) {
				sortname = "g.name";
			}else if (sortname.equals("GOODS_NUMBER")) {
				sortname = "g.number";
			}else if (sortname.equals("GOODS_TYPE_NAME")) {
				sortname = "gt.`name`";
			}else {
				sortname = "cag."+sortname.toLowerCase();
			}
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("ifQuery", ifQuery);
		map.put("isStart", request.getParameter("isStart"));
		map.put("unitId", unitId);
		map.put("dataId", Long.valueOf(dataId));
		map.put("dataType", Long.valueOf(dataType));


		List<Map<String,Object>> dataList = commonActivityGoodsDao.getListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", commonActivityGoodsDao.getListRows(map));
		return gridData;
	}


	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @return
	 */
	public Map<String,Object> getDateCrossGridData(HttpServletRequest request,long unitId){

		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String goodsPriceId = request.getParameter("goodsPriceId");
		String id = request.getParameter("id");
		String op = request.getParameter("op");

		if (sortname!=null && !sortname.equals("")) {

			if (sortname.equals("TITLE")) {
				sortname = "a.title";
			}else {
				sortname = "a."+sortname.toLowerCase();
			}
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("goodsPriceId", Long.valueOf(goodsPriceId));
		map.put("id", Long.valueOf(id));
		map.put("op", op);
		map.put("unitId", unitId);

		if (startDate!=null && !startDate.equals("")) {
			map.put("startDate", DateUtil.toDate(startDate, DateUtil.YYYY_MM_DD_HH_MM));
		}

		if (endDate!=null && !endDate.equals("")) {
			map.put("endDate", DateUtil.toDate(endDate, DateUtil.YYYY_MM_DD_HH_MM));
		}

		List<Map<String,Object>> dataList = commonActivityGoodsDao.getDateCrossListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", commonActivityGoodsDao.getDateCrossListRows(map));
		return gridData;
	}

	/**
	 * 保存操作
	 * @param commonActivityGoods 
	 */
	public void save(String op,CommonActivityGoodsRt commonActivityGoods){

		if (op.equals("add")) {


			commonActivityGoods.setCreateDate(DateUtil.getSystemTime());
			commonActivityGoodsDao.add(commonActivityGoods);
		}else {

			CommonActivityGoodsRt commonActivityGoods2 = commonActivityGoodsDao.get(commonActivityGoods.getId());

			commonActivityGoods.setCreateDate(commonActivityGoods2.getCreateDate());
			commonActivityGoods.setUnitId(commonActivityGoods2.getUnitId());
			//时间修改重置销售数量
			if (commonActivityGoods2.getStartDate().compareTo(commonActivityGoods.getStartDate())==0 && commonActivityGoods2.getEndDate().compareTo(commonActivityGoods.getEndDate())==0) {
				commonActivityGoods.setSellNumber(commonActivityGoods2.getSellNumber());
			}else {
				commonActivityGoods.setSellNumber(0);
			}
			commonActivityGoodsDao.update(commonActivityGoods);
		}
	}
}