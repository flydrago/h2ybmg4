package com.h2y.bmg.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.ISysLogService;


/**
 * 日志操作url跳转控制类

 * 作者：侯飞龙

 * 时间：2014-10-13 上午11:31:51

 * 电子邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/sys/log")
@Scope("prototype")
public class SysLogController  extends BaseController{

	private final static Logger logger = Logger.getLogger(SysLogController.class);

	@Autowired
	protected ISysLogService sysLogService;
	

	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/log/init");
		
		SysUser sysUser = getLoginUser();

		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}
	
	/**
	 * 获取列表
	 * @param op grid:表格列，tree:树
	 * @param parentCode 父级Id
	 */
	@RequestMapping(value = "/getList")
	public void getList() {
		outJson(sysLogService.getGridData(request,getLoginUnitId()));
	}
}
