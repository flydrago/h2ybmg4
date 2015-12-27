package com.h2y.jxc.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.entity.SysUser;
import com.h2y.jxc.entity.JxcSalesOutbound;
import com.h2y.jxc.services.IJxcBillDetailService;
import com.h2y.jxc.services.IJxcContactsUnitsService;
import com.h2y.jxc.services.IJxcSalesOutboundService;
import com.h2y.util.DateUtil;

/**
 * 销售出库单
 * @author jyd-yfb-02
 */
@Controller
@Scope("prototype")
@RequestMapping("jxc/saleout")
public class SalesOutController extends BaseController{
	
	@Autowired
	protected IJxcSalesOutboundService jxcSalesOutboundService;
	
	@Autowired
	protected IJxcContactsUnitsService jxcContactsUnitsService;
	
	@Autowired
	protected IJxcBillDetailService jxcBillDetailService;
	/**
	 * 销售出库单 初始化
	 */
	@RequestMapping("/init")
	public ModelAndView init(){
		ModelAndView view = new ModelAndView();
		view.setViewName("jxc/saleout/init");
		
		SysUser sysUser = getLoginUser();
		
		view.addObject("toolbar", sysButtonService.getMenuButtons(request,getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns",sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByRequest(request,sysUser.getUnitId()));
		view.addObject("unitId", sysUser.getUnitId());
		
		return view;
	}
	
	/**
	 * 销售出库单  获取全部信息
	 */
	@RequestMapping("/getList")
	public void getList(){
		outJson(jxcSalesOutboundService.getGridData(request));
	}
	
	/**
	 * 销售出库单  新单初始化
	 */
	@RequestMapping("/add")
	public ModelAndView add(@ModelAttribute JxcSalesOutbound saleoutBill,@RequestParam String op){
		ModelAndView view = new ModelAndView();

		//生产单据编号
		Date today = DateUtil.getSystemTime();
		SimpleDateFormat sdfForNo = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeNow = sdfForNo.format(today);
		int randomNum = (int) ((Math.random()*9+1)*1000);
		String day = timeNow.substring(0, 8);
		String minutes = timeNow.substring(8, 14);
		String billNo = "XSCKD-"+day+"-"+minutes+randomNum;
		
		//获取当前登录用户 作为制单人
		SysUser billMaker = sysCacheService.getLoginUser(session.getId());
		//当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String billCreateDate = sdf.format(DateUtil.getSystemTime());
		
		view.setViewName("jxc/saleout/add");
		view.addObject("billNo", billNo);
		view.addObject("billMaker", billMaker);
		view.addObject("billCreateDate", billCreateDate);
		
		return view;
	}

	/**
	 * 销售出库单据   新单保存接口
	 */
	@RequestMapping("/outStorageSave")
	public void outStorageSave(){
		//当前登录用户，用于记录日志
		SysUser operator = sysCacheService.getLoginUser(session.getId());
		//接口请求map
		Map<String,Object> reqMap = getReqMap();
		reqMap.put("operator", operator);
		
		outJson(jxcSalesOutboundService.outStorageSave(reqMap));
	}
	
	/**
	 * 销售出库单据  获取单据详情
	 */
	@RequestMapping("/getSaleoutDetail")
	public ModelAndView getSaleoutDetail(Long billId){
		ModelAndView view = new ModelAndView();
		
		JxcSalesOutbound billDetail  =  jxcSalesOutboundService.get(billId);
		List<Map<String,Object>> goodsList =  jxcBillDetailService.getBillGoods(billDetail.getBillNo());
		view.addObject("billDetail", billDetail);
		view.addObject("billGoods",goodsList);
		view.addObject("loginUnitId", getLoginUnitId());
		view.setViewName("jxc/saleout/detail");

		return view;
	}
	
}
