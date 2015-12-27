package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.SysDepartment;

/**
 * SysDepartmentDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2014-10-18
 * email:info@hwttnet.com
 */
@Component
public interface ISysDepartmentDao{

	/**
	 * add
	 */
	public void add(SysDepartment sysDepartment);
	
	/**
	 * update
	 */
	public void update(SysDepartment sysDepartment);
	
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
	public SysDepartment get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<SysDepartment> getList(SysDepartment sysDepartment);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<SysDepartment> getListPage(Map<String, Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String, Object> map);


    /**
     * 根据parentId,得到子级树数据
     * @param map
     * key:#{parentId},#{unitId} value:#{父级Id},#{单位Id}
     * @return
     */
    public List<Map<String,Object>> getChildTreeData(Map<String,Object> map);

    /**
     * 根据parentId,得到子级部门的行数
     * @param map
     * key:#{parentId} value：父级Id
     * @return
     */
    public long getChildDepartmentRows(long parentId);


    /**
     * 获取分页列表
     * @param map
     * key:#{parentId},#{unitId},${page},${pagesize} 
     * value:#{父级Id},#{单位Id}，${当前页}，${页显示最大数}
     * @return
     */
    public List<Map<String,Object>> getListMap(Map<String,Object> map);

    /**
     * 获取总行数
     * @param map
     * key:#{parentId},#{unitId},${page},${pagesize} 
     * value:#{父级Id},#{单位Id}，${当前页}，${页显示最大数}
     * @return
     */
    public long getListRows(Map<String,Object> map);
    
    
	/**
	 * 得到选择窗口中的部门列表
	 * @param map
	 * key1:unitId value1:单位Id
	 * key1:deptId value1:部门Id（检索直属下级使用）
	 * key1:deptCode value1:部门编码（级联使用）
	 * key4:isCascade value4:是否级联（yes：是、no：否）
	 * @return
	 */
    public List<Map<String,Object>> getSelectDialogDepartmentList(Map<String,Object> map);
    
    
    
    /**
     * 根据部门编码列表，得到下面的用户Id集合
     * @param map
     * key:list value:部门编码集合
     */
    public List<Long> getUserIdsByDeptCodes(Map<String,Object> map);
    
    /**
     * 修改部门为具有门店部门标示
     * @param key:ifHasShop value:是否有门店：1：有，0：没有
     * @param key:ids value:Ids
     */
    public void updateIfShopByIds(Map<String,Object> map);
    
    /**
     * 得到部门下面门店的数量
     * @param pid
     */
    public long getShopRowsByPid(long pid);
    
    /**
     * 根据部门Id，得到此部门下面用户的数量
     * @param deptId 部门Id
     * @return
     */
    public long getUserRowsByDeptId(long deptId);
    
    
    /**
     * 根据用户ID 获取用户所属的门店
     * @param map
     * @return
     */
    public List<Map<String,Object>> getShopList(Map<String, Object> map);
    
    
    /**
     * 得到虚拟门店的数量
     * @param map
     * {unitId:单位Id,
     * id:门店Id（null时不做判断）}
     * @return
     */
    public long getVirtualShopRows(Map<String,Object> map);
    
}