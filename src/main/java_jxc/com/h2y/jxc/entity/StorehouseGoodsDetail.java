package com.h2y.jxc.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 进销存  仓库商品信息库存变动日志  Model
 * @author hwttnet
 * version:1.2
 * time:2015-07-06
 * email:info@hwttnet.com
 */

public class StorehouseGoodsDetail extends BaseObject{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyStorehouseGoodsDetail";
    private long id;
    private long storehouseId;
    private long goodsPriceId;
    private int goodsPriceVersion;
    private long goodsId;
    private String goodsLocation;
    private int goodsCount;
    private int type;
    private int status;
    private Date createDate;
    private long createUserId;
    private String memo;
    private long subareaId;
    private String billNo;
    private int billStatus;
    private long previousCount;
    private long afterCount;
    private String data1;
    private String data2;
    private String data3;
    private long data4;
    private long data5;
    private long data6;
    private long data7;
    private String data8;

	public StorehouseGoodsDetail(){
		super();
	}

	public StorehouseGoodsDetail(long id){
		super();
		this.id = id;
	}

	public StorehouseGoodsDetail(long id,long storehouseId,long goodsPriceId,int goodsPriceVersion,long goodsId,String goodsLocation,int goodsCount,int type,int status,Date createDate,long createUserId,String memo,long subareaId,String billNo,int billStatus,long previousCount,long afterCount,String data1,String data2,String data3,long data4,long data5,long data6,long data7,String data8){
		super();
		this.id = id;
		this.storehouseId = storehouseId;
		this.goodsPriceId = goodsPriceId;
		this.goodsPriceVersion = goodsPriceVersion;
		this.goodsId = goodsId;
		this.goodsLocation = goodsLocation;
		this.goodsCount = goodsCount;
		this.type = type;
		this.status = status;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.memo = memo;
		this.subareaId = subareaId;
		this.billNo = billNo;
		this.billStatus = billStatus;
		this.previousCount = previousCount;
		this.afterCount = afterCount;
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


    public long getGoodsPriceId(){
      return goodsPriceId;
    }
    
    public void setGoodsPriceId(long goodsPriceId){
      this.goodsPriceId = goodsPriceId;
    }


    public int getGoodsPriceVersion(){
      return goodsPriceVersion;
    }
    
    public void setGoodsPriceVersion(int goodsPriceVersion){
      this.goodsPriceVersion = goodsPriceVersion;
    }


    public long getGoodsId(){
      return goodsId;
    }
    
    public void setGoodsId(long goodsId){
      this.goodsId = goodsId;
    }


    public String getGoodsLocation(){
      return goodsLocation;
    }
    
    public void setGoodsLocation(String goodsLocation){
      this.goodsLocation = goodsLocation;
    }


    public int getGoodsCount(){
      return goodsCount;
    }
    
    public void setGoodsCount(int goodsCount){
      this.goodsCount = goodsCount;
    }


    public int getType(){
      return type;
    }
    
    public void setType(int type){
      this.type = type;
    }


    public int getStatus(){
      return status;
    }
    
    public void setStatus(int status){
      this.status = status;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public long getCreateUserId(){
      return createUserId;
    }
    
    public void setCreateUserId(long createUserId){
      this.createUserId = createUserId;
    }


    public String getMemo(){
      return memo;
    }
    
    public void setMemo(String memo){
      this.memo = memo;
    }


    public long getSubareaId(){
      return subareaId;
    }
    
    public void setSubareaId(long subareaId){
      this.subareaId = subareaId;
    }


    public String getBillNo(){
      return billNo;
    }
    
    public void setBillNo(String billNo){
      this.billNo = billNo;
    }


    public int getBillStatus(){
      return billStatus;
    }
    
    public void setBillStatus(int billStatus){
      this.billStatus = billStatus;
    }


    public long getPreviousCount(){
      return previousCount;
    }
    
    public void setPreviousCount(long previousCount){
      this.previousCount = previousCount;
    }


    public long getAfterCount(){
      return afterCount;
    }
    
    public void setAfterCount(long afterCount){
      this.afterCount = afterCount;
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


    public String getData8(){
      return data8;
    }
    
    public void setData8(String data8){
      this.data8 = data8;
    }

    public String toString(){
	return "id:"+id+"\t"+"storehouseId:"+storehouseId+"\t"+"goodsPriceId:"+goodsPriceId+"\t"+"goodsPriceVersion:"+goodsPriceVersion+"\t"+"goodsId:"+goodsId+"\t"+"goodsLocation:"+goodsLocation+"\t"+"goodsCount:"+goodsCount+"\t"+"type:"+type+"\t"+"status:"+status+"\t"+"createDate:"+createDate+"\t"+"createUserId:"+createUserId+"\t"+"memo:"+memo+"\t"+"subareaId:"+subareaId+"\t"+"billNo:"+billNo+"\t"+"billStatus:"+billStatus+"\t"+"previousCount:"+previousCount+"\t"+"afterCount:"+afterCount+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4+"\t"+"data5:"+data5+"\t"+"data6:"+data6+"\t"+"data7:"+data7+"\t"+"data8:"+data8;
    }
}