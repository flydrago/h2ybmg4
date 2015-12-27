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
import com.h2y.jxc.entity.JxcOverflow;
import com.h2y.jxc.entity.JxcSalesReturns;
import com.h2y.jxc.services.IJxcBillDetailService;
import com.h2y.jxc.services.IJxcSalesReturnsService;
import com.h2y.util.DateUtil;

/**
 *	销售退货单 Controller 
 * @author jyd-yfb-02
 */
@Controller
@Scope("prototype")
@RequestMapping("/jxc/salesReturn")
public class SalesReturnsController extends BaseController{

	@Autowired
	protected IJxcSalesReturnsService jxcSalesReturnsService;
	
	@Autowired
	protected IJxcBillDetailService jxcBillDetailService;
	
	@RequestMapping("/init")
	public ModelAndView init(){
		ModelAndView view = new ModelAndView();
		view.setViewName("jxc/salesReturns/init");
		
		SysUser sysUser = getLoginUser();
		view.addObject("toolbar", sysButtonService.getMenuButtons(request,getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns",sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByRequest(request,sysUser.getUnitId()));
		view.addObject("unitId", sysUser.getUnitId());
		
		return view;
	}
	
	@RequestMapping("/getList")
	public void getList(){
		outJson(jxcSalesReturnsService.getGridData(request));
	}
	
	@RequestMapping("/add")
	public ModelAndView add(@ModelAttribute JxcOverflow overflowBill,@RequestParam String op ){
		ModelAndView view = new ModelAndView();
		
		//生产单据编号
		Date today = DateUtil.getSystemTime();
		SimpleDateFormat sdfForNo = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeNow = sdfForNo.format(today);
		int randomNum = (int) ((Math.random()*9+1)*1000);
		String day = timeNow.substring(0, 8);
		String minutes = timeNow.substring(8, 14);
		String billNo = "XSTHD-"+day+"-"+minutes+randomNum;
		
		//获取当前登录用户 作为制单人
		SysUser billMaker = sysCacheService.getLoginUser(session.getId());
		//当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String billCreateDate = sdf.format(DateUtil.getSystemTime());
		
		view.setViewName("jxc/salesReturns/add");
		view.addObject("billNo", billNo);
		view.addObject("billMaker", billMaker);
		view.addObject("billCreateDate", billCreateDate);
		
		return view;
	}
	
	/**
	 * 进销存  销售退货单  新单保存接口
	 */
	@RequestMapping("/save")
	public void save(){
		
		//当前登录用户，用于记录日志
		SysUser operator = sysCacheService.getLoginUser(session.getId());
		//接口请求map
		Map<String,Object> reqMap = getReqMap();
		reqMap.put("operator", operator);
		
		outJson(jxcSalesReturnsService.save(reqMap));
	}
	
	/**
	 * 进销存 销售退货单  获取单据详情
	 */
	@RequestMapping("/getDetail")
	public ModelAndView getOverflowBillDetail(Long billId){
		ModelAndView view = new ModelAndView();
		
		JxcSalesReturns billDetail  =  jxcSalesReturnsService.get(billId);
		List<Map<String,Object>> goodsList =  jxcBillDetailService.getBillGoods(billDetail.getBillNo());
		view.addObject("billDetail", billDetail);
		view.addObject("billGoods",goodsList);
		view.addObject("loginUnitId", getLoginUnitId());
		view.setViewName("jxc/salesReturns/detail");
		
		return view;
	}
}