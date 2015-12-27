package com.h2y.memcached;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地Map缓存
 */
public class LocalCachedUtil {

	private static Map<String,Object> cachMap = new HashMap<String,Object>();
	
	private static LocalCachedUtil localCachedUtil;
	
	private LocalCachedUtil(){
		super();
	}
	
	public static LocalCachedUtil getInstance(){
		if (localCachedUtil==null) {
			localCachedUtil = new LocalCachedUtil();
		}
		return localCachedUtil;
	}
	
	
	/**
	 * 添加并更新
	 * @param key
	 * @param object
	 */
	public void put(String key,Object object){
		
		cachMap.put(key, object);
	}

	/**
	 * 得到
	 * @param key
	 * @return
	 */
	public Object get(String key){
		
		return cachMap.get(key);
	}
	
	/**
	 * 清空
	 */
	public void clear(){
		
		cachMap.clear();
	}
	
	/**
	 * 
	 * @param key
	 */
	public void remove(String key){
		
		cachMap.remove(key);
	}
	
}
