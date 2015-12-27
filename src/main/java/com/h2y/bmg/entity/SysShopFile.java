package com.h2y.bmg.entity;



import java.util.Date;

/**
 * SysShopFile Model create
 * @author hwttnet
 * version:1.2
 * time:2015-06-17
 * email:info@hwttnet.com
 */

public class SysShopFile{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	public static final String key = "keySysShopFile";
	private long id;
	private long shopId;
	private int status;
	private String fileName;
	private String diskFileName;
	private String rootPath;
	private String relativePath;
	private long fileSize;
	private String fileSuffix;
	private int fileType;
	private int ord;
	private Date createDate;
	private Date updateDate;

	public SysShopFile(){
		super();
	}

	public SysShopFile(long id){
		super();
		this.id = id;
	}

	public SysShopFile(long id,long shopId,int status,String fileName,String diskFileName,String rootPath,String relativePath,long fileSize,String fileSuffix,int fileType,int ord,Date createDate,Date updateDate){
		super();
		this.id = id;
		this.shopId = shopId;
		this.status = status;
		this.fileName = fileName;
		this.diskFileName = diskFileName;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.fileSize = fileSize;
		this.fileSuffix = fileSuffix;
		this.fileType = fileType;
		this.ord = ord;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public long getShopId(){
		return shopId;
	}

	public void setShopId(long shopId){
		this.shopId = shopId;
	}


	public int getStatus(){
		return status;
	}

	public void setStatus(int status){
		this.status = status;
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


	public int getFileType(){
		return fileType;
	}

	public void setFileType(int fileType){
		this.fileType = fileType;
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


	public Date getUpdateDate(){
		return updateDate;
	}

	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}

	public String toString(){
		return "id:"+id+"\t"+"shopId:"+shopId+"\t"+"status:"+status+"\t"+"fileName:"+fileName+"\t"+"diskFileName:"+diskFileName+"\t"+"rootPath:"+rootPath+"\t"+"relativePath:"+relativePath+"\t"+"fileSize:"+fileSize+"\t"+"fileSuffix:"+fileSuffix+"\t"+"fileType:"+fileType+"\t"+"ord:"+ord+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate;
	}
}