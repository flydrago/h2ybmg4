package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.CommonActivityGoodsRt;



/**
 * 类描述：一般活动与商品业务操作接口   
 * 作者：侯飞龙
 * 时间：2015年1月7日上午10:03:14
 * 邮件：1162040314@qq.com
 */
public interface ICommonActivityGoodsRtService{
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId);
	
	
	/**
	 * 得到冲突表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getDateCrossGridData(HttpServletRequest request,long unitId);
	
	/**
	 * 保存操作
	 * @param op add：添加 modify：修改
	 * @param commonActivityGoods 
	 */
	public void save(String op,CommonActivityGoodsRt commonActivityGoods);
	
}
