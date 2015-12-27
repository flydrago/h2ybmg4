package com.h2y.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则验证工具类

 * 作者：段晓刚

 * 时间：2014年2月27日 上午10:54:43

 * 电子邮件：@qq.com

 * QQ：

 * Gmail :
 */
public class MatcherUtil {

	/**
	 * 判断手机格式是否正确
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		
		if (null==mobiles) {
			return false;
		}

		Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(14[0-9])|(17[0-9]))\\d{8}$");

		Matcher m = p.matcher(mobiles);

		return m.matches();
	}

	/**
	 * 判断email格式是否正确
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		
		if (null==email) {
			return false;
		}

		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

		Pattern p = Pattern.compile(str);

		Matcher m = p.matcher(email);

		return m.matches();
	}
	
	
	/**  
     * 检查浮点数  
     * @param num  检查字符串
     * @return  
     */  
    public static boolean checkFloat(String num){
        return checkFloat(num,"");   
    } 
	
    /**  
     * 检查浮点数  
     * @param num  检查字符串
     * @param type "+0":非负浮点数 "+":正浮点数 "-0":非正浮点数 "-":负浮点数 "":浮点数  
     * @return  
     */  
    public static boolean checkFloat(String num,String type){
    	if (null==num) {
			return false;
		}
    	
        String eL = "";   
        if(type.equals("+0"))eL = "^\\d+(\\.\\d+)?$";//非负浮点数   
        else if(type.equals("+"))eL = "^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$";//正浮点数   
        else if(type.equals("-0"))eL = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";//非正浮点数   
        else if(type.equals("-"))eL = "^(-((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*)))$";//负浮点数   
        else eL = "^(-?\\d+)(\\.\\d+)?$";//浮点数   
        Pattern p = Pattern.compile(eL);   
        Matcher m = p.matcher(num);   
        return m.matches();   
    } 
    
    /**  
     * 检查整数  
     * @param num 检查字符串
     * @return  
     */  
    public static boolean checkNumber(String num){  
    	
        return checkNumber(num, "");   
    }  
    
    /**  
     * 检查整数  
     * @param num  检查字符串
     * @param type "+0":非负整数 "+":正整数 "-0":非正整数 "-":负整数 "":整数  
     * @return  
     */  
    public static boolean checkNumber(String num,String type){   
    	
		if (null==num) {
			return false;
		}
		
        String eL = "";   
        if(type.equals("+0"))eL = "^\\d+$";//非负整数   
        else if(type.equals("+"))eL = "^\\d*[1-9]\\d*$";//正整数   
        else if(type.equals("-0"))eL = "^((-\\d+)|(0+))$";//非正整数   
        else if(type.equals("-"))eL = "^-\\d*[1-9]\\d*$";//负整数   
        else eL = "^-?\\d+$";//整数   
        Pattern p = Pattern.compile(eL);   
        Matcher m = p.matcher(num);   
        return m.matches();   
    }  
    
    
	public static boolean isSqlAttack(String sql){
		
		String reg = "^.*where|select|update|truncate|drop|delete|exec.*$";
		Pattern p = Pattern.compile(reg);    
		sql = sql.toLowerCase();
		return p.matcher(sql).find();
	}
    
    
    public static void main(String[] args) {
		
    	
    	System.out.println(checkFloat("11231213213132123123132.456465464564654600", ""));
    	System.out.println(checkFloat("12.0.0", ""));
	}
}
