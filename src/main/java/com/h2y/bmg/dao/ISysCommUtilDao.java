package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * SysButtonBusinessMapperInterface,order find

 * @author hwttnet

 * time:2012-07-18 23:14:57

 * email:duanxg@hwttnet.com

 * QQ:2410960521

 * Gmail:
 */
@Component
public interface ISysCommUtilDao{

	/**
	 * 得到列表数据
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> params);
	
	/**
	 * 插入数据
	 * @param params
	 */
	public void insert(Map<String,Object> params);
	
	/**
	 * 更新数据
	 * @param params
	 */
	public void update(Map<String,Object> params);
	
	/**
	 * 删除数据
	 * @param params
	 */
	public String delete(Map<String,Object> params);
	
	/**
	 * 得到符合条件的总记录数
	 * @param params
	 * @return
	 */
	public long getRows(Map<String,Object> params);
}