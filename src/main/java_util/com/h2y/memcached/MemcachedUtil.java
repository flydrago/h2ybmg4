package com.h2y.memcached;

import com.danga.MemCached.MemCachedClient;

import java.util.*;

/**
 * Memcached帮助工具类
 * 作者：段晓刚
 * 时间：2012-11-1 下午06:05:11
 * 电子邮件：
 */
public class MemcachedUtil {

    private static MemCachedClient memCachedClient = null;

    static {

        memCachedClient = MemcachedConn.getMemCachedClient();
    }


    private MemcachedUtil() {
        super();
    }

    public static MemcachedUtil getInstance() {

        return new MemcachedUtil();
    }

    /**
     * 添加一个指定的值到缓存中.
     *
     * @param key
     * @param value
     * @return
     */
    public boolean add(String key, Object value) {

        return memCachedClient.set(MemcachedConn.keyFlg + key, value);
    }

    /**
     * 添加一个指定的值到缓存中,通知指定过期时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean add(String key, Object value, long time) {

        return memCachedClient.set(MemcachedConn.keyFlg + key, value, new Date(time*1000));
    }

    /**
     * 替换一个指定的值到缓存中.
     *
     * @param key
     * @param value
     * @return
     */
    public boolean update(String key, Object value) {

        return memCachedClient.replace(MemcachedConn.keyFlg + key, value);
    }


    /**
     * 替换一个指定的值到缓存中,同时替换过期时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean update(String key, Object value, long time) {

        return memCachedClient.replace(MemcachedConn.keyFlg + key, value, new Date(time*1000));
    }

    /**
     * 添加一个指定的值到缓存中,通知指定过期时间
     *
     * @param key
     * @param value
     * @param flg   flg为0标示永不失效
     * @return
     */
    public boolean update2(String key, Object value, int flg) {
        return memCachedClient.replace(MemcachedConn.keyFlg + key, value, flg);
    }

    /**
     * 删除一个指定的值到缓存中.
     *
     * @param key
     * @return
     */
    public boolean delete(String key) {

        return memCachedClient.delete(MemcachedConn.keyFlg + key);
    }


    /**
     * 根据指定的关键字获取对象.
     *
     * @param key
     * @return
     */
    public Object get(String key) {

        return memCachedClient.get(MemcachedConn.keyFlg + key);
    }

    public Object get(String key, Integer flg) {
        return memCachedClient.get(MemcachedConn.keyFlg + key, flg);
    }

    /**
     * 获取所有的数据key
     *
     * @return
     */
    public List<String> getAllKeys() {

        List<String> list = new ArrayList<String>();

        Map<String, Map<String, String>> items = memCachedClient.statsItems();

        for (Iterator<String> itemIt = items.keySet().iterator(); itemIt.hasNext(); ) {

            String itemKey = itemIt.next();

            Map<String, String> maps = items.get(itemKey);

            for (Iterator<String> mapsIt = maps.keySet().iterator(); mapsIt.hasNext(); ) {
                String mapsKey = mapsIt.next();
                String mapsValue = maps.get(mapsKey);

                if (mapsKey.endsWith("number")) {//memcached key 类型  item_str:integer:number_str
                    String[] arr = mapsKey.split(":");
                    int slabNumber = Integer.valueOf(arr[1].trim());
                    int limit = Integer.valueOf(mapsValue.trim());

                    Map<String, Map<String, String>> dumpMaps = memCachedClient.statsCacheDump(slabNumber, limit);
                    for (Iterator<String> dumpIt = dumpMaps.keySet().iterator(); dumpIt.hasNext(); ) {

                        String dumpKey = dumpIt.next();

                        Map<String, String> allMap = dumpMaps.get(dumpKey);
                        for (Iterator<String> allIt = allMap.keySet().iterator(); allIt.hasNext(); ) {
                            String allKey = allIt.next();
                            list.add(allKey.trim());
                        }
                    }
                }
            }
        }

        return list;
    }

    public boolean existsKey(String key) {

        return memCachedClient.keyExists(MemcachedConn.keyFlg + key);
    }

    /**
     * 清空所用
     */
    @SuppressWarnings("unused")
    private void clearAll() {

        memCachedClient.flushAll();
    }


//    /**
//     * 以秒为单位
//     *
//     * @param time
//     * @return
//     */
//    private Date expiryTime(long time) {
//        Calendar c = Calendar.getInstance();
//        c.add(Calendar.SECOND, (int) time);
//
//        return c.getTime();
//    }
}
