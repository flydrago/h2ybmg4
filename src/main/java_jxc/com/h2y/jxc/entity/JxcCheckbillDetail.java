package com.h2y.jxc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 进销存  仓库盘点单  单据商品明细 Model
 * @author hwttnet
 * version:1.2
 * time:2015-07-14
 */

public class JxcCheckbillDetail implements Serializable{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyJxcCheckbillDetail";
    private long id;
    private String billNo;
    private long billId;
    private long storageId;
    private String storage;
    private long goodsId;
    private String goodsNickname;
    private String goodsNumber;
    private int inventoryCount;
    private int checkCount;
    private int breakevenCount;
    private Double costSinglePrice;
    private Double breakevenAmount;
    private String notes;
    private Date createDate;
    private String data1;
    private String data2;
    private String data3;
    private int data4;
    private int data5;
    private long data6;
    private long data7;
    private Double data8;
    private Double data9;
    private Double data10;

	public JxcCheckbillDetail(){
		super();
	}

	public JxcCheckbillDetail(long id){
		super();
		this.id = id;
	}

	public JxcCheckbillDetail(long id,String billNo,long billId,long storageId,String storage,long goodsId,String goodsNickname,String goodsNumber,int inventoryCount,int checkCount,int breakevenCount,Double costSinglePrice,Double breakevenAmount,String notes,Date createDate,String data1,String data2,String data3,int data4,int data5,long data6,long data7,Double data8,Double data9,Double data10){
		super();
		this.id = id;
		this.billNo = billNo;
		this.billId = billId;
		this.storageId = storageId;
		this.storage = storage;
		this.goodsId = goodsId;
		this.goodsNickname = goodsNickname;
		this.goodsNumber = goodsNumber;
		this.inventoryCount = inventoryCount;
		this.checkCount = checkCount;
		this.breakevenCount = breakevenCount;
		this.costSinglePrice = costSinglePrice;
		this.breakevenAmount = breakevenAmount;
		this.notes = notes;
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


    public int getInventoryCount(){
      return inventoryCount;
    }
    
    public void setInventoryCount(int inventoryCount){
      this.inventoryCount = inventoryCount;
    }


    public int getCheckCount(){
      return checkCount;
    }
    
    public void setCheckCount(int checkCount){
      this.checkCount = checkCount;
    }


    public int getBreakevenCount(){
      return breakevenCount;
    }
    
    public void setBreakevenCount(int breakevenCount){
      this.breakevenCount = breakevenCount;
    }


    public Double getCostSinglePrice(){
      return costSinglePrice;
    }
    
    public void setCostSinglePrice(Double costSinglePrice){
      this.costSinglePrice = costSinglePrice;
    }


    public Double getBreakevenAmount(){
      return breakevenAmount;
    }
    
    public void setBreakevenAmount(Double breakevenAmount){
      this.breakevenAmount = breakevenAmount;
    }


    public String getNotes(){
      return notes;
    }
    
    public void setNotes(String notes){
      this.notes = notes;
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

    public String toString(){
	return "id:"+id+"\t"+"billNo:"+billNo+"\t"+"billId:"+billId+"\t"+"storageId:"+storageId+"\t"+"storage:"+storage+"\t"+"goodsId:"+goodsId+"\t"+"goodsNickname:"+goodsNickname+"\t"+"goodsNumber:"+goodsNumber+"\t"+"inventoryCount:"+inventoryCount+"\t"+"checkCount:"+checkCount+"\t"+"breakevenCount:"+breakevenCount+"\t"+"costSinglePrice:"+costSinglePrice+"\t"+"breakevenAmount:"+breakevenAmount+"\t"+"notes:"+notes+"\t"+"createDate:"+createDate+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4+"\t"+"data5:"+data5+"\t"+"data6:"+data6+"\t"+"data7:"+data7+"\t"+"data8:"+data8+"\t"+"data9:"+data9+"\t"+"data10:"+data10;
    }
}