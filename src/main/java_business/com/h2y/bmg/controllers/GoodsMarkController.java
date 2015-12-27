package com.h2y.bmg.controllers;

import java.util.ArrayList;
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
import com.h2y.bmg.dao.IGoodsMarkDao;
import com.h2y.bmg.dao.IGoodsMarkInfoDao;
import com.h2y.bmg.dao.IGoodsTypeDao;
import com.h2y.bmg.entity.GoodsMark;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IGoodsMarkService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.util.JSONUtil;

/**
 * 类描述：商品标签Controller 作者：侯飞龙 时间：2015年1月7日上午10:44:05 邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/business/goodsmark")
@Scope("prototype")
public class GoodsMarkController extends BaseController {

	private final static Logger logger = Logger
			.getLogger(GoodsMarkController.class);

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected IGoodsTypeDao goodsTypeDao;

	@Autowired
	protected IGoodsMarkDao goodsMarkDao;

	@Autowired
	protected IGoodsMarkService goodsMarkService;

	@Autowired
	protected IGoodsMarkInfoDao goodsMarkInfoDao;

	/**
	 * 页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/goodsmark/init");

		SysUser sysUser = getLoginUser();

		List<Map<String, Object>> treeList = goodsTypeDao.getTreeListByUnitId(sysUser.getUnitId());
		
		if (treeList == null) {
			treeList = new ArrayList<Map<String, Object>>();
		}
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("id", 0);
		root.put("text", "商品类型");
		treeList.add(root);
		view.addObject("treedata", JSONUtil.getJson(treeList));

		view.addObject("toolbar", sysButtonService.getMenuButtons(request,
				getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns",
				sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject(
				"conditionHtml",
				sysQueryItemService.getConditionHtmlByRequest(request,
						sysUser.getUnitId()));
		return view;
	}

	/**
	 * 编辑页面初始化
	 * 
	 * @param goodsMark
	 * @param op
	 *            操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute GoodsMark goodsMark,
			@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify")) {
			goodsMark = goodsMarkDao.get(goodsMark.getId());
		}

		view.addObject("goodsMark", goodsMark);
		view.addObject("op", op);
		view.setViewName("business/goodsmark/add");
		view.addObject("validationRules",
				sysValidationService.getValidationByCode("goodsmark_validate"));
		return view;
	}

	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(goodsMarkService.getGridData(request));
	}

	/**
	 * 保存
	 * 
	 * @param goodsMark
	 * @param op
	 *            add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute GoodsMark goodsMark,
			@RequestParam String op) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			map.put("code", "1");
			map.put("msg", "保存成功！");
			
			goodsMarkService.save(op, goodsMark);

			addSaveLog(op, goodsMark, OpRresult.success + "", "添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, goodsMark, OpRresult.fail + "", "添加失败！");
		}

		outJson(map);
	}

	/**
	 * 添加保存操作日志
	 * 
	 * @param op
	 * @param goodsMark
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op, GoodsMark goodsMark, String opResult,
			String memo) {

		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "商品标签", OpType.add
					+ "", opResult, memo, goodsMark.getId() + "",
					BusinessTableName.goodsMark.name);
		} else {

			sysLogService.addLog(request, getLoginUser(), "商品标签", OpType.modify
					+ "", opResult, memo, goodsMark.getId() + "",
					BusinessTableName.goodsMark.name);
		}
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute GoodsMark goodsMark) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			if (goodsMarkInfoDao.getRowsByMarkId(goodsMark.getId()) > 0) {

				map.put("code", "0");
				map.put("msg", "当前标签下面有详细，不可删除！");
				outJson(map);
				return;
			}

			map.put("code", "1");
			map.put("msg", "删除成功！");

			GoodsMark goodsMark2 = goodsMarkDao.get(goodsMark.getId());
			goodsMark2.setStatus(-1);
			goodsMarkDao.update(goodsMark2);

			sysLogService.addLog(request, getLoginUser(), "商品标签", OpType.delete
					+ "", OpRresult.success + "", "删除成功！", goodsMark.getId()
					+ "", BusinessTableName.goodsMark.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "商品标签", OpType.delete
					+ "", OpRresult.fail + "", "删除失败！", goodsMark.getId() + "",
					BusinessTableName.goodsMark.name);
		}
		outJson(map);
	}

}
