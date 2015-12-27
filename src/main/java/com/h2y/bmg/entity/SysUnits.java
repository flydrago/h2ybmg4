package com.h2y.bmg.entity;

import java.util.Date;
import com.h2y.object.BaseObject;

/**
 * 类描述：单位模型   
 * 作者：侯飞龙
 * 时间：2015年1月22日下午3:27:30
 * 邮件：1162040314@qq.com
 */
public class SysUnits extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	public static final String key = "keySysUnits";
	private long id;
	private String unitCode;
	private long parentId;
	private String zoneCode;
	private String unitDomain;
	private String unitName;
	private String shortName;
	private Date regDate;
	private Date stopDate;
	private long userCount;
	private String unitStatus;
	private String adminUrl;
	private String portalUrl;
	private String unitAddress;
	private String telAreaCode;
	private String tel;
	private String telService;
	private String fax;
	private String legalPerson;
	private String legalPersonMobile;
	private String memo;
	private int unitType;
	private String s3ucode;
	private String s3uname;
	private Date s3createdate;
	private int unitKind;

	public SysUnits(){
		super();
	}

	public SysUnits(long id){
		super();
		this.id = id;
	}

	public SysUnits(long id,String unitCode,long parentId,String zoneCode,String unitDomain,String unitName,String shortName,Date regDate,Date stopDate,long userCount,String unitStatus,String adminUrl,String portalUrl,String unitAddress,String telAreaCode,String tel,String telService,String fax,String legalPerson,String legalPersonMobile,String memo,int unitType,String s3ucode,String s3uname,Date s3createdate,int unitKind){
		super();
		this.id = id;
		this.unitCode = unitCode;
		this.parentId = parentId;
		this.zoneCode = zoneCode;
		this.unitDomain = unitDomain;
		this.unitName = unitName;
		this.shortName = shortName;
		this.regDate = regDate;
		this.stopDate = stopDate;
		this.userCount = userCount;
		this.unitStatus = unitStatus;
		this.adminUrl = adminUrl;
		this.portalUrl = portalUrl;
		this.unitAddress = unitAddress;
		this.telAreaCode = telAreaCode;
		this.tel = tel;
		this.telService = telService;
		this.fax = fax;
		this.legalPerson = legalPerson;
		this.legalPersonMobile = legalPersonMobile;
		this.memo = memo;
		this.unitType = unitType;
		this.s3ucode = s3ucode;
		this.s3uname = s3uname;
		this.s3createdate = s3createdate;
		this.unitKind = unitKind;
	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public String getUnitCode(){
		return unitCode;
	}

	public void setUnitCode(String unitCode){
		this.unitCode = unitCode;
	}


	public long getParentId(){
		return parentId;
	}

	public void setParentId(long parentId){
		this.parentId = parentId;
	}


	public String getZoneCode(){
		return zoneCode;
	}

	public void setZoneCode(String zoneCode){
		this.zoneCode = zoneCode;
	}


	public String getUnitDomain(){
		return unitDomain;
	}

	public void setUnitDomain(String unitDomain){
		this.unitDomain = unitDomain;
	}


	public String getUnitName(){
		return unitName;
	}

	public void setUnitName(String unitName){
		this.unitName = unitName;
	}


	public String getShortName(){
		return shortName;
	}

	public void setShortName(String shortName){
		this.shortName = shortName;
	}


	public Date getRegDate(){
		return regDate;
	}

	public void setRegDate(Date regDate){
		this.regDate = regDate;
	}


	public Date getStopDate(){
		return stopDate;
	}

	public void setStopDate(Date stopDate){
		this.stopDate = stopDate;
	}


	public long getUserCount(){
		return userCount;
	}

	public void setUserCount(long userCount){
		this.userCount = userCount;
	}


	public String getUnitStatus(){
		return unitStatus;
	}

	public void setUnitStatus(String unitStatus){
		this.unitStatus = unitStatus;
	}


	public String getAdminUrl(){
		return adminUrl;
	}

	public void setAdminUrl(String adminUrl){
		this.adminUrl = adminUrl;
	}


	public String getPortalUrl(){
		return portalUrl;
	}

	public void setPortalUrl(String portalUrl){
		this.portalUrl = portalUrl;
	}


	public String getUnitAddress(){
		return unitAddress;
	}

	public void setUnitAddress(String unitAddress){
		this.unitAddress = unitAddress;
	}


	public String getTelAreaCode(){
		return telAreaCode;
	}

	public void setTelAreaCode(String telAreaCode){
		this.telAreaCode = telAreaCode;
	}


	public String getTel(){
		return tel;
	}

	public void setTel(String tel){
		this.tel = tel;
	}


	public String getTelService(){
		return telService;
	}

	public void setTelService(String telService){
		this.telService = telService;
	}


	public String getFax(){
		return fax;
	}

	public void setFax(String fax){
		this.fax = fax;
	}


	public String getLegalPerson(){
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson){
		this.legalPerson = legalPerson;
	}


	public String getLegalPersonMobile(){
		return legalPersonMobile;
	}

	public void setLegalPersonMobile(String legalPersonMobile){
		this.legalPersonMobile = legalPersonMobile;
	}


	public String getMemo(){
		return memo;
	}

	public void setMemo(String memo){
		this.memo = memo;
	}


	public int getUnitType(){
		return unitType;
	}

	public void setUnitType(int unitType){
		this.unitType = unitType;
	}


	public String getS3ucode(){
		return s3ucode;
	}

	public void setS3ucode(String s3ucode){
		this.s3ucode = s3ucode;
	}


	public String getS3uname(){
		return s3uname;
	}

	public void setS3uname(String s3uname){
		this.s3uname = s3uname;
	}


	public Date getS3createdate(){
		return s3createdate;
	}

	public void setS3createdate(Date s3createdate){
		this.s3createdate = s3createdate;
	}


	public int getUnitKind(){
		return unitKind;
	}

	public void setUnitKind(int unitKind){
		this.unitKind = unitKind;
	}

	public String toString(){
		return "id:"+id+"\t"+"unitCode:"+unitCode+"\t"+"parentId:"+parentId+"\t"+"zoneCode:"+zoneCode+"\t"+"unitDomain:"+unitDomain+"\t"+"unitName:"+unitName+"\t"+"shortName:"+shortName+"\t"+"regDate:"+regDate+"\t"+"stopDate:"+stopDate+"\t"+"userCount:"+userCount+"\t"+"unitStatus:"+unitStatus+"\t"+"adminUrl:"+adminUrl+"\t"+"portalUrl:"+portalUrl+"\t"+"unitAddress:"+unitAddress+"\t"+"telAreaCode:"+telAreaCode+"\t"+"tel:"+tel+"\t"+"telService:"+telService+"\t"+"fax:"+fax+"\t"+"legalPerson:"+legalPerson+"\t"+"legalPersonMobile:"+legalPersonMobile+"\t"+"memo:"+memo+"\t"+"unitType:"+unitType+"\t"+"s3ucode:"+s3ucode+"\t"+"s3uname:"+s3uname+"\t"+"s3createdate:"+s3createdate+"\t"+"unitKind:"+unitKind;
	}
}