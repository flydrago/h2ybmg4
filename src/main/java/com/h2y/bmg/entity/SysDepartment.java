package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;


/**
 * SysDepartment Model create
 * @author hwttnet
 * version:1.2
 * time:2014-10-23
 * email:info@hwttnet.com
 */

public class SysDepartment extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keySysDepartment";
    private long id;
    private String deptCode;
    private long unitId;
    private long parentId;
    private long deptLeader;
    private String deptName;
    private String deptShortName;
    private String deptDesc;
    private long deptOrd;
    private long deptType;
    private long ifDelete;
    private String position;
    private Double gpsLongitude;
    private Double gpsLatitude;
    private String reverse1;
    private String reverse2;
    private long ifHasShop;
    private String xghdcode;
    private Date xghcreatedate;

	public SysDepartment(){
		super();
	}

	public SysDepartment(long id){
		super();
		this.id = id;
	}

	public SysDepartment(long id,String deptCode,long unitId,long parentId,long deptLeader,String deptName,String deptShortName,String deptDesc,long deptOrd,long deptType,long ifDelete,String position,Double gpsLongitude,Double gpsLatitude,String reverse1,String reverse2,long ifHasShop,String xghdcode,Date xghcreatedate){
		super();
		this.id = id;
		this.deptCode = deptCode;
		this.unitId = unitId;
		this.parentId = parentId;
		this.deptLeader = deptLeader;
		this.deptName = deptName;
		this.deptShortName = deptShortName;
		this.deptDesc = deptDesc;
		this.deptOrd = deptOrd;
		this.deptType = deptType;
		this.ifDelete = ifDelete;
		this.position = position;
		this.gpsLongitude = gpsLongitude;
		this.gpsLatitude = gpsLatitude;
		this.reverse1 = reverse1;
		this.reverse2 = reverse2;
		this.ifHasShop = ifHasShop;
		this.xghdcode = xghdcode;
		this.xghcreatedate = xghcreatedate;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public String getDeptCode(){
      return deptCode;
    }
    
    public void setDeptCode(String deptCode){
      this.deptCode = deptCode;
    }


    public long getUnitId(){
      return unitId;
    }
    
    public void setUnitId(long unitId){
      this.unitId = unitId;
    }


    public long getParentId(){
      return parentId;
    }
    
    public void setParentId(long parentId){
      this.parentId = parentId;
    }


    public long getDeptLeader(){
      return deptLeader;
    }
    
    public void setDeptLeader(long deptLeader){
      this.deptLeader = deptLeader;
    }


    public String getDeptName(){
      return deptName;
    }
    
    public void setDeptName(String deptName){
      this.deptName = deptName;
    }


    public String getDeptShortName(){
      return deptShortName;
    }
    
    public void setDeptShortName(String deptShortName){
      this.deptShortName = deptShortName;
    }


    public String getDeptDesc(){
      return deptDesc;
    }
    
    public void setDeptDesc(String deptDesc){
      this.deptDesc = deptDesc;
    }


    public long getDeptOrd(){
      return deptOrd;
    }
    
    public void setDeptOrd(long deptOrd){
      this.deptOrd = deptOrd;
    }


    public long getDeptType(){
      return deptType;
    }
    
    public void setDeptType(long deptType){
      this.deptType = deptType;
    }


    public long getIfDelete(){
      return ifDelete;
    }
    
    public void setIfDelete(long ifDelete){
      this.ifDelete = ifDelete;
    }


    public String getPosition(){
      return position;
    }
    
    public void setPosition(String position){
      this.position = position;
    }


    public Double getGpsLongitude(){
      return gpsLongitude;
    }
    
    public void setGpsLongitude(Double gpsLongitude){
      this.gpsLongitude = gpsLongitude;
    }


    public Double getGpsLatitude(){
      return gpsLatitude;
    }
    
    public void setGpsLatitude(Double gpsLatitude){
      this.gpsLatitude = gpsLatitude;
    }


    public String getReverse1(){
      return reverse1;
    }
    
    public void setReverse1(String reverse1){
      this.reverse1 = reverse1;
    }


    public String getReverse2(){
      return reverse2;
    }
    
    public void setReverse2(String reverse2){
      this.reverse2 = reverse2;
    }


    public long getIfHasShop(){
      return ifHasShop;
    }
    
    public void setIfHasShop(long ifHasShop){
      this.ifHasShop = ifHasShop;
    }


    public String getXghdcode(){
      return xghdcode;
    }
    
    public void setXghdcode(String xghdcode){
      this.xghdcode = xghdcode;
    }


    public Date getXghcreatedate(){
      return xghcreatedate;
    }
    
    public void setXghcreatedate(Date xghcreatedate){
      this.xghcreatedate = xghcreatedate;
    }

    public String toString(){
	return "id:"+id+"\t"+"deptCode:"+deptCode+"\t"+"unitId:"+unitId+"\t"+"parentId:"+parentId+"\t"+"deptLeader:"+deptLeader+"\t"+"deptName:"+deptName+"\t"+"deptShortName:"+deptShortName+"\t"+"deptDesc:"+deptDesc+"\t"+"deptOrd:"+deptOrd+"\t"+"deptType:"+deptType+"\t"+"ifDelete:"+ifDelete+"\t"+"position:"+position+"\t"+"gpsLongitude:"+gpsLongitude+"\t"+"gpsLatitude:"+gpsLatitude+"\t"+"reverse1:"+reverse1+"\t"+"reverse2:"+reverse2+"\t"+"ifHasShop:"+ifHasShop+"\t"+"xghdcode:"+xghdcode+"\t"+"xghcreatedate:"+xghcreatedate;
    }
}