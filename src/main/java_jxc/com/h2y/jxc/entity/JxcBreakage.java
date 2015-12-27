package com.h2y.jxc.entity;

import java.util.Date;

import com.h2y.jxc.basic.BaseBill;

/**
 * 进销存报损单 Model
 * @author hwttnet
 * version:1.2
 * time:2015-08-24
 * email:info@hwttnet.com
 */

public class JxcBreakage extends BaseBill{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyJxcBreakage";
    private long id;
    private String billNo;
    private String billCustomNo;
    private Date breakageDate;
    private long storageId;
    private String storage;
    private long brokerId;
    private String broker;
    private String excursus;
    private String notes;
    private int breakageCount;
    private Double breakageAmount;
    private int reviseMark;
    private int checkStatus;
    private int billStatus;
    private String billMaker;
    private Date createDate;
    private String zoneCode;
    private String data1;
    private String data2;
    private String data3;
    private String data4;
    private int data5;
    private int data6;
    private int data7;
    private Double data8;
    private Double data9;
    private Double data10;
    private long data11;
    private long data12;

	public JxcBreakage(){
		super();
	}

	public JxcBreakage(long id){
		super();
		this.id = id;
	}

	public JxcBreakage(long id,String billNo,String billCustomNo,Date breakageDate,long storageId,String storage,long brokerId,String broker,String excursus,String notes,int breakageCount,Double breakageAmount,int reviseMark,int checkStatus,int billStatus,String billMaker,Date createDate,String zoneCode,String data1,String data2,String data3,String data4,int data5,int data6,int data7,Double data8,Double data9,Double data10,long data11,long data12){
		super();
		this.id = id;
		this.billNo = billNo;
		this.billCustomNo = billCustomNo;
		this.breakageDate = breakageDate;
		this.storageId = storageId;
		this.storage = storage;
		this.brokerId = brokerId;
		this.broker = broker;
		this.excursus = excursus;
		this.notes = notes;
		this.breakageCount = breakageCount;
		this.breakageAmount = breakageAmount;
		this.reviseMark = reviseMark;
		this.checkStatus = checkStatus;
		this.billStatus = billStatus;
		this.billMaker = billMaker;
		this.createDate = createDate;
		this.zoneCode = zoneCode;
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
		this.data11 = data11;
		this.data12 = data12;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public String getBillNo(){
      return billNo;
    }
    
    public void setBillNo(String billNo){
      this.billNo = billNo;
    }


    public String getBillCustomNo(){
      return billCustomNo;
    }
    
    public void setBillCustomNo(String billCustomNo){
      this.billCustomNo = billCustomNo;
    }


    public Date getBreakageDate(){
      return breakageDate;
    }
    
    public void setBreakageDate(Date breakageDate){
      this.breakageDate = breakageDate;
    }


    public long getStorageId(){
      return storageId;
    }
    
    public void setStorageId(long storageId){
      this.storageId = storageId;
    }


    public String getStorage(){
      return storage;
    }
    
    public void setStorage(String storage){
      this.storage = storage;
    }


    public long getBrokerId(){
      return brokerId;
    }
    
    public void setBrokerId(long brokerId){
      this.brokerId = brokerId;
    }


    public String getBroker(){
      return broker;
    }
    
    public void setBroker(String broker){
      this.broker = broker;
    }


    public String getExcursus(){
      return excursus;
    }
    
    public void setExcursus(String excursus){
      this.excursus = excursus;
    }


    public String getNotes(){
      return notes;
    }
    
    public void setNotes(String notes){
      this.notes = notes;
    }


    public int getBreakageCount(){
      return breakageCount;
    }
    
    public void setBreakageCount(int breakageCount){
      this.breakageCount = breakageCount;
    }


    public Double getBreakageAmount(){
      return breakageAmount;
    }
    
    public void setBreakageAmount(Double breakageAmount){
      this.breakageAmount = breakageAmount;
    }


    public int getReviseMark(){
      return reviseMark;
    }
    
    public void setReviseMark(int reviseMark){
      this.reviseMark = reviseMark;
    }


    public int getCheckStatus(){
      return checkStatus;
    }
    
    public void setCheckStatus(int checkStatus){
      this.checkStatus = checkStatus;
    }


    public int getBillStatus(){
      return billStatus;
    }
    
    public void setBillStatus(int billStatus){
      this.billStatus = billStatus;
    }


    public String getBillMaker(){
      return billMaker;
    }
    
    public void setBillMaker(String billMaker){
      this.billMaker = billMaker;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
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


    public String getData4(){
      return data4;
    }
    
    public void setData4(String data4){
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


    public int getData7(){
      return data7;
    }
    
    public void setData7(int data7){
      this.data7 = data7;
    }


    public Double getData8(){
      return data8;
    }
    
    public void setData8(Double data8){
      this.data8 = data8;
    }


    public Double getData9(){
      return data9;
    }
    
    public void setData9(Double data9){
      this.data9 = data9;
    }


    public Double getData10(){
      return data10;
    }
    
    public void setData10(Double data10){
      this.data10 = data10;
    }


    public long getData11(){
      return data11;
    }
    
    public void setData11(long data11){
      this.data11 = data11;
    }


    public long getData12(){
      return data12;
    }
    
    public void setData12(long data12){
      this.data12 = data12;
    }

    public String toString(){
	return "id:"+id+"\t"+"billNo:"+billNo+"\t"+"billCustomNo:"+billCustomNo+"\t"+"breakageDate:"+breakageDate+"\t"+"storageId:"+storageId+"\t"+"storage:"+storage+"\t"+"brokerId:"+brokerId+"\t"+"broker:"+broker+"\t"+"excursus:"+excursus+"\t"+"notes:"+notes+"\t"+"breakageCount:"+breakageCount+"\t"+"breakageAmount:"+breakageAmount+"\t"+"reviseMark:"+reviseMark+"\t"+"checkStatus:"+checkStatus+"\t"+"billStatus:"+billStatus+"\t"+"billMaker:"+billMaker+"\t"+"createDate:"+createDate+"\t"+"zoneCode:"+zoneCode+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4+"\t"+"data5:"+data5+"\t"+"data6:"+data6+"\t"+"data7:"+data7+"\t"+"data8:"+data8+"\t"+"data9:"+data9+"\t"+"data10:"+data10+"\t"+"data11:"+data11+"\t"+"data12:"+data12;
    }
}