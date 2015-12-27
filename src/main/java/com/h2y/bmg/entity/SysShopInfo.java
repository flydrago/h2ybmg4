package com.h2y.bmg.entity;


import java.util.Date;

/**
 * SysShopInfo Model create
 * @author hwttnet
 * version:1.2
 * time:2015-06-17
 * email:info@hwttnet.com
 */

public class SysShopInfo{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	public static final String key = "keySysShopInfo";
	private long id;
	private long shopId;
	private String shopPhone;
	private String shopTel;
	private int isWifi;
	private int isPark;
	private int isReservation;
	private int isJyd;
	private int status;
	private Double personCost;
	private String hoursStart;
	private String hoursEnd;
	private String memo;
	private int isApprove;
	private int approveLevel;
	private Date approveDate;
	private long approveUserId;
	private String approveReviews;
	private int isSpread;
	private Date spreadStartDate;
	private Date spreadEndDate;
	private String reverse1;
	private String reverse2;
	private String reverse3;
	private String reverse4;
	private Date createDate;
	private Date updateDate;

	public SysShopInfo(){
		super();
	}

	public SysShopInfo(long id){
		super();
		this.id = id;
	}

	public SysShopInfo(long id,long shopId,String shopPhone,String shopTel,int isWifi,int isPark,int isReservation,int isJyd,int status,Double personCost,String hoursStart,String hoursEnd,String memo,int isApprove,int approveLevel,Date approveDate,long approveUserId,String approveReviews,int isSpread,Date spreadStartDate,Date spreadEndDate,String reverse1,String reverse2,String reverse3,String reverse4,Date createDate,Date updateDate){
		super();
		this.id = id;
		this.shopId = shopId;
		this.shopPhone = shopPhone;
		this.shopTel = shopTel;
		this.isWifi = isWifi;
		this.isPark = isPark;
		this.isReservation = isReservation;
		this.isJyd = isJyd;
		this.status = status;
		this.personCost = personCost;
		this.hoursStart = hoursStart;
		this.hoursEnd = hoursEnd;
		this.memo = memo;
		this.isApprove = isApprove;
		this.approveLevel = approveLevel;
		this.approveDate = approveDate;
		this.approveUserId = approveUserId;
		this.approveReviews = approveReviews;
		this.isSpread = isSpread;
		this.spreadStartDate = spreadStartDate;
		this.spreadEndDate = spreadEndDate;
		this.reverse1 = reverse1;
		this.reverse2 = reverse2;
		this.reverse3 = reverse3;
		this.reverse4 = reverse4;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public long getShopId(){
		return shopId;
	}

	public void setShopId(long shopId){
		this.shopId = shopId;
	}


	public String getShopPhone(){
		return shopPhone;
	}

	public void setShopPhone(String shopPhone){
		this.shopPhone = shopPhone;
	}


	public String getShopTel(){
		return shopTel;
	}

	public void setShopTel(String shopTel){
		this.shopTel = shopTel;
	}


	public int getIsWifi(){
		return isWifi;
	}

	public void setIsWifi(int isWifi){
		this.isWifi = isWifi;
	}


	public int getIsPark(){
		return isPark;
	}

	public void setIsPark(int isPark){
		this.isPark = isPark;
	}


	public int getIsReservation(){
		return isReservation;
	}

	public void setIsReservation(int isReservation){
		this.isReservation = isReservation;
	}


	public int getIsJyd(){
		return isJyd;
	}

	public void setIsJyd(int isJyd){
		this.isJyd = isJyd;
	}


	public int getStatus(){
		return status;
	}

	public void setStatus(int status){
		this.status = status;
	}


	public Double getPersonCost(){
		return personCost;
	}

	public void setPersonCost(Double personCost){
		this.personCost = personCost;
	}


	public String getHoursStart(){
		return hoursStart;
	}

	public void setHoursStart(String hoursStart){
		this.hoursStart = hoursStart;
	}


	public String getHoursEnd(){
		return hoursEnd;
	}

	public void setHoursEnd(String hoursEnd){
		this.hoursEnd = hoursEnd;
	}


	public String getMemo(){
		return memo;
	}

	public void setMemo(String memo){
		this.memo = memo;
	}


	public int getIsApprove(){
		return isApprove;
	}

	public void setIsApprove(int isApprove){
		this.isApprove = isApprove;
	}


	public int getApproveLevel(){
		return approveLevel;
	}

	public void setApproveLevel(int approveLevel){
		this.approveLevel = approveLevel;
	}


	public Date getApproveDate(){
		return approveDate;
	}

	public void setApproveDate(Date approveDate){
		this.approveDate = approveDate;
	}


	public long getApproveUserId(){
		return approveUserId;
	}

	public void setApproveUserId(long approveUserId){
		this.approveUserId = approveUserId;
	}


	public String getApproveReviews(){
		return approveReviews;
	}

	public void setApproveReviews(String approveReviews){
		this.approveReviews = approveReviews;
	}


	public int getIsSpread(){
		return isSpread;
	}

	public void setIsSpread(int isSpread){
		this.isSpread = isSpread;
	}


	public Date getSpreadStartDate(){
		return spreadStartDate;
	}

	public void setSpreadStartDate(Date spreadStartDate){
		this.spreadStartDate = spreadStartDate;
	}


	public Date getSpreadEndDate(){
		return spreadEndDate;
	}

	public void setSpreadEndDate(Date spreadEndDate){
		this.spreadEndDate = spreadEndDate;
	}


	public String getReverse1(){
		return reverse1;
	}

	public void setReverse1(String reverse1){
		this.reverse1 = reverse1;
	}


	public String getReverse2(){
		return reverse2;
	}

	public void setReverse2(String reverse2){
		this.reverse2 = reverse2;
	}


	public String getReverse3(){
		return reverse3;
	}

	public void setReverse3(String reverse3){
		this.reverse3 = reverse3;
	}


	public String getReverse4(){
		return reverse4;
	}

	public void setReverse4(String reverse4){
		this.reverse4 = reverse4;
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

	public String toString(){
		return "id:"+id+"\t"+"shopId:"+shopId+"\t"+"shopPhone:"+shopPhone+"\t"+"shopTel:"+shopTel+"\t"+"isWifi:"+isWifi+"\t"+"isPark:"+isPark+"\t"+"isReservation:"+isReservation+"\t"+"isJyd:"+isJyd+"\t"+"status:"+status+"\t"+"personCost:"+personCost+"\t"+"hoursStart:"+hoursStart+"\t"+"hoursEnd:"+hoursEnd+"\t"+"memo:"+memo+"\t"+"isApprove:"+isApprove+"\t"+"approveLevel:"+approveLevel+"\t"+"approveDate:"+approveDate+"\t"+"approveUserId:"+approveUserId+"\t"+"approveReviews:"+approveReviews+"\t"+"isSpread:"+isSpread+"\t"+"spreadStartDate:"+spreadStartDate+"\t"+"spreadEndDate:"+spreadEndDate+"\t"+"reverse1:"+reverse1+"\t"+"reverse2:"+reverse2+"\t"+"reverse3:"+reverse3+"\t"+"reverse4:"+reverse4+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate;
	}
}