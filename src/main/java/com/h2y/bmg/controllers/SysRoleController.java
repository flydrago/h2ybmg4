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
import com.h2y.bmg.entity.SysRole;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.services.ISysRoleService;
import com.h2y.bmg.util.SysBaseUtil;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.util.JSONUtil;


/**
 * 角色管理url跳转控制类

 * 作者：侯飞龙

 * 时间：2014-10-13 上午11:31:51

 * 电子邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/sys/role")
@Scope("prototype")
public class SysRoleController  extends BaseController{

	private final static Logger logger = Logger.getLogger(SysRoleController.class);

	@Autowired
	protected ISysRoleService sysRoleService;
	
    
    @Autowired
    protected ISysLogService sysLogService;

	/**
	 * 页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/role/init");

//		view.addObject("toolbar",sysButtonService.getMenuButtons(request,getLoginUnitId(),getLoginUserId()));
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), getLoginUser()));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		return view;
	}
	
	
	/**
	 * 角色权限页面初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rolemenu")
	public ModelAndView rolemenu() {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/role/rolemenu");
		
		SysUser sysUser = getLoginUser();
		long unitId = sysUser.getUnitId();

//		view.addObject("toolbar",sysButtonService.getMenuButtons(request,getLoginUnitId(),getLoginUserId()));
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("treedata",JSONUtil.getJson(sysRoleService.getRoleTreeData("rolemenu",unitId,sysUser.getId())));
		return view;
	}
	
	/**
	 * 角色用户初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/roleuser")
	public ModelAndView roleuser() {

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/role/roleuser");
		
		SysUser sysUser = getLoginUser();
		long unitId = sysUser.getUnitId();
		
//		view.addObject("toolbar",sysButtonService.getMenuButtons(request,getLoginUnitId(),getLoginUserId()));
		view.addObject("toolbar",sysButtonService.getMenuButtons(request, getLoginSysRoleId(), sysUser));
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
		view.addObject("treedata",JSONUtil.getJson(sysRoleService.getRoleTreeData("roleuser",unitId,sysUser.getId())));
		return view;
	}


	/**
	 * 编辑页面初始化
	 * @param sysDepartment 部门
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(@ModelAttribute SysRole sysRole,@RequestParam String op) {

		ModelAndView view = new ModelAndView();

		if (op.equals("modify")) {
			sysRole = sysRoleService.get(sysRole.getId());
		}
		view.addObject("unitId",getLoginUnitId());
		view.addObject("userId", getLoginUserId());
		view.addObject("sysRole",sysRole);
		view.addObject("op",op);
		view.setViewName("sys/role/add");
		
		view.addObject("validationRules", sysValidationService.getValidationByCode("sysrole_validate"));
		return view;
	}



	/**
	 * 获取列表
	 * @param op grid:表格列，tree:树
	 * @param parentCode 父级Id
	 */
	@RequestMapping(value = "/getList")
	public void getList() {
		outJson(sysRoleService.getGridData(request,getLoginUnitId(),getLoginSysRoleId(),getLoginUserId()));
	}


	/**
	 * 保存
	 * @param sysRole 角色对象
	 * @param op add：添加、modify：修改
	 */
	@RequestMapping(value = "/save")
	public void save(@ModelAttribute SysRole sysRole,@RequestParam String op) {
		Map<String, Object> map = new HashMap<String, Object>();

		long unitId = getLoginUnitId();
		sysRole.setUnitId(unitId);
		sysRole.setIfDelete(0l);

		try {
			
			
//			if (sysRoleService.isHasSameCode(sysRole, op)) {
//				map.put("code", "0");
//				map.put("msg", "角色编码重复，请使用其他编码！");
//			}else {
				
				map.put("code", "1");
				map.put("msg", "保存成功！");
				
				String roleCode_prefix = "normal_";
				
				if (sysRole.getUnitId()==1) {
					if (sysRole.getIfSys()==1 && sysRole.getIfPrivilege()==1) {
						roleCode_prefix  = "privilege_";
					}else if(sysRole.getIfSys()==1 && sysRole.getIfPrivilege()==0){
						roleCode_prefix  = "sys_";
					}
				}
				
				if (op.equals("add")) {
					
					sysRoleService.add(sysRole);
					sysRole.setRoleCode(roleCode_prefix+sysRole.getId());
					sysRoleService.update(sysRole);
					
					sysLogService.addLog(request, getLoginUser(), "角色", SysBaseUtil.OpType.add+"", 
							OpRresult.success+"", "角色添加", sysRole.getId()+"", BusinessTableName.SysRole.name);
				}else {
					
					SysRole sysRole2 = sysRoleService.get(sysRole.getId());
					sysRole2.setRoleName(sysRole.getRoleName());
					sysRole2.setRoleDesc(sysRole.getRoleDesc());
					sysRole2.setRoleCode(roleCode_prefix+sysRole2.getId());
					sysRole2.setIfPrivilege(sysRole.getIfPrivilege());
					sysRole2.setRoleOrd(sysRole.getRoleOrd());
					sysRoleService.update(sysRole2);
					
					sysLogService.addLog(request, getLoginUser(), "角色", SysBaseUtil.OpType.modify+"", 
							OpRresult.success+"", "角色修改", sysRole.getId()+"", BusinessTableName.SysRole.name);
				}
				
//			}
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
    public void delete(@ModelAttribute SysRole sysRole) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
        	
            map.put("code", "1");
            map.put("msg", "删除成功！");
            SysRole sysRole2 = sysRoleService.get(sysRole.getId());
            sysRole2.setIfDelete(1l);
            sysRoleService.update(sysRole2);
            
            sysLogService.addLog(request, getLoginUser(), "角色", SysBaseUtil.OpType.delete+"", 
					OpRresult.success+"", "角色删除", sysRole.getId()+"", BusinessTableName.SysRole.name);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            map.put("code", "0");
            map.put("msg", "删除失败！");
        }
        outJson(map);
    }
    
    
    
	/**
	 * 保存权限
	 */
	@RequestMapping(value = "/savePrivilege")
	public void savePrivilege(long roleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("code", "1");
			map.put("msg", "保存成功！");
			
			sysRoleService.savePrivilege(request, getLoginUnitId(), roleId);;
			sysLogService.addLog(request, getLoginUser(), "权限保存", "savePrivilege",
					OpRresult.success+"", "角色权限保存，数据"+request.getParameter("checkedData"), roleId+"", BusinessTableName.SysPrivilegeList.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			sysLogService.addLog(request, getLoginUser(), "权限保存", "savePrivilege",
					OpRresult.fail+"", "角色权限保存", roleId+"", BusinessTableName.SysPrivilegeList.name);
			map.put("code", "0");
			map.put("msg", "保存失败！");
		}
		outJson(map);
	}
	
	/**
	 * 保存角色用户
	 */
	@RequestMapping(value = "/saveRoleUser")
	public void saveRoleUser(long roleId,String op){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			map.put("code", "1");
			map.put("msg", "保存成功！");
			if (op.equals("add")) {
				
				sysRoleService.saveRoleUser(request, getLoginUnitId(), roleId);
				sysLogService.addLog(request, getLoginUser(), "角色用户关联", op, 
						OpRresult.success+"", "角色("+roleId+")，添加用户Id为("+request.getParameter("userData")+")",roleId+"",BusinessTableName.SysPrivilegeList.name);
			}else if (op.equals("remove")) {
				
				map.put("msg", "移除成功！");
				sysRoleService.removeRoleUser(request);
				sysLogService.addLog(request, getLoginUser(), "角色用户关联", op, 
						OpRresult.success+"", "角色("+roleId+")，移除权限Id为("+request.getParameter("privilegeIds")+")",roleId+"",BusinessTableName.SysPrivilegeList.name);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
			sysLogService.addLog(request, getLoginUser(), "角色用户关联", op, 
					OpRresult.success+"", "保存失败",roleId+"",BusinessTableName.SysPrivilegeList.name);
		}
		outJson(map);
	}
}
