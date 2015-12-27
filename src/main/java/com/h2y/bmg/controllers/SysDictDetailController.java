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
import com.h2y.bmg.dao.ISysDictMainDao;
import com.h2y.bmg.entity.SysDictDetail;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.services.ISysDictDetailService;
import com.h2y.bmg.services.ISysDictMainService;
import com.h2y.bmg.services.ISysRoleService;
import com.h2y.util.JSONUtil;


/**
 * 字典主表管理url跳转控制类

 * 作者：侯飞龙

 * 时间：2014-10-13 上午11:31:51

 * 电子邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/sys/sysdictdetail")
@Scope("prototype")
public class SysDictDetailController  extends BaseController{

	private final static Logger logger = Logger.getLogger(SysDictDetailController.class);

	@Autowired
	protected ISysRoleService sysRoleService;

	@Autowired
	protected ISysDictMainService sysDictMainService;

	@Autowired
	protected ISysDictMainDao sysDictMainDao;

	@Autowired
	protected ISysDictDetailService sysDictDetailService;

	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/sysdictdetail/init");

		long unitId = getLoginUnitId();

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("unitId", unitId);
		List<Map<String,Object>> dictMainList = sysDictMainDao.getDictMainTreeList(map);
		if (dictMainList==null) {
			dictMainList = new ArrayList<Map<String,Object>>();
		}
		view.addObject("treedata", JSONUtil.getJson(dictMainList));
		view.addObject("unitId", unitId);
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		return view;
	}


	/**
	 * 字典配置页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/menuInit")
	public ModelAndView menuInit(String mainCode) {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/sysdictdetail/menuInit");

		long unitId = getLoginUnitId();

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("unitId", unitId);

		SysDictMain sysDictMain = sysDictMainDao.getSysDictMainByCode(mainCode);
		view.addObject("sysDictMain", sysDictMain);
		view.addObject("unitId", unitId);
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("dictdictail_grid"));
		return view;
	}


	/**
	 * 编辑页面初始化
	 * @param sysDictDetail
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute SysDictDetail sysDictDetail,@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify")) {
			sysDictDetail = sysDictDetailService.get(sysDictDetail.getId());
		}

		SysDictMain sysDictMain = sysDictMainDao.get(sysDictDetail.getDictMainId());
		String validationRules = sysValidationService.getValidationByCode("Dict_"+sysDictMain.getDictCode());
		if (null==validationRules || "".equals(validationRules)) {
			validationRules = sysValidationService.getValidationByCode("sysdictdetail_validate");
		}

		view.addObject("validationRules", validationRules);

		view.addObject("sysDictMain", sysDictMainDao.get(sysDictDetail.getDictMainId()));
		view.addObject("unitId", this.getLoginUnitId());
		view.addObject("sysDictDetail",sysDictDetail);
		view.addObject("op",op);
		view.setViewName("sys/sysdictdetail/add");
		return view;
	}


	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(sysDictDetailService.getGridData(request, getLoginUnitId()));
	}


	/**
	 * 保存
	 * @param sysDepartment 部门对象
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute SysDictDetail sysDictDetail,@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();
		long unitId = getLoginUnitId();
		sysDictDetail.setUnitId(unitId);
		try {


			SysDictMain sysDictMain = sysDictMainDao.get(sysDictDetail.getDictMainId());

			//可继承
			if (sysDictMain.getIsExtends()==1) {

				map.put("code", "1");
				map.put("msg", "保存成功！");
				if (op.equals("add")) {

					if (sysDictDetailService.isHasSameCode(sysDictDetail, op)) {
						map.put("code", "0");
						map.put("msg", "字典编码重复，请使用其他编码！");
					}else {
						if (unitId!=1 && !sysDictMain.getDictCode().equals("topSearch")) {//品牌搜索放开
							map.put("code", "0");
							map.put("msg", "非平台单位不可添加可继承的字典数据！");
						}else {
							sysDictDetailService.add(sysDictDetail);
						}
					}
				}else {

					SysDictDetail sysDictDetail2 = sysDictDetailService.get(sysDictDetail.getId());
					if (unitId!=1) {
						sysDictDetail.setCode(sysDictDetail2.getCode());
					}
					if (sysDictDetail2.getUnitId()!=unitId) {
						sysDictDetailService.add(sysDictDetail);
					}else {
						sysDictDetailService.update(sysDictDetail);
					}
				}
			}else {//不可继承

				if (sysDictDetailService.isHasSameCode(sysDictDetail, op)) {
					map.put("code", "0");
					map.put("msg", "字典编码重复，请使用其他编码！");
				}else {

					map.put("code", "1");
					map.put("msg", "保存成功！");
					if (op.equals("add")) {

						sysDictDetailService.add(sysDictDetail);
					}else {

						sysDictDetailService.update(sysDictDetail);
					}
				}
			}

			sysDictMainService.loadDictDataToCache();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
		}
		outJson(map);
	}


	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute SysDictDetail sysDictDetail) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("code", "1");
			map.put("msg", "删除成功！");

			sysDictDetailService.delete(sysDictDetail.getId());
			sysDictMainService.loadDictDataToCache();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
		}
		outJson(map);
	}
}
