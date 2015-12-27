package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.h2y.bmg.entity.SysType;

/**
 * SysTypeDaoInterface,order CRUD ;CRUD:Create,Retrieve/Read,Update,Delete
 * @author hwttnet
 * version:1.2
 * time:2014-10-17
 * email:info@hwttnet.com
 */
@Component
public interface ISysTypeDao{

	/**
	 * add
	 */
	public void add(SysType sysType);
	
	/**
	 * update
	 */
	public void update(SysType sysType);
	
	/**
	 * delete
	 */
	public void deleteById(Long id);

	/**
	 * deleteByIds
	 */
	public void deleteByIds(List<Long> ids);
	
	/**
	 * get
	 * @return
	 */
	public SysType get(Long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<SysType> getList(SysType sysType);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<SysType> getListPage(Map<String, Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public Long getRows(Map<String, Object> map);

    /**
     * 批量添加
     * @param list
     */
    public void addBatch(List<SysType> list);

    /**
     * 得到列表
     * @param map
     * @return
     */
    public List<Map<String,Object>> getListMap(Map<String,Object> map);

    /**
     * 系统类型的树数据
     * @param map
     * key:#{pid}，#{type} value:父级Id，系统类型（gird:列维护类型、query:查询类型、validate：验证类型）
     * @return
     */
    public List<Map<String,Object>> getTreeData(Map<String,Object> map);


    /**
     * 得到子级行数
     * @param pid 父级Id
     * @return
     */
    public long getChildTypeRows(long pid);


    /**
     * 得到相同编码的行数
     * @param map
     * key:#{code},#{type},#{id} value:编码，类型（grid:列维护、query:查询、validate：验证），主键ID（null:不做判断）
     * @return
     */
    public long getSameCodeRows(Map<String,Object> map);
    
    
    /**
     * 根据编码得到系统类型
     * @param map
     * key1:code value1:编码
     * key2:type value2:类型（grid:列维护、query:查询、validate：验证），主键ID（null:不做判断）
     * @return
     */
    public SysType getSysTypeByCode(Map<String,Object> map);
}