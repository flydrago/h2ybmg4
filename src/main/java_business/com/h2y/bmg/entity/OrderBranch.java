package com.h2y.bmg.entity;

import java.util.Date;

public class OrderBranch{
	
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyOrderBranch";
    private long id;
    private String orderNo;
    private long mainOrderId;
    private String mainOrderNo;
    private long userId;
    private String userName;
    private String userMobile;
    private int goodsCount;
    private Double goodsAmount;
    private String transportType;
    private String transportCode;
    private Double transportAmount;
    private Double realAmount;
    private String orderType;
    private long goodsShopId;
    private String isPay;
    private String payType;
    private String orderCategory;
    private String isGrab;
    private String orderFlag;
    private String userFlag;
    private Date goodsTakeDate;
    private String zoneCode;
    private long deliveryId;
    private String deliveryUserName;
    private String deliveryUserMobile;
    private Double longitude;
    private Double latitude;
    private String position;
    private long receiveAddressId;
    private String receiveUserName;
    private String receiveUserMobile;
    private String receiveUserAddress;
    private Double receiveLongitude;
    private Double receiveLatitude;
    private Date createDate;
    private long createUserId;

	public OrderBranch(){
		super();
	}

	public OrderBranch(long id){
		super();
		this.id = id;
	}

	public OrderBranch(long id,String orderNo,long mainOrderId,String mainOrderNo,long userId,String userName,String userMobile,int goodsCount,Double goodsAmount,String transportType,String transportCode,Double transportAmount,Double realAmount,String orderType,long goodsShopId,String isPay,String payType,String orderCategory,String isGrab,String orderFlag,String userFlag,Date goodsTakeDate,String zoneCode,long deliveryId,String deliveryUserName,String deliveryUserMobile,Double longitude,Double latitude,String position,long receiveAddressId,String receiveUserName,String receiveUserMobile,String receiveUserAddress,Double receiveLongitude,Double receiveLatitude,Date createDate,long createUserId){
		super();
		this.id = id;
		this.orderNo = orderNo;
		this.mainOrderId = mainOrderId;
		this.mainOrderNo = mainOrderNo;
		this.userId = userId;
		this.userName = userName;
		this.userMobile = userMobile;
		this.goodsCount = goodsCount;
		this.goodsAmount = goodsAmount;
		this.transportType = transportType;
		this.transportCode = transportCode;
		this.transportAmount = transportAmount;
		this.realAmount = realAmount;
		this.orderType = orderType;
		this.goodsShopId = goodsShopId;
		this.isPay = isPay;
		this.payType = payType;
		this.orderCategory = orderCategory;
		this.isGrab = isGrab;
		this.orderFlag = orderFlag;
		this.userFlag = userFlag;
		this.goodsTakeDate = goodsTakeDate;
		this.zoneCode = zoneCode;
		this.deliveryId = deliveryId;
		this.deliveryUserName = deliveryUserName;
		this.deliveryUserMobile = deliveryUserMobile;
		this.longitude = longitude;
		this.latitude = latitude;
		this.position = position;
		this.receiveAddressId = receiveAddressId;
		this.receiveUserName = receiveUserName;
		this.receiveUserMobile = receiveUserMobile;
		this.receiveUserAddress = receiveUserAddress;
		this.receiveLongitude = receiveLongitude;
		this.receiveLatitude = receiveLatitude;
		this.createDate = createDate;
		this.createUserId = createUserId;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public String getOrderNo(){
      return orderNo;
    }
    
    public void setOrderNo(String orderNo){
      this.orderNo = orderNo;
    }


    public long getMainOrderId(){
      return mainOrderId;
    }
    
    public void setMainOrderId(long mainOrderId){
      this.mainOrderId = mainOrderId;
    }


    public String getMainOrderNo(){
      return mainOrderNo;
    }
    
    public void setMainOrderNo(String mainOrderNo){
      this.mainOrderNo = mainOrderNo;
    }


    public long getUserId(){
      return userId;
    }
    
    public void setUserId(long userId){
      this.userId = userId;
    }


    public String getUserName(){
      return userName;
    }
    
    public void setUserName(String userName){
      this.userName = userName;
    }


    public String getUserMobile(){
      return userMobile;
    }
    
    public void setUserMobile(String userMobile){
      this.userMobile = userMobile;
    }


    public int getGoodsCount(){
      return goodsCount;
    }
    
    public void setGoodsCount(int goodsCount){
      this.goodsCount = goodsCount;
    }


    public Double getGoodsAmount(){
      return goodsAmount;
    }
    
    public void setGoodsAmount(Double goodsAmount){
      this.goodsAmount = goodsAmount;
    }


    public String getTransportType(){
      return transportType;
    }
    
    public void setTransportType(String transportType){
      this.transportType = transportType;
    }


    public String getTransportCode(){
      return transportCode;
    }
    
    public void setTransportCode(String transportCode){
      this.transportCode = transportCode;
    }


    public Double getTransportAmount(){
      return transportAmount;
    }
    
    public void setTransportAmount(Double transportAmount){
      this.transportAmount = transportAmount;
    }


    public Double getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(Double realAmount) {
		this.realAmount = realAmount;
	}

	public String getOrderType(){
      return orderType;
    }
    
    public void setOrderType(String orderType){
      this.orderType = orderType;
    }


    public long getGoodsShopId(){
      return goodsShopId;
    }
    
    public void setGoodsShopId(long goodsShopId){
      this.goodsShopId = goodsShopId;
    }


    public String getIsPay(){
      return isPay;
    }
    
    public void setIsPay(String isPay){
      this.isPay = isPay;
    }


    public String getPayType(){
      return payType;
    }
    
    public void setPayType(String payType){
      this.payType = payType;
    }


    public String getOrderCategory(){
      return orderCategory;
    }
    
    public void setOrderCategory(String orderCategory){
      this.orderCategory = orderCategory;
    }


    public String getIsGrab(){
      return isGrab;
    }
    
    public void setIsGrab(String isGrab){
      this.isGrab = isGrab;
    }


    public String getOrderFlag(){
      return orderFlag;
    }
    
    public void setOrderFlag(String orderFlag){
      this.orderFlag = orderFlag;
    }


    public String getUserFlag(){
      return userFlag;
    }
    
    public void setUserFlag(String userFlag){
      this.userFlag = userFlag;
    }


    public Date getGoodsTakeDate(){
      return goodsTakeDate;
    }
    
    public void setGoodsTakeDate(Date goodsTakeDate){
      this.goodsTakeDate = goodsTakeDate;
    }


    public String getZoneCode(){
      return zoneCode;
    }
    
    public void setZoneCode(String zoneCode){
      this.zoneCode = zoneCode;
    }


    public long getDeliveryId(){
      return deliveryId;
    }
    
    public void setDeliveryId(long deliveryId){
      this.deliveryId = deliveryId;
    }


    public String getDeliveryUserName(){
      return deliveryUserName;
    }
    
    public void setDeliveryUserName(String deliveryUserName){
      this.deliveryUserName = deliveryUserName;
    }


    public String getDeliveryUserMobile(){
      return deliveryUserMobile;
    }
    
    public void setDeliveryUserMobile(String deliveryUserMobile){
      this.deliveryUserMobile = deliveryUserMobile;
    }


    public Double getLongitude(){
      return longitude;
    }
    
    public void setLongitude(Double longitude){
      this.longitude = longitude;
    }


    public Double getLatitude(){
      return latitude;
    }
    
    public void setLatitude(Double latitude){
      this.latitude = latitude;
    }


    public String getPosition(){
      return position;
    }
    
    public void setPosition(String position){
      this.position = position;
    }


    public long getReceiveAddressId(){
      return receiveAddressId;
    }
    
    public void setReceiveAddressId(long receiveAddressId){
      this.receiveAddressId = receiveAddressId;
    }


    public String getReceiveUserName(){
      return receiveUserName;
    }
    
    public void setReceiveUserName(String receiveUserName){
      this.receiveUserName = receiveUserName;
    }


    public String getReceiveUserMobile(){
      return receiveUserMobile;
    }
    
    public void setReceiveUserMobile(String receiveUserMobile){
      this.receiveUserMobile = receiveUserMobile;
    }


    public String getReceiveUserAddress(){
      return receiveUserAddress;
    }
    
    public void setReceiveUserAddress(String receiveUserAddress){
      this.receiveUserAddress = receiveUserAddress;
    }


    public Double getReceiveLongitude(){
      return receiveLongitude;
    }
    
    public void setReceiveLongitude(Double receiveLongitude){
      this.receiveLongitude = receiveLongitude;
    }


    public Double getReceiveLatitude(){
      return receiveLatitude;
    }
    
    public void setReceiveLatitude(Double receiveLatitude){
      this.receiveLatitude = receiveLatitude;
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

    public String toString(){
	return "id:"+id+"\t"+"orderNo:"+orderNo+"\t"+"mainOrderId:"+mainOrderId+"\t"+"mainOrderNo:"+mainOrderNo+"\t"+"userId:"+userId+"\t"+"userName:"+userName+"\t"+"userMobile:"+userMobile+"\t"+"goodsCount:"+goodsCount+"\t"+"goodsAmount:"+goodsAmount+"\t"+"transportType:"+transportType+"\t"+"transportCode:"+transportCode+"\t"+"transportAmount:"+transportAmount+"\t"+"orderType:"+orderType+"\t"+"goodsShopId:"+goodsShopId+"\t"+"isPay:"+isPay+"\t"+"payType:"+payType+"\t"+"orderCategory:"+orderCategory+"\t"+"isGrab:"+isGrab+"\t"+"orderFlag:"+orderFlag+"\t"+"userFlag:"+userFlag+"\t"+"goodsTakeDate:"+goodsTakeDate+"\t"+"zoneCode:"+zoneCode+"\t"+"deliveryId:"+deliveryId+"\t"+"deliveryUserName:"+deliveryUserName+"\t"+"deliveryUserMobile:"+deliveryUserMobile+"\t"+"longitude:"+longitude+"\t"+"latitude:"+latitude+"\t"+"position:"+position+"\t"+"receiveAddressId:"+receiveAddressId+"\t"+"receiveUserName:"+receiveUserName+"\t"+"receiveUserMobile:"+receiveUserMobile+"\t"+"receiveUserAddress:"+receiveUserAddress+"\t"+"receiveLongitude:"+receiveLongitude+"\t"+"receiveLatitude:"+receiveLatitude+"\t"+"createDate:"+createDate+"\t"+"createUserId:"+createUserId;
    }
}