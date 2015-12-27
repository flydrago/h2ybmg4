package com.h2y.bmg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.services.IMMsgHisService;


/**
 * 项目名称：h2ybmg2  
 * 类名称：MMsgHisController  
 * 类描述：推送消息Controller  
 * 创建人：侯飞龙  
 * 创建时间：2015年7月28日 上午9:37:38  
 * 修改人：侯飞龙
 * 修改时间：2015年7月28日 上午9:37:38  
 * 修改备注：  
 * @version
 */
@Controller
@RequestMapping(value = "/business/mmsghis")
@Scope("prototype")
public class MMsgHisController  extends BaseController{
	
	@Autowired
	protected IMMsgHisService msgHisService;

	/**
	 * 推送消息页面初始化
	 * @param target （delivery:配送端、app:客户端、pc:电脑客户端）
	 * @param datasourceType 数据源类型（订单信息 order 、小达快报 news）
	 * @param datasourceId 数据源ID（订单编号、小达快报ID）
	 * @return
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init(String target,String datasourceType,String datasourceId) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/mmsghis/init");
		
		view.addObject("target", target);
		view.addObject("datasourceType", datasourceType);
		view.addObject("datasourceId", datasourceId);
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("mmsghis_"+target+"_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("mmsghis_"+target+"_query", this.getLoginUnitId()));
		return view;
	}
	
	
	/**
	 * 获取推送消息列表
	 */
	@RequestMapping(value = "/getList")
	public void getList(){
		
		outJson(msgHisService.getGridData(request));
	}
	
}
