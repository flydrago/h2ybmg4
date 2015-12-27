package com.h2y.bmg.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.basic.WbsKeys.SInvokeKeys;
import com.h2y.bmg.services.ITraceCirculateRecordService;
import com.h2y.bmg.services.ITraceQrcodeSerialService;


@Controller
@RequestMapping(value = "/business/tracecirculaterecord")
@Scope("prototype")
public class TraceCirculateRecordController extends BaseController{
	
	private final static Logger logger = Logger.getLogger(TraceCirculateRecordController.class);
	
	@Autowired
	protected ITraceCirculateRecordService traceCirculateRecordService;
	
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
		view.setViewName("business/tracecirculaterecord/init");
		view.addObject("bottleQrcodeNo",request.getParameter("bottleQrcodeNo"));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("queryqrrecord_grid"));
		return view;
	}
	
	
	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {
		
		outJson(traceCirculateRecordService.getGridData(request));
	}
	
	/**
	 * 添加记录
	 */
	@RequestMapping(value = "/addRecord")
	public void addRecord() {
		
		outJson(traceCirculateRecordService.addRecord(getReqMap()));
	}	
	
	/**
	 * 获取记录
	 */
	@RequestMapping(value = "/getRecord")
	public void getRecord() {
		
		outJson(traceCirculateRecordService.getRecordList(getReqMap()));
	}
	
	
	/**
	 * 获取参数
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @update：2015年4月7日 下午1:45:43
	 */
	protected Map<String, Object> getReqMap() {

		Map<String, Object> reqMap = new HashMap<String, Object>();
		System.out.println(SInvokeKeys.slock.value());
		// 获取参数
		reqMap.put(SInvokeKeys.slock.value(),request.getParameter(SInvokeKeys.slock.value()));
		reqMap.put(SInvokeKeys.skey.value(),request.getParameter(SInvokeKeys.skey.value()));
		reqMap.put(SInvokeKeys.sid.value(),request.getParameter(SInvokeKeys.sid.value()));
		reqMap.put(SInvokeKeys.os.value(),request.getParameter(SInvokeKeys.os.value()));
		reqMap.put(SInvokeKeys.osv.value(),request.getParameter(SInvokeKeys.osv.value()));
		reqMap.put(SInvokeKeys.appv.value(),request.getParameter(SInvokeKeys.appv.value()));
		reqMap.put(SInvokeKeys.postData.value(),request.getParameter(SInvokeKeys.postData.value()));

		return reqMap;
	}
	
}
