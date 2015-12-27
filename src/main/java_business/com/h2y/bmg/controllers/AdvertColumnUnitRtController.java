package com.h2y.bmg.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.IAdvertColumnDao;
import com.h2y.bmg.dao.IAdvertColumnUnitRtDao;
import com.h2y.bmg.entity.AdvertColumnUnitRt;
import com.h2y.bmg.services.IAdvertColumnUnitRtService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.services.ISysRoleService;
import com.h2y.bmg.services.ISysUnitsService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.util.JSONUtil;


/**
 * 广告栏维护@Controller

 * 作者：侯飞龙

 * 时间：2014-10-13 上午11:31:51

 * 电子邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/business/advertcolumnunitrt")
@Scope("prototype")
public class AdvertColumnUnitRtController  extends BaseController{

	private final static Logger logger = Logger.getLogger(AdvertColumnUnitRtController.class);

	@Autowired
	protected ISysRoleService sysRoleService;

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected ISysUnitsService sysUnitsService;

	@Autowired
	protected IAdvertColumnUnitRtDao advertColumnUnitRtDao;
	
	@Autowired
	protected IAdvertColumnUnitRtService advertColumnUnitRtService;
	
	@Autowired
	protected IAdvertColumnDao advertColumnDao;


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
	}


	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/advertcolumnunitrt/init");
		view.addObject("treedata", JSONUtil.getJson(sysUnitsService.getUnitTreeData()));
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		return view;
	}

	/**
	 * 编辑页面初始化
	 * @param advertColumnUnitRt 广告栏关联
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute AdvertColumnUnitRt advertColumnUnitRt,@RequestParam String op) {

		ModelAndView view = new ModelAndView();
		if (op.equals("modify")) {
			advertColumnUnitRt = advertColumnUnitRtDao.get(advertColumnUnitRt.getId());
		}
		view.addObject("op", op);
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("op", op);
		params.put("unitId", advertColumnUnitRt.getUnitId());
		view.addObject("columnList", advertColumnDao.getSelectList(params));
		view.addObject("advertColumnUnitRt", advertColumnUnitRt);
		view.setViewName("business/advertcolumnunitrt/add");
		view.addObject("validationRules", sysValidationService.getValidationByCode("advertcolumnunitrt_validate"));
		return view;
	}


	/**
	 * 获取单位广告栏列表
	 * @param unitId 单位Id
	 */
	@RequestMapping(value = "/getList")
	public void getList(long unitId) {

		outJson(advertColumnUnitRtService.getGridData(request, unitId));
	}


	/**
	 * 保存
	 * @param advertColumnUnitRt 广告栏位单位关联
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute AdvertColumnUnitRt advertColumnUnitRt,@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "保存成功！");
			
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("unitId", advertColumnUnitRt.getUnitId());
			params.put("columnId", advertColumnUnitRt.getColumnId());
			if (op.equals("modify")) {
				params.put("id", advertColumnUnitRt.getId());					
			}
			
			if (advertColumnUnitRtDao.getColumnRowsByUnitId(params)>0) {
				map.put("code", "0");
				map.put("msg", "当前广告栏已经分配到当前单位下面，如果时间到期，请调整对应的时间！");
				outJson(map);
				return;
			}
			
			advertColumnUnitRtService.save(op, advertColumnUnitRt);
			addSaveLog(op, advertColumnUnitRt, OpRresult.success+"", "广告栏位关联保存成功！");
			
		} catch (Exception e) {
			
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, advertColumnUnitRt, OpRresult.success+"", "广告栏位关联保存成功！");
		}
		outJson(map);
	}
	
	/**
	 * 添加保存操作日志
	 * @param op
	 * @param advertColumnUnitRt
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op,AdvertColumnUnitRt advertColumnUnitRt,String opResult,String memo){
		
		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "广告栏单位关联", OpType.add+"", 
					opResult, memo, advertColumnUnitRt.getId()+"", BusinessTableName.advertColumnUnitRt.name);
		}else {

			sysLogService.addLog(request, getLoginUser(), "广告栏单位关联", OpType.modify+"", 
					opResult, memo, advertColumnUnitRt.getId()+"", BusinessTableName.advertColumnUnitRt.name);
		}
	}

	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute AdvertColumnUnitRt advertColumnUnitRt) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "删除成功！");

			AdvertColumnUnitRt advertColumnUnitRt2 = advertColumnUnitRtDao.get(advertColumnUnitRt.getId());
			advertColumnUnitRt2.setStatus(-1);
			advertColumnUnitRtDao.update(advertColumnUnitRt2);
			sysLogService.addLog(request, getLoginUser(), "广告栏单位关联", OpType.delete+"", 
					OpRresult.success+"", "广告栏单位关联删除成功！", advertColumnUnitRt.getId()+"", BusinessTableName.advertColumnUnitRt.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "广告栏单位关联", OpType.delete+"", 
					OpRresult.fail+"", "广告栏单位关联删除失败！", advertColumnUnitRt.getId()+"", BusinessTableName.advertColumnUnitRt.name);
		}
		outJson(map);
	}
}
