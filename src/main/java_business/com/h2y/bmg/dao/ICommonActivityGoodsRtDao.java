package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.CommonActivityGoodsRt;

/**
 * 类描述：活动商品数据库操作接口   
 * 作者：侯飞龙
 * 时间：2015年1月7日上午10:01:31
 * 邮件：1162040314@qq.com
 */
@Component
public interface ICommonActivityGoodsRtDao{

	/**
	 * add
	 */
	public void add(CommonActivityGoodsRt commonActivityGoods);

	/**
	 * update
	 */
	public void update(CommonActivityGoodsRt commonActivityGoods);

	/**
	 * 根据dataId 修改商品开始时间和结束时间
	 * @param map
	 */
	public int updateByDataId(Map<String,Object> map);


	/**
	 * 根据dataId 修改定额商品价钱
	 * @param map
	 */
	public int updateFixedPriceByDataId(Map<String,Object> map);


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
	public CommonActivityGoodsRt get(long id);

	/**
	 * 得到分页列表
	 * @param map
	 * {unitId:单位Id,
	 * dataId:数据Id(dataType0:活动Id、1:主题Id),
	 * dataType:数据类型(0:活动、1:主题), 
	 * ifQuery:查询条件,
	 * sortname:排序字段,
	 * sortorder:排序方式,
	 * page:页码,
	 * pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);

	/**
	 * 得到记录总数
	 * @param map
	 * {unitId:单位Id,
	 * dataId:数据Id(dataType0:活动Id、1:主题Id),
	 * dataType:数据类型(0:活动、1:主题), 
	 * ifQuery:查询条件}
	 * @return
	 */
	public Long getListRows(Map<String,Object> map);

	/**
	 * 得到日期冲突的活动商品数量
	 * @param map
	 * {unitId:单位Id,
	 * goodsPriceId: 商品定价Id,
	 * startDate: 开始时间,
	 * endDate: 结束时间,
	 * op: 操作类型（add:添加、modify:修改）
	 * id: 活动商品Id（op为修改时，进行判断<>id）}
	 * @return
	 */
	public long getDateCrossActivityGoodsRows(Map<String,Object> map);

	/**
	 * 得到日期冲突的活动商品数量
	 * @param map
	 * {unitId:单位Id,
	 * goodsPriceId: 商品定价Id,
	 * startDate: 开始时间,
	 * endDate: 结束时间,
	 * op: 操作类型（add:添加、modify:修改）,
	 * id: 活动商品Id（op为修改时，进行判断<>id）
	 * @return
	 */
	public List<Map<String,Object>> getDateCrossListMap(Map<String,Object> map);



	/**
	 * 得到日期冲突的活动商品数量
	 * @param map
	 * {unitId:单位Id,
	 * goodsPriceId: 商品定价Id,
	 * startDate: 开始时间,
	 * endDate: 结束时间,
	 * op: 操作类型（add:添加、modify:修改）,
	 * id: 活动商品Id（op为修改时，进行判断<>id）}
	 * @return
	 */
	public long getDateCrossListRows(Map<String,Object> map);

	/**
	 * 根据dataId和dataType,得到活动商品关联列表
	 * @param map
	 * {dataId:数据Id,
	 * dataType:数据类型（0：活动、1：主题）}
	 * @return
	 */
	public List<CommonActivityGoodsRt> getListByDataType(Map<String,Object> map);


	/**
	 * 根据dataId和dataType,得到活动商品关联行数
	 * @param map
	 * {dataId:数据Id,
	 * dataType:数据类型（0：活动、1：主题）}
	 * @return
	 */
	public long getRowsByDataType(Map<String,Object> map);

}