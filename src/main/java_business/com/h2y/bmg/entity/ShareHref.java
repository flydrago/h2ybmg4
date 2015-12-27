package com.h2y.bmg.entity;



import java.util.Date;

/**
 * ShareHref Model create
 * @author hwttnet
 * version:1.2
 * time:2015-08-17
 * email:info@hwttnet.com
 */

public class ShareHref{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyShareHref";
    private long id;
    private long unitId;
    private int unitType;
    private String zoneCode;
    private String name;
    private Date createDate;
    private Date updateDate;
    private String title;
    private String description;
    private String fileName;
    private String diskFileName;
    private String rootPath;
    private String relativePath;
    private long fileSize;
    private String fileSuffix;
    private String dataType;
    private String str1;
    private String str2;
    private String str3;
    private int ord;
    private int hrefStatus;

	public ShareHref(){
		super();
	}

	public ShareHref(long id){
		super();
		this.id = id;
	}

	public ShareHref(long id,long unitId,int unitType,String zoneCode,String name,Date createDate,Date updateDate,String title,String description,String fileName,String diskFileName,String rootPath,String relativePath,long fileSize,String fileSuffix,String dataType,String str1,String str2,String str3,int ord,int hrefStatus){
		super();
		this.id = id;
		this.unitId = unitId;
		this.unitType = unitType;
		this.zoneCode = zoneCode;
		this.name = name;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.title = title;
		this.description = description;
		this.fileName = fileName;
		this.diskFileName = diskFileName;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.fileSize = fileSize;
		this.fileSuffix = fileSuffix;
		this.dataType = dataType;
		this.str1 = str1;
		this.str2 = str2;
		this.str3 = str3;
		this.ord = ord;
		this.hrefStatus = hrefStatus;
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


    public String getName(){
      return name;
    }
    
    public void setName(String name){
      this.name = name;
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


    public String getTitle(){
      return title;
    }
    
    public void setTitle(String title){
      this.title = title;
    }


    public String getDescription(){
      return description;
    }
    
    public void setDescription(String description){
      this.description = description;
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


    public String getDataType(){
      return dataType;
    }
    
    public void setDataType(String dataType){
      this.dataType = dataType;
    }


    public String getStr1(){
      return str1;
    }
    
    public void setStr1(String str1){
      this.str1 = str1;
    }


    public String getStr2(){
      return str2;
    }
    
    public void setStr2(String str2){
      this.str2 = str2;
    }


    public String getStr3(){
      return str3;
    }
    
    public void setStr3(String str3){
      this.str3 = str3;
    }


    public int getOrd(){
      return ord;
    }
    
    public void setOrd(int ord){
      this.ord = ord;
    }


    public int getHrefStatus(){
      return hrefStatus;
    }
    
    public void setHrefStatus(int hrefStatus){
      this.hrefStatus = hrefStatus;
    }

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"unitType:"+unitType+"\t"+"zoneCode:"+zoneCode+"\t"+"name:"+name+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"title:"+title+"\t"+"description:"+description+"\t"+"fileName:"+fileName+"\t"+"diskFileName:"+diskFileName+"\t"+"rootPath:"+rootPath+"\t"+"relativePath:"+relativePath+"\t"+"fileSize:"+fileSize+"\t"+"fileSuffix:"+fileSuffix+"\t"+"dataType:"+dataType+"\t"+"str1:"+str1+"\t"+"str2:"+str2+"\t"+"str3:"+str3+"\t"+"ord:"+ord+"\t"+"hrefStatus:"+hrefStatus;
    }
}