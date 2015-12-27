package com.h2y.bmg.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.h2y.bmg.entity.GoodsMark;
import com.h2y.bmg.entity.GoodsMarkInfo;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.services.IGoodsMarkInfoService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.dict.DictUtil;

/**
 * 类描述：商品标签详细Controller 作者：侯飞龙 时间：2015年1月7日上午10:44:05 邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/business/goodsmarkinfo")
@Scope("prototype")
public class GoodsMarkInfoController extends BaseController {

	private final static Logger logger = Logger
			.getLogger(GoodsMarkInfoController.class);

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected IGoodsMarkInfoDao goodsMarkInfoDao;

	@Autowired
	protected IGoodsMarkInfoService goodsMarkInfoService;

	@Autowired
	protected IGoodsMarkDao goodsMarkDao;

	/**
	 * 页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init(long markId) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/goodsmarkinfo/init");

		view.addObject("markId", markId);
		view.addObject("gridComluns",
				sysGridColumnsService.getGridColumsByCode("goodsmarkinfo_grid"));
		view.addObject("conditionHtml",
				sysQueryItemService.getConditionHtmlByCode(
						"goodsmarkinfo_query", getLoginUnitId()));
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
	public ModelAndView add(@ModelAttribute GoodsMarkInfo goodsMarkInfo,
			@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify")) {

			goodsMarkInfo = goodsMarkInfoDao.get(goodsMarkInfo.getId());
		}

		//是否上传图片
		GoodsMark goodsMark = goodsMarkDao.get(goodsMarkInfo.getMarkId());
		if(null != goodsMark){
			view.addObject("isShowImg", goodsMark.getIsShowImg());
		}else{
			view.addObject("isShowImg", 0);//客户端是否图片显示 默认不显示
		}
		view.addObject("goodsMarkInfo", goodsMarkInfo);
		view.addObject("op", op);
		view.setViewName("business/goodsmarkinfo/add");
		view.addObject("validationRules", sysValidationService
				.getValidationByCode("goodsmarkinfo_validate"));
		return view;
	}

	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(goodsMarkInfoService.getGridData(request));
	}

	/**
	 * 保存
	 * 
	 * @param goodsMarkInfo
	 * @param op
	 *            add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute GoodsMarkInfo goodsMarkInfo,
			@RequestParam String op) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			map.put("code", "1");
			map.put("msg", "保存成功！");

			//图片路径
			SysDictMain sysDictMain = DictUtil.getMainByCode("goodsMarkInfo_path");

			if (sysDictMain!=null && StringUtils.isNotBlank(sysDictMain.getDictValue())) {
				//判断标签名称是否重复
				int sameMarkCount = goodsMarkInfoService.getSameMarkInfoName(goodsMarkInfo,op);
				if(sameMarkCount > 0){
					map.put("code", "0");
					map.put("msg", "\""+goodsMarkInfo.getName()+"\" 已经存在，请修改！");
				}else{
					goodsMarkInfoService.save(request,op, goodsMarkInfo,sysDictMain.getDictValue());

					addSaveLog(op, goodsMarkInfo, OpRresult.success + "", "添加成功！");
				}				


			}else{
				map.put("code", "0");
				map.put("msg", "请维护标签详细图片上传路径，编码为：goodsMarkInfo_path！");
			}


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, goodsMarkInfo, OpRresult.fail + "", "添加失败！");
		}

		outJson(map);
	}

	/**
	 * 添加保存操作日志
	 * 
	 * @param op
	 * @param goodsMarkInfo
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op, GoodsMarkInfo goodsMarkInfo,
			String opResult, String memo) {

		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "商品标签详细", OpType.add
					+ "", opResult, memo, goodsMarkInfo.getId() + "",
					BusinessTableName.goodsMarkInfo.name);
		} else {

			sysLogService.addLog(request, getLoginUser(), "商品标签详细",
					OpType.modify + "", opResult, memo, goodsMarkInfo.getId()
					+ "", BusinessTableName.goodsMarkInfo.name);
		}
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute GoodsMarkInfo goodsMarkInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "删除成功！");

			GoodsMarkInfo goodsMarkInfo2 = goodsMarkInfoDao.get(goodsMarkInfo
					.getId());
			goodsMarkInfo2.setStatus(-1);
			goodsMarkInfoDao.update(goodsMarkInfo2);

			sysLogService.addLog(request, getLoginUser(), "商品标签详细",
					OpType.delete + "", OpRresult.success + "", "删除成功！",
					goodsMarkInfo.getId() + "",
					BusinessTableName.goodsMarkInfo.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "商品标签详细",
					OpType.delete + "", OpRresult.fail + "", "删除失败！",
					goodsMarkInfo.getId() + "",
					BusinessTableName.goodsMarkInfo.name);
		}
		outJson(map);
	}

	/**
	 * 得到详细选择的列表数据
	 * 
	 * @param markId
	 *            标签Id
	 */
	@RequestMapping(value = "/getSelectList")
	public void getSelectList(long markId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("markId", markId);
		String searchName = request.getParameter("searchName");
		if (null != searchName) {
			params.put("searchName", "%" + searchName + "%");
		}
		outJson(goodsMarkInfoDao.getListMapByMarkId(params));
	}
}
