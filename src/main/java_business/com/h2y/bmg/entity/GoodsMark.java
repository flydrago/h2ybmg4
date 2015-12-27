package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 类描述：商品标签（品牌、产地等） 作者：侯飞龙 时间：2015年1月24日上午9:15:52 邮件：1162040314@qq.com
 */
public class GoodsMark extends BaseObject {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	public static final String key = "keyGoodsMark";
	private long id;
	private long typeId;
	private String typeCode;
	private String markName;
	private String speName;
	private String firSpeName;
	private int status;
	private long ord;
	private Date createDate;
	private String memo;
	private long userId;
	private int isShowApp;
	private int isShowImg;

	public GoodsMark() {
		super();
	}

	public GoodsMark(long id) {
		super();
		this.id = id;
	}

	public GoodsMark(long id, long typeId, String typeCode, String markName,
			String speName, String firSpeName, int status, long ord,
			Date createDate, String memo, long userId,int isShowApp,int isShowImg) {
		super();
		this.id = id;
		this.typeId = typeId;
		this.typeCode = typeCode;
		this.markName = markName;
		this.speName = speName;
		this.firSpeName = firSpeName;
		this.status = status;
		this.ord = ord;
		this.createDate = createDate;
		this.memo = memo;
		this.userId = userId;
		this.isShowApp = isShowApp;
		this.isShowImg = isShowImg;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getMarkName() {
		return markName;
	}

	public void setMarkName(String markName) {
		this.markName = markName;
	}

	public String getSpeName() {
		return speName;
	}

	public void setSpeName(String speName) {
		this.speName = speName;
	}

	public String getFirSpeName() {
		return firSpeName;
	}

	public void setFirSpeName(String firSpeName) {
		this.firSpeName = firSpeName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getOrd() {
		return ord;
	}

	public void setOrd(long ord) {
		this.ord = ord;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getIsShowApp(){
		return isShowApp;
	}

	public void setIsShowApp(int isShowApp){
		this.isShowApp = isShowApp;
	}


	public int getIsShowImg(){
		return isShowImg;
	}

	public void setIsShowImg(int isShowImg){
		this.isShowImg = isShowImg;
	}

	public String toString() {
		return "id:"+id+"\t"+"typeId:"+typeId+"\t"+"typeCode:"+typeCode+"\t"+"markName:"+markName+"\t"+"speName:"+speName+"\t"+"firSpeName:"+firSpeName+"\t"+"status:"+status+"\t"+"ord:"+ord+"\t"+"createDate:"+createDate+"\t"+"memo:"+memo+"\t"+"userId:"+userId+"\t"+"isShowApp:"+isShowApp+"\t"+"isShowImg:"+isShowImg;
	}
}