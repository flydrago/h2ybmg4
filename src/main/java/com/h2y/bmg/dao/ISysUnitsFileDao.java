package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.SysUnitsFile;

/**
 * 类描述： 单位文件数据库操作接口
 * 作者：侯飞龙
 * 时间：2015年1月23日上午9:24:29
 * 邮件：1162040314@qq.com
 */
@Component
public interface ISysUnitsFileDao{

	/**
	 * add
	 */
	public void add(SysUnitsFile sysUnitsFile);
	
	/**
	 * update
	 */
	public void update(SysUnitsFile sysUnitsFile);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * deleteByIds
	 */
	//public void deleteByIds(List<long> ids);
	
	/**
	 * get
	 * @return
	 */
	public SysUnitsFile get(long id);
	
	/**
	 * 根据单位Id，得到单位文件列表
	 * @param params {unitId:单位Id}
	 * @return
	 */
	public List<Map<String,Object>> getListByUnitId(Map<String,Object> params);
	
	/**
	 * 批量添加单位文件
	 * @param list 
	 */
	public void addBatch(List<SysUnitsFile> list);
	
	
	/**
	 * 根据单位Id，更新ifDelete字段为0（即删除状态）
	 * @param params
	 * {unitId:单位Id,filterIdList:更新过滤Id列表 null时：不做判断}
	 */
	public void updateIfDeleteByUnitId(Map<String,Object> params);
	
}