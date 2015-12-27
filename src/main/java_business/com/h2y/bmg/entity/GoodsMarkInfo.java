package com.h2y.bmg.entity;



import java.util.Date;

/**
 * GoodsMarkInfo Model create
 * @author hwttnet
 * version:1.2
 * time:2015-08-24
 * email:info@hwttnet.com
 */

public class GoodsMarkInfo{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	public static final String key = "keyGoodsMarkInfo";
	private long id;
	private long markId;
	private String name;
	private String speName;
	private String firSpellName;
	private int status;
	private long ord;
	private Date createDate;
	private String memo;
	private long userId;
	private String fileName;
	private String diskFileName;
	private String rootPath;
	private String relativePath;
	private long fileSize;
	private String fileSuffix;
	private Date updateDate;

	public GoodsMarkInfo(){
		super();
	}

	public GoodsMarkInfo(long id){
		super();
		this.id = id;
	}

	public GoodsMarkInfo(long id,long markId,String name,String speName,String firSpellName,int status,long ord,Date createDate,String memo,long userId,String fileName,String diskFileName,String rootPath,String relativePath,long fileSize,String fileSuffix,Date updateDate){
		super();
		this.id = id;
		this.markId = markId;
		this.name = name;
		this.speName = speName;
		this.firSpellName = firSpellName;
		this.status = status;
		this.ord = ord;
		this.createDate = createDate;
		this.memo = memo;
		this.userId = userId;
		this.fileName = fileName;
		this.diskFileName = diskFileName;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.fileSize = fileSize;
		this.fileSuffix = fileSuffix;
		this.updateDate = updateDate;
	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public long getMarkId(){
		return markId;
	}

	public void setMarkId(long markId){
		this.markId = markId;
	}


	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}


	public String getSpeName(){
		return speName;
	}

	public void setSpeName(String speName){
		this.speName = speName;
	}


	public String getFirSpellName(){
		return firSpellName;
	}

	public void setFirSpellName(String firSpellName){
		this.firSpellName = firSpellName;
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


	public Date getUpdateDate(){
		return updateDate;
	}

	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}

	public String toString(){
		return "id:"+id+"\t"+"markId:"+markId+"\t"+"name:"+name+"\t"+"speName:"+speName+"\t"+"firSpellName:"+firSpellName+"\t"+"status:"+status+"\t"+"ord:"+ord+"\t"+"createDate:"+createDate+"\t"+"memo:"+memo+"\t"+"userId:"+userId+"\t"+"fileName:"+fileName+"\t"+"diskFileName:"+diskFileName+"\t"+"rootPath:"+rootPath+"\t"+"relativePath:"+relativePath+"\t"+"fileSize:"+fileSize+"\t"+"fileSuffix:"+fileSuffix+"\t"+"updateDate:"+updateDate;
	}
}