package com.h2y.bmg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.IOrderDao;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IOrderService;
import com.h2y.bmg.services.ISysLogService;

/**
 * 项目名称：h2ybmg2  
 * 类名称：OrderController  
 * 类描述：订单Controller
 * 创建人：侯飞龙  
 * 创建时间：2015年5月5日 下午2:09:15  
 * 修改人：侯飞龙
 * 修改时间：2015年5月5日 下午2:09:15  
 * 修改备注：  
 * @version
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/business/order")
public class OrderController extends BaseController{
	
	@Autowired
	protected ISysLogService sysLogService;
	
	@Autowired
	protected IOrderService orderService;
	
	@Autowired
	protected IOrderDao orderDao;
	
	
	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {
		
		SysUser sysUser = getLoginUser();
		SysUnits sysUnits = getLoginUnit();
		ModelAndView view = new ModelAndView();
		view.setViewName("business/order/init");
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("sysUnits", sysUnits);
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByCode("order_unit_query", sysUnits.getId()));
//		}else {
//			view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByRequest(request,sysUser.getUnitId()));
//		}
		return view;
	}
	
	/**
	 * 订单商品页面初始化
	 * @param orderNo 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goods")
	public ModelAndView goods(String orderNo){
		
		ModelAndView view = new ModelAndView();
		view.setViewName("business/order/goods");
		view.addObject("orderNo", orderNo);
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("order_goods_grid"));
		return view;
	}
	
	
	/**
	 * 订单流程页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/flow")
	public ModelAndView flow(String orderNo){
		
		ModelAndView view = new ModelAndView();
		view.setViewName("business/order/flow");
		view.addObject("orderNo", orderNo);
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("order_flow_grid"));
		return view;
	}
	
	
	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {
		
		outJson(orderService.getGridData(request, this.getLoginUnit()));
	}
	
	
	/**
	 * 获取商品列表
	 */
	@RequestMapping(value = "/getGoodsList")
	public void getGoodsList(String orderNo) {
		
		outJson(orderService.getGoodsGridData(orderNo));
	}
	
	
	/**
	 * 获取流程列表
	 */
	@RequestMapping(value = "/getFlowList")
	public void getFlowList(String orderNo) {
		
		outJson(orderService.getFlowGridData(orderNo));
	}
	
	
	
}
