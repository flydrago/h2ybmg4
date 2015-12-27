package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 类描述：标签详细 作者：侯飞龙 时间：2015年1月26日上午11:37:03 邮件：1162040314@qq.com
 */
public class GoodsMarkRt extends BaseObject {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	public static final String key = "keyGoodsMarkRt";
	private long id;
	private long goodsId;
	private String typeCode;
	private long markId;
	private long markInfoId;
	private int version;
	private Date createDate;
	private long userId;

	public GoodsMarkRt() {
		super();
	}

	public GoodsMarkRt(long id) {
		super();
		this.id = id;
	}

	public GoodsMarkRt(long id, long goodsId, String typeCode, long markId,
			long markInfoId, int version, Date createDate, long userId) {
		super();
		this.id = id;
		this.goodsId = goodsId;
		this.typeCode = typeCode;
		this.markId = markId;
		this.markInfoId = markInfoId;
		this.version = version;
		this.createDate = createDate;
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

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public long getMarkId() {
		return markId;
	}

	public void setMarkId(long markId) {
		this.markId = markId;
	}

	public long getMarkInfoId() {
		return markInfoId;
	}

	public void setMarkInfoId(long markInfoId) {
		this.markInfoId = markInfoId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String toString() {
		return "id:" + id + "\t" + "goodsId:" + goodsId + "\t" + "typeCode:"
				+ typeCode + "\t" + "markId:" + markId + "\t" + "markInfoId:"
				+ markInfoId + "\t" + "version:" + version + "\t"
				+ "createDate:" + createDate + "\t" + "userId:" + userId;
	}
}