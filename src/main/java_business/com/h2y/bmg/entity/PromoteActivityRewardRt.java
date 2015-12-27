package com.h2y.bmg.entity;



import java.util.Date;

/**
 * PromoteActivityRewardRt Model create
 * @author hwttnet
 * version:1.2
 * time:2015-08-17
 * email:info@hwttnet.com
 */

public class PromoteActivityRewardRt{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyPromoteActivityRewardRt";
    private long id;
    private long promoteId;
    private int rewardTarget;
    private int dataType;
    private long bigint1;
    private long bigint2;
    private Date date1;
    private Date date2;
    private int int1;
    private int int2;
    private Double double1;
    private Double double2;
    private String str1;
    private String str2;
    private String str3;
    private String str4;

	public PromoteActivityRewardRt(){
		super();
	}

	public PromoteActivityRewardRt(long id){
		super();
		this.id = id;
	}

	public PromoteActivityRewardRt(long id,long promoteId,int rewardTarget,int dataType,long bigint1,long bigint2,Date date1,Date date2,int int1,int int2,Double double1,Double double2,String str1,String str2,String str3,String str4){
		super();
		this.id = id;
		this.promoteId = promoteId;
		this.rewardTarget = rewardTarget;
		this.dataType = dataType;
		this.bigint1 = bigint1;
		this.bigint2 = bigint2;
		this.date1 = date1;
		this.date2 = date2;
		this.int1 = int1;
		this.int2 = int2;
		this.double1 = double1;
		this.double2 = double2;
		this.str1 = str1;
		this.str2 = str2;
		this.str3 = str3;
		this.str4 = str4;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getPromoteId(){
      return promoteId;
    }
    
    public void setPromoteId(long promoteId){
      this.promoteId = promoteId;
    }


    public int getRewardTarget(){
      return rewardTarget;
    }
    
    public void setRewardTarget(int rewardTarget){
      this.rewardTarget = rewardTarget;
    }


    public int getDataType(){
      return dataType;
    }
    
    public void setDataType(int dataType){
      this.dataType = dataType;
    }


    public long getBigint1(){
      return bigint1;
    }
    
    public void setBigint1(long bigint1){
      this.bigint1 = bigint1;
    }


    public long getBigint2(){
      return bigint2;
    }
    
    public void setBigint2(long bigint2){
      this.bigint2 = bigint2;
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


    public String getStr4(){
      return str4;
    }
    
    public void setStr4(String str4){
      this.str4 = str4;
    }

    public String toString(){
	return "id:"+id+"\t"+"promoteId:"+promoteId+"\t"+"rewardTarget:"+rewardTarget+"\t"+"dataType:"+dataType+"\t"+"bigint1:"+bigint1+"\t"+"bigint2:"+bigint2+"\t"+"date1:"+date1+"\t"+"date2:"+date2+"\t"+"int1:"+int1+"\t"+"int2:"+int2+"\t"+"double1:"+double1+"\t"+"double2:"+double2+"\t"+"str1:"+str1+"\t"+"str2:"+str2+"\t"+"str3:"+str3+"\t"+"str4:"+str4;
    }
}