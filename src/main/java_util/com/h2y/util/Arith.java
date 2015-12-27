package com.h2y.util;

import java.math.BigDecimal;

/**
 * 项目名称：h2yorsos  
 * 类名称：Arith  
 * 类描述：Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精确的浮点数运算，包括加减乘除和四舍五入。     
 * 创建人：侯飞龙  
 * 创建时间：2015年5月12日 下午2:25:44  
 * 修改人：侯飞龙
 * 修改时间：2015年5月12日 下午2:25:44  
 * 修改备注：  
 * @version
 */
public class Arith {
	
    //默认除法运算精度   
    private static final int DEF_DIV_SCALE = 2;

    /**
     *   提供精确的加法运算。
     *   @param   v1   被加数
     *   @param   v2   加数
     *   @return   两个参数的和
     */
    public static double add(Double v1, Double v2) {
    	
        BigDecimal b1 = new BigDecimal(Double.toString(setDefaultValue(v1)));
        BigDecimal b2 = new BigDecimal(Double.toString(setDefaultValue(v2)));
        return b1.add(b2).doubleValue();
    }

    /**
     *   提供精确的减法运算。
     *   @param   v1   被减数
     *   @param   v2   减数
     *   @return   两个参数的差
     */
    public static double sub(Double v1, Double v2) {
    	
        BigDecimal b1 = new BigDecimal(Double.toString(setDefaultValue(v1)));
        BigDecimal b2 = new BigDecimal(Double.toString(setDefaultValue(v2)));
        return b1.subtract(b2).doubleValue();
    }

    /**
     *   提供精确的乘法运算。
     *   @param   v1   被乘数
     *   @param   v2   乘数
     *   @return   两个参数的积
     */
    public static double mul(Double v1, Double v2) {
    	
        BigDecimal b1 = new BigDecimal(Double.toString(setDefaultValue(v1)));
        BigDecimal b2 = new BigDecimal(Double.toString(setDefaultValue(v2)));
        return b1.multiply(b2).doubleValue();
    }

    /**
     *   提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     *   小数点以后2位，以后的数字四舍五入。
     *   @param   v1   被除数
     *   @param   v2   除数
     *   @return   两个参数的商
     */
    public static double div(Double v1, Double v2) {
    	
        return div(setDefaultValue(v1), setDefaultValue(v2), DEF_DIV_SCALE);
    }

    /**
     *   提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     *   定精度，以后的数字四舍五入。
     *   @param   v1   被除数
     *   @param   v2   除数
     *   @param   scale   表示表示需要精确到小数点以后几位。
     *   @return   两个参数的商
     */
    public static double div(Double v1, Double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                "The   scale   must   be   a   positive   integer   or   zero");
        }

        BigDecimal b1 = new BigDecimal(Double.toString(setDefaultValue(v1)));
        BigDecimal b2 = new BigDecimal(Double.toString(setDefaultValue(v2)));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     *   提供精确的小数位四舍五入处理。
     *   @param   v   需要四舍五入的数字
     *   @param   scale   小数点后保留几位
     *   @return   四舍五入后的结果
     */
    public static double round(Double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                "The   scale   must   be   a   positive   integer   or   zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(setDefaultValue(v)));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    
    /**
     *   提供精确的小数位四舍五入处理。
     *   @param   v   需要四舍五入的数字（小数点后保留两位位）
     *   @return   四舍五入后的结果
     */
    public static double round(Double v) {
        return round(setDefaultValue(v), DEF_DIV_SCALE);
    }
    
    /**
     * 设置默认值0
     * @param value
     * @return
     */
    private static Double setDefaultValue(Double value){
		
		return value instanceof Double?value:0d;
	}
}
