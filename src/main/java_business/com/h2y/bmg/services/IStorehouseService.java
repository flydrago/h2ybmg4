package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.entity.Storehouse;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;

/**
 * 类描述：仓库的业务操作接口
 * 作者：侯飞龙
 * 时间：2015年1月7日上午9:59:01
 * 邮件：1162040314@qq.com
 */
public interface IStorehouseService{
	
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
	 * @param op add：添加 modify：修改
	 * @param storehouse
	 */
	@Transactional(rollbackFor=Exception.class)
	public void save(HttpServletRequest request,String op,Storehouse storehouse,SysUser sysUser);
	
	
	/**
	 * 初始化单位仓库
	 * @param sysUnits
	 */
	public void initStorehouse(SysUnits sysUnits);
	
}
