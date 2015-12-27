package com.h2y.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * 使用反射对对象进行赋值

 * 作者：段晓刚

 * 时间：2011-10-24 下午02:48:43

 * 电子邮件：duanxg@hwttnet.com

 * QQ：

 * Gmail :
 */
public class ObjectRefUtil {


	/**
	 * 取Bean的属性和值对应关系的MAP
	 * @param bean
	 * @return Map
	 */
	public static Map<String,Object> getFieldValueMap(Object bean) {

		Class<?> cls = bean.getClass();
		Map<String,Object> valueMap = new HashMap<String,Object>();
		// 取出bean里的所有方法
		Method[] methods = cls.getDeclaredMethods();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			try {
				String fieldType = field.getType().getSimpleName();
				String fieldGetName = parGetName(field.getName());
				if (!checkGetMet(methods, fieldGetName)) {
					continue;
				}
				Method fieldGetMet = cls.getMethod(fieldGetName, new Class[] {});
				Object fieldVal = fieldGetMet.invoke(bean, new Object[] {});
				String result = null;
				if("String".equals(fieldType)){

					result = String.valueOf(fieldVal);

					//进行编码转换获取UNICODE
					//result = EncodeHelper.encode(fieldVal.toString(), UNICODE);
				}else  if ("Date".equals(fieldType)) {
					result = fmtDate((Date) fieldVal);
				}else if ("Integer".equals(fieldType) || "int".equals(fieldType) || "Long".equalsIgnoreCase(fieldType)
						|| "Double".equalsIgnoreCase(fieldType) || "Boolean".equalsIgnoreCase(fieldType)) {
					if(null != fieldVal){
						result = String.valueOf(fieldVal);
					}
				}else{
					if(null != fieldVal){
						result = String.valueOf(fieldVal);
					}

					//进行编码转换获取UNICODE
					//result = EncodeHelper.encode(result, UNICODE);
				}

				valueMap.put(field.getName(), result);
			} catch (Exception e) {
				continue;
			}
		}
		return valueMap;
	}

	/**
	 * set属性的值到Bean
	 * @param bean
	 * @param valMap
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static <T> T setFieldValue(Class<T> cls, Map<?,?> valMap) throws InstantiationException, IllegalAccessException {

		//重新加载对象
		T bean = cls.newInstance();

		//Class<?> cls = bean.getClass();
		// 取出bean里的所有方法
		Method[] methods = cls.getDeclaredMethods();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			try {
				String fieldSetName = parSetName(field.getName());
				if (!checkSetMet(methods, fieldSetName)) {
					continue;
				}
				Method fieldSetMet = cls.getMethod(fieldSetName, field.getType());
				String value = valMap.get(field.getName())+"";

				String fieldType = field.getType().getSimpleName();


				if (null != value && !"".equals(value) && !"null".equals(value)) {

				}else {
					if("Long".equals(fieldType) || "Integer".equals(fieldType) || "int".equals(fieldType))
						value = "0";
					else {
						value = "";
					}
				}


				if ("String".equals(fieldType)) {
					//进行编码转换获取UTF-8
					//value = EncodeHelper.encode(value, UTF8);
					fieldSetMet.invoke(bean, value);
				} else if ("Date".equals(fieldType)) {
					Date temp = parseDate(value);
					fieldSetMet.invoke(bean, temp);
				} else if ("Integer".equals(fieldType) || "int".equals(fieldType)) {
					Integer intval = Integer.parseInt(value);
					fieldSetMet.invoke(bean, intval);
				} else if ("Long".equalsIgnoreCase(fieldType)) {
					Long temp = Long.parseLong(value);
					fieldSetMet.invoke(bean, temp);
				} else if ("Double".equalsIgnoreCase(fieldType)) {
					Double temp = Double.parseDouble(value);
					fieldSetMet.invoke(bean, temp);
				} else if ("Boolean".equalsIgnoreCase(fieldType)) {
					Boolean temp = Boolean.parseBoolean(value);
					fieldSetMet.invoke(bean, temp);
				} else if ("Float".equalsIgnoreCase(fieldType)) {
					float temp = Float.parseFloat(value);
					fieldSetMet.invoke(bean, temp);
				} else {

					//进行编码转换获取UTF-8
					//value = EncodeHelper.encode(value, UTF8);
					//value = Base64Helper.encodeBytes(value.getBytes());

					fieldSetMet.invoke(bean, value);
				}

			} catch (Exception e) {
				continue;
			}
		}

		return bean;
	}

	/**
	 * 把指定的属性值加到对象中
	 * @param obj
	 * @param key
	 * @param keyvalue
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static Object setKeyValue(Object obj,String key,String keyvalue) throws InstantiationException, IllegalAccessException{

		Map<String,Object> map = getFieldValueMap(obj);

		map.put(key, keyvalue);

		obj = setFieldValue(obj.getClass(),map);

		return obj;
	}

	/**
	 * 传入列表用反射的方式进行赋值
	 * @param obj
	 * @param valueList
	 * @return
	 */
	public static List<Object> getObjectList(Object obj,List<Map<String,String>> valueList){

		List<Object> list = new ArrayList<Object>();

		for(Map<String,String> map:valueList){
			try {
				list.add(setFieldValue(obj.getClass(),map));
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return list;
	}

	/**
	 * 格式化string为Date
	 * @param datestr
	 * @return date
	 */
	private static Date parseDate(String datestr) {
		if (null == datestr || "".equals(datestr)) {
			return null;
		}
		try {
			String fmtstr = null;
			if (datestr.indexOf(':') > 0) {
				fmtstr = "yyyy-MM-dd HH:mm:ss";
			} else {
				fmtstr = "yyyy-MM-dd";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.UK);
			return sdf.parse(datestr);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 日期转化为String
	 * @param date
	 * @return date string
	 */
	private static String fmtDate(Date date) {
		if (null == date) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
					Locale.US);
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 判断是否存在某属性的 set方法
	 * @param methods
	 * @param fieldSetMet
	 * @return boolean
	 */
	private static boolean checkSetMet(Method[] methods, String fieldSetMet) {
		for (Method met : methods) {
			if (fieldSetMet.equals(met.getName())) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 判断是否存在某属性的 get方法
	 * @param methods
	 * @param fieldGetMet
	 * @return boolean
	 */
	private static boolean checkGetMet(Method[] methods, String fieldGetMet) {
		for (Method met : methods) {
			if (fieldGetMet.equals(met.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 拼接某属性的 get方法
	 * @param fieldName
	 * @return String
	 */
	private static String parGetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		return "get" + fieldName.substring(0, 1).toUpperCase()
		+ fieldName.substring(1);
	}
	/**
	 * 拼接在某属性的 set方法
	 * @param fieldName
	 * @return String
	 */
	private static String parSetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		return "set" + fieldName.substring(0, 1).toUpperCase()
		+ fieldName.substring(1);
	}

	/**
	 * 获得对应类型的全部属性
	 * @param fields 属性集合
	 * @param c  class
	 * @return
	 */
	public static List<Field> getAllFields(List<Field> fields, Class<?> c) {
		for (Field field : c.getDeclaredFields()) {
			fields.add(field);
		}
		if (c.getSuperclass() != null) {
			fields = getAllFields(fields, c.getSuperclass());
		}
		return fields;
	}
	/**
	 * 获得对应类型的全部方法
	 * @param methods 方法集合
	 * @param c  class
	 * @return
	 */
	public static List<Method> getAllMethods(List<Method> methods, Class<?> c) {
		for (Method field : c.getDeclaredMethods()) {
			methods.add(field);
		}
		if (c.getSuperclass() != null) {
			methods = getAllMethods(methods, c.getSuperclass());
		}
		return methods;
	}
}
