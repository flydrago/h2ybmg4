package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.SysQueryItem;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2014-10-27
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ISysQueryItemService{
	
	public void add(SysQueryItem sysQueryItem);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(SysQueryItem sysQueryItem);

	public SysQueryItem get(long id);
	
	/**
	 * 得到列表
	 * @param map
	 * @return
	 */
	public Map<String,Object> getGirdData(Map<String,Object> map);
	
	
    /**
     * 保存查询
     * @param joinId
     * @param joinType
     * @param dataList
     */
    public void saveQueryItem(long joinId,String joinType,List<Map<String,Object>> dataList);
    
    
    /**
     * 根据访问菜单链接，得到查询条件html
     * @param request 
     * @param unitId 单位Id
     * @return
     */
    public String getConditionHtmlByRequest(HttpServletRequest request,long unitId);
    
    
    
    /**
     * 根据编码的访问链接，得到对应验证
     * @param typeCode 编码
     * @param unitId 单位Id
     * @return
     */
	public String getConditionHtmlByCode(String typeCode,long unitId);
}
