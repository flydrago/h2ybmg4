package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 选择窗口Service
 */
public interface ISysDialogService {

	
	/**
	 * 获取选择窗口的数据列表
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @param op user:用户列表、dept:部门列表
	 * @return
	 */
	public List<Map<String,Object>> getList(HttpServletRequest request,long unitId,String op);
	
	/**
	 * 得到混合窗口选中的列表
	 * @param dataArray 后台获得的列表数组
	 * @return
	 */
	public Map<String,List<String>> getMixSelectedList(String [] dataArray);
	
	
	/**
	 * 得到混合窗口选中的列表中，过滤的人员Id集合
	 * @param dataArray  后台获得的列表数组
	 * @param unitId 单位Id
	 * @return
	 */
	public List<Long> getMixDistinctPepleIds(String [] dataArray,Long unitId);
	
	
	
	/**
	 * 获取选择窗口的商品数据列表
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @param op user:用户列表、dept:部门列表
	 * @return
	 */
	public List<Map<String,Object>> getGoodsList(HttpServletRequest request,long unitId);
	
	
	/**
	 * 得到商品类型树数据
	 * @return
	 */
	public List<Map<String,Object>> getGroodsTypeTreeData();
	
	/**
	 * 得到标签树数据
	 * @param typeCode 商品类型编码
	 * @return
	 */
	public List<Map<String,Object>> getMarkTreeDataByTypeCode(String typeCode);
	
	/**
	 * 进销存 获取选择窗口的商品数据列表
	 * @param request
	 * @param unitId
	 * @return
	 */
	public List<Map<String,Object>> getGoodsListForJxc(HttpServletRequest request,long unitId);
}
