package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.Coupons;
import com.h2y.bmg.entity.CouponsDetail;
import com.h2y.bmg.entity.SysUnits;

/**
 * 项目名称：h2ybmg2  
 * 类名称：ICouponsService  
 * 类描述：优惠券业务逻辑操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年7月3日 下午3:39:08  
 * 修改人：侯飞龙
 * 修改时间：2015年7月3日 下午3:39:08  
 * 修改备注：  
 * @version
 */
public interface ICouponsService {
	
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
	 * @param coupons
	 * @param couponsDetail
	 * @param sysUnits
	 */
	public void save(HttpServletRequest request,String op,Coupons coupons,CouponsDetail couponsDetail,SysUnits sysUnits);
	
	
	/**
	 * 得到认领表格数据
	 * @param request 访问对象
	 * @return
	 */
	public Map<String,Object> getClaimGridData(HttpServletRequest request);
	
	/**
	 * 得到选择表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getSelectGridData(HttpServletRequest request,long unitId);
	
	
	/**
	 * 得到窗口选择表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getSelectDialogList(HttpServletRequest request,long unitId);
	
}
