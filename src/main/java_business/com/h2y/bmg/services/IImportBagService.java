package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.BaseResult;
import com.h2y.bmg.entity.ImportBag;
import com.h2y.bmg.entity.ImportBagUserRt;
import com.h2y.bmg.util.SysBaseUtil.ImportBagTask;

/**
 * 项目名称：h2ybmg2  
 * 类名称：IImportBagService  
 * 类描述：导入礼包业务逻辑接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年6月19日 下午3:18:58  
 * 修改人：侯飞龙
 * 修改时间：2015年6月19日 下午3:18:58  
 * 修改备注：  
 * @version
 */
public interface IImportBagService{
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @param taskCode 任务编码
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId,ImportBagTask ... importBagTask);
	
	
	/**
	 * 得到用户表格数据
	 * @param request 访问对象
	 * @return
	 */
	public Map<String,Object> getUserGridData(HttpServletRequest request);
	
	
	/**
	 * 得到审核跟踪数据
	 * @param request 访问对象
	 * @return
	 */
	public Map<String,Object> getTrackGridData(HttpServletRequest request);
	
	
	/**
	 * 保存操作
	 * @param op add：添加 modify：修改
	 * @param importBag 
	 */
	public BaseResult save(String op,ImportBag importBag);
	
	/**
	 * 礼包用户保存操作
	 * @param op add：添加 modify：修改
	 * @param importBagUserRt 
	 */
	public BaseResult saveUser(String op,ImportBagUserRt importBagUserRt);
	
	/**
	 * 执行审核操作
	 * @param request
	 * @param bagCode
	 * @return
	 */
	public BaseResult doCheck(HttpServletRequest request,long userId,String bagCode) throws Exception;
	
	
	/**
	 * 导入操作
	 * @param request 访问对象
	 * @param importBag 
	 */
	public BaseResult importUser(HttpServletRequest request,ImportBag importBag);
}
