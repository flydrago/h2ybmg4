package com.h2y.bmg.entity;

import com.h2y.object.BaseObject;


/**
 * SysPrivilegeList Model create
 * @author hwttnet
 * version:1.2
 * time:2014-10-20
 * email:info@hwttnet.com
 */

public class SysPrivilegeList extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keySysPrivilegeList";
    private long id;
    private long unitId;
    private String privilegeMaster;
    private long privilegeMasterValue;
    private String privilegeAccess;
    private long privilegeAccessValue;

	public SysPrivilegeList(){
		super();
	}

	public SysPrivilegeList(long id){
		super();
		this.id = id;
	}

	public SysPrivilegeList(long id,long unitId,String privilegeMaster,long privilegeMasterValue,String privilegeAccess,long privilegeAccessValue){
		super();
		this.id = id;
		this.unitId = unitId;
		this.privilegeMaster = privilegeMaster;
		this.privilegeMasterValue = privilegeMasterValue;
		this.privilegeAccess = privilegeAccess;
		this.privilegeAccessValue = privilegeAccessValue;
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


    public String getPrivilegeMaster(){
      return privilegeMaster;
    }
    
    public void setPrivilegeMaster(String privilegeMaster){
      this.privilegeMaster = privilegeMaster;
    }


    public long getPrivilegeMasterValue(){
      return privilegeMasterValue;
    }
    
    public void setPrivilegeMasterValue(long privilegeMasterValue){
      this.privilegeMasterValue = privilegeMasterValue;
    }


    public String getPrivilegeAccess(){
      return privilegeAccess;
    }
    
    public void setPrivilegeAccess(String privilegeAccess){
      this.privilegeAccess = privilegeAccess;
    }


    public long getPrivilegeAccessValue(){
      return privilegeAccessValue;
    }
    
    public void setPrivilegeAccessValue(long privilegeAccessValue){
      this.privilegeAccessValue = privilegeAccessValue;
    }

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"privilegeMaster:"+privilegeMaster+"\t"+"privilegeMasterValue:"+privilegeMasterValue+"\t"+"privilegeAccess:"+privilegeAccess+"\t"+"privilegeAccessValue:"+privilegeAccessValue;
    }
}