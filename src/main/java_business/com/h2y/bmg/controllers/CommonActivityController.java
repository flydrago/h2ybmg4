package com.h2y.bmg.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.h2y.bmg.dao.ICommonActivityDao;
import com.h2y.bmg.dao.ICommonActivityGoodsRtDao;
import com.h2y.bmg.entity.CommonActivity;
import com.h2y.bmg.entity.CommonActivityGoodsRt;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.ICommonActivityService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;


/**
 * 类描述：促销活动Controller类   
 * 作者：侯飞龙
 * 时间：2015年1月7日上午10:44:05
 * 邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/business/commonactivity")
@Scope("prototype")
public class CommonActivityController  extends BaseController{

	private final static Logger logger = Logger.getLogger(CommonActivityController.class);

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected ICommonActivityDao commonActivityDao;

	@Autowired
	protected ICommonActivityService commonActivityService;

	@Autowired
	protected ICommonActivityGoodsRtDao commonActivityGoodsRtDao;


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
		view.setViewName("business/commonactivity/init");

		SysUser sysUser = getLoginUser();
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}


	/**
	 * 编辑页面初始化
	 * @param commonActivity
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute CommonActivity commonActivity,@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify")) {

			commonActivity = commonActivityDao.get(commonActivity.getId());
		}

		view.addObject("commonActivity",commonActivity);
		view.addObject("op",op);
		view.setViewName("business/commonactivity/add");
		view.addObject("validationRules", sysValidationService.getValidationByCode("commonactivity_validate"));
		return view;
	}


	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(commonActivityService.getGridData(request, getLoginUnitId()));
	}

	/**
	 * 保存
	 * @param commonActivity 
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute CommonActivity commonActivity,@RequestParam String op) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("unitId", getLoginUnitId());
			paramsMap.put("startDate", commonActivity.getStartDate());
			paramsMap.put("endDate", commonActivity.getEndDate());
			paramsMap.put("id", commonActivity.getId());
			map.put("code", "1");
			map.put("msg", "保存成功！");

			if (op.equals("add")) {
				SysUser sysUser = this.getLoginUser();
				commonActivity.setUnitId( sysUser.getUnitId());
				commonActivity.setUserId(sysUser.getId());
			}
			commonActivityService.save(op, commonActivity);
			addSaveLog(op, commonActivity, OpRresult.success+"", "活动添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, commonActivity, OpRresult.fail+"", "活动添加失败！");
		}

		outJson(map);
	}


	/**
	 * 添加保存操作日志
	 * @param op
	 * @param commonActivity
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op,CommonActivity commonActivity,String opResult,String memo){

		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "促销活动", OpType.add+"", 
					opResult, memo, commonActivity.getId()+"", BusinessTableName.commonActivity.name);
		}else {

			sysLogService.addLog(request, getLoginUser(), "促销活动", OpType.modify+"", 
					opResult, memo, commonActivity.getId()+"", BusinessTableName.commonActivity.name);
		}
	}


	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute CommonActivity commonActivity) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "删除成功！");

			CommonActivity commonActivity2 = commonActivityDao.get(commonActivity.getId());
			commonActivity2.setStatus(-1);
			commonActivityDao.update(commonActivity2);

			sysLogService.addLog(request, getLoginUser(), "促销活动", OpType.delete+"", 
					OpRresult.success+"", "活动删除成功！", commonActivity.getId()+"", BusinessTableName.commonActivity.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "促销活动", OpType.delete+"", 
					OpRresult.fail+"", "活动删除失败！", commonActivity.getId()+"", BusinessTableName.commonActivity.name);
		}
		outJson(map);
	}
}
