package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.CouponsSource;
import com.h2y.bmg.entity.SysUnits;

/**
 * 项目名称：h2ybmg2  
 * 类名称：ICouponsSourceService  
 * 类描述： 优惠券来源业务操作接口
 * 创建人：侯飞龙  
 * 创建时间：2015年7月14日 下午5:51:31  
 * 修改人：侯飞龙
 * 修改时间：2015年7月14日 下午5:51:31  
 * 修改备注：  
 * @version
 */
public interface ICouponsSourceService {

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
	 * @param op (add：添加 modify：修改)
	 * @param couponsSource 优惠券来源
	 * @param sysUnits
	 */
	public void save(HttpServletRequest request,String op,CouponsSource couponsSource,SysUnits sysUnits);


	/**
	 * 得到来源对应的优惠间表格数据
	 * @param request 访问对象
	 * @param unitId 单位id
	 * @param sourceId 来源id
	 * @return
	 */
	public Map<String,Object> getCouponsGridData(HttpServletRequest request,long unitId,long sourceId);

	/**
	 * 添加优惠劵
	 * @param request 
	 * @param sourceId 来源id
	 */
	public void addCoupons(HttpServletRequest request,long sourceId);

	/**
	 * 移除优惠劵
	 * @param request 
	 */
	public void removeCoupons(HttpServletRequest request);


	/**
	 * 推送消息优惠劵
	 * @param request 
	 * @param sourceId 来源id
	 */
	public void sendCouponsMsg(HttpServletRequest request,long couponsId);

}
