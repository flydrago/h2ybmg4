package com.h2y.bmg.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.h2y.bmg.entity.ShareHref;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IShareHrefService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.dict.DictUtil;


/**
 * 分享链接维护
 * @author sunfj
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value="business/sharehref")
public class ShareHrefController extends BaseController {

	private final static Logger logger = Logger.getLogger(ShareHrefController.class);

	@Autowired
	protected IShareHrefService shareHrefService;

	@Autowired
	protected ISysLogService sysLogService;


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
	}



	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/sharehref/init");

		SysUser sysUser = getLoginUser();
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}


	/**
	 * 编辑页面初始化
	 * @param shareHref
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute ShareHref shareHref,@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify")) {
			shareHref = shareHrefService.get(shareHref.getId());
		}

		view.addObject("shareHref",shareHref);
		view.addObject("op",op);
		view.setViewName("business/sharehref/add");
		view.addObject("validationRules", sysValidationService.getValidationByCode("sharehref_validate"));
		return view;
	}


	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(shareHrefService.getGridData(request, getLoginUnitId()));
	}

	/**
	 * 保存
	 * @param shareHref 
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute ShareHref shareHref, @RequestParam String op) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "1");
		map.put("msg", "保存成功！");

		try{
			//图片路径
			SysDictMain sysDictMain = DictUtil.getMainByCode("shareHref_path");

			if (sysDictMain!=null && StringUtils.isNotBlank(sysDictMain.getDictValue())) {

				shareHref.setUnitId(getLoginUnitId());
				shareHref.setUnitType(getLoginUnit().getUnitType());
				shareHref.setZoneCode(getLoginUnit().getZoneCode());


				shareHrefService.save(request,shareHref,op,sysDictMain.getDictValue());
				addSaveLog(op, shareHref, OpRresult.success+"", "分享链接添加成功！");

			}else{
				map.put("code", "0");
				map.put("msg", "请维护推广活动图片上传路径，编码为：promoteActivity_path！");
			}

		}catch(Exception e){
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, shareHref, OpRresult.fail+"", "分享链接添加失败！");
		}



		outJson(map);
	}




	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute  ShareHref shareHref) {
		Map<String, Object> map = new HashMap<String, Object>();


		try{
			map.put("code", "1");
			map.put("msg", "删除成功！");

			ShareHref shareHref2 = shareHrefService.get(shareHref.getId());
			shareHref2.setHrefStatus(-1);
			shareHrefService.update(shareHref2);

			sysLogService.addLog(request, getLoginUser(), "分享链接", OpType.delete+"", 
					OpRresult.success+"", "分享链接删除成功！", shareHref.getId()+"", BusinessTableName.shareHref.name);

		}catch(Exception e){
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "分享链接", OpType.delete+"", 
					OpRresult.fail+"", "分享链接删除失败！", shareHref.getId()+"", BusinessTableName.shareHref.name);
		}


		outJson(map);
	}


	/**
	 * 添加保存操作日志
	 * @param op
	 * @param shareHref
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op,ShareHref shareHref,String opResult,String memo){

		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "分享链接维护", OpType.add+"", 
					opResult, memo, shareHref.getId()+"", BusinessTableName.shareHref.name);
		}else {

			sysLogService.addLog(request, getLoginUser(), "分享链接维护", OpType.modify+"", 
					opResult, memo, shareHref.getId()+"", BusinessTableName.shareHref.name);
		}
	}


}
