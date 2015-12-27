package com.h2y.bmg.controllers;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.IMemberUserDao;
import com.h2y.bmg.dao.ISysRoleDao;
import com.h2y.bmg.dao.ISysUnitsDao;
import com.h2y.bmg.dao.ISysUnitsFileDao;
import com.h2y.bmg.dao.IZoneDao;
import com.h2y.bmg.entity.MemberUser;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.Zone;
import com.h2y.bmg.services.IMemberUserService;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.services.ISysUnitsService;
import com.h2y.bmg.services.IUnitSortService;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.util.DateUtil;


@Controller
@RequestMapping(value = "/business/memberuser")
@Scope("prototype")
public class MemberUserController extends BaseController{

	@Autowired
	protected IMemberUserService memberUserService;

	@Autowired
	protected ISysUnitsService sysUnitsService;

	private final static Logger logger = Logger.getLogger(MemberUserController.class);

	@Autowired
	protected ISysLogService sysLogService;
	@Autowired
	protected ISysRoleDao sysRoleDao;


	@Autowired
	protected ISysUnitsDao sysUnitsDao;

	@Autowired
	protected ISysUnitsFileDao sysUnitsFileDao;

	@Autowired
	protected IUnitSortService unitSortService;

	@Autowired
	protected IZoneDao zoneDao;

	@Autowired
	protected IMemberUserDao memberUserDao;



	/**
	 * 页面初始化
	 * @return
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();

		SysUser sysUser = getLoginUser();

		view.addObject("toolbar", sysButtonService.getMenuButtons(request,getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns",sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByRequest(request,sysUser.getUnitId()));


		view.setViewName("business/memberuser/init");

		return view;
	}


	/**
	 * 获取会员列表
	 */
	@RequestMapping(value = "/getList")
	public void getList(){
		outJson(memberUserService.getList(request));
	}


	@RequestMapping(value = "/modifyStatus")
	public void modifyStatus(String dataIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();

		String opName = "";
		int status = -1;
		params.put("updateDate", DateUtil.getSystemTime());



		map.put("code", "1");
		map.put("msg", opName + "成功！");

		if (null == dataIds || "".equals(dataIds)) {
			return;
		}

		List<Long> ids = new ArrayList<Long>();
		String dataId_array[] = dataIds.split(",");
		if (dataId_array.length > 0) {
			for (String string : dataId_array) {
				ids.add(Long.valueOf(string));
			}
		}

		params.put("status", status);
		params.put("ids", ids);

		memberUserService.updateStatusByIds(params);
		sysLogService.addLog(request, getLoginUser(), "商品定价", "modify",OpRresult.success + "", opName + "成功！", dataIds,BusinessTableName.memberUser.name);

		outJson(map);
	}



	@RequestMapping(value = "/detail")
	public ModelAndView detail() {
		String id = request.getParameter("id");

		ModelAndView view = new ModelAndView();
		MemberUser memberUser = memberUserService.get(Long.valueOf(id));

		view.addObject("memberUser", memberUser);

		view.setViewName("business/memberuser/detail");

		return view;
	}

	@RequestMapping("/recommendInit")
	public ModelAndView recommendInit(@RequestParam String account){
		ModelAndView view = new ModelAndView();
		view.setViewName("business/memberuser/recommendInit");
		view.addObject("account", account);
		view.addObject("gridComluns",sysGridColumnsService.getGridColumsByCode("memberreflist_grid"));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByCode("member_ref_search",getLoginUnitId()));
		return view;
	}

	@RequestMapping("/getRecommendList")
	public void getRecommendList(String account){
		outJson(memberUserService.getMemberRecommendList(request,account));
	}

	/**
	 * 区域修改
	 * @param account
	 */
	@RequestMapping("/zoneUpdate")
	public ModelAndView zoneUpdate(){
		ModelAndView view = new ModelAndView();
		view.setViewName("business/memberuser/zoneUpdate");
		view.addObject("account", request.getParameter("account"));
		String zoneCode = request.getParameter("zoneCode");
		if (zoneCode != null && zoneCode != "") {

			Zone zone = zoneDao.getZoneByCode(zoneCode);
			view.addObject("zoneName", zone.getName());
			view.addObject("zonePrefix", zone.getPreFix());
		}
		else {
			view.addObject("zoneName", "当前用户无区域");
		}
		view.addObject("zoneList", sysUnitsService.getFirstZoneList());
		return view;
	}

	/**
	 * 区域修改保存
	 * @param account
	 */
	@RequestMapping("/zoneUpdateSave")
	public void  zoneUpdateSave(){
		Map<String, Object> map = new HashMap<String, Object>();
		String account = request.getParameter("account");
		String zoneCode = request.getParameter("zoneCode");
		try {
			//用户区域修改
			MemberUser memberUser = memberUserDao.getByAccount(account);
			memberUser.setZone(zoneCode);
			memberUserDao.update(memberUser);
			//与用户关联的一级用户区域修改
			map.put("account", account);
			List<String> refAccountList1 = memberUserDao.getRefAccountList(map);
			for(String tmpAccount1 : refAccountList1){
				MemberUser tmpMember1 = memberUserDao.getByAccount(tmpAccount1);
				tmpMember1.setZone(zoneCode);
				memberUserDao.update(tmpMember1);
				//与用户关联的二级用户区域修改
				map.put("account", tmpAccount1);
				List<String> refAccountList2 = memberUserDao.getRefAccountList(map);
				for(String tmpAccount2 : refAccountList2){
					MemberUser tmpMember2 = memberUserDao.getByAccount(tmpAccount2);
					tmpMember2.setZone(zoneCode);
					memberUserDao.update(tmpMember2);
					//与用户关联的三级用户区域修改
					map.put("account", tmpAccount2);
					List<String> refAccountList3 = memberUserDao.getRefAccountList(map);
					for(String tmpAccount3 : refAccountList3){
						MemberUser tmpMember3 = memberUserDao.getByAccount(tmpAccount3);
						tmpMember3.setZone(zoneCode);
						memberUserDao.update(tmpMember3);
					}
				}
			}
			map.put("code", "1");
			map.put("msg", "修改成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "修改失败！");
		}
		outJson(map);
	}

}
