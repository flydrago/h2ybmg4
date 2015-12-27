package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.SysDeptUser;

/**
 * SysDeptUserDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2014-10-19
 * email:info@hwttnet.com
 */
@Component
public interface ISysDeptUserDao{

	/**
	 * add
	 */
	public void add(SysDeptUser sysDeptUser);
	
	/**
	 * update
	 */
	public void update(SysDeptUser sysDeptUser);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public SysDeptUser get(long id);
	
	/**
	 * 根据用户Id，删除用户部门关联
	 * @param userId
	 */
	public void deleteByUserId(long userId);
	
	/**
	 * 批量添加
	 * @param list
	 */
	public void addBatch(List<SysDeptUser> list);
	
	/**
	 * 根据部门Id和用户Id集合，删除
	 * @param map
	 * key1:deptId value1:部门Id
	 * key2:userIds value2:用户Id字符串，用逗点分割
	 */
	public void deleteByDeptIdAndUserIds(Map<String,Object> map);
	
	/**
	 * 根据Id，得到对应部门的行数
	 * @param userId 用户Id
	 */
	public long getDeptRowsByuserId(long userId);
	
	
	/**
	 * 根据人员id，获取人员部门列表
	 * @param userId 人员id
	 * @return
	 */
	public List<SysDeptUser> getListByUserId(Long userId);
}