package com.h2y.jxc.entity;

import java.util.Date;

import com.h2y.jxc.basic.BaseBill;

/**
 * 进销存  销售退货单 Model
 * @author hwttnet
 * version:1.2
 * time:2015-08-24
 */

public class JxcSalesReturns extends BaseBill{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyJxcSalesReturns";
    private long id;
    private String billNo;
    private String billCustomNo;
    private Date returnsDate;
    private Date refundDate;
    private long customerId;
    private String customer;
    private long storageId;
    private String storage;
    private long storageAreaId;
    private String storageArea;
    private long brokerId;
    private String broker;
    private long refundAccountId;
    private String refundAccount;
    private Double currentRefund;
    private String notes;
    private int reviseMark;
    private int checkStatus;
    private int billStatus;
    private Double totalAmount;
    private Double discount;
    private Double discountAmount;
    private Double refundableAmount;
    private String billMaker;
    private Date createDate;
    private String zoneCode;
    private String data1;
    private String data2;
    private String data3;
    private String data4;
    private int data5;
    private int data6;
    private long data7;
    private long data8;
    private Double data9;
    private Double data10;
    private Double data11;

	public JxcSalesReturns(){
		super();
	}

	public JxcSalesReturns(long id){
		super();
		this.id = id;
	}

	public JxcSalesReturns(long id,String billNo,String billCustomNo,Date returnsDate,Date refundDate,long customerId,String customer,long storageId,String storage,long storageAreaId,String storageArea,long brokerId,String broker,long refundAccountId,String refundAccount,Double currentRefund,String notes,int reviseMark,int checkStatus,int billStatus,Double totalAmount,Double discount,Double discountAmount,Double refundableAmount,String billMaker,Date createDate,String zoneCode,String data1,String data2,String data3,String data4,int data5,int data6,long data7,long data8,Double data9,Double data10,Double data11){
		super();
		this.id = id;
		this.billNo = billNo;
		this.billCustomNo = billCustomNo;
		this.returnsDate = returnsDate;
		this.refundDate = refundDate;
		this.customerId = customerId;
		this.customer = customer;
		this.storageId = storageId;
		this.storage = storage;
		this.storageAreaId = storageAreaId;
		this.storageArea = storageArea;
		this.brokerId = brokerId;
		this.broker = broker;
		this.refundAccountId = refundAccountId;
		this.refundAccount = refundAccount;
		this.currentRefund = currentRefund;
		this.notes = notes;
		this.reviseMark = reviseMark;
		this.checkStatus = checkStatus;
		this.billStatus = billStatus;
		this.totalAmount = totalAmount;
		this.discount = discount;
		this.discountAmount = discountAmount;
		this.refundableAmount = refundableAmount;
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


    public Date getReturnsDate(){
      return returnsDate;
    }
    
    public void setReturnsDate(Date returnsDate){
      this.returnsDate = returnsDate;
    }


    public Date getRefundDate(){
      return refundDate;
    }
    
    public void setRefundDate(Date refundDate){
      this.refundDate = refundDate;
    }


    public long getCustomerId(){
      return customerId;
    }
    
    public void setCustomerId(long customerId){
      this.customerId = customerId;
    }


    public String getCustomer(){
      return customer;
    }
    
    public void setCustomer(String customer){
      this.customer = customer;
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


    public long getStorageAreaId(){
      return storageAreaId;
    }
    
    public void setStorageAreaId(long storageAreaId){
      this.storageAreaId = storageAreaId;
    }


    public String getStorageArea(){
      return storageArea;
    }
    
    public void setStorageArea(String storageArea){
      this.storageArea = storageArea;
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


    public long getRefundAccountId(){
      return refundAccountId;
    }
    
    public void setRefundAccountId(long refundAccountId){
      this.refundAccountId = refundAccountId;
    }


    public String getRefundAccount(){
      return refundAccount;
    }
    
    public void setRefundAccount(String refundAccount){
      this.refundAccount = refundAccount;
    }


    public Double getCurrentRefund(){
      return currentRefund;
    }
    
    public void setCurrentRefund(Double currentRefund){
      this.currentRefund = currentRefund;
    }


    public String getNotes(){
      return notes;
    }
    
    public void setNotes(String notes){
      this.notes = notes;
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


    public Double getTotalAmount(){
      return totalAmount;
    }
    
    public void setTotalAmount(Double totalAmount){
      this.totalAmount = totalAmount;
    }


    public Double getDiscount(){
      return discount;
    }
    
    public void setDiscount(Double discount){
      this.discount = discount;
    }


    public Double getDiscountAmount(){
      return discountAmount;
    }
    
    public void setDiscountAmount(Double discountAmount){
      this.discountAmount = discountAmount;
    }


    public Double getRefundableAmount(){
      return refundableAmount;
    }
    
    public void setRefundableAmount(Double refundableAmount){
      this.refundableAmount = refundableAmount;
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


    public Double getData11(){
      return data11;
    }
    
    public void setData11(Double data11){
      this.data11 = data11;
    }

    public String toString(){
	return "id:"+id+"\t"+"billNo:"+billNo+"\t"+"billCustomNo:"+billCustomNo+"\t"+"returnsDate:"+returnsDate+"\t"+"refundDate:"+refundDate+"\t"+"customerId:"+customerId+"\t"+"customer:"+customer+"\t"+"storageId:"+storageId+"\t"+"storage:"+storage+"\t"+"storageAreaId:"+storageAreaId+"\t"+"storageArea:"+storageArea+"\t"+"brokerId:"+brokerId+"\t"+"broker:"+broker+"\t"+"refundAccountId:"+refundAccountId+"\t"+"refundAccount:"+refundAccount+"\t"+"currentRefund:"+currentRefund+"\t"+"notes:"+notes+"\t"+"reviseMark:"+reviseMark+"\t"+"checkStatus:"+checkStatus+"\t"+"billStatus:"+billStatus+"\t"+"totalAmount:"+totalAmount+"\t"+"discount:"+discount+"\t"+"discountAmount:"+discountAmount+"\t"+"refundableAmount:"+refundableAmount+"\t"+"billMaker:"+billMaker+"\t"+"createDate:"+createDate+"\t"+"zoneCode:"+zoneCode+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4+"\t"+"data5:"+data5+"\t"+"data6:"+data6+"\t"+"data7:"+data7+"\t"+"data8:"+data8+"\t"+"data9:"+data9+"\t"+"data10:"+data10+"\t"+"data11:"+data11;
    }
}