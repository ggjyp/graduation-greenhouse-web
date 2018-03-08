package com.jyp.greenhouse.core.redis;

import redis.clients.jedis.*;

public class RedisAPI {


    private static volatile JedisPool pool = null;

    private static final int MaxTotal = 1000;
    private static final int MaxIdle = 5;
    private static final int MaxWaitMillis = 100 * 1000;
    private static final int TIMEOUT = 10000;
    private static final int PORT = 6379;
    private static final String ADDR = "127.0.0.1";
//    private static final String ADDR = "r-bp1f8ce1ab45d404.redis.rds.aliyuncs.com";
    private static final String AUTH = "";
//    private static final String AUTH = "Woh20172017";

    public static JedisPool getPool() {
        if (pool == null) {
            synchronized (JedisPool.class) {
                if (pool == null) {
                    JedisPoolConfig config = new JedisPoolConfig();
                    //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
                    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
                    config.setMaxTotal(MaxTotal);
                    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
                    config.setMaxIdle(MaxIdle);
                    //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
                    config.setMaxWaitMillis(MaxWaitMillis);
                    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
                    config.setTestOnBorrow(true);

//                    pool = new JedisPool(config, ADDR, PORT, TIMEOUT,AUTH);
                    pool = new JedisPool(config, ADDR, PORT, TIMEOUT);
                }
            }
        }
        return pool;
    }


    public static void close(Jedis jedis) {
        if (jedis != null)
            jedis.close();
    }


}
