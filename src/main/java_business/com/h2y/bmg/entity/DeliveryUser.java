package com.h2y.bmg.entity;


import java.util.Date;


/**
 * 配送员
 * @author sunfj
 *
 */
public class DeliveryUser{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	public static final String key = "keyDeliveryUser";
	private long id;
	private String account;
	private long unitId;
	private long deptId;
	private long shopId;
	private String name;
	private String password;
	private String mobile;
	private Date createDate;
	private Double latitude;
	private Double longitude;
	private String address;
	private Date updateDate;
	private int checkStatus;
	private int workStatus;
	private int loginType;
	private int registerType;
	private int workType;
	private String iosPushCode;
	private String androidPushCode;
	private String regZone;
	private String regZoneDetail;
	private String memo;

	public DeliveryUser(){
		super();
	}

	public DeliveryUser(long id){
		super();
		this.id = id;
	}

	public DeliveryUser(long id,String account,long unitId,long deptId,long shopId,String name,String password,String mobile,Date createDate,Double latitude,Double longitude,String address,Date updateDate,int checkStatus,int workStatus,int loginType,int registerType,int workType,String iosPushCode,String androidPushCode,String regZone,String regZoneDetail,String memo){
		super();
		this.id = id;
		this.account = account;
		this.unitId = unitId;
		this.deptId = deptId;
		this.shopId = shopId;
		this.name = name;
		this.password = password;
		this.mobile = mobile;
		this.createDate = createDate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.updateDate = updateDate;
		this.checkStatus = checkStatus;
		this.workStatus = workStatus;
		this.loginType = loginType;
		this.registerType = registerType;
		this.workType = workType;
		this.iosPushCode = iosPushCode;
		this.androidPushCode = androidPushCode;
		this.regZone = regZone;
		this.regZoneDetail = regZoneDetail;
		this.memo = memo;
	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public String getAccount(){
		return account;
	}

	public void setAccount(String account){
		this.account = account;
	}


	public long getUnitId(){
		return unitId;
	}

	public void setUnitId(long unitId){
		this.unitId = unitId;
	}


	public long getDeptId(){
		return deptId;
	}

	public void setDeptId(long deptId){
		this.deptId = deptId;
	}


	public long getShopId(){
		return shopId;
	}

	public void setShopId(long shopId){
		this.shopId = shopId;
	}


	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}


	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}


	public String getMobile(){
		return mobile;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}


	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}


	public Double getLatitude(){
		return latitude;
	}

	public void setLatitude(Double latitude){
		this.latitude = latitude;
	}


	public Double getLongitude(){
		return longitude;
	}

	public void setLongitude(Double longitude){
		this.longitude = longitude;
	}


	public String getAddress(){
		return address;
	}

	public void setAddress(String address){
		this.address = address;
	}


	public Date getUpdateDate(){
		return updateDate;
	}

	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}


	public int getCheckStatus(){
		return checkStatus;
	}

	public void setCheckStatus(int checkStatus){
		this.checkStatus = checkStatus;
	}


	public int getWorkStatus(){
		return workStatus;
	}

	public void setWorkStatus(int workStatus){
		this.workStatus = workStatus;
	}


	public int getLoginType(){
		return loginType;
	}

	public void setLoginType(int loginType){
		this.loginType = loginType;
	}


	public int getRegisterType(){
		return registerType;
	}

	public void setRegisterType(int registerType){
		this.registerType = registerType;
	}


	public int getWorkType(){
		return workType;
	}

	public void setWorkType(int workType){
		this.workType = workType;
	}


	public String getIosPushCode(){
		return iosPushCode;
	}

	public void setIosPushCode(String iosPushCode){
		this.iosPushCode = iosPushCode;
	}


	public String getAndroidPushCode(){
		return androidPushCode;
	}

	public void setAndroidPushCode(String androidPushCode){
		this.androidPushCode = androidPushCode;
	}


	public String getRegZone(){
		return regZone;
	}

	public void setRegZone(String regZone){
		this.regZone = regZone;
	}


	public String getRegZoneDetail(){
		return regZoneDetail;
	}

	public void setRegZoneDetail(String regZoneDetail){
		this.regZoneDetail = regZoneDetail;
	}


	public String getMemo(){
		return memo;
	}

	public void setMemo(String memo){
		this.memo = memo;
	}

	public String toString(){
		return "id:"+id+"\t"+"account:"+account+"\t"+"unitId:"+unitId+"\t"+"deptId:"+deptId+"\t"+"shopId:"+shopId+"\t"+"name:"+name+"\t"+"password:"+password+"\t"+"mobile:"+mobile+"\t"+"createDate:"+createDate+"\t"+"latitude:"+latitude+"\t"+"longitude:"+longitude+"\t"+"address:"+address+"\t"+"updateDate:"+updateDate+"\t"+"checkStatus:"+checkStatus+"\t"+"workStatus:"+workStatus+"\t"+"loginType:"+loginType+"\t"+"registerType:"+registerType+"\t"+"workType:"+workType+"\t"+"iosPushCode:"+iosPushCode+"\t"+"androidPushCode:"+androidPushCode+"\t"+"regZone:"+regZone+"\t"+"regZoneDetail:"+regZoneDetail+"\t"+"memo:"+memo;
	}
}