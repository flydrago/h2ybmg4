package com.h2y.bmg.services;

import com.h2y.bmg.dao.ISysMenuDao;
import com.h2y.bmg.dao.ISysPrivilegeListDao;
import com.h2y.bmg.entity.SysMenu;
import com.h2y.bmg.entity.SysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
  * ServiceImpl
 * @author hwttnet
 * version:1.2
 * time:2014-10-13
 * email:info@hwttnet.com
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements ISysMenuService{


    @Autowired
    protected ISysMenuDao sysMenuDao;
    
    @Autowired
    protected ISysPrivilegeListDao sysPrivilegeListDao;


	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param sysMenu
	 *
	 */
	public void add(SysMenu sysMenu) {
		// TODO Auto-generated method stub

        sysMenuDao.add(sysMenu);
	}


	public void delete(Long id) {
		// TODO Auto-generated method stub
		sysMenuDao.deleteById(id);
	}

	public void deleteByIds(List<Long> ids){
        sysMenuDao.deleteByIds(ids);
	}

	public void update(SysMenu sysMenu) {
		// TODO Auto-generated method stub
        sysMenuDao.update(sysMenu);
	}

	public SysMenu get(Long id) {
		// TODO Auto-generated method stub
		return sysMenuDao.get(id);
	}


	public List<SysMenu> getList(SysMenu sysMenu){
		List<SysMenu> list = sysMenuDao.getList(sysMenu);
		//
		//sysMenu = null;

		return list;
	}


	/**
	 * getListPage
	 * @return
	 */
	public List<SysMenu> getListPage(Map<String,Object> map){
		//map.put("aaa", new SysMenu());
		return sysMenuDao.getListPage(map);
	}

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String,Object> map){

		return sysMenuDao.getRows(map);
	}

	/**
	 * 得到用户的功能菜单的树数据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getUserMenuTreeData(long sysRoleId,SysUser sysUser) {
		
		List<Long> roleIds = sysPrivilegeListDao.getRoleIdsByUserId(sysUser.getId());
		
		List<Map<String,Object>> menuList = new ArrayList<Map<String,Object>>();
		
		if (roleIds!=null && !roleIds.isEmpty()) {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("unitId", sysUser.getUnitId());
			params.put("roleList", roleIds);
			params.put("sysRoleId", sysRoleId);
			menuList = sysMenuDao.getUserMenuTreeData(params);
		}
		
		if (menuList==null) {
			menuList = new ArrayList<Map<String,Object>>();
		}
		return menuList;
	}
	
	public List<Map<String,Object>> getSysMenuTreeData(){
		
		return sysMenuDao.getSysMenuTreeData();
	}
	
	
	/**
	 * 得到列表数据
	 * @param map
	 * @return
	 */
	public Map<String,Object> getGridData(Map<String,Object> map){
		
		Map<String,Object> gridData = new HashMap<String, Object>();
		
		List<Map<String,Object>> dataList = sysMenuDao.getListMap(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", dataList.size());
		return gridData;
	}

    /**
     * 判断菜单是否有子级菜单
     * @param menuId
     * @return
     */
    public boolean isHasChildMenu(long menuId){

        return sysMenuDao.getChildMenuRows(menuId)>0;
    }


    /**
     * 得到父级菜单列表
     * @param menuId
     * @return
     */
    public List<SysMenu> getParentMenus(long menuId){

        return sysMenuDao.getParentMenus(menuId);
    }


    /**
     * 得到菜单维护的树数据
     * @return
     */
    public List<Map<String, Object>> getTreeData() {

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("pid", 0);
        List<Map<String,Object>> treeMaps = sysMenuDao.getTreeData(map);

        if (treeMaps==null) {
            treeMaps = new ArrayList<Map<String,Object>>();
        }

        Map<String,Object> rootMap = new HashMap<String, Object>();
        rootMap.put("id", 0);
        rootMap.put("text", "菜单");
        treeMaps.add(rootMap);
        return treeMaps;
    }


    /**
     * 得到按钮维护的菜单树数据
     * @return
     */
    public List<Map<String,Object>> getButtonMenuTreeData(){

        Map<String,Object> map = new HashMap<String, Object>();
        List<Map<String,Object>> treeMaps = sysMenuDao.getTreeData(map);
        if (treeMaps==null) {
            treeMaps = new ArrayList<Map<String,Object>>();
        }
        return treeMaps;
    }

    /**
     * 得到表格列维护的菜单树数据
     * @return
     */
    public List<Map<String,Object>> getGridMenuTreeData(){

        return getMenuTreeData("ifGrid");
    }
    
    
    /**
     * 得到表格列维护的菜单树数据
     * @return
     */
    public List<Map<String,Object>> getQueryMenuTreeData(){

    	return getMenuTreeData("ifQuery");
    }
    
    
    /**
     * 得到表格列维护的菜单树数据
     * @return
     */
    public List<Map<String,Object>> getValidationMenuTreeData(){

        return getMenuTreeData("ifValidate");
    }
    
    /**
     * 得到表格列、查询、验证的菜单树数据
     * @param key ifGrid:表格列、ifQuery:查询、ifValidation:验证
     * @return
     */
    private List<Map<String,Object>> getMenuTreeData(String key){
    	
    	Map<String,Object> map = new HashMap<String, Object>();
        map.put(key,1);
        List<Map<String,Object>> treeMaps = sysMenuDao.getTreeData(map);
        if (treeMaps==null) {
            treeMaps = new ArrayList<Map<String,Object>>();
        }
        return treeMaps;
    }
    
    
    
    /**
     * 根据访问链接，得到对应菜单
     * @param request
     * @return
     */
    public SysMenu getSysMenuByRequest(HttpServletRequest request){
    	
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("menuUrl", getRequestUrl(request));
        List<SysMenu> menuList = sysMenuDao.getMenuByUrl(map);
		return menuList!=null && !menuList.isEmpty() ? menuList.get(0): null;
    }
    
    
    /**
     * 访问的链接
     * @param request
     * @return
     */
    private String getRequestUrl(HttpServletRequest request) {

        String requestUrl = request.getRequestURI();

        //去掉上下文路径
        String contextPath = request.getContextPath();
        if (null != contextPath) {
            requestUrl = requestUrl.substring(contextPath.length() + 1);
        }

        String urlParams = "";

        Enumeration<String> names = (Enumeration<String>) request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = request.getParameter(name);

            if(urlParams.equals("")){
                urlParams = name+"="+value;
            }else{
                urlParams += "&"+name+"="+value;
            }
        }
        if (!urlParams.equals("")){
            requestUrl+="?"+urlParams;
        }
        return requestUrl;
    }
}