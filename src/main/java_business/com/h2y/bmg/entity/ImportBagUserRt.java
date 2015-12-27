package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 项目名称：h2ybmg2  
 * 类名称：ImportBagUserRt  
 * 类描述：  导入礼包用户关联
 * 创建人：侯飞龙  
 * 创建时间：2015年6月19日 下午2:34:27  
 * 修改人：侯飞龙
 * 修改时间：2015年6月19日 下午2:34:27  
 * 修改备注：  
 * @version
 */
public class ImportBagUserRt extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyImportBagUserRt";
    private long id;
    private String bagCode;
    private String toAccount;
    private int goodsCount;
    private Date createDate;
    private Date updateDate;
    private Date signDate;
    private String memo;
    private int status;

	public ImportBagUserRt(){
		super();
	}

	public ImportBagUserRt(long id){
		super();
		this.id = id;
	}

	public ImportBagUserRt(long id,String bagCode,String toAccount,int goodsCount,Date createDate,Date updateDate,Date signDate,String memo,int status){
		super();
		this.id = id;
		this.bagCode = bagCode;
		this.toAccount = toAccount;
		this.goodsCount = goodsCount;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.signDate = signDate;
		this.memo = memo;
		this.status = status;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public String getBagCode(){
      return bagCode;
    }
    
    public void setBagCode(String bagCode){
      this.bagCode = bagCode;
    }


    public String getToAccount(){
      return toAccount;
    }
    
    public void setToAccount(String toAccount){
      this.toAccount = toAccount;
    }


    public int getGoodsCount(){
      return goodsCount;
    }
    
    public void setGoodsCount(int goodsCount){
      this.goodsCount = goodsCount;
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


    public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public String getMemo(){
      return memo;
    }
    
    public void setMemo(String memo){
      this.memo = memo;
    }

    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String toString(){
	return "id:"+id+"\t"+"bagCode:"+bagCode+"\t"+"toAccount:"+toAccount+"\t"+"goodsCount:"+goodsCount+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"signDate:"+signDate+"\t"+"memo:"+memo+"\t"+"status:"+status;
    }
}