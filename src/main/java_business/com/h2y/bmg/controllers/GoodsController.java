package com.h2y.bmg.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.IFileDataDao;
import com.h2y.bmg.dao.IGoodsDao;
import com.h2y.bmg.dao.IGoodsInfoDao;
import com.h2y.bmg.dao.IGoodsPriceDao;
import com.h2y.bmg.dao.IGoodsTypeDao;
import com.h2y.bmg.entity.Goods;
import com.h2y.bmg.entity.GoodsInfo;
import com.h2y.bmg.entity.GoodsPrice;
import com.h2y.bmg.entity.GoodsType;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IGoodsService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.services.ISysUnitsService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.dict.DictUtil;
import com.h2y.util.JSONUtil;

/**
 * 类描述：商品Controller 作者：侯飞龙 时间：2015年1月7日上午10:44:05 邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/business/goods")
@Scope("prototype")
public class GoodsController extends BaseController {

	private final static Logger logger = Logger
			.getLogger(GoodsController.class);

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected IGoodsService goodsService;

	@Autowired
	protected IGoodsDao goodsDao;

	@Autowired
	protected IGoodsInfoDao goodsInfoDao;

	@Autowired
	protected IFileDataDao fileDataDao;

	@Autowired
	protected IGoodsTypeDao goodsTypeDao;

	@Autowired
	protected IGoodsPriceDao goodsPriceDao;

	// 注册公司
	@Autowired
	protected ISysUnitsService sysUnitsService;

	/**
	 * 页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/goods/init");

		SysUser sysUser = getLoginUser();

		view.addObject("toolbar", sysButtonService.getMenuButtons(request,
				getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns",
				sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject(
				"conditionHtml",
				sysQueryItemService.getConditionHtmlByRequest(request,
						sysUser.getUnitId()));
		view.addObject("unitId", sysUser.getUnitId());
		return view;
	}

	/**
	 * 商品选择页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectInit")
	public ModelAndView selectInit() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/goods/selectInit");
		view.addObject("selectType", request.getParameter("selectType"));
		view.addObject("gridComluns",
				sysGridColumnsService.getGridColumsByCode("goodsselect_grid"));
		return view;
	}

	/**
	 * 商品选择页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/unitInit")
	public ModelAndView unitInit() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/goods/unitInit");
		view.addObject("goodsId", request.getParameter("goodsId"));
		view.addObject("gridComluns",
				sysGridColumnsService.getGridColumsByCode("goodsunit_grid"));
		view.addObject("conditionHtml", sysQueryItemService
				.getConditionHtmlByCode("goodsunit_search", getLoginUnitId()));
		return view;
	}

	/**
	 * 编辑页面初始化
	 * 
	 * @param goods
	 * @param op
	 *            操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute Goods goods, @RequestParam String op) {

		ModelAndView view = new ModelAndView();
		SysUnits sysUnits = this.getLoginUnit();

		String unitCode = sysUnits.getUnitCode();

		//单位类型
		int unitType = sysUnits.getUnitType();
		// 商品编号前缀
		String goosNumberPrefix = unitType + unitCode;
		view.addObject("goosNumberPrefix", goosNumberPrefix);

		if (op.equals("modify") || op.equals("detail")) {

			goods = goodsDao.get(goods.getId());
			// 商品编号前缀-分类
			String goosTypePrefix = goods.getGdsCode().split("_")[0];
			view.addObject("goosTypePrefix", goosTypePrefix);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("goodsId", goods.getId());
			map.put("version", goods.getVersion());
			map.put("dataType", 0);
			GoodsInfo goodsInfo = goodsInfoDao.getByVersionAndGoodsId(map);
			view.addObject("goodsInfo", goodsInfo);
			// 标签数据
			view.addObject("markData",
					JSONUtil.getJson(goodsService.getGoodsMarkData(goods)));

			// 取商品的图片信息
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dataId", goods.getId());
			params.put("dataVersion", goods.getVersion());
			params.put("dataType", 0);
			List<Map<String, Object>> fileDataList = fileDataDao
					.getListByGoodsVersion(params);
			view.addObject("fileDataListJson", JSONUtil.getJson(fileDataList));
		}else{
			goods.setInventory(1000);//默认库存
		}

		view.addObject("goods", goods);
		view.addObject("op", op);

		if (op.equals("detail")) {

			view.setViewName("business/goods/detail");
		} else {
			// view.addObject("imUpUrl",
			// DictUtil.getMainByCode("goodsEditorPicPath").getDictValue());
			view.setViewName("business/goods/add");
			view.addObject("validationRules",
					sysValidationService.getValidationByCode("goods_validate"));
		}
		String imUpUrl = DictUtil.getMainByCode("imageuploadroot_url").getDictValue();
		view.addObject("imUpUrl", imUpUrl);
		return view;
	}

	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(goodsService.getGridData(request));
	}

	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getSelectList")
	public void getSelectList() {

		outJson(goodsService.getSelectGridData(request, this.getLoginUnitId()));
	}

	/**
	 * 获取商品代理列表
	 */
	@RequestMapping(value = "/getUnitList")
	public void getUnitList() {

		outJson(goodsService.getUnitGridData(request));
	}

	/**
	 * 保存
	 * 
	 * @param goods
	 * @param op
	 *            add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute Goods goods, @RequestParam String op) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("number", goods.getGoodsNumber());
			if (op.equals("modify")) {
				params.put("id", goods.getId());
			}
			// 验证商品编码唯一性
			if (goodsDao.getRowsByNumber(params) > 0) {
				map.put("code", "0");
				map.put("msg", "商品编码已重复，请使用其他编码！");
				outJson(map);
				return;
			}

			SysUser sysUser = getLoginUser();
			SysUnits sysUnits = getLoginUnit();

			SysDictMain logoData = DictUtil.getMainByCode("goodsLogoData_path");
			SysDictMain picData = DictUtil.getMainByCode("goodsPicData_path");

			if (null == logoData || null == logoData.getDictValue()
					|| "".equals(logoData.getDictValue())) {

				map.put("code", "0");
				map.put("msg", "请在字典中维护商品Logo图片上传路径，编码为：goodsLogoData_path！");
				outJson(map);
				return;
			}

			if (null == picData || null == picData.getDictValue()
					|| "".equals(picData.getDictValue())) {

				map.put("code", "0");
				map.put("msg", "请在字典中维护商品图片上传路径，编码为：goodsPicData_path！");
				outJson(map);
				return;
			}

			map.put("code", "1");
			map.put("msg", "保存成功！");

			goodsService.save(request, op, goods, sysUser, sysUnits);

			addSaveLog(op, goods, OpRresult.success + "", "添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, goods, OpRresult.fail + "", "添加失败！");
		}

		outJson(map);
	}

	/**
	 * 添加保存操作日志
	 * 
	 * @param op
	 * @param goods
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op, Goods goods, String opResult, String memo) {

		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "商品",
					OpType.add + "", opResult, memo, goods.getId() + "",
					BusinessTableName.goods.name);
		} else {

			sysLogService.addLog(request, getLoginUser(), "商品", OpType.modify
					+ "", opResult, memo, goods.getId() + "",
					BusinessTableName.goods.name);
		}
	}

	/**
	 * 状态改变操作
	 * 
	 * @param op
	 *            {delete:删除、up:上架、down:下架}
	 * @param dataIds
	 *            执行响应操作的商品Ids字符串（eg:1,2）
	 */
	@RequestMapping(value = "/status")
	public void status(String op, String dataIds) {
		Map<String, Object> map = new HashMap<String, Object>();

		String opName = "";
		int status = 0;
		if (op.equals("delete")) {
			opName = "删除";
			status = -1;
		} else if (op.equals("up")) {
			opName = "上架";
			status = 0;
		} else if (op.equals("down")) {
			opName = "下架";
			status = 1;
		} else {
			map.put("code", "1");
			map.put("msg", "无效标示符，op:" + op);
			outJson(map);
			return;
		}

		try {

			map.put("code", "1");
			map.put("msg", opName + "成功！");

			if (null == dataIds || "".equals(dataIds)) {
				return;
			}

			List<Long> ids = new ArrayList<Long>();
			String dataId_array[] = dataIds.split(",");
			if (dataId_array.length > 0) {
				for (String string : dataId_array) {
					ids.add(Long.valueOf(string));
				}
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status", status);
			params.put("ids", ids);
			goodsDao.updateStatusByIds(params);
			sysLogService.addLog(request, getLoginUser(), "商品", op,
					OpRresult.success + "", opName + "成功！", dataIds,
					BusinessTableName.goods.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", opName + "失败！");
			sysLogService.addLog(request, getLoginUser(), "商品", op,
					OpRresult.fail + "", opName + "失败！", dataIds,
					BusinessTableName.goods.name);
		}
		outJson(map);
	}

	/**
	 * 获取详细(包括商品详细、logo、图片、详细信息)
	 */
	@RequestMapping(value = "/getDetail")
	public void getDetail(long id, String op) {

		Map<String, Object> detailData = new HashMap<String, Object>();
		// 商品信息
		Goods goods = goodsDao.get(id);
		detailData.put("goods", goods);

		detailData.put(
				"goodsUnitName",
				DictUtil.getDetailByCode(1, "goods_unit",
						goods.getGoodsUnit() + "").getValue());

		// 商品选择回调
		if (op.equals("callback")) {

			// 取商品的图片信息
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dataId", goods.getId());
			params.put("dataVersion", goods.getVersion());
			params.put("dataType", 0);
			List<Map<String, Object>> fileDataList = fileDataDao
					.getListByGoodsVersion(params);
			detailData.put("fileDataList", fileDataList);

			// 取商品的图片信息
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("goodsId", goods.getId());
			map.put("version", goods.getVersion());
			map.put("dataType", 0);
			GoodsInfo goodsInfo = goodsInfoDao.getByVersionAndGoodsId(map);
			detailData.put("goodsInfo", goodsInfo);
		}

		// 取出商品标签
		List<Map<String, Object>> markList = goodsService
				.getGoodsMarkData(goods);
		detailData.put("markList", markList);

		// 取出类型
		Map<String, Object> typeMap = new HashMap<String, Object>();
		typeMap.put("ids", goods.getGdsCode().replaceAll("_", ","));
		List<GoodsType> typeList = goodsTypeDao.getListByIds(typeMap);
		detailData.put("typeList", typeList);

		outJson(detailData);
	}

	/**
	 * 获取详细(包括商品详细、logo、图片、详细信息)
	 */
	@RequestMapping(value = "/getPriceDetail")
	public void getPriceDetail(long id, String op) {

		Map<String, Object> detailData = new HashMap<String, Object>();
		// 商品信息
		Goods goods = goodsDao.get(id);
		detailData.put("goods", goods);
		detailData.put(
				"goodsUnitName",
				DictUtil.getDetailByCode(1, "goods_unit",
						goods.getGoodsUnit() + "").getValue());

		// 商品定价信息
		Map<String, Object> priceParams = new HashMap<String, Object>();
		priceParams.put("goodsId", id);
		priceParams.put("unitId", getLoginUnitId());
		GoodsPrice goodsPrice = goodsPriceDao.getByGoodsId(priceParams);
		detailData.put("goodsPrice", goodsPrice);

		// 取商品的图片信息
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dataId", goodsPrice.getId());
		params.put("dataVersion", goodsPrice.getVersion());
		params.put("dataType", 1);
		List<Map<String, Object>> fileDataList = fileDataDao
				.getListByGoodsVersion(params);
		detailData.put("fileDataList", fileDataList);

		// 取商品的图片信息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsId", goodsPrice.getId());
		map.put("version", goodsPrice.getVersion());
		map.put("dataType", 1);
		GoodsInfo goodsInfo = goodsInfoDao.getByVersionAndGoodsId(map);
		detailData.put("goodsInfo", goodsInfo);

		// 取出商品标签
		List<Map<String, Object>> markList = goodsService
				.getGoodsMarkData(goods);
		detailData.put("markList", markList);

		// 取出类型
		Map<String, Object> typeMap = new HashMap<String, Object>();
		typeMap.put("ids", goods.getGdsCode().replaceAll("_", ","));
		List<GoodsType> typeList = goodsTypeDao.getListByIds(typeMap);
		detailData.put("typeList", typeList);

		outJson(detailData);
	}

	/**
	 * 商品代理状态改变操作
	 * 
	 * @param op
	 *            {up:上架、down:下架}
	 * @param dataIds
	 *            执行响应操作的商品Ids字符串（eg:1,2）
	 */
	@RequestMapping(value = "/unitStatus")
	public void unitStatus(String op, String dataIds) {
		Map<String, Object> map = new HashMap<String, Object>();

		String opName = "";
		int status = 0;
		if (op.equals("up")) {
			opName = "上架";
			status = 0;
		} else if (op.equals("down")) {
			opName = "下架";
			status = 1;
		} else {
			map.put("code", "1");
			map.put("msg", "无效标示符，op:" + op);
			outJson(map);
			return;
		}

		try {

			map.put("code", "1");
			map.put("msg", opName + "成功！");
			if (null == dataIds || "".equals(dataIds)) {
				return;
			}

			List<Long> ids = new ArrayList<Long>();
			String dataId_array[] = dataIds.split(",");
			if (dataId_array.length > 0) {
				for (String string : dataId_array) {
					ids.add(Long.valueOf(string));
				}
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status", status);
			params.put("ids", ids);
			goodsPriceDao.updateGoodsStatusByIds(params);
			sysLogService.addLog(request, getLoginUser(), "代理商品审核", op,
					OpRresult.success + "", opName + "成功！", dataIds,
					BusinessTableName.goodsPrice.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", opName + "失败！");
			sysLogService.addLog(request, getLoginUser(), "代理商品审核", op,
					OpRresult.fail + "", opName + "失败！", dataIds,
					BusinessTableName.goodsPrice.name);
		}
		outJson(map);
	}

}
