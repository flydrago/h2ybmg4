package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.SysDictMain;

/**
 * 项目名称：h2ybmg2  
 * 类名称：ISysDictMainDao  
 * 类描述：  字典主表数据库操作接口
 * 创建人：侯飞龙  
 * 创建时间：2015年3月23日 上午11:12:08  
 * 修改人：侯飞龙
 * 修改时间：2015年3月23日 上午11:12:08  
 * 修改备注：  
 * @version
 */
@Component
public interface ISysDictMainDao{

	/**
	 * add
	 */
	public void add(SysDictMain sysDictMain);
	
	/**
	 * update
	 */
	public void update(SysDictMain sysDictMain);
	
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
	public SysDictMain get(long id);
	
	/**
	 * 得到列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	public long getListRows(Map<String,Object> map);
	
	/**
	 * 得到指定编码的行数
	 * @param 
	 * key1:dictCode value1:编码
	 * key2:id value2:主键Id（null时，不做判断） 
	 * @return
	 */
	public long getRowsByDictCode(Map<String,Object> map);
	
	
	/**
	 * 根据字典主表Id,得到的字典详细行数
	 * @param mainId 字典主表Id
	 * @return
	 */
	public long getDictDetailRowsByMainId(long mainId);
	
	/**
	 * 得到字典主表，列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getDictMainTreeList(Map<String,Object> map);
	
	
	/**
	 * 得到所有列表
	 * @return
	 */
	public List<SysDictMain> getAllList();
	
	
	/**
	 * 根据编码得到字典主表信息
	 * @return
	 */
	public SysDictMain getSysDictMainByCode(String code);
	
	/**
	 * 得到字典主表第一级树列表
	 * @return
	 */
	public List<Map<String,Object>> getSysDictMainFirstTreeData();
	
	/**
	 * 得到字典主表子级的数量
	 * @param parentId 父级Id
	 * @return
	 */
	public long getSysDictMainChildRows(long parentId);
}