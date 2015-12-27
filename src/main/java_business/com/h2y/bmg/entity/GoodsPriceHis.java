package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 类描述：商品定价历史 作者：侯飞龙 时间：2015年1月28日下午1:16:20 邮件：1162040314@qq.com
 */
public class GoodsPriceHis extends BaseObject {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	public static final String key = "keyGoodsPriceHis";
	private long id;
	private long goodsPriceId;
	private long goodsId;
	private long goodsTypeId;
	private String gdsCode;
	private String gdsCode2;
	private String goodsNumber;
	private String shortGoodsNumber;
	private String goodsName;
	private String goodsNickName;
	private int inventory;
	private int goodsUnit;
	private int spec;
	private String spellName;
	private String firSpellName;
	private Double maxPrice;
	private Double minPrice;
	private Double memberPrice;
	private Double marketPrice;
	private Double sellPrice;
	private int version;
	private int status;
	private Date createDate;
	private Date shelvesDate;
	private int clickRate;
	private int sellRate;
	private String markIds;
	private String markInfoIds;
	private long ord;
	private String memo;
	private Date updateDate;
	private long userId;
	private long sellUnit;
	private int isLimitSell;
	private int isAllowGrade;
	private int isGiftGrade;
	private long limitSellNumber;
	private Double limitGradeNumber;
	private long limitGiftNumber;
	private int isMallVisible;
	private String gdsQr1;
	private String gdsQrInside;
	private Double sparePrice1;
	private Double sparePrice2;
	private long markIds2;
	private int goodsSource;
	private Date addedDate;
	private int goodsVersion;
	private int goodsStatus;
	private String zoneCode;
	private long unitId;
	private int unitType;
	private int isActivity;
	private Double activityPrice;
	private long activityGoodsId;
	private int activityType;
	private int isGift;
	private int isRelation;
	private long weight;
	private long isAd;
	private Double allowGradePrice;
	private int goodsEditStatus;
	private String s3gdscode;
	private Date s3createdate;
	private String s3ucode;
	private String goodsScode;
	private String goodsScode2;

	public GoodsPriceHis() {
		super();
	}

	public GoodsPriceHis(long id) {
		super();
		this.id = id;
	}

	public GoodsPriceHis(long id, long goodsPriceId, long goodsId,
			long goodsTypeId, String gdsCode, String gdsCode2,
			String goodsNumber, String shortGoodsNumber, String goodsName,
			String goodsNickName, int inventory, int goodsUnit, int spec,
			String spellName, String firSpellName, Double maxPrice,
			Double minPrice, Double memberPrice, Double marketPrice,
			Double sellPrice, int version, int status, Date createDate,
			Date shelvesDate, int clickRate, int sellRate, String markIds,
			String markInfoIds, long ord, String memo, Date updateDate,
			long userId, long sellUnit, int isLimitSell, int isAllowGrade,
			int isGiftGrade, long limitSellNumber, Double limitGradeNumber,
			long limitGiftNumber, int isMallVisible, String gdsQr1,
			String gdsQrInside, Double sparePrice1, Double sparePrice2,
			long markIds2, int goodsSource, Date addedDate, int goodsVersion,
			int goodsStatus, String zoneCode, long unitId, int unitType,
			int isActivity, Double activityPrice, long activityGoodsId,
			int activityType, int isGift, int isRelation, long weight,
			long isAd, Double allowGradePrice, int goodsEditStatus,
			String s3gdscode, Date s3createdate, String s3ucode,
			String goodsScode, String goodsScode2) {
		super();
		this.id = id;
		this.goodsPriceId = goodsPriceId;
		this.goodsId = goodsId;
		this.goodsTypeId = goodsTypeId;
		this.gdsCode = gdsCode;
		this.gdsCode2 = gdsCode2;
		this.goodsNumber = goodsNumber;
		this.shortGoodsNumber = shortGoodsNumber;
		this.goodsName = goodsName;
		this.goodsNickName = goodsNickName;
		this.inventory = inventory;
		this.goodsUnit = goodsUnit;
		this.spec = spec;
		this.spellName = spellName;
		this.firSpellName = firSpellName;
		this.maxPrice = maxPrice;
		this.minPrice = minPrice;
		this.memberPrice = memberPrice;
		this.marketPrice = marketPrice;
		this.sellPrice = sellPrice;
		this.version = version;
		this.status = status;
		this.createDate = createDate;
		this.shelvesDate = shelvesDate;
		this.clickRate = clickRate;
		this.sellRate = sellRate;
		this.markIds = markIds;
		this.markInfoIds = markInfoIds;
		this.ord = ord;
		this.memo = memo;
		this.updateDate = updateDate;
		this.userId = userId;
		this.sellUnit = sellUnit;
		this.isLimitSell = isLimitSell;
		this.isAllowGrade = isAllowGrade;
		this.isGiftGrade = isGiftGrade;
		this.limitSellNumber = limitSellNumber;
		this.limitGradeNumber = limitGradeNumber;
		this.limitGiftNumber = limitGiftNumber;
		this.isMallVisible = isMallVisible;
		this.gdsQr1 = gdsQr1;
		this.gdsQrInside = gdsQrInside;
		this.sparePrice1 = sparePrice1;
		this.sparePrice2 = sparePrice2;
		this.markIds2 = markIds2;
		this.goodsSource = goodsSource;
		this.addedDate = addedDate;
		this.goodsVersion = goodsVersion;
		this.goodsStatus = goodsStatus;
		this.zoneCode = zoneCode;
		this.unitId = unitId;
		this.unitType = unitType;
		this.isActivity = isActivity;
		this.activityPrice = activityPrice;
		this.activityGoodsId = activityGoodsId;
		this.activityType = activityType;
		this.isGift = isGift;
		this.isRelation = isRelation;
		this.weight = weight;
		this.isAd = isAd;
		this.allowGradePrice = allowGradePrice;
		this.goodsEditStatus = goodsEditStatus;
		this.s3gdscode = s3gdscode;
		this.s3createdate = s3createdate;
		this.s3ucode = s3ucode;
		this.goodsScode = goodsScode;
		this.goodsScode2 = goodsScode2;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getGoodsPriceId() {
		return goodsPriceId;
	}

	public void setGoodsPriceId(long goodsPriceId) {
		this.goodsPriceId = goodsPriceId;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public long getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(long goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public String getGdsCode() {
		return gdsCode;
	}

	public void setGdsCode(String gdsCode) {
		this.gdsCode = gdsCode;
	}

	public String getGdsCode2() {
		return gdsCode2;
	}

	public void setGdsCode2(String gdsCode2) {
		this.gdsCode2 = gdsCode2;
	}

	public String getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(String goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public String getShortGoodsNumber() {
		return shortGoodsNumber;
	}

	public void setShortGoodsNumber(String shortGoodsNumber) {
		this.shortGoodsNumber = shortGoodsNumber;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsNickName() {
		return goodsNickName;
	}

	public void setGoodsNickName(String goodsNickName) {
		this.goodsNickName = goodsNickName;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(int goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public int getSpec() {
		return spec;
	}

	public void setSpec(int spec) {
		this.spec = spec;
	}

	public String getSpellName() {
		return spellName;
	}

	public void setSpellName(String spellName) {
		this.spellName = spellName;
	}

	public String getFirSpellName() {
		return firSpellName;
	}

	public void setFirSpellName(String firSpellName) {
		this.firSpellName = firSpellName;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(Double memberPrice) {
		this.memberPrice = memberPrice;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getShelvesDate() {
		return shelvesDate;
	}

	public void setShelvesDate(Date shelvesDate) {
		this.shelvesDate = shelvesDate;
	}

	public int getClickRate() {
		return clickRate;
	}

	public void setClickRate(int clickRate) {
		this.clickRate = clickRate;
	}

	public int getSellRate() {
		return sellRate;
	}

	public void setSellRate(int sellRate) {
		this.sellRate = sellRate;
	}

	public String getMarkIds() {
		return markIds;
	}

	public void setMarkIds(String markIds) {
		this.markIds = markIds;
	}

	public String getMarkInfoIds() {
		return markInfoIds;
	}

	public void setMarkInfoIds(String markInfoIds) {
		this.markInfoIds = markInfoIds;
	}

	public long getOrd() {
		return ord;
	}

	public void setOrd(long ord) {
		this.ord = ord;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getSellUnit() {
		return sellUnit;
	}

	public void setSellUnit(long sellUnit) {
		this.sellUnit = sellUnit;
	}

	public int getIsLimitSell() {
		return isLimitSell;
	}

	public void setIsLimitSell(int isLimitSell) {
		this.isLimitSell = isLimitSell;
	}

	public int getIsAllowGrade() {
		return isAllowGrade;
	}

	public void setIsAllowGrade(int isAllowGrade) {
		this.isAllowGrade = isAllowGrade;
	}

	public int getIsGiftGrade() {
		return isGiftGrade;
	}

	public void setIsGiftGrade(int isGiftGrade) {
		this.isGiftGrade = isGiftGrade;
	}

	public long getLimitSellNumber() {
		return limitSellNumber;
	}

	public void setLimitSellNumber(long limitSellNumber) {
		this.limitSellNumber = limitSellNumber;
	}

	public Double getLimitGradeNumber() {
		return limitGradeNumber;
	}

	public void setLimitGradeNumber(Double limitGradeNumber) {
		this.limitGradeNumber = limitGradeNumber;
	}

	public long getLimitGiftNumber() {
		return limitGiftNumber;
	}

	public void setLimitGiftNumber(long limitGiftNumber) {
		this.limitGiftNumber = limitGiftNumber;
	}

	public int getIsMallVisible() {
		return isMallVisible;
	}

	public void setIsMallVisible(int isMallVisible) {
		this.isMallVisible = isMallVisible;
	}

	public String getGdsQr1() {
		return gdsQr1;
	}

	public void setGdsQr1(String gdsQr1) {
		this.gdsQr1 = gdsQr1;
	}

	public String getGdsQrInside() {
		return gdsQrInside;
	}

	public void setGdsQrInside(String gdsQrInside) {
		this.gdsQrInside = gdsQrInside;
	}

	public Double getSparePrice1() {
		return sparePrice1;
	}

	public void setSparePrice1(Double sparePrice1) {
		this.sparePrice1 = sparePrice1;
	}

	public Double getSparePrice2() {
		return sparePrice2;
	}

	public void setSparePrice2(Double sparePrice2) {
		this.sparePrice2 = sparePrice2;
	}

	public long getMarkIds2() {
		return markIds2;
	}

	public void setMarkIds2(long markIds2) {
		this.markIds2 = markIds2;
	}

	public int getGoodsSource() {
		return goodsSource;
	}

	public void setGoodsSource(int goodsSource) {
		this.goodsSource = goodsSource;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public int getGoodsVersion() {
		return goodsVersion;
	}

	public void setGoodsVersion(int goodsVersion) {
		this.goodsVersion = goodsVersion;
	}

	public int getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(int goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
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

	public int getIsActivity() {
		return isActivity;
	}

	public void setIsActivity(int isActivity) {
		this.isActivity = isActivity;
	}

	public Double getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(Double activityPrice) {
		this.activityPrice = activityPrice;
	}

	public long getActivityGoodsId() {
		return activityGoodsId;
	}

	public void setActivityGoodsId(long activityGoodsId) {
		this.activityGoodsId = activityGoodsId;
	}

	public int getActivityType() {
		return activityType;
	}

	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}

	public int getIsGift() {
		return isGift;
	}

	public void setIsGift(int isGift) {
		this.isGift = isGift;
	}

	public int getIsRelation() {
		return isRelation;
	}

	public void setIsRelation(int isRelation) {
		this.isRelation = isRelation;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public long getIsAd() {
		return isAd;
	}

	public void setIsAd(long isAd) {
		this.isAd = isAd;
	}

	public Double getAllowGradePrice() {
		return allowGradePrice;
	}

	public void setAllowGradePrice(Double allowGradePrice) {
		this.allowGradePrice = allowGradePrice;
	}

	public int getGoodsEditStatus() {
		return goodsEditStatus;
	}

	public void setGoodsEditStatus(int goodsEditStatus) {
		this.goodsEditStatus = goodsEditStatus;
	}

	public String getS3gdscode() {
		return s3gdscode;
	}

	public void setS3gdscode(String s3gdscode) {
		this.s3gdscode = s3gdscode;
	}

	public Date getS3createdate() {
		return s3createdate;
	}

	public void setS3createdate(Date s3createdate) {
		this.s3createdate = s3createdate;
	}

	public String getS3ucode() {
		return s3ucode;
	}

	public void setS3ucode(String s3ucode) {
		this.s3ucode = s3ucode;
	}

	public String getGoodsScode() {
		return goodsScode;
	}

	public void setGoodsScode(String goodsScode) {
		this.goodsScode = goodsScode;
	}

	public String getGoodsScode2() {
		return goodsScode2;
	}

	public void setGoodsScode2(String goodsScode2) {
		this.goodsScode2 = goodsScode2;
	}

	public String toString() {
		return "id:" + id + "\t" + "goodsPriceId:" + goodsPriceId + "\t"
				+ "goodsId:" + goodsId + "\t" + "goodsTypeId:" + goodsTypeId
				+ "\t" + "gdsCode:" + gdsCode + "\t" + "gdsCode2:" + gdsCode2
				+ "\t" + "goodsNumber:" + goodsNumber + "\t"
				+ "shortGoodsNumber:" + shortGoodsNumber + "\t" + "goodsName:"
				+ goodsName + "\t" + "goodsNickName:" + goodsNickName + "\t"
				+ "inventory:" + inventory + "\t" + "goodsUnit:" + goodsUnit
				+ "\t" + "spec:" + spec + "\t" + "spellName:" + spellName
				+ "\t" + "firSpellName:" + firSpellName + "\t" + "maxPrice:"
				+ maxPrice + "\t" + "minPrice:" + minPrice + "\t"
				+ "memberPrice:" + memberPrice + "\t" + "marketPrice:"
				+ marketPrice + "\t" + "sellPrice:" + sellPrice + "\t"
				+ "version:" + version + "\t" + "status:" + status + "\t"
				+ "createDate:" + createDate + "\t" + "shelvesDate:"
				+ shelvesDate + "\t" + "clickRate:" + clickRate + "\t"
				+ "sellRate:" + sellRate + "\t" + "markIds:" + markIds + "\t"
				+ "markInfoIds:" + markInfoIds + "\t" + "ord:" + ord + "\t"
				+ "memo:" + memo + "\t" + "updateDate:" + updateDate + "\t"
				+ "userId:" + userId + "\t" + "sellUnit:" + sellUnit + "\t"
				+ "isLimitSell:" + isLimitSell + "\t" + "isAllowGrade:"
				+ isAllowGrade + "\t" + "isGiftGrade:" + isGiftGrade + "\t"
				+ "limitSellNumber:" + limitSellNumber + "\t"
				+ "limitGradeNumber:" + limitGradeNumber + "\t"
				+ "limitGiftNumber:" + limitGiftNumber + "\t"
				+ "isMallVisible:" + isMallVisible + "\t" + "gdsQr1:" + gdsQr1
				+ "\t" + "gdsQrInside:" + gdsQrInside + "\t" + "sparePrice1:"
				+ sparePrice1 + "\t" + "sparePrice2:" + sparePrice2 + "\t"
				+ "markIds2:" + markIds2 + "\t" + "goodsSource:" + goodsSource
				+ "\t" + "addedDate:" + addedDate + "\t" + "goodsVersion:"
				+ goodsVersion + "\t" + "goodsStatus:" + goodsStatus + "\t"
				+ "zoneCode:" + zoneCode + "\t" + "unitId:" + unitId + "\t"
				+ "unitType:" + unitType + "\t" + "isActivity:" + isActivity
				+ "\t" + "activityPrice:" + activityPrice + "\t"
				+ "activityGoodsId:" + activityGoodsId + "\t" + "activityType:"
				+ activityType + "\t" + "isGift:" + isGift + "\t"
				+ "isRelation:" + isRelation + "\t" + "weight:" + weight + "\t"
				+ "isAd:" + isAd + "\t" + "allowGradePrice:" + allowGradePrice
				+ "\t" + "goodsEditStatus:" + goodsEditStatus + "\t"
				+ "s3gdscode:" + s3gdscode + "\t" + "s3createdate:"
				+ s3createdate + "\t" + "s3ucode:" + s3ucode + "\t"
				+ "goodsScode:" + goodsScode + "\t" + "goodsScode2:"
				+ goodsScode2;
	}
}