package com.h2y.bmg.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.h2y.bmg.basic.BaseController;
import com.h2y.util.HttpTookit;


/**
 * 项目名称：h2ybmg2  
 * 类名称：CmbsCenterController  
 * 类描述：转发服务中心类  
 * 创建人：侯飞龙  
 * 创建时间：2015年5月6日 上午9:04:37  
 * 修改人：侯飞龙
 * 修改时间：2015年5月6日 上午9:04:37  
 * 修改备注：  
 * @version
 */
@Controller
@RequestMapping(value = "/cmbs/{moduleName}")
@Scope("prototype")
public class CmbsCenterController extends BaseController{

	@RequestMapping(value="/{methodName}")
	public void doCmbs(@PathVariable(value="moduleName") String moduleName,@PathVariable(value="methodName") String methodName){
		
		out(HttpTookit.doCmbsPost(moduleName, methodName, request));
	}
}
