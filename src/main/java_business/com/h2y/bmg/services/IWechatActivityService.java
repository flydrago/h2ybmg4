package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.entity.WechatActivity;


/**
 * 类描述：微活动业务Service接口   
 * 作者：侯飞龙
 * 时间：2014年12月17日上午11:34:30
 * 邮件：1162040314@qq.com
 */
public interface IWechatActivityService{
	
	public void add(WechatActivity wechatActivity);
	
	public void delete(long id);
	
	public void update(WechatActivity wechatActivity);

	public WechatActivity get(long id);
	
	/**
	 * 获取Grid列表数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId);
	
	
	/**
	 * 获取中奖用户Grid列表数据
	 * @param request 访问对象
	 * @return
	 */
	public Map<String,Object> getHitUserGridData(HttpServletRequest request);
	
	
	/**
	 * 保存操作
	 * @param request 访问对象
	 * @param op 操作类型 add:添加，modify:修改
	 * @param wechatActivity 微活动对象
	 * @param dictPath 存储路径
	 */
	@Transactional(rollbackFor=Exception.class)
	public void save(HttpServletRequest request,String op,WechatActivity wechatActivity,String dictPath);
	
	
	/**
	 * 根据活动Id，得到奖项列表
	 * @param activityId 活动Id
	 * @return
	 */
	public String getPrizeListByActivityId(long activityId);
}
