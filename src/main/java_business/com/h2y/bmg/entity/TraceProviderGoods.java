package com.h2y.bmg.entity;

import java.util.Date;

/**
 * TraceProviderGoods Model create
 * @author hwttnet
 * version:1.2
 * time:2015-07-05
 * email:info@hwttnet.com
 */

public class TraceProviderGoods{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyTraceProviderGoods";
    private Long id;
    private Long provideId;
    private Long goodsId;
    private String goodsName;
    private int batchNo;
    private Date privideDate;
    private Date startDate;
    private Date endDate;
    private int ifEnable;
    private Long optUserId;
    private String optUserAccount;
    private String optUserName;
    private String data1;
    private String data2;
    private String data3;

	public TraceProviderGoods(){
		super();
	}

	public TraceProviderGoods(Long id){
		super();
		this.id = id;
	}

	public TraceProviderGoods(Long id,Long provideId,Long goodsId,String goodsName,int batchNo,Date privideDate,Date startDate,Date endDate,int ifEnable,Long optUserId,String optUserAccount,String optUserName,String data1,String data2,String data3){
		super();
		this.id = id;
		this.provideId = provideId;
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.batchNo = batchNo;
		this.privideDate = privideDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.ifEnable = ifEnable;
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

    public Long getProvideId(){
      return provideId;
    }
    
    public void setProvideId(Long provideId){
      this.provideId = provideId;
    }


    public Long getGoodsId(){
      return goodsId;
    }
    
    public void setGoodsId(Long goodsId){
      this.goodsId = goodsId;
    }


    public String getGoodsName(){
      return goodsName;
    }
    
    public void setGoodsName(String goodsName){
      this.goodsName = goodsName;
    }


    public int getBatchNo(){
      return batchNo;
    }
    
    public void setBatchNo(int batchNo){
      this.batchNo = batchNo;
    }


    public Date getPrivideDate(){
      return privideDate;
    }
    
    public void setPrivideDate(Date privideDate){
      this.privideDate = privideDate;
    }


    public Date getStartDate(){
      return startDate;
    }
    
    public void setStartDate(Date startDate){
      this.startDate = startDate;
    }


    public Date getEndDate(){
      return endDate;
    }
    
    public void setEndDate(Date endDate){
      this.endDate = endDate;
    }


    public int getIfEnable(){
      return ifEnable;
    }
    
    public void setIfEnable(int ifEnable){
      this.ifEnable = ifEnable;
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
	return "id:"+id+"\t"+"provideId:"+provideId+"\t"+"goodsId:"+goodsId+"\t"+"goodsName:"+goodsName+"\t"+"batchNo:"+batchNo+"\t"+"privideDate:"+privideDate+"\t"+"startDate:"+startDate+"\t"+"endDate:"+endDate+"\t"+"ifEnable:"+ifEnable+"\t"+"optUserId:"+optUserId+"\t"+"optUserAccount:"+optUserAccount+"\t"+"optUserName:"+optUserName+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3;
    }
}