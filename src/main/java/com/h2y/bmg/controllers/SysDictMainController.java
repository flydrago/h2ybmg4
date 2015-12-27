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
import com.h2y.bmg.dao.ISysDictMainDao;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.services.ISysDictMainService;
import com.h2y.bmg.services.ISysRoleService;
import com.h2y.dict.DictUtil;
import com.h2y.util.JSONUtil;

/**
 * 字典主表管理url跳转控制类

 * 作者：侯飞龙

 * 时间：2014-10-13 上午11:31:51

 * 电子邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/sys/sysdictmain")
@Scope("prototype")
public class SysDictMainController  extends BaseController{

	private final static Logger logger = Logger.getLogger(SysDictMainController.class);

	@Autowired
	protected ISysRoleService sysRoleService;

	@Autowired
	protected ISysDictMainService sysDictMainService;

	@Autowired
	protected ISysDictMainDao sysDictMainDao;

	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/sysdictmain/init");
		
		List<Map<String,Object>> treeList = sysDictMainDao.getSysDictMainFirstTreeData();
		if (null==treeList) {
			treeList = new ArrayList<Map<String,Object>>();
		}
		Map<String,Object> nodeData = new HashMap<String, Object>();
		nodeData.put("id", 0);
		nodeData.put("text", "字典");
		treeList.add(nodeData);
		
		//字典测试
		logger.info("字典列表测试（不可继承）："+DictUtil.getDetailListByMainCode(1, "xueli"));
		logger.info("字典列表测试（不可继承）："+DictUtil.getDetailListByMainCode(14, "xueli"));
		logger.info("字典主表测试（不可继承）："+DictUtil.getMainByCode("imageuploadroot_url"));
		logger.info("字典列表测试（可继承）："+DictUtil.getDetailListByMainCode(1, "goods_params"));
		logger.info("字典列表测试（可继承）："+DictUtil.getDetailListByMainCode(14, "goods_params"));
		logger.info("字典列表测试（可继承）："+DictUtil.getDetailListByMainCode(16, "goods_params"));
		logger.info("字典子表详细测试（可继承）："+DictUtil.getDetailByCode(1, "goods_params", "fee"));
		logger.info("字典子表详细测试（可继承）："+DictUtil.getDetailByCode(14, "goods_params", "fee"));
		logger.info("字典子表详细测试（可继承）："+DictUtil.getDetailByCode(16, "goods_params", "fee"));
		
		
		view.addObject("treedata", JSONUtil.getJson(treeList));
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		return view;
	}


	/**
	 * 编辑页面初始化
	 * @param sysDictMain
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute SysDictMain sysDictMain,@RequestParam String op) {

		ModelAndView view = new ModelAndView();
		view.addObject("validationRules", sysValidationService.getValidationByCode("sysdictmain_validate"));

		if (op.equals("modify")) {
			sysDictMain = sysDictMainService.get(sysDictMain.getId());
		}
		view.addObject("sysDictMain",sysDictMain);
		view.addObject("op",op);
		view.setViewName("sys/sysdictmain/add");
		return view;
	}



	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {
		outJson(sysDictMainService.getGridData(request));
	}


	/**
	 * 保存
	 * @param sysDepartment 部门对象
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute SysDictMain sysDictMain,@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			if (sysDictMainService.isHasSameCode(sysDictMain, op)) {
				map.put("code", "0");
				map.put("msg", "字典编码重复，请使用其他编码！");
			}else {

				map.put("code", "1");
				map.put("msg", "保存成功！");

				if (op.equals("add")) {
					
					sysDictMainService.add(sysDictMain);
					if (sysDictMain.getParentId()==0) {
						sysDictMain.setDictPrefix(sysDictMain.getId()+"");
					}else {
						SysDictMain parentDictMain = sysDictMainService.get(sysDictMain.getParentId());
						sysDictMain.setDictPrefix(parentDictMain.getId()+"_"+sysDictMain.getId());
					}
					sysDictMainService.update(sysDictMain);
				}else {

					SysDictMain sysDictMain2 = sysDictMainService.get(sysDictMain.getId());
					sysDictMain2.setDictCode(sysDictMain.getDictCode());
					sysDictMain2.setDictName(sysDictMain.getDictName());
					sysDictMain2.setDictOrd(sysDictMain.getDictOrd());
					sysDictMain2.setDictType(sysDictMain.getDictType());
					sysDictMain2.setDictValue(sysDictMain.getDictValue());
					sysDictMain2.setIfSys(sysDictMain.getIfSys());
					sysDictMain2.setIfUserConf(sysDictMain.getIfUserConf());
					sysDictMain2.setIsExtends(sysDictMain.getIsExtends());
					sysDictMainService.update(sysDictMain2);
				}
				//加载缓存
				sysDictMainService.loadDictDataToCache();
			}
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
	public void delete(@ModelAttribute SysDictMain sysDictMain) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("code", "1");
			map.put("msg", "删除成功！");

			
			if (sysDictMainDao.getDictDetailRowsByMainId(sysDictMain.getId())>0) {
				
				map.put("code", "0");
				map.put("msg", "当前字典主表有字典详细，不可删除！");
				outJson(map);
				return;
			}
			
			if (sysDictMainDao.getSysDictMainChildRows(sysDictMain.getId())>0) {
				
				map.put("code", "0");
				map.put("msg", "当前字典主表有子级不可删除！");
				outJson(map);
				return;
			}

			sysDictMainService.delete(sysDictMain.getId());
			sysDictMainService.loadDictDataToCache();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
		}
		outJson(map);
	}
}
