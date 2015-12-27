package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 类描述：商品类型 作者：侯飞龙 时间：2015年1月23日下午2:41:42 邮件：1162040314@qq.com
 */
public class GoodsType extends BaseObject {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	public static final String key = "keyGoodsType";
	private long id;
	private long parentId;
	private String typeCode;
	private String typeName;
	private int status;
	private long ord;
	private Date createDate;
	private String memo;
	private long userId;

	public GoodsType() {
		super();
	}

	public GoodsType(long id) {
		super();
		this.id = id;
	}

	public GoodsType(long id, long parentId, String typeCode, String typeName,
			int status, long ord, Date createDate, String memo, long userId) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.status = status;
		this.ord = ord;
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

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getOrd() {
		return ord;
	}

	public void setOrd(long ord) {
		this.ord = ord;
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
		return "id:" + id + "\t" + "parentId:" + parentId + "\t" + "typeCode:"
				+ typeCode + "\t" + "typeName:" + typeName + "\t" + "status:"
				+ status + "\t" + "ord:" + ord + "\t" + "createDate:"
				+ createDate + "\t" + "memo:" + memo + "\t" + "userId:"
				+ userId;
	}
}