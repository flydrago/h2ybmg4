package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 类描述：活动参与历史（会员抽奖历史）   
 * 作者：侯飞龙
 * 时间：2014年12月19日上午10:43:59
 * 邮件：1162040314@qq.com
 */
public class WechatActivityHis extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyWechatActivityHis";
    private long id;
    private long unitId;
    private long activityId;
    private long prizeId;
    private long memberId;
    private String openId;
    private Date createDate;
    private Date exchangeDate;
    private String prizerMobile;
    private String prizerName;
    private String prizerAddress;
    private int ifExchange;
    private String reverse1;
    private String reverse2;
    private String prizeName;
    private long prizeNumber;
    private Double prizeRate;
    private String prizeLevel;
    private String prizeType;
    private long goodsPriceId;
    private int goodsPriceVersion;

	public WechatActivityHis(){
		super();
	}

	public WechatActivityHis(long id){
		super();
		this.id = id;
	}

	public WechatActivityHis(long id,long unitId,long activityId,long prizeId,long memberId,String openId,Date createDate,Date exchangeDate,String prizerMobile,String prizerName,String prizerAddress,int ifExchange,String reverse1,String reverse2,String prizeName,long prizeNumber,Double prizeRate,String prizeLevel,String prizeType,long goodsPriceId,int goodsPriceVersion){
		super();
		this.id = id;
		this.unitId = unitId;
		this.activityId = activityId;
		this.prizeId = prizeId;
		this.memberId = memberId;
		this.openId = openId;
		this.createDate = createDate;
		this.exchangeDate = exchangeDate;
		this.prizerMobile = prizerMobile;
		this.prizerName = prizerName;
		this.prizerAddress = prizerAddress;
		this.ifExchange = ifExchange;
		this.reverse1 = reverse1;
		this.reverse2 = reverse2;
		this.prizeName = prizeName;
		this.prizeNumber = prizeNumber;
		this.prizeRate = prizeRate;
		this.prizeLevel = prizeLevel;
		this.prizeType = prizeType;
		this.goodsPriceId = goodsPriceId;
		this.goodsPriceVersion = goodsPriceVersion;
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


    public long getActivityId(){
      return activityId;
    }
    
    public void setActivityId(long activityId){
      this.activityId = activityId;
    }


    public long getPrizeId(){
      return prizeId;
    }
    
    public void setPrizeId(long prizeId){
      this.prizeId = prizeId;
    }


    public long getMemberId(){
      return memberId;
    }
    
    public void setMemberId(long memberId){
      this.memberId = memberId;
    }


    public String getOpenId(){
      return openId;
    }
    
    public void setOpenId(String openId){
      this.openId = openId;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public Date getExchangeDate(){
      return exchangeDate;
    }
    
    public void setExchangeDate(Date exchangeDate){
      this.exchangeDate = exchangeDate;
    }


    public String getPrizerMobile(){
      return prizerMobile;
    }
    
    public void setPrizerMobile(String prizerMobile){
      this.prizerMobile = prizerMobile;
    }


    public String getPrizerName(){
      return prizerName;
    }
    
    public void setPrizerName(String prizerName){
      this.prizerName = prizerName;
    }


    public String getPrizerAddress(){
      return prizerAddress;
    }
    
    public void setPrizerAddress(String prizerAddress){
      this.prizerAddress = prizerAddress;
    }


    public int getIfExchange(){
      return ifExchange;
    }
    
    public void setIfExchange(int ifExchange){
      this.ifExchange = ifExchange;
    }


    public String getReverse1(){
      return reverse1;
    }
    
    public void setReverse1(String reverse1){
      this.reverse1 = reverse1;
    }


    public String getReverse2(){
      return reverse2;
    }
    
    public void setReverse2(String reverse2){
      this.reverse2 = reverse2;
    }


    public String getPrizeName(){
      return prizeName;
    }
    
    public void setPrizeName(String prizeName){
      this.prizeName = prizeName;
    }


    public long getPrizeNumber(){
      return prizeNumber;
    }
    
    public void setPrizeNumber(long prizeNumber){
      this.prizeNumber = prizeNumber;
    }


    public Double getPrizeRate(){
      return prizeRate;
    }
    
    public void setPrizeRate(Double prizeRate){
      this.prizeRate = prizeRate;
    }


    public String getPrizeLevel(){
      return prizeLevel;
    }
    
    public void setPrizeLevel(String prizeLevel){
      this.prizeLevel = prizeLevel;
    }


    public String getPrizeType(){
      return prizeType;
    }
    
    public void setPrizeType(String prizeType){
      this.prizeType = prizeType;
    }


    public long getGoodsPriceId(){
      return goodsPriceId;
    }
    
    public void setGoodsPriceId(long goodsPriceId){
      this.goodsPriceId = goodsPriceId;
    }


    public int getGoodsPriceVersion(){
      return goodsPriceVersion;
    }
    
    public void setGoodsPriceVersion(int goodsPriceVersion){
      this.goodsPriceVersion = goodsPriceVersion;
    }

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"activityId:"+activityId+"\t"+"prizeId:"+prizeId+"\t"+"memberId:"+memberId+"\t"+"openId:"+openId+"\t"+"createDate:"+createDate+"\t"+"exchangeDate:"+exchangeDate+"\t"+"prizerMobile:"+prizerMobile+"\t"+"prizerName:"+prizerName+"\t"+"prizerAddress:"+prizerAddress+"\t"+"ifExchange:"+ifExchange+"\t"+"reverse1:"+reverse1+"\t"+"reverse2:"+reverse2+"\t"+"prizeName:"+prizeName+"\t"+"prizeNumber:"+prizeNumber+"\t"+"prizeRate:"+prizeRate+"\t"+"prizeLevel:"+prizeLevel+"\t"+"prizeType:"+prizeType+"\t"+"goodsPriceId:"+goodsPriceId+"\t"+"goodsPriceVersion:"+goodsPriceVersion;
    }
}