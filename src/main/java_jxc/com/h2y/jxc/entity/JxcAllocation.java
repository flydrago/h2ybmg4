package com.h2y.jxc.entity;

import java.util.Date;

import com.h2y.jxc.basic.BaseBill;

/**
 * 进销存仓库调拨单 Model
 * @author hwttnet
 * version:1.2
 * time:2015-08-24
 * email:info@hwttnet.com
 */

public class JxcAllocation extends BaseBill{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyJxcAllocation";
    private long id;
    private String billNo;
    private String billCustomNo;
    private Date allocateDate;
    private long exportStorageId;
    private String storageExport;
    private long importStorageId;
    private String storageImport;
    private long brokerId;
    private String broker;
    private String notes;
    private Double totalAmount;
    private Double discount;
    private Double discountAmount;
    private Double actualAmount;
    private int totalCount;
    private Double totalDifference;
    private int checkStatus;
    private int billStatus;
    private String billMaker;
    private Date createDate;
    private int reviseMark;
    private String zoneCode;
    private String data1;
    private String data2;
    private String data3;
    private String data4;
    private long data5;
    private long data6;
    private long data7;
    private long data8;
    private Double data9;
    private Double data10;

	public JxcAllocation(){
		super();
	}

	public JxcAllocation(long id){
		super();
		this.id = id;
	}

	public JxcAllocation(long id,String billNo,String billCustomNo,Date allocateDate,long exportStorageId,String storageExport,long importStorageId,String storageImport,long brokerId,String broker,String notes,Double totalAmount,Double discount,Double discountAmount,Double actualAmount,int totalCount,Double totalDifference,int checkStatus,int billStatus,String billMaker,Date createDate,int reviseMark,String zoneCode,String data1,String data2,String data3,String data4,long data5,long data6,long data7,long data8,Double data9,Double data10){
		super();
		this.id = id;
		this.billNo = billNo;
		this.billCustomNo = billCustomNo;
		this.allocateDate = allocateDate;
		this.exportStorageId = exportStorageId;
		this.storageExport = storageExport;
		this.importStorageId = importStorageId;
		this.storageImport = storageImport;
		this.brokerId = brokerId;
		this.broker = broker;
		this.notes = notes;
		this.totalAmount = totalAmount;
		this.discount = discount;
		this.discountAmount = discountAmount;
		this.actualAmount = actualAmount;
		this.totalCount = totalCount;
		this.totalDifference = totalDifference;
		this.checkStatus = checkStatus;
		this.billStatus = billStatus;
		this.billMaker = billMaker;
		this.createDate = createDate;
		this.reviseMark = reviseMark;
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


    public Date getAllocateDate(){
      return allocateDate;
    }
    
    public void setAllocateDate(Date allocateDate){
      this.allocateDate = allocateDate;
    }


    public long getExportStorageId(){
      return exportStorageId;
    }
    
    public void setExportStorageId(long exportStorageId){
      this.exportStorageId = exportStorageId;
    }


    public String getStorageExport(){
      return storageExport;
    }
    
    public void setStorageExport(String storageExport){
      this.storageExport = storageExport;
    }


    public long getImportStorageId(){
      return importStorageId;
    }
    
    public void setImportStorageId(long importStorageId){
      this.importStorageId = importStorageId;
    }


    public String getStorageImport(){
      return storageImport;
    }
    
    public void setStorageImport(String storageImport){
      this.storageImport = storageImport;
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


    public String getNotes(){
      return notes;
    }
    
    public void setNotes(String notes){
      this.notes = notes;
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


    public Double getActualAmount(){
      return actualAmount;
    }
    
    public void setActualAmount(Double actualAmount){
      this.actualAmount = actualAmount;
    }


    public int getTotalCount(){
      return totalCount;
    }
    
    public void setTotalCount(int totalCount){
      this.totalCount = totalCount;
    }


    public Double getTotalDifference(){
      return totalDifference;
    }
    
    public void setTotalDifference(Double totalDifference){
      this.totalDifference = totalDifference;
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


    public int getReviseMark(){
      return reviseMark;
    }
    
    public void setReviseMark(int reviseMark){
      this.reviseMark = reviseMark;
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

    public String toString(){
	return "id:"+id+"\t"+"billNo:"+billNo+"\t"+"billCustomNo:"+billCustomNo+"\t"+"allocateDate:"+allocateDate+"\t"+"exportStorageId:"+exportStorageId+"\t"+"storageExport:"+storageExport+"\t"+"importStorageId:"+importStorageId+"\t"+"storageImport:"+storageImport+"\t"+"brokerId:"+brokerId+"\t"+"broker:"+broker+"\t"+"notes:"+notes+"\t"+"totalAmount:"+totalAmount+"\t"+"discount:"+discount+"\t"+"discountAmount:"+discountAmount+"\t"+"actualAmount:"+actualAmount+"\t"+"totalCount:"+totalCount+"\t"+"totalDifference:"+totalDifference+"\t"+"checkStatus:"+checkStatus+"\t"+"billStatus:"+billStatus+"\t"+"billMaker:"+billMaker+"\t"+"createDate:"+createDate+"\t"+"reviseMark:"+reviseMark+"\t"+"zoneCode:"+zoneCode+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4+"\t"+"data5:"+data5+"\t"+"data6:"+data6+"\t"+"data7:"+data7+"\t"+"data8:"+data8+"\t"+"data9:"+data9+"\t"+"data10:"+data10;
    }
}