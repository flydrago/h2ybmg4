package com.h2y.bmg.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * 项目名称：h2ygdsos  
 * 类名称：ModuleCodeUitl  
 * 类描述：模块编码生成工具  
 * 创建人：侯飞龙  
 * 创建时间：2015年6月10日 下午2:38:23  
 * 修改人：侯飞龙
 * 修改时间：2015年6月10日 下午2:38:23  
 * 修改备注：  
 * @version
 */
public class ModuleCodeUitl {

	/**
	 * 得到酒库编码
	 * @param account 当前会员账号
	 * @return
	 */
	public static String getSpiritRoomCode(String account){
		
		return account+getRandom(3);
	}
	
	/**
	 * 得到礼包编码
	 * @return
	 */
	public static String getGiftBagCode(){
		
		return getTimeNo()+getRandom(3);
	}
	
	
	/**
	 * 生成时间戳编码，精确到毫秒
	 * @return
	 */
	private static String getTimeNo(){
		
		String timeNo="";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		timeNo=df.format(new Date());
		return timeNo;
	}
	
	/**
	 * 得到随机数
	 * @param length 随机数长度
	 * @return
	 */
	public static String getRandom(int length){
		
		Random random = new Random();
		String randomCode = "";
		for ( int i = 0; i < length; i++ ){
			randomCode += Integer.toString(random.nextInt(9));
		}
		return randomCode;
	}

}
