package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.SysMenu;
import com.h2y.bmg.entity.SysUser;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2014-10-13
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ISysMenuService{
	
	public void add(SysMenu sysMenu);
	
	public void delete(Long id);
	
	public void deleteByIds(List<Long> ids);

	public void update(SysMenu sysMenu);

	public SysMenu get(Long id);
	
	public List<SysMenu> getList(SysMenu sysMenu);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<SysMenu> getListPage(Map<String, Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String, Object> map);
	
	
	/**
	 * 得到用户的功能菜单的树数据
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getUserMenuTreeData(long sysRoleId,SysUser sysUser);
	
    /**
     * 得到系统菜单树数据
     * @return
     */
	public List<Map<String,Object>> getSysMenuTreeData();
	
	/**
	 * 得到列表数据
	 * @param map
	 * @return
	 */
	public Map<String,Object> getGridData(Map<String, Object> map);


    /**
     * 判断菜单是否有子级菜单
     * @param menuId
     * @return
     */
    public boolean isHasChildMenu(long menuId);


    /**
     * 得到父级菜单列表
     * @param menuId
     * @return
     */
    public List<SysMenu> getParentMenus(long menuId);


    /**
     * 得到菜单维护的树数据
     * @return
     */
    public List<Map<String,Object>> getTreeData();


    /**
     * 得到按钮维护的菜单树数据
     * @return
     */
    public List<Map<String,Object>> getButtonMenuTreeData();

    /**
     * 得到表格列维护的菜单树数据
     * @return
     */
    public List<Map<String,Object>> getGridMenuTreeData();
    
    /**
     * 得到查询维护的菜单树数据
     * @return
     */
    public List<Map<String,Object>> getQueryMenuTreeData();
    
    /**
     * 得到验证维护的菜单数据库
     * @return
     */
    public List<Map<String,Object>> getValidationMenuTreeData();
    
    
    /**
     * 根据访问链接，得到对应菜单
     * @param request
     * @return
     */
    public SysMenu getSysMenuByRequest(HttpServletRequest request);
}
