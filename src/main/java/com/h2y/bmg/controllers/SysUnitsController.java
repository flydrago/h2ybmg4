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
import com.h2y.bmg.dao.ISysRoleDao;
import com.h2y.bmg.dao.ISysUnitsDao;
import com.h2y.bmg.dao.ISysUnitsFileDao;
import com.h2y.bmg.dao.IZoneDao;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.Zone;
import com.h2y.bmg.services.ISysUnitsService;
import com.h2y.bmg.services.IUnitSortService;
import com.h2y.dict.DictUtil;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
 * 单位管理url跳转控制类

 * 作者：侯飞龙

 * 时间：2014-10-13 上午11:31:51

 * 电子邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/sys/units")
@Scope("prototype")
public class SysUnitsController extends BaseController{


	private final static Logger logger = Logger.getLogger(SysUnitsController.class);

	@Autowired
	protected ISysUnitsService sysUnitsService;

	@Autowired
	protected ISysRoleDao sysRoleDao;

	@Autowired
	protected ISysUnitsDao sysUnitsDao;

	@Autowired
	protected ISysUnitsFileDao sysUnitsFileDao;

	@Autowired
	protected IUnitSortService unitSortService;

	@Autowired
	protected IZoneDao zoneDao;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
	}

	/**
	 * 平台审核省级  页面初始化
	 * 根据菜单配置 跳转到此方法
	 * unitType 单位类型：0：区域代理（区域唯一）、1：旗舰店、2：省级代理（区域唯一）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/units/init");
//		view.addObject("treedata", JSONUtil.getJson(sysUnitsService.getUnitTreeDataByUnitId(getLoginUnitId())));
		view.addObject("parentId",getLoginUnitId());
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, getLoginUnitId()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		return view;
	}


	/**
	 * 省级审核区域代理 初始化
	 * 根据菜单配置 跳转到此方法
	 * unitType 单位类型：0：区域代理（区域唯一）、1：旗舰店、2：省级代理（区域唯一）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/initunit")
	public ModelAndView initunit() {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/units/init");
		view.addObject("initUnitType",2);
		view.addObject("treedata", JSONUtil.getJson(sysUnitsService.getUnitTreeDataByUnitId(getLoginUnitId())));
		view.addObject("parentId",getLoginUnitId());
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, getLoginUnitId()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		return view;
	}


	/**
	 * 区域代理审核旗舰店  初始化
	 * 根据菜单配置 跳转到此方法
	 * unitType 单位类型：0：区域代理（区域唯一）、1：旗舰店、2：省级代理（区域唯一）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/initshop")
	public ModelAndView initshop() {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/units/init");
		view.addObject("initUnitType",0);
		view.addObject("treedata", JSONUtil.getJson(sysUnitsService.getUnitTreeDataByUnitId(getLoginUnitId())));
		view.addObject("parentId",getLoginUnitId());
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, getLoginUnitId()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		return view;
	}


	/**
	 * 省级代理审核旗舰店  初始化
	 * unitType 单位类型：0：区域代理（区域唯一）、1：旗舰店、2：省级代理（区域唯一）
	 * 根据菜单配置 跳转到此方法
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/provincechecklist")
	public ModelAndView provincechecklist() {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/units/provinceCheckInit");
		view.addObject("unitType",4);
		view.addObject("treedata", JSONUtil.getJson(sysUnitsService.getUnitTreeDataByProvinceId(getLoginUnitId())));
		view.addObject("parentId",getLoginUnitId());
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, getLoginUnitId()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		return view;
	}

	/**
	 * 页面初始化
	 * 审核列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checklist")
	public ModelAndView checklist() {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/units/checklist");
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		return view;
	}

	/**
	 * 区域代理审核旗舰店
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkshoplist")
	public ModelAndView checkshoplist() {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/units/checklist");
		view.addObject("unitType",1);
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		return view;
	}

	/**
	 * 省级代理审核区域代理
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkunitlist")
	public ModelAndView checkunitlist() {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/units/checklist");
		view.addObject("unitType",0);
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		return view;
	}

	/**
	 * 编辑页面初始化
	 * 单位类型：0：区域代理（区域唯一）、1：旗舰店、2：省级代理（区域唯一）
	 * @param sysUnits 
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute SysUnits sysUnits,@RequestParam String op) {
		ModelAndView view = new ModelAndView();

		if (op.equals("modify")) {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("unitId", sysUnits.getId());
			//文件列表
			view.addObject("fileListDataJson", JSONUtil.getJson(sysUnitsFileDao.getListByUnitId(params)));
			sysUnits = sysUnitsDao.get(sysUnits.getId());
		}


		//文件类型，页面上的文件上传按钮在这里配置
		view.addObject("fileTypeList", sysUnitsService.getUnitFileTypeList());
		view.addObject("sysUnits",sysUnits);
		view.addObject("loginUnitId",getLoginUnitId());		
		view.addObject("op",op);
		//域名前缀设为父级域名
		view.addObject("prefixDomain", getLoginUnit().getUnitDomain());
		view.setViewName("sys/units/add");
		view.addObject("validationRules", sysValidationService.getValidationByCode("sysunits_validate"));
		return view;
	}


	/**
	 * 编辑页面初始化
	 * @param sysUnits 
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/check")
	public ModelAndView check(@ModelAttribute SysUnits sysUnits) {

		ModelAndView view = new ModelAndView();
		sysUnits = sysUnitsService.get(sysUnits.getId());

		Map<String,Object> params = new HashMap<String, Object>();
		params.put("unitId", sysUnits.getId());
		//文件列表
		view.addObject("fileListDataJson", JSONUtil.getJson(sysUnitsFileDao.getListByUnitId(params)));
		//文件类型，页面上的文件上传按钮在这里配置
		view.addObject("fileTypeList", sysUnitsService.getUnitFileTypeList());

		//角色列表
		List<Map<String,Object>> roleList = sysRoleDao.getUnitRoleList();
		view.addObject("roleList", roleList);
		view.addObject("loginUnitId", getLoginUnitId());
		view.addObject("sysUnits",sysUnits);
		view.setViewName("sys/units/check");
		//获取经营范围列表
		view.addObject("unitSortDataJson", JSONUtil.getJson(unitSortService.getSortList(sysUnits)));
		return view;
	}


	/**
	 * 得到列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getList")
	public void getList(long parentId) {
		outJson(sysUnitsService.getGridData(getLoginUnitId(),request, parentId));
	}

	/**
	 * 得到列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getZone")
	public void getZone(String op,String code) {

		if (op.equals("code")) {

			outJson(sysUnitsService.getChildZoneList(request, code));
		}else if(op.equals("id")){

			outJson(sysUnitsService.getChildZoneListByPid(Long.valueOf(code)));
		}
	}


	/**
	 * 保存
	 * @param sysDepartment 部门对象
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute SysUnits sysUnits,@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {

			SysDictMain sysDictMain = null;
			if (op.equals("add") || op.equals("modify")) {

				sysDictMain = DictUtil.getMainByCode("unitRegisterFile_path");
				if (sysDictMain==null || sysDictMain.getDictValue()==null || sysDictMain.getDictValue().equals("")) {
					map.put("code", "0");
					map.put("msg", "请维护单位注册的图片存储路径，编码为：unitRegisterFile_path");
					outJson(map);
					return;
				}

				//判断域名是否重复
				map.put("unitDomain", sysUnits.getUnitDomain());
				if (sysUnitsService.isHasSame(op,sysUnits, map)) {
					map.put("code", "0");
					map.put("msg", "该域名已使用,请重新添加！");
					outJson(map);
					return;
				}
				map.clear();
				//判断公司名称是否重复
				map.put("unitName", sysUnits.getUnitName());
				if (sysUnitsService.isHasSame(op, sysUnits,map)) {
					map.put("code", "0");
					map.put("msg", "该单位名称已使用,请重新添加！");
					outJson(map);
					return;
				}
				map.clear();

			}

			//新增
			if (op.equals("add")) {
				sysUnits.setUnitCode(sysUnits.getZoneCode());
				sysUnits.setRegDate(DateUtil.getSystemTime());
				sysUnits.setUnitStatus("register");
				sysUnitsService.add(sysUnits);
				sysUnitsService.saveUnitFile(request, op, sysUnits, sysDictMain.getDictValue());
			}else if(op.equals("modify")){//修改
				SysUnits sysUnits2 = sysUnitsService.get(sysUnits.getId());
				sysUnits.setUnitStatus(sysUnits2.getUnitStatus());
				sysUnits.setS3createdate(sysUnits2.getS3createdate());
				//UnitStatus：register初次审核 rechange再次审核并修改权限 recheck再次审核 pass审核通过  unpass审核不通过
				sysUnitsService.update(sysUnits);
				sysUnitsService.saveUnitFile(request, op, sysUnits, sysDictMain.getDictValue());
			}else if (op.equals("check")) {//审核
				sysUnitsService.checkUnit(request, sysUnits,getLoginUser());
			}
			map.put("code", "1");
			map.put("msg", "保存成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
		}
		outJson(map);
	}






}
