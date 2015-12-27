package com.h2y.bmg.entity;

import java.util.Date;

/**
 * ShoppingCart Model create
 * @author hwttnet
 * version:1.2
 * time:2014-11-14
 * email:info@hwttnet.com
 */

public class ShoppingCart{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyShoppingCart";
    private long id;
    private long unitsId;
    private long goodsId;
    private int goodsCount;
    private Double goodsAmount;
    private String ifWork;
    private String type;
    private Date createDate;
    private long createUserId;
    private String remark;

	public ShoppingCart(){
		super();
	}

	public ShoppingCart(long id){
		super();
		this.id = id;
	}

	 
  
    public ShoppingCart(long id, long unitsId, long goodsId, int goodsCount,
			Double goodsAmount, String ifWork, String type, Date createDate,
			long createUserId, String remark) {
		super();
		this.id = id;
		this.unitsId = unitsId;
		this.goodsId = goodsId;
		this.goodsCount = goodsCount;
		this.goodsAmount = goodsAmount;
		this.ifWork = ifWork;
		this.type = type;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.remark = remark;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUnitsId() {
		return unitsId;
	}

	public void setUnitsId(long unitsId) {
		this.unitsId = unitsId;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public int getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}

	public Double getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(Double goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public String getIfWork() {
		return ifWork;
	}

	public void setIfWork(String ifWork) {
		this.ifWork = ifWork;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	 
}