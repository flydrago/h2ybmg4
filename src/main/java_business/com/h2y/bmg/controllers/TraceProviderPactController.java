package com.h2y.bmg.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.ITraceProviderPactDao;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.TraceProviderPact;
import com.h2y.bmg.entity.TraceScanningPath;
import com.h2y.bmg.services.ITraceProviderPactService;
import com.h2y.bmg.services.ITraceScanningPathService;
import com.h2y.dict.DictUtil;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;
/**
 * TraceNumberSectionAction
 * @author hwttnet
 * version:1.2
 * time:2015-06-30
 * email:info@hwttnet.com
 */   
@Controller
@RequestMapping(value="/business/traceproviderpact")
@Scope("prototype")
public class TraceProviderPactController extends BaseController{

	private static Logger logger = Logger.getLogger(TraceProviderPactController.class);
	
	@Autowired
	protected ITraceProviderPactService traceProviderPactService;
	
	@Autowired
	protected ITraceScanningPathService traceScanningPathService;
	
	@Autowired
	protected ITraceProviderPactDao traceProviderPactDao;

	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/traceproviderpact/init");	
		SysUser sysUser = getLoginUser();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", sysUser.getUnitId());
		List<Map<String, Object>> providerList = traceProviderPactDao.getProviderTreeList(params);
		view.addObject("treedata", JSONUtil.getJson(providerList));
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));	
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}
	
	
	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {
		
		outJson(traceProviderPactService.getGridData(request));
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
	public ModelAndView add(@RequestParam String op) {
		
		ModelAndView view = new ModelAndView();	
		view.addObject("op", op);
		view.addObject("parentId", request.getParameter("parentId"));
		view.addObject("parentName", request.getParameter("parentName"));
		view.setViewName("business/traceproviderpact/add");
		return view;
	}
	
	/**
	 * 查看详情
	 * @param op
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public ModelAndView detail(@RequestParam String op) {
		
		Long id = Long.valueOf(request.getParameter("id"));
		
		ModelAndView view = new ModelAndView();	
		view.addObject("op", op);
		TraceProviderPact traceProviderPact = traceProviderPactService.get(id);
		List<Map<String,Object>> traceScanningPathList = traceScanningPathService.getList(id);
		view.addObject("traceProviderPact", traceProviderPact);
		view.addObject("traceScanningPathList", JSONUtil.getJson(traceScanningPathList));
		view.setViewName("business/traceproviderpact/detail");
		return view;
	}
	
	
	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	public void save(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		SysUser sysUser = getLoginUser();

		SysDictMain picData = DictUtil.getMainByCode("pactPicData_path");

		
		if (null == picData || null == picData.getDictValue()
				|| "".equals(picData.getDictValue())) {

			map.put("code", "0");
			map.put("msg", "请在字典中维护供应商合同扫描件上传路径，编码为：pactPicData_path！");
			outJson(map);
			return;
		}

		map.put("code", 1);
		map.put("msg", "保存成功！");
		
		
		traceProviderPactService.save(request,sysUser);
		
		outJson(map);
	}
	
	
	
	/**
	 * 停用
	 */
	@RequestMapping(value = "/stop")
	public void stop() {
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
		dataMap.put("endDate", DateUtil.getSystemTime());
		dataMap.put("ifEnable", 0);

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", 1);
		map.put("msg", "操作成功");
		outJson(map);
	}
	
	
	
	
}