package com.h2y.img.model;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * ImgStorage Model create
 * @author hwttnet
 * version:1.2
 * time:2015-10-11
 */

public class ImgStorage extends BaseObject{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyImgStorage";
    private long id;
    private long unitId;
    private long moduleId;
    private String moduleCode;
    private long usageId;
    private String usageCode;
    private String imgDes;
    private String rootPath;
    private String relativePath;
    private String fileName;
    private String saveName;
    private Date createDate;
    private int status;
    private String memo;
    private int ord;
    private String data1;
    private String data2;
    private String data3;
    private String data4;

	public ImgStorage(){
		super();
	}

	public ImgStorage(long id){
		super();
		this.id = id;
	}

	public ImgStorage(long id,long unitId,long moduleId,String moduleCode,long usageId,String usageCode,String imgDes,String rootPath,String relativePath,String fileName,String saveName,Date createDate,int status,String memo,int ord,String data1,String data2,String data3,String data4){
		super();
		this.id = id;
		this.unitId = unitId;
		this.moduleId = moduleId;
		this.moduleCode = moduleCode;
		this.usageId = usageId;
		this.usageCode = usageCode;
		this.imgDes = imgDes;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.fileName = fileName;
		this.saveName = saveName;
		this.createDate = createDate;
		this.status = status;
		this.memo = memo;
		this.ord = ord;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
		this.data4 = data4;
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


    public long getModuleId(){
      return moduleId;
    }
    
    public void setModuleId(long moduleId){
      this.moduleId = moduleId;
    }


    public String getModuleCode(){
      return moduleCode;
    }
    
    public void setModuleCode(String moduleCode){
      this.moduleCode = moduleCode;
    }


    public long getUsageId(){
      return usageId;
    }
    
    public void setUsageId(long usageId){
      this.usageId = usageId;
    }


    public String getUsageCode(){
      return usageCode;
    }
    
    public void setUsageCode(String usageCode){
      this.usageCode = usageCode;
    }


    public String getImgDes(){
      return imgDes;
    }
    
    public void setImgDes(String imgDes){
      this.imgDes = imgDes;
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


    public String getFileName(){
      return fileName;
    }
    
    public void setFileName(String fileName){
      this.fileName = fileName;
    }


    public String getSaveName(){
      return saveName;
    }
    
    public void setSaveName(String saveName){
      this.saveName = saveName;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public int getStatus(){
      return status;
    }
    
    public void setStatus(int status){
      this.status = status;
    }


    public String getMemo(){
      return memo;
    }
    
    public void setMemo(String memo){
      this.memo = memo;
    }


    public int getOrd(){
      return ord;
    }
    
    public void setOrd(int ord){
      this.ord = ord;
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


    public String getData4(){
      return data4;
    }
    
    public void setData4(String data4){
      this.data4 = data4;
    }

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"moduleId:"+moduleId+"\t"+"moduleCode:"+moduleCode+"\t"+"usageId:"+usageId+"\t"+"usageCode:"+usageCode+"\t"+"imgDes:"+imgDes+"\t"+"rootPath:"+rootPath+"\t"+"relativePath:"+relativePath+"\t"+"fileName:"+fileName+"\t"+"saveName:"+saveName+"\t"+"createDate:"+createDate+"\t"+"status:"+status+"\t"+"memo:"+memo+"\t"+"ord:"+ord+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4;
    }
}