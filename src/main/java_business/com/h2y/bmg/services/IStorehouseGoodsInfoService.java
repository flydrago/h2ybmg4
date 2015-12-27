package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.entity.SysUser;

/**
 * 类描述：仓库的业务操作接口
 * 作者：侯飞龙
 * 时间：2015年1月7日上午9:59:01
 * 邮件：1162040314@qq.com
 */
public interface IStorehouseGoodsInfoService{
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId);
	
	/**
	 * 保存操作
	 * @param request
	 * @param sysUser 登陆用户
	 */
	@Transactional(rollbackFor=Exception.class)
	public void save(HttpServletRequest request,SysUser sysUser);
	
	/**
	 * 得到仓库 商品详细表格数据
	 * @param request 访问对象
	 * @return
	 */
	public Map<String,Object> getDetailGridData(HttpServletRequest request,long unitId);
	
}
