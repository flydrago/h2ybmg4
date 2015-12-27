package com.h2y.bmg.entity;

import java.util.Date;

/**
 * 
 *   
 * 项目名称：h2ybmg2  
 * 类名称：MSmsHis  
 * 类描述：  短信发送记录查看实体类
 * 创建人：李剑 
 * 创建时间：2015年9月21日 下午4:00:17  
 * 修改人：李剑
 * 修改时间：2015年9月21日 下午4:00:17  
 * 修改备注：  如果你看到这个，那么说明你现在已经在负责我以前的项目了。我感到非常抱歉。愿上帝保佑你。
 * @version
 */

public class MSmsHis{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
//	private static final long serialVersionUID = 1L;	
    public static final String key = "keyMSmsHis";
    private long id;
    private long unitId;
    private long userId;
    private String mobile;
    private String moduleName;
    private Date createDate;
    private Date sendDate;
    private String sendState;
    private String msg;
    private String batchId;
    private String memo;
    private String receiptStatus;
    private Date receiptDate;
    private String data1;
    private String data2;
    private String data3;

	public MSmsHis(){
		super();
	}

	public MSmsHis(long id){
		super();
		this.id = id;
	}

	public MSmsHis(long id,long unitId,long userId,String mobile,String moduleName,Date createDate,Date sendDate,String sendState,String msg,String batchId,String memo,String receiptStatus,Date receiptDate,String data1,String data2,String data3){
		super();
		this.id = id;
		this.unitId = unitId;
		this.userId = userId;
		this.mobile = mobile;
		this.moduleName = moduleName;
		this.createDate = createDate;
		this.sendDate = sendDate;
		this.sendState = sendState;
		this.msg = msg;
		this.batchId = batchId;
		this.memo = memo;
		this.receiptStatus = receiptStatus;
		this.receiptDate = receiptDate;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
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


    public long getUserId(){
      return userId;
    }
    
    public void setUserId(long userId){
      this.userId = userId;
    }


    public String getMobile(){
      return mobile;
    }
    
    public void setMobile(String mobile){
      this.mobile = mobile;
    }


    public String getModuleName(){
      return moduleName;
    }
    
    public void setModuleName(String moduleName){
      this.moduleName = moduleName;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public Date getSendDate(){
      return sendDate;
    }
    
    public void setSendDate(Date sendDate){
      this.sendDate = sendDate;
    }


    public String getSendState(){
      return sendState;
    }
    
    public void setSendState(String sendState){
      this.sendState = sendState;
    }


    public String getMsg(){
      return msg;
    }
    
    public void setMsg(String msg){
      this.msg = msg;
    }


    public String getBatchId(){
      return batchId;
    }
    
    public void setBatchId(String batchId){
      this.batchId = batchId;
    }


    public String getMemo(){
      return memo;
    }
    
    public void setMemo(String memo){
      this.memo = memo;
    }


    public String getReceiptStatus(){
      return receiptStatus;
    }
    
    public void setReceiptStatus(String receiptStatus){
      this.receiptStatus = receiptStatus;
    }


    public Date getReceiptDate(){
      return receiptDate;
    }
    
    public void setReceiptDate(Date receiptDate){
      this.receiptDate = receiptDate;
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
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"userId:"+userId+"\t"+"mobile:"+mobile+"\t"+"moduleName:"+moduleName+"\t"+"createDate:"+createDate+"\t"+"sendDate:"+sendDate+"\t"+"sendState:"+sendState+"\t"+"msg:"+msg+"\t"+"batchId:"+batchId+"\t"+"memo:"+memo+"\t"+"receiptStatus:"+receiptStatus+"\t"+"receiptDate:"+receiptDate+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3;
    }
}