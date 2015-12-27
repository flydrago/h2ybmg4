package com.h2y.bmg.controllers;

import java.util.HashMap;
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
import com.h2y.bmg.dao.IAdvertColumnDao;
import com.h2y.bmg.entity.AdvertColumn;
import com.h2y.bmg.services.IAdvertColumnService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.services.ISysRoleService;
import com.h2y.bmg.services.ISysUnitsService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;


/**
 * 项目名称：h2ybmg2  
 * 类名称：AdvertColumnController  
 * 类描述：广告栏位Controller  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月7日 上午9:28:47  
 * 修改人：侯飞龙
 * 修改时间：2015年4月7日 上午9:28:47  
 * 修改备注：  
 * @version
 */
@Controller
@RequestMapping(value = "/business/advertcolumn")
@Scope("prototype")
public class AdvertColumnController  extends BaseController{

	private final static Logger logger = Logger.getLogger(AdvertColumnController.class);

	@Autowired
	protected ISysRoleService sysRoleService;

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected ISysUnitsService sysUnitsService;
	
	@Autowired
	protected IAdvertColumnDao advertColumnDao;
	
	@Autowired
	protected IAdvertColumnService advertColumnService;
	
	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/advertcolumn/init");
		
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		return view;
	}
	
	/**
	 * 编辑页面初始化
	 * @param advertColumn 广告栏
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute AdvertColumn advertColumn,@RequestParam String op) {

		ModelAndView view = new ModelAndView();
		if (op.equals("modify")) {
			advertColumn = advertColumnDao.get(advertColumn.getId());
		}else {
			advertColumn.setStatus(1);
		}
		view.addObject("op", op);
		view.addObject("advertColumn", advertColumn);
		view.setViewName("business/advertcolumn/add");
		view.addObject("validationRules", sysValidationService.getValidationByCode("advertcolumn_validate"));
		return view;
	}


	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {
		outJson(advertColumnService.getGridData(request));
	}
	
	/**
	 * 保存
	 * @param advertColumn 广告栏
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute AdvertColumn advertColumn,@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			map.put("code", "1");
			map.put("msg", "保存成功！");
			
			advertColumnService.save(op, advertColumn);
			addSaveLog(op, advertColumn, OpRresult.success+"", "广告栏位保存成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, advertColumn, OpRresult.success+"", "广告栏位保存失败！");
		}
		outJson(map);
	}
	
	/**
	 * 添加保存操作日志
	 * @param op
	 * @param advertColumn
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op,AdvertColumn advertColumn,String opResult,String memo){
		
		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "广告栏", OpType.add+"", 
					opResult, memo, advertColumn.getId()+"", BusinessTableName.advertColumn.name);
		}else {

			sysLogService.addLog(request, getLoginUser(), "广告栏", OpType.modify+"", 
					opResult, memo, advertColumn.getId()+"", BusinessTableName.advertColumn.name);
		}
	}

	/**
	 * 删除
	 * @param advertColumn 广告栏位
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute AdvertColumn advertColumn) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "删除成功！");
			
			AdvertColumn advertColumn2 = advertColumnDao.get(advertColumn.getId());
			advertColumn2.setStatus(-1);
			advertColumnDao.update(advertColumn2);
			sysLogService.addLog(request, getLoginUser(), "广告栏", OpType.delete+"", 
					OpRresult.success+"", "广告栏删除成功！", advertColumn.getId()+"", BusinessTableName.advertColumn.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "广告栏", OpType.delete+"", 
					OpRresult.fail+"", "广告栏删除失败！", advertColumn.getId()+"", BusinessTableName.advertColumn.name);
		}
		outJson(map);
	}
}
