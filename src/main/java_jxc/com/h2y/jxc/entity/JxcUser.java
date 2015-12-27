package com.h2y.jxc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 进销存  系统用户 Model
 * @author hwttnet
 * version:1.2
 * time:2015-06-24
 * email:info@hwttnet.com
 */

public class JxcUser implements Serializable{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyJxcUser";
    private long id;
    private long unitId;
    private String userName;
    private String account;
    private String password;
    private String mobile;
    private String email;
    private String idNumber;
    private Date createDate;
    private Date updateDate;
    private int lockFlag;
    private int sysFlag;
    private int deleteFlag;
    private long data1;
    private String data2;
    private String data3;

	public JxcUser(){
		super();
	}

	public JxcUser(long id){
		super();
		this.id = id;
	}

	public JxcUser(long id,long unitId,String userName,String account,String password,String mobile,String email,String idNumber,Date createDate,Date updateDate,int lockFlag,int sysFlag,int deleteFlag,long data1,String data2,String data3){
		super();
		this.id = id;
		this.unitId = unitId;
		this.userName = userName;
		this.account = account;
		this.password = password;
		this.mobile = mobile;
		this.email = email;
		this.idNumber = idNumber;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.lockFlag = lockFlag;
		this.sysFlag = sysFlag;
		this.deleteFlag = deleteFlag;
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

    public long getUnitId(){
      return unitId;
    }
    
    public void setUnitId(long unitId){
      this.unitId = unitId;
    }


    public String getUserName(){
      return userName;
    }
    
    public void setUserName(String userName){
      this.userName = userName;
    }


    public String getAccount(){
      return account;
    }
    
    public void setAccount(String account){
      this.account = account;
    }


    public String getPassword(){
      return password;
    }
    
    public void setPassword(String password){
      this.password = password;
    }


    public String getMobile(){
      return mobile;
    }
    
    public void setMobile(String mobile){
      this.mobile = mobile;
    }


    public String getEmail(){
      return email;
    }
    
    public void setEmail(String email){
      this.email = email;
    }


    public String getIdNumber(){
      return idNumber;
    }
    
    public void setIdNumber(String idNumber){
      this.idNumber = idNumber;
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


    public int getLockFlag(){
      return lockFlag;
    }
    
    public void setLockFlag(int lockFlag){
      this.lockFlag = lockFlag;
    }


    public int getSysFlag(){
      return sysFlag;
    }
    
    public void setSysFlag(int sysFlag){
      this.sysFlag = sysFlag;
    }


    public int getDeleteFlag(){
      return deleteFlag;
    }
    
    public void setDeleteFlag(int deleteFlag){
      this.deleteFlag = deleteFlag;
    }


    public long getData1(){
      return data1;
    }
    
    public void setData1(long data1){
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
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"userName:"+userName+"\t"+"account:"+account+"\t"+"password:"+password+"\t"+"mobile:"+mobile+"\t"+"email:"+email+"\t"+"idNumber:"+idNumber+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"lockFlag:"+lockFlag+"\t"+"sysFlag:"+sysFlag+"\t"+"deleteFlag:"+deleteFlag+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3;
    }
}