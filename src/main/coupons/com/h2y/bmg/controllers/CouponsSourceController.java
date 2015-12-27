package com.h2y.bmg.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.h2y.bmg.dao.ICouponsSourceDao;
import com.h2y.bmg.entity.CouponsSource;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.ICouponsSourceService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;


/**
 * 项目名称：h2ybmg2  
 * 类名称：CouponsSourceController  
 * 类描述：优惠券来源 Controller  
 * 创建人：侯飞龙  
 * 创建时间：2015年7月14日 下午5:57:30  
 * 修改人：侯飞龙
 * 修改时间：2015年7月14日 下午5:57:30  
 * 修改备注：  
 * @version
 */
@Controller
@RequestMapping(value = "/business/couponssource")
@Scope("prototype")
public class CouponsSourceController  extends BaseController{

	private final static Logger logger = Logger.getLogger(CouponsSourceController.class);

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected ICouponsSourceDao couponsSourceDao;

	@Autowired
	protected ICouponsSourceService couponsSourceService;

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
		view.setViewName("business/couponssource/init");

		SysUser sysUser = getLoginUser();
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}


	/**
	 * 来源对应优惠劵页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/couponsInit")
	public ModelAndView couponsInit(long sourceId) {

		ModelAndView view = new ModelAndView();

		view.addObject("sourceId", sourceId);
		view.setViewName("business/couponssource/couponsInit");
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("sourceCounpons_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("sourceCounpons_query", this.getLoginUnitId()));
		return view;
	}


	/**
	 * 编辑页面初始化
	 * @param couponsSource
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute CouponsSource couponsSource,@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify") || op.equals("detail")) {

			couponsSource = couponsSourceDao.get(couponsSource.getId());
		}

		view.addObject("couponsSource",couponsSource);
		view.addObject("op",op);
		if (op.equals("detail")) {
			view.setViewName("business/couponssource/detail");
		}else {
			view.setViewName("business/couponssource/add");
			view.addObject("validationRules", sysValidationService.getValidationByCode("couponssource_validate"));
		}
		return view;
	}


	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(couponsSourceService.getGridData(request, getLoginUnitId()));
	}


	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getCouponsList")
	public void getCouponsList(long sourceId) {

		outJson(couponsSourceService.getCouponsGridData(request, this.getLoginUnitId(), sourceId));
	}


	/**
	 * 保存
	 * @param coupons 
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute CouponsSource couponsSource,@RequestParam String op) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			SysUnits sysUnits = this.getLoginUnit();

			map.put("code", "0");
			if (!op.equals("add") && !op.equals("modify")) {
				map.put("msg", "无效操作符op:"+op+"！");
				outJson(map);
				return ;
			}

			Map<String,Object> params = new HashMap<String, Object>();
			params.put("startDate", couponsSource.getStartDate());
			params.put("endDate", couponsSource.getEndDate());
			params.put("sourceCode", couponsSource.getSourceCode());
			params.put("id", couponsSource.getId());
			params.put("op", op);
			params.put("unitId", sysUnits.getId());
			if (couponsSourceDao.getDateCrossRows(params)>0) {

				map.put("msg", "时间有冲突，请调整起止时间！");
				outJson(map);
				return ;
			}

			//每个来源只能维护两个
			if (couponsSourceDao.getCountBySourceCode(params)>=2) {

				map.put("msg", "每个来源最多只能维护两个！");
				outJson(map);
				return ;
			}

			map.put("code", "1");
			map.put("msg", "保存成功！");

			couponsSourceService.save(request, op, couponsSource, sysUnits);
			addSaveLog(op, couponsSource, OpRresult.success+"", "优惠券来源添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, couponsSource, OpRresult.fail+"", "优惠券来源添加失败！");
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
	private void addSaveLog(String op,CouponsSource couponsSource,String opResult,String memo){

		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "优惠券来源", OpType.add+"", 
					opResult, memo, couponsSource.getId()+"", BusinessTableName.couponsSource.name);
		}else {

			sysLogService.addLog(request, getLoginUser(), "优惠券来源", OpType.modify+"", 
					opResult, memo, couponsSource.getId()+"", BusinessTableName.couponsSource.name);
		}
	}


	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute CouponsSource couponsSource) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "删除成功！");

			CouponsSource couponsSource2 = couponsSourceDao.get(couponsSource.getId());
			couponsSource2.setStatus(-1);
			couponsSourceDao.update(couponsSource2);

			sysLogService.addLog(request, getLoginUser(), "优惠券来源", OpType.delete+"", 
					OpRresult.success+"", "优惠券来源删除成功！", couponsSource.getId()+"", BusinessTableName.couponsSource.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "优惠券", OpType.delete+"", 
					OpRresult.fail+"", "优惠券来源删除失败！", couponsSource.getId()+"", BusinessTableName.couponsSource.name);
		}
		outJson(map);
	}


	/**
	 * 添加优惠劵
	 * @return
	 */
	@RequestMapping(value = "/addCoupons")
	public void addCoupons(long sourceId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			couponsSourceService.addCoupons(request, sourceId);
			map.put("code", "1");
			map.put("msg", "添加成功！");
			sysLogService.addLog(request, getLoginUser(), "优惠券来源", OpType.add+"", 
					OpRresult.success+"", "添加优惠劵成功！", sourceId+"", BusinessTableName.couponsSourceRt.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "添加失败！");
			sysLogService.addLog(request, getLoginUser(), "优惠券来源", OpType.add+"", 
					OpRresult.fail+"", "添加优惠劵失败！", sourceId+"", BusinessTableName.couponsSourceRt.name);
		}
		outJson(map);
	}


	/**
	 * 移除优惠劵
	 * @return
	 */
	@RequestMapping(value = "/removeCoupons")
	public void removeCoupons() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			couponsSourceService.removeCoupons(request);
			map.put("code", "1");
			map.put("msg", "添加成功！");
			sysLogService.addLog(request, getLoginUser(), "优惠券来源", "remove", 
					OpRresult.success+"", "优惠劵移除成功！",request.getParameter("ids"), BusinessTableName.couponsSourceRt.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "添加失败！");
			sysLogService.addLog(request, getLoginUser(), "优惠券来源", OpType.delete+"", 
					OpRresult.fail+"", "优惠劵移除失败！", request.getParameter("ids"), BusinessTableName.couponsSourceRt.name);
		}
		outJson(map);
	}


	/**
	 * 优惠券消息推送
	 */
	@RequestMapping(value="/sendCouponsMsg")
	public void sendCouponsMsg(long couponsId){
		Map<String, Object> map = new HashMap<String, Object>();

		try {

			couponsSourceService.sendCouponsMsg(request,couponsId);
			map.put("code", "1");
			map.put("msg", "消息推送成功！");
			sysLogService.addLog(request, getLoginUser(), "优惠券消息推送", "sendCouponsMsg", 
					OpRresult.success+"", "优惠券消息推送成功！",String.valueOf(couponsId), BusinessTableName.coupons.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "消息推送失败！");
			sysLogService.addLog(request, getLoginUser(), "优惠券消息推送","sendCouponsMsg", 
					OpRresult.fail+"", "优惠券消息推送失败！", String.valueOf(couponsId), BusinessTableName.coupons.name);
		}


		outJson(map);
	}


}
