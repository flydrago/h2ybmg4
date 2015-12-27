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
import com.h2y.bmg.dao.ICommonImageDao;
import com.h2y.bmg.entity.CommonImage;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.ICommonImageService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.dict.DictUtil;

/**
 * 项目名称：h2ybmg2  
 * 类名称：CommonImageController  
 * 类描述：  图片维护Controller   
 * 创建人：侯飞龙  
 * 创建时间：2015年5月8日 下午2:44:41  
 * 修改人：侯飞龙
 * 修改时间：2015年5月8日 下午2:44:41  
 * 修改备注：  
 * @version
 */
@Controller
@RequestMapping(value = "/business/commonimage")
@Scope("prototype")
public class CommonImageController  extends BaseController{

	private final static Logger logger = Logger.getLogger(CommonImageController.class);

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected ICommonImageDao commonImageDao;
	
	@Autowired
	protected ICommonImageService commonImageService;
	
	
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
	public ModelAndView init(String typeCode) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/commonimage/init");
		SysUser sysUser = getLoginUser();
		
		view.addObject("typeCode", typeCode);
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("commonimage_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}
	
	/**
	 * 编辑页面初始化
	 * @param commonImage
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute CommonImage commonImage,@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify") || op.equals("detail")) {

			commonImage = commonImageDao.get(commonImage.getId());
		}else if (op.equals("result")) {
			
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("unitId", this.getLoginUnitId());
			params.put("typeCode", commonImage.getTypeCode());
			commonImage = commonImageDao.getResult(params);
		}

		view.addObject("commonImage",commonImage);
		view.addObject("op",op);
		
		if (op.equals("detail") || op.equals("result")) {
			view.setViewName("business/commonimage/detail");
		}else {
			view.setViewName("business/commonimage/add");
			view.addObject("validationRules", sysValidationService.getValidationByCode("commonimage_validate"));
		}
		return view;
	}


	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(commonImageService.getGridData(request, getLoginUnitId()));
	}
	
	/**
	 * 保存
	 * @param commonImage 
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute CommonImage commonImage,@RequestParam String op) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			SysDictMain sysDictMain = DictUtil.getMainByCode("commonImage_path");
			
			if (null==sysDictMain || null==sysDictMain.getDictValue() || "".equals(sysDictMain.getDictValue())) {
				
				map.put("code", "0");
				map.put("msg", "请在字典中维护图片维护（启动加载图片等）存储路径，编码为：commonImage_path");
				outJson(map);
				return;
			}
			
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("typeCode", commonImage.getTypeCode());
			params.put("op", op);
			params.put("unitId", this.getLoginUnitId());
			params.put("id", commonImage.getId());
			
			if (commonImage.getIsDefault()!=1) {
				
				params.put("startDate", commonImage.getStartDate());
				params.put("endDate", commonImage.getEndDate());
				if (commonImageDao.getDateCrossListRows(params)>0) {
					
					map.put("code", "0");
					map.put("msg", "当前时间设置有冲突，请调整起止时间！");
					outJson(map);
					return;
				}
			}else {
				
				if (commonImageDao.getDefaultListRows(params)>0) {
					map.put("code", "0");
					map.put("msg", "已有默认图片，不可重复设置！");
					outJson(map);
					return;
				}
			}
			
			map.put("code", "1");
			map.put("msg", "保存成功！");
			commonImage.setUnitId( getLoginUnitId());
			
			commonImageService.save(request,op, commonImage,sysDictMain);

			addSaveLog(op, commonImage, OpRresult.success+"", "图片维护（启动加载图片等）添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, commonImage, OpRresult.fail+"", "图片维护（启动加载图片等）添加失败！");
		}
		
		outJson(map);
	}
	
	
	/**
	 * 添加保存操作日志
	 * @param op
	 * @param commonImage
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op,CommonImage commonImage,String opResult,String memo){
		
		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "图片维护（启动加载图片等）", OpType.add+"", 
					opResult, memo, commonImage.getId()+"", BusinessTableName.commonImage.name);
		}else {

			sysLogService.addLog(request, getLoginUser(), "图片维护（启动加载图片等）", OpType.modify+"", 
					opResult, memo, commonImage.getId()+"", BusinessTableName.commonImage.name);
		}
	}


	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute CommonImage commonImage) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "删除成功！");

			CommonImage commonImage2 = commonImageDao.get(commonImage.getId());
			commonImage2.setStatus(-1);
			commonImageDao.update(commonImage2);
			
			sysLogService.addLog(request, getLoginUser(), "图片维护（启动加载图片等）", OpType.delete+"", 
					OpRresult.success+"", "删除成功！", commonImage.getId()+"", BusinessTableName.commonImage.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "图片维护（启动加载图片等）", OpType.delete+"", 
					OpRresult.fail+"", "删除失败！", commonImage.getId()+"", BusinessTableName.commonImage.name);
		}
		outJson(map);
	}
	
}
