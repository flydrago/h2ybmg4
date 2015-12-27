package com.h2y.entity;

import java.util.Date;

/**
 * 项目名称：h2yorsos  
 * 类名称：BaseCheck  
 * 类描述：  推送模型
 * 创建人：侯飞龙  
 * 创建时间：2015年4月15日 上午9:29:15  
 * 修改人：侯飞龙
 * 修改时间：2015年4月15日 上午9:29:15  
 * 修改备注：  
 * @version
 */
public class PushToData{

	private String loginType;//推送推向标识
	private String mto; //结果信息
	private String pushcode; //结果信息
	private String tag; //结果信息 按照标签推送	
	private Date createDate;//创建时间
	private Date sendDate;//推送时间
	private String datasourceId;//小达快报id
	private String datasourceType;//数据源类型（订单、小达快报:news）
	private int isAll;
	private int type;

	public PushToData(){
		super();
	}



	public PushToData(String loginType, String mto) {
		super();
		this.loginType = loginType;
		this.mto = mto;
	}



	public PushToData(String loginType, String mto, String pushcode) {
		super();
		this.loginType = loginType;
		this.mto = mto;
		this.pushcode = pushcode;
	}



	public PushToData(String loginType, String mto, String pushcode,
			String tag, Date createDate, Date sendDate) {
		super();
		this.loginType = loginType;
		this.mto = mto;
		this.pushcode = pushcode;
		this.tag = tag;
		this.createDate = createDate;
		this.sendDate = sendDate;
	}



	public PushToData(String loginType, String mto, String pushcode,
			String tag, Date createDate, Date sendDate, String datasourceId,
			String datasourceType,int isAll,int type) {
		super();
		this.loginType = loginType;
		this.mto = mto;
		this.pushcode = pushcode;
		this.tag = tag;
		this.createDate = createDate;
		this.sendDate = sendDate;
		this.datasourceId = datasourceId;
		this.datasourceType = datasourceType;
		this.isAll =  isAll;
		this.type = type;
	}



	public String toString() {
		return "loginType=" + loginType + ", mto=" + mto
				+ ", pushcode=" + pushcode + ", tag=" + tag + ", createDate="
				+ createDate + ", sendDate=" + sendDate + ", datasourceId="
				+ datasourceId + ", datasourceType=" + datasourceType
				+", isAll=" + isAll
				+", type=" + type;
	}


	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getMto() {
		return mto;
	}

	public void setMto(String mto) {
		this.mto = mto;
	}

	public String getPushcode() {
		return pushcode;
	}

	public void setPushcode(String pushcode) {
		this.pushcode = pushcode;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getDatasourceId() {
		return datasourceId;
	}

	public void setDatasourceId(String datasourceId) {
		this.datasourceId = datasourceId;
	}

	public String getDatasourceType() {
		return datasourceType;
	}

	public void setDatasourceType(String datasourceType) {
		this.datasourceType = datasourceType;
	}



	public int getIsAll() {
		return isAll;
	}



	public void setIsAll(int isAll) {
		this.isAll = isAll;
	}



	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}



}