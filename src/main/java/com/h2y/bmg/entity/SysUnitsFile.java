package com.h2y.bmg.entity;


/**
 * 类描述：单位图片   
 * 作者：侯飞龙
 * 时间：2015年1月23日上午9:23:23
 * 邮件：1162040314@qq.com
 */
public class SysUnitsFile{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keySysUnitsFile";
    private long id;
    private long unitId;
    private String fileName;
    private String diskFileName;
    private String rootPath;
    private String relativePath;
    private long fileSize;
    private String fileSuffix;
    private int fileType;
    private int ifDelete;

	public SysUnitsFile(){
		super();
	}

	public SysUnitsFile(long id){
		super();
		this.id = id;
	}

	public SysUnitsFile(long id,long unitId,String fileName,String diskFileName,String rootPath,String relativePath,long fileSize,String fileSuffix,int fileType,int ifDelete){
		super();
		this.id = id;
		this.unitId = unitId;
		this.fileName = fileName;
		this.diskFileName = diskFileName;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.fileSize = fileSize;
		this.fileSuffix = fileSuffix;
		this.fileType = fileType;
		this.ifDelete = ifDelete;
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

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"fileName:"+fileName+"\t"+"diskFileName:"+diskFileName+"\t"+"rootPath:"+rootPath+"\t"+"relativePath:"+relativePath+"\t"+"fileSize:"+fileSize+"\t"+"fileSuffix:"+fileSuffix+"\t"+"fileType:"+fileType+"\t"+"ifDelete:"+ifDelete;
    }
}