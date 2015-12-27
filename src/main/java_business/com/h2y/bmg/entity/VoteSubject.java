package com.h2y.bmg.entity;

import java.util.Date;

/**
 * 

* @ClassName: VoteSubject

* @Description: 投票主题

* @author 李剑

* @date 2015年8月31日 下午4:13:57
 */

public class VoteSubject{
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
//	private static final long serialVersionUID = 1L;	
    public static final String key = "keyVoteSubject";
    private long id;
    private long unitId;
    private int unitType;
    private String zoneCode;
    private String subjectName;
    private String subjectType;
    private long promoteId;
    private int limitTimes;
    private int totalTimes;
    private Date startDate;
    private Date endDate;
    private Date createDate;
    private Date updateDate;
    private String fileName;
    private String diskFileName;
    private String rootPath;
    private String relativePath;
    private long fileSize;
    private String fileSuffix;
    private String description;
    private int subjectStatus;
    private String reserve1;
    private String reserve2;
    private String reserve3;

	public VoteSubject(){
		super();
	}

	public VoteSubject(long id){
		super();
		this.id = id;
	}

	public VoteSubject(long id,long unitId,int unitType,String zoneCode,String subjectName,String subjectType,long promoteId,int limitTimes,int totalTimes,Date startDate,Date endDate,Date createDate,Date updateDate,String fileName,String diskFileName,String rootPath,String relativePath,long fileSize,String fileSuffix,String description,int subjectStatus,String reserve1,String reserve2,String reserve3){
		super();
		this.id = id;
		this.unitId = unitId;
		this.unitType = unitType;
		this.zoneCode = zoneCode;
		this.subjectName = subjectName;
		this.subjectType = subjectType;
		this.promoteId = promoteId;
		this.limitTimes = limitTimes;
		this.totalTimes = totalTimes;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.fileName = fileName;
		this.diskFileName = diskFileName;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.fileSize = fileSize;
		this.fileSuffix = fileSuffix;
		this.description = description;
		this.subjectStatus = subjectStatus;
		this.reserve1 = reserve1;
		this.reserve2 = reserve2;
		this.reserve3 = reserve3;
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


    public int getUnitType(){
      return unitType;
    }
    
    public void setUnitType(int unitType){
      this.unitType = unitType;
    }


    public String getZoneCode(){
      return zoneCode;
    }
    
    public void setZoneCode(String zoneCode){
      this.zoneCode = zoneCode;
    }


    public String getSubjectName(){
      return subjectName;
    }
    
    public void setSubjectName(String subjectName){
      this.subjectName = subjectName;
    }


    public String getSubjectType(){
      return subjectType;
    }
    
    public void setSubjectType(String subjectType){
      this.subjectType = subjectType;
    }


    public long getPromoteId(){
      return promoteId;
    }
    
    public void setPromoteId(long promoteId){
      this.promoteId = promoteId;
    }


    public int getLimitTimes(){
      return limitTimes;
    }
    
    public void setLimitTimes(int limitTimes){
      this.limitTimes = limitTimes;
    }


    public int getTotalTimes(){
      return totalTimes;
    }
    
    public void setTotalTimes(int totalTimes){
      this.totalTimes = totalTimes;
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


    public String getDescription(){
      return description;
    }
    
    public void setDescription(String description){
      this.description = description;
    }


    public int getSubjectStatus(){
      return subjectStatus;
    }
    
    public void setSubjectStatus(int subjectStatus){
      this.subjectStatus = subjectStatus;
    }


    public String getReserve1(){
      return reserve1;
    }
    
    public void setReserve1(String reserve1){
      this.reserve1 = reserve1;
    }


    public String getReserve2(){
      return reserve2;
    }
    
    public void setReserve2(String reserve2){
      this.reserve2 = reserve2;
    }


    public String getReserve3(){
      return reserve3;
    }
    
    public void setReserve3(String reserve3){
      this.reserve3 = reserve3;
    }

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"unitType:"+unitType+"\t"+"zoneCode:"+zoneCode+"\t"+"subjectName:"+subjectName+"\t"+"subjectType:"+subjectType+"\t"+"promoteId:"+promoteId+"\t"+"limitTimes:"+limitTimes+"\t"+"totalTimes:"+totalTimes+"\t"+"startDate:"+startDate+"\t"+"endDate:"+endDate+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"fileName:"+fileName+"\t"+"diskFileName:"+diskFileName+"\t"+"rootPath:"+rootPath+"\t"+"relativePath:"+relativePath+"\t"+"fileSize:"+fileSize+"\t"+"fileSuffix:"+fileSuffix+"\t"+"description:"+description+"\t"+"subjectStatus:"+subjectStatus+"\t"+"reserve1:"+reserve1+"\t"+"reserve2:"+reserve2+"\t"+"reserve3:"+reserve3;
    }
}