package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 类描述：活动商品关联   
 * 作者：侯飞龙
 * 时间：2015年2月6日下午4:15:36
 * 邮件：1162040314@qq.com
 */
public class CommonActivityGoodsRt extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	public static final String key = "keyCommonActivityGoodsRt";
	private long id;
	private long unitId;
	private long dataId;
	private int dataType;
	private long goodsId;
	private long goodsPriceId;
	private Double activityPrice;
	private Date createDate;
	private Date startDate;
	private Date endDate;
	private int limitNumber;
	private int sellNumber;
	private int rewardType;
	private int rewardNumber;
	private String memo;
	private int goodsLevel;
	private long ord;
	private int status;
	private int limitSellNumber;

	public CommonActivityGoodsRt(){
		super();
	}

	public CommonActivityGoodsRt(long id){
		super();
		this.id = id;
	}

	public CommonActivityGoodsRt(long id,long unitId,long dataId,int dataType,long goodsId,long goodsPriceId,Double activityPrice,Date createDate,Date startDate,Date endDate,int limitNumber,int sellNumber,int rewardType,int rewardNumber,String memo,int goodsLevel,long ord,int status,int limitSellNumber){
		super();
		this.id = id;
		this.unitId = unitId;
		this.dataId = dataId;
		this.dataType = dataType;
		this.goodsId = goodsId;
		this.goodsPriceId = goodsPriceId;
		this.activityPrice = activityPrice;
		this.createDate = createDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.limitNumber = limitNumber;
		this.sellNumber = sellNumber;
		this.rewardType = rewardType;
		this.rewardNumber = rewardNumber;
		this.memo = memo;
		this.goodsLevel = goodsLevel;
		this.ord = ord;
		this.status = status;
		this.limitSellNumber = limitSellNumber;
	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public long getUnitId(){
		return unitId;
	}

	public void setUnitId(long unitId){
		this.unitId = unitId;
	}


	public long getDataId(){
		return dataId;
	}

	public void setDataId(long dataId){
		this.dataId = dataId;
	}


	public int getDataType(){
		return dataType;
	}

	public void setDataType(int dataType){
		this.dataType = dataType;
	}


	public long getGoodsId(){
		return goodsId;
	}

	public void setGoodsId(long goodsId){
		this.goodsId = goodsId;
	}


	public long getGoodsPriceId(){
		return goodsPriceId;
	}

	public void setGoodsPriceId(long goodsPriceId){
		this.goodsPriceId = goodsPriceId;
	}


	public Double getActivityPrice(){
		return activityPrice;
	}

	public void setActivityPrice(Double activityPrice){
		this.activityPrice = activityPrice;
	}


	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}


	public Date getStartDate(){
		return startDate;
	}

	public void setStartDate(Date startDate){
		this.startDate = startDate;
	}


	public Date getEndDate(){
		return endDate;
	}

	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}


	public int getLimitNumber(){
		return limitNumber;
	}

	public void setLimitNumber(int limitNumber){
		this.limitNumber = limitNumber;
	}


	public int getSellNumber(){
		return sellNumber;
	}

	public void setSellNumber(int sellNumber){
		this.sellNumber = sellNumber;
	}


	public int getRewardType(){
		return rewardType;
	}

	public void setRewardType(int rewardType){
		this.rewardType = rewardType;
	}


	public int getRewardNumber(){
		return rewardNumber;
	}

	public void setRewardNumber(int rewardNumber){
		this.rewardNumber = rewardNumber;
	}


	public String getMemo(){
		return memo;
	}

	public void setMemo(String memo){
		this.memo = memo;
	}


	public int getGoodsLevel(){
		return goodsLevel;
	}

	public void setGoodsLevel(int goodsLevel){
		this.goodsLevel = goodsLevel;
	}


	public long getOrd(){
		return ord;
	}

	public void setOrd(long ord){
		this.ord = ord;
	}


	public int getStatus(){
		return status;
	}

	public void setStatus(int status){
		this.status = status;
	}



	public int getLimitSellNumber() {
		return limitSellNumber;
	}

	public void setLimitSellNumber(int limitSellNumber) {
		this.limitSellNumber = limitSellNumber;
	}

	public String toString(){
		return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"dataId:"+dataId+"\t"+"dataType:"+dataType+"\t"+"goodsId:"+goodsId+"\t"+"goodsPriceId:"+goodsPriceId+"\t"+"activityPrice:"+activityPrice+"\t"+"createDate:"+createDate+"\t"+"startDate:"+startDate+"\t"+"endDate:"+endDate+"\t"+"limitNumber:"+limitNumber+"\t"+"sellNumber:"+sellNumber+"\t"+"rewardType:"+rewardType+"\t"+"rewardNumber:"+rewardNumber+"\t"+"memo:"+memo+"\t"+"goodsLevel:"+goodsLevel+"\t"+"ord:"+ord+"\t"+"status:"+status+"\t"+"limitSellNumber:"+limitSellNumber;
	}
}