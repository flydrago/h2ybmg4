package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.GoodsType;

/**
 * 类描述：商品类型的业务操作接口
 * 作者：侯飞龙
 * 时间：2015年1月7日上午9:59:01
 * 邮件：1162040314@qq.com
 */
public interface IGoodsTypeService{
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId);
	
	/**
	 * 保存操作
	 * @param op add：添加 modify：修改
	 * @param goodsType 
	 */
	public void save(String op,GoodsType goodsType);
	
}
