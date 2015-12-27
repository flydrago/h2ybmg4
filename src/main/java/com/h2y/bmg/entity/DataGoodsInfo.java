package com.h2y.bmg.entity;

import java.util.Date;

public class DataGoodsInfo {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	public static final String key = "keyDataGoodsInfo";
	private long id;
	private long dataGoodsId;
	private String dataGoodsNumber;
	private int dataGoodsVersion;
	private int dataGoodsCount;
	private long goodsId;
	private String goodsNumber;
	private int goodsVersion;
	private int dataType;
	private Date createDate;
	private int dataStatus;
	private String zoneCode;
	private int dataGoodsSource;

	public DataGoodsInfo() {
		super();
	}

	public DataGoodsInfo(long id) {
		super();
		this.id = id;
	}

	public DataGoodsInfo(long id, long dataGoodsId, String dataGoodsNumber,
			int dataGoodsVersion, int dataGoodsCount, long goodsId,
			String goodsNumber, int goodsVersion, int dataType,
			Date createDate, int dataStatus, String zoneCode,
			int dataGoodsSource) {
		super();
		this.id = id;
		this.dataGoodsId = dataGoodsId;
		this.dataGoodsNumber = dataGoodsNumber;
		this.dataGoodsVersion = dataGoodsVersion;
		this.dataGoodsCount = dataGoodsCount;
		this.goodsId = goodsId;
		this.goodsNumber = goodsNumber;
		this.goodsVersion = goodsVersion;
		this.dataType = dataType;
		this.createDate = createDate;
		this.dataStatus = dataStatus;
		this.zoneCode = zoneCode;
		this.dataGoodsSource = dataGoodsSource;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDataGoodsId() {
		return dataGoodsId;
	}

	public void setDataGoodsId(long dataGoodsId) {
		this.dataGoodsId = dataGoodsId;
	}

	public String getDataGoodsNumber() {
		return dataGoodsNumber;
	}

	public void setDataGoodsNumber(String dataGoodsNumber) {
		this.dataGoodsNumber = dataGoodsNumber;
	}

	public int getDataGoodsVersion() {
		return dataGoodsVersion;
	}

	public void setDataGoodsVersion(int dataGoodsVersion) {
		this.dataGoodsVersion = dataGoodsVersion;
	}

	public int getDataGoodsCount() {
		return dataGoodsCount;
	}

	public void setDataGoodsCount(int dataGoodsCount) {
		this.dataGoodsCount = dataGoodsCount;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(String goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public int getGoodsVersion() {
		return goodsVersion;
	}

	public void setGoodsVersion(int goodsVersion) {
		this.goodsVersion = goodsVersion;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(int dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public int getDataGoodsSource() {
		return dataGoodsSource;
	}

	public void setDataGoodsSource(int dataGoodsSource) {
		this.dataGoodsSource = dataGoodsSource;
	}

	public String toString() {
		return "id:" + id + "\t" + "dataGoodsId:" + dataGoodsId + "\t"
				+ "dataGoodsNumber:" + dataGoodsNumber + "\t"
				+ "dataGoodsVersion:" + dataGoodsVersion + "\t"
				+ "dataGoodsCount:" + dataGoodsCount + "\t" + "goodsId:"
				+ goodsId + "\t" + "goodsNumber:" + goodsNumber + "\t"
				+ "goodsVersion:" + goodsVersion + "\t" + "dataType:"
				+ dataType + "\t" + "createDate:" + createDate + "\t"
				+ "dataStatus:" + dataStatus + "\t" + "zoneCode:" + zoneCode
				+ "\t" + "dataGoodsSource:" + dataGoodsSource;
	}

}
