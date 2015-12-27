package com.h2y.bmg.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;






import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.IFindServiceUnitDao;
import com.h2y.bmg.entity.FindServiceUnit;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IFindServiceService;
import com.h2y.bmg.services.IFindServiceUnitService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.services.ISysUnitsService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.util.JSONUtil;


/**
 * 类描述：发现服务 @Controller  
 * 作者：侯飞龙
 * 时间：2015年1月7日上午10:44:05
 * 邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/business/findserviceunit")
@Scope("prototype")
public class FindServiceUnitController  extends BaseController{

	private final static Logger logger = Logger.getLogger(FindServiceUnitController.class);

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected ISysUnitsService sysUnitsService;

	@Autowired
	protected IFindServiceService findServiceService;

	@Autowired
	protected IFindServiceUnitService findServiceUnitService;

	@Autowired
	protected IFindServiceUnitDao findServiceUnitDao;


	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/findserviceunit/init");

		SysUser sysUser = getLoginUser();
		view.addObject("unitTreeData", JSONUtil.getJson(sysUnitsService.getUnitTreeData()));

		String serviceType = "1";
		List<Map<String,Object>> treeList = findServiceService.getTreeList(serviceType);
		view.addObject("serviceTreedata",JSONUtil.getJson(treeList));

		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}

	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList(long unitId) {

		outJson(findServiceUnitService.getUnitTreeList(unitId));
	}

	/**
	 * 保存
	 * @param findService 
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save() {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			map.put("code", "1");
			map.put("msg", "保存成功！");

			findServiceUnitService.save(request);
			sysLogService.addLog(request, getLoginUser(), "发现服务单位分配", OpType.add+"", 
					OpRresult.success+"", "分配的服务Id为："+request.getParameter("serviceIds"), request.getParameter("unitId")+"", BusinessTableName.findServiceUnit.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
		}
		outJson(map);
	}


	/**
	 * 移除
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute FindServiceUnit findServiceUnit) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			map.put("code", "1");
			map.put("msg", "保存成功！");

			int flag = findServiceUnitService.delete(findServiceUnit);
			if(flag <= 0){
				map.put("code", "0");
				map.put("msg", "保存失败！");
			}
			sysLogService.addLog(request, getLoginUser(), "发现服务单位分配", OpType.add+"", 
					OpRresult.success+"", "分配的服务Id为："+request.getParameter("serviceIds"), request.getParameter("unitId")+"", BusinessTableName.findServiceUnit.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
		}
		outJson(map);
	}



	/**
	 * 当前公司公共服务初始化页面
	 * @return
	 */
	@RequestMapping(value = "/unitServiceInit")
	public ModelAndView unitServiceInit() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/findserviceunit/unitServiceInit");

		SysUser sysUser = getLoginUser();		

		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		view.addObject("unitId",getLoginUnitId());
		return view;
	}

	/**
	 * 获取当前公司公共服务列表
	 */
	@RequestMapping(value = "/getUnitServiceList")
	public void getUnitServiceList() {

		outJson(findServiceUnitService.getUnitServiceList(request, getLoginUnitId()));

	}



	@RequestMapping(value = "/unitServiceSelectInit")
	public ModelAndView unitServiceSelectInit() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/findserviceunit/serviceSelectInit");

		SysUser sysUser = getLoginUser();		

		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("public_service_select"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		view.addObject("unitId",getLoginUnitId());
		return view;
	}

	/**
	 * 获取当前公司公共服务列表
	 */
	@RequestMapping(value = "/getUnitServiceSelectList")
	public void getUnitServiceSelectList() {

		outJson(findServiceUnitService.getUnitServiceSelectList(request, getLoginUnitId()));

	}


	/**
	 * 保存排序
	 * @param id
	 * @param ord
	 */
	@RequestMapping(value = "/saveOrd")
	public void saveOrd(@RequestParam long id,@RequestParam long ord) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "1");
		map.put("msg", "保存成功！");
		try{						

			FindServiceUnit findServiceUnit = findServiceUnitDao.get(id);
			findServiceUnit.setOrd(ord);
			int flag = findServiceUnitService.update(findServiceUnit);
			if(flag <= 0){
				map.put("code", "0");
				map.put("msg", "保存失败！");
				sysLogService.addLog(request, getLoginUser(), "保存排序", OpType.modify+"", 
						OpRresult.fail+"", "分配的服务Id为："+id, ord+"", BusinessTableName.findServiceUnit.name);
			}else{
				sysLogService.addLog(request, getLoginUser(), "保存排序", OpType.modify+"", 
						OpRresult.success+"", "分配的服务Id为："+id, ord+"", BusinessTableName.findServiceUnit.name);
			}

		}catch(Exception e){
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			sysLogService.addLog(request, getLoginUser(), "保存排序", OpType.modify+"", 
					OpRresult.fail+"", "分配的服务Id为："+id, ord+"", BusinessTableName.findServiceUnit.name);
		}



		outJson(map);
	}


	/**
	 * 保存
	 * @param findService 
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/saveUnitService")
	public void saveUnitService() {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			map.put("code", "1");
			map.put("msg", "保存成功！");

			findServiceUnitService.saveUnitService(request);
			sysLogService.addLog(request, getLoginUser(), "公共服务单位分配", OpType.add+"", 
					OpRresult.success+"", "分配的服务Id为："+request.getParameter("serviceIds"), request.getParameter("unitId")+"", BusinessTableName.findServiceUnit.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			sysLogService.addLog(request, getLoginUser(), "公共服务单位分配", OpType.add+"", 
					OpRresult.fail+"", "分配的服务Id为："+request.getParameter("serviceIds"), request.getParameter("unitId")+"", BusinessTableName.findServiceUnit.name);
		}
		outJson(map);
	}



}
