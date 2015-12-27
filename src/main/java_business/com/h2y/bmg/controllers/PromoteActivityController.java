package com.h2y.bmg.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.h2y.bmg.entity.PromoteActivity;
import com.h2y.bmg.entity.PromoteActivityDetail;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IPromoteActivityDetailService;
import com.h2y.bmg.services.IPromoteActivityService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.dict.DictUtil;


/**
 * 推广活动维护
 * @author sunfj
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value="business/promoteactivity")
public class PromoteActivityController extends BaseController {

	private final static Logger logger = Logger.getLogger(PromoteActivityController.class);

	@Autowired
	protected IPromoteActivityService promoteActivityService;


	@Autowired
	protected IPromoteActivityDetailService promoteActivityDetailService;


	@Autowired
	protected ISysLogService sysLogService;


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
	}


	@InitBinder("promoteActivity")    
	public void initBinder1(WebDataBinder binder) {    
		binder.setFieldDefaultPrefix("promoteActivity.");    
	}    
	@InitBinder("promoteActivityDetail")    
	public void initBinder2(WebDataBinder binder) {    
		binder.setFieldDefaultPrefix("promoteActivityDetail.");    
	}  

	/**
	 * 列表页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/promoteactivity/init");

		SysUser sysUser = getLoginUser();
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}


	/**
	 * 编辑页面初始化
	 * @param promoteActivity
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute PromoteActivity promoteActivity,@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify")) {

			promoteActivity = promoteActivityService.get(promoteActivity.getId());
			PromoteActivityDetail promoteActivityDetail = promoteActivityDetailService.getByPromoteId(promoteActivity.getId());
			//活动细则
			if(null == promoteActivityDetail){
				promoteActivityDetail = new PromoteActivityDetail();
			}
			view.addObject("promoteActivityDetail",promoteActivityDetail);

		}else{
			view.addObject("promoteActivityDetail",new PromoteActivityDetail());
		}

		view.addObject("promoteActivity",promoteActivity);
		view.addObject("op",op);
		view.setViewName("business/promoteactivity/add");
		view.addObject("validationRules", sysValidationService.getValidationByCode("promoteactivity_validate"));
		return view;
	}


	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(promoteActivityService.getGridData(request, getLoginUnitId()));
	}

	/**
	 * 保存
	 * @param promoteActivity 
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute PromoteActivity promoteActivity,@ModelAttribute PromoteActivityDetail promoteActivityDetail, @RequestParam String op) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "1");
		map.put("msg", "保存成功！");

		try{
			//图片路径
			SysDictMain sysDictMain = DictUtil.getMainByCode("promoteActivity_path");

			if (sysDictMain!=null && StringUtils.isNotBlank(sysDictMain.getDictValue())) {

				promoteActivity.setUnitId(getLoginUnitId());
				promoteActivity.setUnitType(getLoginUnit().getUnitType());
				promoteActivity.setZoneCode(getLoginUnit().getZoneCode());


				promoteActivityService.save(request,op, promoteActivity,promoteActivityDetail,sysDictMain.getDictValue());

				addSaveLog(op, promoteActivity, OpRresult.success+"", "推广添加成功！");

			}else{
				map.put("code", "0");
				map.put("msg", "请维护推广活动图片上传路径，编码为：promoteActivity_path！");
			}

		}catch(Exception e){
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, promoteActivity, OpRresult.fail+"", "推广添加失败！");
		}



		outJson(map);
	}




	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute  PromoteActivity promoteActivity) {
		Map<String, Object> map = new HashMap<String, Object>();

		try{

			map.put("code", "1");
			map.put("msg", "删除成功！");

			//推广活动逻辑删除，删除后对应活动细则不做处理
			PromoteActivity promoteActivity2 = promoteActivityService.get(promoteActivity.getId());
			promoteActivity2.setPromoteStatus(-1);
			promoteActivityService.update(promoteActivity2);

			sysLogService.addLog(request, getLoginUser(), "推广活动", OpType.delete+"", 
					OpRresult.success+"", "活动删除成功！", promoteActivity.getId()+"", BusinessTableName.promoteActivity.name);

		}catch(Exception e){
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "推广活动", OpType.delete+"", 
					OpRresult.fail+"", "活动删除失败！", promoteActivity.getId()+"", BusinessTableName.promoteActivity.name);
		}
		outJson(map);
	}

	/**
	 * 查看活动参与人员
	 * @return
	 */
	@RequestMapping(value = "/userRtInit")
	public ModelAndView userRtInit(String promoteId) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/promoteactivity/userRtInit");

		SysUser sysUser = getLoginUser();
		view.addObject("promoteId", promoteId);
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("promote_activity_user_rt"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("promote_activity_user_rt", sysUser.getUnitId()));
		return view;
	}


	/**
	 * 获取参加活动人员列表
	 */
	@RequestMapping(value = "/getUserRtList")
	public void getUserRtList() {

		outJson(promoteActivityService.getUserRtList(request));
	}


	/**
	 * 添加保存操作日志
	 * @param op
	 * @param promoteActivity
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op,PromoteActivity promoteActivity,String opResult,String memo){

		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "推广活动", OpType.add+"", 
					opResult, memo, promoteActivity.getId()+"", BusinessTableName.promoteActivity.name);
		}else {

			sysLogService.addLog(request, getLoginUser(), "推广活动", OpType.modify+"", 
					opResult, memo, promoteActivity.getId()+"", BusinessTableName.promoteActivity.name);
		}
	}

	/**
	 * 选择列表页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectInit")
	public ModelAndView selectInit() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/promoteactivity/selectInit");

		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("promote_select_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("promote_select_query", getLoginUnitId()));
		return view;
	}

	
	/**
	 * 获取选择列表
	 */
	@RequestMapping(value = "/getSelectList")
	public void getSelectList() {

		outJson(promoteActivityService.getSelectGridData(request, getLoginUnitId()));
	}
}
