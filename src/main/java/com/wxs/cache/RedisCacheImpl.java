package com.wxs.cache;


import com.wxs.redis.RedisClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service("cache")
public class RedisCacheImpl implements ICache {
    @Autowired
    private RedisClientTemplate redisClient;
    @Value("${redis.keyPreFix}")
    private  String keyPreFix;

    @Override
    public void putCache(final String key, final Object value) {
        redisClient.setObject(keyPreFix + ":" + key,value);
    }
    /**
     * expireDate是到这个时间点过期
     * 例：cache.putCache("test", "testValue", new Date(System.currentTimeMillis() + 5));
     * 说明过期时间是从现在时间向后的5秒，这5秒包含当前的1秒，所以 如果想要5秒后需要
     * cache.putCache("test", "testValue", new Date(System.currentTimeMillis() + (5+1)));
     */
    @Override
    public void putCache(final String key, final Object value, final int seconds) {
        redisClient.setObject(keyPreFix + ":" + key,value,seconds * 1000);
    }

    @Override
    public void replaceCache(String key, Object value) {
        putCache(key,value);
    }

    @Override
    public void replaceCache(String key, Object value, int seconds) {
        putCache(key,value,seconds);
    }

    @Override
    public <T> T getCache(final String key) {
        return (T)redisClient.getObject(keyPreFix + ":" + key);
    }

    @Override
    public void removeCache(final String key) {
       redisClient.delKey(keyPreFix + ":" + key);
    }
}
