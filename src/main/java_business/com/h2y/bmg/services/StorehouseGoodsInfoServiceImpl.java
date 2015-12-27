package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.dao.IStorehouseDao;
import com.h2y.bmg.dao.IStorehouseGoodsDetailDao;
import com.h2y.bmg.dao.IStorehouseGoodsInfoDao;
import com.h2y.bmg.entity.Storehouse;
import com.h2y.bmg.entity.StorehouseGoodsDetail;
import com.h2y.bmg.entity.StorehouseGoodsInfo;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.util.DateUtil;

/**
 * 类描述：仓库的业务操作接口实现类 作者：侯飞龙 时间：2015年1月7日上午9:59:28 邮件：1162040314@qq.com
 */
@Service("storehouseGoodsInfoService")
public class StorehouseGoodsInfoServiceImpl implements
		IStorehouseGoodsInfoService {

	@Autowired
	protected IStorehouseGoodsInfoDao storehouseGoodsInfoDao;

	@Autowired
	protected IStorehouseGoodsDetailDao storehouseGoodsDetailDao;

	@Autowired
	protected IStorehouseDao storehouseDao;

	@Autowired
	protected ISysLogService sysLogService;

	public Map<String, Object> getGridData(HttpServletRequest request,
			long unitId) {

		Map<String, Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		String storehouseId = request.getParameter("storehouseId");

		if (sortname != null && !sortname.equals("")) {

			if (sortname.equals("GOODS_NICK_NAME")
					|| sortname.equals("MEMBER_PRICE")
					|| sortname.equals("MARKET_PRICE")
					|| sortname.equals("SELL_PRICE")) {

				sortname = "gp." + sortname.toLowerCase();
			} else if (sortname.equals("NUMBER") || sortname.equals("NAME")
					|| sortname.equals("SPEC") || sortname.equals("SELL_PRICE")) {

				sortname = "g." + sortname.toLowerCase();
			} else if (sortname.equals("TYPE_NAME")) {
				sortname = "gt.name";
			} else {
				sortname = "gf." + sortname.toLowerCase();
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("ifQuery", ifQuery);
		map.put("unitId", unitId);
		map.put("storehouseId", Long.valueOf(storehouseId));

		List<Map<String, Object>> dataList = storehouseGoodsInfoDao
				.getListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", storehouseGoodsInfoDao.getListRows(map));
		return gridData;
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(HttpServletRequest request, SysUser sysUser) {

		String[] goodsId_array = request.getParameterValues("goodsId");
		String[] goodsPriceId_array = request
				.getParameterValues("goodsPriceId");
		String[] goodsPriceVersion_array = request
				.getParameterValues("goodsPriceVersion");
		String[] goodsCount_array = request.getParameterValues("goodsCount");

		long storehouseId = Long.valueOf(request.getParameter("storehouseId"));

		Storehouse storehouse = storehouseDao.get(storehouseId);

		// 添加商品历史
		if (goodsId_array.length < 2) {
			return;
		}

		String dataIds = "";

		for (int i = 1; i < goodsId_array.length; i++) {

			StorehouseGoodsDetail storehouseGoodsDetail = new StorehouseGoodsDetail();
			storehouseGoodsDetail.setCreateDate(DateUtil.getSystemTime());
			storehouseGoodsDetail.setCreateUserId(sysUser.getId());
			storehouseGoodsDetail.setGoodsCount(Integer
					.parseInt(goodsCount_array[i]));
			storehouseGoodsDetail.setGoodsId(Long.valueOf(goodsId_array[i]));
			storehouseGoodsDetail.setGoodsPriceId(Long
					.valueOf(goodsPriceId_array[i]));
			storehouseGoodsDetail.setGoodsPriceVersion(Integer
					.parseInt(goodsPriceVersion_array[i]));
			storehouseGoodsDetail.setMemo("后台操作入库");
			storehouseGoodsDetail.setStatus(0);
			storehouseGoodsDetail.setStorehouseId(storehouseId);
			storehouseGoodsDetail.setType(0);
			storehouseGoodsDetailDao.add(storehouseGoodsDetail);

			// 检测商品主表是否有记录，一般更新商品数量
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("goodsPriceId", storehouseGoodsDetail.getGoodsPriceId());
			params.put("storehouseId", storehouseGoodsDetail.getStorehouseId());
			StorehouseGoodsInfo storehouseGoodsInfo = storehouseGoodsInfoDao
					.getByGoodsPriceId(params);

			if (null == storehouseGoodsInfo) {

				storehouseGoodsInfo = new StorehouseGoodsInfo();
				storehouseGoodsInfo.setGoodsId(storehouseGoodsDetail
						.getGoodsId());
				storehouseGoodsInfo.setGoodsPriceId(storehouseGoodsDetail
						.getGoodsPriceId());
				storehouseGoodsInfo.setGoodsCount(storehouseGoodsDetail
						.getGoodsCount());
				storehouseGoodsInfo.setStorehouseId(storehouseGoodsDetail
						.getStorehouseId());
				storehouseGoodsInfo.setShopId(storehouse.getShopId());
				storehouseGoodsInfoDao.add(storehouseGoodsInfo);
			} else {

				storehouseGoodsInfo.setGoodsCount(storehouseGoodsInfo
						.getGoodsCount()
						+ storehouseGoodsDetail.getGoodsCount());
				storehouseGoodsInfoDao.update(storehouseGoodsInfo);
			}

			dataIds += storehouseGoodsDetail.getId();
		}

		sysLogService.addLog(request, sysUser, "仓库历史", "add", OpRresult.success
				+ "", "入库成功！", dataIds,
				BusinessTableName.storehouseGoodsDetail.name);

	}

	/**
	 * 得到仓库 商品详细表格数据
	 * 
	 * @param request
	 *            访问对象
	 * @return
	 */
	public Map<String, Object> getDetailGridData(HttpServletRequest request,
			long unitId) {

		Map<String, Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String ifQuery = request.getParameter("ifQuery");
		String storehouseId = request.getParameter("storehouseId");
		String goodsId = request.getParameter("goodsId");
		String goodsPriceId = request.getParameter("goodsPriceId");

		if (sortname != null && !sortname.equals("")) {

			if (sortname.equals("GOODS_NICK_NAME")) {
				sortname = "gp." + sortname.toLowerCase();
			} else if (sortname.equals("USER_NAME")
					|| sortname.equals("ACCOUNT")) {
				sortname = "sysu." + sortname.toLowerCase();
			} else {
				sortname = "gd." + sortname.toLowerCase();
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("ifQuery", ifQuery);
		map.put("storehouseId", Long.valueOf(storehouseId));
		map.put("goodsId", Long.valueOf(goodsId));
		map.put("goodsPriceId", Long.valueOf(goodsPriceId));
		map.put("unitId", unitId);

		List<Map<String, Object>> dataList = storehouseGoodsInfoDao
				.getDetailListMap(map);

		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", storehouseGoodsInfoDao.getDetailListRows(map));
		return gridData;
	}
}