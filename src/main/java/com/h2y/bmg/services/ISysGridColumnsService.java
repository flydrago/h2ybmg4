package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.SysGridColumns;


/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2014-10-16
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ISysGridColumnsService{
	
	public void add(SysGridColumns sysGridColumns);
	
	public void delete(Long id);
	
	public void deleteByIds(List<Long> ids);

	public void update(SysGridColumns sysGridColumns);

	public SysGridColumns get(Long id);
	
	public List<SysGridColumns> getList(SysGridColumns sysGridColumns);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<SysGridColumns> getListPage(Map<String, Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String, Object> map);

    /**
     * 得到表格列数据
     * @param map
     * @return
     */
    public Map<String,Object> getGridData(Map<String,Object> map);


    /**
     * 保存列宽
     * @param joinId
     * @param joinType
     * @param dataList
     */
    public void saveGridColumns(long joinId,String joinType,List<Map<String,Object>> dataList);
    
    
    /**
     * 根据菜单的访问链接，得到对应列维护
     * @param request
     */
    public String getGridColumsByRequest(HttpServletRequest request);
    
    
    /**
     * 根据编码，得到对应列维护
     * @param request
     */
    public String getGridColumsByCode(String code);
}
