package com.h2y.bmg.entity;

/**
 * 文件下载模型

 * 作者：段晓刚

 * 时间：2012-7-27 下午05:10:00

 * 电子邮件：duanxg@hwttnet.com

 * QQ：2410960521

 * Gmail :
 */
public class FileDownMode implements java.io.Serializable{

	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	
	private static final long serialVersionUID = 1L;

	private String filePath;
	
	private String saveName;

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public String getSaveName() {
		return saveName;
	}
}
