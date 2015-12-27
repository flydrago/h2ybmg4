package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.DataGoodsInfo;
import com.h2y.bmg.entity.GoodsPrice;

/**
 * 类描述：商品定价数据库操作接口 作者：侯飞龙 时间：2015年1月28日上午11:57:47 邮件：1162040314@qq.com
 */
@Component
public interface IGoodsPriceDao {

	/**
	 * add
	 */
	public int add(GoodsPrice goodsPrice);

	/**
	 * update
	 */
	public int update(GoodsPrice goodsPrice);

	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * 
	 * @return
	 */
	public GoodsPrice get(long id);

	/**
	 * 得到商品定价信息分页列表
	 * 
	 * @param map
	 *            {unitId:单位Id, ifQuery:查询条件, sortname:排序字段, sortorder:排序方式,
	 *            page:页码, pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String, Object>> getListMap(Map<String, Object> map);

	/**
	 * 获取旗舰店商品列表
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getListPriceMap(Map<String, Object> map);
	/**
	 * 获取旗舰店商品列表总数
	 * 
	 * @param map
	 * @return long
	 */
	public long getListPriceRows(Map<String, Object> map);

	/**
	 * 得到商品定价总数量
	 * 
	 * @param map
	 *            {unitId:单位Id, ifQuery:查询条件}
	 * @return
	 */
	public long getListRows(Map<String, Object> map);

	/**
	 * 改变定价商品的状态
	 * 
	 * @param map
	 *            {status:状态 0：上架、1：下架、2：删除, ids:要改变状态的id集合}
	 */
	public int updateStatusByIds(Map<String, Object> map);
	
	
	/**
	 * 批量删除原关联关系
	 * @param map
	 * @return
	 */
	public int updateGoodsPriceRtByIds(Map<String, Object> map);
	

	/**
	 * 改变定价商品的平台审核状态
	 * 
	 * @param map
	 *            {status:状态 0：上架、1：下架, ids:要改变状态的id集合}
	 */
	public void updateGoodsStatusByIds(Map<String, Object> map);

	/**
	 * 商品表数据修改后自动更新 代理表状态 0代理中 -1已过期
	 * 
	 * @param map
	 */
	public void updateEditStatus(long goodsId);

	/**
	 * 更具商品Id，得到当前单位添加此商品的数量（用于判断避免重复添加）
	 * 
	 * @param map
	 *            {unitId:单位Id, goodsId:商品Id, id:定价商品主键Id null时不做过滤判断}
	 * @return
	 */
	public long getRowsByGoodsId(Map<String, Object> map);

	// 采购商浏览商品列表
	public List<Map<String, Object>> getBuyGridData(Map<String, Object> map);

	public List<Map<String, Object>> getBuyGridDataSize(Map<String, Object> map);

	/**
	 * 根据商品Id，得到当前单位下面商品的定价信息
	 * 
	 * @param map
	 *            {goodsId:商品Id, unitId:单位Id}
	 * @return
	 */
	public GoodsPrice getByGoodsId(Map<String, Object> map);

	/**
	 * 得到定价商品列表
	 * 
	 * @param map
	 *            {unitId:单位Id, typeCode:类型编码（模糊查询 null时不做判断）, number:商品编码（模糊查询
	 *            null时不做判断）, name:商品名称（模糊查询 null时不做判断）, sortname:排序字段,
	 *            sortorder:排序方式, page:页码, pagesize:页显示最大记录数}
	 * @return
	 */
	public List<Map<String, Object>> getPriceSelectListMap(
			Map<String, Object> map);

	/**
	 * 得到定价商品列表
	 * 
	 * @param map
	 *            {unitId:单位Id, typeCode:类型编码（模糊查询 null时不做判断）, number:商品编码（模糊查询
	 *            null时不做判断）, name:商品名称（模糊查询 null时不做判断）}
	 * @return
	 */
	public List<Map<String, Object>> getPriceSelectListRows(
			Map<String, Object> map);

	/**
	 * 得到选择窗口商品列表
	 * 
	 * @param map
	 *            key1:unitId value1:单位Id key2:typeId value2:商品类型Id
	 *            key3:searchName value3:按商品名称模糊查询
	 * @return
	 */
	public List<Map<String, Object>> getSelectDialogGoodsList(
			Map<String, Object> map);

	/**
	 * 商品赠品、关联
	 * 
	 * @param dataGoodsInfo
	 */
	public void addDataGoodsInfo(DataGoodsInfo dataGoodsInfo);

	/**
	 * 删除商品的赠品信息
	 */
	public void delateGoodaGift(long goodsId);

	/**
	 * 删除商品关联
	 * @param goodsId
	 */
	public void deleteRelation(long goodsId);

	/**
	 * 获取关联商品（赠品或关联商品）
	 * 
	 * @param map
	 *            {dataType:类型（1：赠品、2：关联商品）, goodsId:商品Id, zoneCode:区域编码}
	 * @return
	 */
	public List<Map<String, Object>> getRelationGoods(Map<String, Object> map);


	/**
	 * 判断该单品是否已被关联
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getGoodsPriceRt(Map<String, Object> map);

	/**
	 * 判断该箱是否已关联商品
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getGoodsPriceRtByGoodsId(Map<String, Object> map);

	/**
	 * 删除原瓶箱关联 以确定新的关联关系
	 * @param map
	 */
	public void deleteGoodsPriceRt(Map<String, Object> map);

	/**
	 * 根据商品编号得到对应商品的数量（用于验证商品编号唯一）
	 * 
	 * @param map
	 *            {number:商品编号, id:商品Id null时不做过滤判断}
	 * @return
	 */
	public long getRowsByNumber(Map<String, Object> map);

	/**
	 * 进销存 获取对话框选择商品列表
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getSelectDialogGoodsListForJxc(Map<String, Object> map);

	/**
	 * 根据商品id，更新是否有赠品
	 * @param map
	 * {id:商品id，isGift:是否有赠品（0：否，1：是）}
	 */
	public void updateIsGiftByGoodsId(Map<String,Object> map);
	
	/**
	 * 根据商品id，更新是否有关联商品
	 * @param map
	 * {id:商品id，isRelation:是否有赠品（0：否，1：是）}
	 */
	public void updateIsRelationByGoodsId(Map<String,Object> map);
	
	
	/**
	 * 根据商品id，获取对应赠品和关联商品列表
	 */
	public List<Map<String,Object>> getDataGoodsListByGoodsId(long goodsId);
}