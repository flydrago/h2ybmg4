package com.h2y.bmg.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.h2y.bmg.entity.Goods;
import com.h2y.bmg.entity.GoodsInfo;
import com.h2y.bmg.entity.GoodsPrice;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IGoodsPriceService;
import com.h2y.bmg.services.IGoodsService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.dict.DictUtil;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
 * 类描述：商品定价Controller 作者：侯飞龙 时间：2015年1月7日上午10:44:05 邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/business/goodsprice")
@Scope("prototype")
public class GoodsPriceController extends BaseController {

	private final static Logger logger = Logger
			.getLogger(GoodsPriceController.class);

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected IGoodsInfoDao goodsInfoDao;

	@Autowired
	protected IFileDataDao fileDataDao;

	@Autowired
	protected IGoodsPriceDao goodsPriceDao;

	@Autowired
	protected IGoodsPriceService goodsPriceService;

	@Autowired
	protected IGoodsService goodsService;

	@Autowired
	protected IGoodsDao goodsDao;

	/**
	 * 页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();

		// 值为2 所有数据包括本公司添加和代理商品，否则代理商品
		String goodsSource = request.getParameter("goodsSource");
		view.addObject("goodsSource", goodsSource);

		SysUser sysUser = getLoginUser();

		view.addObject("toolbar", sysButtonService.getMenuButtons(request,
				getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns",
				sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject(
				"conditionHtml",
				sysQueryItemService.getConditionHtmlByRequest(request,
						sysUser.getUnitId()));
		view.setViewName("business/goodsprice/initPrice");
		return view;
	}

	@RequestMapping(value = "/buy")
	public ModelAndView buy() {
		SysUser sysUser = getLoginUser();
		ModelAndView view = new ModelAndView();
		view.addObject("toolbar", sysButtonService.getMenuButtons(request,
				getLoginSysRoleId(), sysUser));
		view.setViewName("business/goodsprice/buy");
		return view;
	}

	// 采购商浏览列表
	@RequestMapping(value = "/buylist")
	public void getBuyList() {
		outJson(goodsPriceService.getBuyGridData(request));
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
		view.setViewName("business/goodsprice/selectInit");
		view.addObject("selectType", request.getParameter("selectType"));
		view.addObject("gridComluns", sysGridColumnsService
				.getGridColumsByCode("goodspriceselect_grid"));
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
	@RequestMapping(value = "/addPrice")
	public ModelAndView addPrice(@ModelAttribute GoodsPrice goodsPrice,
			@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify") || op.equals("detail")) {
			goodsPrice = goodsPriceDao.get(goodsPrice.getId());

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("goodsId", goodsPrice.getId());
			map.put("version", goodsPrice.getVersion());
			map.put("dataType", 1);// 数据类型，0：商品详细、1：定价商品详细
			GoodsInfo goodsInfo = goodsInfoDao.getByVersionAndGoodsId(map);
			view.addObject("goodsInfo", goodsInfo);
			// 标签数据
			view.addObject("markData",
					JSONUtil.getJson(goodsService.getGoodsMarkData(goodsPrice)));

			// 取商品的图片信息
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dataId", goodsPrice.getId());
			params.put("dataVersion", goodsPrice.getVersion());
			params.put("dataType", 1);
			List<Map<String, Object>> fileDataList = fileDataDao.getListByGoodsVersion(params);
			view.addObject("fileDataListJson", JSONUtil.getJson(fileDataList));

			List<Map<String,Object>> dataGoodsList = goodsPriceDao.getDataGoodsListByGoodsId(goodsPrice.getId());
			view.addObject("dataGoodsListJson", JSONUtil.getJson(dataGoodsList));
		} else {
			goodsPrice.setInventory(1000);
		}

		view.addObject("goodsPrice", goodsPrice);
		view.addObject("op", op);

		if (op.equals("detail")) {

			view.setViewName("business/goodsprice/detailPrice");
		} else {
			view.setViewName("business/goodsprice/addPrice");
			view.addObject("validationRules", sysValidationService
					.getValidationByCode("goodsprice_add_validate"));
		}
		return view;
	}


	/**
	 * 批量添加
	 * @param op
	 * @return
	 */
	@RequestMapping(value = "/addBatch")
	public ModelAndView addBatch(@RequestParam String op) {
		ModelAndView view = new ModelAndView();

		view.addObject("toolbar", sysButtonService.getMenuButtons(request,getLoginSysRoleId(), getLoginUser()));
		view.setViewName("business/goodsprice/addBatch");
		view.addObject("gridComluns",sysGridColumnsService.getGridColumsByCode("goodsselect_grid"));
		return view;
	}

	/**
	 * 批量保存
	 * @param op
	 * @return
	 */
	@RequestMapping(value = "/saveBatch")
	public void saveBatch(String op, String dataIds) {
		Map<String, Object> map = new HashMap<String, Object>();

		String opName = "批量添加";

		try {

			map.put("code", "1");
			map.put("msg", opName + "成功！");

			if (null == dataIds || "".equals(dataIds)) {
				return;
			}

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

			//保存
			goodsPriceService.saveBatch(getLoginUser(), getLoginUnit(), dataIds);

			sysLogService.addLog(request, getLoginUser(), "批量添加", op,OpRresult.success + "", opName + "成功！", dataIds,BusinessTableName.goodsPrice.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", opName + "失败！");
			sysLogService.addLog(request, getLoginUser(), "批量添加", op,OpRresult.fail + "", opName + "失败！", dataIds,BusinessTableName.goodsPrice.name);
		}
		outJson(map);

	}

	/**
	 * 批量添加 获取商品列表
	 * @param op
	 */
	@RequestMapping(value = "/getBatchList")
	public void getBatchList() {		
		outJson(goodsService.getSelectGridData(request, getLoginUnit().getId()));		
	}

	/**
	 * 编辑页面初始化
	 * 
	 * @param goodsprice
	 * @param op
	 *            操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute GoodsPrice goodsPrice,
			@RequestParam String op) {

		ModelAndView view = new ModelAndView();
		SysUnits sysUnits = this.getLoginUnit();

		String unitCode = sysUnits.getUnitCode();
		int unitType = sysUnits.getUnitType();
		// 商品编号前缀
		String goosNumberPrefix = unitType + unitCode;
		view.addObject("goosNumberPrefix", goosNumberPrefix);

		if (op.equals("modify") || op.equals("detail")) {

			String goodsPriceId = request.getParameter("id");
			goodsPrice = goodsPriceDao.get(Long.valueOf(goodsPriceId));

			Goods goods = goodsDao.get(goodsPrice.getGoodsId());

			// 商品编号前缀-分类
			String goosTypePrefix = goods.getGdsCode().split("_")[0];
			view.addObject("goosTypePrefix", goosTypePrefix);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("goodsId", goodsPrice.getId());
			map.put("version", goodsPrice.getVersion());
			map.put("dataType", 1);
			GoodsInfo goodsInfo = goodsInfoDao.getByVersionAndGoodsId(map);
			view.addObject("goodsInfo", goodsInfo);

			// 取商品定价的图片信息
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dataId", goodsPrice.getId());
			params.put("dataVersion", goodsPrice.getVersion());
			params.put("dataType", 1);
			List<Map<String, Object>> fileDataList = fileDataDao
					.getListByGoodsVersion(params);
			view.addObject("fileDataListJson", JSONUtil.getJson(fileDataList));

			// 整箱、单品关联
			Map<String, Object> goodsPriceRtMap = goodsPriceService
					.getGoodsPriceRt(goodsPrice.getId(),
							goodsPrice.getZoneCode());
			if (null != goodsPriceRtMap) {
				view.addObject("goodsDataJson4",
						goodsPriceRtMap.get("goodsDataJson4"));
				view.addObject("returnList", goodsPriceRtMap.get("returnList"));
			} else {
				view.addObject("goodsDataJson4", "[]");
			}
			// 赠品
			if (1 == goodsPrice.getIsGift()) {
				Map<String, Object> giftMap = goodsPriceService.getGoodsGift(
						goodsPrice.getId(), goodsPrice.getZoneCode());
				view.addObject("goodsIds", giftMap.get("goodsIds"));
				view.addObject("goodsInfos", giftMap.get("goodsInfos"));
				view.addObject("goodsGiftList", giftMap.get("goodsGiftList"));
				view.addObject("goodsDataJson2", giftMap.get("goodsDataJson2"));
				view.addObject("giftList", giftMap.get("giftList"));
			} else {
				view.addObject("goodsDataJson2", "[]");
			}

			// 该商品修改前的关联商品
			Map<String, Object> relationMap = new HashMap<String, Object>();
			relationMap.put("goodsId", goodsPrice.getId());
			relationMap.put("dataType", "2");
			relationMap.put("zoneCode", goodsPrice.getZoneCode());
			List<Map<String, Object>> goodsRelationList = goodsPriceDao
					.getRelationGoods(relationMap);
			if (null != goodsRelationList && goodsRelationList.size() > 0) {
				goodsPrice.setIsRelation(1);
				relationMap = goodsPriceService.getGoodsRelation(
						goodsPrice.getId(), goodsPrice.getZoneCode());
				view.addObject("relationGoodsIds",
						relationMap.get("relationGoodsIds"));
				view.addObject("relationGoodsInfos",
						relationMap.get("relationGoodsInfos"));
				view.addObject("goodsDataJson3",
						relationMap.get("goodsDataJson3"));
				view.addObject("returnList", relationMap.get("returnList"));
			} else {
				goodsPrice.setIsRelation(0);
				view.addObject("goodsDataJson3", "[]");
			}

			if (StringUtils.isBlank(goodsPrice.getGoodsNickName())) {
				goodsPrice.setGoodsNickName(goods.getGoodsName());
			}

		} else {
			view.addObject("goodsDataJson2", "[]");
			view.addObject("goodsDataJson3", "[]");
			view.addObject("goodsDataJson4", "[]");
			goodsPrice.setInventory(1000);
		}

		view.addObject("goodsPrice", goodsPrice);
		view.addObject("op", op);

		if (op.equals("detail")) {

			view.setViewName("business/goodsprice/detail");
		} else {

			view.setViewName("business/goodsprice/add");
			view.addObject("validationRules", sysValidationService
					.getValidationByCode("goodsprice_validate"));
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

		outJson(goodsPriceService.getGridData(request, this.getLoginUnitId()));
	}

	/**
	 * 获取选择列表
	 */
	@RequestMapping(value = "/getSelectList")
	public void getSelectList() {

		outJson(goodsPriceService.getSelectGridData(request,
				this.getLoginUnitId()));
	}

	/**
	 * 保存
	 * 
	 * @param goodsPrice
	 * @param op
	 *            add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute GoodsPrice goodsPrice,
			@RequestParam String op) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			SysUnits sysUnits = getLoginUnit();
			SysUser sysUser = getLoginUser();

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("unitId", sysUnits.getId());
			params.put("goodsId", goodsPrice.getGoodsId());
			if (op.equals("modify")) {
				params.put("id", goodsPrice.getId());
			}
			if (goodsPriceDao.getRowsByGoodsId(params) > 0) {

				map.put("code", "0");
				map.put("msg", "当前商品已经添加，不可重复添加！");
				outJson(map);
				return;
			}

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

			goodsPriceService.save(request, op, goodsPrice, sysUser, sysUnits);

			addSaveLog(op, goodsPrice, OpRresult.success + "", "添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, goodsPrice, OpRresult.fail + "", "添加失败！");
		}

		outJson(map);
	}

	/**
	 * 保存 本公司/旗舰自增商品保存
	 * 
	 * @param goods
	 * @param op
	 *            add：添加、modify：修改
	 */
	@RequestMapping(value = "/savePrice")
	public void savePrice(@ModelAttribute GoodsPrice goodsPrice,
			@RequestParam String op) {

		Map<String, Object> map = new HashMap<String, Object>();

		SysUser sysUser = getLoginUser();
		SysUnits sysUnits = getLoginUnit();

		try {

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

			goodsPriceService.savePrice(request, op, goodsPrice, sysUser,
					sysUnits);
			addSaveLog(op, goodsPrice, OpRresult.success + "", "添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, goodsPrice, OpRresult.fail + "", "添加失败！");
		}

		outJson(map);
	}



	@RequestMapping(value = "/getGoodsPriceRt")
	public void getGoodsPriceRt() {

		Map<String, Object> map = new HashMap<String, Object>();
		String goodsPriceId = request.getParameter("goodsPriceId");
		String dataGoodsId = request.getParameter("dataGoodsId");

		Map<String, Object> params = new HashMap<String, Object>();
		if(null != goodsPriceId){
			params.put("goodsId", Long.valueOf(goodsPriceId));
		}

		params.put("dataGoodsId", Long.valueOf(dataGoodsId));
		params.put("dataType", 3);
		params.put("zoneCode", getLoginUnit().getZoneCode());
		List<Map<String,Object>> list = this.goodsPriceDao.getGoodsPriceRt(params);
		if(null != list && !list.isEmpty()){			
			GoodsPrice goodsPriceRt = goodsPriceDao.get(Long.valueOf(list.get(0).get("data_goods_id")+""));
			map.put("code", "0");
			map.put("msg", "该单品已被"+goodsPriceRt.getGoodsNickName()+"关联，请重新选择或删除原关联！");
			outJson(map);
			return;
		}else{
			map.put("code", "1");
			map.put("msg", "是否确定关联该单品！");
		}

		outJson(map);
	}


	/**
	 * 添加保存操作日志
	 * 
	 * @param op
	 * @param goodsPrice
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op, GoodsPrice goodsPrice, String opResult,
			String memo) {

		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "商品定价", OpType.add
					+ "", opResult, memo, goodsPrice.getId() + "",
					BusinessTableName.goodsPrice.name);
		} else {

			sysLogService.addLog(request, getLoginUser(), "商品定价", OpType.modify
					+ "", opResult, memo, goodsPrice.getId() + "",
					BusinessTableName.goodsPrice.name);
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
		Map<String, Object> params = new HashMap<String, Object>();

		String opName = "";
		int status = 0;
		params.put("updateDate", DateUtil.getSystemTime());
		if (op.equals("delete")) {
			opName = "删除";
			status = -1;
		} else if (op.equals("up")) {
			params.put("addedDate", DateUtil.getSystemTime());
			opName = "上架";
			status = 0;
		} else if (op.equals("down")) {
			params.put("shelvesDate", DateUtil.getSystemTime());
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
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("dataType", 3);
			paramsMap.put("zoneCode", getLoginUnit().getZoneCode());
			if (dataId_array.length > 0) {
				for (String dataId : dataId_array) {
					//上架判断该箱是否已经关联单品
					if(op.equals("up")){
						GoodsPrice goodsPrice = goodsPriceDao.get(Long.valueOf(dataId));						
						if(goodsPrice.getGoodsUnit() == 1){
							paramsMap.put("goodsId", Long.valueOf(dataId));
							List<Map<String,Object>> list = this.goodsPriceDao.getGoodsPriceRtByGoodsId(paramsMap);
							if(null == list || list.isEmpty()){
								map.put("code", "0");
								map.put("msg", goodsPrice.getGoodsNickName() + ":请关联单品！");
								sysLogService.addLog(request, getLoginUser(), "商品定价", op,
										OpRresult.fail + "", opName + "失败！", dataIds,
										BusinessTableName.goodsPrice.name);
							}else{
								ids.add(Long.valueOf(dataId));
							}
						}else{
							ids.add(Long.valueOf(dataId));
						}
					}else{
						ids.add(Long.valueOf(dataId));
					}

				}
			}
			if(null != ids && !ids.isEmpty()){
				params.put("status", status);
				params.put("ids", ids);

				int opFlag = goodsPriceDao.updateStatusByIds(params);
				//删除商品同时删除单品/套装关联关系
				if(opFlag > 0 && op.equals("delete")){
					params.put("dataType", 3);
					goodsPriceDao.updateGoodsPriceRtByIds(params);
				}
				sysLogService.addLog(request, getLoginUser(), "商品定价", op,
						OpRresult.success + "", opName + "成功！", dataIds,
						BusinessTableName.goodsPrice.name);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", opName + "失败！");
			sysLogService.addLog(request, getLoginUser(), "商品定价", op,
					OpRresult.fail + "", opName + "失败！", dataIds,
					BusinessTableName.goodsPrice.name);
		}
		outJson(map);
	}

}
