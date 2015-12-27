package com.h2y.bmg.entity;




/**
 * FindActivityGoods Model create
 * @author hwttnet
 * version:1.2
 * time:2015-04-17
 * email:info@hwttnet.com
 */

public class FindActivityGoods{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	public static final String key = "keyFindActivityGoods";
	private long id;
	private long activityId;
	private long goodsId;
	private String reverse1;
	private String reverse2;

	public FindActivityGoods(){
		super();
	}

	public FindActivityGoods(long id){
		super();
		this.id = id;
	}

	public FindActivityGoods(long id,long activityId,long goodsId,String reverse1,String reverse2){
		super();
		this.id = id;
		this.activityId = activityId;
		this.goodsId = goodsId;
		this.reverse1 = reverse1;
		this.reverse2 = reverse2;
	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public long getActivityId(){
		return activityId;
	}

	public void setActivityId(long activityId){
		this.activityId = activityId;
	}


	public long getGoodsId(){
		return goodsId;
	}

	public void setGoodsId(long goodsId){
		this.goodsId = goodsId;
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

	public String toString(){
		return "id:"+id+"\t"+"activityId:"+activityId+"\t"+"goodsId:"+goodsId+"\t"+"reverse1:"+reverse1+"\t"+"reverse2:"+reverse2;
	}
}