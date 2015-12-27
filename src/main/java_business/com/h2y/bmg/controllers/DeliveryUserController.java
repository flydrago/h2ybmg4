package com.h2y.bmg.controllers;

import java.util.Arrays;
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
import com.h2y.bmg.basic.WbsKeys.ZoneLevelKeys;
import com.h2y.bmg.dao.IDeliveryUserDao;
import com.h2y.bmg.dao.ISysUnitsDao;
import com.h2y.bmg.entity.DeliveryUser;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.Zone;
import com.h2y.bmg.services.IDeliveryUserService;
import com.h2y.bmg.services.ISysUnitsService;
import com.h2y.bmg.services.IZoneService;
import com.h2y.util.DateUtil;

/**
 * 配送员管理
 * 
 * @author sunfj
 * 
 */
@Controller
@RequestMapping(value = "/business/deliveryuser")
@Scope("prototype")
public class DeliveryUserController extends BaseController {

	private final static Logger logger = Logger
			.getLogger(DeliveryUserController.class);

	@Autowired
	protected IDeliveryUserService deliveryUserService;

	@Autowired
	protected ISysUnitsService sysUnitsService;
	
	@Autowired
	protected IDeliveryUserDao deliveryUserDao;

	@Autowired
	protected IZoneService zoneService;
	
	@Autowired
	protected ISysUnitsDao sysUnitsDao;
	
	/**
	 * 页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("business/deliveryuser/init");

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
	 * 获取列表
	 * 
	 * @param op
	 *            grid:表格列，tree:树
	 * @param parentCode
	 *            父级Id
	 */
	@RequestMapping(value = "/getList")
	public void getList() {
		outJson(deliveryUserService.getGridData(request, this.getLoginUnit()));
	}

	/**
	 * 审核
	 */
	@RequestMapping(value = "/check")
	public ModelAndView check() {
		ModelAndView view = new ModelAndView();
		DeliveryUser deliveryUser = deliveryUserService.get(Long.valueOf(request.getParameter("id")));


		view.setViewName("business/deliveryuser/check");
		view.addObject("op", request.getParameter("op"));
		view.addObject("unitList", deliveryUserDao.getPassUnitList());
		view.addObject("deliveryUser", deliveryUser);
		
		//当前选中配送员的单位信息
		String regZoneCode = deliveryUser.getRegZone();
		if( null != regZoneCode && !"".equals(regZoneCode) ){
			Zone currentZone = zoneService.getZoneByCode(regZoneCode);
			int currentLevel = Integer.valueOf(currentZone.getLevel());
			if(currentLevel == 5){
				view.addObject("countyZone",currentZone.getCode());
				Zone cityZone = zoneService.getZoneByCode(currentZone.getPcode());
				view.addObject("cityZone",cityZone.getCode());
				Zone provinceZone = zoneService.getZoneByCode(cityZone.getPcode());
				view.addObject("provinceZone", provinceZone.getCode());
			}else if(currentLevel == 4){
				view.addObject("cityZone",currentZone.getCode());
				Zone provinceZone = zoneService.getZoneByCode(currentZone.getPcode());
				view.addObject("provinceZone", provinceZone.getCode());
			}else if(currentLevel == 3){
				view.addObject("provinceZone",currentZone.getCode());
			}
		}
		
		//获取当前登录用户的单位信息
		SysUser sysUser = getLoginUser();
		SysUnits sysUnits = sysUnitsService.get(sysUser.getUnitId());

		//查询登录用户权限范围内可分配的区域列表
		Map<String,Object> argMap = new HashMap<String, Object>();
		//参数Map
		Map<String,Object> getAreaMap = new HashMap<String, Object>();
		getAreaMap.put("sysUnits", sysUnits);
		
		if(1 == sysUnits.getId()){	//如果用户属于平台用户（最高等级）
			argMap.put("unit", sysUnits);
			argMap.put("zoneLevel", ZoneLevelKeys.province.value());
			List<Map<String,Object>> provinceList = sysUnitsService.getZoneListForDelivery(argMap);
			view.addObject("provinceList",provinceList);
			
			if( null != regZoneCode && !"".equals(regZoneCode) ){
				Zone currentZone = zoneService.getZoneByCode(regZoneCode);
				int currentLevel = Integer.valueOf(currentZone.getLevel());
				
				if(currentLevel == 5){
					Map<String,Object> getOriginalZoneMap = new HashMap<String, Object>();
					getOriginalZoneMap.put("zoneCode", currentZone.getPcode()+"%");
					List<Map<String,Object>> countyList =  sysUnitsDao.getCountyAgentZoneList(getOriginalZoneMap);
					view.addObject("countyList", countyList);
					
					Zone parentZone = zoneService.getZoneByCode(currentZone.getPcode());
					getOriginalZoneMap.put("zoneCode", parentZone.getPcode());
					List<Map<String,Object>> cityList =  sysUnitsDao.getMunicipalAgentZoneList(getOriginalZoneMap);
					view.addObject("cityList", cityList);
				}
				
				if(currentLevel == 4){
					Map<String,Object> getOriginalCityMap = new HashMap<String, Object>();
					getOriginalCityMap.put("zoneCode", currentZone.getPcode());
					List<Map<String,Object>> cityList =  sysUnitsDao.getMunicipalAgentZoneList(getOriginalCityMap);
					view.addObject("cityList", cityList);
				}
			}
			
		}else if(2 == sysUnits.getUnitType()){ //如果用户属于省级代理商
			argMap.put("unit", sysUnits);
			argMap.put("zoneLevel", ZoneLevelKeys.city.value());
			
			getAreaMap.put("queryFlag", "current");
			List<Map<String,Object>> provinceList = sysUnitsService.getAreaList(getAreaMap);
			List<Map<String,Object>> cityList = sysUnitsService.getZoneListForDelivery(argMap);
			
			view.addObject("provinceList",provinceList);
			view.addObject("cityList",cityList);
			
			
			if( null != regZoneCode && !"".equals(regZoneCode) ){
				Zone currentZone = zoneService.getZoneByCode(regZoneCode);
				int currentLevel = Integer.valueOf(currentZone.getLevel());
				
				if(currentLevel == 5){
					Map<String,Object> getOriginalCountyMap = new HashMap<String, Object>();
					getOriginalCountyMap.put("zoneCode", currentZone.getPcode()+"%");
					List<Map<String,Object>> countyList =  sysUnitsDao.getCountyAgentZoneList(getOriginalCountyMap);
					view.addObject("countyList", countyList);
				}
			}
			
			
		}else if(0 == sysUnits.getUnitType()){ //如果用户属于区域代理商
			argMap.put("unit", sysUnits);
			argMap.put("zoneLevel", ZoneLevelKeys.county.value());
			
			getAreaMap.put("queryFlag", "parent");
			List<Map<String,Object>> provinceList = sysUnitsService.getAreaList(getAreaMap);
			getAreaMap.put("queryFlag", "current");
			List<Map<String,Object>> cityList = sysUnitsService.getAreaList(getAreaMap);
			List<Map<String,Object>> countyList = sysUnitsService.getZoneListForDelivery(argMap);
			view.addObject("provinceList",provinceList);
			view.addObject("cityList", cityList);
			view.addObject("countyList",countyList);
		}
		return view;
	}
	
	/**
	 * 选中某个区域后，获取其从属区域列表
	 */
	@RequestMapping("/getZoneList")
	public void getZoneList(){
		outJson(sysUnitsService.getZoneListOnChange(request));
	}

	/**
	 * 配送员负责的门店
	 */
	@RequestMapping("/takeChargeShopList")
	public ModelAndView takeChargeShopList(){
		ModelAndView view = new ModelAndView();
		DeliveryUser deliveryUser = deliveryUserService.get(Long.valueOf(request.getParameter("id")));
		
		view.addObject("deliveryUserId",deliveryUser.getId());
		view.addObject("unitId",deliveryUser.getUnitId());
		view.addObject("account",deliveryUser.getAccount());
		view.addObject("gridComluns",sysGridColumnsService.getGridColumsByCode("retailStore_grid"));
		view.setViewName("business/deliveryuser/takeChargeShopList");
		return view;
	}
	
	/**
	 * 获取配送员负责的门店列表
	 */
	@RequestMapping("/getDeliveryTakeChargeShopList")
	public void getDeliveryTakeChargeShopList(){
		outJson(deliveryUserService.getDeliveryTakeChargeShopList(request));
	}
	
	/**
	 * 删除配送员&门店 关联关系
	 */
	@RequestMapping("/deleteDeliveryShopRt")
	public void deleteDeliveryShopRt(){
		outJson(deliveryUserService.deleteDeliveryShopRt(request));
	}
	
	/**
	 * 给配送员分配门店
	 */
	@RequestMapping("/assignShop")
	public ModelAndView assignShop(){
		ModelAndView view = new ModelAndView();
		DeliveryUser deliveryUser = deliveryUserService.get(Long.valueOf(request.getParameter("id")));   
		
		view.addObject("deliveryUserId", deliveryUser.getId());
		view.addObject("unitId",deliveryUser.getUnitId());
		view.addObject("zoneCode",deliveryUser.getRegZone());
		
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("retailStore_grid"));
		System.out.println(sysQueryItemService.getConditionHtmlByCode("shop_search", getLoginUser().getUnitId()));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByCode("shop_search", getLoginUser().getUnitId()));
		view.setViewName("business/deliveryuser/assignShop");
		return view;
	}
	
	/**
	 * 分页获取门店表格数据（供分配门店选择）
	 * @param unitId
	 */
	@RequestMapping("/getShopGridData")
	public void getShopGridData(){
		outJson(deliveryUserService.getShopGridData(request));
	}
	
	/**
	 * 保存配送员&门店关联关系
	 */
	@RequestMapping("/saveDeliveryShopRt")
	public void saveDeliveryShopRt(){
		outJson(deliveryUserService.saveDeliveryShopRt(request));
	}
	
	/**
	 * 保存操作（审核、停用、启用）
	 * 
	 * @param op
	 *            check：审核、start：启用、stop：停用
	 */
	@RequestMapping(value = "/save")
	public void save(@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();

		String opName = "操作";

		try {

			String objectIds = request.getParameter("objectIds");
			List<String> objectIdList = Arrays.asList(objectIds.split(","));

			long status = 0;
			if (op.equals("check")) {// 审核

				opName = "审核";
				DeliveryUser deliveryUser = deliveryUserService.get(Long.valueOf(objectIdList.get(0)));
				deliveryUser.setCheckStatus(Integer.valueOf(request.getParameter("checkStatus")));
				String zoneCode = request.getParameter("chosenZone");
				Zone zone = zoneService.getZoneByCode(zoneCode);
				SysUnits unit = sysUnitsService.getUnitByZoneCode(zoneCode);
				if(null == unit){
					map.put("code", "0");
					map.put("msg", opName+"失败，原因："+zone.getName()+"H2Y未注册。");
					outJson(map);
					return;
				}
				deliveryUser.setRegZone(zoneCode);
				deliveryUser.setUnitId(unit.getId());
				/*deliveryUser.setUnitId(Long.valueOf(request.getParameter("unitId")));
				deliveryUser.setShopId(Long.valueOf(request.getParameter("shopId")));*/
				deliveryUser.setUpdateDate(DateUtil.getSystemTime());
				deliveryUserService.update(deliveryUser);
				//审核后，清空配送员门店关联关系
				deliveryUserService.emptyDeliveryShopRt(deliveryUser.getAccount());
			} else if (op.equals("start")) {// 启用
				status = 1;
				opName = "启用";
				deliveryUserService.updateStatus(objectIdList, status);
			} else if (op.equals("stop")) {// 停用
				status = 2;
				opName = "停用";
				deliveryUserService.updateStatus(objectIdList, status);
			} else {// 无效标识
				map.put("code", "1");
				map.put("msg", "无效操作，op" + op + "！");
				outJson(map);
				return;
			}
			map.put("code", "1");
			map.put("msg", opName + "成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", opName + "失败！");
		}
		outJson(map);
	}

	/**
	 * 获取门店列表
	 * 
	 * @param op
	 *            grid:表格列，tree:树
	 * @param parentCode
	 *            父级Id
	 */
	@RequestMapping(value = "/getShopList")
	public void getShopList(@RequestParam long unitId) {
		outJson(deliveryUserService.getShopList(unitId));
	}
	
	
	/**
	 * 查询页面初始化
	 */
	@RequestMapping(value = "/selectInit")
	public ModelAndView selectInit(){
		
		SysUser sysUser = getLoginUser();
		ModelAndView view = new ModelAndView();
		view.setViewName("business/deliveryuser/selectInit");
		view.addObject("shopId", request.getParameter("shopId"));
		view.addObject("gridComluns",sysGridColumnsService.getGridColumsByCode("delivery_select_grid"));
		view.addObject("conditionHtml",sysQueryItemService.getConditionHtmlByCode("delivery_select_query", sysUser.getUnitId()));
		return view;
	}
	
	
	/**
	 * 获取选择列表
	 */
	@RequestMapping(value = "/getSelectList")
	public void getSelectList() {
		outJson(deliveryUserService.getSelectGridData(request, this.getLoginUnitId()));
	}

}
