package com.h2y.bmg.services;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.MemberUser;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2015-04-08
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface IMemberUserService{

	//获取会员列表
	public Map<String, Object> getList(HttpServletRequest request);

	public void delete(long id);

	public void update(MemberUser memberUser);

	public MemberUser get(long id);


	//根据id批量修改状态
	public void updateStatusByIds(Map<String,Object> map);

	/**
	 * 获取本地会员列表
	 * @param request
	 */
	public Map<String,Object> getLocalMemberList(HttpServletRequest request,String zoneCode);

	/**
	 * 获取本地会员 的推荐用户列表
	 * @param request
	 * @return
	 */
	public Map<String, Object> getMemberRecommendList(HttpServletRequest request,String account);


}
