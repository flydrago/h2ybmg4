package com.h2y.bmg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IMemberUserService;

@Controller
@Scope("prototype")
@RequestMapping("/business/localmember")
public class LocalMemberController extends BaseController{

	@Autowired
	protected IMemberUserService memberUserService;
	
	/**
	 * 页面初始化
	 */
	@RequestMapping("/init")
	public ModelAndView init(){
		ModelAndView view = new ModelAndView();
		view.setViewName("business/localmember/init");

		SysUser sysUser = getLoginUser();
		
		view.addObject("toolbar", sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns",sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByRequest(request,sysUser.getUnitId()));

		return view;
	}
	
	/**
	 * 获取本地会员列表
	 */
	@RequestMapping("/getList")
	public void getMemberList(){
		SysUnits sysUnit = getLoginUnit();
		outJson(memberUserService.getLocalMemberList(request,sysUnit.getZoneCode()));
	}
	
	@RequestMapping("/recommendInit")
	public ModelAndView recommendInit(@RequestParam String account){
		ModelAndView view = new ModelAndView();
		view.setViewName("business/localmember/recommendInit");

		view.addObject("account", account);
		view.addObject("gridComluns",sysGridColumnsService.getGridColumsByCode("memberreflist_grid"));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByCode("member_ref_search",getLoginUnitId()));

		return view;
	}
	
	@RequestMapping("/getRecommendList")
	public void getRecommendList(String account){
		outJson(memberUserService.getMemberRecommendList(request,account));
	}
}
