package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.AdvertSubjectGoodsRt;

/**
 * 项目名称：h2ybmg2  
 * 类名称：IAdvertSubjectGoodsRtDao  
 * 类描述：广告主题商品关联  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月8日 上午9:48:00  
 * 修改人：侯飞龙
 * 修改时间：2015年4月8日 上午9:48:00  
 * 修改备注：  
 * @version
 */
@Component
public interface IAdvertSubjectGoodsRtDao{

	/**
	 * add
	 */
	public void add(AdvertSubjectGoodsRt advertSubjectGoodsRt);
	
	/**
	 * update
	 */
	public void update(AdvertSubjectGoodsRt advertSubjectGoodsRt);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public AdvertSubjectGoodsRt get(long id);
	
	/**
	 * 批量添加广告主题商品关联
	 * @param list
	 */
	public void addBatch(List<AdvertSubjectGoodsRt> list);
	
	/**
	 * 根据主题Id和定价Id，删除主题商品关联
	 * @param map
	 * {subjectId:主题Id,
	 * goodsPriceIds:商品定价Id集合}
	 */
	public void deleteBySubjectIdAndPriceIds(Map<String,Object> map);
	
	/**
	 * 根据主题id，得到主题对应的商品列表
	 * @param map
	 * {subjectId:主题Id,
	 * ifQuery:查询条件 null时不做判断,
	 * sortname:排序字段,
	 * sortorder:排序方式}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);
	
	/**
	 * 根据主题id，得到主题对应的商品数量（用于判断商品列表是否已有商品）
	 * @param subjectId 主题Id
	 * @return
	 */
	public long getListRows(long subjectId);
}