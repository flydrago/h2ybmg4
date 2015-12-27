package com.h2y.jxc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.services.ISysDialogService;
import com.h2y.util.JSONUtil;

/**
 * 各单据 选择商品时  商品列表接口
 * @author jyd-yfb-02
 */
@Controller
@Scope("prototype")
@RequestMapping("jxc/billDetail")
public class ChooseBillDetailController extends BaseController{

	@Autowired
	private ISysDialogService sysDialogService;
	
	@RequestMapping("/goodsSelectInit")
	public ModelAndView goodsSelectInit(){
		ModelAndView view = new ModelAndView();
		
		view.setViewName("jxc/billCommon/selectBillGoodsInit");
		view.addObject("selectType", request.getParameter("selectType"));
		view.addObject("treedata",JSONUtil.getJson(sysDialogService.getGroodsTypeTreeData()));
		
		return view;
	}
	
	
	@RequestMapping("/getGoodsList")
	public void getGoodsList(){
		outJson(sysDialogService.getGoodsListForJxc(request, getLoginUnitId()));
	}
}
