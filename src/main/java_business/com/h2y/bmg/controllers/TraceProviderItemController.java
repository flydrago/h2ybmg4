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
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.TraceProviderItem;
import com.h2y.bmg.services.ITraceProviderItemService;
import com.h2y.util.DateUtil;
/**
 * TraceNumberSectionAction
 * @author hwttnet
 * version:1.2
 * time:2015-06-30
 * email:info@hwttnet.com
 */   
@Controller
@RequestMapping(value="/business/traceprovideritem")
@Scope("prototype")
public class TraceProviderItemController extends BaseController{

	private static Logger logger = Logger.getLogger(TraceProviderItemController.class);
	
	@Autowired
	protected ITraceProviderItemService traceProviderItemService;
	
	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/traceprovideritem/init");
		SysUnits sysUnits = this.getLoginUnit();
		SysUser sysUser = this.getLoginUser();
		Long unitId = sysUnits.getId();
		view.addObject("parentId",unitId);
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByRequest(request,sysUser.getUnitId()));
		return view;
	}
	
	
	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {
		
		outJson(traceProviderItemService.getGridData(request));
	}
	
	
	/**
	 * 编辑页面初始化
	 * 
	 * @param goods
	 * @param op
	 *            操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute TraceProviderItem traceProviderItem,
			@RequestParam String op) {
		
		ModelAndView view = new ModelAndView();	
		if(op.equals("modify")){
			traceProviderItem = traceProviderItemService.get(traceProviderItem.getId());
		}
		view.addObject("traceProviderItem", traceProviderItem);
		view.addObject("op", op);
		view.addObject("validationRules",sysValidationService.getValidationByCode("provideritem_validate"));
		view.setViewName("business/traceprovideritem/add");
		return view;
	}
	/**
	 * 编辑页面初始化
	 * 
	 * @param goods
	 * @param op
	 *            操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute TraceProviderItem traceProviderItem,
			@RequestParam String op) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		SysUnits sysUnits = this.getLoginUnit();
		SysUser sysUser = this.getLoginUser();
		try {
			traceProviderItemService.save(request,op,sysUser,sysUnits,traceProviderItem);
			
			map.put("code", 1);
			map.put("msg", "保存成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
		}
		outJson(map);
	}
	
	
	/**
	 * 状态切换
	 * 启用与停用
	 * @param op
	 */
	@RequestMapping(value="/status")
	public void start(@RequestParam String op){
		
		String ids = request.getParameter("ids");
		String parentId = request.getParameter("parentId");
		String[] idsArry = ids.split("_");
		List<Long> idsList = new ArrayList<Long>();
		for(String idsTemp : idsArry){
			idsList.add(Long.valueOf(idsTemp));
		}
		Map<String,Object>dataMap = new HashMap<String,Object>();
		dataMap.put("ids", idsList);
		dataMap.put("parentId", parentId);
		dataMap.put("updateDate", DateUtil.getSystemTime());
		if(op.equals("start")){
			dataMap.put("ifEnable", 1);
		}else{
			dataMap.put("ifEnable", 0);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			traceProviderItemService.updateStatusByIds(dataMap);
			
			map.put("code", 1);
			map.put("msg", "修改成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "修改失败！");
		}
		outJson(map);
	}
}