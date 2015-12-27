package com.h2y.img.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.entity.SysUser;
import com.h2y.img.service.IImgStorageService;

@Controller
@Scope("prototype")
@RequestMapping("img/mng")
public class ImgManageController extends BaseController{

	@Autowired
	protected IImgStorageService imgStorageService;
	
	@RequestMapping("/init")
	public String init(Model model){
		
		SysUser sysUser = getLoginUser();
		model.addAttribute("toolbar", sysButtonService.getMenuButtons(request,getLoginSysRoleId(), sysUser));
		model.addAttribute("gridComluns",sysGridColumnsService.getGridColumsByRequest(request));
		model.addAttribute("conditionHtml",sysQueryItemService.getConditionHtmlByRequest(request,sysUser.getUnitId()));
		model.addAttribute("unitId", sysUser.getUnitId());
		
		return "business/img/mng/init";
	}
	
	/**
	 * 获取图片列表
	 */
	@RequestMapping("/getImgList")
	public void getImgList(){
		outJson(imgStorageService.getGridData(request));
	}
	
	/**
	 * 转到 上传图片页面
	 */
	@RequestMapping("/toUploadPage")
	public String toUploadPage(Model model,String unitId){
		model.addAttribute("unitId", unitId);
		return "business/img/mng/uploadImg";
	}
	
	/**
	 * 保存图片信息
	 */
	@RequestMapping("/saveImg")
	public void saveImgInfo(){
		outJson(imgStorageService.saveImg(getReqMap()));
	}
	
	@RequestMapping("/deleteImgInfo")
	public void deleteImgInfo(long imgId){
		imgStorageService.delete(imgId);
	}
	
}
