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
import com.h2y.bmg.dao.IStorehouseDao;
import com.h2y.bmg.entity.Storehouse;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IStorehouseService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.util.JSONUtil;

/**
 * 类描述：仓库Controller 作者：侯飞龙 时间：2015年1月7日上午10:44:05 邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/business/storehouse")
@Scope("prototype")
public class StorehouseController extends BaseController {

	private final static Logger logger = Logger
			.getLogger(StorehouseController.class);

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected IStorehouseService storehouseService;

	@Autowired
	protected IStorehouseDao storehouseDao;

	/**
	 * 页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/storehouse/init");

		SysUser sysUser = getLoginUser();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitId", sysUser.getUnitId());
		List<Map<String, Object>> shopList = storehouseDao
				.getShopTreeList(params);
		view.addObject("treedata", JSONUtil.getJson(shopList));
		view.addObject("sysUnits", getLoginUnit());
		view.addObject("toolbar", sysButtonService.getMenuButtons(request,
				getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns",
				sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject(
				"conditionHtml",
				sysQueryItemService.getConditionHtmlByRequest(request,
						sysUser.getUnitId()));
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
	public ModelAndView add(@ModelAttribute Storehouse storehouse,
			@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify")) {

			storehouse = storehouseDao.get(storehouse.getId());
		}

		view.addObject("storehouse", storehouse);
		view.addObject("op", op);
		view.setViewName("business/storehouse/add");
		view.addObject("validationRules",
				sysValidationService.getValidationByCode("storehouse_validate"));
		return view;
	}

	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(storehouseService.getGridData(request, getLoginUnitId()));
	}

	/**
	 * 保存
	 * 
	 * @param goods
	 * @param op
	 *            add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute Storehouse storehouse,
			@RequestParam String op) {

		Map<String, Object> map = new HashMap<String, Object>();

		SysUnits sysUnits = getLoginUnit();
		SysUser sysUser = getLoginUser();
		try {

			// 供应商和采购商不可添加仓库，只能使用初始化仓库
			/*if (op.equals("add")&& (sysUnits.getUnitType() == 1 || sysUnits.getUnitType() == 2)) {

				map.put("code", "0");
				map.put("msg", "不可添加仓库！");
				outJson(map);
				return;
			}*/

			String parentType = storehouse.getParentType();

			if (!"unit".equals(parentType) && !"shop".equals(parentType)) {

				map.put("code", "0");
				map.put("msg", "无效标示符，parentType：" + parentType + "！");
				outJson(map);
				return;
			}

			// 门店下面限制只能添加一个门店
			if ("add".equals(op) && "shop".equals(parentType)) {

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("parentType", parentType);
				params.put("shopId", storehouse.getShopId());
				params.put("unitId", sysUnits.getId());
				if (storehouseDao.getRowsByParentType(params) > 0) {

					map.put("code", "0");
					map.put("msg", "当前门店下面已经有了仓库不能重复添加！");
					outJson(map);
					return;
				}
			}

			map.put("code", "1");
			map.put("msg", "保存成功！");
			storehouse.setUnitId(sysUnits.getId());
			storehouse.setUnitType(sysUnits.getUnitType());
			storehouse.setZoneCode(sysUnits.getZoneCode());

			storehouseService.save(request, op, storehouse,sysUser);

			addSaveLog(op, storehouse, OpRresult.success + "", "添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, storehouse, OpRresult.fail + "", "添加失败！");
		}

		outJson(map);
	}

	/**
	 * 添加保存操作日志
	 * 
	 * @param op
	 * @param storehouse
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op, Storehouse storehouse, String opResult,
			String memo) {

		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "仓库",
					OpType.add + "", opResult, memo, storehouse.getId() + "",
					BusinessTableName.storehouse.name);
		} else {

			sysLogService.addLog(request, getLoginUser(), "仓库", OpType.modify
					+ "", opResult, memo, storehouse.getId() + "",
					BusinessTableName.storehouse.name);
		}
	}

	/**
	 * 状态改变操作
	 * 
	 * @param op
	 *            {delete:删除、stop:停用、start:启用}
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
		} else if (op.equals("stop")) {
			opName = "停用";
			status = 1;
		} else if (op.equals("start")) {
			opName = "启用";
			status = 0;
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
			params.put("op", op);
			storehouseDao.updateStatusByIds(params);
			sysLogService.addLog(request, getLoginUser(), "仓库", op,
					OpRresult.success + "", opName + "成功！", dataIds,
					BusinessTableName.storehouse.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", opName + "失败！");
			sysLogService.addLog(request, getLoginUser(), "仓库", op,
					OpRresult.fail + "", opName + "失败！", dataIds,
					BusinessTableName.storehouse.name);
		}
		outJson(map);
	}

}
