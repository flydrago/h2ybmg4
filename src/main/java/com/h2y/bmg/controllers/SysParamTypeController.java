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
import com.h2y.bmg.entity.SysParamType;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.ISysParamTypeService;


/**
 * 系统参数类型Controller（主要用于参数可继承平台情况）

 * 作者：侯飞龙

 * 时间：2014-10-13 上午11:31:51

 * 电子邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value="/sys/paramtype")
@Scope("prototype")
public class SysParamTypeController extends BaseController{

	private static Logger logger = Logger.getLogger(SysParamTypeController.class);

	@Autowired
	protected ISysParamTypeService sysParamTypeService;


	/**
	 * 页面初始化
	 */
	@RequestMapping(value="/init")
	public ModelAndView init(){

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/paramtype/init");
		
		SysUser sysUser = getLoginUser();
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}

	/**
	 * 编辑初始化
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@ModelAttribute SysParamType sysParamType,@RequestParam String op){

		ModelAndView view = new ModelAndView();
		view.addObject("validationRules", sysValidationService.getValidationByCode("sysparamtype_validate"));
		if (op.equals("modify")) {
			sysParamType = sysParamTypeService.get(sysParamType.getId());
		}
		view.addObject("sysParamType",sysParamType);
		view.addObject("op",op);
		view.setViewName("sys/paramtype/add");
		return view;
	}


	/**
	 * 获取列表
	 */
	@RequestMapping(value="/getList")
	public void getList(@RequestParam String op){

		outJson(sysParamTypeService.getGirdData(request));
	}
	

	/**
	 * 保存
	 */
	@RequestMapping(value="/save")
	public void save(@ModelAttribute SysParamType sysParamType,String op){

		Map<String,Object> map = new HashMap<String, Object>();
		try {
			
			map.put("code", "1");
			map.put("msg", "保存成功！");
			
			if (sysParamTypeService.isHasSameCode(op, sysParamType)) {
				map.put("code", "0");
				map.put("msg", "当前编码已使用，请使用其他编码！");
			}else {
				
				if (op.equals("add")) {
					sysParamTypeService.add(sysParamType);
				}else {
					sysParamTypeService.update(sysParamType);
				}
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
		}
		outJson(map);
	}


	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(@ModelAttribute SysParamType sysParamType){

		Map<String,Object> map = new HashMap<String, Object>();

		String code = "1";
		String msg = "删除成功！";
		try {
			
			sysParamTypeService.delete(sysParamType.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			code = "0";
			msg = "删除失败！";
		}
		map.put("code",code);
		map.put("msg",msg);
		outJson(map);
	}
	
}
