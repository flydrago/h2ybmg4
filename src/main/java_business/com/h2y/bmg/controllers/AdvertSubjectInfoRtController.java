package com.h2y.bmg.controllers;

import java.util.Enumeration;
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
import com.h2y.bmg.dao.IAdvertSubjectInfoRtDao;
import com.h2y.bmg.entity.AdvertSubject;
import com.h2y.bmg.entity.AdvertSubjectInfoRt;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IAdvertSubjectInfoRtService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;

/**
 * 项目名称：h2ybmg2  
 * 类名称：AdvertSubjectController  
 * 类描述：广告主题Controller  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月8日 上午10:31:30  
 * 修改人：侯飞龙
 * 修改时间：2015年4月8日 上午10:31:30  
 * 修改备注：  
 * @version
 */
@Controller
@RequestMapping(value = "/business/advertsubjectinfort")
@Scope("prototype")
public class AdvertSubjectInfoRtController  extends BaseController{

	private final static Logger logger = Logger.getLogger(AdvertSubjectInfoRtController.class);

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected IAdvertSubjectInfoRtService advertSubjectInfoRtService;

	@Autowired
	protected IAdvertSubjectInfoRtDao advertSubjectInfoRtDao;

	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/activityInit")
	public ModelAndView activityInit(@ModelAttribute AdvertSubject advertSubject) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/advertsubjectinfort/activityInit");
		SysUser sysUser = getLoginUser();

		view.addObject("unitId",getLoginUnitId());
		view.addObject("subjectId", advertSubject.getId());
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("advertsubjectinfort_grid"));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByCode("advertsubjectinfort_query", this.getLoginUnitId()));
		return view;
	}


	/**
	 * 优惠劵页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/couponsInit")
	public ModelAndView couponsInit(@ModelAttribute AdvertSubject advertSubject) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/advertsubjectinfort/couponsInit");
		SysUser sysUser = getLoginUser();

		view.addObject("unitId",getLoginUnitId());
		view.addObject("subjectId", advertSubject.getId());
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("sourceCounpons_grid"));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByCode("sourceCounpons_query", this.getLoginUnitId()));
		return view;
	}

	/**
	 * 图片页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/imgInit")
	public ModelAndView imgInit(@ModelAttribute AdvertSubject advertSubject) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/advertsubjectinfort/imgInit");
		SysUser sysUser = getLoginUser();

		view.addObject("unitId",getLoginUnitId());
		view.addObject("subjectId", advertSubject.getId());
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("imgstorageShow_grid"));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByCode("imgstorageShow_query", this.getLoginUnitId()));
		return view;
	}


	/**
	 * 分享图片页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/shareImgInit")
	public ModelAndView shareImgInit(@ModelAttribute AdvertSubject advertSubject) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/advertsubjectinfort/shareImgInit");
		SysUser sysUser = getLoginUser();

		view.addObject("unitId",getLoginUnitId());
		view.addObject("subjectId", advertSubject.getId());
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("imgstorageShow_grid"));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByCode("imgstorageShow_query", this.getLoginUnitId()));
		return view;
	}

	/**
	 * 获取分享图片列表
	 */
	@RequestMapping(value = "/getShareImgList")
	public void getShareImgList() {

		outJson(advertSubjectInfoRtService.getGridData(request, getLoginUnit()));
	}

	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectActivityInit")
	public ModelAndView selectActivityInit(Long subjectId) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/advertsubjectinfort/selectActivityInit");
		Enumeration<String> names=(Enumeration<String>)request.getParameterNames();
		while(names.hasMoreElements()){
			String name=names.nextElement();
			view.addObject(name, request.getParameter(name));
		}
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("select_activity_grid"));
		return view;
	}

	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(advertSubjectInfoRtService.getGridData(request, getLoginUnit()));
	}

	/**
	 * 获取促销活动选择列表
	 */
	@RequestMapping(value = "/getActivityList")
	public void getActivityList() {

		outJson(advertSubjectInfoRtService.getActivityGridData(request, getLoginUnit()));
	}


	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	public void save() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "保存成功！");
			advertSubjectInfoRtService.save(request);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
		}
		outJson(map);
	}



	/**
	 * 删除
	 * @param advertColumn 广告栏位
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute AdvertSubjectInfoRt advertSubjectInfoRt) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "移除成功！");
			AdvertSubjectInfoRt advertSubjectInfoRt2 = advertSubjectInfoRtDao.get(advertSubjectInfoRt.getId());
			advertSubjectInfoRt2.setStatus(-1);
			advertSubjectInfoRtDao.update(advertSubjectInfoRt2);

			sysLogService.addLog(request, getLoginUser(), "广告主题信息", OpType.delete+"", 
					OpRresult.success+"", "广告主题信息移除成功！", advertSubjectInfoRt.getId()+"", BusinessTableName.advertSubjectInfoRt.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "移除失败！");
			sysLogService.addLog(request, getLoginUser(), "广告主题信息", OpType.delete+"", 
					OpRresult.fail+"", "广告主题信息移除失败！", advertSubjectInfoRt.getId()+"", BusinessTableName.advertSubjectInfoRt.name);
		}
		outJson(map);
	}


	/**
	 * 更新排序
	 */
	@RequestMapping(value = "/updateOrd")
	public void updateOrd(@ModelAttribute AdvertSubjectInfoRt advertSubjectInfoRt) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "更新成功！");
			AdvertSubjectInfoRt advertSubjectInfoRt2 = advertSubjectInfoRtDao.get(advertSubjectInfoRt.getId());
			advertSubjectInfoRt2.setOrd(advertSubjectInfoRt.getOrd());
			advertSubjectInfoRtDao.update(advertSubjectInfoRt2);

			sysLogService.addLog(request, getLoginUser(), "广告主题信息", "updateOrd", 
					OpRresult.success+"", "广告主题信息更新排序成功！", advertSubjectInfoRt.getId()+"", BusinessTableName.advertSubjectInfoRt.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "更新失败！");
			sysLogService.addLog(request, getLoginUser(), "广告主题信息", "updateOrd", 
					OpRresult.fail+"", "广告主题信息更新排序失败！", advertSubjectInfoRt.getId()+"", BusinessTableName.advertSubjectInfoRt.name);
		}
		outJson(map);
	}
}
