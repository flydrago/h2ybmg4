package com.h2y.bmg.entity;

import java.util.Date;

/**
 * TraceNumberSection Model create
 * @author hwttnet
 * version:1.2
 * time:2015-06-30
 * email:info@hwttnet.com
 */

public class TraceNumberSection{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final Long serialVersionUID = 1L;	
    public static final String key = "keyTraceNumberSection";
    private Long id;
    private Long fromId;
    private Long toId;
    private Long parentId;
    private Long startNo;
    private Long endNo;
    private Integer spec;
    private Integer ifReceive;
    private Integer ifEnable;
    private Integer ifCreate;
    private Date createDate;
    private Long optUserId;
    private String optUserAccount;
    private String optUserName;
    private Integer optFlag;
    private String memo;
    private String data1;
    private String data2;
    private String data3;

	public TraceNumberSection(){
		super();
	}

	public TraceNumberSection(Long id){
		super();
		this.id = id;
	}

	public TraceNumberSection(Long id,Long fromId,Long toId,Long parentId,Long startNo,Long endNo,Integer spec,Integer ifReceive,Integer ifEnable,Integer ifCreate,Date createDate,Long optUserId,String optUserAccount,String optUserName,Integer optFlag,String memo,String data1,String data2,String data3){
		super();
		this.id = id;
		this.fromId = fromId;
		this.toId = toId;
		this.parentId = parentId;
		this.startNo = startNo;
		this.endNo = endNo;
		this.spec = spec;
		this.ifReceive = ifReceive;
		this.ifEnable = ifEnable;
		this.ifCreate = ifCreate;
		this.createDate = createDate;
		this.optUserId = optUserId;
		this.optUserAccount = optUserAccount;
		this.optUserName = optUserName;
		this.optFlag = optFlag;
		this.memo = memo;
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

    public Long getFromId(){
      return fromId;
    }
    
    public void setFromId(Long fromId){
      this.fromId = fromId;
    }


    public Long getToId(){
      return toId;
    }
    
    public void setToId(Long toId){
      this.toId = toId;
    }


    public Long getParentId(){
      return parentId;
    }
    
    public void setParentId(Long parentId){
      this.parentId = parentId;
    }


    public Long getStartNo(){
      return startNo;
    }
    
    public void setStartNo(Long startNo){
      this.startNo = startNo;
    }


    public Long getEndNo(){
      return endNo;
    }
    
    public void setEndNo(Long endNo){
      this.endNo = endNo;
    }


    public Integer getSpec(){
      return spec;
    }
    
    public void setSpec(Integer spec){
      this.spec = spec;
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


    public Integer getIfCreate(){
      return ifCreate;
    }
    
    public void setIfCreate(Integer ifCreate){
      this.ifCreate = ifCreate;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
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


    public Integer getOptFlag(){
      return optFlag;
    }
    
    public void setOptFlag(Integer optFlag){
      this.optFlag = optFlag;
    }


    public String getMemo(){
      return memo;
    }
    
    public void setMemo(String memo){
      this.memo = memo;
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
	return "id:"+id+"\t"+"fromId:"+fromId+"\t"+"toId:"+toId+"\t"+"parentId:"+parentId+"\t"+"startNo:"+startNo+"\t"+"endNo:"+endNo+"\t"+"spec:"+spec+"\t"+"ifReceive:"+ifReceive+"\t"+"ifEnable:"+ifEnable+"\t"+"ifCreate:"+ifCreate+"\t"+"createDate:"+createDate+"\t"+"optUserId:"+optUserId+"\t"+"optUserAccount:"+optUserAccount+"\t"+"optUserName:"+optUserName+"\t"+"optFlag:"+optFlag+"\t"+"memo:"+memo+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3;
    }
}