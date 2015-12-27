package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import com.h2y.bmg.entity.SysButton;
import com.h2y.bmg.entity.SysUser;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2014-10-16
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ISysButtonService{
	
	public void add(SysButton sysButton);
	
	public void delete(Long id);
	
	public void deleteByIds(List<Long> ids);

	public void update(SysButton sysButton);

	public SysButton get(Long id);
	
	public List<SysButton> getList(SysButton sysButton);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<SysButton> getListPage(Map<String, Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String, Object> map);


    /**
     * 得到菜单的按钮列表
     * @param menuId
     * @return
     */
    public Map<String,Object> getGridData(long menuId);


    /**
     * 保存按钮数据
     * @param menuId
     * @param dataList
     */
    public void saveButton(long menuId,List<Map<String,Object>> dataList);


    /**
     * 得到菜单的按钮
     * @param request
     * @param unitId
     * @param userId
     * @return
     */
    public String getMenuButtons(HttpServletRequest request,Long sysRoleId,SysUser sysUser);
}
