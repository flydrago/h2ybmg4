package com.h2y.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 解析xml字符串

 * @author：段晓刚

 * @update：2014年7月31日 下午4:08:47

 * @Email：
 */
public class Dom4jUtil {

	/**
	 * 解析支付宝xml返回结果
	 * @param xml
	 * @return
	 * @update：2014年7月31日 下午4:23:02
	 */
	public static Map<String,Object> readQueryAlipayXmlOut(String xml) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Document doc = null;
		try {
			// 将字符串转为XML
			doc = DocumentHelper.parseText(xml); 
			// 获取根节点
			Element rootElt = doc.getRootElement(); 
			
			//获取头部
			Element isSuccessElement = rootElt.element("is_success");
			String is_success = isSuccessElement.getData()+"";
			resultMap.put("is_success", is_success);

			if(is_success.equals("T")){
				//成功
				Map<String,Object> requestMap = new HashMap<String, Object>();
				//获取request节点数据
				Element reqElement = rootElt.element("request"); 
				// 遍历request节点
				// 获取子节点request下的子节点param
				@SuppressWarnings("unchecked")
				Iterator<Element> paramIt = reqElement.elementIterator("param"); 
				// 遍历Header节点下的Response节点
				while (paramIt.hasNext()) {
					Element itemEle = paramIt.next();
					requestMap.put(itemEle.attributeValue("name"), itemEle.getData());
				}
				//添加到结果Map中
				resultMap.put("request", requestMap);

				//获取response节点数据
				Element resElement = rootElt.element("response"); 

				Map<String,Object> responseMap = new HashMap<String, Object>();
				//获取根节点下的子节点trade
				Element tradeElement = resElement.element("trade"); 
				@SuppressWarnings("unchecked")
				Iterator<Element> tradeIt = tradeElement.elementIterator();
				// 遍历trade节点
				while (tradeIt.hasNext()) {
					Element itemEle = tradeIt.next();
					// 拿到body节点下的子节点result值
					responseMap.put(itemEle.getName(), itemEle.getData());
				}
				//添加到结果Map中
				resultMap.put("response", responseMap);

				//获取sign信息
				Element signElement = rootElt.element("sign");
				String sign = signElement.getData()+"";
				resultMap.put("sign", sign);

				Element sign_typeElement = rootElt.element("sign_type");
				String sign_type = sign_typeElement.getData()+"";
				resultMap.put("sign", sign_type);

			}else {
				//返回错误
				//获取错误数据
				Element errorElement = rootElt.element("error");
				String error = errorElement.getData()+"";
				resultMap.put("error", error);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	//	public static void main(String[] args) {
	//		String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
	//				+ "<alipay><is_success>T</is_success><request><param name=\"_input_charset\">utf-8</param><param name=\"service\">single_trade_query</param><param name=\"partner\">2088311843874216</param><param name=\"out_trade_no\">imooly1001</param></request><response><trade><body>'http://pay.h2y.net/mpay/mpay/alipaynotice.htm'</body><buyer_email>kehuzijinbu023@alipay.com</buyer_email><buyer_id>2088502970160936</buyer_id><discount>0.00</discount><flag_trade_locked>0</flag_trade_locked><gmt_create>2014-07-30 14:53:32</gmt_create><gmt_last_modified_time>2014-07-30 14:54:23</gmt_last_modified_time><gmt_payment>2014-07-30 14:54:23</gmt_payment><is_total_fee_adjust>F</is_total_fee_adjust><operator_role>B</operator_role><out_trade_no>imooly1001</out_trade_no><payment_type>1</payment_type><price>0.01</price><quantity>1</quantity><seller_email>zhifu@h2y.com</seller_email><seller_id>2088311843874216</seller_id><subject>'zftest'</subject><to_buyer_fee>0.00</to_buyer_fee><to_seller_fee>0.01</to_seller_fee><total_fee>0.01</total_fee><trade_no>2014073058047993</trade_no><trade_status>TRADE_SUCCESS</trade_status><use_coupon>F</use_coupon></trade></response><sign>52c948f482403adb73d4c6ad755f0d53</sign><sign_type>MD5</sign_type></alipay>";
	//		Map<String,Object> map = readQueryAlipayXmlOut(xmlString);
	//		//Map map = readStringXmlOut(xmlString);
	//		System.out.println(map);
	//	}
}