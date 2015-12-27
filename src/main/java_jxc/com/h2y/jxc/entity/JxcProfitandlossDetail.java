package com.h2y.jxc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 进销存 报损单&报溢单 单据商品详情 Model
 * @author hwttnet
 * version:1.2
 * time:2015-07-17
 */

public class JxcProfitandlossDetail implements Serializable{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyJxcProfitandlossDetail";
    private long id;
    private String billNo;
    private long billId;
    private long storageId;
    private String storage;
    private long goodsId;
    private String goodsNickname;
    private String goodsNumber;
    private String goodsUnit;
    private Double goodsCostPrice;
    private int plCount;
    private Double plAmount;
    private Double goodsRetailPrice;
    private Double retailAmount;
    private int giftMark;
    private String notes;
    private Date createDate;
    private String data1;
    private String data2;
    private String data3;
    private long data4;
    private long data5;
    private int data6;
    private int data7;
    private int data8;
    private Double data9;
    private Double data10;
    private Double data11;
    private long data12;

	public JxcProfitandlossDetail(){
		super();
	}

	public JxcProfitandlossDetail(long id){
		super();
		this.id = id;
	}

	public JxcProfitandlossDetail(long id,String billNo,long billId,long storageId,String storage,long goodsId,String goodsNickname,String goodsNumber,String goodsUnit,Double goodsCostPrice,int plCount,Double plAmount,Double goodsRetailPrice,Double retailAmount,int giftMark,String notes,Date createDate,String data1,String data2,String data3,long data4,long data5,int data6,int data7,int data8,Double data9,Double data10,Double data11,long data12){
		super();
		this.id = id;
		this.billNo = billNo;
		this.billId = billId;
		this.storageId = storageId;
		this.storage = storage;
		this.goodsId = goodsId;
		this.goodsNickname = goodsNickname;
		this.goodsNumber = goodsNumber;
		this.goodsUnit = goodsUnit;
		this.goodsCostPrice = goodsCostPrice;
		this.plCount = plCount;
		this.plAmount = plAmount;
		this.goodsRetailPrice = goodsRetailPrice;
		this.retailAmount = retailAmount;
		this.giftMark = giftMark;
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


    public String getGoodsUnit(){
      return goodsUnit;
    }
    
    public void setGoodsUnit(String goodsUnit){
      this.goodsUnit = goodsUnit;
    }


    public Double getGoodsCostPrice(){
      return goodsCostPrice;
    }
    
    public void setGoodsCostPrice(Double goodsCostPrice){
      this.goodsCostPrice = goodsCostPrice;
    }


    public int getPlCount(){
      return plCount;
    }
    
    public void setPlCount(int plCount){
      this.plCount = plCount;
    }


    public Double getPlAmount(){
      return plAmount;
    }
    
    public void setPlAmount(Double plAmount){
      this.plAmount = plAmount;
    }


    public Double getGoodsRetailPrice(){
      return goodsRetailPrice;
    }
    
    public void setGoodsRetailPrice(Double goodsRetailPrice){
      this.goodsRetailPrice = goodsRetailPrice;
    }


    public Double getRetailAmount(){
      return retailAmount;
    }
    
    public void setRetailAmount(Double retailAmount){
      this.retailAmount = retailAmount;
    }


    public int getGiftMark(){
      return giftMark;
    }
    
    public void setGiftMark(int giftMark){
      this.giftMark = giftMark;
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


    public long getData4(){
      return data4;
    }
    
    public void setData4(long data4){
      this.data4 = data4;
    }


    public long getData5(){
      return data5;
    }
    
    public void setData5(long data5){
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


    public int getData8(){
      return data8;
    }
    
    public void setData8(int data8){
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


    public long getData12(){
      return data12;
    }
    
    public void setData12(long data12){
      this.data12 = data12;
    }

    public String toString(){
	return "id:"+id+"\t"+"billNo:"+billNo+"\t"+"billId:"+billId+"\t"+"storageId:"+storageId+"\t"+"storage:"+storage+"\t"+"goodsId:"+goodsId+"\t"+"goodsNickname:"+goodsNickname+"\t"+"goodsNumber:"+goodsNumber+"\t"+"goodsUnit:"+goodsUnit+"\t"+"goodsCostPrice:"+goodsCostPrice+"\t"+"plCount:"+plCount+"\t"+"plAmount:"+plAmount+"\t"+"goodsRetailPrice:"+goodsRetailPrice+"\t"+"retailAmount:"+retailAmount+"\t"+"giftMark:"+giftMark+"\t"+"notes:"+notes+"\t"+"createDate:"+createDate+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4+"\t"+"data5:"+data5+"\t"+"data6:"+data6+"\t"+"data7:"+data7+"\t"+"data8:"+data8+"\t"+"data9:"+data9+"\t"+"data10:"+data10+"\t"+"data11:"+data11+"\t"+"data12:"+data12;
    }
}