package com.h2y.bmg.dao;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.CouponsDetail;

/**
 * 项目名称：h2ybmg2  
 * 类名称：ICouponsDetailDao  
 * 类描述：优惠券详细数据库操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年7月3日 下午3:01:52  
 * 修改人：侯飞龙
 * 修改时间：2015年7月3日 下午3:01:52  
 * 修改备注：  
 * @version
 */
@Component
public interface ICouponsDetailDao{

	/**
	 * add
	 */
	public void add(CouponsDetail couponsDetail);
	
	/**
	 * update
	 */
	public void update(CouponsDetail couponsDetail);
	
	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public CouponsDetail get(long id);
	
	/**
	 * 根据优惠券编码，得到优惠券详细
	 * @param couponsCode 优惠券编码
	 * @return
	 */
	public CouponsDetail getByCouponsCode(String couponsCode);
	
}