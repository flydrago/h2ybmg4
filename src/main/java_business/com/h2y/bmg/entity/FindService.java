package com.h2y.bmg.entity;



import java.util.Date;

/**
 * FindService Model create
 * @author hwttnet
 * version:1.2
 * time:2015-04-17
 * email:info@hwttnet.com
 */

public class FindService{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	public static final String key = "keyFindService";
	private long id;
	private String serviceCode;
	private String servicePrefix;
	private long parentId;
	private String name;
	private String rootPath;
	private String relativePath;
	private String iosFileName;
	private String androidFileName;
	private String wechatFileName;
	private Date createDate;
	private Date updateDate;
	private int status;
	private String memo;
	private int ord;
	private String serviceUrl;
	private int clickEvent;
	private String urlParams;
	private int dataType;
	private int serviceType;
	private int isLogin;

	public FindService(){
		super();
	}

	public FindService(long id){
		super();
		this.id = id;
	}

	public FindService(long id,String serviceCode,String servicePrefix,long parentId,String name,String rootPath,String relativePath,String iosFileName,String androidFileName,String wechatFileName,Date createDate,Date updateDate,int status,String memo,int ord,String serviceUrl,int clickEvent,String urlParams,int dataType,int serviceType,int isLogin){
		super();
		this.id = id;
		this.serviceCode = serviceCode;
		this.servicePrefix = servicePrefix;
		this.parentId = parentId;
		this.name = name;
		this.rootPath = rootPath;
		this.relativePath = relativePath;
		this.iosFileName = iosFileName;
		this.androidFileName = androidFileName;
		this.wechatFileName = wechatFileName;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.status = status;
		this.memo = memo;
		this.ord = ord;
		this.serviceUrl = serviceUrl;
		this.clickEvent = clickEvent;
		this.urlParams = urlParams;
		this.dataType = dataType;
		this.serviceType = serviceType;
		this.isLogin = isLogin;
	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public String getServiceCode(){
		return serviceCode;
	}

	public void setServiceCode(String serviceCode){
		this.serviceCode = serviceCode;
	}


	public String getServicePrefix(){
		return servicePrefix;
	}

	public void setServicePrefix(String servicePrefix){
		this.servicePrefix = servicePrefix;
	}


	public long getParentId(){
		return parentId;
	}

	public void setParentId(long parentId){
		this.parentId = parentId;
	}


	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}


	public String getRootPath(){
		return rootPath;
	}

	public void setRootPath(String rootPath){
		this.rootPath = rootPath;
	}


	public String getRelativePath(){
		return relativePath;
	}

	public void setRelativePath(String relativePath){
		this.relativePath = relativePath;
	}


	public String getIosFileName(){
		return iosFileName;
	}

	public void setIosFileName(String iosFileName){
		this.iosFileName = iosFileName;
	}


	public String getAndroidFileName(){
		return androidFileName;
	}

	public void setAndroidFileName(String androidFileName){
		this.androidFileName = androidFileName;
	}


	public String getWechatFileName(){
		return wechatFileName;
	}

	public void setWechatFileName(String wechatFileName){
		this.wechatFileName = wechatFileName;
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


	public int getStatus(){
		return status;
	}

	public void setStatus(int status){
		this.status = status;
	}


	public String getMemo(){
		return memo;
	}

	public void setMemo(String memo){
		this.memo = memo;
	}


	public int getOrd(){
		return ord;
	}

	public void setOrd(int ord){
		this.ord = ord;
	}


	public String getServiceUrl(){
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl){
		this.serviceUrl = serviceUrl;
	}


	public int getClickEvent(){
		return clickEvent;
	}

	public void setClickEvent(int clickEvent){
		this.clickEvent = clickEvent;
	}


	public String getUrlParams(){
		return urlParams;
	}

	public void setUrlParams(String urlParams){
		this.urlParams = urlParams;
	}


	public int getDataType(){
		return dataType;
	}

	public void setDataType(int dataType){
		this.dataType = dataType;
	}


	public int getServiceType(){
		return serviceType;
	}

	public void setServiceType(int serviceType){
		this.serviceType = serviceType;
	}


	public int getIsLogin(){
		return isLogin;
	}

	public void setIsLogin(int isLogin){
		this.isLogin = isLogin;
	}

	public String toString(){
		return "id:"+id+"\t"+"serviceCode:"+serviceCode+"\t"+"servicePrefix:"+servicePrefix+"\t"+"parentId:"+parentId+"\t"+"name:"+name+"\t"+"rootPath:"+rootPath+"\t"+"relativePath:"+relativePath+"\t"+"iosFileName:"+iosFileName+"\t"+"androidFileName:"+androidFileName+"\t"+"wechatFileName:"+wechatFileName+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"status:"+status+"\t"+"memo:"+memo+"\t"+"ord:"+ord+"\t"+"serviceUrl:"+serviceUrl+"\t"+"clickEvent:"+clickEvent+"\t"+"urlParams:"+urlParams+"\t"+"dataType:"+dataType+"\t"+"serviceType:"+serviceType+"\t"+"isLogin:"+isLogin;
	}
}