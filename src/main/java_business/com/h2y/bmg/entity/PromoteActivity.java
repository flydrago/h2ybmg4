package com.h2y.bmg.entity;



import java.util.Date;

/**
 * PromoteActivity Model create
 * @author hwttnet
 * version:1.2
 * time:2015-08-17
 * email:info@hwttnet.com
 */

public class PromoteActivity{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyPromoteActivity";
    private long id;
    private long unitId;
    private int unitType;
    private String zoneCode;
    private String title;
    private int claimLimit;
    private int claimNum;
    private String fileName;
    private String diskFileName;
    private String rootPath;
    private String relativePath;
    private long fileSize;
    private String fileSuffix;
    private Date createDate;
    private Date updateDate;
    private Date startDate;
    private Date endDate;
    private String description;
    private String memo;
    private int promoteStatus;
    private int ord;

	public PromoteActivity(){
		super();
	}

	public PromoteActivity(long id){
		super();
		this.id = id;
	}

	public PromoteActivity(long id,long unitId,int unitType,String zoneCode,String title,int claimLimit,int claimNum,String fileName,String diskFileName,String rootPath,String relativePath,long fileSize,String fileSuffix,Date createDate,Date updateDate,Date startDate,Date endDate,String description,String memo,int promoteStatus,int ord){
		super();
		this.id = id;
		this.unitId = unitId;
		this.unitType = unitType;
		this.zoneCode = zoneCode;
		this.title = title;
		this.claimLimit = claimLimit;
		this.claimNum = claimNum;
		this.fileName = fileName;
		this.diskFileName = diskFileName;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.fileSize = fileSize;
		this.fileSuffix = fileSuffix;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.memo = memo;
		this.promoteStatus = promoteStatus;
		this.ord = ord;
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


    public String getTitle(){
      return title;
    }
    
    public void setTitle(String title){
      this.title = title;
    }


    public int getClaimLimit(){
      return claimLimit;
    }
    
    public void setClaimLimit(int claimLimit){
      this.claimLimit = claimLimit;
    }


    public int getClaimNum(){
      return claimNum;
    }
    
    public void setClaimNum(int claimNum){
      this.claimNum = claimNum;
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


    public Date getUpdateDate(){
      return updateDate;
    }
    
    public void setUpdateDate(Date updateDate){
      this.updateDate = updateDate;
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


    public String getDescription(){
      return description;
    }
    
    public void setDescription(String description){
      this.description = description;
    }


    public String getMemo(){
      return memo;
    }
    
    public void setMemo(String memo){
      this.memo = memo;
    }


    public int getPromoteStatus(){
      return promoteStatus;
    }
    
    public void setPromoteStatus(int promoteStatus){
      this.promoteStatus = promoteStatus;
    }


    public int getOrd(){
      return ord;
    }
    
    public void setOrd(int ord){
      this.ord = ord;
    }

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"unitType:"+unitType+"\t"+"zoneCode:"+zoneCode+"\t"+"title:"+title+"\t"+"claimLimit:"+claimLimit+"\t"+"claimNum:"+claimNum+"\t"+"fileName:"+fileName+"\t"+"diskFileName:"+diskFileName+"\t"+"rootPath:"+rootPath+"\t"+"relativePath:"+relativePath+"\t"+"fileSize:"+fileSize+"\t"+"fileSuffix:"+fileSuffix+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"startDate:"+startDate+"\t"+"endDate:"+endDate+"\t"+"description:"+description+"\t"+"memo:"+memo+"\t"+"promoteStatus:"+promoteStatus+"\t"+"ord:"+ord;
    }
}