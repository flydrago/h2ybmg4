package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 项目名称：h2ybmg2  
 * 类名称：AdvertSubjectGoodsRt  
 * 类描述：广告主题商品关联模型  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月8日 上午9:47:16  
 * 修改人：侯飞龙
 * 修改时间：2015年4月8日 上午9:47:16  
 * 修改备注：  
 * @version
 */
public class AdvertSubjectGoodsRt extends BaseObject{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
    public static final String key = "keyAdvertSubjectGoodsRt";
    private long id;
    private long subjectId;
    private long goodsId;
    private long goodsPriceId;
    private Date createDate;
    private int ord;

	public AdvertSubjectGoodsRt(){
		super();
	}

	public AdvertSubjectGoodsRt(long id){
		super();
		this.id = id;
	}

	public AdvertSubjectGoodsRt(long id,long subjectId,long goodsId,long goodsPriceId,Date createDate,int ord){
		super();
		this.id = id;
		this.subjectId = subjectId;
		this.goodsId = goodsId;
		this.goodsPriceId = goodsPriceId;
		this.createDate = createDate;
		this.ord = ord;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getSubjectId(){
      return subjectId;
    }
    
    public void setSubjectId(long subjectId){
      this.subjectId = subjectId;
    }


    public long getGoodsId(){
      return goodsId;
    }
    
    public void setGoodsId(long goodsId){
      this.goodsId = goodsId;
    }


    public long getGoodsPriceId(){
      return goodsPriceId;
    }
    
    public void setGoodsPriceId(long goodsPriceId){
      this.goodsPriceId = goodsPriceId;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public int getOrd(){
      return ord;
    }
    
    public void setOrd(int ord){
      this.ord = ord;
    }

    public String toString(){
	return "id:"+id+"\t"+"subjectId:"+subjectId+"\t"+"goodsId:"+goodsId+"\t"+"goodsPriceId:"+goodsPriceId+"\t"+"createDate:"+createDate+"\t"+"ord:"+ord;
    }
}