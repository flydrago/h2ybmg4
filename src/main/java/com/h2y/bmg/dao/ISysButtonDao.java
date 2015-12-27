package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.h2y.bmg.entity.SysButton;

/**
 * SysButtonDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2014-10-16
 * email:info@hwttnet.com
 */
@Component
public interface ISysButtonDao{

	/**
	 * add
	 */
	public void add(SysButton sysButton);
	
	/**
	 * update
	 */
	public void update(SysButton sysButton);
	
	/**
	 * delete
	 */
	public void deleteById(Long id);

	/**
	 * deleteByIds
	 */
	public void deleteByIds(List<Long> ids);
	
	/**
	 * get
	 * @return
	 */
	public SysButton get(Long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<SysButton> getList(SysButton sysButton);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<SysButton> getListPage(Map<String, Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String, Object> map);


    /**
     * 得到按钮列表
     * @param map
     * @return
     */
    public List<Map<String,Object>> getListMap(Map<String,Object> map);

    /**
     * 
     * @param map
     * key:menuId value:菜单Id
     * key:filterIdList value:删除过滤的Id集合
     */
    public void deleteByMenuId(Map<String,Object> map);

    public void addBatch(List<SysButton> list);


    /**
     * 得到用户菜单按钮，进行权限控制
     * @param map
     * @return
     */
    public List<SysButton> getUserButtons(Map<String,Object> map);
    
    /**
     * 得到菜单的按钮
     * @param menuId
     * @return
     */
    public List<SysButton> getButtonByMenuId(long menuId);
    
    
    /**
     * 根据菜单Id集合，得到按钮Id集合
     * @param map
     * key:list value:菜单Id集合
     * @return
     */
    public List<Long> getButtonIdsByMenuIds(Map<String,Object> map);
}