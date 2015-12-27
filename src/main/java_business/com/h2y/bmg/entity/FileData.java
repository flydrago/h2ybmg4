package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 类描述：商品图片数据 
 * 作者：侯飞龙
 * 时间：2015年1月26日下午5:00:03
 * 邮件：1162040314@qq.com
 */
public class FileData extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyFileData";
    private long id;
    private String fileName;
    private String diskFileName;
    private String rootPath;
    private String relativePath;
    private long fileSize;
    private String fileSuffix;
    private int ifDelete;
    private int fileType;
    private long dataId;
    private int dataVersion;
    private Date createDate;
    private int ord;
    private int dataType;

	public FileData(){
		super();
	}

	public FileData(long id){
		super();
		this.id = id;
	}

	public FileData(long id,String fileName,String diskFileName,String rootPath,String relativePath,long fileSize,String fileSuffix,int ifDelete,int fileType,long dataId,int dataVersion,Date createDate,int ord,int dataType){
		super();
		this.id = id;
		this.fileName = fileName;
		this.diskFileName = diskFileName;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.fileSize = fileSize;
		this.fileSuffix = fileSuffix;
		this.ifDelete = ifDelete;
		this.fileType = fileType;
		this.dataId = dataId;
		this.dataVersion = dataVersion;
		this.createDate = createDate;
		this.ord = ord;
		this.dataType = dataType;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
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


    public int getIfDelete(){
      return ifDelete;
    }
    
    public void setIfDelete(int ifDelete){
      this.ifDelete = ifDelete;
    }


    public int getFileType(){
      return fileType;
    }
    
    public void setFileType(int fileType){
      this.fileType = fileType;
    }


    public long getDataId(){
      return dataId;
    }
    
    public void setDataId(long dataId){
      this.dataId = dataId;
    }


    public int getDataVersion(){
      return dataVersion;
    }
    
    public void setDataVersion(int dataVersion){
      this.dataVersion = dataVersion;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public int getOrd(){
      return ord;
    }
    
    public void setOrd(int ord){
      this.ord = ord;
    }


    public int getDataType(){
      return dataType;
    }
    
    public void setDataType(int dataType){
      this.dataType = dataType;
    }

    public String toString(){
	return "id:"+id+"\t"+"fileName:"+fileName+"\t"+"diskFileName:"+diskFileName+"\t"+"rootPath:"+rootPath+"\t"+"relativePath:"+relativePath+"\t"+"fileSize:"+fileSize+"\t"+"fileSuffix:"+fileSuffix+"\t"+"ifDelete:"+ifDelete+"\t"+"fileType:"+fileType+"\t"+"dataId:"+dataId+"\t"+"dataVersion:"+dataVersion+"\t"+"createDate:"+createDate+"\t"+"ord:"+ord+"\t"+"dataType:"+dataType;
    }
}