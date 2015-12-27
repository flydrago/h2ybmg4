package com.h2y.bmg.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.IScheduleTaskDao;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IScheduleTaskService;


@Controller
@RequestMapping(value = "/business/scheduletask")
@Scope("prototype")
public class ScheduleTaskController extends BaseController {
	/**  

	 * @Title: ScheduleTaskController.java

	 * @Package com.h2y.bmg.controllers

	 * @Description:

	 * @author lijian 

	 * @date 2015年9月25日 上午8:54:08

	 */
	
	
	@Autowired
	protected IScheduleTaskDao scheduleTaskDao;
	@Autowired
	protected IScheduleTaskService scheduleTasService;


		@InitBinder
		public void initBinder(WebDataBinder binder) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateFormat.setLenient(false);
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
		}


		/**
		 * 编辑页面初始化
		 * @return ModelAndView
		 */
		@RequestMapping(value = "/init")
		public ModelAndView init() {
			ModelAndView view = new ModelAndView();
			SysUser sysUser = getLoginUser();
			view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
			System.out.println(sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
			view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
//			view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
			view.setViewName("business/scheduletask/init");
			return view;
		}

		/**
		 * 获取列表
		 */
		@RequestMapping(value = "/getList")
		public void getList() {
			outJson(scheduleTasService.getGridData(request));
		}
	
}
