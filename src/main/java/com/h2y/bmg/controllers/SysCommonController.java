package com.h2y.bmg.controllers;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.h2y.bmg.basic.BaseController;


/**
 * 类描述：系统通用接口Controller   
 * 作者：侯飞龙
 * 时间：2014年12月22日下午4:40:27
 * 邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value="/sys/common")
@Scope("prototype")
public class SysCommonController extends BaseController{

	private static Logger logger = Logger.getLogger(SysCommonController.class);

    /**
     * 获取列表
     * @param op
     * @return
     */
	@RequestMapping(value="/param")
	public void getList(@RequestParam String op){
		
		
	}
}
