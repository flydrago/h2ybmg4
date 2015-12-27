package com.h2y.bmg.entity;

import java.util.Date;

/**
 * TraceQrcodeSerial Model create
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 */

public class TraceQrcodeSerial{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyTraceQrcodeSerial";
    private Long id;
    private Long parentId;
    private Long boxQrcodeNo;
    private Long bottleQrcodeNo;
    private Long provinceId;
    private Long privideId;
    private String privideName;
    private Integer ifReceive;
    private Integer ifEnable;
    private Integer relateFlag;
    private Date createDate;
    private Date updateDate;
    private Long optUserId;
    private String optUserAccount;
    private String optUserName;
    private String data1;
    private String data2;
    private String data3;

	public TraceQrcodeSerial(){
		super();
	}

	public TraceQrcodeSerial(Long id){
		super();
		this.id = id;
	}

	public TraceQrcodeSerial(Long id,Long parentId,Long boxQrcodeNo,Long bottleQrcodeNo,Long provinceId,Long privideId,String privideName,Integer ifReceive,Integer ifEnable,Integer relateFlag,Date createDate,Date updateDate,Long optUserId,String optUserAccount,String optUserName,String data1,String data2,String data3){
		super();
		this.id = id;
		this.parentId = parentId;
		this.boxQrcodeNo = boxQrcodeNo;
		this.bottleQrcodeNo = bottleQrcodeNo;
		this.provinceId = provinceId;
		this.privideId = privideId;
		this.privideName = privideName;
		this.ifReceive = ifReceive;
		this.ifEnable = ifEnable;
		this.relateFlag = relateFlag;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.optUserId = optUserId;
		this.optUserAccount = optUserAccount;
		this.optUserName = optUserName;
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

    public Long getParentId(){
      return parentId;
    }
    
    public void setParentId(Long parentId){
      this.parentId = parentId;
    }


    public Long getBoxQrcodeNo(){
      return boxQrcodeNo;
    }
    
    public void setBoxQrcodeNo(Long boxQrcodeNo){
      this.boxQrcodeNo = boxQrcodeNo;
    }


    public Long getBottleQrcodeNo(){
      return bottleQrcodeNo;
    }
    
    public void setBottleQrcodeNo(Long bottleQrcodeNo){
      this.bottleQrcodeNo = bottleQrcodeNo;
    }


    public Long getProvinceId(){
      return provinceId;
    }
    
    public void setProvinceId(Long provinceId){
      this.provinceId = provinceId;
    }


    public Long getPrivideId(){
      return privideId;
    }
    
    public void setPrivideId(Long privideId){
      this.privideId = privideId;
    }


    public String getPrivideName(){
      return privideName;
    }
    
    public void setPrivideName(String privideName){
      this.privideName = privideName;
    }


    public Integer getIfReceive(){
      return ifReceive;
    }
    
    public void setIfReceive(Integer ifReceive){
      this.ifReceive = ifReceive;
    }


    public Integer getIfEnable(){
      return ifEnable;
    }
    
    public void setIfEnable(Integer ifEnable){
      this.ifEnable = ifEnable;
    }


    public Integer getRelateFlag(){
      return relateFlag;
    }
    
    public void setRelateFlag(Integer relateFlag){
      this.relateFlag = relateFlag;
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
	return "id:"+id+"\t"+"parentId:"+parentId+"\t"+"boxQrcodeNo:"+boxQrcodeNo+"\t"+"bottleQrcodeNo:"+bottleQrcodeNo+"\t"+"provinceId:"+provinceId+"\t"+"privideId:"+privideId+"\t"+"privideName:"+privideName+"\t"+"ifReceive:"+ifReceive+"\t"+"ifEnable:"+ifEnable+"\t"+"relateFlag:"+relateFlag+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"optUserId:"+optUserId+"\t"+"optUserAccount:"+optUserAccount+"\t"+"optUserName:"+optUserName+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3;
    }
}