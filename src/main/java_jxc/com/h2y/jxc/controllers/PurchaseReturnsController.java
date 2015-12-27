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
import com.h2y.jxc.entity.JxcPurchaseReturns;
import com.h2y.jxc.services.IJxcBillDetailService;
import com.h2y.jxc.services.IJxcPurchaseReturnsService;
import com.h2y.util.DateUtil;

/**
 * 采购退货单 Controller
 * @author jyd-yfb-02
 */
@Controller
@Scope("prototype")
@RequestMapping("/jxc/purchaseReturn")
public class PurchaseReturnsController extends BaseController{

	@Autowired
	protected IJxcPurchaseReturnsService jxcPurchaseReturnsService;
	
	@Autowired
	protected IJxcBillDetailService jxcBillDetailService;
	
	/**
	 * 采购退货单据 管理页面 初始化
	 * @return
	 */
	@RequestMapping("/init")
	public ModelAndView init(){
		ModelAndView view = new ModelAndView();
		view.setViewName("jxc/purchaseReturns/init");
		
		SysUser sysUser = getLoginUser();
		view.addObject("toolbar", sysButtonService.getMenuButtons(request,getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns",sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByRequest(request,sysUser.getUnitId()));
		view.addObject("unitId", sysUser.getUnitId());
		
		return view;
	}
	
	/**
	 * 采购退货单据 分页获取数据
	 */
	@RequestMapping("/getList")
	public void getList(){
		outJson(jxcPurchaseReturnsService.getGridData(request));
	}
	
	/**
	 * 采购退货单 编辑页面初始化
	 * 操作：添加
	 * @param returns
	 * @param op
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView add(@ModelAttribute JxcPurchaseReturns returnsBill,@RequestParam String op){
		ModelAndView view = new ModelAndView();

		//生产单据编号
		Date today = DateUtil.getSystemTime();
		SimpleDateFormat sdfForNo = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeNow = sdfForNo.format(today);
		int randomNum = (int)((Math.random()*9+1)*1000);
		String day = timeNow.substring(0, 8);
		String minutes = timeNow.substring(8, 14);
		String billNo = "CGTHD-"+day+"-"+minutes+randomNum;
		
		//获取当前登录用户 做为制单人
		SysUser billMaker = sysCacheService.getLoginUser(session.getId());
		//当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String billCreateDate = sdf.format(DateUtil.getSystemTime());
		
		view.setViewName("jxc/purchaseReturns/add");
		view.addObject("billNo", billNo);
		view.addObject("billMaker", billMaker);
		view.addObject("billCreateDate", billCreateDate);
		
		return view;
	}
	
	/**
	 * 采购退货单  新单保存
	 */
	@RequestMapping("/outStorageSave")
	public void outStorageSave(){
		//当前登录用户，用于记录日志
		SysUser operator = sysCacheService.getLoginUser(session.getId());
		//接口请求map
		Map<String,Object> reqMap = getReqMap();
		reqMap.put("operator", operator);
		
		outJson(jxcPurchaseReturnsService.outStorageSave(reqMap));
	}
	
	/**
	 * 获取 采购退货单 单据详情
	 */
	@RequestMapping("/getDetail")
	public ModelAndView getBillDetail(Long billId){
		ModelAndView view = new ModelAndView();
		
		JxcPurchaseReturns billDetail  =  jxcPurchaseReturnsService.get(billId);
		List<Map<String,Object>> goodsList =  jxcBillDetailService.getBillGoods(billDetail.getBillNo());
		view.addObject("billDetail", billDetail);
		view.addObject("billGoods",goodsList);
		view.addObject("loginUnitId", getLoginUnitId());
		view.setViewName("jxc/purchaseReturns/detail");
		
		return view;
	}
}
