package com.h2y.jxc.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 进销存 单据审核日志 Model 
 * @author hwttnet
 * version:1.2
 * time:2015-08-03
 */

public class JxcAuditLog extends BaseObject{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyJxcAuditLog";
    private long id;
    private long billId;
    private String billNo;
    private int auditStage;
    private String auditProcess;
    private long handlerId;
    private String handler;
    private Date createDate;
    private String data1;
    private String data2;
    private String data3;
    private int data4;
    private int data5;
    private int data6;
    private long data7;
    private long data8;
    private long data9;
    private Date data10;

	public JxcAuditLog(){
		super();
	}

	public JxcAuditLog(long id){
		super();
		this.id = id;
	}

	public JxcAuditLog(long id,long billId,String billNo,int auditStage,String auditProcess,long handlerId,String handler,Date createDate,String data1,String data2,String data3,int data4,int data5,int data6,long data7,long data8,long data9,Date data10){
		super();
		this.id = id;
		this.billId = billId;
		this.billNo = billNo;
		this.auditStage = auditStage;
		this.auditProcess = auditProcess;
		this.handlerId = handlerId;
		this.handler = handler;
		this.createDate = createDate;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
		this.data4 = data4;
		this.data5 = data5;
		this.data6 = data6;
		this.data7 = data7;
		this.data8 = data8;
		this.data9 = data9;
		this.data10 = data10;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getBillId(){
      return billId;
    }
    
    public void setBillId(long billId){
      this.billId = billId;
    }


    public String getBillNo(){
      return billNo;
    }
    
    public void setBillNo(String billNo){
      this.billNo = billNo;
    }


    public int getAuditStage(){
      return auditStage;
    }
    
    public void setAuditStage(int auditStage){
      this.auditStage = auditStage;
    }


    public String getAuditProcess(){
      return auditProcess;
    }
    
    public void setAuditProcess(String auditProcess){
      this.auditProcess = auditProcess;
    }


    public long getHandlerId(){
      return handlerId;
    }
    
    public void setHandlerId(long handlerId){
      this.handlerId = handlerId;
    }


    public String getHandler(){
      return handler;
    }
    
    public void setHandler(String handler){
      this.handler = handler;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
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


    public int getData4(){
      return data4;
    }
    
    public void setData4(int data4){
      this.data4 = data4;
    }


    public int getData5(){
      return data5;
    }
    
    public void setData5(int data5){
      this.data5 = data5;
    }


    public int getData6(){
      return data6;
    }
    
    public void setData6(int data6){
      this.data6 = data6;
    }


    public long getData7(){
      return data7;
    }
    
    public void setData7(long data7){
      this.data7 = data7;
    }


    public long getData8(){
      return data8;
    }
    
    public void setData8(long data8){
      this.data8 = data8;
    }


    public long getData9(){
      return data9;
    }
    
    public void setData9(long data9){
      this.data9 = data9;
    }


    public Date getData10(){
      return data10;
    }
    
    public void setData10(Date data10){
      this.data10 = data10;
    }

    public String toString(){
	return "id:"+id+"\t"+"billId:"+billId+"\t"+"billNo:"+billNo+"\t"+"auditStage:"+auditStage+"\t"+"auditProcess:"+auditProcess+"\t"+"handlerId:"+handlerId+"\t"+"handler:"+handler+"\t"+"createDate:"+createDate+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4+"\t"+"data5:"+data5+"\t"+"data6:"+data6+"\t"+"data7:"+data7+"\t"+"data8:"+data8+"\t"+"data9:"+data9+"\t"+"data10:"+data10;
    }
}