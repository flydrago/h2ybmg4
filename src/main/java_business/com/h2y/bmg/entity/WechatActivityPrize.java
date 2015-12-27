package com.h2y.bmg.entity;

import com.h2y.object.BaseObject;


/**
 * 类描述：微活动奖项   
 * 作者：侯飞龙
 * 时间：2014年12月17日上午11:36:39
 * 邮件：1162040314@qq.com
 */
public class WechatActivityPrize extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyWechatActivityPrize";
    private long id;
    private long unitId;
    private long activityId;
    private String prizeName;
    private long prizeCount;
    private long prizeNumber;
    private Double prizeRate;
    private String prizeLevel;
    private String prizeType;
    private long goodsPriceId;
    private int ord;
    private String reverse1;
    private String reverse2;

	public WechatActivityPrize(){
		super();
	}

	public WechatActivityPrize(long id){
		super();
		this.id = id;
	}

	public WechatActivityPrize(long id,long unitId,long activityId,String prizeName,long prizeCount,long prizeNumber,Double prizeRate,String prizeLevel,String prizeType,long goodsPriceId,int ord,String reverse1,String reverse2){
		super();
		this.id = id;
		this.unitId = unitId;
		this.activityId = activityId;
		this.prizeName = prizeName;
		this.prizeCount = prizeCount;
		this.prizeNumber = prizeNumber;
		this.prizeRate = prizeRate;
		this.prizeLevel = prizeLevel;
		this.prizeType = prizeType;
		this.goodsPriceId = goodsPriceId;
		this.ord = ord;
		this.reverse1 = reverse1;
		this.reverse2 = reverse2;
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


    public String getPrizeName(){
      return prizeName;
    }
    
    public void setPrizeName(String prizeName){
      this.prizeName = prizeName;
    }


    public long getPrizeCount(){
      return prizeCount;
    }
    
    public void setPrizeCount(long prizeCount){
      this.prizeCount = prizeCount;
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


    public int getOrd(){
      return ord;
    }
    
    public void setOrd(int ord){
      this.ord = ord;
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

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"activityId:"+activityId+"\t"+"prizeName:"+prizeName+"\t"+"prizeCount:"+prizeCount+"\t"+"prizeNumber:"+prizeNumber+"\t"+"prizeRate:"+prizeRate+"\t"+"prizeLevel:"+prizeLevel+"\t"+"prizeType:"+prizeType+"\t"+"goodsPriceId:"+goodsPriceId+"\t"+"ord:"+ord+"\t"+"reverse1:"+reverse1+"\t"+"reverse2:"+reverse2;
    }
}