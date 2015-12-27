package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.CommonSubject;
import com.h2y.bmg.entity.SysDictMain;

/**
 * 类描述：活动主题业务类接口
 * 作者：侯飞龙
 * 时间：2015年1月7日上午9:59:01
 * 邮件：1162040314@qq.com
 */
public interface ICommonSubjectService {
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId);
	
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getSelectGridData(HttpServletRequest request,long unitId);
	
	
	/**
	 * 保存操作
	 * @param op add：添加 modify：修改
	 * @param commonSubject 活动主题
	 * @param sysDictMain 活动主题图片存储路径
	 */
	public void save(HttpServletRequest request,String op,CommonSubject commonSubject,SysDictMain sysDictMain);
	
}
