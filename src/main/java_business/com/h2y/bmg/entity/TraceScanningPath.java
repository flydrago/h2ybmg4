package com.h2y.bmg.entity;

import java.util.Date;

/**
 * TraceScanningPath Model create
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 */

public class TraceScanningPath{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyTraceScanningPath";
    private long id;
    private long provideId;
    private String pactFileName;
    private String diskFileName;
    private String rootPath;
    private String relativePath;
    private String fileSuffix;
    private Date createDate;
    private Date updateDate;
    private long parentId;
    private long fileSize;
    private int ifEnable;
    private int ord;
    private String optUserName;
    private String data1;
    private String data2;
    private String data3;

	public TraceScanningPath(){
		super();
	}

	public TraceScanningPath(long id){
		super();
		this.id = id;
	}

	public TraceScanningPath(long id,long provideId,String pactFileName,String diskFileName,String rootPath,String relativePath,String fileSuffix,Date createDate,Date updateDate,long parentId,int ifEnable,long fileSize, int ord,String data1,String data2,String data3){
		super();
		this.id = id;
		this.provideId = provideId;
		this.pactFileName = pactFileName;
		this.diskFileName = diskFileName;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.fileSuffix = fileSuffix;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.parentId = parentId;
		this.ord = ord;
		this.ifEnable = ifEnable;
		this.fileSize = fileSize;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
	}
  
    public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public int getIfEnable() {
		return ifEnable;
	}

	public void setIfEnable(int ifEnable) {
		this.ifEnable = ifEnable;
	}

	public int getOrd() {
		return ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}

	public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getProvideId(){
      return provideId;
    }
    
    public void setProvideId(long provideId){
      this.provideId = provideId;
    }


    public String getPactFileName(){
      return pactFileName;
    }
    
    public void setPactFileName(String pactFileName){
      this.pactFileName = pactFileName;
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

    public String getOptUserName(){
      return optUserName;
    }
    
    public void setOptUserName(String optUserName){
      this.optUserName = optUserName;
    }


    public String getData1(){
      return data1;
    }
    
    public void setData1(String data1){
      this.data1 = data1;
    }


    public String getData2(){
      return data2;
    }
    
    public void setData2(String data2){
      this.data2 = data2;
    }


    public String getData3(){
      return data3;
    }
    
    public void setData3(String data3){
      this.data3 = data3;
    }

    public String toString(){
	return "id:"+id+"\t"+"provideId:"+provideId+"\t"+"pactFileName:"+pactFileName+"\t"+"diskFileName:"+diskFileName+"\t"+"rootPath:"+rootPath+"\t"+"relativePath:"+relativePath+"\t"+"fileSuffix:"+fileSuffix+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"parentId:"+parentId+"\t"+"fileSize:"+fileSize+"\t"+"ord:"+ord+"\t"+"ifEnable:"+ifEnable+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3;
    }
}