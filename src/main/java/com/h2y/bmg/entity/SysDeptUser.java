package com.h2y.bmg.entity;

import com.h2y.object.BaseObject;


/**
 * SysDeptUser Model create
 * @author hwttnet
 * version:1.2
 * time:2014-10-19
 * email:info@hwttnet.com
 */

public class SysDeptUser extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keySysDeptUser";
    private long id;
    private long deptId;
    private String deptCode;
    private long userId;
    private long duOrd;
    private long userLevel;

	public SysDeptUser(){
		super();
	}

	public SysDeptUser(long id){
		super();
		this.id = id;
	}

	public SysDeptUser(long id,long deptId,String deptCode,long userId,long duOrd,long userLevel){
		super();
		this.id = id;
		this.deptId = deptId;
		this.deptCode = deptCode;
		this.userId = userId;
		this.duOrd = duOrd;
		this.userLevel = userLevel;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getDeptId(){
      return deptId;
    }
    
    public void setDeptId(long deptId){
      this.deptId = deptId;
    }


    public String getDeptCode(){
      return deptCode;
    }
    
    public void setDeptCode(String deptCode){
      this.deptCode = deptCode;
    }


    public long getUserId(){
      return userId;
    }
    
    public void setUserId(long userId){
      this.userId = userId;
    }


    public long getDuOrd(){
      return duOrd;
    }
    
    public void setDuOrd(long duOrd){
      this.duOrd = duOrd;
    }


    public long getUserLevel(){
      return userLevel;
    }
    
    public void setUserLevel(long userLevel){
      this.userLevel = userLevel;
    }

    public String toString(){
	return "id:"+id+"\t"+"deptId:"+deptId+"\t"+"deptCode:"+deptCode+"\t"+"userId:"+userId+"\t"+"duOrd:"+duOrd+"\t"+"userLevel:"+userLevel;
    }
}