package com.h2y.jxc.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.ISysCacheService;
import com.h2y.jxc.entity.JxcPurchaseReceipts;
import com.h2y.jxc.services.IJxcBillDetailService;
import com.h2y.jxc.services.IJxcContactsUnitsService;
import com.h2y.jxc.services.IJxcPurchaseReceiptsService;
import com.h2y.jxc.services.IJxcStorehouseService;
import com.h2y.util.DateUtil;

/**
 * 采购入库单  Controller
 * @author jyd-yfb-02
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/jxc/purchase")
public class PurchaseController extends BaseController{

	@Autowired
	private IJxcPurchaseReceiptsService jxcPurchaseReceiptsService;
	
	@Autowired
	private IJxcBillDetailService jxcBillDetailService;
	
	@Autowired
	private IJxcContactsUnitsService jxcContactsUnitsService;
	
	@Autowired
	private IJxcStorehouseService jxcStorehouseService;
	
	@Autowired
	private ISysCacheService sysCacheService;
	
	private static Logger logger = Logger.getLogger(PurchaseController.class);
	
	/**
	 * 采购入库单 管理页 初始化
	 */
	@RequestMapping("/init")
	public String init(Model model){
		
		SysUser sysUser = getLoginUser();

		model.addAttribute("toolbar", sysButtonService.getMenuButtons(request,getLoginSysRoleId(), sysUser));
		model.addAttribute("gridComluns",sysGridColumnsService.getGridColumsByRequest(request));
		model.addAttribute("conditionHtml",sysQueryItemService.getConditionHtmlByRequest(request,sysUser.getUnitId()));
		model.addAttribute("unitId", sysUser.getUnitId());
		
		return "jxc/purchase/init";
	}
	
	/**
	 * 采购入库单 列表
	 */
	@RequestMapping("/getList")
	public void getList(){
		outJson(jxcPurchaseReceiptsService.getGridData(request));
	}
	
	/**
	 * 采购入库单据保存
	 */
	@RequestMapping("/inStorageSave")
	public void inStorageSave(){
		logger.info("采购入库单保存");
		
		//当前登录用户，用于记录日志
		SysUser operator = sysCacheService.getLoginUser(session.getId());
		//接口请求map
		Map<String,Object> reqMap = getReqMap();
		reqMap.put("operator", operator);
		
		outJson(jxcPurchaseReceiptsService.inStorageSave(reqMap));
	}

	/**
	 * 编辑页面初始化
	 * 操作：添加
	 * @param receipts
	 * @param op
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView add(@ModelAttribute JxcPurchaseReceipts receipts,@RequestParam String op){
		ModelAndView view = new ModelAndView();

		//生产单据编号
		Date today = DateUtil.getSystemTime();
		SimpleDateFormat sdfForNo = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeNow = sdfForNo.format(today);
		int randomNum = (int)((Math.random()*9+1)*1000);
		String day = timeNow.substring(0, 8);
		String minutes = timeNow.substring(8, 14);
		String billNo = "CGRKD-"+day+"-"+minutes+randomNum;
		
		//获取当前登录用户 作为制单人
		SysUser billMaker = sysCacheService.getLoginUser(session.getId());
		//当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String billCreateDate = sdf.format(DateUtil.getSystemTime());
		
		view.setViewName("jxc/purchase/add");
		view.addObject("billNo", billNo);
		view.addObject("billMaker", billMaker);
		view.addObject("billCreateDate", billCreateDate);
		
		return view;
	}
	
	/**
	 * 查询单据详情
	 * @param billId
	 */
	@RequestMapping("/getReceiptsDetail")
	public ModelAndView getReceiptsDetail(Long billId){
		
		ModelAndView view = new ModelAndView();
		JxcPurchaseReceipts billDetail  =  jxcPurchaseReceiptsService.get(billId);
		List<Map<String,Object>> goodsList =  jxcBillDetailService.getBillGoods(billDetail.getBillNo());
		view.addObject("billDetail", billDetail);
		view.addObject("billGoods",goodsList);
		view.addObject("loginUnitId", getLoginUnitId());
		view.setViewName("jxc/purchase/detail");

		return view;
	}
	
	/**
	 * 单据冲账
	 */
	@RequestMapping("/billStrikeBalance")
	public void billStrikeBalance(){
		//获取当前登录用户
		SysUser billMaker = getLoginUser();
		//请求参数Map
		Map<String,Object> postMap = getPostMap();
		postMap.put("billMaker", billMaker);
		
		outJson(jxcPurchaseReceiptsService.billStrikeBalance(postMap));
	}
}
