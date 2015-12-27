package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.GoodsMarkInfo;

/**
 * 类描述：   商品标签详细数据库操作接口
 * 作者：侯飞龙
 * 时间：2015年1月24日下午1:43:30
 * 邮件：1162040314@qq.com
 */
@Component
public interface IGoodsMarkInfoDao{

	/**
	 * add
	 */
	public void add(GoodsMarkInfo goodsMarkInfo);

	/**
	 * update
	 */
	public void update(GoodsMarkInfo goodsMarkInfo);

	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public GoodsMarkInfo get(long id);

	/**
	 * 得到标签详细的分页列表
	 * @param map
	 * {markId:标签Id,
	 * ifQuery:查询条件 null时不做判断,
	 * sortname:排序字段,
	 * sortorder:排序方式,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);

	/**
	 * 得到标签详细的行数
	 * @param map
	 * {markId:标签Id,
	 * ifQuery:查询条件 null时不做判断}
	 * @return
	 */
	public long getListRows(Map<String,Object> map);


	/**
	 * 根据标签Id，得到下面详细数量
	 * @param markId 标签ID
	 * @return
	 */
	public long getRowsByMarkId(long markId);

	/**
	 * 根据标签Id，得到标签详细选择列表
	 * @param map 
	 * {markId: 标签ID,
	 * searchName: 标签详细名称模糊查询 null时不做判断}
	 * @return
	 */
	public List<Map<String,Object>> getListMapByMarkId(Map<String,Object> map);

	/**
	 * 标签详细Ids获取表现详细选择列表
	 * @param map
	 * {markInfoIds:标签详细Id字符串（用逗点分割）}
	 * @return
	 */
	public List<Map<String,Object>> getListMapByIds(Map<String,Object> map);



	/**
	 * 由标签名称查询  判断该标签是否重复
	 * @param goodsMark
	 * @return
	 */
	public int getSameMarkInfoName(Map<String,Object> map);

}