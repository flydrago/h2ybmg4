package com.h2y.jxc.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.h2y.bmg.basic.WbsKeys.SInvokeKeys;
import com.h2y.util.HttpTookit;
import com.h2y.util.JSONUtil;
import com.h2y.util.StringUtil;

/**
 * 采购测试
 * @author jyd-yfb-02
 *
 */
public class PurchaseTest {

	public static final String server = "http://10.10.10.186:8080/h2ybmg2/";
	
	public static void main(String[] args) {
		purchaseInSave();
	}
	
	public static void purchaseInSave(){
		String url = server+"jxc/purchase/inStorageSave.htm";
		Map<String,Object> postData = new HashMap<String, Object>();
		postData.put("billNo","CGRKD-20150626-0001");
		postData.put("billCustomNo","");
		postData.put("paymentDate","");
		postData.put("receiptsDate", "");
		postData.put("supplierId","1");
		postData.put("storageId", "14");
		postData.put("brokerId", "94");
		postData.put("paymentAccountId","");
		postData.put("currentPayment", "");
		postData.put("notes", "测试第一单");
		postData.put("reviseMark", "0");
		
		Map<String,Object> goodsInfo = new HashMap<String, Object>();
		goodsInfo.put("goodsId",69);
		goodsInfo.put("singlePrice", 699.9);
		goodsInfo.put("goodsCount", 69);
		goodsInfo.put("goodsNotes", "茅台小王子");
		
		List<Map<String,Object>> goodsList = new ArrayList<Map<String,Object>>();
		goodsList.add(goodsInfo);
		
		postData.put("billGoodsList", goodsList);
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put(SInvokeKeys.postData.value(), JSONUtil.getJson(postData));
		params.put(SInvokeKeys.slock.value(),"slock1");
		params.put(SInvokeKeys.skey.value(),"skey2");
		params.put(SInvokeKeys.sid.value(),"sid3");
		String result = HttpTookit.doPost(url, params);
		System.out.println("返回结果：\n"+result);
	}
}
