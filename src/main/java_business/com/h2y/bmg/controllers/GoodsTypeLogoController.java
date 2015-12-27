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
import com.h2y.bmg.dao.IGoodsTypeDao;
import com.h2y.bmg.dao.IGoodsTypeLogoDao;
import com.h2y.bmg.entity.GoodsTypeLogo;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IGoodsTypeLogoService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.dict.DictUtil;
import com.h2y.util.JSONUtil;

/**
 * 类描述：商品类型Logo @Controller 作者：侯飞龙 时间：2015年1月7日上午10:44:05 邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/business/goodstypelogo")
@Scope("prototype")
public class GoodsTypeLogoController extends BaseController {

	private final static Logger logger = Logger
			.getLogger(GoodsTypeLogoController.class);

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected IGoodsTypeDao goodsTypeDao;

	@Autowired
	protected IGoodsTypeLogoDao goodsTypeLogoDao;

	@Autowired
	protected IGoodsTypeLogoService goodsTypeLogoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true)); // true:允许输入空值，false:不能为空值
	}

	/**
	 * 页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/goodstypelogo/init");

		SysUser sysUser = getLoginUser();

		List<Map<String, Object>> treeList = goodsTypeDao.getAllTreeList();
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
	 * @param goodsTypeLogo
	 * @param op
	 *            操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute GoodsTypeLogo goodsTypeLogo,
			@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify")) {

			goodsTypeLogo = goodsTypeLogoDao.get(goodsTypeLogo.getId());
		}

		view.addObject("goodsTypeLogo", goodsTypeLogo);
		view.addObject("op", op);
		view.setViewName("business/goodstypelogo/add");
		view.addObject("validationRules", sysValidationService
				.getValidationByCode("goodstypelogo_validate"));
		return view;
	}

	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(goodsTypeLogoService.getGridData(request));
	}

	/**
	 * 保存
	 * 
	 * @param goodsTypeLogo
	 * @param op
	 *            add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute GoodsTypeLogo goodsTypeLogo,
			@RequestParam String op) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			if (!op.equals("add") && !op.equals("modify")) {
				map.put("code", "0");
				map.put("msg", "无效操作符：op:" + op);
				outJson(map);
			}

			SysDictMain sysDictMain = DictUtil
					.getMainByCode("goodsTypeLogo_path");

			if (null == sysDictMain || null == sysDictMain.getDictValue()
					|| "".equals(sysDictMain.getDictValue())) {

				map.put("code", "0");
				map.put("msg", "请在字典中维护商品类型Logo存储路径，编码为：goodsTypeLogo_path");
				outJson(map);
				return;
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", goodsTypeLogo.getId());
			params.put("op", op);
			params.put("typeId", goodsTypeLogo.getTypeId());
			if (goodsTypeLogo.getIsDefault() == 1) {
				if (goodsTypeLogoDao.getDefaultRows(params) > 0) {
					map.put("code", "0");
					map.put("msg", "当前类型下面已有默认Logo，不可重复维护！");
					outJson(map);
					return;
				}
			} else {
				params.put("startDate", goodsTypeLogo.getStartDate());
				params.put("endDate", goodsTypeLogo.getEndDate());
				if (goodsTypeLogoDao.getDateConflictRows(params) > 0) {
					map.put("code", "0");
					map.put("msg", "类型Logo时间有冲突，请调整时间！");
					outJson(map);
					return;
				}
			}

			map.put("code", "1");
			map.put("msg", "保存成功！");

			SysUser sysUser = getLoginUser();

			goodsTypeLogoService.save(request, op, goodsTypeLogo, sysDictMain,
					sysUser);

			addSaveLog(op, goodsTypeLogo, OpRresult.success + "", "添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, goodsTypeLogo, OpRresult.fail + "", "添加失败！");
		}

		outJson(map);
	}

	/**
	 * 添加保存操作日志
	 * 
	 * @param op
	 * @param goodsTypeLogo
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op, GoodsTypeLogo goodsTypeLogo,
			String opResult, String memo) {

		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "商品类型Logo",
					OpType.add + "", opResult, memo,
					goodsTypeLogo.getId() + "",
					BusinessTableName.goodsTypeLogo.name);
		} else {

			sysLogService.addLog(request, getLoginUser(), "商品类型Logo",
					OpType.modify + "", opResult, memo, goodsTypeLogo.getId()
							+ "", BusinessTableName.goodsTypeLogo.name);
		}
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute GoodsTypeLogo goodsTypeLogo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "删除成功！");

			GoodsTypeLogo goodsTypeLogo2 = goodsTypeLogoDao.get(goodsTypeLogo
					.getId());
			goodsTypeLogo2.setStatus(-1);
			goodsTypeLogoDao.update(goodsTypeLogo2);

			sysLogService.addLog(request, getLoginUser(), "商品类型Logo",
					OpType.delete + "", OpRresult.success + "", "删除成功！",
					goodsTypeLogo.getId() + "",
					BusinessTableName.goodsTypeLogo.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "商品类型Logo",
					OpType.delete + "", OpRresult.fail + "", "删除失败！",
					goodsTypeLogo.getId() + "",
					BusinessTableName.goodsTypeLogo.name);
		}
		outJson(map);
	}
}
