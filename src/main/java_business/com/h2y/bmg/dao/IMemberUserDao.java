package com.h2y.bmg.dao;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.MemberUser;

/**
 * MemberUserDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2015-04-08
 * email:info@hwttnet.com
 */
@Component
public interface IMemberUserDao{

	//获取会员列表
	public List<Map<String, Object>> getListMap(Map<String,Object> map);


	/**
	 * update
	 */
	public void update(MemberUser memberUser);

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
	public MemberUser get(long id);



	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getListRows(Map<String,Object> map);

	/**
	 * 根据id批量修改状态
	 * @param map
	 */
	public void updateStatusByIds(Map<String, Object> map);
	
	
	/**
	 * 根据账号得到会员信息
	 * @return
	 */
	public MemberUser getByAccount(String account);


	/**
	 * 获取本地会员List
	 * @param map
	 * @return
	 */
	public List<MemberUser> getLocalMemberListMap(
			Map<String, Object> map);

	/**
	 * 获取本地会员总数
	 * @param map
	 * @return
	 */
	public Long getLocalMemberListRows(Map<String, Object> map);


	/**
	 * 获取会员 推荐用户
	 * @param map
	 * @return
	 */
	public List<MemberUser> getRefMemberList(Map<String, Object> map);

	/**
	 * 获取会员 推荐用户总数
	 * @param map
	 * @return
	 */
	public Long getRefMemberRows(Map<String, Object> map);


	/**
	 * 获取会员 推荐用户account
	 * @param map
	 * @return
	 */
	public List<String> getRefAccountList(Map<String, Object> map);
	
	
	/**
	 * 获取投票新增用户
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getNewListMap(Map<String, Object> map);

	/**
	 * 获取投票新增用户总数
	 * @param map
	 * @return
	 */
	public Long getNewListRows(Map<String, Object> map);

}