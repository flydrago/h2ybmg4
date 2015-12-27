package com.h2y.jxc.entity;

import com.h2y.object.BaseObject;


/**
 * 进销存  商品库存信息 Model
 * @author hwttnet
 * version:1.2
 * time:2015-07-06
 * email:info@hwttnet.com
 */

public class StorehouseGoodsInfo extends BaseObject{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyStorehouseGoodsInfo";
    private long id;
    private long storehouseId;
    private long goodsId;
    private long goodsPriceId;
    private int goodsCount;
    private int virtualCount;
    private long shopId;
    private long subareaId;
    private String data1;
    private String data2;
    private String data3;
    private String data4;
    private long data5;
    private long data6;
    private long data7;
    private long data8;

	public StorehouseGoodsInfo(){
		super();
	}

	public StorehouseGoodsInfo(long id){
		super();
		this.id = id;
	}

	public StorehouseGoodsInfo(long id,long storehouseId,long goodsId,long goodsPriceId,int goodsCount,int virtualCount,long shopId,long subareaId,String data1,String data2,String data3,String data4,long data5,long data6,long data7,long data8){
		super();
		this.id = id;
		this.storehouseId = storehouseId;
		this.goodsId = goodsId;
		this.goodsPriceId = goodsPriceId;
		this.goodsCount = goodsCount;
		this.virtualCount = virtualCount;
		this.shopId = shopId;
		this.subareaId = subareaId;
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

    public long getStorehouseId(){
      return storehouseId;
    }
    
    public void setStorehouseId(long storehouseId){
      this.storehouseId = storehouseId;
    }


    public long getGoodsId(){
      return goodsId;
    }
    
    public void setGoodsId(long goodsId){
      this.goodsId = goodsId;
    }


    public long getGoodsPriceId(){
      return goodsPriceId;
    }
    
    public void setGoodsPriceId(long goodsPriceId){
      this.goodsPriceId = goodsPriceId;
    }


    public int getGoodsCount(){
      return goodsCount;
    }
    
    public void setGoodsCount(int goodsCount){
      this.goodsCount = goodsCount;
    }


    public int getVirtualCount(){
      return virtualCount;
    }
    
    public void setVirtualCount(int virtualCount){
      this.virtualCount = virtualCount;
    }


    public long getShopId(){
      return shopId;
    }
    
    public void setShopId(long shopId){
      this.shopId = shopId;
    }


    public long getSubareaId(){
      return subareaId;
    }
    
    public void setSubareaId(long subareaId){
      this.subareaId = subareaId;
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
	return "id:"+id+"\t"+"storehouseId:"+storehouseId+"\t"+"goodsId:"+goodsId+"\t"+"goodsPriceId:"+goodsPriceId+"\t"+"goodsCount:"+goodsCount+"\t"+"virtualCount:"+virtualCount+"\t"+"shopId:"+shopId+"\t"+"subareaId:"+subareaId+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4+"\t"+"data5:"+data5+"\t"+"data6:"+data6+"\t"+"data7:"+data7+"\t"+"data8:"+data8;
    }
}