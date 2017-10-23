package com.wxs.redis;

import redis.clients.jedis.ShardedJedis;

public interface RedisDataSource {
	
	public abstract ShardedJedis getRedisClient();
	public void returnResource(ShardedJedis shardedJedis);
    public void returnResource(ShardedJedis shardedJedis, boolean broken);
    public void closeJedis(ShardedJedis shardedJedis);

}
