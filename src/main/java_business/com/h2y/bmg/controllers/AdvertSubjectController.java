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
import com.h2y.bmg.dao.IAdvertSubjectDao;
import com.h2y.bmg.dao.IAdvertSubjectGoodsRtDao;
import com.h2y.bmg.entity.AdvertSubject;
import com.h2y.bmg.entity.AdvertSubjectGoodsRt;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IAdvertSubjectService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.services.ISysRoleService;
import com.h2y.bmg.services.ISysUnitsService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.dict.DictUtil;


/**
 * 项目名称：h2ybmg2  
 * 类名称：AdvertSubjectController  
 * 类描述：广告主题Controller  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月8日 上午10:31:30  
 * 修改人：侯飞龙
 * 修改时间：2015年4月8日 上午10:31:30  
 * 修改备注：  
 * @version
 */
@Controller
@RequestMapping(value = "/business/advertsubject")
@Scope("prototype")
public class AdvertSubjectController  extends BaseController{

	private final static Logger logger = Logger.getLogger(AdvertSubjectController.class);

	@Autowired
	protected ISysRoleService sysRoleService;

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected ISysUnitsService sysUnitsService;
	
	@Autowired
	protected IAdvertSubjectService advertSubjectService;
	
	@Autowired
	protected IAdvertSubjectDao advertSubjectDao;
	
	@Autowired
	protected IAdvertSubjectGoodsRtDao advertSubjectGoodsRtDao;
	
	
	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/advertsubject/init");
		SysUser sysUser = getLoginUser();
		
		view.addObject("unitId",getLoginUnitId());
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByRequest(request,sysUser.getUnitId()));
		return view;
	}
	
	/**
	 * 广告主题商品维护页面初始化
	 * @param subjectId 广告主题id
	 * @return
	 */
	@RequestMapping(value = "/goodsInit")
	public ModelAndView goodsInit(long subjectId) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/advertsubject/goodsInit");
		view.addObject("advertSubject",advertSubjectDao.get(subjectId));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("advertsubject_goods_grid"));
		return view;
	}
	
	
	/**
	 * 主题选择页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectInit")
	public ModelAndView selectInit() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/advertsubject/selectInit");
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByCode("advertsubject_select_query", this.getLoginUnitId()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("advertsubject_select_grid"));
		return view;
	}
	
	/**
	 * 编辑页面初始化
	 * @param advertSubject 广告主题
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute AdvertSubject advertSubject,@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify") || op.equals("detail")) {
			advertSubject = advertSubjectDao.get(advertSubject.getId());
		}
		
		String imUpUrl = DictUtil.getMainByCode("imageuploadroot_url").getDictValue();
		view.addObject("imUpUrl", imUpUrl);
		view.addObject("advertSubject", advertSubject);
		view.addObject("op",op);
		if (op.equals("detail")) {
			
			view.setViewName("business/advertsubject/detail");
		}else {
			
			view.addObject("validationRules", sysValidationService.getValidationByCode("advertsubject_validate"));
			view.setViewName("business/advertsubject/add");
		}
		return view;
	}

	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {
		
		outJson(advertSubjectService.getGridData(request, getLoginUnit()));
	}

	/**
	 * 获取商品列表
	 */
	@RequestMapping(value = "/getGoodsList")
	public void getGoodsList(@RequestParam long subjectId) {
		
		outJson(advertSubjectService.getGoodsGridData(request, subjectId));
	}
	
	
	/**
	 * 保存
	 * @param advertSubject 广告主题
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute AdvertSubject advertSubject,@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			SysDictMain sysDictMain = DictUtil.getMainByCode("advertSubject_path");
			
			if (null==sysDictMain || null==sysDictMain.getDictValue() || "".equals(sysDictMain.getDictValue())) {
				
				map.put("code", "0");
				map.put("msg", "请在字典中维护广告主题图片存储路径，编码为：advertSubject_path");
				outJson(map);
				return;
			}
			
			map.put("code", "1");
			map.put("msg", "保存成功！");
			
			if (!op.equals("add") && !op.equals("modify")) {
				
				map.put("code", "0");
				map.put("msg", "无效标示符："+op);
				outJson(map);
				return;
			}
			
			SysUser sysUser = getLoginUser();
			
			if (op.equals("modify")) {
				
				AdvertSubject advertSubject2 = advertSubjectDao.get(advertSubject.getId());
				if (sysUser.getUnitId()!=advertSubject2.getUnitId()) {
					
					map.put("code", "0");
					map.put("msg", "非当前平台数据不可操作！");
					outJson(map);
					return;
				}
			}
			
			advertSubject.setUserId(sysUser.getId());
			advertSubject.setUnitId(sysUser.getUnitId());
			advertSubjectService.save(request, op, advertSubject, sysDictMain);
			
			addSaveLog(op, advertSubject, OpRresult.success+"", "广告主题保存成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, advertSubject, OpRresult.fail+"", "广告主题保存成功！");
		}
		outJson(map);
	}
	
	/**
	 * 广告主题商品保存操作
	 * @param advertSubject 广告主题
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/saveGoods")
	public void saveGoods(@RequestParam String subjectId,@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			map.put("code", "1");
			map.put("msg", "保存成功！");
			
			AdvertSubject advertSubject2 = advertSubjectDao.get(Long.valueOf(subjectId));
			if (this.getLoginUnitId()!=advertSubject2.getUnitId()) {
				
				map.put("code", "0");
				map.put("msg", "非当前平台数据不可操作！");
				outJson(map);
				return;
			}
			
			advertSubjectService.saveGoods(request, op);
			sysLogService.addLog(request, getLoginUser(), "广告主题商品关联",op, 
					 OpRresult.success+"", "广告主题商品保存操作成功", subjectId, BusinessTableName.advertSubjectGoodsRt.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			sysLogService.addLog(request, getLoginUser(), "广告主题商品关联",op, 
					 OpRresult.fail+"", "广告主题商品保存操作失败", subjectId, BusinessTableName.advertSubjectGoodsRt.name);
		}
		outJson(map);
	}
	
	
	/**
	 * 广告主题商品排序保存操作
	 * @param id 广告主题商品关联主键
	 * @param ord 排序
	 */
	@RequestMapping(value = "/saveGoodsOrd")
	public void saveGoodsOrd(@RequestParam long id,@RequestParam int ord) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			map.put("code", "1");
			map.put("msg", "排序成功！");
			
			AdvertSubjectGoodsRt advertSubjectGoodsRt = advertSubjectGoodsRtDao.get(id);
			advertSubjectGoodsRt.setOrd(ord);
			advertSubjectGoodsRtDao.update(advertSubjectGoodsRt);
			sysLogService.addLog(request, getLoginUser(), "广告主题商品关联","updateOrd", 
					 OpRresult.success+"", "广告主题商品排序修改操作成功", id+"", BusinessTableName.advertSubjectGoodsRt.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			sysLogService.addLog(request, getLoginUser(), "广告主题商品关联","updateOrd", 
					 OpRresult.fail+"", "广告主题商品排序修改操作失败", id+"", BusinessTableName.advertSubjectGoodsRt.name);
			map.put("code", "0");
			map.put("msg", "排序失败！");
		}
		outJson(map);
	}
	
	
	/**
	 * 得到主题，对应商品的总数量
	 * @param subjectId 广告主题Id
	 * @return
	 */
	@RequestMapping(value = "/getGoodsRows")
	public void getGoodsRows(long subjectId){
		
		out(advertSubjectGoodsRtDao.getListRows(subjectId)+"");
	}
	
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute AdvertSubject advertSubject) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "删除成功！");
			
			AdvertSubject advertSubject2 = advertSubjectDao.get(advertSubject.getId());
			advertSubject2.setStatus(-1);
			advertSubjectDao.update(advertSubject2);
			
			sysLogService.addLog(request, getLoginUser(), "广告主题", OpType.delete+"", 
					OpRresult.success+"", "广告主题删除成功！", advertSubject.getId()+"", BusinessTableName.advertSubject.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "广告主题", OpType.delete+"", 
					OpRresult.fail+"", "广告主题删除失败！", advertSubject.getId()+"", BusinessTableName.advertSubject.name);
		}
		outJson(map);
	}
	
	
	/**
	 * 添加保存操作日志
	 * @param op
	 * @param advertSubject
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op,AdvertSubject advertSubject,String opResult,String memo){
		
		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "广告主题", OpType.add+"", 
					opResult, memo, advertSubject.getId()+"", BusinessTableName.advertSubject.name);
		}else {

			sysLogService.addLog(request, getLoginUser(), "广告主题", OpType.modify+"", 
					opResult, memo, advertSubject.getId()+"", BusinessTableName.advertSubject.name);
		}
	}

}
