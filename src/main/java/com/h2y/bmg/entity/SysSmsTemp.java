package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * SysSmsTemp Model create
 * @author hwttnet
 * version:1.2
 * time:2014-10-31
 * email:info@hwttnet.com
 */

public class SysSmsTemp extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keySysSmsTemp";
    private long id;
    private long unitId;
    private long userId;
    private String mobile;
    private String moduleName;
    private Date createDate;
    private String msg;
    private String memo;

	public SysSmsTemp(){
		super();
	}

	public SysSmsTemp(long id){
		super();
		this.id = id;
	}

	public SysSmsTemp(long id,long unitId,long userId,String mobile,String moduleName,Date createDate,String msg,String memo){
		super();
		this.id = id;
		this.unitId = unitId;
		this.userId = userId;
		this.mobile = mobile;
		this.moduleName = moduleName;
		this.createDate = createDate;
		this.msg = msg;
		this.memo = memo;
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


    public long getUserId(){
      return userId;
    }
    
    public void setUserId(long userId){
      this.userId = userId;
    }


    public String getMobile(){
      return mobile;
    }
    
    public void setMobile(String mobile){
      this.mobile = mobile;
    }


    public String getModuleName(){
      return moduleName;
    }
    
    public void setModuleName(String moduleName){
      this.moduleName = moduleName;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public String getMsg(){
      return msg;
    }
    
    public void setMsg(String msg){
      this.msg = msg;
    }


    public String getMemo(){
      return memo;
    }
    
    public void setMemo(String memo){
      this.memo = memo;
    }

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"userId:"+userId+"\t"+"mobile:"+mobile+"\t"+"moduleName:"+moduleName+"\t"+"createDate:"+createDate+"\t"+"msg:"+msg+"\t"+"memo:"+memo;
    }
}