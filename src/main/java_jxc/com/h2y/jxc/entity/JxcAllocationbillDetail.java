package com.h2y.jxc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 仓库调拨单  单据商品明细 Model
 * @author hwttnet
 * version:1.2
 * time:2015-07-10
 * email:info@hwttnet.com
 */

public class JxcAllocationbillDetail implements Serializable{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyJxcAllocationbillDetail";
    private long id;
    private String billNo;
    private long billId;
    private long exportStorageId;
    private String exportStorage;
    private long importStorageId;
    private String importStorage;
    private long goodsId;
    private String goodsNickname;
    private String goodsNumber;
    private Double costSinglePrice;
    private Double allocateSinglePrice;
    private int goodsCount;
    private Double totalAmount;
    private Double totalDifference;
    private Date createDate;
    private String notes;
    private String data1;
    private String data2;
    private String data3;
    private Double data4;
    private Double data5;
    private Double data6;
    private long data7;
    private long data8;
    private long data9;
    private int data10;

	public JxcAllocationbillDetail(){
		super();
	}

	public JxcAllocationbillDetail(long id){
		super();
		this.id = id;
	}

	public JxcAllocationbillDetail(long id,String billNo,long billId,long exportStorageId,String exportStorage,long importStorageId,String importStorage,long goodsId,String goodsNickname,String goodsNumber,Double costSinglePrice,Double allocateSinglePrice,int goodsCount,Double totalAmount,Double totalDifference,Date createDate,String notes,String data1,String data2,String data3,Double data4,Double data5,Double data6,long data7,long data8,long data9,int data10){
		super();
		this.id = id;
		this.billNo = billNo;
		this.billId = billId;
		this.exportStorageId = exportStorageId;
		this.exportStorage = exportStorage;
		this.importStorageId = importStorageId;
		this.importStorage = importStorage;
		this.goodsId = goodsId;
		this.goodsNickname = goodsNickname;
		this.goodsNumber = goodsNumber;
		this.costSinglePrice = costSinglePrice;
		this.allocateSinglePrice = allocateSinglePrice;
		this.goodsCount = goodsCount;
		this.totalAmount = totalAmount;
		this.totalDifference = totalDifference;
		this.createDate = createDate;
		this.notes = notes;
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


    public long getBillId(){
      return billId;
    }
    
    public void setBillId(long billId){
      this.billId = billId;
    }


    public long getExportStorageId(){
      return exportStorageId;
    }
    
    public void setExportStorageId(long exportStorageId){
      this.exportStorageId = exportStorageId;
    }


    public String getExportStorage(){
      return exportStorage;
    }
    
    public void setExportStorage(String exportStorage){
      this.exportStorage = exportStorage;
    }


    public long getImportStorageId(){
      return importStorageId;
    }
    
    public void setImportStorageId(long importStorageId){
      this.importStorageId = importStorageId;
    }


    public String getImportStorage(){
      return importStorage;
    }
    
    public void setImportStorage(String importStorage){
      this.importStorage = importStorage;
    }


    public long getGoodsId(){
      return goodsId;
    }
    
    public void setGoodsId(long goodsId){
      this.goodsId = goodsId;
    }


    public String getGoodsNickname(){
      return goodsNickname;
    }
    
    public void setGoodsNickname(String goodsNickname){
      this.goodsNickname = goodsNickname;
    }


    public String getGoodsNumber(){
      return goodsNumber;
    }
    
    public void setGoodsNumber(String goodsNumber){
      this.goodsNumber = goodsNumber;
    }


    public Double getCostSinglePrice(){
      return costSinglePrice;
    }
    
    public void setCostSinglePrice(Double costSinglePrice){
      this.costSinglePrice = costSinglePrice;
    }


    public Double getAllocateSinglePrice(){
      return allocateSinglePrice;
    }
    
    public void setAllocateSinglePrice(Double allocateSinglePrice){
      this.allocateSinglePrice = allocateSinglePrice;
    }


    public int getGoodsCount(){
      return goodsCount;
    }
    
    public void setGoodsCount(int goodsCount){
      this.goodsCount = goodsCount;
    }


    public Double getTotalAmount(){
      return totalAmount;
    }
    
    public void setTotalAmount(Double totalAmount){
      this.totalAmount = totalAmount;
    }


    public Double getTotalDifference(){
      return totalDifference;
    }
    
    public void setTotalDifference(Double totalDifference){
      this.totalDifference = totalDifference;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public String getNotes(){
      return notes;
    }
    
    public void setNotes(String notes){
      this.notes = notes;
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


    public Double getData4(){
      return data4;
    }
    
    public void setData4(Double data4){
      this.data4 = data4;
    }


    public Double getData5(){
      return data5;
    }
    
    public void setData5(Double data5){
      this.data5 = data5;
    }


    public Double getData6(){
      return data6;
    }
    
    public void setData6(Double data6){
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


    public int getData10(){
      return data10;
    }
    
    public void setData10(int data10){
      this.data10 = data10;
    }

    public String toString(){
	return "id:"+id+"\t"+"billNo:"+billNo+"\t"+"billId:"+billId+"\t"+"exportStorageId:"+exportStorageId+"\t"+"exportStorage:"+exportStorage+"\t"+"importStorageId:"+importStorageId+"\t"+"importStorage:"+importStorage+"\t"+"goodsId:"+goodsId+"\t"+"goodsNickname:"+goodsNickname+"\t"+"goodsNumber:"+goodsNumber+"\t"+"costSinglePrice:"+costSinglePrice+"\t"+"allocateSinglePrice:"+allocateSinglePrice+"\t"+"goodsCount:"+goodsCount+"\t"+"totalAmount:"+totalAmount+"\t"+"totalDifference:"+totalDifference+"\t"+"createDate:"+createDate+"\t"+"notes:"+notes+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4+"\t"+"data5:"+data5+"\t"+"data6:"+data6+"\t"+"data7:"+data7+"\t"+"data8:"+data8+"\t"+"data9:"+data9+"\t"+"data10:"+data10;
    }
}