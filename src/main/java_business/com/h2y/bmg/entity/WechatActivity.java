package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 微活动
 * 作者：侯飞龙
 * 时间：2014年12月17日上午11:32:31
 * 邮件：1162040314@qq.com
 */
public class WechatActivity extends BaseObject{


	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyWechatActivity";
    private long id;
    private long unitId;
    private long userId;
    private String name;
    private String description;
    private String keyword;
    private String fileName;
    private String diskFileName;
    private String rootPath;
    private String relativePath;
    private long fileSize;
    private String fileSuffix;
    private Date createDate;
    private Date startDate;
    private Date endDate;
    private int prizeInfo;
    private int limitNumber;
    private int limitFlag;
    private int ifShareReward;
    private int rewardNumber;
    private int activityStatus;
    private int ifSetDate;
    private String activityType;
    private String reverse1;
    private String reverse2;
    private int useVal;

	public WechatActivity(){
		super();
	}

	public WechatActivity(long id){
		super();
		this.id = id;
	}

	public WechatActivity(long id,long unitId,long userId,String name,String description,String keyword,String fileName,String diskFileName,String rootPath,String relativePath,long fileSize,String fileSuffix,Date createDate,Date startDate,Date endDate,int prizeInfo,int limitNumber,int limitFlag,int ifShareReward,int rewardNumber,int activityStatus,int ifSetDate,String activityType,String reverse1,String reverse2,int useVal){
		super();
		this.id = id;
		this.unitId = unitId;
		this.userId = userId;
		this.name = name;
		this.description = description;
		this.keyword = keyword;
		this.fileName = fileName;
		this.diskFileName = diskFileName;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.fileSize = fileSize;
		this.fileSuffix = fileSuffix;
		this.createDate = createDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.prizeInfo = prizeInfo;
		this.limitNumber = limitNumber;
		this.limitFlag = limitFlag;
		this.ifShareReward = ifShareReward;
		this.rewardNumber = rewardNumber;
		this.activityStatus = activityStatus;
		this.ifSetDate = ifSetDate;
		this.activityType = activityType;
		this.reverse1 = reverse1;
		this.reverse2 = reverse2;
		this.useVal = useVal;
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


    public String getName(){
      return name;
    }
    
    public void setName(String name){
      this.name = name;
    }


    public String getDescription(){
      return description;
    }
    
    public void setDescription(String description){
      this.description = description;
    }


    public String getKeyword(){
      return keyword;
    }
    
    public void setKeyword(String keyword){
      this.keyword = keyword;
    }


    public String getFileName(){
      return fileName;
    }
    
    public void setFileName(String fileName){
      this.fileName = fileName;
    }


    public String getDiskFileName(){
      return diskFileName;
    }
    
    public void setDiskFileName(String diskFileName){
      this.diskFileName = diskFileName;
    }


    public String getRootPath(){
      return rootPath;
    }
    
    public void setRootPath(String rootPath){
      this.rootPath = rootPath;
    }


    public String getRelativePath(){
      return relativePath;
    }
    
    public void setRelativePath(String relativePath){
      this.relativePath = relativePath;
    }


    public long getFileSize(){
      return fileSize;
    }
    
    public void setFileSize(long fileSize){
      this.fileSize = fileSize;
    }


    public String getFileSuffix(){
      return fileSuffix;
    }
    
    public void setFileSuffix(String fileSuffix){
      this.fileSuffix = fileSuffix;
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


    public int getPrizeInfo(){
      return prizeInfo;
    }
    
    public void setPrizeInfo(int prizeInfo){
      this.prizeInfo = prizeInfo;
    }


    public int getLimitNumber(){
      return limitNumber;
    }
    
    public void setLimitNumber(int limitNumber){
      this.limitNumber = limitNumber;
    }


    public int getLimitFlag(){
      return limitFlag;
    }
    
    public void setLimitFlag(int limitFlag){
      this.limitFlag = limitFlag;
    }


    public int getIfShareReward(){
      return ifShareReward;
    }
    
    public void setIfShareReward(int ifShareReward){
      this.ifShareReward = ifShareReward;
    }


    public int getRewardNumber(){
      return rewardNumber;
    }
    
    public void setRewardNumber(int rewardNumber){
      this.rewardNumber = rewardNumber;
    }


    public int getActivityStatus(){
      return activityStatus;
    }
    
    public void setActivityStatus(int activityStatus){
      this.activityStatus = activityStatus;
    }


    public int getIfSetDate(){
      return ifSetDate;
    }
    
    public void setIfSetDate(int ifSetDate){
      this.ifSetDate = ifSetDate;
    }


    public String getActivityType(){
      return activityType;
    }
    
    public void setActivityType(String activityType){
      this.activityType = activityType;
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


    public int getUseVal(){
      return useVal;
    }
    
    public void setUseVal(int useVal){
      this.useVal = useVal;
    }

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"userId:"+userId+"\t"+"name:"+name+"\t"+"description:"+description+"\t"+"keyword:"+keyword+"\t"+"fileName:"+fileName+"\t"+"diskFileName:"+diskFileName+"\t"+"rootPath:"+rootPath+"\t"+"relativePath:"+relativePath+"\t"+"fileSize:"+fileSize+"\t"+"fileSuffix:"+fileSuffix+"\t"+"createDate:"+createDate+"\t"+"startDate:"+startDate+"\t"+"endDate:"+endDate+"\t"+"prizeInfo:"+prizeInfo+"\t"+"limitNumber:"+limitNumber+"\t"+"limitFlag:"+limitFlag+"\t"+"ifShareReward:"+ifShareReward+"\t"+"rewardNumber:"+rewardNumber+"\t"+"activityStatus:"+activityStatus+"\t"+"ifSetDate:"+ifSetDate+"\t"+"activityType:"+activityType+"\t"+"reverse1:"+reverse1+"\t"+"reverse2:"+reverse2+"\t"+"useVal:"+useVal;
    }
}