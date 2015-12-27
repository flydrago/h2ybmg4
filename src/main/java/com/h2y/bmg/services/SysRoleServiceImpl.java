package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.dao.ISysButtonDao;
import com.h2y.bmg.dao.ISysMenuDao;
import com.h2y.bmg.dao.ISysPrivilegeListDao;
import com.h2y.bmg.dao.ISysRoleDao;
import com.h2y.bmg.entity.SysPrivilegeList;
import com.h2y.bmg.entity.SysRole;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.PrivilegeKey;
import com.h2y.util.JSONUtil;

/**
 * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2014-10-20
 * email:info@hwttnet.com
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements ISysRoleService{


	@Autowired
	protected ISysRoleDao sysRoleDao;

	@Autowired
	protected ISysPrivilegeListDao sysPrivilegeListDao;

	@Autowired
	protected ISysMenuDao sysMenuDao;

	@Autowired
	protected ISysButtonDao sysButtonDao;

	@Autowired
	protected ISysDialogService sysDialogService;
	
	
	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param sysRole
	 *
	 */
	public void add(SysRole sysRole) {
		// TODO Auto-generated method stub

		sysRoleDao.add(sysRole);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		sysRoleDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	sysRoleDao.deleteByIds(ids);
	//}

	public void update(SysRole sysRole) {
		// TODO Auto-generated method stub
		sysRoleDao.update(sysRole);
	}

	public SysRole get(long id) {
		// TODO Auto-generated method stub
		return sysRoleDao.get(id);
	}

	/**
	 * 得到gird列表
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId,long sysRoleId,long userId){

		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String op = request.getParameter("op");

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("unitId", unitId);

		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		long totalRows = 0;
		//角色列表
		if (op.equals("grid")) {

			if (sortname!=null && !sortname.equals("")) {
				sortname = sortname.toLowerCase();
			}
			map.put("sortname", sortname);
			map.put("sortorder", sortorder);
			map.put("userId", userId);

			dataList = sysRoleDao.getListMap(map);
			totalRows = sysRoleDao.getListRows(map);
		}else if(op.equals("rolemenu")){//角色菜单按钮列表

			long roleId = Long.valueOf(request.getParameter("roleId"));

			map.put("pId", 0l);
			map.put("roleId", roleId);
			map.put("sysRoleId", sysRoleId);
			dataList = sysRoleDao.getRoleGridMenuList(map);
			totalRows = sysRoleDao.getRoleGridMenuRows(map);

			if (dataList!=null) {
				//添加子菜单和按钮
				for (Map<String, Object> map2 : dataList) {

					map.put("pId", map2.get("ID"));
					map.put("page",null);
					map.put("pagesize",null);
					List<Map<String,Object>> secondList = sysRoleDao.getRoleGridMenuList(map);
					if (secondList!=null && !secondList.isEmpty()) {
						map2.put("children", secondList);
						for (Map<String, Object> map3 : secondList) {

							map.put("menuId", map3.get("ID"));
							map3.put("children", sysRoleDao.getRoleButtonMenuList(map));
						}
					}
				}
			}
		}else if (op.equals("roleuser")) {//角色用户列表
			long roleId = Long.valueOf(request.getParameter("roleId"));
			if (sortname!=null && !sortname.equals("")) {
				if (sortname.equals("USER_NAME")) {
					sortname = "su.user_name";
				}else if (sortname.equals("ACCOUNT")) {
					sortname = "su.account";
				}else if (sortname.equals("MOBILE")) {
					sortname = "su.mobile";
				}else if (sortname.equals("EMAIL")) {
					sortname = "su.email";
				}else {
					sortname = "";
				}
			}
			map.put("userName", request.getParameter("userName"));
			map.put("roleId", roleId);
			map.put("sortname", sortname);
			map.put("sortorder", sortorder);
			dataList = sysRoleDao.getRoleUserList(map);
			totalRows = sysRoleDao.getRoleUserRows(map);
		}

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", totalRows);
		return gridData;
	}


	/**
	 * 是否有相同编码
	 * @param sysRole
	 * @return
	 */
	public boolean isHasSameCode(SysRole sysRole,String op){

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("unitId", sysRole.getUnitId());
		map.put("roleCode", sysRole.getRoleCode());
		map.put("unitId", sysRole.getUnitId());
		if (op.equals("modify")) {
			map.put("id", sysRole.getId());
		}

		return sysRoleDao.getSameCodeRows(map)>0 ? true : false;

	}


	/**
	 * 根据单位Id，得到角色Id
	 * @param unitId 单位Id
	 * @return
	 */
	public List<Map<String,Object>> getRoleTreeData(String op,long unitId,long userId){

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("unitId", unitId);
		map.put("userId", userId);
		
		List<Map<String,Object>> roleList = new ArrayList<Map<String,Object>>();
		if (op.equals("rolemenu")) {
			roleList = sysRoleDao.getMenuRoleTreeList(map);
			if (roleList==null) {
				roleList = new ArrayList<Map<String,Object>>();
			}
			if(userId==-1){
				
				Map<String,Object> rootMap1 = new HashMap<String, Object>();
				rootMap1.put("id", "privilege_0");
				rootMap1.put("text", "特权角色");
				
				Map<String,Object> rootMap2 = new HashMap<String, Object>();
				rootMap2.put("id", "sys_0");
				rootMap2.put("text", "系统角色");
				roleList.add(rootMap1);
				roleList.add(rootMap2);
			}else{
				
				Map<String,Object> rootMap1 = new HashMap<String, Object>();
				rootMap1.put("id", "normal_0");
				rootMap1.put("text", "角色");
				roleList.add(rootMap1);
			}
		}else if (op.equals("roleuser")) {
			
			roleList = sysRoleDao.getUserRoleTreeList(map);
			if (roleList==null) {
				roleList = new ArrayList<Map<String,Object>>();
			}
			
			Map<String,Object> rootMap1 = new HashMap<String, Object>();
			rootMap1.put("id", "sys_0");
			rootMap1.put("text", "系统角色");
			
			Map<String,Object> rootMap2 = new HashMap<String, Object>();
			rootMap2.put("id", "normal_0");
			rootMap2.put("text", "角色");
			
			roleList.add(rootMap1);
			roleList.add(rootMap2);
		}

		return roleList;
	}


	/**
	 * 保存权限
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @param roleId 角色Id
	 */
	@Transactional(rollbackFor=Exception.class)
	public void savePrivilege(HttpServletRequest request,long unitId,long roleId){

		//得到顶级菜单Ids
		String topMenuIds = request.getParameter("topMenuIds");

		//根据顶级菜单，清除权限
		clearPrivilegeByTopMenuIds(roleId, topMenuIds);



		String checkedData = request.getParameter("checkedData");
		List<Map<String,Object>> dataList = JSONUtil.jsonToListMap(checkedData);

		List<SysPrivilegeList> privilegeLists = new ArrayList<SysPrivilegeList>();
		for (Map<String, Object> dataMap : dataList) {
			SysPrivilegeList sysPrivilegeList = new SysPrivilegeList();
			sysPrivilegeList.setUnitId(unitId);
			sysPrivilegeList.setPrivilegeAccess(dataMap.get("TYPE")+"");
			sysPrivilegeList.setPrivilegeAccessValue(Long.valueOf((dataMap.get("ID")+"")));
			sysPrivilegeList.setPrivilegeMaster(PrivilegeKey.ROLE);
			sysPrivilegeList.setPrivilegeMasterValue(roleId);
			privilegeLists.add(sysPrivilegeList);
		}

		if (!privilegeLists.isEmpty()) {
			sysPrivilegeListDao.addBatch(privilegeLists);
		}
	}


	/**
	 * 保存角色用户
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @param roleId 角色Id
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveRoleUser(HttpServletRequest request,long unitId,long roleId){

		String userData = request.getParameter("userData");

		String userData_array [] = userData.split(",");
		List<Long> userIds = sysDialogService.getMixDistinctPepleIds(userData_array, unitId);

		if (userIds==null || userIds.isEmpty()) {
			return;
		}

		//删除拥有角色的用户
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("master", PrivilegeKey.USER);
		map.put("masterValueList", userIds);
		map.put("accessValue", roleId);
		map.put("access", PrivilegeKey.ROLE);
		sysPrivilegeListDao.deleteByMasterList(map);

		List<SysPrivilegeList> privilegeLists = new ArrayList<SysPrivilegeList>();
		for (Long userId : userIds) {
			SysPrivilegeList sysPrivilegeList = new SysPrivilegeList();
			sysPrivilegeList.setUnitId(unitId);
			sysPrivilegeList.setPrivilegeMaster(PrivilegeKey.USER);
			sysPrivilegeList.setPrivilegeMasterValue(userId);
			sysPrivilegeList.setPrivilegeAccess(PrivilegeKey.ROLE);
			sysPrivilegeList.setPrivilegeAccessValue(roleId);
			privilegeLists.add(sysPrivilegeList);
			if (privilegeLists.size()>1000) {
				sysPrivilegeListDao.addBatch(privilegeLists);
				privilegeLists.clear();
			}
		}
		if (!privilegeLists.isEmpty()) {
			sysPrivilegeListDao.addBatch(privilegeLists);
		}
		
	}

	/**
	 * 移除角色用户
	 * @param request 访问对象
	 */
	public void removeRoleUser(HttpServletRequest request){

		String privilegeIds = request.getParameter("privilegeIds");
		String [] privilegeId_array = privilegeIds.split(",");

		List<Long> privilegeIdList = new ArrayList<Long>();

		for (String privilegeIdstr : privilegeId_array) {
			privilegeIdList.add(Long.valueOf(privilegeIdstr));
		}

		if (!privilegeIdList.isEmpty()) {
			sysPrivilegeListDao.deleteByIds(privilegeIdList);
		}
	}


	/**
	 * 根据顶级菜单清除权限
	 * @param roleId
	 * @param topMenuIds
	 */
	private void clearPrivilegeByTopMenuIds(long roleId,String topMenuIds){

		Map<String,Object> menuParams = new HashMap<String, Object>();
		menuParams.put("parentIds", topMenuIds);
		List<Long> childMenuIds = sysMenuDao.getMenuIdByParentIds(menuParams);

		if (childMenuIds==null || childMenuIds.isEmpty()) {
			childMenuIds = new ArrayList<Long>();
		}
		String [] menuId_array = topMenuIds.split(",");
		for (String menuIdStr : menuId_array) {
			childMenuIds.add(Long.valueOf(menuIdStr));
		}
		//清除菜单权限
		deletePrivilege(PrivilegeKey.ROLE, roleId, PrivilegeKey.MENU, childMenuIds);



		Map<String,Object> buttonMap = new HashMap<String, Object>();
		buttonMap.put("list", childMenuIds);
		List<Long> buttonIds = sysButtonDao.getButtonIdsByMenuIds(buttonMap);
		//清除按钮权限
		deletePrivilege(PrivilegeKey.ROLE, roleId, PrivilegeKey.BUTTON, buttonIds);
	}


	/**
	 * 删除权限
	 * @param master 主字段
	 * @param masterValue 主字段值
	 * @param access 对应字段
	 * @param accessValueList 对应字段集合
	 */
	private void deletePrivilege(String master,long masterValue,String access,List<Long> accessValueList){

		if (accessValueList==null || accessValueList.isEmpty()) {
			return;
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("master", master);
		map.put("masterValue", masterValue);
		map.put("access", access);
		map.put("accessValueList", accessValueList);
		sysPrivilegeListDao.deleteByAccessList(map);
	}
}