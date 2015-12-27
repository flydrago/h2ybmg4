package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 项目名称：h2ybmg2  
 * 类名称：Coupons  
 * 类描述：  优惠券主表
 * 创建人：侯飞龙  
 * 创建时间：2015年7月3日 下午2:54:04  
 * 修改人：侯飞龙
 * 修改时间：2015年7月3日 下午2:54:04  
 * 修改备注：  
 * @version
 */
public class Coupons extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	public static final String key = "keyCoupons";
	private Long id;
	private Long unitId;
	private Integer unitType;
	private String zoneCode;
	private String couponsCode;
	private String couponsName;
	private Double couponsBalance;
	private Date createDate;
	private Date startDate;
	private Date endDate;
	private Integer status;
	private Integer isActivity;
	private Integer isMulti;
	private Integer isLimitAmount;
	private Double limitAmount;
	private Integer isLimitType;
	private String limitType;
	private Integer isLimitMark;
	private String limitMark;
	private Integer isLimitGoods;
	private Long limitGoods;
	private Integer isLimitPlatform;
	private String limitPlatform;
	private String memo;
	private String str1;
	private String str2;
	private Integer isLimitCount;
	private Long limitCount;
	private Long claimedCount;

	public Coupons(){
		super();
	}

	public Coupons(Long id){
		super();
		this.id = id;
	}



	public Coupons(Long id, Long unitId, Integer unitType, String zoneCode,
			String couponsCode, String couponsName, Double couponsBalance,
			Date createDate, Date startDate, Date endDate, Integer status,
			Integer isActivity, Integer isMulti, Integer isLimitAmount,
			Double limitAmount, Integer isLimitType, String limitType,
			Integer isLimitMark, String limitMark, Integer isLimitGoods,
			Long limitGoods, Integer isLimitPlatform, String limitPlatform,
			String memo, String str1, String str2, Integer isLimitCount,
			Long limitCount,Long claimedCount) {
		super();
		this.id = id;
		this.unitId = unitId;
		this.unitType = unitType;
		this.zoneCode = zoneCode;
		this.couponsCode = couponsCode;
		this.couponsName = couponsName;
		this.couponsBalance = couponsBalance;
		this.createDate = createDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.isActivity = isActivity;
		this.isMulti = isMulti;
		this.isLimitAmount = isLimitAmount;
		this.limitAmount = limitAmount;
		this.isLimitType = isLimitType;
		this.limitType = limitType;
		this.isLimitMark = isLimitMark;
		this.limitMark = limitMark;
		this.isLimitGoods = isLimitGoods;
		this.limitGoods = limitGoods;
		this.isLimitPlatform = isLimitPlatform;
		this.limitPlatform = limitPlatform;
		this.memo = memo;
		this.str1 = str1;
		this.str2 = str2;
		this.isLimitCount = isLimitCount;
		this.limitCount = limitCount;
		this.claimedCount = claimedCount;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getUnitId(){
		return unitId;
	}

	public void setUnitId(Long unitId){
		this.unitId = unitId;
	}


	public Integer getUnitType(){
		return unitType;
	}

	public void setUnitType(Integer unitType){
		this.unitType = unitType;
	}


	public String getZoneCode(){
		return zoneCode;
	}

	public void setZoneCode(String zoneCode){
		this.zoneCode = zoneCode;
	}


	public String getCouponsCode(){
		return couponsCode;
	}

	public void setCouponsCode(String couponsCode){
		this.couponsCode = couponsCode;
	}


	public String getCouponsName(){
		return couponsName;
	}

	public void setCouponsName(String couponsName){
		this.couponsName = couponsName;
	}


	public Double getCouponsBalance(){
		return couponsBalance;
	}

	public void setCouponsBalance(Double couponsBalance){
		this.couponsBalance = couponsBalance;
	}


	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}


	public Date getStartDate(){
		return startDate;
	}

	public void setStartDate(Date startDate){
		this.startDate = startDate;
	}


	public Date getEndDate(){
		return endDate;
	}

	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}


	public Integer getStatus(){
		return status;
	}

	public void setStatus(Integer status){
		this.status = status;
	}


	public Integer getIsActivity(){
		return isActivity;
	}

	public void setIsActivity(Integer isActivity){
		this.isActivity = isActivity;
	}


	public Integer getIsMulti(){
		return isMulti;
	}

	public void setIsMulti(Integer isMulti){
		this.isMulti = isMulti;
	}


	public Integer getIsLimitAmount(){
		return isLimitAmount;
	}

	public void setIsLimitAmount(Integer isLimitAmount){
		this.isLimitAmount = isLimitAmount;
	}


	public Double getLimitAmount(){
		return limitAmount;
	}

	public void setLimitAmount(Double limitAmount){
		this.limitAmount = limitAmount;
	}


	public Integer getIsLimitType(){
		return isLimitType;
	}

	public void setIsLimitType(Integer isLimitType){
		this.isLimitType = isLimitType;
	}


	public String getLimitType(){
		return limitType;
	}

	public void setLimitType(String limitType){
		this.limitType = limitType;
	}


	public Integer getIsLimitMark(){
		return isLimitMark;
	}

	public void setIsLimitMark(Integer isLimitMark){
		this.isLimitMark = isLimitMark;
	}


	public String getLimitMark(){
		return limitMark;
	}

	public void setLimitMark(String limitMark){
		this.limitMark = limitMark;
	}


	public Integer getIsLimitGoods(){
		return isLimitGoods;
	}

	public void setIsLimitGoods(Integer isLimitGoods){
		this.isLimitGoods = isLimitGoods;
	}


	public Long getLimitGoods(){
		return limitGoods;
	}

	public void setLimitGoods(Long limitGoods){
		this.limitGoods = limitGoods;
	}


	public Integer getIsLimitPlatform(){
		return isLimitPlatform;
	}

	public void setIsLimitPlatform(Integer isLimitPlatform){
		this.isLimitPlatform = isLimitPlatform;
	}


	public String getLimitPlatform(){
		return limitPlatform;
	}

	public void setLimitPlatform(String limitPlatform){
		this.limitPlatform = limitPlatform;
	}


	public String getMemo(){
		return memo;
	}

	public void setMemo(String memo){
		this.memo = memo;
	}


	public String getStr1(){
		return str1;
	}

	public void setStr1(String str1){
		this.str1 = str1;
	}


	public String getStr2(){
		return str2;
	}

	public void setStr2(String str2){
		this.str2 = str2;
	}



	public Integer getIsLimitCount() {
		return isLimitCount;
	}

	public void setIsLimitCount(Integer isLimitCount) {
		this.isLimitCount = isLimitCount;
	}


	public Long getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(Long limitCount) {
		this.limitCount = limitCount;
	}



	public Long getClaimedCount() {
		return claimedCount;
	}

	public void setClaimedCount(Long claimedCount) {
		this.claimedCount = claimedCount;
	}

	public String toString(){
		return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"unitType:"+unitType+"\t"+"zoneCode:"+zoneCode+"\t"+"couponsCode:"+couponsCode+"\t"+"couponsName:"+couponsName+"\t"+"couponsBalance:"+couponsBalance+"\t"+"createDate:"+createDate+"\t"+"startDate:"+startDate+"\t"+"endDate:"+endDate+"\t"+"status:"+status+"\t"+"isActivity:"+isActivity+"\t"+"isMulti:"+isMulti+"\t"+"isLimitAmount:"+isLimitAmount+"\t"+"limitAmount:"+limitAmount+"\t"+"isLimitType:"+isLimitType+"\t"+"limitType:"+limitType+"\t"+"isLimitMark:"+isLimitMark+"\t"+"limitMark:"+limitMark+"\t"+"isLimitGoods:"+isLimitGoods+"\t"+"limitGoods:"+limitGoods+"\t"+"isLimitPlatform:"+isLimitPlatform+"\t"+"limitPlatform:"+limitPlatform+"\t"+"memo:"+memo+"\t"+"str1:"+str1+"\t"+"str2:"+str2+"\t"+ "isLimitCount:" + isLimitCount+ "\t"+"limitCount:" + limitCount+ "\t"+"claimedCount:" + claimedCount;
	}



}