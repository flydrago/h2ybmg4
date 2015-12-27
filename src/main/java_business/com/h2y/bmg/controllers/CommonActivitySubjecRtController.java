package com.h2y.bmg.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.ICommonActivitySubjectRtDao;
import com.h2y.bmg.entity.CommonActivitySubjectRt;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.ICommonActivityService;
import com.h2y.bmg.services.ICommonActivitySubjectRtService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.util.JSONUtil;


/**
 * 类描述：促销活动主题关联Controller类   
 * 作者：侯飞龙
 * 时间：2015年1月7日上午10:44:05
 * 邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/business/commonactivitysubjectrt")
@Scope("prototype")
public class CommonActivitySubjecRtController  extends BaseController{

	private final static Logger logger = Logger.getLogger(CommonActivitySubjecRtController.class);

	@Autowired
	protected ISysLogService sysLogService;
	
	@Autowired
	protected ICommonActivityService commonActivityService;
	
	@Autowired
	protected ICommonActivitySubjectRtService commonActivitySubjectRtService;
	
	@Autowired
	protected ICommonActivitySubjectRtDao commonActivitySubjectRtDao;

	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/commonactivitysubjectrt/init");

		SysUser sysUser = getLoginUser();
		view.addObject("treedata", JSONUtil.getJson(commonActivityService.getTreeList(sysUser.getUnitId(),1)));
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}
	
	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(commonActivitySubjectRtService.getGridData(request, getLoginUnitId()));
	}
	

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	public void save() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			if (commonActivitySubjectRtDao.getRowsByActivityId(Long.valueOf(request.getParameter("activityId")))>3) {
				map.put("code", "0");
				map.put("msg", "活动下面最多添加4个主题！");
				outJson(map);
				return;
			}
			
			map.put("code", "1");
			map.put("msg", "添加成功！");
			commonActivitySubjectRtService.save(request);
			sysLogService.addLog(request, getLoginUser(), "促销活动主题关联", OpType.add+"", 
					OpRresult.success+"", "添加主题成功，活动Id为："+request.getParameter("activityId"), request.getParameter("subjectIds")+"", BusinessTableName.commonActivitySubjectRt.name);
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
	public void delete(@ModelAttribute CommonActivitySubjectRt commonActivitySubject) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "移除成功！");
			
			commonActivitySubjectRtDao.deleteById(commonActivitySubject.getId());

			sysLogService.addLog(request, getLoginUser(), "促销活动主题关联", OpType.delete+"", 
					OpRresult.success+"", "活动与主题关联移除成功！", commonActivitySubject.getId()+"", BusinessTableName.commonActivitySubjectRt.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "移除失败！");
		}
		outJson(map);
	}
	
	
	
	/**
	 * 更新排序
	 * @return
	 */
	@RequestMapping(value = "/update")
	public void update(@ModelAttribute CommonActivitySubjectRt commonActivitySubject) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "排序成功！");
			CommonActivitySubjectRt commonActivitySubject2 = commonActivitySubjectRtDao.get(commonActivitySubject.getId());
			commonActivitySubject2.setOrd(commonActivitySubject.getOrd());
			commonActivitySubjectRtDao.update(commonActivitySubject2);
			sysLogService.addLog(request, getLoginUser(), "活动与主题关联", OpType.modify+"", 
					OpRresult.success+"", "活动与主题关联排序成功！", commonActivitySubject.getId()+"", BusinessTableName.commonActivitySubjectRt.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "排序失败！");
		}
		outJson(map);
	}
}
