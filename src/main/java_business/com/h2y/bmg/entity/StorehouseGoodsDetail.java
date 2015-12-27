package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 类描述：仓库出入库历史 作者：侯飞龙 时间：2015年1月31日下午2:32:25 邮件：1162040314@qq.com
 */
public class StorehouseGoodsDetail extends BaseObject {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	public static final String key = "keyStorehouseGoodsDetail";
	private long id;
	private long storehouseId;
	private long goodsPriceId;
	private int goodsPriceVersion;
	private long goodsId;
	private String goodsLocation;
	private int goodsCount;
	private int type;
	private int status;
	private Date createDate;
	private long createUserId;
	private String memo;

	public StorehouseGoodsDetail() {
		super();
	}

	public StorehouseGoodsDetail(long id) {
		super();
		this.id = id;
	}

	public StorehouseGoodsDetail(long id, long storehouseId, long goodsPriceId,
			int goodsPriceVersion, long goodsId, String goodsLocation,
			int goodsCount, int type, int status, Date createDate,
			long createUserId, String memo) {
		super();
		this.id = id;
		this.storehouseId = storehouseId;
		this.goodsPriceId = goodsPriceId;
		this.goodsPriceVersion = goodsPriceVersion;
		this.goodsId = goodsId;
		this.goodsLocation = goodsLocation;
		this.goodsCount = goodsCount;
		this.type = type;
		this.status = status;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.memo = memo;
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

	public long getGoodsPriceId() {
		return goodsPriceId;
	}

	public void setGoodsPriceId(long goodsPriceId) {
		this.goodsPriceId = goodsPriceId;
	}

	public int getGoodsPriceVersion() {
		return goodsPriceVersion;
	}

	public void setGoodsPriceVersion(int goodsPriceVersion) {
		this.goodsPriceVersion = goodsPriceVersion;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsLocation() {
		return goodsLocation;
	}

	public void setGoodsLocation(String goodsLocation) {
		this.goodsLocation = goodsLocation;
	}

	public int getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String toString() {
		return "id:" + id + "\t" + "storehouseId:" + storehouseId + "\t"
				+ "goodsPriceId:" + goodsPriceId + "\t" + "goodsPriceVersion:"
				+ goodsPriceVersion + "\t" + "goodsId:" + goodsId + "\t"
				+ "goodsLocation:" + goodsLocation + "\t" + "goodsCount:"
				+ goodsCount + "\t" + "type:" + type + "\t" + "status:"
				+ status + "\t" + "createDate:" + createDate + "\t"
				+ "createUserId:" + createUserId + "\t" + "memo:" + memo;
	}
}