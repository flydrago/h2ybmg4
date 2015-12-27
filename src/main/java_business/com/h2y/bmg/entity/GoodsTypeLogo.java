package com.h2y.bmg.entity;

import java.util.Date;

import com.h2y.object.BaseObject;

/**
 * 类描述：商品类型Logo 作者：侯飞龙 时间：2015年2月13日下午2:42:14 邮件：1162040314@qq.com
 */
public class GoodsTypeLogo extends BaseObject {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	public static final String key = "keyGoodsTypeLogo";
	private long id;
	private long typeId;
	private String typeCode;
	private String logoName;
	private String rootPath;
	private String relativePath;
	private String iosFileName;
	private String androidFileName;
	private String wechatFileName;
	private Date createDate;
	private Date startDate;
	private Date endDate;
	private int isDefault;
	private int status;
	private String memo;
	private long userId;

	public GoodsTypeLogo() {
		super();
	}

	public GoodsTypeLogo(long id) {
		super();
		this.id = id;
	}

	public GoodsTypeLogo(long id, long typeId, String typeCode,
			String logoName, String rootPath, String relativePath,
			String iosFileName, String androidFileName, String wechatFileName,
			Date createDate, Date startDate, Date endDate, int isDefault,
			int status, String memo, long userId) {
		super();
		this.id = id;
		this.typeId = typeId;
		this.typeCode = typeCode;
		this.logoName = logoName;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.iosFileName = iosFileName;
		this.androidFileName = androidFileName;
		this.wechatFileName = wechatFileName;
		this.createDate = createDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isDefault = isDefault;
		this.status = status;
		this.memo = memo;
		this.userId = userId;
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

	public String getLogoName() {
		return logoName;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getIosFileName() {
		return iosFileName;
	}

	public void setIosFileName(String iosFileName) {
		this.iosFileName = iosFileName;
	}

	public String getAndroidFileName() {
		return androidFileName;
	}

	public void setAndroidFileName(String androidFileName) {
		this.androidFileName = androidFileName;
	}

	public String getWechatFileName() {
		return wechatFileName;
	}

	public void setWechatFileName(String wechatFileName) {
		this.wechatFileName = wechatFileName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String toString() {
		return "id:" + id + "\t" + "typeId:" + typeId + "\t" + "typeCode:"
				+ typeCode + "\t" + "logoName:" + logoName + "\t" + "rootPath:"
				+ rootPath + "\t" + "relativePath:" + relativePath + "\t"
				+ "iosFileName:" + iosFileName + "\t" + "androidFileName:"
				+ androidFileName + "\t" + "wechatFileName:" + wechatFileName
				+ "\t" + "createDate:" + createDate + "\t" + "startDate:"
				+ startDate + "\t" + "endDate:" + endDate + "\t" + "isDefault:"
				+ isDefault + "\t" + "status:" + status + "\t" + "memo:" + memo
				+ "\t" + "userId:" + userId;
	}
}