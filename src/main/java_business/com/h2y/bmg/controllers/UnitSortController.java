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
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.UnitSort;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.services.IUnitSortService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.util.JSONUtil;


@Controller
@RequestMapping(value="/business/unitsort")
@Scope("prototype")
public class UnitSortController extends BaseController{

	private final static Logger logger = Logger.getLogger(UnitSortController.class);


	@Autowired
	protected IUnitSortService unitSortService;

	@Autowired
	protected ISysLogService sysLogService;

	/**
	 * 页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/unitsort/init");

		// 获取登陆用户
		SysUser sysUser = getLoginUser();

		// 获取类型树
		view.addObject("treedata", JSONUtil.getJson(this.unitSortService.getAllTreeList()));

		view.addObject("toolbar", sysButtonService.getMenuButtons(request,getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns",sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByRequest(request,sysUser.getUnitId()));
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
	public ModelAndView add(@ModelAttribute UnitSort unitSort,
			@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify")) {
			unitSort = this.unitSortService.get(unitSort.getId());
		}

		view.addObject("unitSort", unitSort);
		view.addObject("op", op);
		view.setViewName("business/unitsort/add");								   
		view.addObject("validationRules",sysValidationService.getValidationByCode("unitsort_validate"));
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

		List<Map<String, Object>> treeList = unitSortService.getChildTreeListById(id);
		outJson(treeList);
	}

	/**
	 * 得到子级下拉框数据
	 * 
	 * @param id
	 *            当前节点Id
	 */
	@RequestMapping(value = "/getSelectList")
	public void getSelectList(long id) {

		List<Map<String, Object>> selectList = unitSortService.getChildSelectListById(id);
		outJson(selectList);
	}

	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(unitSortService.getGridData(request));
	}

	@RequestMapping(value = "/save")
	public void save(@ModelAttribute UnitSort unitSort,
			@RequestParam String op) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {


			if (unitSortService.getUnitSortByCode(unitSort).size() > 0) {
				map.put("code", "0");
				map.put("msg", "该编码已存在，请重新添加！");
				outJson(map);
				return;
			}

			map.put("code", "1");
			map.put("msg", "保存成功！");

			if (unitSortService.getRowsByParentId(unitSort.getId()).size() > 0 && "modify".equals(op) && unitSort.getStatus() == 1) {
				map.put("code", "0");
				map.put("msg", "该分类下有子分类，不能停用！");
				outJson(map);
				return;
			}

			unitSortService.save(op, unitSort);

			addSaveLog(op, unitSort, OpRresult.success + "", "添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, unitSort, OpRresult.fail + "", "添加失败！");
		}

		outJson(map);
	}



	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute UnitSort unitSort) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			if (unitSortService.getRowsByParentId(unitSort.getId()).size() > 0) {

				map.put("code", "0");
				map.put("msg", "当前分类下有子分类，不可删除！");
				outJson(map);
				return;
			}

			map.put("code", "1");
			map.put("msg", "删除成功！");

			UnitSort unitSort2 = unitSortService.get(unitSort.getId());
			unitSort2.setStatus(-1);
			unitSortService.update(unitSort2);

			sysLogService.addLog(request, getLoginUser(), "单位分类", OpType.delete
					+ "", OpRresult.success + "", "删除成功！", unitSort.getId()
					+ "", BusinessTableName.unitSort.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "单位分类", OpType.delete
					+ "", OpRresult.fail + "", "删除失败！", unitSort.getId() + "",
					BusinessTableName.unitSort.name);
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
	private void addSaveLog(String op, UnitSort unitSort, String opResult,
			String memo) {

		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "单位经营分类", OpType.add
					+ "", opResult, memo, unitSort.getId() + "",
					BusinessTableName.unitSort.name);
		} else {

			sysLogService.addLog(request, getLoginUser(), "单位经营分类", OpType.modify
					+ "", opResult, memo, unitSort.getId() + "",
					BusinessTableName.unitSort.name);
		}
	}


	/**
	 * 单位分类
	 * 
	 * @return
	 */
	@RequestMapping(value = "/unitSortInit")
	public ModelAndView unitSortInit() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/unitsort/unitSortInit");
		view.addObject("selectType", request.getParameter("selectType"));
		view.addObject("treedata", JSONUtil.getJson(unitSortService.getAllTreeList()));
		return view;
	}


	/**
	 * 得到详细选择的列表数据
	 * 
	 * @param markId
	 *            标签Id
	 */
	@RequestMapping(value = "/getListMapById")
	public void getListMapById() {

		Map<String, Object> params = new HashMap<String, Object>();
		String id = request.getParameter("id");
		if(null != id && !"".equals(id)){
			params.put("id", Long.valueOf(id));
		}

		String searchName = request.getParameter("searchName");

		if (null != searchName) {
			params.put("sortName", "%" + searchName + "%");
		}
		outJson(unitSortService.getListMapById(params));
	}

}
