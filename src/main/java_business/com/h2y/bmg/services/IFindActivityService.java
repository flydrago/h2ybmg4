package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.entity.FindActivity;
import com.h2y.bmg.entity.SysUser;



/**
 * 发现模块活动Service类
 */
public interface IFindActivityService{

	public void add(FindActivity findActivity);

	public void delete(long id);

	//public void deleteByIds(List<long> ids);

	public void update(FindActivity findActivity);

	public FindActivity get(long id);


	/**
	 * 得到列表
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return 活动列表数据
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId);


	/**
	 * 保存操作
	 * @param request 访问对象
	 * @param op 操作类型 add:添加 modify:修改
	 * @param findActivity 活动对象
	 * @param dictPath 字典维护的保存路径
	 */
	@Transactional(rollbackFor=Exception.class)
	public void save(HttpServletRequest request,String op,FindActivity findActivity,String dictPath,SysUser sysUser);


	/**
	 * 根据活动Id，得到对应商品关联列表JSON
	 * @param activityId 活动Id
	 * @return
	 */
	public String getGoodsListJsonActivityId(long activityId);


	/**
	 * 得到评论列表
	 * @param request 访问对象
	 * @param activityId 活动Id
	 * @return 活动对应评论列表数据
	 */
	public Map<String,Object> getCommentGridData(HttpServletRequest request,long activityId);
}
