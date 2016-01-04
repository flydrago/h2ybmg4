package com.h2y.bmg.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.basic.WbsKeys.SInvokeKeys;
import com.h2y.bmg.dao.ISysDeptUserDao;
import com.h2y.bmg.entity.SysDeptUser;
import com.h2y.bmg.entity.SysDictDetail;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.ISysDepartmentService;
import com.h2y.bmg.services.ISysDeptUserService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.services.ISysUnitsService;
import com.h2y.bmg.services.ISysUserService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.bmg.util.SysBaseUtil.SmsContentTemplate;
import com.h2y.dict.DictUtil;
import com.h2y.security.MD5Util;
import com.h2y.util.DataRequestUtil;
import com.h2y.util.DateUtil;
import com.h2y.util.FreeMarkerUtil;
import com.h2y.util.JSONUtil;

/**
 * 用户管理url跳转控制类

 * 作者：侯飞龙

 * 时间：2014-10-13 上午11:31:51

 * 电子邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/sys/user")
@Scope("prototype")
public class SysUserController extends BaseController{

	private final static Logger logger = Logger.getLogger(SysUserController.class);

	@Autowired
	protected ISysDepartmentService sysDepartmentService;

	@Autowired
	protected ISysUnitsService sysUnitsService;

	@Autowired
	protected ISysUserService sysUserService;

	@Autowired
	protected ISysDeptUserService sysDeptUserService;

	@Autowired
	protected ISysDeptUserDao sysDeptUserDao;


	@Autowired
	protected ISysLogService sysLogService;

	@InitBinder("sysUser")    
	public void initBinder1(WebDataBinder binder) {    
		binder.setFieldDefaultPrefix("sysUser.");    
	}    
	@InitBinder("sysDeptUser")    
	public void initBinder2(WebDataBinder binder) {    
		binder.setFieldDefaultPrefix("sysDeptUser.");    
	}  


	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/user/init");

		//得到单位Id
		long unitId = getLoginUnitId();

		List<Map<String, Object>> treeList = sysDepartmentService.getChildTreeData(unitId,0l);

		SysUnits sysUnits = sysUnitsService.get(unitId);
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("id", 0);
		rootMap.put("code", sysUnits.getId());
		rootMap.put("text", sysUnits.getUnitName());
		treeList.add(rootMap);
		view.addObject("treedata", JSONUtil.getJson(treeList));

		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, unitId));
		//		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("sysuser_grid"));
		return view;
	}

	/**
	 * 编辑页面初始化
	 * @param sysUser 
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute SysUser sysUser,@ModelAttribute SysDeptUser sysDeptUser,@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		view.addObject("validationRules", sysValidationService.getValidationByCode("sysuser_validate"));

		if (op.equals("modify")) {
			sysUser = sysUserService.get(sysUser.getId());
			sysDeptUser = sysDeptUserService.get(sysDeptUser.getId());
		}
		view.addObject("sysUser",sysUser);
		view.addObject("sysDeptUser",sysDeptUser);
		view.addObject("op",op);
		view.setViewName("sys/user/add");
		return view;
	}


	/**
	 * 获取列表
	 * @param deptId 部门Id
	 */
	@RequestMapping(value = "/getList")
	public void getList(@RequestParam long deptId) {
		outJson(sysUserService.getGridData(request, deptId));
	}


	/**
	 * 保存
	 * @param sysDepartment 部门对象
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute SysUser sysUser,@ModelAttribute SysDeptUser sysDeptUser,@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();

		long unitId = getLoginUnitId();
		sysUser.setUnitId(unitId);
		sysUser.setIfDelete(0l);
		try {

			if (sysUserService.isHasSameAcount(sysUser, op)) {
				map.put("code", "0");
				map.put("msg", "账号重复，请用其他账号！");
			}else {
				map.put("code", "1");
				map.put("msg", "保存成功！");
				sysUserService.save(sysUser, sysDeptUser, op);

				String memo = op.equals("add")?"用户注册":"用户修改";
				sysLogService.addLog(request, getLoginUser(), "用户维护",
						op, OpRresult.success.toString(), memo, sysUser.getId()+"", BusinessTableName.SysUser.name);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
		}
		outJson(map);
	}


	/**
	 * 删除
	 * @param sysDepartment 部门对象
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute SysUser sysUser,@ModelAttribute SysDeptUser sysDeptUser,@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			if (op.equals("delete")) {

				SysUser sysUser2 = sysUserService.get(sysUser.getId());
				sysUser2.setIfDelete(1l);
				sysUserService.update(sysUser2);
				//删除关联关系
				sysDeptUserService.deleteByUserId(sysUser.getId());

				sysLogService.addLog(request, getLoginUser(), "用户维护", OpType.delete.toString(), 
						OpRresult.success.toString(), "删除成功！", sysUser.getId()+"", BusinessTableName.SysUser.name);
				map.put("code", "1");
				map.put("msg", "删除成功！");
			}else if (op.equals("remove")) {

				//判断用户移除的部门是不是最后一个部门
				if (sysUserService.isLastDeptForUser(sysUser.getId())) {
					map.put("code", "0");
					map.put("msg", "当前部门是用户的最后一个部门，不可移除！");
				}else {
					sysDeptUserService.delete(sysDeptUser.getId());
					map.put("code", "1");
					map.put("msg", "移除成功！");
				}
			}else {

				map.put("code", "0");
				map.put("msg", "无效标示符，op："+op+"！");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
		}
		outJson(map);
	}




	/**
	 * 移动人员
	 * @param op in：移入、out：移除
	 */
	@RequestMapping(value = "/move")
	public void move(@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();
		String msgfix = op.equals("in")?"移入":"移除";
		try {

			String userId = request.getParameter("userId");
			if (op.equals("out")) {
				long rows = sysDeptUserDao.getDeptRowsByuserId(Long.valueOf(userId));
				if(rows==1){
					map.put("code", "0");
					map.put("msg", "当前部门为用户最后一个部门，不可移除！");
					outJson(map);
					return;
				}
			}

			sysUserService.move(request, op);
			map.put("code", "1");
			map.put("msg", msgfix+"成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", msgfix+"失败！");
		}
		outJson(map);
	}


	/**
	 * 重置密码
	 */
	@RequestMapping(value = "/resetPwd")
	public void resetPwd() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			String pwd = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));

			String id = request.getParameter("id");
			SysUser sysUser = sysUserService.get(Long.valueOf(id));
			sysUser.setPassword(MD5Util.getMD5(pwd));
			sysUserService.update(sysUser);


			//			//推送密码重置
			//			Map<String,Object> params = new HashMap<String, Object>();
			//			params.put("token", SysBaseUtil.MSGS_TOKEN);
			//			params.put("ts", SysBaseUtil.MSGS_TS);
			//			
			//			Map<String,Object> jsonMap = new HashMap<String, Object>();
			//			jsonMap.put("account", sysUser.getAccount());
			//			jsonMap.put("password", pwd);
			//			params.put("ps", JSONUtil.getJson(jsonMap));
			//			String result = HttpTookit.doPost(SysBaseUtil.MSGS_RESETPWD_URL, params);
			//			logger.info("用户："+sysUser.getAccount()+"，推送用户重置密码结果："+result);

			//发送短信
			if (StringUtils.isNotBlank(sysUser.getMobile())) {
				Map<String, Object> smsMap = new HashMap<String, Object>();

				String msgTempl = SmsContentTemplate.bmgUserResetPwd.value;
				SysDictDetail sysDictDetail = DictUtil.getDetailByCode(1, "sms_template", "bmgUserResetPwd");
				if (null!=sysDictDetail && null!=sysDictDetail.getValue() && !"".equals(sysDictDetail.getValue())) {
					msgTempl = sysDictDetail.getValue();
				}
				
				Map<String,Object> dataMap = new HashMap<String, Object>();
				dataMap.put("account", sysUser.getAccount());
				dataMap.put("pwd", pwd);
				
				String message = FreeMarkerUtil.getContentFromStringTemplate(dataMap, msgTempl);
				smsMap.put("mobile", sysUser.getMobile());
				smsMap.put("createDate", DateUtil.DateNow());
				smsMap.put("msg", message);

				Map<String, Object> postMap = new HashMap<String, Object>();
				postMap.put("msms", smsMap);

				Map<String,Object> resultData = DataRequestUtil.getRequestData("sms/sendsms.htm", postMap);
				if ("1".equals(resultData.get(SInvokeKeys.resultFlag.value())+"")) {
					logger.info("用户："+sysUser.getAccount()+"重置密码，短信发送成功！");
					map.put("code", "1");
					map.put("msg","密码重置成功！");
				}else {
					logger.info("用户："+sysUser.getAccount()+"重置密码，短信发送失败！");
					map.put("code", "0");
					map.put("msg","密码重置失败！");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "密码重置失败！");
		}
		outJson(map);
	}

	/**
	 * 修改密码初始化
	 */
	@RequestMapping(value = "/changePwd")
	public ModelAndView changePwd() {
		ModelAndView view = new ModelAndView();
		view.addObject("validationRules", sysValidationService.getValidationByCode("changepwd_validate"));
		view.setViewName("sys/user/changePwd");
		return view;
	}


	/**
	 * 修改密码
	 */
	@RequestMapping(value = "/savePwd")
	public void savePwd() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			SysUser sysUser = sysUserService.get(getLoginUserId());

			String oldPassword = MD5Util.getMD5(request.getParameter("oldPassword"));

			String newPassword = MD5Util.getMD5(request.getParameter("newPassword"));

			if (oldPassword.equals(sysUser.getPassword())) {

				sysUser.setPassword(newPassword);
				sysUserService.update(sysUser);
				map.put("code", "1");
				map.put("msg","密码修改成功！");
			}else{
				map.put("code", "0");
				map.put("msg","密码错误！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "密码修改失败！");
		}
		outJson(map);
	}
}
