package com.h2y.jxc.util;

import java.math.BigDecimal;

/**
 * Double类型数据运算方法 防止Double运算出现异常
 * @author jyd-yfb-02
 *
 */
public class DoubleOperationUtil {

	public static Double accAdd(Double arg1,Double arg2){
		BigDecimal Barg1 = BigDecimal.valueOf(arg1);
		BigDecimal Barg2 = BigDecimal.valueOf(arg2);
		
		BigDecimal res = Barg1.add(Barg2);
		return res.doubleValue();
	}
}
