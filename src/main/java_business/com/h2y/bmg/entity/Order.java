package com.h2y.bmg.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 项目名称：h2yorsos 类名称：Order 类描述： 订单表模型 创建人：侯飞龙 创建时间：2015年4月15日 上午9:29:15 修改人：侯飞龙
 * 修改时间：2015年4月15日 上午9:29:15 修改备注：
 * @version
 */
public class Order extends BaseObject {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	public static final String key = "keyOrder";
	private long id;
	private String orderNo;
	private String orderUnique;
	private BigDecimal orderLongitude;
	private BigDecimal orderLatitude;
	private String orderPosition;
	private String orderOs;
	private String orderOsv;
	private String orderAppv;
	private int goodsCount;
	private Double goodsAmount;
	private Double goodsTransportAmount;
	private long unitId;
	private long unitType;
	private String unitName;
	private String unitShortName;
	private long shopId;
	private long storehouseId;
	private String storehouseName;
	private String shopName;
	private BigDecimal shopLongitude;
	private BigDecimal shopLatitude;
	private String shopType;
	private String shopAddress;
	private long memberId;
	private String memberAccount;
	private long receiverAddressId;
	private String receiverAddress;
	private BigDecimal receiverLongitude;
	private BigDecimal receiverLatitude;
	private String receiverName;
	private String receiverMobile;
	private String receiverTelephone;
	private String receiverMail;
	private Date createDate;
	private int orderStatus;
	private int deliveryerStatus;
	private int receiverStatus;
	private long deliveryerId;
	private String deliveryerName;
	private String deliveryerMobile;
	private Date deliveryerDate;
	private int isPay;
	private int payType;
	private String payAccount;
	private int orderCategory;
	private String receiveCode;
	private Double vipCoin;
	private Double coinAmount;
	private Double realAmount;
	private int isChild;
	private String s3ordercode;
	private String s3ordercode2;
	private Date s3createdate;
	private String s3op;
	private String s3ucode;
	private String s3stcode;
	private String zoneCode;
	private int isComment;
	private int isCoupons;
	private Double couponsAmount;
	private int isHasCount;
	
	public Order(){
		super();
	}

	public Order(long id){
		super();
		this.id = id;
	}

	public Order(long id,String orderNo,String orderUnique,BigDecimal orderLongitude,BigDecimal orderLatitude,String orderPosition,String orderOs,String orderOsv,String orderAppv,int goodsCount,Double goodsAmount,Double goodsTransportAmount,long unitId,long unitType,String unitName,String unitShortName,long shopId,long storehouseId,String storehouseName,String shopName,BigDecimal shopLongitude,BigDecimal shopLatitude,String shopType,String shopAddress,long memberId,String memberAccount,long receiverAddressId,String receiverAddress,BigDecimal receiverLongitude,BigDecimal receiverLatitude,String receiverName,String receiverMobile,String receiverTelephone,String receiverMail,Date createDate,int orderStatus,int deliveryerStatus,int receiverStatus,long deliveryerId,String deliveryerName,String deliveryerMobile,Date deliveryerDate,int isPay,int payType,String payAccount,int orderCategory,String receiveCode,Double vipCoin,Double coinAmount,Double realAmount,int isChild,String s3ordercode,String s3ordercode2,Date s3createdate,String s3op,String s3ucode,String s3stcode,int isCoupons,Double couponsAmount){
		super();
		this.id = id;
		this.orderNo = orderNo;
		this.orderUnique = orderUnique;
		this.orderLongitude = orderLongitude;
		this.orderLatitude = orderLatitude;
		this.orderPosition = orderPosition;
		this.orderOs = orderOs;
		this.orderOsv = orderOsv;
		this.orderAppv = orderAppv;
		this.goodsCount = goodsCount;
		this.goodsAmount = goodsAmount;
		this.goodsTransportAmount = goodsTransportAmount;
		this.unitId = unitId;
		this.unitType = unitType;
		this.unitName = unitName;
		this.unitShortName = unitShortName;
		this.shopId = shopId;
		this.storehouseId = storehouseId;
		this.storehouseName = storehouseName;
		this.shopName = shopName;
		this.shopLongitude = shopLongitude;
		this.shopLatitude = shopLatitude;
		this.shopType = shopType;
		this.shopAddress = shopAddress;
		this.memberId = memberId;
		this.memberAccount = memberAccount;
		this.receiverAddressId = receiverAddressId;
		this.receiverAddress = receiverAddress;
		this.receiverLongitude = receiverLongitude;
		this.receiverLatitude = receiverLatitude;
		this.receiverName = receiverName;
		this.receiverMobile = receiverMobile;
		this.receiverTelephone = receiverTelephone;
		this.receiverMail = receiverMail;
		this.createDate = createDate;
		this.orderStatus = orderStatus;
		this.deliveryerStatus = deliveryerStatus;
		this.receiverStatus = receiverStatus;
		this.deliveryerId = deliveryerId;
		this.deliveryerName = deliveryerName;
		this.deliveryerMobile = deliveryerMobile;
		this.deliveryerDate = deliveryerDate;
		this.isPay = isPay;
		this.payType = payType;
		this.payAccount = payAccount;
		this.orderCategory = orderCategory;
		this.receiveCode = receiveCode;
		this.vipCoin = vipCoin;
		this.coinAmount = coinAmount;
		this.realAmount = realAmount;
		this.isChild = isChild;
		this.s3ordercode = s3ordercode;
		this.s3ordercode2 = s3ordercode2;
		this.s3createdate = s3createdate;
		this.s3op = s3op;
		this.s3ucode = s3ucode;
		this.s3stcode = s3stcode;
		this.isCoupons = isCoupons;
		this.couponsAmount = couponsAmount;
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


	public String getOrderUnique(){
		return orderUnique;
	}

	public void setOrderUnique(String orderUnique){
		this.orderUnique = orderUnique;
	}


	public BigDecimal getOrderLongitude(){
		return orderLongitude;
	}

	public void setOrderLongitude(BigDecimal orderLongitude){
		this.orderLongitude = orderLongitude;
	}


	public BigDecimal getOrderLatitude(){
		return orderLatitude;
	}

	public void setOrderLatitude(BigDecimal orderLatitude){
		this.orderLatitude = orderLatitude;
	}


	public String getOrderPosition(){
		return orderPosition;
	}

	public void setOrderPosition(String orderPosition){
		this.orderPosition = orderPosition;
	}


	public String getOrderOs() {
		return orderOs;
	}

	public void setOrderOs(String orderOs) {
		this.orderOs = orderOs;
	}

	public String getOrderOsv() {
		return orderOsv;
	}

	public void setOrderOsv(String orderOsv) {
		this.orderOsv = orderOsv;
	}

	public String getOrderAppv() {
		return orderAppv;
	}

	public void setOrderAppv(String orderAppv) {
		this.orderAppv = orderAppv;
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


	public Double getGoodsTransportAmount(){
		return goodsTransportAmount;
	}

	public void setGoodsTransportAmount(Double goodsTransportAmount){
		this.goodsTransportAmount = goodsTransportAmount;
	}


	public long getUnitId(){
		return unitId;
	}

	public void setUnitId(long unitId){
		this.unitId = unitId;
	}


	public long getUnitType(){
		return unitType;
	}

	public void setUnitType(long unitType){
		this.unitType = unitType;
	}


	public String getUnitName(){
		return unitName;
	}

	public void setUnitName(String unitName){
		this.unitName = unitName;
	}


	public String getUnitShortName(){
		return unitShortName;
	}

	public void setUnitShortName(String unitShortName){
		this.unitShortName = unitShortName;
	}


	public long getShopId(){
		return shopId;
	}

	public void setShopId(long shopId){
		this.shopId = shopId;
	}


	public long getStorehouseId(){
		return storehouseId;
	}

	public void setStorehouseId(long storehouseId){
		this.storehouseId = storehouseId;
	}


	public String getStorehouseName() {
		return storehouseName;
	}

	public void setStorehouseName(String storehouseName) {
		this.storehouseName = storehouseName;
	}

	public String getShopName(){
		return shopName;
	}

	public void setShopName(String shopName){
		this.shopName = shopName;
	}


	public BigDecimal getShopLongitude(){
		return shopLongitude;
	}

	public void setShopLongitude(BigDecimal shopLongitude){
		this.shopLongitude = shopLongitude;
	}


	public BigDecimal getShopLatitude(){
		return shopLatitude;
	}

	public void setShopLatitude(BigDecimal shopLatitude){
		this.shopLatitude = shopLatitude;
	}


	public String getShopType(){
		return shopType;
	}

	public void setShopType(String shopType){
		this.shopType = shopType;
	}


	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public long getMemberId(){
		return memberId;
	}

	public void setMemberId(long memberId){
		this.memberId = memberId;
	}


	public String getMemberAccount(){
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount){
		this.memberAccount = memberAccount;
	}


	public long getReceiverAddressId(){
		return receiverAddressId;
	}

	public void setReceiverAddressId(long receiverAddressId){
		this.receiverAddressId = receiverAddressId;
	}


	public String getReceiverAddress(){
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress){
		this.receiverAddress = receiverAddress;
	}


	public BigDecimal getReceiverLongitude(){
		return receiverLongitude;
	}

	public void setReceiverLongitude(BigDecimal receiverLongitude){
		this.receiverLongitude = receiverLongitude;
	}


	public BigDecimal getReceiverLatitude(){
		return receiverLatitude;
	}

	public void setReceiverLatitude(BigDecimal receiverLatitude){
		this.receiverLatitude = receiverLatitude;
	}


	public String getReceiverName(){
		return receiverName;
	}

	public void setReceiverName(String receiverName){
		this.receiverName = receiverName;
	}


	public String getReceiverMobile(){
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile){
		this.receiverMobile = receiverMobile;
	}


	public String getReceiverTelephone(){
		return receiverTelephone;
	}

	public void setReceiverTelephone(String receiverTelephone){
		this.receiverTelephone = receiverTelephone;
	}


	public String getReceiverMail(){
		return receiverMail;
	}

	public void setReceiverMail(String receiverMail){
		this.receiverMail = receiverMail;
	}


	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}


	public int getOrderStatus(){
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus){
		this.orderStatus = orderStatus;
	}


	public int getDeliveryerStatus(){
		return deliveryerStatus;
	}

	public void setDeliveryerStatus(int deliveryerStatus){
		this.deliveryerStatus = deliveryerStatus;
	}


	public int getReceiverStatus(){
		return receiverStatus;
	}

	public void setReceiverStatus(int receiverStatus){
		this.receiverStatus = receiverStatus;
	}


	public long getDeliveryerId(){
		return deliveryerId;
	}

	public void setDeliveryerId(long deliveryerId){
		this.deliveryerId = deliveryerId;
	}


	public String getDeliveryerName(){
		return deliveryerName;
	}

	public void setDeliveryerName(String deliveryerName){
		this.deliveryerName = deliveryerName;
	}


	public String getDeliveryerMobile(){
		return deliveryerMobile;
	}

	public void setDeliveryerMobile(String deliveryerMobile){
		this.deliveryerMobile = deliveryerMobile;
	}


	public Date getDeliveryerDate(){
		return deliveryerDate;
	}

	public void setDeliveryerDate(Date deliveryerDate){
		this.deliveryerDate = deliveryerDate;
	}


	public int getIsPay(){
		return isPay;
	}

	public void setIsPay(int isPay){
		this.isPay = isPay;
	}


	public int getPayType(){
		return payType;
	}

	public void setPayType(int payType){
		this.payType = payType;
	}


	public String getPayAccount(){
		return payAccount;
	}

	public void setPayAccount(String payAccount){
		this.payAccount = payAccount;
	}


	public int getOrderCategory(){
		return orderCategory;
	}

	public void setOrderCategory(int orderCategory){
		this.orderCategory = orderCategory;
	}


	public String getReceiveCode(){
		return receiveCode;
	}

	public void setReceiveCode(String receiveCode){
		this.receiveCode = receiveCode;
	}


	public Double getVipCoin(){
		return vipCoin;
	}

	public void setVipCoin(Double vipCoin){
		this.vipCoin = vipCoin;
	}


	public Double getCoinAmount(){
		return coinAmount;
	}

	public void setCoinAmount(Double coinAmount){
		this.coinAmount = coinAmount;
	}


	public Double getRealAmount(){
		return realAmount;
	}

	public void setRealAmount(Double realAmount){
		this.realAmount = realAmount;
	}


	public int getIsChild(){
		return isChild;
	}

	public void setIsChild(int isChild){
		this.isChild = isChild;
	}


	public String getS3ordercode(){
		return s3ordercode;
	}

	public void setS3ordercode(String s3ordercode){
		this.s3ordercode = s3ordercode;
	}


	public String getS3ordercode2(){
		return s3ordercode2;
	}

	public void setS3ordercode2(String s3ordercode2){
		this.s3ordercode2 = s3ordercode2;
	}


	public Date getS3createdate(){
		return s3createdate;
	}

	public void setS3createdate(Date s3createdate){
		this.s3createdate = s3createdate;
	}


	public String getS3op(){
		return s3op;
	}

	public void setS3op(String s3op){
		this.s3op = s3op;
	}


	public String getS3ucode(){
		return s3ucode;
	}

	public void setS3ucode(String s3ucode){
		this.s3ucode = s3ucode;
	}


	public String getS3stcode(){
		return s3stcode;
	}

	public void setS3stcode(String s3stcode){
		this.s3stcode = s3stcode;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public int getIsComment() {
		return isComment;
	}

	public void setIsComment(int isComment) {
		this.isComment = isComment;
	}


	public int getIsCoupons() {
		return isCoupons;
	}

	public void setIsCoupons(int isCoupons) {
		this.isCoupons = isCoupons;
	}

	public Double getCouponsAmount() {
		return couponsAmount;
	}

	public void setCouponsAmount(Double couponsAmount) {
		this.couponsAmount = couponsAmount;
	}

	public int getIsHasCount() {
		return isHasCount;
	}

	public void setIsHasCount(int isHasCount) {
		this.isHasCount = isHasCount;
	}

	public String toString(){
		return "id:"+id+"\t"+"orderNo:"+orderNo+"\t"+"orderUnique:"+orderUnique+"\t"+"orderLongitude:"+orderLongitude+"\t"+"orderLatitude:"+orderLatitude+"\t"+"orderPosition:"+orderPosition+"\t"+"orderOs:"+orderOs+"\t"+"orderOsv:"+orderOsv+"\t"+"orderAppv:"+orderAppv+"\t"+"goodsCount:"+goodsCount+"\t"+"goodsAmount:"+goodsAmount+"\t"+"goodsTransportAmount:"+goodsTransportAmount+"\t"+"unitId:"+unitId+"\t"+"unitType:"+unitType+"\t"+"unitName:"+unitName+"\t"+"unitShortName:"+unitShortName+"\t"+"shopId:"+shopId+"\t"+"storehouseId:"+storehouseId+"\t"+"storehouseName:"+storehouseName+"\t"+"shopName:"+shopName+"\t"+"shopLongitude:"+shopLongitude+"\t"+"shopLatitude:"+shopLatitude+"\t"+"shopType:"+shopType+"\t"+"shopAddress:"+shopAddress+"\t"+"memberId:"+memberId+"\t"+"memberAccount:"+memberAccount+"\t"+"receiverAddressId:"+receiverAddressId+"\t"+"receiverAddress:"+receiverAddress+"\t"+"receiverLongitude:"+receiverLongitude+"\t"+"receiverLatitude:"+receiverLatitude+"\t"+"receiverName:"+receiverName+"\t"+"receiverMobile:"+receiverMobile+"\t"+"receiverTelephone:"+receiverTelephone+"\t"+"receiverMail:"+receiverMail+"\t"+"createDate:"+createDate+"\t"+"orderStatus:"+orderStatus+"\t"+"deliveryerStatus:"+deliveryerStatus+"\t"+"receiverStatus:"+receiverStatus+"\t"+"deliveryerId:"+deliveryerId+"\t"+"deliveryerName:"+deliveryerName+"\t"+"deliveryerMobile:"+deliveryerMobile+"\t"+"deliveryerDate:"+deliveryerDate+"\t"+"isPay:"+isPay+"\t"+"payType:"+payType+"\t"+"payAccount:"+payAccount+"\t"+"orderCategory:"+orderCategory+"\t"+"receiveCode:"+receiveCode+"\t"+"vipCoin:"+vipCoin+"\t"+"coinAmount:"+coinAmount+"\t"+"realAmount:"+realAmount+"\t"+"isChild:"+isChild+"\t"+"s3ordercode:"+s3ordercode+"\t"+"s3ordercode2:"+s3ordercode2+"\t"+"s3createdate:"+s3createdate+"\t"+"s3op:"+s3op+"\t"+"s3ucode:"+s3ucode+"\t"+"s3stcode:"+s3stcode+"\t"+"zoneCode:"+zoneCode+"\t"+"isComment:"+isComment+"\t"+"isCoupons:"+isCoupons+"\t"+"couponsAmount:"+couponsAmount+"\t"+"isHasCount:"+isHasCount;
	}




}