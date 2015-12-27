package com.h2y.bmg.controllers;

import java.util.HashMap;
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
import com.h2y.bmg.entity.ShareHrefDataRt;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.IShareHrefDataRtService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;

/**
 * 分享链接关联推广活动
 * @author sunfj
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value="business/sharehrefdatart")
public class ShareHrefDataRtController extends BaseController {

	private final static Logger logger = Logger.getLogger(ShareHrefDataRtController.class);

	@Autowired
	protected IShareHrefDataRtService shareHrefDataRtService;

	@Autowired
	protected ISysLogService sysLogService;


	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init(String hrefId) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/sharehrefdatart/init");
		view.addObject("hrefId",hrefId);
		SysUser sysUser = getLoginUser();
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("share_href_data_rt"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}


	/**
	 * 编辑页面初始化
	 * @param shareHrefDataRt
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute ShareHrefDataRt shareHrefDataRt,@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify")) {

			shareHrefDataRt = shareHrefDataRtService.get(shareHrefDataRt.getId());
			view.addObject("shareHrefDataRt",shareHrefDataRt);

		}

		view.addObject("shareHrefDataRt",shareHrefDataRt);


		view.addObject("op",op);
		view.setViewName("business/sharehrefdatart/add");
		return view;
	}


	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(shareHrefDataRtService.getGridData(request));
	}

	/**
	 * 保存
	 * 每一个分享链接只能维护一个推广活动，一对一关系
	 * 
	 * @param  
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(String hrefId,String str1) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "1");
		map.put("msg", "保存成功！");
		try{						

			ShareHrefDataRt shareHrefDataRt = shareHrefDataRtService.save(request, hrefId,str1);	
			addSaveLog("add", shareHrefDataRt, OpRresult.success+"", "推广添加成功！");	

		}catch(Exception e){
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog("add", new ShareHrefDataRt(), OpRresult.fail+"", "推广添加失败！");	
		}



		outJson(map);
	}


	@RequestMapping(value = "/saveDataRtOrd")
	public void saveDataRtOrd(@RequestParam long id,@RequestParam int ord) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "1");
		map.put("msg", "保存成功！");
		try{						

			ShareHrefDataRt shareHrefDataRt = shareHrefDataRtService.get(id);
			shareHrefDataRt.setOrd(ord);
			shareHrefDataRtService.update(shareHrefDataRt);
			addSaveLog("modify", shareHrefDataRt, OpRresult.success+"", "修改排序成功！");	

		}catch(Exception e){
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			addSaveLog("modify", new ShareHrefDataRt(), OpRresult.fail+"", "修改排序失败！");	
		}



		outJson(map);
	}




	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public void delete(@ModelAttribute  ShareHrefDataRt shareHrefDataRt) {
		Map<String, Object> map = new HashMap<String, Object>();

		try{
			map.put("code", "1");
			map.put("msg", "删除成功！");

			int flag = shareHrefDataRtService.delete(shareHrefDataRt.getId());
			if(flag <= 0){
				map.put("code", "0");
				map.put("msg", "删除失败！");
				sysLogService.addLog(request, getLoginUser(), "享活动关联", OpType.delete+"", 
						OpRresult.fail+"", "分享活动关联删除失败！", shareHrefDataRt.getId()+"", BusinessTableName.shareHrefDataRt.name);
			}else{
				sysLogService.addLog(request, getLoginUser(), "享活动关联", OpType.delete+"", 
						OpRresult.success+"", "分享活动关联删除成功！", shareHrefDataRt.getId()+"", BusinessTableName.shareHrefDataRt.name);
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "分享活动关联", OpType.delete+"", 
					OpRresult.success+"", "分享活动关联删除成功！", shareHrefDataRt.getId()+"", BusinessTableName.shareHrefDataRt.name);
		}
		outJson(map);
	}




	/**
	 * 查看关联活动
	 * @return
	 */
	@RequestMapping(value = "/promoteInit")
	public ModelAndView promoteInit(String hrefId) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/sharehrefdatart/promoteInit");

		SysUser sysUser = getLoginUser();
		view.addObject("hrefId", hrefId);
		view.addObject("selectType", request.getParameter("selectType"));
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("share_href"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}


	/**
	 * 活动列表
	 */
	@RequestMapping(value = "/getPromoteList")
	public void getPromoteList() {

		outJson(shareHrefDataRtService.getPromoteList(request,this.getLoginUnitId()));
	}



	/**
	 * 查看投票
	 * @return
	 */
	@RequestMapping(value = "/voteInit")
	public ModelAndView voteInit(String hrefId) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/votedatart/init");

		SysUser sysUser = getLoginUser();
		view.addObject("hrefId", hrefId);
		view.addObject("selectType", request.getParameter("selectType"));
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("vote_data_rt"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}


	/**
	 * 投票列表
	 */
	@RequestMapping(value = "/getVoteList")
	public void getVoteList() {

		outJson(shareHrefDataRtService.getVoteList(request,this.getLoginUnitId()));
	}


	/**
	 * 选择投票
	 * @return
	 */
	@RequestMapping(value = "/voteSelectInit")
	public ModelAndView voteSelectInit(String hrefId) {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/votedatart/voteInit");

		SysUser sysUser = getLoginUser();
		view.addObject("hrefId", hrefId);
		view.addObject("selectType", request.getParameter("selectType"));
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("vote_data"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByRequest(request, sysUser.getUnitId()));
		return view;
	}


	/**
	 * 投票列表
	 */
	@RequestMapping(value = "/getVoteSelectList")
	public void getVoteSelectList() {

		outJson(shareHrefDataRtService.getVoteSelectList(request,this.getLoginUnitId()));
	}

	/**
	 * 添加保存操作日志
	 * @param op
	 * @param commonActivity
	 * @param opResult
	 * @param memo
	 */
	private void addSaveLog(String op,ShareHrefDataRt shareHrefDataRt,String opResult,String memo){

		if (op.equals("add")) {

			sysLogService.addLog(request, getLoginUser(), "推广活动", OpType.add+"", 
					opResult, memo, shareHrefDataRt.getId()+"", BusinessTableName.shareHrefDataRt.name);
		}else {

			sysLogService.addLog(request, getLoginUser(), "推广活动", OpType.modify+"", 
					opResult, memo, shareHrefDataRt.getId()+"", BusinessTableName.shareHrefDataRt.name);
		}
	}


}
