package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.Zone;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2014-11-04
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface IZoneService{
	
	public void add(Zone zone);
	
	public void delete(long id);
	
	//public void deleteByIds(List<long> ids);

	public void update(Zone zone);

	public Zone get(long id);
	
	/**
	 * 根据父级Id，得到tree列表
	 * @param pid 父级Id（0：为顶级）
	 * @return
	 */
	public List<Map<String,Object>> getTreeList(long pid,String zonecode,long unitId);
	
	
    /**
     * 根据父级Id，得到grid列表
     * @param request 访问对象
     * @param parentId 父级Id
     * @return
     */
    public Map<String,Object> getGridData(HttpServletRequest request,long pid,String zonecode,long unitId);
    
    
    /**
     * 保存
     * @param op
     * @param zone
     */
    public void save(String op,Zone zone);
    
	/**
	 * 得到当前位置名称
	 * @param prefix 前缀
	 * @return
	 */
	public String getCurrentName(String prefix);
	
	
	/**
	 * 是否有子级
	 * @param pid
	 * @return
	 */
	public boolean isHasChild(String pid);
	
	
	public Map<String, Object> getAll(HttpServletRequest request,long unid);

	/**
	 * 根据code获取zone
	 * @param argsMap
	 * @return
	 */
	public Zone getZoneByCode(String zoneCode);
}
