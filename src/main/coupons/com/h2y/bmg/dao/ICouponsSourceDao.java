package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.CouponsSource;

/**
 * 项目名称：h2ybmg2  
 * 类名称：ICouponsSourceDao  
 * 类描述：优惠券来源数据库操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年7月14日 下午5:18:56  
 * 修改人：侯飞龙
 * 修改时间：2015年7月14日 下午5:18:56  
 * 修改备注：  
 * @version
 */
@Component
public interface ICouponsSourceDao{

	/**
	 * add
	 */
	public void add(CouponsSource couponsSource);

	/**
	 * update
	 */
	public void update(CouponsSource couponsSource);

	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public CouponsSource get(long id);

	/**
	 * 得到列表
	 * @param map
	 * {unitId:单位id,
	 * isStart:是否开始,
	 * ifQuery:查询条件,
	 * page:页码,
	 * pagesize:页显示最大记录数,
	 * sortorder:排序方式,
	 * sortname:排序字段}
	 * @return
	 */
	public List<Map<String,Object>> getListMap(Map<String,Object> map);

	/**
	 * 得到列表总数
	 * @param map
	 * {unitId:单位id,
	 * isStart:是否开始,
	 * ifQuery:查询条件}
	 * @return
	 */
	public long getListRows(Map<String,Object> map);

	/**
	 * 得到日期冲突的行数
	 * @param map
	 * {startDate:开始时间,
	 * endDate:截止时间,
	 * sourceCode:来源编码,
	 * id:来源id,
	 * op:操作类型（add:添加操作、modify:修改操作）
	 * }
	 * @return
	 */
	public long getDateCrossRows(Map<String,Object> map);

	/**
	 * 根据来源编码获取来源数量
	 * 每个来源最多维护两个
	 * @param map
	 * @return
	 */
	public long getCountBySourceCode(Map<String,Object> map);

}