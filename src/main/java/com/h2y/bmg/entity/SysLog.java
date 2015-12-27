package com.h2y.bmg.entity;

import java.util.Date;

/**
 * SysLog Model create
 * @author hwttnet
 * version:1.2
 * time:2014-11-12
 * email:info@hwttnet.com
 */

public class SysLog{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keySysLog";
    private long id;
    private long userId;
    private String userName;
    private String account;
    private String ip;
    private String moduleName;
    private Date operateDate;
    private String operateType;
    private String operateResult;
    private String operateOs;
    private String operateBrowser;
    private String memo;
    private long unitId;
    private String businessId;
    private String tableName;
    private String reserve1;
    private String reserve2;

	public SysLog(){
		super();
	}

	public SysLog(long id){
		super();
		this.id = id;
	}

	public SysLog(long id,long userId,String userName,String account,String ip,String moduleName,Date operateDate,String operateType,String operateResult,String operateOs,String operateBrowser,String memo,long unitId,String businessId,String tableName,String reserve1,String reserve2){
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.account = account;
		this.ip = ip;
		this.moduleName = moduleName;
		this.operateDate = operateDate;
		this.operateType = operateType;
		this.operateResult = operateResult;
		this.operateOs = operateOs;
		this.operateBrowser = operateBrowser;
		this.memo = memo;
		this.unitId = unitId;
		this.businessId = businessId;
		this.tableName = tableName;
		this.reserve1 = reserve1;
		this.reserve2 = reserve2;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getUserId(){
      return userId;
    }
    
    public void setUserId(long userId){
      this.userId = userId;
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


    public String getIp(){
      return ip;
    }
    
    public void setIp(String ip){
      this.ip = ip;
    }


    public String getModuleName(){
      return moduleName;
    }
    
    public void setModuleName(String moduleName){
      this.moduleName = moduleName;
    }


    public Date getOperateDate(){
      return operateDate;
    }
    
    public void setOperateDate(Date operateDate){
      this.operateDate = operateDate;
    }


    public String getOperateType(){
      return operateType;
    }
    
    public void setOperateType(String operateType){
      this.operateType = operateType;
    }


    public String getOperateResult(){
      return operateResult;
    }
    
    public void setOperateResult(String operateResult){
      this.operateResult = operateResult;
    }


    public String getOperateOs(){
      return operateOs;
    }
    
    public void setOperateOs(String operateOs){
      this.operateOs = operateOs;
    }


    public String getOperateBrowser(){
      return operateBrowser;
    }
    
    public void setOperateBrowser(String operateBrowser){
      this.operateBrowser = operateBrowser;
    }


    public String getMemo(){
      return memo;
    }
    
    public void setMemo(String memo){
      this.memo = memo;
    }


    public long getUnitId(){
      return unitId;
    }
    
    public void setUnitId(long unitId){
      this.unitId = unitId;
    }


    public String getBusinessId(){
      return businessId;
    }
    
    public void setBusinessId(String businessId){
      this.businessId = businessId;
    }


    public String getTableName(){
      return tableName;
    }
    
    public void setTableName(String tableName){
      this.tableName = tableName;
    }


    public String getReserve1(){
      return reserve1;
    }
    
    public void setReserve1(String reserve1){
      this.reserve1 = reserve1;
    }


    public String getReserve2(){
      return reserve2;
    }
    
    public void setReserve2(String reserve2){
      this.reserve2 = reserve2;
    }

    public String toString(){
	return "id:"+id+"\t"+"userId:"+userId+"\t"+"userName:"+userName+"\t"+"account:"+account+"\t"+"ip:"+ip+"\t"+"moduleName:"+moduleName+"\t"+"operateDate:"+operateDate+"\t"+"operateType:"+operateType+"\t"+"operateResult:"+operateResult+"\t"+"operateOs:"+operateOs+"\t"+"operateBrowser:"+operateBrowser+"\t"+"memo:"+memo+"\t"+"unitId:"+unitId+"\t"+"businessId:"+businessId+"\t"+"tableName:"+tableName+"\t"+"reserve1:"+reserve1+"\t"+"reserve2:"+reserve2;
    }
}