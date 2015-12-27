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
import com.h2y.bmg.dao.IFindServiceDao;
import com.h2y.bmg.entity.FindService;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IFindServiceService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
import com.h2y.dict.DictUtil;
import com.h2y.util.JSONUtil;


/**
 * 类描述：发现服务 @Controller  
 * 作者：侯飞龙
 * 时间：2015年1月7日上午10:44:05
 * 邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/business/findservice")
@Scope("prototype")
public class FindServiceController  extends BaseController{

	private final static Logger logger = Logger.getLogger(FindServiceController.class);

	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected IFindServiceDao findServiceDao;

	@Autowired
	protected IFindServiceService findServiceService;

	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/findservice/init");

		SysUser sysUser = getLoginUser();

		//服务类型：0：公共服务（无需分配） 1：私有服务（可分配）
		String serviceType = request.getParameter("serviceType");
		view.addObject("serviceType",serviceType);

		List<Map<String,Object>> treeList = findServiceService.getTreeList(serviceType);
		view.addObject("treedata",JSONUtil.getJson(treeList));

		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}

	/**
	 * 编辑页面初始化
	 * @param findService
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute FindService findService,@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify") || op.equals("detail")) {

			findService = findServiceDao.get(findService.getId());
		}		

		if (op.equals("detail")) {
			if(0 == findService.getDataType()){//元数据
				view.setViewName("business/findservice/detail");
			}else{
				view.setViewName("business/findservice/detailService");
			}

		}else{ 
			if(op.equals("add")){
				//dataType 服务类型：0：元数据 1：正常服务
				String dataType = request.getParameter("dataType");

				//服务类型：0：公共服务（无需分配） 1：私有服务（可分配）
				String serviceType = request.getParameter("serviceType");

				findService.setServiceType(Integer.parseInt(serviceType));

				findService.setDataType(Integer.parseInt(dataType));
			}
			if(0 == findService.getDataType()){//元数据

				view.setViewName("business/findservice/add");
				view.addObject("validationRules", sysValidationService.getValidationByCode("findservice_validate"));
			}else{

				view.setViewName("business/findservice/addService");
				view.addObject("validationRules", sysValidationService.getValidationByCode("findservice_validate"));
			}

		}

		view.addObject("findService",findService);
		view.addObject("op",op);
		return view;
	}


	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList(long parentId) {

		outJson(findServiceService.getGridData(request, parentId));
	}

	/**
	 * 保存
	 * @param findService 
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute FindService findService,@RequestParam String op) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (!op.equals("add") && !op.equals("modify")) {
				map.put("code", "0");
				map.put("msg", "无效操作符：op:"+op);
				outJson(map);
			}

			SysDictMain sysDictMain = DictUtil.getMainByCode("findService_path");

			if (null==sysDictMain || null==sysDictMain.getDictValue() || "".equals(sysDictMain.getDictValue())) {

				map.put("code", "0");
				map.put("msg", "请在字典中维护发现服务Logo存储路径，编码为：findService_path");
				outJson(map);
				return;
			}

			Map<String,Object> params = new HashMap<String, Object>();
			params.put("id", findService.getId());
			params.put("op", op);
			params.put("serviceCode", findService.getServiceCode());
			params.put("memo", findService.getMemo());

			if (findServiceDao.getSameServiceCodeRows(params)>0) {

				map.put("code", "0");
				map.put("msg", "当前服务编码重复，请使用其他编码！");
				outJson(map);
				return;
			}

			map.put("code", "1");
			map.put("msg", "保存成功！");


			//dataType 服务类型：0：元数据 1：正常服务
			if(findService.getDataType() == 0){
				findServiceService.save(request, op, findService);
			}else{
				findServiceService.saveService(request, op, findService, sysDictMain);
			}


			addSaveLog(op, findService, OpRresult.success+"", "添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog(op, findService, OpRresult.fail+"", "添加失败！");
		}

		outJson(map);
	}


	/**
	 * 添加保存操作日志
	 * @param op
	 * @param findService
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op,FindService findService,String opResult,String memo){

		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "发现服务", OpType.add+"", 
					opResult, memo, findService.getId()+"", BusinessTableName.findService.name);
		}else {

			sysLogService.addLog(request, getLoginUser(), "发现服务", OpType.modify+"", 
					opResult, memo, findService.getId()+"", BusinessTableName.findService.name);
		}
	}


	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute FindService findService) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("code", "1");
			map.put("msg", "删除成功！");

			FindService findService2 = findServiceDao.get(findService.getId());
			findService2.setStatus(-1);
			findServiceDao.update(findService2);

			sysLogService.addLog(request, getLoginUser(), "发现服务", OpType.delete+"", 
					OpRresult.success+"", "删除成功！", findService.getId()+"", BusinessTableName.findService.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "发现服务", OpType.delete+"", 
					OpRresult.fail+"", "删除失败！", findService.getId()+"", BusinessTableName.findService.name);
		}
		outJson(map);
	}
}
