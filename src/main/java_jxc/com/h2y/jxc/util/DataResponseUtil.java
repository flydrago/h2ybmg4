package com.h2y.jxc.util;

import java.util.HashMap;
import java.util.Map;
import com.h2y.bmg.basic.WbsKeys.SInvokeKeys;


/**
 * 类名称：DataResponseUtil  
 * 类描述：  服务数据响应数据工具类
 * @version
 */
public class DataResponseUtil{
	
	/**
	 * 得到返回的结果数据
	 * @param reqMap 访问的参数
	 * @param resultFlag 返回结果标识（0：失败、1：成功、其他自定）
	 * @param resultMsg 返回结果提示信息
	 * @return
	 */
	public static Map<String,Object> getResultData(Map<String,Object> reqMap,int resultFlag,String resultMsg) {
		
		return getResultData(reqMap, resultFlag, resultMsg, null);
    }
	
	
	/**
	 * 得到返回的结果数据
	 * @param reqMap 访问的参数
	 * @param resultFlag 返回结果标识（0：失败、1：成功、其他自定）
	 * @param resultMsg 返回结果提示信息
	 * @param resultData 返回结果数据
	 * @return
	 */
	public static Map<String,Object> getResultData(Map<String,Object> reqMap,int resultFlag,String resultMsg,Object resultData) {
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put(SInvokeKeys.slock.value(),reqMap.get(SInvokeKeys.slock.value()));
		resultMap.put(SInvokeKeys.skey.value(),reqMap.get(SInvokeKeys.skey.value()));
		resultMap.put(SInvokeKeys.sid.value(),reqMap.get(SInvokeKeys.sid.value()));
		resultMap.put(SInvokeKeys.os.value(),reqMap.get(SInvokeKeys.os.value()));
		resultMap.put(SInvokeKeys.osv.value(),reqMap.get(SInvokeKeys.osv.value()));
		resultMap.put(SInvokeKeys.appv.value(),reqMap.get(SInvokeKeys.appv.value()));
		
		resultMap.put(SInvokeKeys.resultFlag.value(), resultFlag);
		resultMap.put(SInvokeKeys.resultMsg.value(), resultMsg);
		if (null==resultData) {
			resultData = new HashMap<String, Object>();
		}
		resultMap.put(SInvokeKeys.resultData.value(), resultData);
		
		return resultMap;
    } 
}
