package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 项目名称：h2ybmg2  
 * 类名称：ImportBag  
 * 类描述：导入礼包  
 * 创建人：侯飞龙  
 * 创建时间：2015年6月19日 下午2:32:55  
 * 修改人：侯飞龙
 * 修改时间：2015年6月19日 下午2:32:55  
 * 修改备注：  
 * @version
 */
public class ImportBag extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyImportBag";
    private long id;
    private long userId;
    private String bagName;
    private String businessUser;
    private String businessMobile;
    private String bagCode;
    private String account;
    private long unitId;
    private int unitType;
    private String zoneCode;
    private String goodsNumber;
    private String goodsNickName;
    private long goodsPriceId;
    private int goodsPriceVersion;
    private long goodsId;
    private Double singlePrice;
    private int status;
    private String currentTask;
    private int chkStatus1;
    private int chkStatus2;
    private int chkStatus3;
    private int chkStatus4;
    private int chkStatus5;
    private Date createDate;
    private Date startDate;
    private Date endDate;
    private String data1;
    private String data2;
    private Date data3;
    private int data4;
    private Double data5;
    private String memo;

	public ImportBag(){
		super();
	}

	public ImportBag(long id){
		super();
		this.id = id;
	}

	public ImportBag(long id,long userId,String bagName,String businessUser,String businessMobile,String bagCode,String account,long unitId,int unitType,String zoneCode,String goodsNumber,String goodsNickName,long goodsPriceId,int goodsPriceVersion,long goodsId,Double singlePrice,int status,String currentTask,int chkStatus1,int chkStatus2,int chkStatus3,int chkStatus4,int chkStatus5,Date createDate,Date startDate,Date endDate,String data1,String data2,Date data3,int data4,Double data5,String memo){
		super();
		this.id = id;
		this.userId = userId;
		this.bagName = bagName;
		this.businessUser = businessUser;
		this.businessMobile = businessMobile;
		this.bagCode = bagCode;
		this.account = account;
		this.unitId = unitId;
		this.unitType = unitType;
		this.zoneCode = zoneCode;
		this.goodsNumber = goodsNumber;
		this.goodsNickName = goodsNickName;
		this.goodsPriceId = goodsPriceId;
		this.goodsPriceVersion = goodsPriceVersion;
		this.goodsId = goodsId;
		this.singlePrice = singlePrice;
		this.status = status;
		this.currentTask = currentTask;
		this.chkStatus1 = chkStatus1;
		this.chkStatus2 = chkStatus2;
		this.chkStatus3 = chkStatus3;
		this.chkStatus4 = chkStatus4;
		this.chkStatus5 = chkStatus5;
		this.createDate = createDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
		this.data4 = data4;
		this.data5 = data5;
		this.memo = memo;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getUserId(){
      return userId;
    }
    
    public void setUserId(long userId){
      this.userId = userId;
    }


    public String getBagName(){
      return bagName;
    }
    
    public void setBagName(String bagName){
      this.bagName = bagName;
    }


    public String getBusinessUser(){
      return businessUser;
    }
    
    public void setBusinessUser(String businessUser){
      this.businessUser = businessUser;
    }


    public String getBusinessMobile(){
      return businessMobile;
    }
    
    public void setBusinessMobile(String businessMobile){
      this.businessMobile = businessMobile;
    }


    public String getBagCode(){
      return bagCode;
    }
    
    public void setBagCode(String bagCode){
      this.bagCode = bagCode;
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


    public int getUnitType(){
      return unitType;
    }
    
    public void setUnitType(int unitType){
      this.unitType = unitType;
    }


    public String getZoneCode(){
      return zoneCode;
    }
    
    public void setZoneCode(String zoneCode){
      this.zoneCode = zoneCode;
    }


    public String getGoodsNumber(){
      return goodsNumber;
    }
    
    public void setGoodsNumber(String goodsNumber){
      this.goodsNumber = goodsNumber;
    }


    public String getGoodsNickName(){
      return goodsNickName;
    }
    
    public void setGoodsNickName(String goodsNickName){
      this.goodsNickName = goodsNickName;
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


    public Double getSinglePrice(){
      return singlePrice;
    }
    
    public void setSinglePrice(Double singlePrice){
      this.singlePrice = singlePrice;
    }


    public int getStatus(){
      return status;
    }
    
    public void setStatus(int status){
      this.status = status;
    }


    public String getCurrentTask(){
      return currentTask;
    }
    
    public void setCurrentTask(String currentTask){
      this.currentTask = currentTask;
    }


    public int getChkStatus1(){
      return chkStatus1;
    }
    
    public void setChkStatus1(int chkStatus1){
      this.chkStatus1 = chkStatus1;
    }


    public int getChkStatus2(){
      return chkStatus2;
    }
    
    public void setChkStatus2(int chkStatus2){
      this.chkStatus2 = chkStatus2;
    }


    public int getChkStatus3(){
      return chkStatus3;
    }
    
    public void setChkStatus3(int chkStatus3){
      this.chkStatus3 = chkStatus3;
    }


    public int getChkStatus4(){
      return chkStatus4;
    }
    
    public void setChkStatus4(int chkStatus4){
      this.chkStatus4 = chkStatus4;
    }


    public int getChkStatus5(){
      return chkStatus5;
    }
    
    public void setChkStatus5(int chkStatus5){
      this.chkStatus5 = chkStatus5;
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


    public Date getData3(){
      return data3;
    }
    
    public void setData3(Date data3){
      this.data3 = data3;
    }


    public int getData4(){
      return data4;
    }
    
    public void setData4(int data4){
      this.data4 = data4;
    }


    public Double getData5(){
      return data5;
    }
    
    public void setData5(Double data5){
      this.data5 = data5;
    }


    public String getMemo(){
      return memo;
    }
    
    public void setMemo(String memo){
      this.memo = memo;
    }

    public String toString(){
	return "id:"+id+"\t"+"userId:"+userId+"\t"+"bagName:"+bagName+"\t"+"businessUser:"+businessUser+"\t"+"businessMobile:"+businessMobile+"\t"+"bagCode:"+bagCode+"\t"+"account:"+account+"\t"+"unitId:"+unitId+"\t"+"unitType:"+unitType+"\t"+"zoneCode:"+zoneCode+"\t"+"goodsNumber:"+goodsNumber+"\t"+"goodsNickName:"+goodsNickName+"\t"+"goodsPriceId:"+goodsPriceId+"\t"+"goodsPriceVersion:"+goodsPriceVersion+"\t"+"goodsId:"+goodsId+"\t"+"singlePrice:"+singlePrice+"\t"+"status:"+status+"\t"+"currentTask:"+currentTask+"\t"+"chkStatus1:"+chkStatus1+"\t"+"chkStatus2:"+chkStatus2+"\t"+"chkStatus3:"+chkStatus3+"\t"+"chkStatus4:"+chkStatus4+"\t"+"chkStatus5:"+chkStatus5+"\t"+"createDate:"+createDate+"\t"+"startDate:"+startDate+"\t"+"endDate:"+endDate+"\t"+"data1:"+data1+"\t"+"data2:"+data2+"\t"+"data3:"+data3+"\t"+"data4:"+data4+"\t"+"data5:"+data5+"\t"+"memo:"+memo;
    }
}