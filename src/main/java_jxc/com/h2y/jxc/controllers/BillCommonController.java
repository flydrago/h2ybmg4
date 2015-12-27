package com.h2y.jxc.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.IStorehouseDao;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IStorehouseService;
import com.h2y.bmg.services.ISysDepartmentService;
import com.h2y.bmg.services.ISysUnitsService;
import com.h2y.bmg.services.ISysUserService;
import com.h2y.jxc.basic.JxcKeys.ContactsKeys;
import com.h2y.jxc.services.IJxcAuditLogService;
import com.h2y.jxc.services.IJxcBillAuditService;
import com.h2y.jxc.services.IJxcContactsAccountService;
import com.h2y.jxc.services.IJxcContactsUnitsService;
import com.h2y.jxc.services.IJxcStorehouseService;
import com.h2y.spring.IocUtil;
import com.h2y.util.JSONUtil;

/**
 * 单据通用 Controller
 * @author jyd-yfb-02
 */
@Controller
@Scope("prototype")
@RequestMapping("jxc/common")
public class BillCommonController extends BaseController{

	@Autowired
	protected IJxcContactsUnitsService jxcContactsUnitsService;
	
	@Autowired
	protected IJxcStorehouseService jxcStorehouseService;
	
	@Autowired
	protected ISysDepartmentService sysDepartmentService;
	
	@Autowired
	protected ISysUnitsService sysUnitsService;
	
	@Autowired
	protected ISysUserService sysUserService;
	
	@Autowired
	protected IStorehouseService storehouseService;
	
	@Autowired
	protected IStorehouseDao storehouseDao;
	
	@Autowired
	protected IJxcContactsAccountService jxcContactsAccountService;
	
	@Autowired
	protected IJxcAuditLogService jxcAuditLogService;
	
	/**
	 * 收支账户选择 对话窗初始化
	 * @return
	 */
	@RequestMapping("/selectUnitAccountInit")
	public ModelAndView selectUnitAccountInit(@RequestParam String selectType){
		ModelAndView view = new ModelAndView();
		view.setViewName("jxc/billCommon/selectUnitAccountInit");
		view.addObject("selectType", request.getParameter("selectType"));
		view.addObject("gridComluns", sysGridColumnsService
				.getGridColumsByCode("unitAccount_grid"));
		
		return view;
	}
	
	/**
	 * 收支账户列表
	 */
	@RequestMapping("/getAccountList")
	public void getAccountList(){
		outJson(jxcContactsAccountService.getUnitAccountList(request));
	}
	
	/**
	 * 供应商选择 对话窗初始化
	 * @return
	 * 
	 */
	@RequestMapping("/selectContactUnitInit")
	public ModelAndView selectContactUnitInit(@RequestParam String unitType){
		ModelAndView view = new ModelAndView();
		view.setViewName("jxc/billCommon/selectContactUnitInit");
		
		view.addObject("unitType", unitType);
		view.addObject("selectType", request.getParameter("selectType"));
		view.addObject("gridComluns", sysGridColumnsService
				.getGridColumsByCode("contactUnit_grid"));
		
		return view;
	}
	
	/**
	 * 供应商列表
	 * @return
	 */
	@RequestMapping("/getSupplierList")
	public void getSupplierList(){
		outJson(jxcContactsUnitsService.getSelectGridData(request,ContactsKeys.supplierType.value()));
	}
	
	/**
	 * 客户列表
	 * @return
	 */
	@RequestMapping("/getCustomerList")
	public void getCustomerList(){
		outJson(jxcContactsUnitsService.getSelectGridData(request, ContactsKeys.customerType.value()));
	}
	
	/**
	 * 选择经手人 页面初始化
	 * @return
	 */
	@RequestMapping("/selectBrokerInit")
	public ModelAndView selectBrokerInit(){
		ModelAndView view = new ModelAndView();
		
		view.setViewName("jxc/billCommon/selectBrokerInit");

		//得到单位Id
		long unitId = getLoginUnitId();

		List<Map<String, Object>> treeList = sysDepartmentService.getChildTreeData(unitId,0l);

		SysUnits sysUnits = sysUnitsService.get(unitId);
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("id", 0);
		rootMap.put("code", sysUnits.getId());
		rootMap.put("text", sysUnits.getUnitName());
		treeList.add(rootMap);
		
		view.addObject("selectType", request.getParameter("selectType"));
		view.addObject("treedata", JSONUtil.getJson(treeList));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("broker_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, unitId));
		return view;
	}
	
	/**
	 * 根据部门ID 获取员工列表
	 * @param deptId 部门Id
	 */
	@RequestMapping(value = "/getBrokerList")
	public void getBrokerList(@RequestParam long deptId) {
		outJson(sysUserService.getGridData(request, deptId));
	}
	
	/**
	 * 选择仓库页面初始化
	 * @return
	 */
	@RequestMapping("/selectStorageInit")
	public ModelAndView selectStorageInit(){
		ModelAndView view = new ModelAndView();
		
		view.setViewName("jxc/billCommon/selectStorageInit");

		SysUser sysUser = getLoginUser();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitId", sysUser.getUnitId());
		List<Map<String, Object>> shopList = storehouseDao.getShopTreeList(params);
		
		view.addObject("selectType",request.getParameter("selectType"));
		view.addObject("treedata", JSONUtil.getJson(shopList));
		view.addObject("sysUnits", getLoginUnit());
		view.addObject("gridComluns",sysGridColumnsService.getGridColumsByCode("storage_grid"));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByRequest(request,sysUser.getUnitId()));
		
		return view;
	}
	
	/**
	 * 获取仓库列表
	 */
	@RequestMapping("/getStorageList")
	public void getStorageList() {
		outJson(storehouseService.getGridData(request, getLoginUnitId()));
	}
	
	
	/**
	 * 单据审核
	 * @param auditBean //单据审核BeanName
	 */
	@RequestMapping("/billAudit")
	public ModelAndView billAudit(@RequestParam String auditBean){
		ModelAndView view = new ModelAndView();
		view.setViewName("jxc/billCommon/billAudit");
		
		view.addObject("auditBean", auditBean);
		view.addObject("billId", request.getParameter("id"));
		view.addObject("billNo", request.getParameter("billNo"));
		view.addObject("op",request.getParameter("op"));
		return view;
	}
	
	/**
	 * 单据审核保存接口
	 */
	@RequestMapping("/billAuditSave")
	public void billAuditSave(@RequestParam String auditBean){
		//通过前端获取 单据审核的 beanName 生成 单据审核bean
		IJxcBillAuditService billAuditService =  IocUtil.getBean(auditBean);
		//单据保存所需参数Map
		Map<String,Object> paraMap = getReqMap();
		//审核人员信息
		SysUser operator = sysCacheService.getLoginUser(session.getId());
		paraMap.put("operator", operator);
		//审核单据 返回审核结果
		outJson(billAuditService.billAudit(paraMap));
	}
	
	/**
	 * 查询单据审核日志页面
	 */
	@RequestMapping("/billAuditLog")
	public ModelAndView billAuditLog(@RequestParam String billNo){
		ModelAndView view = new ModelAndView();

		view.setViewName("jxc/billCommon/billAuditLog");
		view.addObject("billNo", billNo);
		view.addObject("gridComluns",sysGridColumnsService.getGridColumsByCode("auditLog_grid"));
		return view;
	}
	
	/**
	 * 查询单据审核日志
	 */
	@RequestMapping("/getAuditLog")
	public void getAuditLog(){
		outJson(jxcAuditLogService.getBillAuditLogGridData(request));
	}
}
