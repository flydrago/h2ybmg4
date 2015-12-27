package com.h2y.jxc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 进销存 单据商品明细 Model
 * @author hwttnet
 * version:1.2
 * time:2015-06-26
 * email:info@hwttnet.com
 */

public class JxcBillDetail implements Serializable{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyJxcBillDetail";
    private long id;
    private String billNo;
    private long storehouseId;
    private String storehouseName;
    private long goodsId;
    private String goodsNickName;
    private String goodsNumber;
    private Double singlePrice;
    private int goodsCount;
    private Double totalAmount;
    private Date createDate;
    private String notes;
    private int data1;
    private Double data2;
    private Double data3;
    private String data4;
    private String data5;
    private String data6;
    private int data7;

	public JxcBillDetail(){
		super();
	}

	public JxcBillDetail(long id){
		super();
		this.id = id;
	}

	public JxcBillDetail(long id,String billNo,long storehouseId,String storehouseName,long goodsId,String goodsNickName,String goodsNumber,Double singlePrice,int goodsCount,Double totalAmount,Date createDate,String notes,int data1,Double data2,Double data3,String data4,String data5,String data6,int data7){
		super();
		this.id = id;
		this.billNo = billNo;
		this.storehouseId = storehouseId;
		this.storehouseName = storehouseName;
		this.goodsId = goodsId;
		this.goodsNickName = goodsNickName;
		this.goodsNumber = goodsNumber;
		this.singlePrice = singlePrice;
		this.goodsCount = goodsCount;
		this.totalAmount = totalAmount;
		this.createDate = createDate;
		this.notes = notes;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
		this.data4 = data4;
		this.data5 = data5;
		this.data6 = data6;
		this.data7 = data7;
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


    public long getStorehouseId(){
      return storehouseId;
    }
    
    public void setStorehouseId(long storehouseId){
      this.storehouseId = storehouseId;
    }


    public String getStorehouseName(){
      return storehouseName;
    }
    
    public void setStorehouseName(String storehouseName){
      this.storehouseName = storehouseName;
    }


    public long getGoodsId(){
      return goodsId;
    }
    
    public void setGoodsId(long goodsId){
      this.goodsId = goodsId;
    }


    public String getGoodsNickName(){
      return goodsNickName;
    }
    
    public void setGoodsNickName(String goodsNickName){
      this.goodsNickName = goodsNickName;
    }


    public String getGoodsNumber(){
      return goodsNumber;
    }
    
    public void setGoodsNumber(String goodsNumber){
      this.goodsNumber = goodsNumber;
    }


    public Double getSinglePrice(){
      return singlePrice;
    }
    
    public void setSinglePrice(Double singlePrice){
      this.singlePrice = singlePrice;
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


    public int getData1(){
      return data1;
    }
    
    public void setData1(int data1){
      this.data1 = data1;
    }


    public Double getData2(){
      return data2;
    }
    
    public void setData2(Double data2){
      this.data2 = data2;
    }


    public Double getData3(){
      return data3;
    }
    
    public void setData3(Double data3){
      this.data3 = data3;
    }


    public String getData4(){
      return data4;
    }
    
    public void setData4(String data4){
      this.data4 = data4;
    }


    public String getData5(){
      return data5;
    }
    
    public void setData5(String data5){
      this.data5 = data5;
    }


    public String getData6(){
      return data6;
    }
    
    public void setData6(String data6){
      this.data6 = data6;
    }


    public int getData7(){
      return data7;
    }
    
    public void setData7(int data7){
      this.data7 = data7;
    }

    public String toString(){
	return "id:"+id+"\t"+"billNo:"+billNo+"\t"+"storehouseId:"+storehouseId+"\t"+"storehouseName:"+storehouseName+"\t"+"goodsId:"+goodsId+"\t"+"goodsNickName:"+goodsNickName+"\t"+"goodsNumber:"+goodsNumber+"\t"+"singlePrice:"+singlePrice+"\t"+"goodsCount:"+goodsCount+"\t"+"totalAmount:"+totalAmount+"\t"+"createDate:"+createDate+"\t"+"notes:"+notes+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4+"\t"+"data5:"+data5+"\t"+"data6:"+data6+"\t"+"data7:"+data7;
    }
}