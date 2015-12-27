package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.FindService;
import com.h2y.bmg.entity.SysDictMain;

/**
 * 类描述：一般活动（热销活动等）业务类接口
 * 作者：侯飞龙
 * 时间：2015年1月7日上午9:59:01
 * 邮件：1162040314@qq.com
 */
public interface IFindServiceService{

	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long parentId);

	/**
	 * 保存服务操作
	 * @param op add：添加 modify：修改
	 * @param findService 
	 */
	public void saveService(HttpServletRequest request,
			String op,FindService findService,SysDictMain sysDictMain);



	/**
	 * 保存操作
	 * @param op add：添加 modify：修改
	 * @param findService 
	 */
	public void save(HttpServletRequest request,
			String op,FindService findService);

	/**
	 * 得到树数据
	 * @return
	 */
	public List<Map<String,Object>> getTreeList(String serviceType);
}
