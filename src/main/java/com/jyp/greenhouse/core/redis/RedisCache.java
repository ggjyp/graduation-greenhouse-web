package com.jyp.greenhouse.core.redis;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * Created by vito on 2016/8/18.
 */
@Component
public class RedisCache {

    public String cache(String key, String value, int seconds,Jedis jedis ) {
        jedis.del(key);
        String result = jedis.setex(key, seconds, value);
        return result;

    }



    public String cache(String key, byte[] value, int seconds,Jedis jedis) {
        jedis.del(key);
        String result = jedis.setex(key.getBytes(), seconds, value);
        return result;
    }

    public String cache(String key, String value,Jedis jedis) {
        jedis.del(key);
        String result = jedis.set(key, value);
        return result;
    }

    public String getString(String key,Jedis jedis) {
        return jedis.get(key);
    }

    public byte[] getByte(String key,Jedis jedis) {
        return jedis.get(key.getBytes());
    }

    public void delCache(String key,Jedis jedis) {
        jedis.del(key);
    }

    public void delCache(byte[] key,Jedis jedis) {
        jedis.del(key);
    }
}
