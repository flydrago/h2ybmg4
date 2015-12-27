package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.CouponsSourceRt;

/**
 * 项目名称：h2ybmg2  
 * 类名称：ICouponsSourceRtDao  
 * 类描述：  优惠劵来源关联数据库操作接口
 * 创建人：侯飞龙  
 * 创建时间：2015年7月15日 下午4:56:00  
 * 修改人：侯飞龙
 * 修改时间：2015年7月15日 下午4:56:00  
 * 修改备注：  
 * @version
 */
@Component
public interface ICouponsSourceRtDao{

	/**
	 * add
	 */
	public void add(CouponsSourceRt couponsSourceRt);
	
	/**
	 * update
	 */
	public void update(CouponsSourceRt couponsSourceRt);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public CouponsSourceRt get(long id);
	
	/**
	 * 优惠劵来源批量添加
	 * @param list 
	 */
	public void addBatch(List<CouponsSourceRt> list);
	
	/**
	 * 根据来源id和优惠劵编码列表，修改对应状态
	 * @param map
	 * {couponsCodeList:优惠劵编码列表,
	 * sourceId:来源id,
	 * status:状态 -1:删除、0:正常}
	 */
	public void updateStatusBySourceId(Map<String,Object> map);
	
	/**
	 * 根据关联id集合，修改对应的状态
	 * @param map
	 * {ids:关联id集合,
	 * status:状态 -1:删除、0:正常}
	 */
	public void updateStatusByIds(Map<String,Object> map);
	
	/**
	 * 根据来源id，得到优惠劵编码里诶包
	 * @param sourceId 来源id
	 * @return
	 */
	public List<String> getCouponsCodeListBySourceId(long sourceId);
}