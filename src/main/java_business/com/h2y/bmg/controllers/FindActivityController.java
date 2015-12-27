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
import com.h2y.bmg.entity.FindActivity;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IFindActivityService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.dict.DictUtil;


/**
 * 发现模块活动信息@Controller

 * 作者：侯飞龙

 * 时间：2014-10-13 上午11:31:51

 * 电子邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/business/findactivity")
@Scope("prototype")
public class FindActivityController  extends BaseController{

	private final static Logger logger = Logger.getLogger(FindActivityController.class);

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected IFindActivityService findActivityService;

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
		view.setViewName("business/findactivity/init");

		SysUser sysUser = getLoginUser();
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}


	/**
	 * 评论列表页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/commentInit")
	public ModelAndView commentInit() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/findactivity/commentInit");

		view.addObject("activityId", request.getParameter("activityId"));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("findactivity_comment"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("findactivity_comment", getLoginUnitId()));
		return view;
	}


	/**
	 * 编辑页面初始化
	 * @param findActivity
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute FindActivity findActivity,@RequestParam String op) {

		ModelAndView view = new ModelAndView();


		if (op.equals("modify")) {

			findActivity = findActivityService.get(findActivity.getId());
			//view.addObject("goodsListJson", findActivityService.getGoodsListJsonActivityId(findActivity.getId()));
			view.addObject("goodsListJson", "[]");
		}


		String imUpUrl = DictUtil.getMainByCode("imageuploadroot_url").getDictValue();
		view.addObject("imUpUrl", imUpUrl);

		view.addObject("findActivity",findActivity);
		view.addObject("loginUnitId",getLoginUnitId());
		view.addObject("op",op);
		view.setViewName("business/findactivity/add");
		view.addObject("validationRules", sysValidationService.getValidationByCode("findactivity_validate"));
		return view;
	}


	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(findActivityService.getGridData(request, getLoginUnitId()));
	}

	/**
	 * 获取活动对应的评论列表
	 * @param activityId 活动Id
	 */
	@RequestMapping(value = "/getCommentList")
	public void getCommentList(long activityId) {

		outJson(findActivityService.getCommentGridData(request, activityId));
	}


	/**
	 * 保存
	 * @param findActivity 
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute FindActivity findActivity,@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "保存成功！");
			SysDictMain sysDictMain = DictUtil.getMainByCode("findactivity_path");

			findActivity.setUnitId( getLoginUnitId());
			findActivity.setActivityStatus(0);

			if (sysDictMain!=null && StringUtils.isNotBlank(sysDictMain.getDictValue())) {

				findActivityService.save(request, op, findActivity, sysDictMain.getDictValue(),getLoginUser());

				if (op.equals("add")) {

					sysLogService.addLog(request, getLoginUser(), "发现活动模块", OpType.add+"", 
							OpRresult.success+"", "活动添加成功！", findActivity.getId()+"", BusinessTableName.findActivity.name);
				}else {

					sysLogService.addLog(request, getLoginUser(), "发现活动模块", OpType.modify+"", 
							OpRresult.success+"", "活动修改成功！", findActivity.getId()+"", BusinessTableName.findActivity.name);
				}
			}else{
				map.put("code", "0");
				map.put("msg", "请维护活动图片上传路径，编码为：findactivity_path！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
		}
		outJson(map);
	}


	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute FindActivity findActivity) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "删除成功！");

			FindActivity findActivity2 = findActivityService.get(findActivity.getId());
			findActivity2.setActivityStatus(-1);
			findActivityService.update(findActivity2);

			sysLogService.addLog(request, getLoginUser(), "发现活动模块", OpType.delete+"", 
					OpRresult.success+"", "活动删除成功！", findActivity.getId()+"", BusinessTableName.findActivity.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "发现活动模块", OpType.delete+"", 
					OpRresult.fail+"", "活动删除失败！", findActivity.getId()+"", BusinessTableName.findActivity.name);
		}
		outJson(map);
	}

	
	/**
	 * pc端预览
	 * @param findActivity
	 * @param op
	 * @return
	 */
	@RequestMapping(value = "/view")
	public ModelAndView view(@ModelAttribute FindActivity findActivity,@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		findActivity = findActivityService.get(findActivity.getId());

		view.addObject("findActivity",findActivity);
		view.addObject("loginUnitId",getLoginUnitId());
		view.addObject("op",op);
		view.setViewName("business/findactivity/view");

		return view;
	}
	
	
	/**
	 * 手机端预览
	 * @param findActivity
	 * @param op
	 * @return
	 */
	@RequestMapping(value = "/phoneView")
	public ModelAndView phoneView(@ModelAttribute FindActivity findActivity,@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		
		String xdkbUrl = SysBaseUtil.XDKB_URL + "?id="+findActivity.getId();//小达快报详情url
	  	String qrCodeUrl = SysBaseUtil.QRCODE_URL;//二维码生成路径
		
	  	view.addObject("xdkbUrl",xdkbUrl);
	  	view.addObject("qrCodeUrl",qrCodeUrl);
		view.addObject("op",op);
		view.setViewName("business/findactivity/findActivityView");

		return view;
	}

}
