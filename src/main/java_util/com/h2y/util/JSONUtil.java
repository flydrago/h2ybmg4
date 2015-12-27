package com.h2y.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.TypeFactory;

/**
 * JSON处理

 * 作者：段晓刚

 * 时间：2014-2-26 下午5:45:36

 * 电子邮件：@qq.com

 * QQ：

 * Gmail :
 */
public class JSONUtil {

	public static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	
	private static ObjectMapper mObjectMapper=new ObjectMapper();
	static{
		SerializationConfig config = mObjectMapper.getSerializationConfig();
		SimpleDateFormat formatter = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss); 
		config.withDateFormat(formatter);
		mObjectMapper.setSerializationConfig(config);
		mObjectMapper.setDateFormat(formatter);
		//设置之后bean中的属性map中无值也不会报错
		mObjectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	

	public static String getJson(Object value){
		
		return getJson(value, yyyy_MM_dd_HH_mm_ss);
	}

	public static String getJson(Object value,String dateFormat){
		//"yyyy-MM-dd HH:mm:ss"
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);  
		SerializationConfig sConfig = mObjectMapper.getSerializationConfig();
		sConfig.withDateFormat(formatter);
		mObjectMapper.setSerializationConfig(sConfig);
		try {
			String json=mObjectMapper.writeValueAsString(value);
			return json;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	@SuppressWarnings("unchecked")
	private static Map<String,Object> getMapFromJson(String json){
		try {  
			return mObjectMapper.readValue(json, Map.class);
		} catch (JsonParseException e) {  
			e.printStackTrace();  
		} catch (JsonMappingException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return null;
	}

	/**
	 * 有对象获取Map
	 * @param value
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Map<String,Object> getMap(Object value){
		if(value!=null){
			String json = "";
			if (value instanceof String) {
				json = (String) value;
			}else {
				json = getJson(value);
			}
			return getMapFromJson(json);
		}else {
			return null;
		}
	}


	/**
	 * Map发序列化为Java对象
	 * @param map
	 * @param clazz
	 * @return
	 */
	public static <T> T getObject(Object value,Class<T> clazz){

		T t = null;
		try {
			String json = "";
			if (value instanceof String) {
				json = (String) value;
			}else {
				json = getJson(value);
			}
			t=  mObjectMapper.readValue(json,clazz);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return t;
	}
	
	/**
	 * List 转换Json
	 * @param list
	 * @return
	 * @update：2014年7月3日 下午3:17:42
	 */
	public static String listToJson(List<?> list){
		
		return getJson(list);
	}
	
	/**
	 * json和List转换
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,Object>> jsonToListMap(String json){

		List<Map<String, Object>> list = null;
		try {
			list = mObjectMapper.readValue(json, List.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public static <T> List<T> jsonToList(String json,Class<T> clazz){

		// 指定容器结构和类型（这里是ArrayList和clazz）
		List<T> list = null;
		try {
			TypeFactory mTypeFactory = TypeFactory.defaultInstance();
			CollectionType ct = mTypeFactory.constructCollectionType(ArrayList.class, clazz);
			list = mObjectMapper.readValue(json,ct);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 如果T确定的情况下可以使用下面的方法：
		// List<T> list = objectMapper.readValue(jsonVal, new TypeReference<List<T>>() {}); 
		return list;
	}

	public static <T> List<T> listToList(List<Map<String,Object>> list,Class<T> clazz){

		List<T> list2 = new ArrayList<T>();
		if(list!=null){
			String json = getJson(list);
			list2 = jsonToList(json, clazz);
		}
		return list2;
	}
}
