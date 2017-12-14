package com.wxs.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * redisPoolConfig
 * 使用注解配置redis pool
 * @author skyer
 * @date 2017年8月25日 上午11:45:35
 */
@Configuration
public class RedisPoolConfig {

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Value("${spring.redis.pool.max-idle}")
	private int maxIdle;

	@Value("${spring.redis.pool.max-wait}")
	private long maxWaitMillis;



	@Bean(name="jedisPoolConfig")
	public JedisPoolConfig jedisPoolConfig(
			@Value("${spring.redis.pool.max-total}")int maxTotal,
            @Value("${spring.redis.pool.max-idle}")int maxIdle,
            @Value("${spring.redis.pool.max-wait}")int maxWaitMillis){
		JedisPoolConfig config =  new JedisPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setMaxWaitMillis(maxWaitMillis);
		return config;
	}


	@Bean
	public JedisPool redisPoolFactory() {
		Logger.getLogger(getClass()).info("JedisPool注入成功！！");
		Logger.getLogger(getClass()).info("redis地址：" + host + ":" + port);
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
		return jedisPool;
	}
	
	@Bean(name="shardedJedisPool")
	@Scope("singleton")
	public ShardedJedisPool shardedJedisPool(
			@Qualifier("jedisPoolConfig")JedisPoolConfig jedisPoolConfig){
		List<JedisShardInfo> shards = new ArrayList<>();
		JedisShardInfo jedisShardInfo = new JedisShardInfo(host, port);
		//jedisShardInfo.setPassword(password);
		shards.add(jedisShardInfo);
		return new ShardedJedisPool(jedisPoolConfig, shards);
	}
	

}
