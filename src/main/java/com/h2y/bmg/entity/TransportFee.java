package com.h2y.bmg.entity;


import java.util.Date;

/**
 * TransportFee Model create
 * @author hwttnet
 * version:1.2
 * time:2015-02-02
 * email:info@hwttnet.com
 */

public class TransportFee{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyTransportFee";
    private long id;
    private long unitsId;
    private String unitsZoneCode;
    private String desZoneCode;
    private String desZoneName;
    private Double feeAmount;
    private String status;
    private Date createDate;
    private long createUserId;
    private Date updateDate;
    private long updateUserId;
    private String remark;

	public TransportFee(){
		super();
	}

	public TransportFee(long id){
		super();
		this.id = id;
	}

	 
  
    public TransportFee(long id, long unitsId, String unitsZoneCode,
			String desZoneCode, String desZoneName, Double feeAmount,
			String status, Date createDate, long createUserId, Date updateDate,
			long updateUserId, String remark) {
		super();
		this.id = id;
		this.unitsId = unitsId;
		this.unitsZoneCode = unitsZoneCode;
		this.desZoneCode = desZoneCode;
		this.desZoneName = desZoneName;
		this.feeAmount = feeAmount;
		this.status = status;
		this.createDate = createDate;
		this.createUserId = createUserId;
		this.updateDate = updateDate;
		this.updateUserId = updateUserId;
		this.remark = remark;
	}

	public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getUnitsId(){
      return unitsId;
    }
    
    public void setUnitsId(long unitsId){
      this.unitsId = unitsId;
    }


    public String getUnitsZoneCode(){
      return unitsZoneCode;
    }
    
    public void setUnitsZoneCode(String unitsZoneCode){
      this.unitsZoneCode = unitsZoneCode;
    }


    public String getDesZoneCode(){
      return desZoneCode;
    }
    
    public void setDesZoneCode(String desZoneCode){
      this.desZoneCode = desZoneCode;
    }
    
    


    public String getDesZoneName() {
		return desZoneName;
	}

	public void setDesZoneName(String desZoneName) {
		this.desZoneName = desZoneName;
	}

	public Double getFeeAmount(){
      return feeAmount;
    }
    
    public void setFeeAmount(Double feeAmount){
      this.feeAmount = feeAmount;
    }


    public String getStatus(){
      return status;
    }
    
    public void setStatus(String status){
      this.status = status;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public long getCreateUserId(){
      return createUserId;
    }
    
    public void setCreateUserId(long createUserId){
      this.createUserId = createUserId;
    }
    


    public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getRemark(){
      return remark;
    }
    
    public void setRemark(String remark){
      this.remark = remark;
    }

    public String toString(){
	return "id:"+id+"\t"+"unitsId:"+unitsId+"\t"+"unitsZoneCode:"+unitsZoneCode+"\t"+"desZoneCode:"+desZoneCode+"\t"+"feeAmount:"+feeAmount+"\t"+"status:"+status+"\t"+"createDate:"+createDate+"\t"+"createUserId:"+createUserId+"\t"+"remark:"+remark;
    }
}