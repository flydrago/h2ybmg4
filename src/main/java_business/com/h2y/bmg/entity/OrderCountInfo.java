package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;


/**
 * OrderCountInfo Model create
 * @author hwttnet
 * version:1.2
 * time:2015-08-06
 * email:info@hwttnet.com
 */

public class OrderCountInfo extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyOrderCountInfo";
    private long id;
    private long countId;
    private Date createDate;
    private Date updateDate;
    private int orderCount;
    private Double realAmount;
    private Double goodsAmount;
    private Double coinAmount;
    private Double goodsTransportAmount;
    private Double couponsAmount;
    private Double double1;
    private Double double2;
    private String str1;
    private String str2;
    private int infoType;
    private Double avgAmount;//非数据库字段

	public OrderCountInfo(){
		super();
	}

	public OrderCountInfo(long id){
		super();
		this.id = id;
	}

	public OrderCountInfo(long id,long countId,Date createDate,Date updateDate,int orderCount,Double realAmount,Double goodsAmount,Double coinAmount,Double goodsTransportAmount,Double couponsAmount,Double double1,Double double2,String str1,String str2,int infoType){
		super();
		this.id = id;
		this.countId = countId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.orderCount = orderCount;
		this.realAmount = realAmount;
		this.goodsAmount = goodsAmount;
		this.coinAmount = coinAmount;
		this.goodsTransportAmount = goodsTransportAmount;
		this.couponsAmount = couponsAmount;
		this.double1 = double1;
		this.double2 = double2;
		this.str1 = str1;
		this.str2 = str2;
		this.infoType = infoType;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getCountId(){
      return countId;
    }
    
    public void setCountId(long countId){
      this.countId = countId;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public Date getUpdateDate(){
      return updateDate;
    }
    
    public void setUpdateDate(Date updateDate){
      this.updateDate = updateDate;
    }


    public int getOrderCount(){
      return orderCount;
    }
    
    public void setOrderCount(int orderCount){
      this.orderCount = orderCount;
    }


    public Double getRealAmount(){
      return realAmount;
    }
    
    public void setRealAmount(Double realAmount){
      this.realAmount = realAmount;
    }


    public Double getGoodsAmount(){
      return goodsAmount;
    }
    
    public void setGoodsAmount(Double goodsAmount){
      this.goodsAmount = goodsAmount;
    }


    public Double getCoinAmount(){
      return coinAmount;
    }
    
    public void setCoinAmount(Double coinAmount){
      this.coinAmount = coinAmount;
    }


    public Double getGoodsTransportAmount(){
      return goodsTransportAmount;
    }
    
    public void setGoodsTransportAmount(Double goodsTransportAmount){
      this.goodsTransportAmount = goodsTransportAmount;
    }


    public Double getCouponsAmount(){
      return couponsAmount;
    }
    
    public void setCouponsAmount(Double couponsAmount){
      this.couponsAmount = couponsAmount;
    }


    public Double getDouble1(){
      return double1;
    }
    
    public void setDouble1(Double double1){
      this.double1 = double1;
    }


    public Double getDouble2(){
      return double2;
    }
    
    public void setDouble2(Double double2){
      this.double2 = double2;
    }


    public String getStr1(){
      return str1;
    }
    
    public void setStr1(String str1){
      this.str1 = str1;
    }


    public String getStr2(){
      return str2;
    }
    
    public void setStr2(String str2){
      this.str2 = str2;
    }


    public int getInfoType(){
      return infoType;
    }
    
    public void setInfoType(int infoType){
      this.infoType = infoType;
    }

    public Double getAvgAmount() {
		return avgAmount;
	}

	public void setAvgAmount(Double avgAmount) {
		this.avgAmount = avgAmount;
	}

	public String toString(){
	return "id:"+id+"\t"+"countId:"+countId+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"orderCount:"+orderCount+"\t"+"realAmount:"+realAmount+"\t"+"goodsAmount:"+goodsAmount+"\t"+"coinAmount:"+coinAmount+"\t"+"goodsTransportAmount:"+goodsTransportAmount+"\t"+"couponsAmount:"+couponsAmount+"\t"+"double1:"+double1+"\t"+"double2:"+double2+"\t"+"str1:"+str1+"\t"+"str2:"+str2+"\t"+"infoType:"+infoType;
    }
}