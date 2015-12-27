package com.h2y.img.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.entity.SysUser;
import com.h2y.img.service.IImgModulesService;
import com.h2y.img.service.IImgUsageService;

@Controller
@Scope("prototype")
@RequestMapping("img/mu")
public class ImgModulesUsageController extends BaseController{

	@Autowired
	private IImgModulesService imgModulesService;

	@Autowired
	private IImgUsageService imgUsageService;
	
	@RequestMapping("/init")
	public String init(Model model){
		
		SysUser sysUser = getLoginUser();
		
		model.addAttribute("toolbar", sysButtonService.getMenuButtons(request,getLoginSysRoleId(), sysUser));
		model.addAttribute("gridComluns",sysGridColumnsService.getGridColumsByRequest(request));
		model.addAttribute("conditionHtml",sysQueryItemService.getConditionHtmlByRequest(request,sysUser.getUnitId()));
		
		return "business/img/mu/init";
	}
	
	/**
	 * 获取 图片-模块 列表
	 */
	@RequestMapping("/getModuleList")
	public void getModuleList(){
		outJson(imgModulesService.getGridData(request));
	}
	
	@RequestMapping("/selectModuleListInit")
	public String selectModuleList(Model model,@RequestParam String selectType){
		
		model.addAttribute("selectType", selectType);
		model.addAttribute("gridComluns", sysGridColumnsService.getGridColumsByCode("img_module_grid"));
		
		return "business/img/mng/selectModuleInit";
	}
	
	
	/**
	 * 添加 模块
	 * @return
	 */
	@RequestMapping(value = "/addModule")
	public String addModule(Model model) {
		model.addAttribute("validationRules", sysValidationService.getValidationByCode("img_module_validate"));
		return "business/img/mu/addModule";
	}
	
	/**
	 * 保存 模块信息
	 * @return
	 */
	@RequestMapping(value = "/saveModule")
	public void saveModule(){
		outJson(imgModulesService.saveModule(getReqMap()));
	}

	/**
	 * 删除模块信息
	 * @param moduleId
	 */
	@RequestMapping(value = "/deleteModule")
	public void deleteModule(long moduleId){
		outJson(imgModulesService.deleteModule(moduleId));
	}

	/**
	 * 转到 模块下 用途列表 页面
	 */
	@RequestMapping("/toUsagePage")
	public String toUsagePage(Model model,long moduleId){
		model.addAttribute("moduleId", moduleId);
		model.addAttribute("gridComluns", sysGridColumnsService.getGridColumsByCode("img_usage_grid"));
		return "business/img/mu/usageInit";
	}
	
	
	/**
	 * 获取 模块下的 用途列表
	 * @param moduleCode
	 */
	@RequestMapping("/getUsageList")
	public void getUsageList(){
		outJson(imgUsageService.getGridData(request));
	}
	
	@RequestMapping("selectUsageListInit")
	public String selectUsageListInit(Model model,@RequestParam String selectType,@RequestParam String moduleId){
		
		model.addAttribute("selectType", selectType);
		model.addAttribute("moduleId", moduleId);
		model.addAttribute("gridComluns", sysGridColumnsService.getGridColumsByCode("img_usage_grid"));
		
		return "business/img/mng/selectUsageInit";
	}
	
	/**
	 * 转到  添加 用途的页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/addUsage")
	public String addUsage(Model model,String moduleId){
		model.addAttribute("moduleId",moduleId);
		model.addAttribute("validationRules", sysValidationService.getValidationByCode("img_module_validate"));
		return "business/img/mu/addUsage";
	}
	
	/**
	 * 删除 图片-用途 信息
	 * @param usageId
	 */
	@RequestMapping("/deleteUsage")
	public void deleteUsage(long usageId){
		imgUsageService.delete(usageId);
	}
	
	/**
	 * 保存 用途 信息
	 */
	@RequestMapping("/saveUsage")
	public void saveUsage(){
		outJson(imgUsageService.saveUsage(getReqMap()));
	}
}

