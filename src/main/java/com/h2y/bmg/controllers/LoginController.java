package com.h2y.bmg.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.ISysPrivilegeListDao;
import com.h2y.bmg.dao.ISysUnitsDao;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.services.ISysMenuService;
import com.h2y.bmg.services.ISysUserService;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.security.MD5Util;
import com.h2y.util.JSONUtil;

/**
 * 登陆验证控制类

 * 作者：侯飞龙

 * 时间：2014-10-11 下午5:52:36

 * 电子邮件：1162040314@qq.com
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/portal/login")
public class LoginController extends BaseController{

	private static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	protected ISysMenuService sysMenuService;

	@Autowired
	protected ISysUserService sysUserService;

	@Autowired
	protected ISysUnitsDao sysUnitsDao;

	@Autowired
	protected ISysPrivilegeListDao sysPrivilegeListDao;

	@Autowired
	protected ISysLogService sysLogService;

	/**
	 * 登陆页面初始化
	 * @param request
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/init")
	public ModelAndView init(){

		ModelAndView view = new ModelAndView();

		//view.addObject("unitList", sysUnitsDao.getPassUnitList());
		view.setViewName("portal/login/login");
		logger.info("LoginController###init:"+session.getId());
		return view;
	}

	/**
	 * 登陆页面初始化
	 * @param request
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/loginOut")
	public ModelAndView loginOut(){

		ModelAndView view = new ModelAndView();

		//退出日志添加
		sysLogService.addLog(request, getLoginUser(), "登录", OpType.loginOut.toString(), OpRresult.success.toString(), "退出！");

		//清除缓存信息
		sysCacheService.clearUserCachInfo(session.getId());

		view.addObject("unitList", sysUnitsDao.getPassUnitList());
		view.setViewName("portal/login/login");
		logger.info("LoginController###loginOut:"+session.getId());
		return view;
	}


	/**
	 * 桌面初始化
	 */
	@RequestMapping(value="/desktop")
	public ModelAndView desktop(){

		ModelAndView view = new ModelAndView();
		view.setViewName("portal/login/desktop");
		return view;
	}


	/**
	 * 登陆验证
	 * @param request
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/check")
	public void loginCheck(String account,String password){
		Map<String,Object> map = new HashMap<String,Object>();
		SysUser sysUser = new SysUser();

		Long unitId = sysUserService.getUnitByDoMain(account);

		if(unitId.equals(0L)){
			map.put("code", "0");
			map.put("msg", "用户不存在，域名错误！");
			sysUser = new SysUser();
			sysUser.setAccount(account);
			sysLogService.addLog(request, sysUser, "登录", OpType.login.toString(), OpRresult.fail.toString(), "登陆失败,域名错误！");
		}else{
			//验证用户
			sysUser = sysUserService.getLoginCheckUser(unitId, account, password);			

			if (null == sysUser) {

				map.put("code", "0");
				map.put("msg", "用户不存在,用户名错误！");
				sysUser = new SysUser();
				sysUser.setAccount(account);
				sysLogService.addLog(request, sysUser, "登录", OpType.login.toString(), OpRresult.fail.toString(), "登陆失败,用户名错误！");
			}else {
				if(sysUser.getPassword().equals(MD5Util.getMD5(password))){
					sysUser.setPassword(null);
					//更新用户的缓存信息
					sysCacheService.updateUserCachInfo(session.getId(), unitId, sysUser);

					sysLogService.addLog(request, sysUser, "登录", OpType.login.toString(), OpRresult.success.toString(), "登录成功！");
					map.put("code", "1");
					map.put("msg", "登陆成功！");
				}else{
					map.put("code", "0");
					map.put("msg", "密码错误！");
					sysUser = new SysUser();
					sysUser.setAccount(account);
					sysLogService.addLog(request, sysUser, "登录", OpType.login.toString(), OpRresult.fail.toString(), "登陆失败,密码错误！");
				}


			}
		}


		logger.info("LoginController###loginCheck:"+session.getId());
		outJson(map);
	}



	/**
	 * 得到功能菜单
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getMenu")
	public void getMenu(){

		SysUser sysUser = getLoginUser();

		long unitId = sysUser.getUnitId();

		List<Map<String,Object>> userMenu = new ArrayList<Map<String,Object>>();
		//系统用户
		if (unitId==1 && sysUser.getIfSys()==1 && sysUser.getAccount().equals("SuperAdmin")) {
			//得到系统菜单
			userMenu = sysMenuService.getSysMenuTreeData();
		}else {
			//得到用户的菜单
			userMenu = sysMenuService.getUserMenuTreeData(getLoginSysRoleId(),sysUser);
		}
		logger.info("LoginController###getMenu:"+session.getId());
		outJson(userMenu);
	}


	/**
	 * 主页初始化
	 * @param request
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/index")
	public ModelAndView index(){

		ModelAndView view = new ModelAndView();

		SysUser sysUser = getLoginUser();
		view.addObject("sysUser",sysUser);
		view.addObject("sysUnits", getLoginUnit());
		view.addObject("deptMentInfo", sysUserService.getDeptInfoByUserId(sysUser.getId()));


		long unitId = sysUser.getUnitId();

		List<Map<String,Object>> userMenu = new ArrayList<Map<String,Object>>();
		//系统用户
		if (unitId==1 && sysUser.getIfSys()==1 && sysUser.getAccount().equals("SuperAdmin")) {
			//得到系统菜单
			userMenu = sysMenuService.getSysMenuTreeData();
		}else {
			//得到用户的菜单
			userMenu = sysMenuService.getUserMenuTreeData(getLoginSysRoleId(),sysUser);
		}


		view.addObject("menuData", JSONUtil.getJson(userMenu));
		view.setViewName("portal/login/index");

		logger.info("LoginController###index:"+session.getId());
		return view;
	}
}

