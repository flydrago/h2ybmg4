package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 项目名称：h2yorsos  
 * 类名称：OrderCount  
 * 类描述：订单统计  
 * 创建人：侯飞龙  
 * 创建时间：2015年8月6日 上午10:35:59  
 * 修改人：侯飞龙
 * 修改时间：2015年8月6日 上午10:35:59  
 * 修改备注：  
 * @version
 */
public class OrderCount extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyOrderCount";
    private long id;
    private long unitId;
    private long unitType;
    private String unitName;
    private String unitShortName;
    private String zoneCode;
    private Date createDate;
    private Date updateDate;
    private Date orderDay;
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

	public OrderCount(){
		super();
	}

	public OrderCount(long id){
		super();
		this.id = id;
	}

	public OrderCount(long id,long unitId,long unitType,String unitName,String unitShortName,String zoneCode,Date createDate,Date updateDate,Date orderDay,int orderCount,Double realAmount,Double goodsAmount,Double coinAmount,Double goodsTransportAmount,Double couponsAmount,Double double1,Double double2,String str1,String str2){
		super();
		this.id = id;
		this.unitId = unitId;
		this.unitType = unitType;
		this.unitName = unitName;
		this.unitShortName = unitShortName;
		this.zoneCode = zoneCode;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.orderDay = orderDay;
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


    public long getUnitType(){
      return unitType;
    }
    
    public void setUnitType(long unitType){
      this.unitType = unitType;
    }


    public String getUnitName(){
      return unitName;
    }
    
    public void setUnitName(String unitName){
      this.unitName = unitName;
    }


    public String getUnitShortName(){
      return unitShortName;
    }
    
    public void setUnitShortName(String unitShortName){
      this.unitShortName = unitShortName;
    }


    public String getZoneCode(){
      return zoneCode;
    }
    
    public void setZoneCode(String zoneCode){
      this.zoneCode = zoneCode;
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


    public Date getOrderDay(){
      return orderDay;
    }
    
    public void setOrderDay(Date orderDay){
      this.orderDay = orderDay;
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

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"unitType:"+unitType+"\t"+"unitName:"+unitName+"\t"+"unitShortName:"+unitShortName+"\t"+"zoneCode:"+zoneCode+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"orderDay:"+orderDay+"\t"+"orderCount:"+orderCount+"\t"+"realAmount:"+realAmount+"\t"+"goodsAmount:"+goodsAmount+"\t"+"coinAmount:"+coinAmount+"\t"+"goodsTransportAmount:"+goodsTransportAmount+"\t"+"couponsAmount:"+couponsAmount+"\t"+"double1:"+double1+"\t"+"double2:"+double2+"\t"+"str1:"+str1+"\t"+"str2:"+str2;
    }
}