package com.h2y.bmg.controllers;



import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.IGoodsPriceDao;
import com.h2y.bmg.dao.IImportBagDao;
import com.h2y.bmg.dao.IImportBagUserRtDao;
import com.h2y.bmg.entity.BaseResult;
import com.h2y.bmg.entity.GoodsPrice;
import com.h2y.bmg.entity.ImportBag;
import com.h2y.bmg.entity.ImportBagUserRt;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IImportBagService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.ImportBagTask;
import com.h2y.dict.DictUtil;
import com.h2y.util.DataRequestUtil;


/**
 * 项目名称：h2ybmg2  
 * 类名称：ImportBagController  
 * 类描述：导入礼包Controller 
 * 创建人：侯飞龙  
 * 创建时间：2015年6月19日 下午2:44:49  
 * 修改人：侯飞龙
 * 修改时间：2015年6月19日 下午2:44:49  
 * 修改备注：  
 * @version
 */
@Controller
@RequestMapping(value = "/business/importbag")
@Scope("prototype")
public class ImportBagController extends BaseController{

	private final static Logger logger = Logger.getLogger(ImportBagController.class);

	@Autowired
	protected ISysLogService sysLogService;
	
	@Autowired
	protected IImportBagService importBagService;
	
	@Autowired
	protected IImportBagDao importBagDao;
	
	@Autowired
	protected IGoodsPriceDao goodsPriceDao;
	
	@Autowired
	protected IImportBagUserRtDao importBagUserRtDao;
	
	
	/**
	 * 初始化页面（发起、审核、审核通过）
	 * @return
	 */
	@RequestMapping(value="/{initPage}")
	public ModelAndView init(@PathVariable(value="initPage") String initPage) {

		ModelAndView view = new ModelAndView();
		SysUser sysUser = getLoginUser();
		view.addObject("toolbar", sysButtonService.getMenuButtons(request,getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns",sysGridColumnsService.getGridColumsByCode("importbag_grid"));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByCode("importbag_query", getLoginUnitId()));
		view.setViewName("business/importbag/"+initPage);
		return view;
	}
	
	
	/**
	 * 礼包接受用户页面
	 * @return
	 */
	@RequestMapping(value = "/userInit")
	public ModelAndView userInit(String bagCode) {
		
		ModelAndView view = new ModelAndView();
		SysUser sysUser = getLoginUser();
		view.addObject("gridComluns",sysGridColumnsService.getGridColumsByCode("bag_user_gird"));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByCode("bag_user_query", sysUser.getUnitId()));
		view.addObject("bagCode",bagCode);
		view.addObject("op", request.getParameter("op"));
		view.setViewName("business/importbag/userInit");
		return view;
	}
	
	/**
	 * 审核跟踪列表
	 * @return
	 */
	@RequestMapping(value = "/trackInit")
	public ModelAndView trackInit(String bagCode) {
		
		ModelAndView view = new ModelAndView();
		view.addObject("gridComluns",sysGridColumnsService.getGridColumsByCode("importbagTrack_grid"));
		view.addObject("bagCode",bagCode);
		view.setViewName("business/importbag/trackInit");
		return view;
	}
	
	
	/**
	 * 编辑页面初始化
	 * @param importBag 导入礼包
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute ImportBag importBag,@RequestParam String op) {

		ModelAndView view = new ModelAndView();
		if (op.equals("modify") || op.equals("detail")) {
			
			importBag = importBagDao.get(importBag.getId());
			GoodsPrice goodsPrice = goodsPriceDao.get(importBag.getGoodsPriceId());
			view.addObject("goodsPrice", goodsPrice);
		}else {
			
		}
		view.addObject("op", op);
		view.addObject("importBag", importBag);
		view.setViewName("business/importbag/add");
		view.addObject("validationRules", sysValidationService.getValidationByCode("importbag_validate"));
		return view;
	}
	
	
	/**
	 * 接受用户页面初始化
	 * @param importBag 导入礼包
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/userAdd")
	public ModelAndView userAdd(@ModelAttribute ImportBagUserRt importBagUserRt,@RequestParam String op) {

		ModelAndView view = new ModelAndView();
		if (op.equals("modify")) {
			
			importBagUserRt = importBagUserRtDao.get(importBagUserRt.getId());
		}
		
		view.addObject("op", op);
		view.addObject("importBagUserRt", importBagUserRt);
		view.setViewName("business/importbag/userAdd");
		view.addObject("validationRules", sysValidationService.getValidationByCode("importbaguser_validate"));
		return view;
	}
	
	
	/**
	 * 审核页面初始化
	 * @param importBag 导入礼包
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/check")
	public ModelAndView check(String bagCode,String taskCode) {

		ModelAndView view = new ModelAndView();
		view.addObject("bagCode", bagCode);
		view.addObject("taskCode", taskCode);
		view.setViewName("business/importbag/check");
		return view;
	}
	
	/**
	 * 导入用户初始化
	 * @param importBag 导入礼包
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/importUserInit")
	public ModelAndView importUserInit(String bagCode) {

		ModelAndView view = new ModelAndView();
		ImportBag importBag = importBagDao.getByBagCode(bagCode);
		view.addObject("importBag", importBag);
		view.setViewName("business/importbag/importUserInit");
		return view;
	}
	
	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getStartList")
	public void getStartList() {
		
		outJson(importBagService.getGridData(request,this.getLoginUnitId(), ImportBagTask.start));
	}
	
	/**
	 * 获取审核通过列表
	 */
	@RequestMapping(value = "/getEndList")
	public void getEndList() {
		
		outJson(importBagService.getGridData(request,this.getLoginUnitId(), ImportBagTask.end));
	}
	
	
	/**
	 * 获取一级审核列表
	 */
	@RequestMapping(value = "/getCheckList1")
	public void getCheckList1() {
		
		outJson(importBagService.getGridData(request,this.getLoginUnitId(), ImportBagTask.check1));
	}
	
	
	/**
	 * 获取二级列表
	 */
	@RequestMapping(value = "/getCheckList2")
	public void getCheckList2() {
		
		outJson(importBagService.getGridData(request,this.getLoginUnitId(), ImportBagTask.check2));
	}
	
	
	/**
	 * 获取礼包接受用户列表
	 */
	@RequestMapping(value = "/getUserList")
	public void getUserList() {
		
		outJson(importBagService.getUserGridData(request));
	}
	
	
	/**
	 * 获取礼包审核过程列表
	 */
	@RequestMapping(value = "/getTrackList")
	public void getTrackList() {
		
		outJson(importBagService.getTrackGridData(request));
	}
	
	/**
	 * 礼包保存
	 * @param importBag 导入礼包
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute ImportBag importBag,@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			importBag.setUserId(this.getLoginUserId());
			BaseResult baseResult = importBagService.save(op, importBag);
			
			map.put("code", baseResult.getResultFlag());
			map.put("msg", baseResult.getResultMsg());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
		}
		outJson(map);
	}
	
	
	/**
	 * 礼包用户保存
	 * @param importBag 导入礼包
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/saveUser")
	public void saveUser(@ModelAttribute ImportBagUserRt importBagUserRt,@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			BaseResult baseResult = importBagService.saveUser(op, importBagUserRt);
			map.put("code", baseResult.getResultFlag());
			map.put("msg", baseResult.getResultMsg());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
		}
		outJson(map);
	}
	
	
	/**
	 * 执行审核
	 * @param bagCode 礼包编码
	 */
	@RequestMapping(value = "/doCheck")
	public void doCheck(String bagCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			BaseResult baseResult = importBagService.doCheck(request, this.getLoginUserId(), bagCode);
			
			map.put("code", baseResult.getResultFlag());
			map.put("msg", baseResult.getResultMsg());
			if (baseResult.getResultFlag()==2) {//审核通过
				Map<String,Object> postData = new HashMap<String, Object>();
				postData.put("bagCode", bagCode);
				BaseResult baseResult2 = DataRequestUtil.getCommonRequestData("giftBag/bmgImportSyn.htm", postData);
				if (baseResult2.getResultFlag()!=1) {
					map.put("code", baseResult2.getResultFlag());
					map.put("msg", baseResult2.getResultMsg());
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "审核失败：提示消息为"+e.getMessage());
		}
		outJson(map);
	}
	
	
	/**
	 * 导入礼包用户数据
	 * @param bagCode 礼包编码
	 */
	@RequestMapping(value = "/importUser")
	public void importUser(String bagCode) {
		BaseResult baseResult = new BaseResult(0);
		try {
			
			SysDictMain sysDictMain=DictUtil.getMainByCode("importbagfile_path");
			
			if (null==sysDictMain || null==sysDictMain.getDictValue() || sysDictMain.getDictValue().equals("")) {
				
				baseResult.setResultMsg("请维护导入用户文件存储路径！编码为：importbagfile_path");
				outJson(baseResult);
				return;
			}
			
			ImportBag importBag = importBagDao.getByBagCode(bagCode);
			
			if (importBag.getCurrentTask().equals(ImportBagTask.end.code)) {
				
				baseResult.setResultMsg("当前礼包已经审核通过不可导入人员！");
				outJson(baseResult);
				return;
			}
			
			baseResult = importBagService.importUser(request, importBag);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			baseResult.setResultFlag(0);
			baseResult.setResultMsg("导入失败，出现异常！");
		}
		outJson(baseResult);
	}
	
	
	/**
	 * 删除礼包
	 * @param bagCode 礼包编码
	 */
	@RequestMapping(value = "/delete")
	public void delete(String bagCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		try {
			
			ImportBag importBag = importBagDao.getByBagCode(bagCode);
			if (importBag.getCurrentTask().equals(ImportBagTask.end.code)) {
				map.put("msg", "当前礼包已经审核通过，不可删除！");
				return;
			}
			importBag.setStatus(-1);
			importBagDao.update(importBag);
			map.put("code", "1");
			map.put("msg", "删除成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("msg", "删除失败，出现异常！");
		}
		outJson(map);
	}
	
	
	/**
	 * 删除礼包用户
	 * @param bagCode 礼包编码
	 */
	@RequestMapping(value = "/deleteUser")
	public void deleteUser(@ModelAttribute ImportBagUserRt importBagUserRt) {
		BaseResult baseResult = new BaseResult(0);
		try {

			
			ImportBagUserRt importBagUserRt2 = importBagUserRtDao.get(importBagUserRt.getId());
			ImportBag importBag = importBagDao.getByBagCode(importBagUserRt2.getBagCode());
			if (importBag.getCurrentTask().equals(ImportBagTask.end.code)) {
				baseResult.setResultMsg("当前礼包已经审核通过，接受用户不可删除！");
				return;
			}
			
			importBagUserRt2.setStatus(-1);
			importBagUserRtDao.update(importBagUserRt2);
			baseResult.setResultFlag(1);
			baseResult.setResultMsg("删除成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			baseResult.setResultMsg("删除失败，出现异常！");
		}
		outJson(baseResult);
	}
	
	/**
	 * 礼包是否已结束
	 * @param bagCode 礼包编码
	 */
	@RequestMapping(value = "/isEnd")
	public void isEnd(String bagCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		try {
			
			ImportBag importBag = importBagDao.getByBagCode(bagCode);
			if (importBag.getCurrentTask().equals(ImportBagTask.end.code)) {
				map.put("msg", "当前礼包已经审核通过，接受用户不可删除！");
				outJson(map);
				return;
			}
			
			map.put("code", "1");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("msg", "出现异常！");
		}
		outJson(map);
	}
	
	
	/**
	 * 礼包同步
	 * @param bagCode 礼包编码
	 */
	@RequestMapping(value = "/bagSyn")
	public void bagSyn(String bagCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		try {
			
			ImportBag importBag = importBagDao.getByBagCode(bagCode);
			if (!importBag.getCurrentTask().equals(ImportBagTask.end.code)) {
				
				map.put("msg", "当前礼包暂未审核通过，不可同步礼包！");
				outJson(map);
				return;
			}
			
			if (importBag.getStatus()==3) {
				
				map.put("msg", "当前礼包已经同步！");
				outJson(map);
				return;
			}
			
			Map<String,Object> postData = new HashMap<String, Object>();
			postData.put("bagCode", bagCode);
			BaseResult baseResult2 = DataRequestUtil.getCommonRequestData("giftBag/bmgImportSyn.htm", postData);
			map.put("code", baseResult2.getResultFlag());
			map.put("msg", baseResult2.getResultMsg());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("msg", "出现异常！");
		}
		outJson(map);
	}
	
}
