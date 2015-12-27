package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.GoodsMarkInfo;

/**
 * 类描述：商品标签详细的业务操作接口
 * 作者：侯飞龙
 * 时间：2015年1月7日上午9:59:01
 * 邮件：1162040314@qq.com
 */
public interface IGoodsMarkInfoService{

	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request);

	/**
	 * 保存操作
	 * @param op add：添加 modify：修改
	 * @param goodsMarkInfo
	 */
	public void save(HttpServletRequest request,String op,GoodsMarkInfo goodsMarkInfo,String dictPath);

	/**
	 * 由标签名称查询  判断该标签是否重复
	 * @param goodsMark
	 * @return
	 */
	public int getSameMarkInfoName(GoodsMarkInfo goodsMarkInfo,String op);

}
