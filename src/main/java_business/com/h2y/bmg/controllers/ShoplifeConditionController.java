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
import com.h2y.bmg.dao.IShoplifeConditionDao;
import com.h2y.bmg.entity.ShoplifeCondition;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IShoplifeConditionService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.dict.DictUtil;
import com.h2y.util.JSONUtil;

/**
 * 生活+ 筛选条件
 * @author sunfj
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/business/shoplifecondition")
public class ShoplifeConditionController extends BaseController{

	private final static Logger logger = Logger.getLogger(ShoplifeConditionController.class);

	@Autowired
	protected IShoplifeConditionService shoplifeConditionService;

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected IShoplifeConditionDao shoplifeConditionDao;


	/**
	 * 页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/shoplifecondition/init");

		// 获取登陆用户
		SysUser sysUser = getLoginUser();

		// 获取类型树
		List<Map<String, Object>> treeList = shoplifeConditionDao.getAllTreeList();

		if (treeList == null) {
			treeList = new ArrayList<Map<String, Object>>();
		}
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("id", 0);
		root.put("text", "筛选条件");
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
	 * @param shoplifeCondition
	 * @param op
	 *            操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute ShoplifeCondition shoplifeCondition,
			@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify")) {
			shoplifeCondition = shoplifeConditionDao.get(shoplifeCondition.getId());
		}

		view.addObject("shoplifeCondition", shoplifeCondition);
		view.addObject("op", op);
		view.setViewName("business/shoplifecondition/add");
		view.addObject("validationRules",sysValidationService.getValidationByCode("shoplifecondition_validate"));
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

		List<Map<String, Object>> treeList = shoplifeConditionDao.getChildTreeListById(id);
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

		List<Map<String, Object>> selectList = shoplifeConditionDao.getChildSelectListById(id);
		outJson(selectList);
	}

	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(shoplifeConditionService.getGridData(request));
	}

	/**
	 * 保存
	 * 
	 * @param shoplifeCondition
	 * @param op
	 *            add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute ShoplifeCondition shoplifeCondition,
			@RequestParam String op) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			if (!op.equals("add") && !op.equals("modify")) {
				map.put("code", "0");
				map.put("msg", "无效操作符：op:" + op);
				outJson(map);
			}

			SysDictMain sysDictMain = DictUtil.getMainByCode("shoplifeCondition_path");

			if (null == sysDictMain || null == sysDictMain.getDictValue()
					|| "".equals(sysDictMain.getDictValue())) {

				map.put("code", "0");
				map.put("msg", "请在字典中维护生活+筛选条件存储路径，编码为：shoplifeCondition_path");
				outJson(map);
				return;
			}

			shoplifeCondition.setUserId(getLoginUserId());
			List<Map<String,Object>> sameList = shoplifeConditionDao.getSameList(shoplifeCondition);
			if(null != sameList && !sameList.isEmpty()){
				map.put("code", "0");
				map.put("msg", "该条件已经存在，请重新添加");
				outJson(map);
				return;
			}else{
				shoplifeConditionService.save(request,op, shoplifeCondition,sysDictMain.getDictValue());

				map.put("code", "1");
				map.put("msg", "保存成功！");
			}
			addSaveLog(op, shoplifeCondition, OpRresult.success + "", "添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, shoplifeCondition, OpRresult.fail + "", "添加失败！");
		}

		outJson(map);
	}

	/**
	 * 添加保存操作日志
	 * 
	 * @param op
	 * @param shoplifeCondition
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op, ShoplifeCondition shoplifeCondition, String opResult,
			String memo) {

		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "商品类型", OpType.add
					+ "", opResult, memo, shoplifeCondition.getId() + "",
					BusinessTableName.shoplifeCondition.name);
		} else {

			sysLogService.addLog(request, getLoginUser(), "商品类型", OpType.modify
					+ "", opResult, memo, shoplifeCondition.getId() + "",
					BusinessTableName.shoplifeCondition.name);
		}
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute ShoplifeCondition shoplifeCondition) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			// 检验是否有子级
			if (shoplifeConditionDao.getChildRowsById(shoplifeCondition.getId()) > 0) {
				map.put("code", "0");
				map.put("msg", "当前节点有子级不可删除！");
				outJson(map);
				return;
			}

			ShoplifeCondition shoplifeCondition2 = shoplifeConditionDao.get(shoplifeCondition.getId());

			map.put("code", "1");
			map.put("msg", "删除成功！");
			shoplifeCondition2.setStatus(-1);
			shoplifeConditionDao.update(shoplifeCondition2);

			sysLogService.addLog(request, getLoginUser(), "商品类型", OpType.delete
					+ "", OpRresult.success + "", "删除成功！", shoplifeCondition.getId()
					+ "", BusinessTableName.shoplifeCondition.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "商品类型", OpType.delete
					+ "", OpRresult.fail + "", "删除失败！", shoplifeCondition.getId() + "",
					BusinessTableName.shoplifeCondition.name);
		}
		outJson(map);
	}


}
