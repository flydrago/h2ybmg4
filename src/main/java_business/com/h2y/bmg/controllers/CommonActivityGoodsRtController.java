package com.h2y.bmg.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
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
import com.h2y.bmg.dao.ICommonActivityDao;
import com.h2y.bmg.dao.ICommonActivityGoodsRtDao;
import com.h2y.bmg.dao.ICommonActivityGoodsRtHisDao;
import com.h2y.bmg.dao.IGoodsDao;
import com.h2y.bmg.dao.IGoodsPriceDao;
import com.h2y.bmg.entity.CommonActivity;
import com.h2y.bmg.entity.CommonActivityGoodsRt;
import com.h2y.bmg.entity.CommonActivityGoodsRtHis;
import com.h2y.bmg.entity.Goods;
import com.h2y.bmg.entity.GoodsPrice;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.ICommonActivityGoodsRtService;
import com.h2y.bmg.services.ICommonActivityService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.util.JSONUtil;


/**
 * 类描述：促销（活动和主题）与商品关联Controller类   
 * 作者：侯飞龙
 * 时间：2015年1月7日上午10:44:05
 * 邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/business/commonactivitygoodsrt")
@Scope("prototype")
public class CommonActivityGoodsRtController  extends BaseController{

	private final static Logger logger = Logger.getLogger(CommonActivityGoodsRtController.class);

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected ICommonActivityService commonActivityService;

	@Autowired
	protected ICommonActivityGoodsRtService commonActivityGoodsRtService;

	@Autowired
	protected ICommonActivityGoodsRtHisDao commonActivityGoodsRtHisDao;

	@Autowired
	protected ICommonActivityGoodsRtDao commonActivityGoodsRtDao;

	@Autowired
	protected IGoodsDao goodsDao;

	@Autowired
	protected IGoodsPriceDao goodsPriceDao;

	@Autowired
	protected ICommonActivityDao commonActivityDao;


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
		view.setViewName("business/commonactivitygoodsrt/init");

		SysUser sysUser = getLoginUser();
		view.addObject("treedata", JSONUtil.getJson(commonActivityService.getTreeList(sysUser.getUnitId(),0)));
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}


	/**
	 * 编辑页面初始化
	 * @param commonActivityGoodsRt
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute CommonActivityGoodsRt commonActivityGoodsRt,@RequestParam String op) {

		ModelAndView view = new ModelAndView();


		if (op.equals("modify")) {

			commonActivityGoodsRt = commonActivityGoodsRtDao.get(commonActivityGoodsRt.getId());
			GoodsPrice goodsPrice = goodsPriceDao.get(commonActivityGoodsRt.getGoodsPriceId());
			view.addObject("goodsPrice", goodsPrice);
		}

		if(commonActivityGoodsRt.getDataType() == 0){ // 0活动 1主题
			CommonActivity commonActivity = commonActivityDao.get(commonActivityGoodsRt.getDataId());
			view.addObject("dataActivityType",commonActivity.getDataType());
		}

		view.addObject("commonActivityGoodsRt",commonActivityGoodsRt);
		view.addObject("op",op);
		view.setViewName("business/commonactivitygoodsrt/add");
		view.addObject("validationRules", sysValidationService.getValidationByCode("commonactivitygoods_validate"));
		return view;
	}


	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(commonActivityGoodsRtService.getGridData(request,this.getLoginUnitId()));
	}


	/**
	 * 日期冲突页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/dateCrossInit")
	public ModelAndView dateCrossInit(@ModelAttribute CommonActivityGoodsRt commonActivityGoodsRt,@RequestParam String op) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/commonactivitygoodsrt/dateCrossInit");
		view.addObject("op",op);
		view.addObject("id",commonActivityGoodsRt.getId());
		view.addObject("startDate",commonActivityGoodsRt.getStartDate());
		view.addObject("endDate",commonActivityGoodsRt.getEndDate());
		return view;
	}


	/**
	 * 得到日期冲突列表
	 */
	@RequestMapping(value = "/getDateCrossList")
	public void getDateCrossList(){

		outJson(commonActivityGoodsRtService.getDateCrossGridData(request,this.getLoginUnitId()));
	}

	/**
	 * 保存
	 * @param commonActivityGoodsRt 
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute CommonActivityGoodsRt commonActivityGoodsRt,@RequestParam String op) {

		Map<String, Object> map = new HashMap<String, Object>();

		String msg = "";
		try {

			msg = "保存成功！";
			map.put("code", "1");

			//判断是否秒杀,秒杀商品时间为秒杀活动设置时间
			if(null != commonActivityGoodsRt && commonActivityGoodsRt.getDataType() == 0){
				CommonActivity commonActivity = commonActivityDao.get(commonActivityGoodsRt.getDataId());
				if(null != commonActivity && commonActivity.getDataType() == 0){
					if(commonActivity.getIsSpike() == 1){//如果是秒杀商品的话，开始时间和截止时间不可修改
						commonActivityGoodsRt.setStartDate(commonActivity.getStartDate());
						commonActivityGoodsRt.setEndDate(commonActivity.getEndDate());
					}					
				}else if(null != commonActivity && commonActivity.getDataType() == 2){//定额商品设置商品定价
					commonActivityGoodsRt.setActivityPrice(commonActivity.getFixedPrice());					
				}
			}


			Map<String,Object> params = new HashMap<String, Object>();
			params.put("unitId", this.getLoginUnitId());
			params.put("goodsPriceId", commonActivityGoodsRt.getGoodsPriceId());
			params.put("startDate", commonActivityGoodsRt.getStartDate());
			params.put("endDate", commonActivityGoodsRt.getEndDate());
			params.put("op", op);
			params.put("id", commonActivityGoodsRt.getId());

			long crossRows = commonActivityGoodsRtDao.getDateCrossListRows(params);

			//判断商品活动时间是否有冲突
			if (crossRows>0) {

				map.put("code", "-1");
				msg = "商品活动时间有冲突！";
			}else {

				commonActivityGoodsRt.setUnitId(getLoginUnitId());
				commonActivityGoodsRtService.save(op, commonActivityGoodsRt);
				addSaveLog(op, commonActivityGoodsRt, OpRresult.success+"", "活动添加商品成功！");
			}
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			map.put("code", "0");
			msg = "保存失败！";
			addSaveLog(op, commonActivityGoodsRt, OpRresult.fail+"", "活动添加商品失败！");
		}
		map.put("msg", msg);
		outJson(map);
	}


	/**
	 * 添加保存操作日志
	 * @param op
	 * @param commonActivityGoodsRt
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op,CommonActivityGoodsRt commonActivityGoodsRt,String opResult,String memo){

		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "促销（活动和主题）与商品关联模块", OpType.add+"", 
					opResult, memo, commonActivityGoodsRt.getId()+"", BusinessTableName.commonActivityGoodsRt.name);
		}else {

			sysLogService.addLog(request, getLoginUser(), "促销（活动和主题）与商品关联模块", OpType.modify+"", 
					opResult, memo, commonActivityGoodsRt.getId()+"", BusinessTableName.commonActivityGoodsRt.name);
		}
	}


	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute CommonActivityGoodsRt commonActivityGoodsRt) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "删除成功！");
			commonActivityGoodsRtDao.deleteById(commonActivityGoodsRt.getId());
			sysLogService.addLog(request, getLoginUser(), "促销（活动和主题）与商品关联模块", OpType.delete+"", 
					OpRresult.success+"", "活动商品删除成功！", commonActivityGoodsRt.getId()+"", BusinessTableName.commonActivityGoodsRt.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "促销（活动和主题）与商品关联模块", OpType.delete+"", 
					OpRresult.fail+"", "活动商品删除成功！", commonActivityGoodsRt.getId()+"", BusinessTableName.commonActivityGoodsRt.name);
		}
		outJson(map);
	}

}
