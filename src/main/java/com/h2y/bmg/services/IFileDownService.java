package com.h2y.bmg.services;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.FileDownMode;

/**
 * 文件下载接口

 * 作者：段晓刚

 * 时间：2012-7-27 下午05:07:20

 * 电子邮件：duanxg@hwttnet.com

 * QQ：2410960521

 * Gmail :
 */
public interface IFileDownService {

	public FileDownMode getFileInfo(HttpServletRequest request,String id);
}
