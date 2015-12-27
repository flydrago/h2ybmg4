package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 项目名称：h2ybmg2  
 * 类名称：CouponsSource  
 * 类描述：   优惠券来源
 * 创建人：侯飞龙  
 * 创建时间：2015年7月14日 下午5:17:52  
 * 修改人：侯飞龙
 * 修改时间：2015年7月14日 下午5:17:52  
 * 修改备注：  
 * @version
 */
public class CouponsSource extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyCouponsSource";
    private long id;
    private long unitId;
    private int unitType;
    private String zoneCode;
    private String sourceName;
    private String sourceCode;
    private Date startDate;
    private Date endDate;
    private String str1;
    private String str2;
    private String str3;
    private Date date1;
    private Date date2;
    private int int1;
    private int int2;
    private Double double1;
    private Double double2;
    private String memo;
    private Date createDate;
    private int status;

	public CouponsSource(){
		super();
	}

	public CouponsSource(long id){
		super();
		this.id = id;
	}

	public CouponsSource(long id,long unitId,int unitType,String zoneCode,String sourceName,String sourceCode,Date startDate,Date endDate,String str1,String str2,String str3,Date date1,Date date2,int int1,int int2,Double double1,Double double2,String memo,Date createDate,int status){
		super();
		this.id = id;
		this.unitId = unitId;
		this.unitType = unitType;
		this.zoneCode = zoneCode;
		this.sourceName = sourceName;
		this.sourceCode = sourceCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.str1 = str1;
		this.str2 = str2;
		this.str3 = str3;
		this.date1 = date1;
		this.date2 = date2;
		this.int1 = int1;
		this.int2 = int2;
		this.double1 = double1;
		this.double2 = double2;
		this.memo = memo;
		this.createDate = createDate;
		this.status = status;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
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


    public String getSourceName(){
      return sourceName;
    }
    
    public void setSourceName(String sourceName){
      this.sourceName = sourceName;
    }


    public String getSourceCode(){
      return sourceCode;
    }
    
    public void setSourceCode(String sourceCode){
      this.sourceCode = sourceCode;
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


    public String getStr3(){
      return str3;
    }
    
    public void setStr3(String str3){
      this.str3 = str3;
    }


    public Date getDate1(){
      return date1;
    }
    
    public void setDate1(Date date1){
      this.date1 = date1;
    }


    public Date getDate2(){
      return date2;
    }
    
    public void setDate2(Date date2){
      this.date2 = date2;
    }


    public int getInt1(){
      return int1;
    }
    
    public void setInt1(int int1){
      this.int1 = int1;
    }


    public int getInt2(){
      return int2;
    }
    
    public void setInt2(int int2){
      this.int2 = int2;
    }


    public Double getDouble1(){
      return double1;
    }
    
    public void setDouble1(Double double1){
      this.double1 = double1;
    }


    public Double getDouble2(){
      return double2;
    }
    
    public void setDouble2(Double double2){
      this.double2 = double2;
    }


    public String getMemo(){
      return memo;
    }
    
    public void setMemo(String memo){
      this.memo = memo;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public int getStatus(){
      return status;
    }
    
    public void setStatus(int status){
      this.status = status;
    }

    public String toString(){
	return "id:"+id+"\t"+"unitId:"+unitId+"\t"+"unitType:"+unitType+"\t"+"zoneCode:"+zoneCode+"\t"+"sourceName:"+sourceName+"\t"+"sourceCode:"+sourceCode+"\t"+"startDate:"+startDate+"\t"+"endDate:"+endDate+"\t"+"str1:"+str1+"\t"+"str2:"+str2+"\t"+"str3:"+str3+"\t"+"date1:"+date1+"\t"+"date2:"+date2+"\t"+"int1:"+int1+"\t"+"int2:"+int2+"\t"+"double1:"+double1+"\t"+"double2:"+double2+"\t"+"memo:"+memo+"\t"+"createDate:"+createDate+"\t"+"status:"+status;
    }
}