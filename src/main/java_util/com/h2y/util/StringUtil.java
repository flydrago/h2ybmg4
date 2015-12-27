package com.h2y.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {

	/**
	 * 对象转换为字符串，避免"null"的出现
	 * @param obj
	 * @return
	 */
	public static String objectToString(Object obj){
		return obj == null ? null : obj+"";
	}
	
	/**
	 * 判断字符串 是否 为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*"); 
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false; 
		} 
		return true; 
	}
}
