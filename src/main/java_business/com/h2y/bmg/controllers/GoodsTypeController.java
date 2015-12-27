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
import com.h2y.bmg.dao.IGoodsMarkDao;
import com.h2y.bmg.dao.IGoodsTypeDao;
import com.h2y.bmg.entity.GoodsType;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IGoodsTypeService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;

/**
 * 类描述：商品类型控制 作者：侯飞龙 时间：2015年1月7日上午10:44:05 邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/business/goodstype")
@Scope("prototype")
public class GoodsTypeController extends BaseController {

	private final static Logger logger = Logger
			.getLogger(GoodsTypeController.class);

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected IGoodsTypeDao goodsTypeDao;

	@Autowired
	protected IGoodsTypeService goodsTypeService;

	@Autowired
	protected IGoodsMarkDao goodsMarkDao;

	/**
	 * 页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/goodstype/init");
		// 获取登陆用户
		SysUser sysUser = getLoginUser();
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
	 * @param goodsType
	 * @param op
	 *            操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute GoodsType goodsType,
			@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify")) {
			goodsType = goodsTypeDao.get(goodsType.getId());
		}

		view.addObject("goodsType", goodsType);
		view.addObject("op", op);
		view.setViewName("business/goodstype/add");
		view.addObject("validationRules",
				sysValidationService.getValidationByCode("goodstype_validate"));
		return view;
	}

	/**
	 * 得到子级树数据
	 * 
	 * @param id
	 *            当前节点Id
	 */
	@RequestMapping(value = "/getTreeList")
	public void getTreeList(long id) {

		List<Map<String, Object>> treeList = goodsTypeDao
				.getChildTreeListById(id);
		outJson(treeList);
	}

	/**
	 * 得到子级下拉框数据
	 * 
	 * @param id
	 *            当前节点Id
	 */
	@RequestMapping(value = "/getSelectList")
	public void getSelectList() {

		List<Map<String, Object>> selectList = goodsTypeDao.getChildSelectListById(this.getLoginUnitId());
		outJson(selectList);
	}

	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(goodsTypeService.getGridData(request,this.getLoginUnitId()));
	}

	/**
	 * 保存
	 * 
	 * @param goodsType
	 * @param op
	 *            add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute GoodsType goodsType,
			@RequestParam String op) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			goodsType.setParentId(this.getLoginUnitId());
			map.put("code", "1");
			map.put("msg", "保存成功！");
			goodsTypeService.save(op, goodsType);
			addSaveLog(op, goodsType, OpRresult.success + "", "添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, goodsType, OpRresult.fail + "", "添加失败！");
		}

		outJson(map);
	}

	/**
	 * 添加保存操作日志
	 * 
	 * @param op
	 * @param goodsType
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op, GoodsType goodsType, String opResult,
			String memo) {

		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "商品类型", OpType.add
					+ "", opResult, memo, goodsType.getId() + "",
					BusinessTableName.goodsType.name);
		} else {

			sysLogService.addLog(request, getLoginUser(), "商品类型", OpType.modify
					+ "", opResult, memo, goodsType.getId() + "",
					BusinessTableName.goodsType.name);
		}
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute GoodsType goodsType) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			GoodsType goodsType2 = goodsTypeDao.get(goodsType.getId());
			// 检验是否有标签
			if (goodsMarkDao.getRowsByTypeCode(goodsType2.getTypeCode()) > 0) {
				map.put("code", "0");
				map.put("msg", "当前节点有标签不可删除！");
				outJson(map);
				return;
			}

			map.put("code", "1");
			map.put("msg", "删除成功！");
			goodsType2.setStatus(-1);
			goodsTypeDao.update(goodsType2);

			sysLogService.addLog(request, getLoginUser(), "商品类型", OpType.delete
					+ "", OpRresult.success + "", "删除成功！", goodsType.getId()
					+ "", BusinessTableName.goodsType.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "商品类型", OpType.delete
					+ "", OpRresult.fail + "", "删除失败！", goodsType.getId() + "",
					BusinessTableName.goodsType.name);
		}
		outJson(map);
	}
}
