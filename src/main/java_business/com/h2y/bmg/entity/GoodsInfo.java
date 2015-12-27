package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 类描述： 商品详细 作者：侯飞龙 时间：2015年1月26日下午2:44:24 邮件：1162040314@qq.com
 */
public class GoodsInfo extends BaseObject {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	public static final String key = "keyGoodsInfo";
	private long id;
	private long goodsId;
	private String introduce;
	private String specParam;
	private int version;
	private int dataType;
	private Date createDate;
	private String memo;
	private long userId;

	public GoodsInfo() {
		super();
	}

	public GoodsInfo(long id) {
		super();
		this.id = id;
	}

	public GoodsInfo(long id, long goodsId, String introduce, String specParam,
			int version, int dataType, Date createDate, String memo, long userId) {
		super();
		this.id = id;
		this.goodsId = goodsId;
		this.introduce = introduce;
		this.specParam = specParam;
		this.version = version;
		this.dataType = dataType;
		this.createDate = createDate;
		this.memo = memo;
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getSpecParam() {
		return specParam;
	}

	public void setSpecParam(String specParam) {
		this.specParam = specParam;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String toString() {
		return "id:" + id + "\t" + "goodsId:" + goodsId + "\t" + "introduce:"
				+ introduce + "\t" + "specParam:" + specParam + "\t"
				+ "version:" + version + "\t" + "dataType:" + dataType + "\t"
				+ "createDate:" + createDate + "\t" + "memo:" + memo + "\t"
				+ "userId:" + userId;
	}
}