package com.h2y.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * base64转码

 * 作者：段晓刚

 * 时间：2011-10-24 下午08:56:53

 * 电子邮件：duanxg@hwttnet.com

 * QQ：

 * Gmail :
 */
public class Base64Util {  

	/**
	 * 等号替换部分
	 */
	private static String EQUAL_SIGN_STR = "_EQUAL_SIGN_STR_";

	/**
	 * 冒号替换部分
	 */
	private static String COLON_SIGN_STR = "_COLON_SIGN_STR_";
	
	/**
	 * 斜杆替换部分
	 */
	private static String SPRIT_SIGN_STR = "SPRIT_SIGN_STR";

	/**
	 * 反斜杠替换部分
	 */
	private static String BACKSLASH_SIGN_STR = "BACKSLASH_SIGN_STR";
	
	/**
	 * 编码
	 * 有byte[]通过Base64转码为String
	 * @param bytes
	 * @return
	 */
	public static String encodeBytes(byte[] bytes){

		String encoded = null;   

		encoded = Base64.encodeBytes(bytes); 

		return encoded;
	}

	/**
	 * 编码
	 * 有byte[]通过Base64转码为String
	 * @param bytes
	 * @return
	 */
	public static byte[] encodeString(String str){

		String encoded = null;   

		encoded = Base64.encodeBytes(str.getBytes()); 

		return encoded.getBytes();
	}

	/**
	 * 编码
	 * 转换成Android中传递的编码
	 * @param bytes
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String encodeBytesInAndroid(String encode) throws UnsupportedEncodingException{

		String encoded = null;   

		encoded = Base64.encodeBytes(encode.getBytes("UTF-8")); 
		//encoded = Base64.encode(encode.getBytes()); 
		if(encoded.contains("=")){
			encoded = encoded.replaceAll("=", EQUAL_SIGN_STR);
		}
		if(encoded.contains(":")){
			encoded = encoded.replaceAll(":", COLON_SIGN_STR);
		}
		if(encoded.contains("/")){
			encoded = encoded.replaceAll("/", SPRIT_SIGN_STR);
		}
		if(encoded.contentEquals("\\")){
			encoded = encoded.replaceAll("\\", BACKSLASH_SIGN_STR);
		}

		return encoded;
	}


	/**
	 * 解码
	 * @param encoded
	 * @return
	 * @throws IOException
	 */
	public static byte[] decodeString(String encoded) throws IOException{

		byte[] decoded = null;

		decoded = Base64.decode(encoded);  

		return decoded;
	}

	/**
	 * 解码
	 * @param encoded
	 * @return
	 * @throws IOException
	 */
	public static String decodeBytes(byte[] encoded) throws IOException{

		byte[] decoded = null;

		decoded = Base64.decode(new String(encoded));  

		return new String(decoded);
	}
	
	/**
	 * 解码
	 * @param encoded
	 * @return
	 * @throws IOException 
	 * @throws IOException
	 */
	public static String decodeBytesInAndroid(String encoded) throws IOException{

		byte[] decoded = null;
		
		String str = "";

		if(encoded.contains(EQUAL_SIGN_STR)){
			encoded = encoded.replaceAll(EQUAL_SIGN_STR,"=");
		}
		if(encoded.contains(COLON_SIGN_STR)){
			encoded = encoded.replaceAll(COLON_SIGN_STR,":");
		}
		if(encoded.contains(SPRIT_SIGN_STR)){
			encoded = encoded.replaceAll(SPRIT_SIGN_STR,"/");
		}
		if(encoded.contentEquals(BACKSLASH_SIGN_STR)){
			encoded = encoded.replaceAll(BACKSLASH_SIGN_STR,"\\");
		}
		//decoded = Base64.decode(encoded);  
		decoded = Base64.decode(encoded);  

		//byte[] b = new String(decoded).getBytes("ISO-8859-1");
		//System.out.println("tt_1");
		try {
			str = new String(decoded,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	    public static void main(String[] args) throws Exception {   
	//        byte[] data = "<p>第二节 环境搭建</p>".getBytes();//"http://lggege.iteye.com".getBytes();   
	//          
	//        String encoded = encodeBytes(data);
	//        byte[] decoded = decode(encoded);
	//           
	//        // 编码（加密）   
	////      encoded = (new BASE64Decoder()).decodeBuffer(data);   
	////      decoded = (new BASE64Decoder()).decodeBuffer(encoded);   
	//           
	        // 解密（解密）   
	          
	        String str = "eyJjb3JwSWQiOjEsImlkIjoyLCJpZkFkbWluIjoxLCJpZkRlbGV0ZSI6MCwiaWZMb2NrIjowLCJpbWVpIjoiIiwiaW1zaSI6IiIsImxvZ2luTmFtZSI6ImFkbWluIiwibW9iaWxlIjoiIiwib3JkZXJCeUtleSI6IiIsInBhc3N3b3JkIjoiZTEwYWRjMzk0OWJhNTlhYmJlNTZlMDU3ZjIwZjg4M2UiLCJ1c2VyQ29kZSI6ImFkbWluIiwidXNlck5hbWUiOiLnrqHnkIblkZgifQ_EQUAL_SIGN_STR__EQUAL_SIGN_STR_";

	        String decode = Base64Util.decodeBytesInAndroid(str);
	        
	        System.out.println(decode);
	        
	        System.out.println(Base64Util.encodeBytesInAndroid(decode));
	    }   
}