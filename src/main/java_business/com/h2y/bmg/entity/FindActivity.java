package com.h2y.bmg.entity;



import java.util.Date;

/**
 * FindActivity Model create
 * @author hwttnet
 * version:1.2
 * time:2015-04-17
 * email:info@hwttnet.com
 */

public class FindActivity{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	public static final String key = "keyFindActivity";
	private long id;
	private long unitId;
	private long userId;
	private String title;
	private String description;
	private int activityLevel;
	private int activityType;
	private String content;
	private String fileName;
	private String diskFileName;
	private String rootPath;
	private String relativePath;
	private long fileSize;
	private String fileSuffix;
	private Date createDate;
	private Date startDate;
	private Date endDate;
	private Date updateDate;
	private int ifSetDate;
	private int ifSetPush;
	private Date pushDate;
	private int activityStatus;
	private String reverse1;
	private String reverse2;
	private int pushType;
	private int pushTimes;
	private String pushDate1;
	private String pushDate2;
	private String pushDate3;
	private String pushDate4;
	private String pushDate5;
	private String pushDate6;

	public FindActivity(){
		super();
	}

	public FindActivity(long id){
		super();
		this.id = id;
	}

	public FindActivity(long id,long unitId,long userId,String title,String description,int activityLevel,int activityType,String content,String fileName,String diskFileName,String rootPath,String relativePath,long fileSize,String fileSuffix,Date createDate,Date startDate,Date endDate,Date updateDate,int ifSetDate,int ifSetPush,Date pushDate,int activityStatus,String reverse1,String reverse2,int pushType,int pushTimes,String pushDate1,String pushDate2,String pushDate3,String pushDate4,String pushDate5,String pushDate6){
		super();
		this.id = id;
		this.unitId = unitId;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.activityLevel = activityLevel;
		this.activityType = activityType;
		this.content = content;
		this.fileName = fileName;
		this.diskFileName = diskFileName;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.fileSize = fileSize;
		this.fileSuffix = fileSuffix;
		this.createDate = createDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.updateDate = updateDate;
		this.ifSetDate = ifSetDate;
		this.ifSetPush = ifSetPush;
		this.pushDate = pushDate;
		this.activityStatus = activityStatus;
		this.reverse1 = reverse1;
		this.reverse2 = reverse2;
		this.pushType = pushType;
		this.pushTimes = pushTimes;
		this.pushDate1 = pushDate1;
		this.pushDate2 = pushDate2;
		this.pushDate3 = pushDate3;
		this.pushDate4 = pushDate4;
		this.pushDate5 = pushDate5;
		this.pushDate6 = pushDate6;
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


	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}


	public int getActivityLevel(){
		return activityLevel;
	}

	public void setActivityLevel(int activityLevel){
		this.activityLevel = activityLevel;
	}


	public int getActivityType(){
		return activityType;
	}

	public void setActivityType(int activityType){
		this.activityType = activityType;
	}


	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;
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


	public Date getUpdateDate(){
		return updateDate;
	}

	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}


	public int getIfSetDate(){
		return ifSetDate;
	}

	public void setIfSetDate(int ifSetDate){
		this.ifSetDate = ifSetDate;
	}


	public int getIfSetPush(){
		return ifSetPush;
	}

	public void setIfSetPush(int ifSetPush){
		this.ifSetPush = ifSetPush;
	}


	public Date getPushDate(){
		return pushDate;
	}

	public void setPushDate(Date pushDate){
		this.pushDate = pushDate;
	}


	public int getActivityStatus(){
		return activityStatus;
	}

	public void setActivityStatus(int activityStatus){
		this.activityStatus = activityStatus;
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


	public int getPushType(){
		return pushType;
	}

	public void setPushType(int pushType){
		this.pushType = pushType;
	}


	public int getPushTimes(){
		return pushTimes;
	}

	public void setPushTimes(int pushTimes){
		this.pushTimes = pushTimes;
	}


	public String getPushDate1(){
		return pushDate1;
	}

	public void setPushDate1(String pushDate1){
		this.pushDate1 = pushDate1;
	}


	public String getPushDate2(){
		return pushDate2;
	}

	public void setPushDate2(String pushDate2){
		this.pushDate2 = pushDate2;
	}


	public String getPushDate3(){
		return pushDate3;
	}

	public void setPushDate3(String pushDate3){
		this.pushDate3 = pushDate3;
	}


	public String getPushDate4(){
		return pushDate4;
	}

	public void setPushDate4(String pushDate4){
		this.pushDate4 = pushDate4;
	}


	public String getPushDate5(){
		return pushDate5;
	}

	public void setPushDate5(String pushDate5){
		this.pushDate5 = pushDate5;
	}


	public String getPushDate6(){
		return pushDate6;
	}

	public void setPushDate6(String pushDate6){
		this.pushDate6 = pushDate6;
	}

	public String toString(){
		return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"userId:"+userId+"\t"+"title:"+title+"\t"+"description:"+description+"\t"+"activityLevel:"+activityLevel+"\t"+"activityType:"+activityType+"\t"+"content:"+content+"\t"+"fileName:"+fileName+"\t"+"diskFileName:"+diskFileName+"\t"+"rootPath:"+rootPath+"\t"+"relativePath:"+relativePath+"\t"+"fileSize:"+fileSize+"\t"+"fileSuffix:"+fileSuffix+"\t"+"createDate:"+createDate+"\t"+"startDate:"+startDate+"\t"+"endDate:"+endDate+"\t"+"updateDate:"+updateDate+"\t"+"ifSetDate:"+ifSetDate+"\t"+"ifSetPush:"+ifSetPush+"\t"+"pushDate:"+pushDate+"\t"+"activityStatus:"+activityStatus+"\t"+"reverse1:"+reverse1+"\t"+"reverse2:"+reverse2+"\t"+"pushType:"+pushType+"\t"+"pushTimes:"+pushTimes+"\t"+"pushDate1:"+pushDate1+"\t"+"pushDate2:"+pushDate2+"\t"+"pushDate3:"+pushDate3+"\t"+"pushDate4:"+pushDate4+"\t"+"pushDate5:"+pushDate5+"\t"+"pushDate6:"+pushDate6;
	}
}