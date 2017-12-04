package com.wxs.redis;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.wxs.core.util.SerializeUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@Service("redisClient")
public class RedisClientTemplate {
	private static final Logger log = Logger.getLogger(RedisClientTemplate.class);

	@Autowired
	private RedisDataSource redisDataSource;

	/**
	 * 断开连接
	 * 
	 * @author skyer
	 * @date 2017年4月18日 下午1:55:55
	 */
	public void disConnect() {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		shardedJedis.disconnect();
	}

	/**
	 * 设置单个值
	 * @author skyer
	 * @date 2017年4月18日 下午2:07:44
	 * @param key
	 * @param value
	 * @return
	 */
	public String setKey(String key, String value) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (null == shardedJedis) {
			return result;
		}
		try {
			result = shardedJedis.set(key, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		finally {
			redisDataSource.closeJedis(shardedJedis);
		}
		return result;
	}
	/**
	 * 设置单个值(如果key存在,则失败)
	 * @author skyer
	 * @date 2017年4月18日 下午2:07:44
	 * @param key
	 * @param value
	 * @return
	 */
	public Long setnxKey(String key, String value) {
		Long result = 0L;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (null == shardedJedis) {
			return result;
		}
		try {
			result = shardedJedis.setnx(key, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		finally {
			redisDataSource.closeJedis(shardedJedis);
		}
		return result;
	}
	/**
	 * 设置单个值 key有时间时效
	 * @author skyer
	 * @date 2017年4月18日 下午3:27:56
	 * @param key
	 * @param value
	 * @param seconds
	 * @return
	 */
	public String setKey(String key, String value,int seconds) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (null == shardedJedis) {
			return result;
		}
		try {
			result = shardedJedis.setex(key, seconds, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		finally {
			redisDataSource.closeJedis(shardedJedis);
		}
		return result;
	}

	/**
	 * 获取单个值
	 * @author skyer
	 * @date 2017年4月18日 下午2:08:34
	 * @param key
	 * @return
	 */
	public String getKey(String key){
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (null == shardedJedis) {
			return result;
		}
		try {
			result = shardedJedis.get(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		finally {
			redisDataSource.closeJedis(shardedJedis);
		}
		return result;
	}

	/**
	 * 设置对象 key有时间时效
	 * @author skyer
	 * @date 2017年4月18日 下午2:59:00
	 * @param key
	 * @param value
	 * @return
	 */
	public String setObject(String key,Object value,int seconds){
		return set(key.getBytes(), SerializeUtil.serialize(value),seconds);
	}
	/**
	 * 设置对象
	 * @author skyer
	 * @date 2017年4月18日 下午2:59:00
	 * @param key
	 * @param value
	 * @return
	 */
	public String setObject(String key,Object value){
		return set(key.getBytes(), SerializeUtil.serialize(value),0);
	}

	/**
	 * 获取对象
	 * @author skyer
	 * @date 2017年4月18日 下午2:59:30
	 * @param key
	 * @return
	 */
	public Object getObject(String key){
		return SerializeUtil.UnSerialize(get(key.getBytes()));
	}

	private String set(byte[] key, byte[] value,int seconds) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		try {
			if(seconds > 0 ){
				result = shardedJedis.setex(key, seconds, value);
			}else{
				result = shardedJedis.set(key, value);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		finally {
			redisDataSource.closeJedis(shardedJedis);
		}
		return result;
	}

	private byte[] get(byte[] bytes) {
		byte[] result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		try {
			result = shardedJedis.get(bytes);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		finally {
			redisDataSource.closeJedis(shardedJedis);
		}
		return result;
	}
	/**
	 * 判断key是否存在
	 * @author skyer
	 * @date 2017年4月18日 下午3:19:19
	 * @param key
	 * @return
	 */
	public boolean existKey(String key){
		boolean result = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		try {
			if(shardedJedis.exists(key) || shardedJedis.exists(key.getBytes())){
				result = true;
				return result;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		finally {
			redisDataSource.closeJedis(shardedJedis);
		}
		return result;
	}


	/**
	 * 删除key
	 * @author skyer
	 * @date 2017年4月18日 下午3:34:42
	 * @param key
	 * @return
	 */
	public Long delKey(String key){
		Long result = 0L;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		try {
			Long result1 = shardedJedis.del(key);
			Long result2 = shardedJedis.del(key.getBytes());
			return result1+result2;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		finally {
			redisDataSource.closeJedis(shardedJedis);
		}
		return result;
	}

	/**
	 * 根据前缀批量删除key
	 * @author skyer
	 * @date 2017年4月18日 下午3:34:42
	 * @param key
	 * @return
	 */
	public Long delBatchKey(String key){
		Long result = 0L;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		try {
			Collection<Jedis> allJedis = shardedJedis.getAllShards();
			Iterator<Jedis> iterator = allJedis.iterator();
			while (iterator.hasNext()) {
				Jedis jedis = (Jedis) iterator.next();
				Set<String> keys = jedis.keys(key+"*");
				Iterator<String> iterator2 = keys.iterator();
				while (iterator2.hasNext()) {
					result+=jedis.del((String) iterator2.next());

				}
			}
			return result;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		finally {
			redisDataSource.closeJedis(shardedJedis);
		}
		return result;
	}

    public static void main(String[] args) {
        ApplicationContext ac =  new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
        RedisClientTemplate redisClient = (RedisClientTemplate)ac.getBean("redisClient");
        //测试设置单个值
        redisClient.setKey("a11", "aan");
//        redisClient.setKey("a2", "bb");
//        redisClient.setKey("a3", "cc");
//        System.out.println(redisClient.getKey("a1"));
        //测试设置对象
//        Map<String, Object> map = new HashMap<>();
//        map.put("aaadf", "bbb");
//        System.out.println(redisClient.setObject("a4", map));
//        System.out.println(((Map<String, Object>)redisClient.getObject("a4")).toString());
        //测试key是否存在
//        System.out.println(redisClient.existKey("user1"));
        //测试删除key
//        System.out.println(redisClient.delKey("user1"));
        //测试批量删除key
//        System.out.println(redisClient.delBatchKey("a"));
    }
}
