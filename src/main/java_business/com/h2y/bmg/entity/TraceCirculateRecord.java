package com.h2y.bmg.entity;

import java.util.Date;

/**
 * TraceCirculateRecord Model create
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 */

public class TraceCirculateRecord{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyTraceCirculateRecord";
    private Long id;
    private Long boxQrcodeNo;
    private Long bottleQrcodeNo;
    private Integer packFlag;
    private Integer spec;
    private Date createDate;
    private Integer circulateFlag;
    private Integer showFlag;
    private Integer newRecordFlag;
    private Long optUserId;
    private String optUserAccount;
    private String optUserName;
    private String data1;
    private String data2;
    private String data3;

	public TraceCirculateRecord(){
		super();
	}

	public TraceCirculateRecord(Long id){
		super();
		this.id = id;
	}

	public TraceCirculateRecord(Long id,Long boxQrcodeNo,Long bottleQrcodeNo,Integer packFlag,Integer spec,Date createDate,Integer circulateFlag,Integer showFlag,Integer newRecordFlag,Long optUserId,String optUserAccount,String optUserName,String data1,String data2,String data3){
		super();
		this.id = id;
		this.boxQrcodeNo = boxQrcodeNo;
		this.bottleQrcodeNo = bottleQrcodeNo;
		this.packFlag = packFlag;
		this.spec = spec;
		this.createDate = createDate;
		this.circulateFlag = circulateFlag;
		this.showFlag = showFlag;
		this.newRecordFlag = newRecordFlag;
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


    public Integer getPackFlag(){
      return packFlag;
    }
    
    public void setPackFlag(Integer packFlag){
      this.packFlag = packFlag;
    }


    public Integer getSpec(){
      return spec;
    }
    
    public void setSpec(Integer spec){
      this.spec = spec;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public Integer getCirculateFlag(){
      return circulateFlag;
    }
    
    public void setCirculateFlag(Integer circulateFlag){
      this.circulateFlag = circulateFlag;
    }


    public Integer getShowFlag(){
      return showFlag;
    }
    
    public void setShowFlag(Integer showFlag){
      this.showFlag = showFlag;
    }


    public Integer getNewRecordFlag(){
      return newRecordFlag;
    }
    
    public void setNewRecordFlag(Integer newRecordFlag){
      this.newRecordFlag = newRecordFlag;
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
	return "id:"+id+"\t"+"boxQrcodeNo:"+boxQrcodeNo+"\t"+"bottleQrcodeNo:"+bottleQrcodeNo+"\t"+"packFlag:"+packFlag+"\t"+"spec:"+spec+"\t"+"createDate:"+createDate+"\t"+"circulateFlag:"+circulateFlag+"\t"+"showFlag:"+showFlag+"\t"+"newRecordFlag:"+newRecordFlag+"\t"+"optUserId:"+optUserId+"\t"+"optUserAccount:"+optUserAccount+"\t"+"optUserName:"+optUserName+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3;
    }
}