package com.wxs.redis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class RedisDataSourceImpl implements RedisDataSource {
	private static Logger log = Logger.getLogger(RedisDataSourceImpl.class);
	
	@Autowired
	private ShardedJedisPool shardedJedisPool;
	
	/**
	 * 获取shardedJedis
	 * @author skyer
	 * @date 2017年8月18日 下午1:47:19
	 * @see com.diandian.common.redis.RedisDataSource#getRedisClient()
	 */
	@Override
	public ShardedJedis getRedisClient() {
		try {
			ShardedJedis shardedJedis = shardedJedisPool.getResource();
			return shardedJedis;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getRedisClient exception",e);
			return null;
		}
	}

	
	@Override
	public void returnResource(ShardedJedis shardedJedis) {
		shardedJedisPool.returnResource(shardedJedis);
	}

	@Override
	public void returnResource(ShardedJedis shardedJedis, boolean broken) {
		if(broken)
			shardedJedisPool.returnBrokenResource(shardedJedis);
		returnResource(shardedJedis);
			
	}

	@Override
	public void closeJedis(ShardedJedis shardedJedis) {
		shardedJedis.close();
	}

}
