package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.SysGridColumns;
import com.h2y.bmg.entity.SysValidation;

/**
 * SysValidationDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2014-10-25
 * email:info@hwttnet.com
 */
@Component
public interface ISysValidationDao{

	/**
	 * add
	 */
	public void add(SysValidation sysValidation);
	
	/**
	 * update
	 */
	public void update(SysValidation sysValidation);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * deleteByIds
	 */
	//public void deleteByIds(List<long> ids);
	
	/**
	 * get
	 * @return
	 */
	public SysValidation get(long id);
	
	
	  /**
     * 得到验证，列表数据
     * @param map
     * key:#{joinId},#{joinType} value:关联主表Id,关联类型（menu:菜单、validate:验证维护主表）
     * @return
     */
    public List<Map<String,Object>> getListMap(Map<String,Object> map);


    /**
     * 根据关联删除，验证
     * @param map
     * key:#{joinId},#{joinType} value:关联主表Id,关联类型（menu:菜单、validate:验证维护主表）
     */
    public void deleteByJoinType(Map<String,Object> map);


    /**
     * 批量添加验证
     * @param list
     */
    public void addBatch(List<SysValidation> list);
    
    
    /**
     * 根据类型，得到对应的验证
     * @param map
     * key:#{joinId},#{joinType} value:关联主表Id,关联类型（menu:菜单、validate:验证维护主表）
     * @return
     */
    public List<SysValidation> getListByJoinType(Map<String,Object> map);
}