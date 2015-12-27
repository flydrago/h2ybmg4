package com.h2y.img.controllers;

import java.util.Enumeration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.img.service.IImgStorageService;

/**
 * 项目名称：h2ybmg2  
 * 类名称：ImgStorageController  
 * 类描述：图片库访问跳转控制类  
 * 创建人：侯飞龙  
 * 创建时间：2015年10月11日 下午6:04:28  
 * 修改人：侯飞龙
 * 修改时间：2015年10月11日 下午6:04:28  
 * 修改备注：  
 * @version
 */
@Controller						   
@RequestMapping(value = "/business/imgstorage")
@Scope("prototype")
public class ImgStorageController  extends BaseController{

	private final static Logger logger = Logger.getLogger(ImgStorageController.class);
	
	@Autowired
	protected IImgStorageService imgStorageService;

	
	/**
	 * 选择页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectInit")
	public ModelAndView selectInit() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/imgstorage/selectInit");
		Enumeration<String> names=(Enumeration<String>)request.getParameterNames();
	    while(names.hasMoreElements()){
	          String name=names.nextElement();
	          view.addObject(name, request.getParameter(name));
	    }
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("imgstorageSelect_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("imgstorageSelect_query", this.getLoginUnitId()));
		return view;
	}

	/**
	 * 获取选择列表
	 */
	@RequestMapping(value = "/getSelectList")
	public void getSelectList() {

		outJson(imgStorageService.getSelectGridData(request, getLoginUnitId()));
	}
	
	/**
	 * 预览页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/view")
	public ModelAndView view(long id) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/imgstorage/view");
		view.addObject("id", id);
		return view;
	}
}
