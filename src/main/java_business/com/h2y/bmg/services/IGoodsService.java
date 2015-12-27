package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.entity.Goods;
import com.h2y.bmg.entity.GoodsPrice;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;

/**
 * 类描述：商品的业务操作接口 作者：侯飞龙 时间：2015年1月7日上午9:59:01 邮件：1162040314@qq.com
 */
public interface IGoodsService {

	/**
	 * 得到表格数据
	 * 
	 * @param request
	 *            访问对象
	 * @return
	 */
	public Map<String, Object> getGridData(HttpServletRequest request);

	/**
	 * 得到选择窗口表格数据
	 * 
	 * @param request
	 *            访问对象
	 * @param unitId
	 *            单位Id
	 * @return
	 */
	public Map<String, Object> getSelectGridData(HttpServletRequest request,
			long unitId);

	/**
	 * 获取商品代理列表
	 * 
	 * @param request
	 *            访问对象
	 * @return
	 */
	public Map<String, Object> getUnitGridData(HttpServletRequest request);

	/**
	 * 保存操作
	 * 
	 * @param request
	 * @param op
	 *            add：添加 modify：修改
	 * @param goods
	 */
	@Transactional(rollbackFor = Exception.class)
	public void save(HttpServletRequest request, String op, Goods goods,
			SysUser sysUser, SysUnits sysUnits);

	/**
	 * 得到商品标签数据
	 * 
	 * @param goods
	 * @return
	 */
	public List<Map<String, Object>> getGoodsMarkData(Goods goods);

	/**
	 * 得到商品标签数据
	 * 
	 * @param goods
	 * @return
	 */
	public List<Map<String, Object>> getGoodsMarkData(GoodsPrice goodsPrice);
}
