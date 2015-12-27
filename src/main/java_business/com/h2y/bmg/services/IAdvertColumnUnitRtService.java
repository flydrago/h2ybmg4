package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.AdvertColumnUnitRt;

/**
 * 项目名称：h2ybmg2  
 * 类名称：IAdvertColumnUnitRtService  
 * 类描述：  广告栏位业务操作接口
 * 创建人：侯飞龙  
 * 创建时间：2015年4月7日 上午10:41:08  
 * 修改人：侯飞龙
 * 修改时间：2015年4月7日 上午10:41:08  
 * 修改备注：  
 * @version
 */
public interface IAdvertColumnUnitRtService{
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId);
	
	/**
	 * 广告栏保存操作
	 * @param op add：添加 modify：修改
	 * @param advertColumnUnitRt 
	 */
	public void save(String op,AdvertColumnUnitRt advertColumnUnitRt);
}
