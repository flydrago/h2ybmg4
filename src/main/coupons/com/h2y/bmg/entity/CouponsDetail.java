package com.h2y.bmg.entity;

import com.h2y.object.BaseObject;


/**
 * 项目名称：h2ybmg2  
 * 类名称：CouponsDetail  
 * 类描述：优惠券详细  
 * 创建人：侯飞龙  
 * 创建时间：2015年7月3日 下午3:00:28  
 * 修改人：侯飞龙
 * 修改时间：2015年7月3日 下午3:00:28  
 * 修改备注：  
 * @version
 */
public class CouponsDetail extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyCouponsDetail";
    private long id;
    private long couponsId;
    private String couponsCode;
    private String couponsRule;

	public CouponsDetail(){
		super();
	}

	public CouponsDetail(long id){
		super();
		this.id = id;
	}

	public CouponsDetail(long id,long couponsId,String couponsCode,String couponsRule){
		super();
		this.id = id;
		this.couponsId = couponsId;
		this.couponsCode = couponsCode;
		this.couponsRule = couponsRule;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
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


    public String getCouponsRule(){
      return couponsRule;
    }
    
    public void setCouponsRule(String couponsRule){
      this.couponsRule = couponsRule;
    }

    public String toString(){
	return "id:"+id+"\t"+"couponsId:"+couponsId+"\t"+"couponsCode:"+couponsCode+"\t"+"couponsRule:"+couponsRule;
    }
}