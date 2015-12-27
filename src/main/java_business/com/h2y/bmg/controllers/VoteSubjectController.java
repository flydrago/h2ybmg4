package com.h2y.bmg.controllers;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import com.h2y.bmg.dao.IPromoteActivityDao;
import com.h2y.bmg.dao.IScheduleTaskDao;
import com.h2y.bmg.dao.IVoteSubjectDao;
import com.h2y.bmg.dao.IVoteSubjectDetailDao;
import com.h2y.bmg.dao.IVoteSubjectFileDao;
import com.h2y.bmg.entity.PromoteActivity;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.VoteSubject;
import com.h2y.bmg.entity.VoteSubjectDetail;
import com.h2y.bmg.entity.VoteSubjectFile;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.services.IVoteSubjectService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.dict.DictUtil;
/**
 * 
 *   
 * 项目名称：h2ybmg2  
 * 类名称：VoteSubjectController  
 * 类描述：  投票主题维护跳转类
 * 创建人：李剑 
 * 创建时间：2015年9月2日 上午9:29:00  
 * 修改人：李剑
 * 修改时间：2015年9月2日 上午9:29:00  
 * 修改备注：  
 * @version
 */
@Controller
@RequestMapping(value = "/business/votesubject")
@Scope("prototype")
public class VoteSubjectController extends BaseController{

	private final static Logger logger = Logger.getLogger(VoteSubjectController.class);
	@Autowired
	protected ISysLogService sysLogService;
	@Autowired
	protected IVoteSubjectDao voteSubjectDao;
	@Autowired
	protected IVoteSubjectDetailDao voteSubjectDetailDao;
	@Autowired
	protected IVoteSubjectFileDao voteSubjectFileDao;
	@Autowired
	protected IPromoteActivityDao promoteActivityDao;
	@Autowired
	protected IVoteSubjectService voteSubjectService;
	
	@Autowired
	protected IScheduleTaskDao scheduleTaskDao;

	@InitBinder("voteSubject")    
	public void initBinder1(WebDataBinder binder) {    
		binder.setFieldDefaultPrefix("voteSubject.");    
	}
	@InitBinder("voteSubjectDetail")    
	public void initBinder2(WebDataBinder binder) {    
		binder.setFieldDefaultPrefix("voteSubjectDetail.");    
	}


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
	}


	/**
	 * 编辑页面初始化
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {
		ModelAndView view = new ModelAndView();
		view.setViewName("business/votesubject/init");
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

		outJson(voteSubjectService.getGridData(request, getLoginUnitId()));
	}


	/**
	 * 编辑页面初始化
	 * @param voteSubject
	 * @param op 操作类型，添加、修改
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute VoteSubject voteSubject,@RequestParam String op) {

		ModelAndView view = new ModelAndView();
		view.addObject("voteSubject",voteSubject);
		view.addObject("op",op);
		if (op.equals("modify")) {
			view.addObject("voteSubject",voteSubjectDao.get(voteSubject.getId()));
			VoteSubject voteSubject2 = voteSubjectDao.get(voteSubject.getId());
			PromoteActivity promoteActivity2 = promoteActivityDao.get(voteSubject2.getPromoteId());
			view.addObject("promoteTitle",promoteActivity2.getTitle());
			view.addObject("voteSubjectFile", voteSubjectFileDao.getBySubjectId(voteSubject.getId()));
			view.addObject("voteSubjectDetail", voteSubjectDetailDao.getBySubjectId(voteSubject.getId()));
		}

		view.addObject("validationRules", sysValidationService.getValidationByCode("voteSubject_validate"));
		view.setViewName("business/votesubject/add");
		return view;
	}	
	/**
	 * 主题页面详细
	 * @param voteSubject
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/detail")
	public ModelAndView detail(@ModelAttribute VoteSubject voteSubject) {
		ModelAndView view = new ModelAndView();
		view.setViewName("business/votesubject/detail");
		try {
			view.addObject("voteSubject",voteSubjectDao.get(voteSubject.getId()));
			VoteSubject voteSubject3 = voteSubjectDao.get(voteSubject.getId());
			PromoteActivity promoteActivity3 = promoteActivityDao.get(voteSubject3.getPromoteId());
			view.addObject("promoteTitle",promoteActivity3.getTitle());
			view.addObject("voteSubjectFile", voteSubjectFileDao.getBySubjectId(voteSubject.getId()));
			view.addObject("voteSubjectDetail", voteSubjectDetailDao.getBySubjectId(voteSubject.getId()));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			sysLogService.addLog(request, getLoginUser(), "投票主题维护", "显示", 
					OpRresult.fail+"", "投票主题详细显示失败！", voteSubject.getId()+"", BusinessTableName.voteSubject.name);
		}
		return view;
	}

	/**
	 * 保存
	 * @param promoteActivity 
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute VoteSubject voteSubject,VoteSubjectDetail voteSubjectDetail,VoteSubjectFile voteSubjectFile,@RequestParam String op) {

		Map<String, Object> map = new HashMap<String, Object>();
		try{
			//图片路径
			SysDictMain sysDictMain = DictUtil.getMainByCode("voteSubject_path");
			SysDictMain sysDictMain1 = DictUtil.getMainByCode("voteSubjectFile_path");
			String dictPath=sysDictMain.getDictValue();
			String dictPath1=sysDictMain1.getDictValue();
			voteSubject.setUnitId(getLoginUnitId());
			voteSubject.setUnitType(getLoginUnit().getUnitType());
			voteSubject.setZoneCode(getLoginUnit().getZoneCode());
			voteSubject.setSubjectType("baby");
			voteSubjectService.save(request,op, voteSubject,voteSubjectDetail,voteSubjectFile,dictPath,dictPath1);
			map.put("code", "1");
			map.put("msg", "保存成功！");
			addSaveLog(op, voteSubject, OpRresult.success+"", "投票主题添加成功！");
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, voteSubject, OpRresult.fail+"", "投票主题保存失败！");
		}
		outJson(map);
	}
	/**
	 * 投票主题删除
	 * @param voteSubject
	 */
	@RequestMapping(value = "/delete")
	public 	void delete(@ModelAttribute VoteSubject voteSubject,@RequestParam String op){
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			VoteSubject voteSubject2 = voteSubjectDao.get(voteSubject.getId());
			voteSubject2.setSubjectStatus(-1);
			voteSubjectDao.update(voteSubject2);
			map.put("code", "1");
			map.put("msg", "删除成功！");
			sysLogService.addLog(request, getLoginUser(), "投票主题", OpType.delete+"", 
					OpRresult.success+"", "投票主题删除成功！", voteSubject.getId()+"", BusinessTableName.voteSubject.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "投票主题", OpType.delete+"", 
					OpRresult.fail+"", "投票主题删除失败！", voteSubject.getId()+"", BusinessTableName.voteSubject.name);
		}
		outJson(map);	
	}
	
	/**
	 * 投票新增用户列表
	 */
	
	@RequestMapping(value = "/getnewuser")
	public void getListNewUser() {
		outJson(voteSubjectService.getNewUserGridData(request, getLoginUnitId()));
	}
	/**
	 * 投票新增用户列表
	 */
	
	@RequestMapping(value = "/newuserinit")
	public ModelAndView newUser(@ModelAttribute VoteSubject voteSubject) {	
		ModelAndView view = new ModelAndView();
		view.setViewName("business/votesubject/newuser");
		SysUser sysUser = getLoginUser();
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("voteNewUser_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("voteNewUser_query", sysUser.getUnitId()));
		view.addObject("subId",request.getParameter("id"));
		view.addObject("regSource",request.getParameter("regSource"));
		return view;
	}
	/**
	 * 添加操作日志
	 * @param op
	 * @param voteSubject
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op,VoteSubject voteSubject,String opResult,String memo){

		if (op.equals("add")) {
			sysLogService.addLog(request, getLoginUser(), "投票主题", OpType.add+"", 
					opResult, memo, voteSubject.getId()+"", BusinessTableName.voteSubject.name);
		};
		if (op.equals("modify")){
			sysLogService.addLog(request, getLoginUser(), "投票主题", OpType.modify+"", 
					opResult, memo, voteSubject.getId()+"", BusinessTableName.voteSubject.name);
		};

		if (op.equals("delete")){
			sysLogService.addLog(request, getLoginUser(), "投票主题", OpType.delete+"", 
					opResult, memo, voteSubject.getId()+"", BusinessTableName.voteSubject.name);
		}
	}

}
