package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 类描述：仓库主表 作者：侯飞龙 时间：2015年1月31日下午2:30:16 邮件：1162040314@qq.com
 */
public class Storehouse extends BaseObject {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	public static final String key = "keyStorehouse";
	private long id;
	private int type;
	private String storeName;
	private String storeSpeName;
	private String storeFirSpeName;
	private int status;
	private long unitId;
	private int unitType;
	private String zoneCode;
	private long shopId;
	private String parentType;
	private Double longitude;
	private Double latitude;
	private String zoneDetail;
	private String telePhone;
	private String principal;
	private String principalMobile;
	private Date createDate;
	private String memo;
	private String s3stcode;
	private Date s3createdate;

	public Storehouse() {
		super();
	}

	public Storehouse(long id) {
		super();
		this.id = id;
	}

	public Storehouse(long id, int type, String storeName, String storeSpeName,
			String storeFirSpeName, int status, long unitId, int unitType,
			String zoneCode, long shopId, String parentType, Double longitude,
			Double latitude, String zoneDetail, String telePhone,
			String principal, String principalMobile, Date createDate,
			String memo, String s3stcode, Date s3createdate) {
		super();
		this.id = id;
		this.type = type;
		this.storeName = storeName;
		this.storeSpeName = storeSpeName;
		this.storeFirSpeName = storeFirSpeName;
		this.status = status;
		this.unitId = unitId;
		this.unitType = unitType;
		this.zoneCode = zoneCode;
		this.shopId = shopId;
		this.parentType = parentType;
		this.longitude = longitude;
		this.latitude = latitude;
		this.zoneDetail = zoneDetail;
		this.telePhone = telePhone;
		this.principal = principal;
		this.principalMobile = principalMobile;
		this.createDate = createDate;
		this.memo = memo;
		this.s3stcode = s3stcode;
		this.s3createdate = s3createdate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreSpeName() {
		return storeSpeName;
	}

	public void setStoreSpeName(String storeSpeName) {
		this.storeSpeName = storeSpeName;
	}

	public String getStoreFirSpeName() {
		return storeFirSpeName;
	}

	public void setStoreFirSpeName(String storeFirSpeName) {
		this.storeFirSpeName = storeFirSpeName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getUnitId() {
		return unitId;
	}

	public void setUnitId(long unitId) {
		this.unitId = unitId;
	}

	public int getUnitType() {
		return unitType;
	}

	public void setUnitType(int unitType) {
		this.unitType = unitType;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getZoneDetail() {
		return zoneDetail;
	}

	public void setZoneDetail(String zoneDetail) {
		this.zoneDetail = zoneDetail;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getPrincipalMobile() {
		return principalMobile;
	}

	public void setPrincipalMobile(String principalMobile) {
		this.principalMobile = principalMobile;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getS3stcode() {
		return s3stcode;
	}

	public void setS3stcode(String s3stcode) {
		this.s3stcode = s3stcode;
	}

	public Date getS3createdate() {
		return s3createdate;
	}

	public void setS3createdate(Date s3createdate) {
		this.s3createdate = s3createdate;
	}

	public String toString() {
		return "id:" + id + "\t" + "type:" + type + "\t" + "storeName:"
				+ storeName + "\t" + "storeSpeName:" + storeSpeName + "\t"
				+ "storeFirSpeName:" + storeFirSpeName + "\t" + "status:"
				+ status + "\t" + "unitId:" + unitId + "\t" + "unitType:"
				+ unitType + "\t" + "zoneCode:" + zoneCode + "\t" + "shopId:"
				+ shopId + "\t" + "parentType:" + parentType + "\t"
				+ "longitude:" + longitude + "\t" + "latitude:" + latitude
				+ "\t" + "zoneDetail:" + zoneDetail + "\t" + "telePhone:"
				+ telePhone + "\t" + "principal:" + principal + "\t"
				+ "principalMobile:" + principalMobile + "\t" + "createDate:"
				+ createDate + "\t" + "memo:" + memo + "\t" + "s3stcode:"
				+ s3stcode + "\t" + "s3createdate:" + s3createdate;
	}
}