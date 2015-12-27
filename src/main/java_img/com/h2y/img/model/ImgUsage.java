package com.h2y.img.model;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * ImgUsage Model create
 * @author hwttnet
 * version:1.2
 * time:2015-10-12
 * email:info@hwttnet.com
 */

public class ImgUsage extends BaseObject{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyImgUsage";
    private long id;
    private String usageCode;
    private String usageName;
    private long moduleId;
    private String moduleCode;
    private int width;
    private int height;
    private int status;
    private String memo;
    private int ord;
    private Date createDate;
    private Date updateDate;
    private String data1;
    private String data2;
    private String data3;
    private String data4;

	public ImgUsage(){
		super();
	}

	public ImgUsage(long id){
		super();
		this.id = id;
	}

	public ImgUsage(long id,String usageCode,String usageName,long moduleId,String moduleCode,int width,int height,int status,String memo,int ord,Date createDate,Date updateDate,String data1,String data2,String data3,String data4){
		super();
		this.id = id;
		this.usageCode = usageCode;
		this.usageName = usageName;
		this.moduleId = moduleId;
		this.moduleCode = moduleCode;
		this.width = width;
		this.height = height;
		this.status = status;
		this.memo = memo;
		this.ord = ord;
		this.createDate = createDate;
		this.updateDate = updateDate;
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

    public String getUsageCode(){
      return usageCode;
    }
    
    public void setUsageCode(String usageCode){
      this.usageCode = usageCode;
    }


    public String getUsageName(){
      return usageName;
    }
    
    public void setUsageName(String usageName){
      this.usageName = usageName;
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


    public int getWidth(){
      return width;
    }
    
    public void setWidth(int width){
      this.width = width;
    }


    public int getHeight(){
      return height;
    }
    
    public void setHeight(int height){
      this.height = height;
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
	return "id:"+id+"\t"+"usageCode:"+usageCode+"\t"+"usageName:"+usageName+"\t"+"moduleId:"+moduleId+"\t"+"moduleCode:"+moduleCode+"\t"+"width:"+width+"\t"+"height:"+height+"\t"+"status:"+status+"\t"+"memo:"+memo+"\t"+"ord:"+ord+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4;
    }
}