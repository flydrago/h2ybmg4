package com.h2y.bmg.entity;

import java.util.Date;

/**
 *

* @ClassName: VoteSubjectFile

* @Description: 投票主题主页图片

* @author 李剑

* @date 2015年8月31日 下午4:14:32
 */

public class VoteSubjectFile{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
//	private static final long serialVersionUID = 1L;	
    public static final String key = "keyVoteSubjectFile";
    private long id;
    private long subjectId;
    private Date createDate;
    private String fileName;
    private String diskFileName;
    private String rootPath;
    private String relativePath;
    private long fileSize;
    private String fileSuffix;
    private int fileType;
    private int ifDelete;
    private int ord;

	public VoteSubjectFile(){
		super();
	}

	public VoteSubjectFile(long id){
		super();
		this.id = id;
	}

	public VoteSubjectFile(long id,long subjectId,Date createDate,String fileName,String diskFileName,String rootPath,String relativePath,long fileSize,String fileSuffix,int fileType,int ifDelete,int ord){
		super();
		this.id = id;
		this.subjectId = subjectId;
		this.createDate = createDate;
		this.fileName = fileName;
		this.diskFileName = diskFileName;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.fileSize = fileSize;
		this.fileSuffix = fileSuffix;
		this.fileType = fileType;
		this.ifDelete = ifDelete;
		this.ord = ord;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getSubjectId(){
      return subjectId;
    }
    
    public void setSubjectId(long subjectId){
      this.subjectId = subjectId;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
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


    public int getIfDelete(){
      return ifDelete;
    }
    
    public void setIfDelete(int ifDelete){
      this.ifDelete = ifDelete;
    }


    public int getOrd(){
      return ord;
    }
    
    public void setOrd(int ord){
      this.ord = ord;
    }

    public String toString(){
	return "id:"+id+"\t"+"subjectId:"+subjectId+"\t"+"createDate:"+createDate+"\t"+"fileName:"+fileName+"\t"+"diskFileName:"+diskFileName+"\t"+"rootPath:"+rootPath+"\t"+"relativePath:"+relativePath+"\t"+"fileSize:"+fileSize+"\t"+"fileSuffix:"+fileSuffix+"\t"+"fileType:"+fileType+"\t"+"ifDelete:"+ifDelete+"\t"+"ord:"+ord;
    }
}