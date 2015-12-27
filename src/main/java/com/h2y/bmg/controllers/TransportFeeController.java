package com.h2y.bmg.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.ITransportFeeDao;
import com.h2y.bmg.dao.IZoneDao;
import com.h2y.bmg.entity.GoodsMark;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.TransportFee;
import com.h2y.bmg.entity.Zone;
import com.h2y.bmg.services.ITransportFeeService;
import com.h2y.bmg.services.IZoneService;
import com.h2y.util.JSONUtil;
import com.h2y.util.PinyinUtil;

/**
 * 运费管理控制类

 * 作者：李亮

 * 电子邮件：liliang@163@163.com
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/business/transfee")
public class TransportFeeController extends BaseController{
	
    private static Logger logger = Logger.getLogger(TransportFeeController.class);
	
	@Autowired
	protected ITransportFeeService transportFeeService;
	
	@Autowired
	protected ITransportFeeDao transportFeeDao;
	
	//初始化
	@RequestMapping(value = "/init")
	public ModelAndView init() {
		ModelAndView view = new ModelAndView();
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.setViewName("business/transfee/init");
		return view;
	}
	
	
	@RequestMapping(value = "/list")
	public void list(){
		outJson(transportFeeService.getGridData(request, getLoginUnitId()));
	}
	
	@RequestMapping(value = "/add")
	public  ModelAndView add(@ModelAttribute TransportFee transportFee){
		ModelAndView view = new ModelAndView();
		if(transportFee.getId()!=0){
			transportFee = transportFeeDao.get(transportFee.getId());
        }
		SysUnits sysUnits = getLoginUnit();
		String op = request.getParameter("op");
		view.addObject("transportFee", transportFee);
		view.setViewName("business/transfee/add");
		view.addObject("sysUnits", sysUnits);
		view.addObject("validationRules",sysValidationService.getValidationByCode("transfee_add"));
		view.addObject("op", op);
		return view;
	}
	
	
	/**
     * 保存
     * @param request
     * @param goodsMark
     * @return
     */
    @RequestMapping(value="/save")
    public void save(HttpServletRequest request,@ModelAttribute TransportFee transportFee) {
        Map<String, Object> map = new HashMap<String, Object>();
        SysUser sysUser = getLoginUser();
        SysUnits sysUnits = getLoginUnit();
        try {
            if(transportFee.getId()==0){
                Date nowDate = new Date();
                transportFee.setCreateDate(nowDate);
                transportFee.setCreateUserId(sysUser.getId());
                transportFee.setUnitsId(sysUnits.getId());
                transportFee.setUnitsZoneCode(sysUnits.getZoneCode());
                transportFee.setStatus("0");//状态正常
                transportFeeDao.add(transportFee);
                
            }
            else{
            	TransportFee oldtFee = transportFeeDao.get(transportFee.getId());
            	transportFee.setUnitsId(oldtFee.getUnitsId());
            	transportFee.setUnitsZoneCode(oldtFee.getUnitsZoneCode());
            	transportFee.setDesZoneCode(oldtFee.getDesZoneCode());
            	transportFee.setDesZoneName(oldtFee.getDesZoneName());
            	transportFee.setStatus(oldtFee.getStatus());
            	transportFee.setCreateDate(oldtFee.getCreateDate());
                transportFee.setCreateUserId(oldtFee.getCreateUserId());
            	transportFee.setUpdateDate(new Date());
            	transportFee.setUpdateUserId(sysUser.getId());
            	transportFeeDao.update(transportFee);
            }
            map.put("code", "1");
            map.put("msg", "保存成功！");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            map.put("code", "0");
            map.put("msg", "保存失败！");
        }
        outJson(map);
    } 
    
    
    @RequestMapping(value="/update")
    public void update(){
    	 Map<String, Object> map = new HashMap<String, Object>();
    	 SysUser sysUser = getLoginUser();
    	 String status = request.getParameter("status");
    	 String transfeeData = request.getParameter("transfeeData");
    	 List<Map<String, Object>> dataList = JSONUtil.jsonToListMap(transfeeData);
    	 try{
    		 for(Map<String, Object> dataMap : dataList){
        		 long tid = Long.valueOf(dataMap.get("id")+"");
        		 TransportFee transportFee = transportFeeDao.get(tid);
        		 transportFee.setStatus(status);
        		 transportFee.setUpdateDate(new Date());
                 transportFee.setUpdateUserId(sysUser.getId());	 
                 transportFeeDao.update(transportFee);	 
        	 } 
    		 map.put("code", "1");
             map.put("msg", "保存成功！");
    	 }catch(Exception e){
    		 logger.error(e.getMessage(), e);
             map.put("code", "0");
             map.put("msg", "保存失败！"); 
    	 }
    	  outJson(map);
    }
    
    
    //查询运费
    @RequestMapping(value="/getTransfee")
    public void getTransfee(){
    	String units = request.getParameter("units");
    	String zcode = request.getParameter("zcode");
    	String unitssub = units.substring(1,units.length()-1);
		String unitArray[] = unitssub.split(",");
		List<String> unitList = Arrays.asList(unitArray);
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		for(String unid : unitList){
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("unid", unid.trim());
			paraMap.put("zcode", zcode);
			TransportFee transportFee = transportFeeDao.getTransfee(paraMap);
			if(null == transportFee){
				paraMap.put("fee", 0);
			}
			else{
				paraMap.put("fee", transportFee.getFeeAmount());
			}
			resultList.add(paraMap);
		}
 		outJson(resultList);
     }
    
    

}
