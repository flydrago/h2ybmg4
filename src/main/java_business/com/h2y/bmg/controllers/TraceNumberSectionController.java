package com.h2y.bmg.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.entity.SysDictDetail;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.TraceNumberSection;
import com.h2y.bmg.services.ITraceNumberSectionService;
import com.h2y.bmg.services.ITraceQrcodeSerialService;
import com.h2y.dict.DictUtil;
import com.h2y.util.DateUtil;
/**
 * TraceNumberSectionAction
 * @author hwttnet
 * version:1.2
 * time:2015-06-30
 * email:info@hwttnet.com
 */   
@Controller
@RequestMapping(value="/business/tracenumbersection")
@Scope("prototype")
public class TraceNumberSectionController extends BaseController{

	private static Logger logger = Logger.getLogger(TraceNumberSectionController.class);
	private static Long START_NO = 1000010000L;
	private static Long END_NO = 9999900000L;
	@Autowired
	protected ITraceNumberSectionService traceNumberSectionService;
	@Autowired
	protected ITraceQrcodeSerialService traceQrcodeSerialService;
	
	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/tracenumbersection/init");
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByRequest(request, this.getLoginUser().getUnitId()));
		return view;
	}
	
	
	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {
		
		outJson(traceNumberSectionService.getGridData(request));
	}
	
	
	/**
	 * 编辑页面初始化
	 * 
	 * @param goods
	 * @param op
	 *            操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@RequestParam String op) {
		ModelAndView view = new ModelAndView();	
		
		//包装规格
		List<SysDictDetail> specArrySysDictDetailList =   DictUtil.getDetailListByMainCode(1, "specArry");
		String specArry = "";
		specArry = specArrySysDictDetailList.get(0).getValue();
		
		//当前号段区间编码
		List<SysDictDetail> currSecCodeSysDictDetailList =   DictUtil.getDetailListByMainCode(1, "currentSection");
		String currentValueCode = currSecCodeSysDictDetailList.get(0).getValue();
		
		//号段区间
		List<SysDictDetail> numSectionSysDictDetailList = DictUtil.getDetailListByMainCode(1, "numberSection");
		String numSection = "";
		for(SysDictDetail sysDictDetail : numSectionSysDictDetailList){
			if(sysDictDetail.getCode().equals(currentValueCode)){
				numSection = sysDictDetail.getValue();
				break;
			}
		}
		
		if(!numSection.equals("")){
			String[] tempSecArry = numSection.split("-");
			if(tempSecArry.length == 2){
				START_NO = Long.valueOf(tempSecArry[0]);
				END_NO = Long.valueOf(tempSecArry[1]);
			}else{
				START_NO = 1000010000L;
				END_NO = 9999900000L;
			}
		}
		
		
		String spec = request.getParameter("spec");
		String parentId = request.getParameter("parentId");
		
		Long provinceId = 0L;
		String provinceName = "";
		if(!op.equals("add")){
			TraceNumberSection tempTraceNumberSection = traceNumberSectionService.get(Long.valueOf(parentId));
			START_NO = tempTraceNumberSection.getStartNo();
			END_NO = tempTraceNumberSection.getEndNo();
			if(op.equals("assign_provider_add")){
				provinceId = Long.valueOf(request.getParameter("provinceId"));
				provinceName = request.getParameter("provinceName");
				view.addObject("provinceId", provinceId);
				view.addObject("provinceName", provinceName);
			}
		}else{
			START_NO = 1000010000L;
			END_NO = 9999900000L;
		}
		
		List<TraceNumberSection> dataList = traceNumberSectionService.getListData(request);
		List<Long> longList = new ArrayList<Long>();
		
		int listSize = dataList.size();
		
		for(int i = 0; i < listSize; i++){
			TraceNumberSection traceNumberSection = dataList.get(i);
			Long tempStart = traceNumberSection.getStartNo();
			Long tempEnd = traceNumberSection.getEndNo();
			
			if(i == 0 && !tempStart.equals(START_NO)){
				longList.add(START_NO);
				longList.add(tempStart - 1L);
				
				if(!tempEnd.equals(END_NO)){
					longList.add(tempEnd + 1L);
					if(listSize == 1){
						longList.add(END_NO);
					}
				}
			}else if(i == 0 && tempStart.equals(START_NO) && !tempEnd.equals(END_NO)){
				longList.add(tempEnd + 1L);
				if(listSize == 1){
					longList.add(END_NO);
				}
				
			}else if(i != 0 && !tempEnd.equals(END_NO)){
				
				if(!tempStart.equals(longList.get(longList.size() - 1))){
					longList.add(tempStart - 1L);
				}else{
					longList.remove(longList.size() - 1);
				}
				longList.add(tempEnd + 1L);
				
				if(i == listSize - 1){
					longList.add(END_NO);
				}
			}else if(i != 0 && tempEnd.equals(END_NO)){
				
				if(!tempStart.equals(longList.get(longList.size() - 1))){
					longList.add(tempStart - 1L);
				}else{
					longList.remove(longList.size() - 1);
				}
			}
			
			
		}
		
		if(listSize == 0){
			longList.add(START_NO);
			longList.add(END_NO);
		}
		
	   String numString = "";
	   Iterator<Long> it = longList.iterator();
       while (it.hasNext()){  
    	   numString = numString + it.next() + ","; 
       }
       int tempIndex = numString.lastIndexOf(",");
       numString = numString.substring(0, tempIndex == -1 ? 0 : tempIndex);
       if(op.equals("add") || op.equals("start_add")){
    	   view.addObject("validationRules",sysValidationService.getValidationByCode("numbersection_validate"));
       }else if(op.equals("register_add")){
    	   view.addObject("validationRules",sysValidationService.getValidationByCode("receiveqrcode_validate"));
       }else if(op.equals("assign_province_add")){
    	   view.addObject("validationRules",sysValidationService.getValidationByCode("provincenumbersection_validate"));
       }else if(op.equals("assign_provider_add")){
    	   view.addObject("validationRules",sysValidationService.getValidationByCode("providernumbersection_validate"));
    	   //一次生成序列数量的限制
    	   List<SysDictDetail> numLimitDictDetailList =   DictUtil.getDetailListByMainCode(1, "qrPatchLimitNum");
    	   String numLimitStr = numLimitDictDetailList.get(0).getValue();
    	   int numLimit = Integer.parseInt(numLimitStr) - Integer.parseInt(numLimitStr) % Integer.parseInt(spec);
    	   int boxLimit = numLimit/Integer.parseInt(spec);
    	   view.addObject("numLimit",numLimit);
    	   view.addObject("boxLimit",boxLimit);
       }
       
        view.addObject("specArry",specArry);
		view.addObject("op", op);
		view.addObject("parentId", parentId);
		view.addObject("numString", numString);
		view.addObject("spec", spec);
		view.setViewName("business/tracenumbersection/add");
		return view;
	}
	/**
	 * 编辑页面初始化
	 * 
	 * @param goods
	 * @param op
	 *            操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/save")
	public void save(HttpServletRequest request) {
		
		SysUnits sysUnits = this.getLoginUnit();
		SysUser sysUser = this.getLoginUser();
		Long unitId = sysUnits.getId();
		
		int unitType = sysUnits.getUnitType();
		String userAccount = sysUser.getAccount();
		String userName = sysUser.getUserName();
		Long userId = sysUser.getId();
		Long startNo = Long.valueOf(request.getParameter("startNo"));
		Long endNo = Long.valueOf(request.getParameter("endNo"));
		String opString = request.getParameter("op");
		String parentIdString = request.getParameter("parentId");
		Long parentId = parentIdString == "" ? -8888L : Long.valueOf(parentIdString);
		
		String specString = request.getParameter("spec");
		Integer spec = specString == null || specString.equals("") ? -1 : Integer.valueOf(specString);
		
		TraceNumberSection traceNumberSection = new TraceNumberSection();
		
		if(opString.equals("add")){
			traceNumberSection.setIfReceive(0);
			traceNumberSection.setIfEnable(0);
			traceNumberSection.setIfCreate(0);
			traceNumberSection.setOptFlag(0);
			traceNumberSection.setToId(-9999L);
			traceNumberSection.setFromId(unitId);
		}else if(opString.equals("register_add")){
			traceNumberSection.setIfReceive(1);
			traceNumberSection.setIfEnable(0);
			traceNumberSection.setIfCreate(0);
			traceNumberSection.setOptFlag(1);
			traceNumberSection.setToId(-9999L);
			traceNumberSection.setFromId(unitId);
		}else if(opString.equals("start_add")){
			traceNumberSection.setIfReceive(1);
			traceNumberSection.setIfEnable(1);
			traceNumberSection.setIfCreate(0);
			traceNumberSection.setOptFlag(2);
			traceNumberSection.setToId(-9999L);
			traceNumberSection.setFromId(unitId);
		}else if(opString.equals("assign_province_add")){
			traceNumberSection.setIfReceive(1);
			traceNumberSection.setIfEnable(1);
			traceNumberSection.setIfCreate(1);
			traceNumberSection.setOptFlag(3);
			String toNameString = request.getParameter("provinceName");
			String toIdString = request.getParameter("provinceId");
			Long toId = Long.valueOf(toIdString);
			traceNumberSection.setToId(toId);
			traceNumberSection.setData1(toNameString);
			traceNumberSection.setFromId(unitId);
		}else if(opString.equals("assign_provider_add")){
			traceNumberSection.setIfReceive(1);
			traceNumberSection.setIfEnable(1);
			traceNumberSection.setIfCreate(2);
			traceNumberSection.setOptFlag(4);
			String toNameString = request.getParameter("providerName");
			String toIdString = request.getParameter("providerId");
			Long toId = Long.valueOf(toIdString);
			traceNumberSection.setToId(toId);
			traceNumberSection.setData2(toNameString);
			String fromIdString = request.getParameter("provinceId");
			String fromNameString = request.getParameter("provinceName");
			Long fromId = Long.valueOf(fromIdString);
			traceNumberSection.setFromId(fromId);
			traceNumberSection.setData1(fromNameString);
		}
		
		traceNumberSection.setParentId(parentId);
		traceNumberSection.setCreateDate(DateUtil.getSystemTime());
		traceNumberSection.setStartNo(startNo);
		traceNumberSection.setEndNo(endNo);
		traceNumberSection.setSpec(spec);
		traceNumberSection.setOptUserId(userId);
		traceNumberSection.setOptUserAccount(userAccount);
		traceNumberSection.setOptUserName(userName);
		
		int ifReceive = traceNumberSection.getIfReceive();
		int ifEnable = traceNumberSection.getIfEnable();
		int ifCreate = traceNumberSection.getIfCreate();
		if(unitId == 1L){
			if(parentId == -8888L){
				traceNumberSection.setMemo("平台生成二维码号段给印刷商");
			}else{
				if(ifReceive == 1 && ifEnable  == 0){
					traceNumberSection.setMemo("平台录入号段");
				}else if(ifReceive == 1 && ifEnable  == 1 && ifCreate == 0){
					traceNumberSection.setMemo("平台启用号段");
				}else if(ifReceive == 1 && ifEnable  == 1 && ifCreate == 1){
					traceNumberSection.setMemo("平台分配给省级代理" + traceNumberSection.getData1());
				}	
			} 
		}else{
			if(unitType == 2){
				traceNumberSection.setMemo("省级代理分配号段给供应商");
			}
		}
		
		
		try {
			traceNumberSectionService.add(traceNumberSection);
			
			if(opString.equals("assign_provider_add")){
				traceQrcodeSerialService.save(request,sysUnits,sysUser,traceNumberSection);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("code", 1);
			map.put("msg", "保存成功！");
			outJson(map);
		} catch (Exception e) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("code", 0);
			map.put("msg", "保存失败！");
			outJson(map);
		}
		
	}
	
	/**
	 * 录入
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/register")
	public ModelAndView register() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/tracenumbersection/register");
		view.addObject("parentId", request.getParameter("parentId"));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("registerqr_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("qrsectionreceivesearch", this.getLoginUser().getUnitId()));
		return view;
	}
	/**
	 * 启用
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/start")
	public ModelAndView start() {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("business/tracenumbersection/start");
		view.addObject("parentId", request.getParameter("parentId"));
		view.addObject("spec", request.getParameter("spec"));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("startqr_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("qrsectionenablesearch", this.getLoginUser().getUnitId()));
		return view;
	}
	/**
	 * 分配省级代理
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/assignProvince")
	public ModelAndView assignProvince() {
		
		SysUnits sysUnits = this.getLoginUnit();
		Long unitId = sysUnits.getId();
		SysUser sysUser = getLoginUser();
		
		ModelAndView view = new ModelAndView();
		view.setViewName("business/tracenumbersection/assignProvince");
		view.addObject("toId", unitId == 1L ? null : unitId);
		view.addObject("parentId", request.getParameter("parentId"));
		view.addObject("spec", request.getParameter("spec"));
		view.addObject("toolbar", sysButtonService.getMenuButtons(request,getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("assignprovinceqr_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("qrsectionprovincesearch", sysUser.getUnitId()));
		return view;
	}
	
	
	
	/**
	 * 省级代理列表页面
	 */
	@RequestMapping(value = "/selectProvinceinit")
	public ModelAndView selectProvinceinit() {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("business/tracenumbersection/selectProvinceinit");
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("selectprovince_grid"));
		return view;
	}
	
	
	/**
	 * 获取省级代理列表
	 */
	@RequestMapping(value = "/getProvinceList")
	public void getProvinceList() {
		
		outJson(traceNumberSectionService.getSelectProvinceGridData(request));
	}
	
	
	/**
	 * 分配供应商
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/assignProvider")
	public ModelAndView assignProvider() {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("business/tracenumbersection/assignProvider");
		view.addObject("parentId", request.getParameter("parentId"));
		view.addObject("spec", request.getParameter("spec"));
		view.addObject("provinceId", request.getParameter("provinceId"));
		view.addObject("provinceName", request.getParameter("provinceName"));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("assignprividerqr_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("qrsectionprovidersearch", this.getLoginUser().getUnitId()));
		return view;
	}
	
	
	/**
	 * 省级代理列表页面
	 */
	@RequestMapping(value = "/selectProviderinit")
	public ModelAndView selectProviderinit() {
		
		ModelAndView view = new ModelAndView();
		view.addObject("parentId", request.getParameter("parentId"));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("selectprovider_grid"));
		view.setViewName("business/tracenumbersection/selectProviderinit");
		return view;
	}
	
	
	/**
	 * 获取供应商列表
	 */
	@RequestMapping(value = "/getProviderList")
	public void getProviderList() {
		
		outJson(traceNumberSectionService.getSelectProviderGridData(request));
	}
	
	
	/**
	 * 二维码详情
	 */
	@RequestMapping(value = "/detail")
	public ModelAndView detail() {
		
		ModelAndView view = new ModelAndView();
		view.addObject("parentId", request.getParameter("parentId"));
		view.addObject("toolbar", sysButtonService.getMenuButtons(request,getLoginSysRoleId(), this.getLoginUser()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("qrcodedetail_grid"));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByCode("qrserialsearch",this.getLoginUser().getUnitId()));
		view.setViewName("business/tracenumbersection/detail");
		return view;
	}
	
	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getDetailList")
	public void getDetailList() {
		
		outJson(traceQrcodeSerialService.getGridData(request));
	}

}