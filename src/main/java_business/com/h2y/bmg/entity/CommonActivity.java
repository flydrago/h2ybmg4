package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 类描述：活动   
 * 作者：侯飞龙
 * 时间：2015年2月6日下午3:27:08
 * 邮件：1162040314@qq.com
 */
public class CommonActivity extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	public static final String key = "keyCommonActivity";
	private long id;
	private long unitId;
	private long userId;
	private String title;
	private String memo;
	private int dataType;
	private String activityType;
	private int ord;
	private Date createDate;
	private int status;
	private int isAllData;
	private int isSpike;
	private Date startDate;
	private Date endDate;
	private Double fixedPrice;

	public CommonActivity(){
		super();
	}

	public CommonActivity(long id){
		super();
		this.id = id;
	}

	public CommonActivity(long id,long unitId,long userId,String title,String memo,int dataType,String activityType,int ord,Date createDate,int status,int isAllData,int isSpike,Date startDate,Date endDate,Double fixedPrice){
		super();
		this.id = id;
		this.unitId = unitId;
		this.userId = userId;
		this.title = title;
		this.memo = memo;
		this.dataType = dataType;
		this.activityType = activityType;
		this.ord = ord;
		this.createDate = createDate;
		this.status = status;
		this.isAllData = isAllData;
		this.isSpike = isSpike;
		this.startDate = startDate;
		this.endDate = endDate;
		this.fixedPrice = fixedPrice;
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


	public long getUserId(){
		return userId;
	}

	public void setUserId(long userId){
		this.userId = userId;
	}


	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}


	public String getMemo(){
		return memo;
	}

	public void setMemo(String memo){
		this.memo = memo;
	}


	public int getDataType(){
		return dataType;
	}

	public void setDataType(int dataType){
		this.dataType = dataType;
	}


	public String getActivityType(){
		return activityType;
	}

	public void setActivityType(String activityType){
		this.activityType = activityType;
	}


	public int getOrd(){
		return ord;
	}

	public void setOrd(int ord){
		this.ord = ord;
	}


	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}


	public int getStatus(){
		return status;
	}

	public void setStatus(int status){
		this.status = status;
	}


	public int getIsAllData(){
		return isAllData;
	}

	public void setIsAllData(int isAllData){
		this.isAllData = isAllData;
	}


	public int getIsSpike(){
		return isSpike;
	}

	public void setIsSpike(int isSpike){
		this.isSpike = isSpike;
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


	public Double getFixedPrice() {
		return fixedPrice;
	}

	public void setFixedPrice(Double fixedPrice) {
		this.fixedPrice = fixedPrice;
	}

	public String toString(){
		return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"userId:"+userId+"\t"+"title:"+title+"\t"+"memo:"+memo+"\t"+"dataType:"+dataType+"\t"+"activityType:"+activityType+"\t"+"ord:"+ord+"\t"+"createDate:"+createDate+"\t"+"status:"+status+"\t"+"isAllData:"+isAllData+"\t"+"isSpike:"+isSpike+"\t"+"startDate:"+startDate+"\t"+"endDate:"+endDate+"\t"+"fixedPrice:"+fixedPrice;
	}
}