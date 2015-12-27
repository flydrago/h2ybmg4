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
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.WechatActivity;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.services.IWechatActivityService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.bmg.util.SysBaseUtil.WechatActivityType;
import com.h2y.dict.DictUtil;
import com.h2y.util.DateUtil;


/**
 * 类描述：微活动（大转盘、刮刮卡、砸金蛋等）
 * 作者：侯飞龙
 * 时间：2014年12月17日下午4:59:59
 * 邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/business/wechatactivity")
@Scope("prototype")
public class WechatActivityController  extends BaseController{

	private final static Logger logger = Logger.getLogger(WechatActivityController.class);

	@Autowired
	protected ISysLogService sysLogService;
	
	@Autowired
	protected IWechatActivityService wechatActivityService;

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
		view.setViewName("business/wechatactivity/init");
		
		view.addObject("activityType", request.getParameter("activityType"));
		
		SysUser sysUser = getLoginUser();
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("wechat_activity_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("wechat_activity_search", sysUser.getUnitId()));
		return view;
	}

	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hitUserInit")
	public ModelAndView hitUserInit() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/wechatactivity/hitUserInit");
		view.addObject("activityId", request.getParameter("activityId"));
		
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("hituser_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("hituser_search", getLoginUnitId()));
		return view;
	}
	
	
	/**
	 * 编辑页面初始化
	 * @param wechatActivity
	 * @param op 操作类型，add：添加、modify:修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute WechatActivity wechatActivity,@RequestParam String op) {

		ModelAndView view = new ModelAndView();
		
		if (op.equals("modify")) {
			
			wechatActivity = wechatActivityService.get(wechatActivity.getId());
			view.addObject("prizeListJson", wechatActivityService.getPrizeListByActivityId(wechatActivity.getId()));
		}
		
		view.addObject("wechatActivity",wechatActivity);
		view.addObject("op",op);
		view.setViewName("business/wechatactivity/add");
		view.addObject("validationRules", sysValidationService.getValidationByCode("wechatactivity_validate"));
		return view;
	}


	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {
		
		outJson(wechatActivityService.getGridData(request, getLoginUnitId()));
	}
	
	
	/**
	 * 获取中奖用户列表
	 */
	@RequestMapping(value = "/getHitUserList")
	public void getHitUserList() {
		
		outJson(wechatActivityService.getHitUserGridData(request));
	}
	
	/**
	 * 保存
	 * @param findActivity 
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute WechatActivity wechatActivity,@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			map.put("code", "1");
			map.put("msg", "保存成功！");
			SysDictMain sysDictMain = DictUtil.getMainByCode("wechatactivity_path");
			
			wechatActivity.setUnitId( getLoginUnitId());
			
			String activityType = wechatActivity.getActivityType();
			
			if (!activityType.equals(WechatActivityType.card.name) 
					&& !activityType.equals(WechatActivityType.egg.name) 
					&& !activityType.equals(WechatActivityType.wheel.name)) {
				
				map.put("code", "0");
				map.put("msg", "无效活动类型："+activityType+"！");
			}else {
				
				if (wechatActivity.getEndDate().before(DateUtil.getSystemTime())) {
					
					map.put("code", "0");
					map.put("msg", "活动结束时间不能再当前时间之前！");
					outJson(map);
					return;
				}
				
				if (sysDictMain!=null && StringUtils.isNotBlank(sysDictMain.getDictValue())) {
					
					wechatActivityService.save(request, op, wechatActivity, sysDictMain.getDictValue());
					
					if (op.equals("add")) {
						
						sysLogService.addLog(request, getLoginUser(), "微信活动模块", OpType.add+"", 
								OpRresult.success+"", "活动添加成功！", wechatActivity.getId()+"", BusinessTableName.wechatActivity.name);
					}else {
						
						sysLogService.addLog(request, getLoginUser(), "微信活动模块", OpType.modify+"", 
								OpRresult.success+"", "活动修改成功！", wechatActivity.getId()+"", BusinessTableName.wechatActivity.name);
					}
				}else{
					map.put("code", "0");
					map.put("msg", "请维护微信活动图片上传路径，编码为：wechatactivity_path！");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
		}
		outJson(map);
	}


	/**
	 * 更新状态（删除、启动、暂停）
	 * @return
	 */
	@RequestMapping(value = "/update")
	public void update(@ModelAttribute WechatActivity wechatActivity,String op) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String msgPrefix = "";
		int status = 0;
		if (op.equals("delete")) {
			msgPrefix = "删除";
			status = -1;
		}else if (op.equals("stop")) {
			msgPrefix = "暂停";
			status = 1;
		}else if (op.equals("start")) {
			msgPrefix = "启动";
			status = 0;
		}
		
		try {
			
			map.put("code", "1");
			map.put("msg", msgPrefix+"成功！");
			
			WechatActivity wechatActivity2 = wechatActivityService.get(wechatActivity.getId());
			if (op.equals("delete") && wechatActivity2.getEndDate().after(DateUtil.getSystemTime())) {
				map.put("code", "0");
				map.put("msg", "活动未结束，不可删除！");
			}else {
				
				wechatActivity2.setActivityStatus(status);
				wechatActivityService.update(wechatActivity2);
				
				sysLogService.addLog(request, getLoginUser(), "微信活动模块", OpType.modify+"", 
						OpRresult.success+"", msgPrefix+"成功！", wechatActivity.getId()+"", BusinessTableName.wechatActivity.name);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", msgPrefix+"失败！");
			sysLogService.addLog(request, getLoginUser(), "微信活动模块", OpType.modify+"", 
					OpRresult.fail+"", msgPrefix+"失败！", wechatActivity.getId()+"", BusinessTableName.wechatActivity.name);
		}
		outJson(map);
	}
}
