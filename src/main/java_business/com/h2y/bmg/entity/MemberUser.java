package com.h2y.bmg.entity;

import java.util.Date;

/**
 * H2Y会员  Model
 * @author hwttnet
 * version:1.2
 * time:2015-07-15
 */

public class MemberUser{

	/**
	 * @Fields serialVersionUID
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyMemberUser";
    private long id;
    private String account;
    private String password;
    private String realName;
    private String nickName;
    private String sex;
    private String birDate;
    private String idCard;
    private String mailAdd;
    private String telPhone;
    private String zone;
    private String zoneDetail;
    private String qrPath;
    private String qrRelPath;
    private String uuid;
    private String headPath;
    private long levelId;
    private String openId;
    private String regDevice;
    private String regSource;
    private String loginType;
    private Date loginDate;
    private int type;
    private int status;
    private Date createDate;
    private Date updateDate;
    private String memo;
    private String iosPushCode;
    private String androidPushCode;
    private int isAgreement;
    private String androidPushUserid;
    private String iosPushUserid;
    private String userKey;
    private String data1;
    private String data2;
    private String data3;

	public MemberUser(){
		super();
	}

	public MemberUser(long id){
		super();
		this.id = id;
	}

	public MemberUser(long id,String account,String password,String realName,String nickName,String sex,String birDate,String idCard,String mailAdd,String telPhone,String zone,String zoneDetail,String qrPath,String qrRelPath,String uuid,String headPath,long levelId,String openId,String regDevice,String regSource,String loginType,Date loginDate,int type,int status,Date createDate,Date updateDate,String memo,String iosPushCode,String androidPushCode,int isAgreement,String androidPushUserid,String iosPushUserid,String userKey,String data1,String data2,String data3){
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.realName = realName;
		this.nickName = nickName;
		this.sex = sex;
		this.birDate = birDate;
		this.idCard = idCard;
		this.mailAdd = mailAdd;
		this.telPhone = telPhone;
		this.zone = zone;
		this.zoneDetail = zoneDetail;
		this.qrPath = qrPath;
		this.qrRelPath = qrRelPath;
		this.uuid = uuid;
		this.headPath = headPath;
		this.levelId = levelId;
		this.openId = openId;
		this.regDevice = regDevice;
		this.regSource = regSource;
		this.loginType = loginType;
		this.loginDate = loginDate;
		this.type = type;
		this.status = status;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.memo = memo;
		this.iosPushCode = iosPushCode;
		this.androidPushCode = androidPushCode;
		this.isAgreement = isAgreement;
		this.androidPushUserid = androidPushUserid;
		this.iosPushUserid = iosPushUserid;
		this.userKey = userKey;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
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


    public String getPassword(){
      return password;
    }
    
    public void setPassword(String password){
      this.password = password;
    }


    public String getRealName(){
      return realName;
    }
    
    public void setRealName(String realName){
      this.realName = realName;
    }


    public String getNickName(){
      return nickName;
    }
    
    public void setNickName(String nickName){
      this.nickName = nickName;
    }


    public String getSex(){
      return sex;
    }
    
    public void setSex(String sex){
      this.sex = sex;
    }


    public String getBirDate(){
      return birDate;
    }
    
    public void setBirDate(String birDate){
      this.birDate = birDate;
    }


    public String getIdCard(){
      return idCard;
    }
    
    public void setIdCard(String idCard){
      this.idCard = idCard;
    }


    public String getMailAdd(){
      return mailAdd;
    }
    
    public void setMailAdd(String mailAdd){
      this.mailAdd = mailAdd;
    }


    public String getTelPhone(){
      return telPhone;
    }
    
    public void setTelPhone(String telPhone){
      this.telPhone = telPhone;
    }


    public String getZone(){
      return zone;
    }
    
    public void setZone(String zone){
      this.zone = zone;
    }


    public String getZoneDetail(){
      return zoneDetail;
    }
    
    public void setZoneDetail(String zoneDetail){
      this.zoneDetail = zoneDetail;
    }


    public String getQrPath(){
      return qrPath;
    }
    
    public void setQrPath(String qrPath){
      this.qrPath = qrPath;
    }


    public String getQrRelPath(){
      return qrRelPath;
    }
    
    public void setQrRelPath(String qrRelPath){
      this.qrRelPath = qrRelPath;
    }


    public String getUuid(){
      return uuid;
    }
    
    public void setUuid(String uuid){
      this.uuid = uuid;
    }


    public String getHeadPath(){
      return headPath;
    }
    
    public void setHeadPath(String headPath){
      this.headPath = headPath;
    }


    public long getLevelId(){
      return levelId;
    }
    
    public void setLevelId(long levelId){
      this.levelId = levelId;
    }


    public String getOpenId(){
      return openId;
    }
    
    public void setOpenId(String openId){
      this.openId = openId;
    }


    public String getRegDevice(){
      return regDevice;
    }
    
    public void setRegDevice(String regDevice){
      this.regDevice = regDevice;
    }


    public String getRegSource(){
      return regSource;
    }
    
    public void setRegSource(String regSource){
      this.regSource = regSource;
    }


    public String getLoginType(){
      return loginType;
    }
    
    public void setLoginType(String loginType){
      this.loginType = loginType;
    }


    public Date getLoginDate(){
      return loginDate;
    }
    
    public void setLoginDate(Date loginDate){
      this.loginDate = loginDate;
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


    public Date getUpdateDate(){
      return updateDate;
    }
    
    public void setUpdateDate(Date updateDate){
      this.updateDate = updateDate;
    }


    public String getMemo(){
      return memo;
    }
    
    public void setMemo(String memo){
      this.memo = memo;
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


    public int getIsAgreement(){
      return isAgreement;
    }
    
    public void setIsAgreement(int isAgreement){
      this.isAgreement = isAgreement;
    }


    public String getAndroidPushUserid(){
      return androidPushUserid;
    }
    
    public void setAndroidPushUserid(String androidPushUserid){
      this.androidPushUserid = androidPushUserid;
    }


    public String getIosPushUserid(){
      return iosPushUserid;
    }
    
    public void setIosPushUserid(String iosPushUserid){
      this.iosPushUserid = iosPushUserid;
    }


    public String getUserKey(){
      return userKey;
    }
    
    public void setUserKey(String userKey){
      this.userKey = userKey;
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

    public String toString(){
	return "id:"+id+"\t"+"account:"+account+"\t"+"password:"+password+"\t"+"realName:"+realName+"\t"+"nickName:"+nickName+"\t"+"sex:"+sex+"\t"+"birDate:"+birDate+"\t"+"idCard:"+idCard+"\t"+"mailAdd:"+mailAdd+"\t"+"telPhone:"+telPhone+"\t"+"zone:"+zone+"\t"+"zoneDetail:"+zoneDetail+"\t"+"qrPath:"+qrPath+"\t"+"qrRelPath:"+qrRelPath+"\t"+"uuid:"+uuid+"\t"+"headPath:"+headPath+"\t"+"levelId:"+levelId+"\t"+"openId:"+openId+"\t"+"regDevice:"+regDevice+"\t"+"regSource:"+regSource+"\t"+"loginType:"+loginType+"\t"+"loginDate:"+loginDate+"\t"+"type:"+type+"\t"+"status:"+status+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"memo:"+memo+"\t"+"iosPushCode:"+iosPushCode+"\t"+"androidPushCode:"+androidPushCode+"\t"+"isAgreement:"+isAgreement+"\t"+"androidPushUserid:"+androidPushUserid+"\t"+"iosPushUserid:"+iosPushUserid+"\t"+"userKey:"+userKey+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3;
    }
}