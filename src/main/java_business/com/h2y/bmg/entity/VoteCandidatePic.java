package com.h2y.bmg.entity;

import java.util.Date;

/**
 * 

* @ClassName: VoteCandidatePic

* @Description: 投票候选人图片

* @author 李剑

* @date 2015年8月31日 下午4:13:45
 */

public class VoteCandidatePic{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
//	private static final long serialVersionUID = 1L;	
    public static final String key = "keyVoteCandidatePic";
    private long id;
    private long candidateId;
    private String picFileName;
    private String diskFileName;
    private String rootPath;
    private String relativePath;
    private long fileSize;
    private String fileSuffix;
    private int fileType;
    private Date createDate;
    private Date updateDate;
    private long optUserId;
    private String optUserAccount;
    private String optUserName;
    private int picStatus;
    private int ord;
    private String reserve1;
    private String reserve2;
    private String reserve3;

	public VoteCandidatePic(){
		super();
	}

	public VoteCandidatePic(long id){
		super();
		this.id = id;
	}

	public VoteCandidatePic(long id,long candidateId,String picFileName,String diskFileName,String rootPath,String relativePath,long fileSize,String fileSuffix,int fileType,Date createDate,Date updateDate,long optUserId,String optUserAccount,String optUserName,int picStatus,int ord,String reserve1,String reserve2,String reserve3){
		super();
		this.id = id;
		this.candidateId = candidateId;
		this.picFileName = picFileName;
		this.diskFileName = diskFileName;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.fileSize = fileSize;
		this.fileSuffix = fileSuffix;
		this.fileType = fileType;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.optUserId = optUserId;
		this.optUserAccount = optUserAccount;
		this.optUserName = optUserName;
		this.picStatus = picStatus;
		this.ord = ord;
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

    public long getCandidateId(){
      return candidateId;
    }
    
    public void setCandidateId(long candidateId){
      this.candidateId = candidateId;
    }


    public String getPicFileName(){
      return picFileName;
    }
    
    public void setPicFileName(String picFileName){
      this.picFileName = picFileName;
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


    public long getOptUserId(){
      return optUserId;
    }
    
    public void setOptUserId(long optUserId){
      this.optUserId = optUserId;
    }


    public String getOptUserAccount(){
      return optUserAccount;
    }
    
    public void setOptUserAccount(String optUserAccount){
      this.optUserAccount = optUserAccount;
    }


    public String getOptUserName(){
      return optUserName;
    }
    
    public void setOptUserName(String optUserName){
      this.optUserName = optUserName;
    }


    public int getPicStatus(){
      return picStatus;
    }
    
    public void setPicStatus(int picStatus){
      this.picStatus = picStatus;
    }


    public int getOrd(){
      return ord;
    }
    
    public void setOrd(int ord){
      this.ord = ord;
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
	return "id:"+id+"\t"+"candidateId:"+candidateId+"\t"+"picFileName:"+picFileName+"\t"+"diskFileName:"+diskFileName+"\t"+"rootPath:"+rootPath+"\t"+"relativePath:"+relativePath+"\t"+"fileSize:"+fileSize+"\t"+"fileSuffix:"+fileSuffix+"\t"+"fileType:"+fileType+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"optUserId:"+optUserId+"\t"+"optUserAccount:"+optUserAccount+"\t"+"optUserName:"+optUserName+"\t"+"picStatus:"+picStatus+"\t"+"ord:"+ord+"\t"+"reserve1:"+reserve1+"\t"+"reserve2:"+reserve2+"\t"+"reserve3:"+reserve3;
    }
}