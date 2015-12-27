package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * SysUser Model create
 * @author hwttnet
 * version:1.2
 * time:2014-10-19
 * email:info@hwttnet.com
 */

public class SysUser extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keySysUser";
    private long id;
    private long unitId;
    private String userName;
    private String account;
    private String password;
    private String mobile;
    private String email;
    private String userCord;
    private Date updateDate;
    private long ifLock;
    private long ifSys;
    private long ifDelete;
    private long userOrd;
    private long reverse1;
    private String reverse2;
    private String reverse3;
    private String xghusercode;
    private Date xghcreatedate;

	public SysUser(){
		super();
	}

	public SysUser(long id){
		super();
		this.id = id;
	}

	public SysUser(long id,long unitId,String userName,String account,String password,String mobile,String email,String userCord,Date updateDate,long ifLock,long ifSys,long ifDelete,long userOrd,long reverse1,String reverse2,String reverse3,String xghusercode,Date xghcreatedate){
		super();
		this.id = id;
		this.unitId = unitId;
		this.userName = userName;
		this.account = account;
		this.password = password;
		this.mobile = mobile;
		this.email = email;
		this.userCord = userCord;
		this.updateDate = updateDate;
		this.ifLock = ifLock;
		this.ifSys = ifSys;
		this.ifDelete = ifDelete;
		this.userOrd = userOrd;
		this.reverse1 = reverse1;
		this.reverse2 = reverse2;
		this.reverse3 = reverse3;
		this.xghusercode = xghusercode;
		this.xghcreatedate = xghcreatedate;
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


    public String getUserCord(){
      return userCord;
    }
    
    public void setUserCord(String userCord){
      this.userCord = userCord;
    }


    public Date getUpdateDate(){
      return updateDate;
    }
    
    public void setUpdateDate(Date updateDate){
      this.updateDate = updateDate;
    }


    public long getIfLock(){
      return ifLock;
    }
    
    public void setIfLock(long ifLock){
      this.ifLock = ifLock;
    }


    public long getIfSys(){
      return ifSys;
    }
    
    public void setIfSys(long ifSys){
      this.ifSys = ifSys;
    }


    public long getIfDelete(){
      return ifDelete;
    }
    
    public void setIfDelete(long ifDelete){
      this.ifDelete = ifDelete;
    }


    public long getUserOrd(){
      return userOrd;
    }
    
    public void setUserOrd(long userOrd){
      this.userOrd = userOrd;
    }


    public long getReverse1(){
      return reverse1;
    }
    
    public void setReverse1(long reverse1){
      this.reverse1 = reverse1;
    }


    public String getReverse2(){
      return reverse2;
    }
    
    public void setReverse2(String reverse2){
      this.reverse2 = reverse2;
    }


    public String getReverse3(){
      return reverse3;
    }
    
    public void setReverse3(String reverse3){
      this.reverse3 = reverse3;
    }


    public String getXghusercode(){
      return xghusercode;
    }
    
    public void setXghusercode(String xghusercode){
      this.xghusercode = xghusercode;
    }


    public Date getXghcreatedate(){
      return xghcreatedate;
    }
    
    public void setXghcreatedate(Date xghcreatedate){
      this.xghcreatedate = xghcreatedate;
    }

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"userName:"+userName+"\t"+"account:"+account+"\t"+"password:"+password+"\t"+"mobile:"+mobile+"\t"+"email:"+email+"\t"+"userCord:"+userCord+"\t"+"updateDate:"+updateDate+"\t"+"ifLock:"+ifLock+"\t"+"ifSys:"+ifSys+"\t"+"ifDelete:"+ifDelete+"\t"+"userOrd:"+userOrd+"\t"+"reverse1:"+reverse1+"\t"+"reverse2:"+reverse2+"\t"+"reverse3:"+reverse3+"\t"+"xghusercode:"+xghusercode+"\t"+"xghcreatedate:"+xghcreatedate;
    }
}