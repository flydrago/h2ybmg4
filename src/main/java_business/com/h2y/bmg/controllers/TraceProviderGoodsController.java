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
import com.h2y.bmg.dao.ITraceProviderGoodsDao;
import com.h2y.bmg.dao.ITraceProviderItemDao;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.TraceProviderGoods;
import com.h2y.bmg.services.ITraceProviderGoodsService;
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
@RequestMapping(value="/business/traceprovidergoods")
@Scope("prototype")
public class TraceProviderGoodsController extends BaseController{

	private static Logger logger = Logger.getLogger(TraceProviderGoodsController.class);
	
	@Autowired
	protected ITraceProviderGoodsService traceProviderGoodsService;
	
	@Autowired
	protected ITraceProviderGoodsDao traceProviderGoodsDao;
	@Autowired
	protected ITraceProviderItemDao traceProviderItemDao;
	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/traceprovidergoods/init");	
		SysUser sysUser = getLoginUser();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", sysUser.getUnitId());
		List<Map<String, Object>> providerList = traceProviderGoodsDao.getProviderTreeList(params);
		view.addObject("treedata", JSONUtil.getJson(providerList));
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));	
		return view;
	}
	
	
	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {
		
		outJson(traceProviderGoodsService.getGridData(request));
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
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId",request.getParameter("parentId"));
		params.put("ifEnable", 1);
		
		List<TraceProviderGoods> traceProviderGoodsList = traceProviderGoodsDao.getGoodsIdList(params);
		List<Long> goodsIdList = new ArrayList<Long>();
		for(TraceProviderGoods traceProviderGoods :traceProviderGoodsList){
			goodsIdList.add(traceProviderGoods.getGoodsId());
		}
		ModelAndView view = new ModelAndView();	
		view.addObject("op", op);
		view.addObject("goodsIds",JSONUtil.listToJson(goodsIdList));
		view.addObject("parentId", request.getParameter("parentId"));
		view.addObject("parentName", request.getParameter("parentName"));
		view.setViewName("business/traceprovidergoods/add");
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
	public void save(HttpServletRequest request) {
		
		String[] goodsNames = request.getParameterValues("providerGoodsName");
		String[] goodsIds = request.getParameterValues("providerGoodsId");
		Long parentId = Long.valueOf(request.getParameter("providerId"));
		SysUser sysUser = this.getLoginUser();
		
		traceProviderGoodsService.add(goodsNames,goodsIds,parentId,sysUser);
		
//		System.out.println(java.util.Arrays.toString(goodsNames) + "***********\n" + java.util.Arrays.toString(goodsIds));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", 1);
		map.put("msg", "保存成功！");
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
		traceProviderGoodsDao.updateStatueByIds(dataMap);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", 1);
		map.put("msg", "操作成功");
		outJson(map);
	}
	
	
	
	
}