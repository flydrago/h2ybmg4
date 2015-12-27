package com.h2y.jxc.entity;

import java.util.Date;
import com.h2y.jxc.basic.BaseBill;

/**
 * 销售退货单 Model
 * @author hwttnet
 * version:1.2
 * time:2015-08-24
 * email:info@hwttnet.com
 */

public class JxcPurchaseReturns extends BaseBill{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyJxcPurchaseReturns";
    private long id;
    private String billNo;
    private String billCustomNo;
    private Date returnsDate;
    private Date proceedsDate;
    private long supplierId;
    private String supplier;
    private long storageId;
    private String storage;
    private String storageArea;
    private long brokerId;
    private String broker;
    private long proceedsAccountId;
    private String proceedsAccount;
    private Double currentProceeds;
    private String notes;
    private int reviseMark;
    private int checkStatus;
    private int billStatus;
    private Double totalAmount;
    private Double discount;
    private Double discountAmount;
    private Double receivableAmount;
    private String billMaker;
    private Date createDate;
    private String zoneCode;
    private String data1;
    private String data2;
    private String data3;
    private String data4;
    private long data5;
    private long data6;
    private long data7;
    private long data8;

	public JxcPurchaseReturns(){
		super();
	}

	public JxcPurchaseReturns(long id){
		super();
		this.id = id;
	}

	public JxcPurchaseReturns(long id,String billNo,String billCustomNo,Date returnsDate,Date proceedsDate,long supplierId,String supplier,long storageId,String storage,String storageArea,long brokerId,String broker,long proceedsAccountId,String proceedsAccount,Double currentProceeds,String notes,int reviseMark,int checkStatus,int billStatus,Double totalAmount,Double discount,Double discountAmount,Double receivableAmount,String billMaker,Date createDate,String zoneCode,String data1,String data2,String data3,String data4,long data5,long data6,long data7,long data8){
		super();
		this.id = id;
		this.billNo = billNo;
		this.billCustomNo = billCustomNo;
		this.returnsDate = returnsDate;
		this.proceedsDate = proceedsDate;
		this.supplierId = supplierId;
		this.supplier = supplier;
		this.storageId = storageId;
		this.storage = storage;
		this.storageArea = storageArea;
		this.brokerId = brokerId;
		this.broker = broker;
		this.proceedsAccountId = proceedsAccountId;
		this.proceedsAccount = proceedsAccount;
		this.currentProceeds = currentProceeds;
		this.notes = notes;
		this.reviseMark = reviseMark;
		this.checkStatus = checkStatus;
		this.billStatus = billStatus;
		this.totalAmount = totalAmount;
		this.discount = discount;
		this.discountAmount = discountAmount;
		this.receivableAmount = receivableAmount;
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


    public Date getProceedsDate(){
      return proceedsDate;
    }
    
    public void setProceedsDate(Date proceedsDate){
      this.proceedsDate = proceedsDate;
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


    public long getProceedsAccountId(){
      return proceedsAccountId;
    }
    
    public void setProceedsAccountId(long proceedsAccountId){
      this.proceedsAccountId = proceedsAccountId;
    }


    public String getProceedsAccount(){
      return proceedsAccount;
    }
    
    public void setProceedsAccount(String proceedsAccount){
      this.proceedsAccount = proceedsAccount;
    }


    public Double getCurrentProceeds(){
      return currentProceeds;
    }
    
    public void setCurrentProceeds(Double currentProceeds){
      this.currentProceeds = currentProceeds;
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


    public Double getReceivableAmount(){
      return receivableAmount;
    }
    
    public void setReceivableAmount(Double receivableAmount){
      this.receivableAmount = receivableAmount;
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

    public String toString(){
	return "id:"+id+"\t"+"billNo:"+billNo+"\t"+"billCustomNo:"+billCustomNo+"\t"+"returnsDate:"+returnsDate+"\t"+"proceedsDate:"+proceedsDate+"\t"+"supplierId:"+supplierId+"\t"+"supplier:"+supplier+"\t"+"storageId:"+storageId+"\t"+"storage:"+storage+"\t"+"storageArea:"+storageArea+"\t"+"brokerId:"+brokerId+"\t"+"broker:"+broker+"\t"+"proceedsAccountId:"+proceedsAccountId+"\t"+"proceedsAccount:"+proceedsAccount+"\t"+"currentProceeds:"+currentProceeds+"\t"+"notes:"+notes+"\t"+"reviseMark:"+reviseMark+"\t"+"checkStatus:"+checkStatus+"\t"+"billStatus:"+billStatus+"\t"+"totalAmount:"+totalAmount+"\t"+"discount:"+discount+"\t"+"discountAmount:"+discountAmount+"\t"+"receivableAmount:"+receivableAmount+"\t"+"billMaker:"+billMaker+"\t"+"createDate:"+createDate+"\t"+"zoneCode:"+zoneCode+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4+"\t"+"data5:"+data5+"\t"+"data6:"+data6+"\t"+"data7:"+data7+"\t"+"data8:"+data8;
    }
}