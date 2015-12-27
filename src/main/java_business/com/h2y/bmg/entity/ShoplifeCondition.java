package com.h2y.bmg.entity;



import java.util.Date;

/**
 * ShoplifeCondition Model create
 * @author hwttnet
 * version:1.2
 * time:2015-06-29
 * email:info@hwttnet.com
 */

public class ShoplifeCondition{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	public static final String key = "keyShoplifeCondition";
	private long id;
	private long parentId;
	private String conditionCode;
	private String conditionName;
	private String conditionValue;
	private int status;
	private long ord;
	private Date createDate;
	private Date updateDate;
	private String memo;
	private long userId;
	private String fileName;
	private String diskFileName;
	private String rootPath;
	private String relativePath;
	private long fileSize;
	private String fileSuffix;
	private String conditionKey;

	public ShoplifeCondition(){
		super();
	}

	public ShoplifeCondition(long id){
		super();
		this.id = id;
	}

	public ShoplifeCondition(long id,long parentId,String conditionCode,String conditionName,String conditionValue,int status,long ord,Date createDate,Date updateDate,String memo,long userId,String fileName,String diskFileName,String rootPath,String relativePath,long fileSize,String fileSuffix,String conditionKey){
		super();
		this.id = id;
		this.parentId = parentId;
		this.conditionCode = conditionCode;
		this.conditionName = conditionName;
		this.conditionValue = conditionValue;
		this.status = status;
		this.ord = ord;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.memo = memo;
		this.userId = userId;
		this.fileName = fileName;
		this.diskFileName = diskFileName;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.fileSize = fileSize;
		this.fileSuffix = fileSuffix;
		this.conditionKey = conditionKey;
	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public long getParentId(){
		return parentId;
	}

	public void setParentId(long parentId){
		this.parentId = parentId;
	}


	public String getConditionCode(){
		return conditionCode;
	}

	public void setConditionCode(String conditionCode){
		this.conditionCode = conditionCode;
	}


	public String getConditionName(){
		return conditionName;
	}

	public void setConditionName(String conditionName){
		this.conditionName = conditionName;
	}


	public String getConditionValue(){
		return conditionValue;
	}

	public void setConditionValue(String conditionValue){
		this.conditionValue = conditionValue;
	}


	public int getStatus(){
		return status;
	}

	public void setStatus(int status){
		this.status = status;
	}


	public long getOrd(){
		return ord;
	}

	public void setOrd(long ord){
		this.ord = ord;
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


	public String getMemo(){
		return memo;
	}

	public void setMemo(String memo){
		this.memo = memo;
	}


	public long getUserId(){
		return userId;
	}

	public void setUserId(long userId){
		this.userId = userId;
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


	public String getConditionKey(){
		return conditionKey;
	}

	public void setConditionKey(String conditionKey){
		this.conditionKey = conditionKey;
	}

	public String toString(){
		return "id:"+id+"\t"+"parentId:"+parentId+"\t"+"conditionCode:"+conditionCode+"\t"+"conditionName:"+conditionName+"\t"+"conditionValue:"+conditionValue+"\t"+"status:"+status+"\t"+"ord:"+ord+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"memo:"+memo+"\t"+"userId:"+userId+"\t"+"fileName:"+fileName+"\t"+"diskFileName:"+diskFileName+"\t"+"rootPath:"+rootPath+"\t"+"relativePath:"+relativePath+"\t"+"fileSize:"+fileSize+"\t"+"fileSuffix:"+fileSuffix+"\t"+"conditionKey:"+conditionKey;
	}
}