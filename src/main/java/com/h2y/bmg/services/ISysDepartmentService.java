package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import com.h2y.bmg.entity.SysDepartment;
import com.h2y.bmg.entity.SysShopInfo;
import com.h2y.bmg.entity.SysUnits;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2014-10-18
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ISysDepartmentService{

	public void add(SysDepartment sysDepartment);

	public void delete(long id);

	//public void deleteByIds(List<long> ids);

	public void update(SysDepartment sysDepartment);

	public SysDepartment get(long id);

	public List<SysDepartment> getList(SysDepartment sysDepartment);

	/**
	 * getListPage
	 * @return
	 */
	public List<SysDepartment> getListPage(Map<String, Object> map);

	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String, Object> map);


	/**
	 * 是否子级
	 * @param sysDepartment
	 * @return
	 */
	public boolean isHasChild(SysDepartment sysDepartment);

	/**
	 * 得到表格列数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @param parentId 父级Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId,long parentId);

	/**
	 * 根据父级编码，得到子级树节点
	 * @param unitId 单位Id
	 * @param parentId 父级Id
	 * @return
	 */
	public List<Map<String,Object>> getChildTreeData(long unitId,long parentId);


	/**
	 * 保存部门（添加、修改）
	 * @param sysDepartment 部门对象
	 * @param op 操作类型，add:添加、modify:修改
	 */
	public void save(HttpServletRequest requst,SysDepartment sysDepartment,SysShopInfo sysShopInfo,String op);

	/**
	 * 逻辑删除部门
	 * @param sysDepartment
	 */
	@Transactional(rollbackFor=Exception.class)
	public void deleteDepartment(SysDepartment sysDepartment);
	
	
	/**
	 * 更新部门的象过河信息
	 * @param requst 访问信息
	 * @param sysDepartment 部门信息
	 * @param sysUnits 单位信息
	 * @param op 操作类型 （add:添加、modify:修改）
	 */
	public void updateDeptXgh(HttpServletRequest requst,SysDepartment sysDepartment,SysUnits sysUnits,String op);

}
