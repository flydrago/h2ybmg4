package com.h2y.bmg.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.ICouponsDao;
import com.h2y.bmg.dao.ICouponsDetailDao;
import com.h2y.bmg.dao.IGoodsMarkDao;
import com.h2y.bmg.dao.IGoodsMarkInfoDao;
import com.h2y.bmg.dao.IGoodsPriceDao;
import com.h2y.bmg.entity.Coupons;
import com.h2y.bmg.entity.CouponsDetail;
import com.h2y.bmg.entity.GoodsMark;
import com.h2y.bmg.entity.GoodsMarkInfo;
import com.h2y.bmg.entity.GoodsPrice;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.ICouponsService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;


/**
 * 项目名称：h2ybmg2  
 * 类名称：CommonActivityController  
 * 类描述：  
 * 创建人：侯飞龙  
 * 创建时间：2015年7月3日 下午3:32:37  
 * 修改人：侯飞龙
 * 修改时间：2015年7月3日 下午3:32:37  
 * 修改备注：  
 * @version
 */
@Controller
@RequestMapping(value = "/business/coupons")
@Scope("prototype")
public class CouponsController  extends BaseController{

	private final static Logger logger = Logger.getLogger(CouponsController.class);

	@Autowired
	protected ISysLogService sysLogService;
	
	@Autowired
	protected ICouponsDao couponsDao;
	
	@Autowired
	protected ICouponsService couponsService;
	
	@Autowired
	protected ICouponsDetailDao couponsDetailDao;

	@Autowired
	protected IGoodsPriceDao goodsPriceDao;
	
	@Autowired
	protected IGoodsMarkInfoDao goodsMarkInfoDao;
	
	@Autowired
	protected IGoodsMarkDao goodsMarkDao;
	
	
	@InitBinder("coupons")    
	public void initBinder1(WebDataBinder binder) {    
		binder.setFieldDefaultPrefix("coupons.");    
	}    
	@InitBinder("couponsDetail")    
	public void initBinder2(WebDataBinder binder) {    
		binder.setFieldDefaultPrefix("couponsDetail.");    
	}  
	
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }
	
	
	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/coupons/init");

		SysUser sysUser = getLoginUser();
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}
	
	
	/**
	 * 认领情况页面初始化
	 * @param couponsCode 优惠券编码
	 * @return
	 */
	@RequestMapping(value = "/claimInit")
	public ModelAndView claimInit(String couponsCode) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/coupons/claimInit");

		view.addObject("couponsCode", couponsCode);
		SysUser sysUser = getLoginUser();
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("couponsClaim_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("couponsClaim_query", sysUser.getUnitId()));
		return view;
	}
	
	
	/**
	 * 选择页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectInit")
	public ModelAndView selectInit() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/coupons/selectInit");

		Enumeration<String> names=(Enumeration<String>)request.getParameterNames();
	    while(names.hasMoreElements()){
	          String name=names.nextElement();
	          view.addObject(name, request.getParameter(name));
	    }
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("couponsSelect_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("couponsSelect_query", this.getLoginUnitId()));
		return view;
	}


	/**
	 * 选择优惠券
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectDialogInit")
	public ModelAndView selectDialogInit() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/coupons/selectDialogInit");

		view.addObject("selectType", request.getParameter("selectType"));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("couponsSelect_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("couponsSelect_query", this.getLoginUnitId()));
		return view;
	}
	
	
	/**
	 * 获取窗口选择列表
	 */
	@RequestMapping(value = "/getSelectDialogList")
	public void getSelectDialogList() {

		outJson(couponsService.getSelectDialogList(request, getLoginUnitId()));
	}
	
	
	/**
	 * 编辑页面初始化
	 * @param coupons
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute Coupons coupons,@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify") || op.equals("detail")) {

			coupons = couponsDao.get(coupons.getId());
			CouponsDetail couponsDetail = couponsDetailDao.getByCouponsCode(coupons.getCouponsCode());
			view.addObject("couponsDetail",couponsDetail);
			
			if (coupons.getLimitGoods()!=null) {
				GoodsPrice goodsPrice = goodsPriceDao.get(coupons.getLimitGoods());
				view.addObject("goodsPrice",goodsPrice);
			}
			
			if (coupons.getIsLimitMark()!=null && coupons.getIsLimitMark().equals(1)) {
				
				
				GoodsMarkInfo goodsMarkInfo = goodsMarkInfoDao.get(Long.valueOf(coupons.getLimitMark()));
				view.addObject("goodsMarkInfo",goodsMarkInfo);
				
				GoodsMark goodsMark = goodsMarkDao.get(goodsMarkInfo.getMarkId());
				view.addObject("goodsMark",goodsMark);
			}
		}

		view.addObject("coupons",coupons);
		view.addObject("op",op);
		if (op.equals("detail")) {
			view.setViewName("business/coupons/detail");
		}else {
			view.setViewName("business/coupons/add");
			view.addObject("validationRules", sysValidationService.getValidationByCode("coupons_validate"));
		}
		return view;
	}


	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(couponsService.getGridData(request, getLoginUnitId()));
	}

	
	/**
	 * 获取认领列表
	 */
	@RequestMapping(value = "/getClaimList")
	public void getClaimList() {

		outJson(couponsService.getClaimGridData(request));
	}
	
	
	/**
	 * 获取选择列表
	 */
	@RequestMapping(value = "/getSelectList")
	public void getSelectList() {

		outJson(couponsService.getSelectGridData(request, getLoginUnitId()));
	}
	
	
	/**
	 * 保存
	 * @param coupons 
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute Coupons coupons,@ModelAttribute CouponsDetail couponsDetail,@RequestParam String op) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			map.put("code", "1");
			map.put("msg", "保存成功！");
			couponsService.save(request,op, coupons,couponsDetail,this.getLoginUnit());
			addSaveLog(op, coupons, OpRresult.success+"", "优惠券添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, coupons, OpRresult.fail+"", "优惠券添加失败！");
		}
		
		outJson(map);
	}
	
	
	/**
	 * 添加保存操作日志
	 * @param op
	 * @param coupons
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op,Coupons coupons,String opResult,String memo){
		
		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "优惠券", OpType.add+"", 
					opResult, memo, coupons.getId()+"", BusinessTableName.coupons.name);
		}else {

			sysLogService.addLog(request, getLoginUser(), "优惠券", OpType.modify+"", 
					opResult, memo, coupons.getId()+"", BusinessTableName.coupons.name);
		}
	}


	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute Coupons coupons) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "删除成功！");

			Coupons coupons2 = couponsDao.get(coupons.getId());
			coupons2.setStatus(-1);
			couponsDao.update(coupons2);
			
			sysLogService.addLog(request, getLoginUser(), "优惠券", OpType.delete+"", 
					OpRresult.success+"", "优惠券删除成功！", coupons.getId()+"", BusinessTableName.coupons.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "优惠券", OpType.delete+"", 
					OpRresult.fail+"", "优惠券删除失败！", coupons.getId()+"", BusinessTableName.coupons.name);
		}
		outJson(map);
	}
}
