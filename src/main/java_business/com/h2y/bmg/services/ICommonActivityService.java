package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.CommonActivity;

/**
 * 类描述：一般活动（热销活动等）业务类接口
 * 作者：侯飞龙
 * 时间：2015年1月7日上午9:59:01
 * 邮件：1162040314@qq.com
 */
public interface ICommonActivityService{

	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId);


	/**
	 * 保存操作
	 * @param op add：添加 modify：修改
	 * @param commonActivity 活动对象
	 */
	public void save(String op,CommonActivity commonActivity);


	/**
	 * 得到活动树数据
	 * @param unitId 单位Id
	 * @param dataType 0：商品列表活动 1:主题活动
	 * @return
	 */
	public List<Map<String,Object>> getTreeList(long unitId,int dataType);



	/**
	 * 判断秒杀活动是否有时间冲突 
	 * @param map
	 * @return
	 */
	public Long getDateCrossListRows(Map<String,Object> map);


}
