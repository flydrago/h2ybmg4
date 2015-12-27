package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 项目名称：h2ybmg2  
 * 类名称：CouponsSourceRt  
 * 类描述：  优惠劵来源关联
 * 创建人：侯飞龙  
 * 创建时间：2015年7月15日 下午4:54:09  
 * 修改人：侯飞龙
 * 修改时间：2015年7月15日 下午4:54:09  
 * 修改备注：  
 * @version
 */
public class CouponsSourceRt extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyCouponsSourceRt";
    private long id;
    private long sourceId;
    private long couponsId;
    private String couponsCode;
    private Date createDate;
    private int status;

	public CouponsSourceRt(){
		super();
	}

	public CouponsSourceRt(long id){
		super();
		this.id = id;
	}

	public CouponsSourceRt(long id,long sourceId,long couponsId,String couponsCode,Date createDate,int status){
		super();
		this.id = id;
		this.sourceId = sourceId;
		this.couponsId = couponsId;
		this.couponsCode = couponsCode;
		this.createDate = createDate;
		this.status = status;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getSourceId(){
      return sourceId;
    }
    
    public void setSourceId(long sourceId){
      this.sourceId = sourceId;
    }


    public long getCouponsId(){
      return couponsId;
    }
    
    public void setCouponsId(long couponsId){
      this.couponsId = couponsId;
    }


    public String getCouponsCode(){
      return couponsCode;
    }
    
    public void setCouponsCode(String couponsCode){
      this.couponsCode = couponsCode;
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
	return "id:"+id+"\t"+"sourceId:"+sourceId+"\t"+"couponsId:"+couponsId+"\t"+"couponsCode:"+couponsCode+"\t"+"createDate:"+createDate+"\t"+"status:"+status;
    }
}