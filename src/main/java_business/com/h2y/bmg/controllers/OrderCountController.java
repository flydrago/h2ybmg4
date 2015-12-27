package com.h2y.bmg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.Zone;
import com.h2y.bmg.services.IOrderCountService;
import com.h2y.bmg.services.IZoneService;
import com.h2y.bmg.services.ZoneServiceImpl;

/**
 * 项目名称：h2ybmg2  
 * 类名称：OrderController  
 * 类描述：订单汇总Controller
 * 创建人：侯飞龙  
 * 创建时间：2015年5月5日 下午2:09:15  
 * 修改人：侯飞龙
 * 修改时间：2015年5月5日 下午2:09:15  
 * 修改备注：  
 * @version
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/business/ordercount")
public class OrderCountController extends BaseController{
	
	@Autowired
	protected IOrderCountService orderCountService;
	
	@Autowired
	protected IZoneService zoneService;
	
	/**
	 * 订单区域热度图 页面
	 * @return
	 */
	@RequestMapping(value="/orderHeatPage")
	public ModelAndView orderHeatPage(){
		ModelAndView view = new ModelAndView();

		String zoneCode = this.getLoginUnit().getZoneCode();
		Zone tmpZone = zoneService.getZoneByCode(zoneCode);
		String cityName = tmpZone.getName();
		
		view.setViewName("business/ordercount/jyd_ods");
		view.addObject("cityName", cityName);
		
		return view;
	}
	
	/**
	 * 订单分析页面
	 * @param longitude
	 * @param latitude
	 * @param radius
	 * @return
	 */
	@RequestMapping(value="/orderAnalyses")
	public ModelAndView orderAnalyses(@RequestParam String longitude,@RequestParam String latitude,String radius){
		
		ModelAndView view = new ModelAndView();
		view.setViewName("business/ordercount/orderAnalyses");
		view.addObject("longitude", longitude);
		view.addObject("latitude",latitude);
		view.addObject("radius",radius);
		
		return view;
	}
	
	/**
	 * 获取订单分析数据
	 */
	@RequestMapping(value="/getAnalysesData")
	public void getAnalysesData(){
		outJson(orderCountService.getAnalysesData(request,this.getLoginUnit()));
	}
	
	
	
	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {
		
		SysUser sysUser = getLoginUser();
		ModelAndView view = new ModelAndView();
		view.setViewName("business/ordercount/init");
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		return view;
	}
	
	
	/**
	 * 当天页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/todayInit")
	public ModelAndView todayInit() {
		
		SysUser sysUser = getLoginUser();
		ModelAndView view = new ModelAndView();
		view.setViewName("business/ordercount/todayInit");
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		return view;
	}
	
	
	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {
		
		outJson(orderCountService.getGridData(request, this.getLoginUnit()));
	}
	
	
	/**
	 * 获取当天列表
	 */
	@RequestMapping(value = "/getTodayList")
	public void getTodayList() {
		
		outJson(orderCountService.getTodayGridData(request, this.getLoginUnit()));
	}
}
