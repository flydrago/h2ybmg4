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
public class PushMsg{

	private String title;//结果标识（0：失败、1：成功）
	private String describtion; //结果信息
	private String body; //结果信息
	private Date createDate;
	private Date sendDate;//推送时间
	private String mto;//
	private String channelId;//pushcode
	private String tag;
	private String topic;
	private int type;
	private String datasourceId;//小达快报id
	private String datasourceType;//数据源类型（订单、小达快报:news）

	public PushMsg(){
		super();
	}






	public PushMsg(String title, String describtion, String body,
			Date createDate, Date sendDate, String mto, String channelId,
			String tag, String topic, int type) {
		super();
		this.title = title;
		this.describtion = describtion;
		this.body = body;
		this.createDate = createDate;
		this.sendDate = sendDate;
		this.mto = mto;
		this.channelId = channelId;
		this.tag = tag;
		this.topic = topic;
		this.type = type;
	}






	public PushMsg(String title, String describtion, String body,
			Date createDate, Date sendDate, String mto, String channelId,
			String tag, String topic, int type, String datasourceId,
			String datasourceType) {
		super();
		this.title = title;
		this.describtion = describtion;
		this.body = body;
		this.createDate = createDate;
		this.sendDate = sendDate;
		this.mto = mto;
		this.channelId = channelId;
		this.tag = tag;
		this.topic = topic;
		this.type = type;
		this.datasourceId = datasourceId;
		this.datasourceType = datasourceType;
	}






	public String toString() {
		return "title=" + title + ", describtion=" + describtion
				+ ", body=" + body + ", createDate=" + createDate
				+ ", sendDate=" + sendDate + ", mto=" + mto + ", channelId="
				+ channelId + ", tag=" + tag + ", topic=" + topic + ", type="
				+ type + ", datasourceId=" + datasourceId
				+ ", datasourceType=" + datasourceType;
	}




	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescribtion() {
		return describtion;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
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

	public String getMto() {
		return mto;
	}

	public void setMto(String mto) {
		this.mto = mto;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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










}