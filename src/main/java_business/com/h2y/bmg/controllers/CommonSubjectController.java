package com.h2y.bmg.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.ICommonActivityGoodsRtDao;
import com.h2y.bmg.dao.ICommonSubjectDao;
import com.h2y.bmg.entity.CommonActivityGoodsRt;
import com.h2y.bmg.entity.CommonSubject;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.ICommonActivityService;
import com.h2y.bmg.services.ICommonSubjectService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.dict.DictUtil;


/**
 * 类描述：促销活动图片主题Controller类   
 * 作者：侯飞龙
 * 时间：2015年1月7日上午10:44:05
 * 邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/business/commonsubject")
@Scope("prototype")
public class CommonSubjectController  extends BaseController{

	private final static Logger logger = Logger.getLogger(CommonSubjectController.class);

	@Autowired
	protected ISysLogService sysLogService;
	
	@Autowired
	protected ICommonActivityService commonActivityService;
	
	@Autowired
	protected ICommonSubjectDao commonSubjectDao;
	
	@Autowired
	protected ICommonSubjectService commonSubjectService;
	
	@Autowired
	protected ICommonActivityGoodsRtDao commonActivityGoodsDao;

	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/commonsubject/init");

		SysUser sysUser = getLoginUser();
//		view.addObject("treedata", JSONUtil.getJson(commonActivityService.getTreeList(sysUser.getUnitId(),1)));
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}
	
	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectInit")
	public ModelAndView selectInit(long activityId) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/commonsubject/selectInit");
		view.addObject("activityId", activityId);
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("subject_select_grid"));
		return view;
	}
	
	
	/**
	 * 商品列表页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goodsList")
	public ModelAndView goodsList(long id) {

		ModelAndView view = new ModelAndView();
		CommonSubject commonSubject = commonSubjectDao.get(id);
		view.addObject("commonSubject", commonSubject);
		
		view.setViewName("business/commonsubject/goodsList");
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("subject_goods_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("subject_goods_query", getLoginUnitId()));
		return view;
	}
	
	

	/**
	 * 编辑页面初始化
	 * @param commonSubject
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute CommonSubject commonSubject,@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify") || op.equals("detail")) {

			commonSubject = commonSubjectDao.get(commonSubject.getId());
		}

		view.addObject("commonSubject",commonSubject);
		view.addObject("op",op);
		
		if (op.equals("detail")) {
			view.setViewName("business/commonsubject/detail");
		}else {
			String imUpUrl = DictUtil.getMainByCode("imageuploadroot_url").getDictValue();
			view.addObject("imUpUrl", imUpUrl);
			view.addObject("validationRules", sysValidationService.getValidationByCode("commonsubject_validate"));
			view.setViewName("business/commonsubject/add");
		}
		return view;
	}


	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(commonSubjectService.getGridData(request, getLoginUnitId()));
	}
	
	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getSelectList")
	public void getSelectList() {

		outJson(commonSubjectService.getSelectGridData(request, getLoginUnitId()));
	}

	/**
	 * 保存
	 * @param commonSubject 
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute CommonSubject commonSubject,@RequestParam String op) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			SysDictMain sysDictMain = DictUtil.getMainByCode("commonSubject_path");
			
			if (null==sysDictMain || null==sysDictMain.getDictValue() || "".equals(sysDictMain.getDictValue())) {
				
				map.put("code", "0");
				map.put("msg", "请在字典中维护促销主题图片存储路径，编码为：commonSubject_path");
				outJson(map);
				return;
			}
			
			map.put("code", "1");
			map.put("msg", "保存成功！");
			if (op.equals("add")) {
				commonSubject.setUnitId( getLoginUnitId());
			}else {
				
				Map<String,Object> params = new HashMap<String, Object>();
				params.put("dataId", commonSubject.getId());
				params.put("dataType", 1);
				long rows = commonActivityGoodsDao.getRowsByDataType(params);
				
				int subjectType = commonSubject.getSubjectType();
				if (subjectType==1 && rows>1) {//商品详细判断，主题下面只能有一个商品
					
					map.put("code", "0");
					map.put("msg", "商品详细只能有一个商品，请删除当前主题下面多余的商品！");
					outJson(map);
					return;
				}
			}
			commonSubjectService.save(request,op, commonSubject,sysDictMain);

			addSaveLog(op, commonSubject, OpRresult.success+"", "活动主题添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, commonSubject, OpRresult.fail+"", "活动主题添加失败！");
		}
		
		outJson(map);
	}
	
	
	/**
	 * 添加保存操作日志
	 * @param op
	 * @param commonActivity
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op,CommonSubject commonSubject,String opResult,String memo){
		
		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "促销活动图片主题", OpType.add+"", 
					opResult, memo, commonSubject.getId()+"", BusinessTableName.commonSubject.name);
		}else {

			sysLogService.addLog(request, getLoginUser(), "促销活动图片主题", OpType.modify+"", 
					opResult, memo, commonSubject.getId()+"", BusinessTableName.commonSubject.name);
		}
	}


	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute CommonSubject commonSubject) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "删除成功！");

			CommonSubject commonSubject2 = commonSubjectDao.get(commonSubject.getId());
			commonSubject2.setStatus(-1);
			commonSubjectDao.update(commonSubject2);
			
			sysLogService.addLog(request, getLoginUser(), "促销活动图片主题", OpType.delete+"", 
					OpRresult.success+"", "活动删除成功！", commonSubject.getId()+"", BusinessTableName.commonSubject.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "促销活动图片主题", OpType.delete+"", 
					OpRresult.fail+"", "活动删除失败！", commonSubject.getId()+"", BusinessTableName.commonSubject.name);
		}
		outJson(map);
	}
	
	
	
	/**
	 * 得到活动商品
	 * @return
	 */
	@RequestMapping(value = "/getActivityGoods")
	public void getActivityGoods(@ModelAttribute CommonSubject commonSubject) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("dataId", commonSubject.getId());
		params.put("dataType", 1);
		List<CommonActivityGoodsRt> goodsList = commonActivityGoodsDao.getListByDataType(params);
		if (null!=goodsList && !goodsList.isEmpty()) {
			map.put("code", "1");
			map.put("id", goodsList.get(0).getId());
		}
		outJson(map);
	}
}
