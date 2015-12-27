package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.entity.GoodsPrice;
import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;

/**
 * 类描述：商品定价的业务操作接口 作者：侯飞龙 时间：2015年1月7日上午9:59:01 邮件：1162040314@qq.com
 */
public interface IGoodsPriceService {

	/**
	 * 得到表格数据
	 * 
	 * @param request
	 *            访问对象
	 * @param unitId
	 *            单位Id
	 * @return
	 */
	public Map<String, Object> getGridData(HttpServletRequest request,
			long unitId);

	public Map<String, Object> getBuyGridData(HttpServletRequest request);

	/**
	 * 得到定价表格数据
	 * 
	 * @param request
	 *            访问对象
	 * @param unitId
	 *            单位Id
	 * @return
	 */
	public Map<String, Object> getSelectGridData(HttpServletRequest request,
			long unitId);

	/**
	 * 保存操作
	 * 
	 * @param request
	 * @param op
	 *            add：添加 modify：修改
	 * @param goodsPrice
	 */
	@Transactional(rollbackFor = Exception.class)
	public void save(HttpServletRequest request, String op,
			GoodsPrice goodsPrice, SysUser sysUser, SysUnits sysUnits);

	/**
	 * 获取关联商品
	 * 
	 * @param goodsId
	 * @param zoneCode
	 * @return
	 */
	public Map<String, Object> getGoodsRelation(long goodsId, String zoneCode);

	/**
	 * 获取赠品
	 * 
	 * @param goodsId
	 * @param zoneCode
	 * @return
	 */
	public Map<String, Object> getGoodsGift(long goodsId, String zoneCode);

	/**
	 * 获取整箱、单品关联
	 * 
	 * @param goodsId
	 * @param zoneCode
	 * @return
	 */
	public Map<String, Object> getGoodsPriceRt(long goodsId, String zoneCode);

	/**
	 * 保存操作
	 * 
	 * @param request
	 * @param op
	 *            add：添加 modify：修改
	 * @param goodsPrice
	 */
	@Transactional(rollbackFor = Exception.class)
	public void savePrice(HttpServletRequest request, String op,
			GoodsPrice goodsPrice, SysUser sysUser, SysUnits sysUnits);

	/**
	 * 批量添加
	 * @param userId
	 * @param unitId
	 * @param dataIds
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public void saveBatch(SysUser sysUser,SysUnits sysUnits, String dataIds);




}
