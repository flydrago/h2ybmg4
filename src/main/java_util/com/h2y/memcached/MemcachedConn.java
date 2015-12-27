package com.h2y.memcached;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.h2y.util.PropertiesUtil;

/**
 * Memcached处理
 * 作者：段晓刚
 * 时间：2012-8-28 上午09:53:00
 * 电子邮件：
 */
public class MemcachedConn {

    private static String memcached_file = "/confing.properties";

    private static String[] this_servers = {"192.168.4.188:11111"};

    //设置初始连接数10
    private static int initialConnections = 10;

    //最小连接数 5
    private static int minSpareConnections = 5;

    // 最大连接数 50
    private static int maxSpareConnections = 50;

    private static long maxIdleTime = 1000 * 60 * 30; // 30 minutes

    private static long maxBusyTime = 1000 * 60 * 5; // 5 minutes


    //设置主线程睡眠时间 每隔30秒醒来  然后开始维护 连接数大小
    private static long maintThreadSleep = 1000 * 5; // 5 seconds

    //设置 读取 超时3秒钟
    private static int socketTimeOut = 1000 * 3; // 3 seconds to block on reads

    private static int socketConnectTO = 1000 * 3; // 3 seconds to block on initial

    // 关闭nagle算法
    private static boolean nagleAlg = false; // turn off Nagle's algorithm on all sockets in

    static String keyFlg = "";

    static {

        PropertiesUtil mPropertiesUtil = PropertiesUtil.getInstance(memcached_file);
        //mPropertiesUtil.setFilepath(memcached_file);

        if (mPropertiesUtil.getValueByKey("mmcd.servers") != null) {
            String servers = mPropertiesUtil.getValueByKey("mmcd.servers");

            if (servers.contains(";")) {
                this_servers = servers.split(";");
            } else {
                this_servers[0] = servers;
            }

        }

        if (mPropertiesUtil.getValueByKey("mmcd.initialConnections") != null) {
            initialConnections = Integer.parseInt(mPropertiesUtil.getValueByKey("mmcd.initialConnections"));
        }
        if (mPropertiesUtil.getValueByKey("mmcd.minSpareConnections") != null) {
            minSpareConnections = Integer.parseInt(mPropertiesUtil.getValueByKey("mmcd.minSpareConnections"));
        }
        if (mPropertiesUtil.getValueByKey("mmcd.maxSpareConnections") != null) {
            maxSpareConnections = Integer.parseInt(mPropertiesUtil.getValueByKey("mmcd.maxSpareConnections"));
        }
        if (mPropertiesUtil.getValueByKey("mmcd.maxIdleTime") != null) {
            maxIdleTime = Integer.parseInt(mPropertiesUtil.getValueByKey("mmcd.maxIdleTime"));
        }
        if (mPropertiesUtil.getValueByKey("mmcd.maxBusyTime") != null) {
            maxBusyTime = Integer.parseInt(mPropertiesUtil.getValueByKey("mmcd.maxBusyTime"));
        }
        if (mPropertiesUtil.getValueByKey("mmcd.maintThreadSleep") != null) {
            maintThreadSleep = Integer.parseInt(mPropertiesUtil.getValueByKey("mmcd.maintThreadSleep"));
        }
        if (mPropertiesUtil.getValueByKey("mmcd.socketTimeOut") != null) {
            socketTimeOut = Integer.parseInt(mPropertiesUtil.getValueByKey("mmcd.socketTimeOut"));
        }
        if (mPropertiesUtil.getValueByKey("mmcd.socketConnectTO") != null) {
            socketConnectTO = Integer.parseInt(mPropertiesUtil.getValueByKey("mmcd.socketConnectTO"));
        }
        if (mPropertiesUtil.getValueByKey("mmcd.nagleAlg") != null) {
            if (mPropertiesUtil.getValueByKey("mmcd.nagleAlg").equals("true"))
                nagleAlg = true;
            else {
                nagleAlg = false;
            }
        }
    }

    /**
     * SockIOPool连接池
     */
    private static SockIOPool pool = SockIOPool.getInstance();


    private MemcachedConn() {
        super();
    }

    public static MemcachedConn getInstance() {

        conn();//获取连接

        return new MemcachedConn();
    }

    public static MemCachedClient getMemCachedClient() {

        conn();//获取连接
        /**
         * 获取客户端连接
         */
        MemCachedClient memCachedClient = new MemCachedClient();

        return memCachedClient;
    }

    private static void conn() {

//		pool.setServers(this_servers);   
//		pool.setFailover(true);   
//		pool.setInitConn(10);   
//		pool.setMinConn(5);   
//		pool.setMaxConn(250);   
//		pool.setMaintSleep(30);   
//		pool.setNagle(false);   
//		pool.setSocketTO(3000);   
//		pool.setAliveCheck(true);   
//		pool.initialize();  

        pool.setServers(this_servers);
        pool.setInitConn(initialConnections);
        pool.setMinConn(minSpareConnections);
        pool.setMaxConn(maxSpareConnections);
        pool.setMaxIdle(maxIdleTime);
        pool.setMaxBusyTime(maxBusyTime);
        pool.setMaintSleep(maintThreadSleep);
        pool.setSocketTO(socketTimeOut);
        pool.setSocketConnectTO(socketConnectTO);
        pool.setNagle(nagleAlg);
        pool.setHashingAlg(SockIOPool.NEW_COMPAT_HASH);
        pool.initialize();
    }
}