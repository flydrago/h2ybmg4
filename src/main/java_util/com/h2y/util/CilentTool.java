package com.h2y.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 

 * 作者：段晓刚

 * 时间：2012-8-16 上午09:10:13

 * 电子邮件：duanxg@hwttnet.com

 * QQ：2410960521

 * Gmail :
 */
public class CilentTool{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(CilentTool.class);
	private HttpServletRequest request;
	private String headerUserAgent;
	private CilentTool (HttpServletRequest request){
		this.request=request;
		headerUserAgent=request.getHeader("user-agent");
		//log.debug(headerUserAgent);
	}
	public static CilentTool getInstance(HttpServletRequest request){
		return new CilentTool(request);
	}
	public String getJson(){
		
		return "'browser':'"+getBrowser()+"','os':'"+getOs()+"','referer':'"+getReferer()+"'";
	}
	
	public  String getBrowser() {
		String browserVersion = null;
		if (headerUserAgent==null) {
			return "";
		}
		if (headerUserAgent.indexOf("MSIE") > 0) {
			browserVersion = "IE";
		} else if (headerUserAgent.indexOf("Firefox") > 0) {
			browserVersion = "Firefox";
		} else if (headerUserAgent.indexOf("Chrome") > 0) {
			browserVersion = "Chrome";
		} else if (headerUserAgent.indexOf("Safari") > 0) {
			browserVersion = "Safari";
		} else if (headerUserAgent.indexOf("Camino") > 0) {
			browserVersion = "Camino";
		} else if (headerUserAgent.indexOf("Konqueror") > 0) {
			browserVersion = "Konqueror";
		}
		//log.debug("Customer browser：" + browserVersion);
		return browserVersion;
	}
	public String getReferer(){
		String referer=request.getHeader("Referer");
		//log.debug("Customer Referer ：" + referer);
		return referer;
	}
	public  String getOs() {
		
		String systenInfo = null;
		if (headerUserAgent==null) {
			return "";
		}
		// 得到用户的操作系统
		if (headerUserAgent.indexOf("NT 6.0") > 0) {
			systenInfo = "Windows Vista/Server 2008";
		} else if (headerUserAgent.indexOf("NT 5.2") > 0) {
			systenInfo = "Windows Server 2003";
		} else if (headerUserAgent.indexOf("NT 5.1") > 0) {
			systenInfo = "Windows XP";
		} else if (headerUserAgent.indexOf("NT 6.0") > 0) {
			systenInfo = "Windows Vista";
		} else if (headerUserAgent.indexOf("NT 6.1") > 0) {
			systenInfo = "Windows 7";
		} else if (headerUserAgent.indexOf("NT 6.2") > 0) {
			systenInfo = "Windows Slate";
		} else if (headerUserAgent.indexOf("NT 6.3") > 0) {
			systenInfo = "Windows 9";
		} else if (headerUserAgent.indexOf("NT 5") > 0) {
			systenInfo = "Windows 2000";
		} else if (headerUserAgent.indexOf("NT 4") > 0) {
			systenInfo = "Windows NT4";
		} else if (headerUserAgent.indexOf("Me") > 0) {
			systenInfo = "Windows Me";
		} else if (headerUserAgent.indexOf("98") > 0) {
			systenInfo = "Windows 98";
		} else if (headerUserAgent.indexOf("95") > 0) {
			systenInfo = "Windows 95";
		} else if (headerUserAgent.indexOf("Mac") > 0) {
			systenInfo = "Mac";
		} else if (headerUserAgent.indexOf("Unix") > 0) {
			systenInfo = "UNIX";
		} else if (headerUserAgent.indexOf("Linux") > 0) {
			systenInfo = "Linux";
		} else if (headerUserAgent.indexOf("SunOS") > 0) {
			systenInfo = "SunOS";
		}
		
		return systenInfo;
	}
	
	
	public String getIpAddr(HttpServletRequest request) { 
		
		String ip = request.getHeader("X-Forwarded-For");
		 
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("Proxy-Client-IP");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getRemoteAddr();
	    }
        return ip; 
     } 
}
