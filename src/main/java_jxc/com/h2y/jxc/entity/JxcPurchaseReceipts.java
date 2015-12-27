package com.h2y.jxc.entity;

import java.util.Date;

import com.h2y.jxc.basic.BaseBill;

/**
 * 销售入库单 Model
 * @author hwttnet
 * version:1.2
 * time:2015-08-24
 */

public class JxcPurchaseReceipts extends BaseBill{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyJxcPurchaseReceipts";
    private long id;
    private String billNo;
    private String billCustomNo;
    private Date receiptsDate;
    private Date paymentDate;
    private long supplierId;
    private String supplier;
    private long storageId;
    private String storage;
    private String storageArea;
    private long brokerId;
    private String broker;
    private long paymentAccountId;
    private String paymentAccount;
    private Double currentPayment;
    private String notes;
    private int reviseMark;
    private int checkStatus;
    private int billStatus;
    private Double totalAmount;
    private Double discount;
    private Double discountAmount;
    private Double payableAmount;
    private String billMaker;
    private Date createDate;
    private String zoneCode;
    private long data1;
    private String data2;
    private String data3;
    private long data4;
    private String data5;

	public JxcPurchaseReceipts(){
		super();
	}

	public JxcPurchaseReceipts(long id){
		super();
		this.id = id;
	}

	public JxcPurchaseReceipts(long id,String billNo,String billCustomNo,Date receiptsDate,Date paymentDate,long supplierId,String supplier,long storageId,String storage,String storageArea,long brokerId,String broker,long paymentAccountId,String paymentAccount,Double currentPayment,String notes,int reviseMark,int checkStatus,int billStatus,Double totalAmount,Double discount,Double discountAmount,Double payableAmount,String billMaker,Date createDate,String zoneCode,long data1,String data2,String data3,long data4,String data5){
		super();
		this.id = id;
		this.billNo = billNo;
		this.billCustomNo = billCustomNo;
		this.receiptsDate = receiptsDate;
		this.paymentDate = paymentDate;
		this.supplierId = supplierId;
		this.supplier = supplier;
		this.storageId = storageId;
		this.storage = storage;
		this.storageArea = storageArea;
		this.brokerId = brokerId;
		this.broker = broker;
		this.paymentAccountId = paymentAccountId;
		this.paymentAccount = paymentAccount;
		this.currentPayment = currentPayment;
		this.notes = notes;
		this.reviseMark = reviseMark;
		this.checkStatus = checkStatus;
		this.billStatus = billStatus;
		this.totalAmount = totalAmount;
		this.discount = discount;
		this.discountAmount = discountAmount;
		this.payableAmount = payableAmount;
		this.billMaker = billMaker;
		this.createDate = createDate;
		this.zoneCode = zoneCode;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
		this.data4 = data4;
		this.data5 = data5;
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


    public Date getReceiptsDate(){
      return receiptsDate;
    }
    
    public void setReceiptsDate(Date receiptsDate){
      this.receiptsDate = receiptsDate;
    }


    public Date getPaymentDate(){
      return paymentDate;
    }
    
    public void setPaymentDate(Date paymentDate){
      this.paymentDate = paymentDate;
    }


    public long getSupplierId(){
      return supplierId;
    }
    
    public void setSupplierId(long supplierId){
      this.supplierId = supplierId;
    }


    public String getSupplier(){
      return supplier;
    }
    
    public void setSupplier(String supplier){
      this.supplier = supplier;
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


    public long getPaymentAccountId(){
      return paymentAccountId;
    }
    
    public void setPaymentAccountId(long paymentAccountId){
      this.paymentAccountId = paymentAccountId;
    }


    public String getPaymentAccount(){
      return paymentAccount;
    }
    
    public void setPaymentAccount(String paymentAccount){
      this.paymentAccount = paymentAccount;
    }


    public Double getCurrentPayment(){
      return currentPayment;
    }
    
    public void setCurrentPayment(Double currentPayment){
      this.currentPayment = currentPayment;
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


    public Double getPayableAmount(){
      return payableAmount;
    }
    
    public void setPayableAmount(Double payableAmount){
      this.payableAmount = payableAmount;
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


    public long getData1(){
      return data1;
    }
    
    public void setData1(long data1){
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


    public long getData4(){
      return data4;
    }
    
    public void setData4(long data4){
      this.data4 = data4;
    }


    public String getData5(){
      return data5;
    }
    
    public void setData5(String data5){
      this.data5 = data5;
    }

    public String toString(){
	return "id:"+id+"\t"+"billNo:"+billNo+"\t"+"billCustomNo:"+billCustomNo+"\t"+"receiptsDate:"+receiptsDate+"\t"+"paymentDate:"+paymentDate+"\t"+"supplierId:"+supplierId+"\t"+"supplier:"+supplier+"\t"+"storageId:"+storageId+"\t"+"storage:"+storage+"\t"+"storageArea:"+storageArea+"\t"+"brokerId:"+brokerId+"\t"+"broker:"+broker+"\t"+"paymentAccountId:"+paymentAccountId+"\t"+"paymentAccount:"+paymentAccount+"\t"+"currentPayment:"+currentPayment+"\t"+"notes:"+notes+"\t"+"reviseMark:"+reviseMark+"\t"+"checkStatus:"+checkStatus+"\t"+"billStatus:"+billStatus+"\t"+"totalAmount:"+totalAmount+"\t"+"discount:"+discount+"\t"+"discountAmount:"+discountAmount+"\t"+"payableAmount:"+payableAmount+"\t"+"billMaker:"+billMaker+"\t"+"createDate:"+createDate+"\t"+"zoneCode:"+zoneCode+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4+"\t"+"data5:"+data5;
    }
}