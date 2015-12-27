package com.h2y.bmg.entity;

import com.h2y.object.BaseObject;

/**
 * 类描述：仓库商品详细数量 作者：侯飞龙 时间：2015年1月31日下午2:34:12 邮件：1162040314@qq.com
 */
public class StorehouseGoodsInfo extends BaseObject {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	public static final String key = "keyStorehouseGoodsInfo";
	private long id;
	private long storehouseId;
	private long goodsId;
	private long goodsPriceId;
	private int goodsCount;
	private int virtualCount;
	private long shopId;

	public StorehouseGoodsInfo() {
		super();
	}

	public StorehouseGoodsInfo(long id) {
		super();
		this.id = id;
	}

	public StorehouseGoodsInfo(long id, long storehouseId, long goodsId,
			long goodsPriceId, int goodsCount, int virtualCount, long shopId) {
		super();
		this.id = id;
		this.storehouseId = storehouseId;
		this.goodsId = goodsId;
		this.goodsPriceId = goodsPriceId;
		this.goodsCount = goodsCount;
		this.virtualCount = virtualCount;
		this.shopId = shopId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStorehouseId() {
		return storehouseId;
	}

	public void setStorehouseId(long storehouseId) {
		this.storehouseId = storehouseId;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public long getGoodsPriceId() {
		return goodsPriceId;
	}

	public void setGoodsPriceId(long goodsPriceId) {
		this.goodsPriceId = goodsPriceId;
	}

	public int getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}

	public int getVirtualCount() {
		return virtualCount;
	}

	public void setVirtualCount(int virtualCount) {
		this.virtualCount = virtualCount;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public String toString() {
		return "id:" + id + "\t" + "storehouseId:" + storehouseId + "\t"
				+ "goodsId:" + goodsId + "\t" + "goodsPriceId:" + goodsPriceId
				+ "\t" + "goodsCount:" + goodsCount + "\t" + "virtualCount:"
				+ virtualCount + "\t" + "shopId:" + shopId;
	}
}