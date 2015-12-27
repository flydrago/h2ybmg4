package com.h2y.jxc.entity;

import java.util.Date;

import com.h2y.jxc.basic.BaseBill;

/**
 * JxcOverflow Model create
 * @author hwttnet
 * version:1.2
 * time:2015-08-24
 * email:info@hwttnet.com
 */

public class JxcOverflow extends BaseBill{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyJxcOverflow";
    private long id;
    private String billNo;
    private String billCustomNo;
    private Date overflowDate;
    private long storageId;
    private String storage;
    private long brokerId;
    private String broker;
    private String excursus;
    private String notes;
    private int overflowCount;
    private Double overflowAmount;
    private int reviseMark;
    private int checkStatus;
    private int billStatus;
    private String billMaker;
    private Date createDate;
    private String zoneCode;
    private String data1;
    private String data2;
    private Double data3;
    private Double data4;
    private long data5;
    private long data6;
    private int data7;
    private int data8;
    private String data9;
    private Double data10;
    private Date data11;

	public JxcOverflow(){
		super();
	}

	public JxcOverflow(long id){
		super();
		this.id = id;
	}

	public JxcOverflow(long id,String billNo,String billCustomNo,Date overflowDate,long storageId,String storage,long brokerId,String broker,String excursus,String notes,int overflowCount,Double overflowAmount,int reviseMark,int checkStatus,int billStatus,String billMaker,Date createDate,String zoneCode,String data1,String data2,Double data3,Double data4,long data5,long data6,int data7,int data8,String data9,Double data10,Date data11){
		super();
		this.id = id;
		this.billNo = billNo;
		this.billCustomNo = billCustomNo;
		this.overflowDate = overflowDate;
		this.storageId = storageId;
		this.storage = storage;
		this.brokerId = brokerId;
		this.broker = broker;
		this.excursus = excursus;
		this.notes = notes;
		this.overflowCount = overflowCount;
		this.overflowAmount = overflowAmount;
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


    public Date getOverflowDate(){
      return overflowDate;
    }
    
    public void setOverflowDate(Date overflowDate){
      this.overflowDate = overflowDate;
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


    public int getOverflowCount(){
      return overflowCount;
    }
    
    public void setOverflowCount(int overflowCount){
      this.overflowCount = overflowCount;
    }


    public Double getOverflowAmount(){
      return overflowAmount;
    }
    
    public void setOverflowAmount(Double overflowAmount){
      this.overflowAmount = overflowAmount;
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


    public Double getData3(){
      return data3;
    }
    
    public void setData3(Double data3){
      this.data3 = data3;
    }


    public Double getData4(){
      return data4;
    }
    
    public void setData4(Double data4){
      this.data4 = data4;
    }


    public long getData5(){
      return data5;
    }
    
    public void setData5(long data5){
      this.data5 = data5;
    }


    public long getData6(){
      return data6;
    }
    
    public void setData6(long data6){
      this.data6 = data6;
    }


    public int getData7(){
      return data7;
    }
    
    public void setData7(int data7){
      this.data7 = data7;
    }


    public int getData8(){
      return data8;
    }
    
    public void setData8(int data8){
      this.data8 = data8;
    }


    public String getData9(){
      return data9;
    }
    
    public void setData9(String data9){
      this.data9 = data9;
    }


    public Double getData10(){
      return data10;
    }
    
    public void setData10(Double data10){
      this.data10 = data10;
    }


    public Date getData11(){
      return data11;
    }
    
    public void setData11(Date data11){
      this.data11 = data11;
    }

    public String toString(){
	return "id:"+id+"\t"+"billNo:"+billNo+"\t"+"billCustomNo:"+billCustomNo+"\t"+"overflowDate:"+overflowDate+"\t"+"storageId:"+storageId+"\t"+"storage:"+storage+"\t"+"brokerId:"+brokerId+"\t"+"broker:"+broker+"\t"+"excursus:"+excursus+"\t"+"notes:"+notes+"\t"+"overflowCount:"+overflowCount+"\t"+"overflowAmount:"+overflowAmount+"\t"+"reviseMark:"+reviseMark+"\t"+"checkStatus:"+checkStatus+"\t"+"billStatus:"+billStatus+"\t"+"billMaker:"+billMaker+"\t"+"createDate:"+createDate+"\t"+"zoneCode:"+zoneCode+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4+"\t"+"data5:"+data5+"\t"+"data6:"+data6+"\t"+"data7:"+data7+"\t"+"data8:"+data8+"\t"+"data9:"+data9+"\t"+"data10:"+data10+"\t"+"data11:"+data11;
    }
}