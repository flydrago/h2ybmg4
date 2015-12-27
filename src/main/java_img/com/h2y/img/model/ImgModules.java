package com.h2y.img.model;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * ImgModules Model create
 * @author hwttnet
 * version:1.2
 * time:2015-10-12
 * email:info@hwttnet.com
 */

public class ImgModules extends BaseObject{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyImgModules";
    private long id;
    private String moduleCode;
    private String moduleName;
    private long parentId;
    private String parentCode;
    private Date createDate;
    private Date updateDate;
    private String memo;
    private int status;
    private String data1;
    private String data2;
    private String data3;

	public ImgModules(){
		super();
	}

	public ImgModules(long id){
		super();
		this.id = id;
	}

	public ImgModules(long id,String moduleCode,String moduleName,long parentId,String parentCode,Date createDate,Date updateDate,String memo,int status,String data1,String data2,String data3){
		super();
		this.id = id;
		this.moduleCode = moduleCode;
		this.moduleName = moduleName;
		this.parentId = parentId;
		this.parentCode = parentCode;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.memo = memo;
		this.status = status;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public String getModuleCode(){
      return moduleCode;
    }
    
    public void setModuleCode(String moduleCode){
      this.moduleCode = moduleCode;
    }


    public String getModuleName(){
      return moduleName;
    }
    
    public void setModuleName(String moduleName){
      this.moduleName = moduleName;
    }


    public long getParentId(){
      return parentId;
    }
    
    public void setParentId(long parentId){
      this.parentId = parentId;
    }


    public String getParentCode(){
      return parentCode;
    }
    
    public void setParentCode(String parentCode){
      this.parentCode = parentCode;
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


    public int getStatus(){
      return status;
    }
    
    public void setStatus(int status){
      this.status = status;
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
	return "id:"+id+"\t"+"moduleCode:"+moduleCode+"\t"+"moduleName:"+moduleName+"\t"+"parentId:"+parentId+"\t"+"parentCode:"+parentCode+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"memo:"+memo+"\t"+"status:"+status+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3;
    }
}