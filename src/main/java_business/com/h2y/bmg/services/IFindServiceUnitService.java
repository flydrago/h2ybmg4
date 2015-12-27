package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.entity.FindServiceUnit;

/**
 * 类描述：一般活动（热销活动等）业务类接口
 * 作者：侯飞龙
 * 时间：2015年1月7日上午9:59:01
 * 邮件：1162040314@qq.com
 */
public interface IFindServiceUnitService{

	/**
	 * 保存操作
	 * @param request
	 * {unitId:单位Id,
	 * serviceIds:单位对应服务的Id字符串用逗点分割}
	 */
	@Transactional(rollbackFor=Exception.class)
	public void save(HttpServletRequest request);


	/**
	 * 保存公共服务
	 * @param request
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveUnitService(HttpServletRequest request);

	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int delete(FindServiceUnit findServiceUnit);



	/**
	 * 得到树数据
	 * @return
	 */
	public List<Map<String,Object>> getUnitTreeList(long unitId);


	/**
	 * 获取当前单位 公共服务列表
	 * @param unitId
	 * @return
	 */
	public Map<String,Object> getUnitServiceList(HttpServletRequest request,long unitId);

	/**
	 * 公共服务选择列表
	 * @param request
	 * @param unitId
	 * @return
	 */
	public Map<String,Object> getUnitServiceSelectList(HttpServletRequest request,long unitId);

	/**
	 * 修改
	 * @param findServiceUnit
	 * @return
	 */
	public int update(FindServiceUnit findServiceUnit);

}
