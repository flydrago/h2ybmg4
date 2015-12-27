package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;
import com.h2y.bmg.entity.SysType;



/**
 * 
 * @author hwttnet
 * version:1.2
 * time:2014-10-17
 * email:info@hwttnet.com
 * 
 * Service Interface
 */
public interface ISysTypeService{
	
	public void add(SysType sysType);
	
	public void delete(Long id);
	
	public void deleteByIds(List<Long> ids);

	public void update(SysType sysType);

	public SysType get(Long id);
	
	public List<SysType> getList(SysType sysType);
	
	/**
	 * getListPage
	 * @return
	 */
	public List<SysType> getListPage(Map<String, Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return
	 */
	public Long getRows(Map<String, Object> map);

    /**
     * 得到子级树数据
     * @param type 系统类型（gird:列维护类型、query:查询类型、validate：验证类型）
     * @param pid 父级Id
     * @return
     */
    public List<Map<String,Object>> getChildTreeData(String type, Long pid);


    /**
     * 得到树数据
     * @param type 系统类型（gird:列维护类型、query:查询类型、validate：验证类型）
     * @return
     */
    public List<Map<String,Object>> getTreeData(String type);

    /**
     * 得到列表数据
     * @param type 系统类型（gird:列维护类型、query:查询类型、validate：验证类型）
     * @param pid 父级Id
     * @return
     */
    public Map<String,Object> getGridData(String type,long pid);


    /**
     *
     * @param type
     * @param pid
     */
    public void save(String type,long pid);


    /**
     * 相同编码验证
     * @param op
     * @param sysType
     * @return
     */
    public boolean isHasSameCode(String op, SysType sysType);


    /**
     * 是否有子级
     * @param sysType
     * @return
     */
    public boolean isHasChild(SysType sysType);
}
