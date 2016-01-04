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
import com.h2y.bmg.dao.ISysDepartmentDao;
import com.h2y.bmg.dao.ISysShopFileDao;
import com.h2y.bmg.dao.ISysShopInfoDao;
import com.h2y.bmg.entity.SysDepartment;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysShopInfo;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.services.ISysDepartmentService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.services.ISysUnitsService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.dict.DictUtil;
import com.h2y.util.JSONUtil;

/**
 * 部门管理url跳转控制类

 * 作者：侯飞龙

 * 时间：2014-10-13 上午11:31:51

 * 电子邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/sys/department")
@Scope("prototype")
public class SysDepartmentController extends BaseController{



	private final static Logger logger = Logger.getLogger(SysDepartmentController.class);

	@Autowired
	protected ISysDepartmentService sysDepartmentService;

	@Autowired
	protected ISysDepartmentDao sysDepartmentDao;

	@Autowired
	protected ISysUnitsService sysUnitsService;

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected ISysShopInfoDao sysShopInfoDao;


	@Autowired
	protected ISysShopFileDao sysShopFileDao;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
	}


	@InitBinder("sysDepartment")    
	public void initBinder1(WebDataBinder binder) {    
		binder.setFieldDefaultPrefix("sysDepartment.");    
	}    
	@InitBinder("sysShopInfo")    
	public void initBinder2(WebDataBinder binder) {    
		binder.setFieldDefaultPrefix("sysShopInfo.");    
	}

	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/department/init");

		//得到单位Id
		long unitId = getLoginUnitId();

		List<Map<String, Object>> treeList = sysDepartmentService.getChildTreeData(unitId,0l);

		SysUnits sysUnits = sysUnitsService.get(unitId);
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("id", 0);
		rootMap.put("text", sysUnits.getUnitName());
		treeList.add(rootMap);
		view.addObject("treedata", JSONUtil.getJson(treeList));

		//        view.addObject("toolbar",sysButtonService.getMenuButtons(request,getLoginUnitId(),getLoginUserId()));
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
	public ModelAndView add(@ModelAttribute SysDepartment sysDepartment,@ModelAttribute SysShopInfo sysShopInfo,@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify")) {
			sysDepartment = sysDepartmentService.get(sysDepartment.getId());
			sysShopInfo =  sysShopInfoDao.getByShopId(sysDepartment.getId());
			if(null != sysShopInfo){
				List<Map<String, Object>> fileDataList = sysShopFileDao.getListByShopId(sysShopInfo.getShopId());
				if(null != fileDataList && !fileDataList.isEmpty() ){
					view.addObject("fileDataListJson", JSONUtil.getJson(fileDataList));
				}else{
					view.addObject("fileDataListJson", "[]");
				}

			}else{
				view.addObject("fileDataListJson", "[]");
				sysShopInfo =  new SysShopInfo();
			}

		}

		if (sysDepartment.getDeptType()==1) {
			view.setViewName("sys/department/shopadd");
			sysShopInfo.setApproveLevel(5);
			view.addObject("validationRules", sysValidationService.getValidationByCode("sysshop_validate"));
		}else{
			view.setViewName("sys/department/add");
			view.addObject("validationRules", sysValidationService.getValidationByCode("sysdepartment_validate"));
		}
		view.addObject("sysDepartment",sysDepartment);
		view.addObject("sysShopInfo",sysShopInfo);
		view.addObject("op",op);
		view.addObject("unitType",getLoginUnit().getUnitType());
		return view;
	}


	/**
	 * 获取列表
	 * @param op grid:表格列，tree:树
	 * @param parentCode 父级Id
	 */
	@RequestMapping(value = "/getList")
	public void getList(@RequestParam String op,@RequestParam long pid) {

		if (op.equals("grid")){

			outJson(sysDepartmentService.getGridData(request,getLoginUnitId(),pid));
		}else if(op.equals("tree")){
			outJson(sysDepartmentService.getChildTreeData(getLoginUnitId(),pid));
		}
	}

	/**
	 * 保存
	 * @param sysDepartment 部门对象
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute SysDepartment sysDepartment,@ModelAttribute SysShopInfo sysShopInfo,@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();

		long unitId = getLoginUnitId();
		sysDepartment.setUnitId(unitId);
		sysDepartment.setIfDelete(0l);

		try {
			if(sysDepartment.getDeptType()==1){
				SysDictMain logoData = DictUtil.getMainByCode("shopLogoData_path");
				SysDictMain picData = DictUtil.getMainByCode("shopPicData_path");

				if (null == logoData || null == logoData.getDictValue()
						|| "".equals(logoData.getDictValue())) {

					map.put("code", "0");
					map.put("msg", "请在字典中维护门店Logo图片上传路径，编码为：shopLogoData_path！");
					outJson(map);
					return;
				}

				if (null == picData || null == picData.getDictValue()
						|| "".equals(picData.getDictValue())) {

					map.put("code", "0");
					map.put("msg", "请在字典中维护门店图片上传路径，编码为：shopPicData_path！");
					outJson(map);
					return;
				}
				
				if (sysDepartment.getReverse1().equals("virtual")) {
					
					Map<String,Object> params = new HashMap<String, Object>();
					params.put("unitId", unitId);
					if (op.equals("modify")) {
						params.put("id", sysDepartment.getId());
					}

					if (sysDepartmentDao.getVirtualShopRows(params)>0) {
						map.put("code", "0");
						map.put("msg", "只能有一个虚拟门店！");
						outJson(map);
						return;
					}
				}
			}


			map.put("code", "1");
			map.put("msg", "保存成功！");
			sysDepartmentService.save(request,sysDepartment,sysShopInfo, op);
			String memo = op.equals("add")?"部门添加":"部门修改";
			sysLogService.addLog(request, getLoginUser(), "部门维护", op, OpRresult.success.toString(),
					memo, sysDepartment.getId()+"", BusinessTableName.SysDepartment.name);
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
	public void delete(@ModelAttribute SysDepartment sysDepartment) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("code", "1");
			map.put("msg", "删除成功！");

			if (sysDepartmentService.isHasChild(sysDepartment)){
				map.put("code", "0");
				map.put("msg", "当前部门有子级，不可删除！");
			}else if(sysDepartmentDao.getUserRowsByDeptId(sysDepartment.getId())>0){
				map.put("code", "0");
				map.put("msg", "当前部门有用户，不可删除！");
			}else{

				sysDepartmentService.deleteDepartment(sysDepartment);
				sysLogService.addLog(request, getLoginUser(), "部门维护", OpType.delete.toString(), 
						OpRresult.success.toString(), "部门删除", sysDepartment.getId()+"", BusinessTableName.SysDepartment.name);

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
		}
		outJson(map);
	}



}
