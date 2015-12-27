package com.h2y.bmg.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.h2y.bmg.dao.IAdvertColumnDao;
import com.h2y.bmg.dao.IAdvertColumnSubjectRtDao;
import com.h2y.bmg.dao.IAdvertSubjectDao;
import com.h2y.bmg.entity.AdvertColumnSubjectRt;
import com.h2y.bmg.entity.AdvertSubject;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IAdvertColumnSubjectRtService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.services.ISysRoleService;
import com.h2y.bmg.services.ISysUnitsService;
import com.h2y.bmg.util.SysBaseUtil;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.util.JSONUtil;


/**
 * 项目名称：h2ybmg2  
 * 类名称：AdvertColumnSubjectRtController  
 * 类描述：广告栏位主题关联Controller  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月9日 上午10:23:51  
 * 修改人：侯飞龙
 * 修改时间：2015年4月9日 上午10:23:51  
 * 修改备注：  
 * @version
 */
@Controller
@RequestMapping(value = "/business/advertcolunmsubjectrt")
@Scope("prototype")
public class AdvertColumnSubjectRtController  extends BaseController{

	private final static Logger logger = Logger.getLogger(AdvertColumnSubjectRtController.class);

	@Autowired
	protected ISysRoleService sysRoleService;

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected ISysUnitsService sysUnitsService;
	
	@Autowired
	protected IAdvertSubjectDao advertSubjectDao;
	
	@Autowired
	protected IAdvertColumnSubjectRtDao advertColumnSubjectRtDao;
	
	@Autowired
	protected IAdvertColumnSubjectRtService advertColumnSubjectRtService;
	
	@Autowired
	protected IAdvertColumnDao advertColumnDao;
	
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
		view.setViewName("business/advertcolunmsubjectrt/init");
		
		SysUser sysUser = getLoginUser();
		
		//得到广告栏位树列表数据
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("unitId", sysUser.getUnitId());
		List<Map<String,Object>> columnList = advertColumnDao.getUnitColumnTreeList(params);
		if (null==columnList) {
			columnList = new ArrayList<Map<String,Object>>();
		}
		Map<String,Object> rootData = new HashMap<String, Object>();
		rootData.put("id", 0);
		rootData.put("text", "广告栏位");
		columnList.add(rootData);
		
		view.addObject("treedata", JSONUtil.getJson(columnList));
		view.addObject("unitId",getLoginUnitId());
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		return view;
	}


	/**
	 * 编辑页面初始化
	 * @param advertColumnSubjectRt 广告栏位主题关联
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute AdvertColumnSubjectRt advertColumnSubjectRt,@RequestParam String op) {

		ModelAndView view = new ModelAndView();
		
		if (op.equals("modify")) {
			
			advertColumnSubjectRt = advertColumnSubjectRtDao.get(advertColumnSubjectRt.getId());
			AdvertSubject advertSubject = advertSubjectDao.get(advertColumnSubjectRt.getSubjectId());
			view.addObject("advertSubject",advertSubject);
		}
		
		view.addObject("advertColumnSubjectRt",advertColumnSubjectRt);
		view.addObject("op",op);
		view.setViewName("business/advertcolunmsubjectrt/add");
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("acsrtdatecross_grid"));
		view.addObject("validationRules", sysValidationService.getValidationByCode("advertcolunmsubjectrt_validate"));
		return view;
	}
	
	/**
	 * 当前单位广告栏位预览页面
	 * @return
	 */
	@RequestMapping(value = "/preview")
	public ModelAndView preview() {
		
		ModelAndView view = new ModelAndView();
		view.addObject("sysUnits", this.getLoginUnit());
		view.addObject("fpUrl", SysBaseUtil.FP_URL);
		view.setViewName("business/advertcolunmsubjectrt/preview");
		return view;
	}


	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {
		
		outJson(advertColumnSubjectRtService.getGridData(request, getLoginUnitId()));
	}
	
	
	/**
	 * 获取时间列表
	 */
	@RequestMapping(value = "/getDateCrossList")
	public void getDateCrossList() {
		
		outJson(advertColumnSubjectRtService.getDateCrossGridData(request, this.getLoginUnitId()));
	}


	/**
	 * 保存
	 * @param advertColumnSubjectRt 广告栏位主题关联
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute AdvertColumnSubjectRt advertColumnSubjectRt,@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("code", "1");
			map.put("msg", "保存成功！");
			long unitId = this.getLoginUnitId();
			
			//验证栏位主题关联默认关联是否重复
			if (advertColumnSubjectRt.getIsDefault()==1) {
				
				Map<String,Object> params = new HashMap<String, Object>();
				params.put("columnId", advertColumnSubjectRt.getColumnId());
				params.put("unitId", unitId);
				params.put("op", op);
				params.put("id", advertColumnSubjectRt.getId());
				
				if (advertColumnSubjectRtDao.getDefaultListRows(params)>0) {
					map.put("code", "0");
					map.put("msg", "当前栏位已经维护默认主题！");
					outJson(map);
					return;
				}
			}
			
			//验证非默认的栏位主题关联，时间是否冲突
			Map<String,Object> params1 = new HashMap<String, Object>();
			params1.put("startDate", advertColumnSubjectRt.getStartDate());
			params1.put("endDate", advertColumnSubjectRt.getEndDate());
			params1.put("repeatStart", advertColumnSubjectRt.getRepeatStart());
			params1.put("repeatEnd", advertColumnSubjectRt.getRepeatEnd());
			params1.put("columnId", advertColumnSubjectRt.getColumnId());
			params1.put("unitId", unitId);
			params1.put("op", op);
			params1.put("id", advertColumnSubjectRt.getId());
			if (advertColumnSubjectRtDao.getDateCrossListRows(params1)>0) {
				map.put("code", "-1");
				map.put("msg", "当前栏位时间设置有冲突，请调整时间！");
				outJson(map);
				return;
			}
			
			if (op.equals("add")) {
				
				advertColumnSubjectRt.setUnitId(unitId);
				advertColumnSubjectRt.setUserId(this.getLoginUserId());
			}
			advertColumnSubjectRtService.save(request, op, advertColumnSubjectRt);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
		}
		outJson(map);
	}



	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute AdvertColumnSubjectRt advertColumnSubjectRt) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "删除成功！");
			
			AdvertColumnSubjectRt advertColumnSubjectRt2 = advertColumnSubjectRtDao.get(advertColumnSubjectRt.getId());
			advertColumnSubjectRt2.setStatus(-1);
			advertColumnSubjectRtDao.update(advertColumnSubjectRt2);
			sysLogService.addLog(request, getLoginUser(), "广告栏位主题关联", OpType.delete+"", 
					OpRresult.success+"", "广告栏位主题关联删除成功！", advertColumnSubjectRt.getId()+"", BusinessTableName.advertColumnSubjectRt.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "广告栏位主题关联", OpType.delete+"", 
					OpRresult.fail+"", "广告栏位主题关联删除失败！", advertColumnSubjectRt.getId()+"", BusinessTableName.advertColumnSubjectRt.name);
		}
		outJson(map);
	}
}
