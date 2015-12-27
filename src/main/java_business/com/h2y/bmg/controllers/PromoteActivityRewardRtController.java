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
import com.h2y.bmg.entity.PromoteActivity;
import com.h2y.bmg.entity.PromoteActivityRewardRt;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IPromoteActivityRewardRtService;
import com.h2y.bmg.services.IPromoteActivityService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;



/**
 * 活动奖励 维护
 * @author sunfj
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value="business/promoteactivityrewardrt")
public class PromoteActivityRewardRtController extends BaseController {

	//日志
	private final static Logger logger = Logger.getLogger(PromoteActivityRewardRtController.class);

	@Autowired
	protected IPromoteActivityRewardRtService promoteActivityRewardRtService;

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected IPromoteActivityService promoteActivityService;


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
	public ModelAndView init(String promoteId) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/promoteactivityrewardrt/init");
		view.addObject("promoteId",promoteId);

		//认领数量 如果已认领则不能修改奖励
		PromoteActivity promoteActivity = this.promoteActivityService.get(Long.valueOf(promoteId));
		view.addObject("claimNum",promoteActivity.getClaimNum());

		SysUser sysUser = getLoginUser();
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("promote_activity_reward_rt"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}


	/**
	 * 编辑页面初始化
	 * @param promoteActivityRewardRt
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute PromoteActivityRewardRt promoteActivityRewardRt,@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify")) {
			promoteActivityRewardRt = promoteActivityRewardRtService.get(promoteActivityRewardRt.getId());
			view.addObject("promoteActivityRewardRt",promoteActivityRewardRt);
		}

		view.addObject("promoteActivityRewardRt",promoteActivityRewardRt);
		view.addObject("op",op);
		view.setViewName("business/promoteactivityrewardrt/add");
		view.addObject("validationRules", sysValidationService.getValidationByCode("promoterewardrt_validate"));
		return view;
	}


	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(promoteActivityRewardRtService.getGridData(request));
	}

	/**
	 * 保存
	 * @param promoteActivityRewardRt 
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute PromoteActivityRewardRt promoteActivityRewardRt, @RequestParam String op) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "1");
		map.put("msg", "保存成功！");
		try{						
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("op", op);
			paramsMap.put("rewardTarget", promoteActivityRewardRt.getRewardTarget());
			paramsMap.put("promoteId", promoteActivityRewardRt.getPromoteId());
			paramsMap.put("id", promoteActivityRewardRt.getId());

			//0认领人  1分享人
			Long sameRows = promoteActivityRewardRtService.getSameDataList(paramsMap);
			String dataText = promoteActivityRewardRt.getRewardTarget()==0?"认领人":"分享人";
			if(sameRows>0){
				map.put("code", "0");
				map.put("msg", "保存失败，不能重复维护"+dataText+"奖励！");
			}else{
				promoteActivityRewardRtService.save(request, op, promoteActivityRewardRt);
				addSaveLog(op, promoteActivityRewardRt, OpRresult.success+"", "活动奖励添加成功！");
			}



		}catch(Exception e){
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, promoteActivityRewardRt, OpRresult.fail+"", "活动奖励添加成功！");
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
	private void addSaveLog(String op,PromoteActivityRewardRt promoteActivityRewardRt,String opResult,String memo){

		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "推广活动", OpType.add+"", 
					opResult, memo, promoteActivityRewardRt.getId()+"", BusinessTableName.promoteActivityRewardRt.name);
		}else {

			sysLogService.addLog(request, getLoginUser(), "推广活动", OpType.modify+"", 
					opResult, memo, promoteActivityRewardRt.getId()+"", BusinessTableName.promoteActivityRewardRt.name);
		}
	}




}
