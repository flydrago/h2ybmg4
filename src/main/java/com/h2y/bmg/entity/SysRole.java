package com.h2y.bmg.entity;

import com.h2y.object.BaseObject;


/**
 * SysRole Model create
 * @author hwttnet
 * version:1.2
 * time:2014-10-20
 * email:info@hwttnet.com
 */

public class SysRole extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keySysRole";
    private long id;
    private long unitId;
    private String roleCode;
    private String roleName;
    private String roleDesc;
    private long ifSys;
    private long ifPrivilege;
    private long ifDelete;
    private long roleOrd;

	public SysRole(){
		super();
	}

	public SysRole(long id){
		super();
		this.id = id;
	}

	public SysRole(long id,long unitId,String roleCode,String roleName,String roleDesc,long ifSys,long ifPrivilege,long ifDelete,long roleOrd){
		super();
		this.id = id;
		this.unitId = unitId;
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.ifSys = ifSys;
		this.ifPrivilege = ifPrivilege;
		this.ifDelete = ifDelete;
		this.roleOrd = roleOrd;
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


    public String getRoleCode(){
      return roleCode;
    }
    
    public void setRoleCode(String roleCode){
      this.roleCode = roleCode;
    }


    public String getRoleName(){
      return roleName;
    }
    
    public void setRoleName(String roleName){
      this.roleName = roleName;
    }


    public String getRoleDesc(){
      return roleDesc;
    }
    
    public void setRoleDesc(String roleDesc){
      this.roleDesc = roleDesc;
    }


    public long getIfSys(){
      return ifSys;
    }
    
    public void setIfSys(long ifSys){
      this.ifSys = ifSys;
    }


    public long getIfPrivilege(){
      return ifPrivilege;
    }
    
    public void setIfPrivilege(long ifPrivilege){
      this.ifPrivilege = ifPrivilege;
    }


    public long getIfDelete(){
      return ifDelete;
    }
    
    public void setIfDelete(long ifDelete){
      this.ifDelete = ifDelete;
    }

    public long getRoleOrd() {
		return roleOrd;
	}

	public void setRoleOrd(long roleOrd) {
		this.roleOrd = roleOrd;
	}

	public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"roleCode:"+roleCode+"\t"+"roleName:"+roleName+"\t"+"roleDesc:"+roleDesc+"\t"+"ifSys:"+ifSys+"\t"+"ifPrivilege:"+ifPrivilege+"\t"+"ifDelete:"+ifDelete+"\t"+"roleOrd:"+roleOrd;
    }
}