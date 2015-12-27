package com.h2y.bmg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.IMSmsHisDao;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IMSmsHisService;
/**
 * 
 *   
 * 项目名称：h2ybmg2  
 * 类名称：MSmsHisAction  
 * 类描述：  短信发送记录查看跳转类
 * 创建人：李剑 
 * 创建时间：2015年9月21日 上午11:34:06  
 * 修改人：李剑
 * 修改时间：2015年9月21日 上午11:34:06  
 * 修改备注：  如果你看到这个，那么说明你现在已经在负责我以前的项目了。我感到非常抱歉。愿上帝保佑你。
 * @version
 */
@Controller
@RequestMapping(value = "/business/MSmsHis")
@Scope("prototype")
public class MSmsHisController extends BaseController{
	@Autowired
	protected IMSmsHisDao mSmsHisDao;
	@Autowired
	protected IMSmsHisService mSmsHisService;
	
	
	/**
	 * @Fields serialVersionUID : 
	 */
//	private static final long serialVersionUID = 1L;
//	private static Logger logger = Logger.getLogger(MSmsHisAction.class);
	
	
	/**
	 * 编辑页面初始化
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {
		ModelAndView view = new ModelAndView();
		view.setViewName("business/mmsghis/smshisinit");
		SysUser sysUser = getLoginUser();
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}

	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(mSmsHisService.getGridData(request));
	}


}