package com.h2y.bmg.controllers;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.IZoneDao;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.Zone;
import com.h2y.bmg.services.ISysDepartmentService;
import com.h2y.bmg.services.ISysDialogService;
import com.h2y.bmg.services.ISysUnitsService;
import com.h2y.util.JSONUtil;

/**
 * 选择窗口url跳转控制类
 * 
 * 作者：侯飞龙
 * 
 * 时间：2014-10-13 上午11:31:51
 * 
 * 电子邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/sys/dialog")
@Scope("prototype")
public class SysDialogController extends BaseController {

	@Autowired
	protected ISysDepartmentService sysDepartmentService;

	@Autowired
	protected ISysUnitsService sysUnitsService;

	@Autowired
	protected ISysDialogService sysDialogService;

	@Autowired
	protected IZoneDao zoneDao;
	/**
	 * 页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init(String op) {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/dialog/" + op);

		// 得到单位Id
		long unitId = getLoginUnitId();

		List<Map<String, Object>> treeList = sysDepartmentService
				.getChildTreeData(unitId, 0l);

		SysUnits sysUnits = sysUnitsService.get(unitId);
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("id", 0);
		rootMap.put("code", sysUnits.getId());
		rootMap.put("text", sysUnits.getUnitName());
		treeList.add(rootMap);
		view.addObject("treedata", JSONUtil.getJson(treeList));
		return view;
	}

	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList(@RequestParam String op) {

		outJson(sysDialogService.getList(request, getLoginUnitId(), op));
	}

	/**
	 * 页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/pageinit")
	public ModelAndView pageinit(String op) {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/dialog/" + op);

		Enumeration<String> names = (Enumeration<String>) request
				.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			view.addObject(name, request.getParameter(name));
		}
		//当前城市名称传给百度地图API
		SysUnits sysUnits = getLoginUnit();
		Zone zone = zoneDao.getZoneByCode(sysUnits.getUnitCode());
		view.addObject("zoneName",zone.getName());
		return view;
	}

	/**
	 * 商品选择框初始化
	 * 
	 * @return
	 */
	@RequestMapping(value = "/goodsInit")
	public ModelAndView goodsInit() {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/dialog/goods");
		view.addObject("selectType", request.getParameter("selectType"));
		view.addObject("callBackFlag", request.getParameter("callBackFlag"));
		view.addObject("treedata",
				JSONUtil.getJson(sysDialogService.getGroodsTypeTreeData()));
		return view;
	}

	/**
	 * 得到商品列表
	 */
	@RequestMapping(value = "/getGoodsList")
	public void getGoodsList() {
		outJson(sysDialogService.getGoodsList(request, getLoginUnitId()));
	}

	/**
	 * 商品选择框初始化
	 * 
	 * @return
	 */
	@RequestMapping(value = "/markInit")
	public ModelAndView markInit(String typeCode) {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/dialog/markInit");
		view.addObject("typeCode", typeCode);
		view.addObject("treedata", JSONUtil.getJson(sysDialogService
				.getMarkTreeDataByTypeCode(typeCode)));
		return view;
	}
}
