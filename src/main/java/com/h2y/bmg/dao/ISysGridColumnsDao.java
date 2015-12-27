package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.h2y.bmg.entity.SysGridColumns;

/**
 * SysGridColumnsDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2014-10-16
 * email:info@hwttnet.com
 */
@Component
public interface ISysGridColumnsDao{

	/**
	 * add
	 */
	public void add(SysGridColumns sysGridColumns);
	
	/**
	 * update
	 */
	public void update(SysGridColumns sysGridColumns);
	
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
	public SysGridColumns get(Long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<SysGridColumns> getList(SysGridColumns sysGridColumns);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<SysGridColumns> getListPage(Map<String, Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String, Object> map);


    /**
     * 得到表格列，列表数据
     * @param map
     * key:#{joinId},#{joinType} value:关联主表Id,关联类型（menu:菜单、grid:列维护主表）
     * @return
     */
    public List<Map<String,Object>> getListMap(Map<String,Object> map);


    /**
     * 根据关联删除，表格列
     * @param map
     * key:#{joinId},#{joinType} value:关联主表Id,关联类型（menu:菜单、grid:列维护主表）
     */
    public void deleteByJoinType(Map<String,Object> map);


    /**
     * 批量添加表格列
     * @param list
     */
    public void addBatch(List<SysGridColumns> list);
    
    
    /**
     * 根据类型，得到对应的表格列
     * @param map
     * key:#{joinId},#{joinType} value:关联主表Id,关联类型（menu:菜单、grid:列维护主表）
     * @return
     */
    public List<SysGridColumns> getListByJoinType(Map<String,Object> map);
}