package com.wxs.cache;

public interface ICache {
	public void putCache(String key, Object value);
	/**
	 * expireDate是到这个时间点过期
	 * 例：cache.putCache("test", "testValue", new Date(System.currentTimeMillis() + 5*1000));
	 * 说明过期时间是从现在时间向后的5秒，这5秒包含当前的1秒，所以 如果想要5秒后需要
	 * cache.putCache("test", "testValue", new Date(System.currentTimeMillis() + (5+1)*1000));
	 */
	public void putCache(String key, Object value, int expireDate);
	public void replaceCache(String key, Object value);
	public void replaceCache(String key, Object value, int seconds);
	public <T> T getCache(String key);
	public void removeCache(String key);
}
