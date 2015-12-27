package com.h2y.bmg.controllers;

import java.util.HashMap;
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
import com.h2y.bmg.entity.StorehouseGoodsDetail;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IStorehouseGoodsInfoService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;

/**
 * 类描述：仓库商品信息Controller 作者：侯飞龙 时间：2015年1月7日上午10:44:05 邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/business/storehousegoodsinfo")
@Scope("prototype")
public class StorehouseGoodsInfoController extends BaseController {

	private final static Logger logger = Logger
			.getLogger(StorehouseGoodsInfoController.class);

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected IStorehouseGoodsInfoService storehouseGoodsInfoService;

	/**
	 * 页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/storehousegoodsinfo/init");
		view.addObject("storehouseId", request.getParameter("storehouseId"));
		view.addObject("gridComluns", sysGridColumnsService
				.getGridColumsByCode("storehousegoodsinfo_grid"));
		view.addObject("conditionHtml", sysQueryItemService
				.getConditionHtmlByCode("storehousegoodsinfo_search",
						getLoginUnitId()));
		return view;
	}

	/**
	 * 仓库商品历史页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detailInit")
	public ModelAndView detailInit(long storehouseId, long goodsId,
			long goodsPriceId) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/storehousegoodsinfo/detailInit");

		view.addObject("storehouseId", storehouseId);
		view.addObject("goodsId", goodsId);
		view.addObject("goodsPriceId", goodsPriceId);
		view.addObject("gridComluns", sysGridColumnsService
				.getGridColumsByCode("storehousegoodsdetail_grid"));
		view.addObject("conditionHtml", sysQueryItemService
				.getConditionHtmlByCode("storehousegoodsdetail_search",
						getLoginUnitId()));
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
	public ModelAndView add(
			@ModelAttribute StorehouseGoodsDetail storehouseGoodsDetail,
			@RequestParam String op) {

		ModelAndView view = new ModelAndView();
		view.addObject("storehouseGoodsDetail", storehouseGoodsDetail);
		view.addObject("op", op);
		view.setViewName("business/storehousegoodsinfo/add");
		view.addObject("validationRules", sysValidationService
				.getValidationByCode("storehousegoodsinfo_validate"));
		return view;
	}

	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(storehouseGoodsInfoService.getGridData(request,
				getLoginUnitId()));
	}

	/**
	 * 获取仓库商品历史列表
	 */
	@RequestMapping(value = "/getDetailList")
	public void getDetailList() {

		outJson(storehouseGoodsInfoService.getDetailGridData(request,
				this.getLoginUnitId()));
	}

	/**
	 * 保存
	 * 
	 * @param goods
	 * @param op
	 *            add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save() {

		Map<String, Object> map = new HashMap<String, Object>();
		SysUser sysUser = getLoginUser();

		try {

			map.put("code", "1");
			map.put("msg", "保存成功！");
			storehouseGoodsInfoService.save(request, sysUser);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			sysLogService.addLog(request, sysUser, "仓库历史", "add",
					OpRresult.fail + "", "入库失败！", "",
					BusinessTableName.storehouseGoodsDetail.name);
		}
		outJson(map);
	}
}
