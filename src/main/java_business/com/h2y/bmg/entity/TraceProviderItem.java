package com.h2y.bmg.entity;

import java.util.Date;

/**
 * TraceProviderItem Model create
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 */

public class TraceProviderItem{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyTraceProviderItem";
    private Long id;
    private String providerName;
    private String legalUser;
    private String contactTelephone;
    private String companyAddress;
    private String beneficiaryAccount;
    private Date createDate;
    private Date updateDate;
    private Long optUserId;
    private String optUserAccount;
    private String optUserName;
    private Integer ifEnable;
    private Long parentId;
    private String zoneCode;
    private String data1;
    private String data2;
    private String data3;

	public TraceProviderItem(){
		super();
	}

	public TraceProviderItem(Long id){
		super();
		this.id = id;
	}

	public TraceProviderItem(Long id,String providerName,String legalUser,String contactTelephone,String companyAddress,String beneficiaryAccount,Date createDate,Date updateDate,Long optUserId,String optUserAccount,String optUserName,Integer ifEnable,Long parentId,String zoneCode,String data1,String data2,String data3){
		super();
		this.id = id;
		this.providerName = providerName;
		this.legalUser = legalUser;
		this.contactTelephone = contactTelephone;
		this.companyAddress = companyAddress;
		this.beneficiaryAccount = beneficiaryAccount;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.optUserId = optUserId;
		this.optUserAccount = optUserAccount;
		this.optUserName = optUserName;
		this.ifEnable = ifEnable;
		this.parentId = parentId;
		this.zoneCode = zoneCode;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
	}
  
    public Long getId(){
      return id;
    }
    
    public void setId(Long id){
      this.id = id;
    }

    public String getProviderName(){
      return providerName;
    }
    
    public void setProviderName(String providerName){
      this.providerName = providerName;
    }


    public String getLegalUser(){
      return legalUser;
    }
    
    public void setLegalUser(String legalUser){
      this.legalUser = legalUser;
    }


    public String getContactTelephone(){
      return contactTelephone;
    }
    
    public void setContactTelephone(String contactTelephone){
      this.contactTelephone = contactTelephone;
    }


    public String getCompanyAddress(){
      return companyAddress;
    }
    
    public void setCompanyAddress(String companyAddress){
      this.companyAddress = companyAddress;
    }


    public String getBeneficiaryAccount(){
      return beneficiaryAccount;
    }
    
    public void setBeneficiaryAccount(String beneficiaryAccount){
      this.beneficiaryAccount = beneficiaryAccount;
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


    public Long getOptUserId(){
      return optUserId;
    }
    
    public void setOptUserId(Long optUserId){
      this.optUserId = optUserId;
    }


    public String getOptUserAccount(){
      return optUserAccount;
    }
    
    public void setOptUserAccount(String optUserAccount){
      this.optUserAccount = optUserAccount;
    }


    public String getOptUserName(){
      return optUserName;
    }
    
    public void setOptUserName(String optUserName){
      this.optUserName = optUserName;
    }


    public Integer getIfEnable(){
      return ifEnable;
    }
    
    public void setIfEnable(Integer ifEnable){
      this.ifEnable = ifEnable;
    }


    public Long getParentId(){
      return parentId;
    }
    
    public void setParentId(Long parentId){
      this.parentId = parentId;
    }


    public String getZoneCode(){
      return zoneCode;
    }
    
    public void setZoneCode(String zoneCode){
      this.zoneCode = zoneCode;
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
	return "id:"+id+"\t"+"providerName:"+providerName+"\t"+"legalUser:"+legalUser+"\t"+"contactTelephone:"+contactTelephone+"\t"+"companyAddress:"+companyAddress+"\t"+"beneficiaryAccount:"+beneficiaryAccount+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"optUserId:"+optUserId+"\t"+"optUserAccount:"+optUserAccount+"\t"+"optUserName:"+optUserName+"\t"+"ifEnable:"+ifEnable+"\t"+"parentId:"+parentId+"\t"+"zoneCode:"+zoneCode+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3;
    }
}