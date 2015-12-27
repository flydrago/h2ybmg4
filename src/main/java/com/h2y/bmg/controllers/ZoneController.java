package com.h2y.bmg.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.IZoneDao;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.Zone;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.services.IZoneService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.util.JSONUtil;
import com.h2y.util.PinyinUtil;

/**
 * 部门管理url跳转控制类

 * 作者：侯飞龙

 * 时间：2014-10-13 上午11:31:51

 * 电子邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/sys/zone")
@Scope("prototype")
public class ZoneController extends BaseController{


	private final static Logger logger = Logger.getLogger(ZoneController.class);

	@Autowired
	protected IZoneService zoneService;
	
    @Autowired
    protected ISysLogService sysLogService;
    
    

	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/zone/init");
		
		SysUnits sysUnits = getLoginUnit();

		List<Map<String, Object>> treeList = zoneService.getTreeList(0l,sysUnits.getZoneCode(),sysUnits.getId());

		//        Map<String, Object> rootMap = new HashMap<String, Object>();
		//        rootMap.put("id", 0);
		//        rootMap.put("text", "地点区域");
		//        treeList.add(rootMap);

		view.addObject("treedata", JSONUtil.getJson(treeList));
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		return view;
	}


	/**
	 * 编辑页面初始化
	 * @param sysDepartment 部门
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute Zone zone,@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify") || op.equals("location")) {

			zone = zoneService.get(zone.getId());

			if (op.equals("location") && !StringUtils.isNotBlank(zone.getLocation())) {

				String prefix = zone.getPreFix();
				if (StringUtils.isNotBlank(prefix) && prefix.contains("_")) {
					zone.setLocation(zoneService.getCurrentName(prefix));
				}else{
					zone.setLocation(zone.getName());
				}
			}
		}
		view.addObject("zone",zone);
		view.addObject("op",op);

		if (op.equals("location")) {
			view.setViewName("sys/zone/location");
		}else {
			view.setViewName("sys/zone/add");
		}
		view.addObject("validationRules", sysValidationService.getValidationByCode("zone_validate"));
		return view;
	}


	/**
	 * 获取列表
	 * @param op grid:表格列，tree:树
	 * @param parentCode 父级Id
	 */
	@RequestMapping(value = "/getList")
	public void getList(@RequestParam String op,@RequestParam long pid) {

		SysUnits sysUnits = getLoginUnit();
		if (op.equals("grid")){

			outJson(zoneService.getGridData(request, pid,sysUnits.getZoneCode(),sysUnits.getId()));
		}else if(op.equals("tree")){
			
			outJson(zoneService.getTreeList(pid,sysUnits.getZoneCode(),sysUnits.getId()));
		}
	}


	/**
	 * 保存
	 * @param sysDepartment 部门对象
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute Zone zone,@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {

			map.put("code", "1");
			map.put("msg", "保存成功！");
			zoneService.save(op, zone);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
		}
		outJson(map);
	}



	/**
	 * 保存
	 * @param sysDepartment 部门对象
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute Zone zone) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "删除成功！");


			if (!zoneService.isHasChild(zone.getId()+"")) {
				
				zoneService.delete(zone.getId());
				sysLogService.addLog(request, getLoginUser(), "区域维护", OpType.delete.toString(), OpRresult.success.toString()
						, "删除成功！", zone.getId()+"", BusinessTableName.Zone.name);
			}else{
				map.put("code", "0");
				map.put("msg", "当前区域有子级不可删除！！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
		}
		outJson(map);
	}
	
	
	
	//获取所有地区列表
	@RequestMapping(value = "/getAll")
	public void getAll(){
		outJson(zoneService.getAll(request,getLoginUnitId()));
	}
	
	
	
	
}
