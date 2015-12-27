package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.SysValidation;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2014-10-25
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ISysValidationService{
	
	public void add(SysValidation sysValidation);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(SysValidation sysValidation);

	public SysValidation get(long id);
	
	
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
    public void saveValidation(long joinId,String joinType,List<Map<String,Object>> dataList);
    
    
    /**
     * 根据菜单的访问链接，得到对应验证
     * @param request
     */
    public String getValidationByRequest(HttpServletRequest request);
    
    /**
     * 根据编码得到验证信息
     * @param code 验证编码
     * @return
     */
    public String getValidationByCode(String code);
}
