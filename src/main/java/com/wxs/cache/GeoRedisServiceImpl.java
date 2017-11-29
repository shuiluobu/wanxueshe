package com.wxs.cache;

import com.wxs.redis.RedisClientTemplate;
import com.wxs.redis.RedisDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/29 0029.
 * redis3.2以上版本提供存储坐标信息和计算坐标信息
 */
@Service("geoRedisService")
public class GeoRedisServiceImpl implements IGeoRedisService {
    private static final Logger log = Logger.getLogger(RedisClientTemplate.class);

    @Autowired
    private RedisDataSource redisDataSource;

    public void addGeoAll(String executeType, Map<String, GeoCoordinate> geoCoordinateMap) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        try {
            shardedJedis.geoadd(executeType, geoCoordinateMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //即时释放jedis
            redisDataSource.closeJedis(shardedJedis);
        }
    }

    public void addGeo(String executeType, String ywId, double dlzbjd, double dlzbwd) {
        Map<String, GeoCoordinate> geoCoordinateMap = new HashMap<String, GeoCoordinate>();
        GeoCoordinate coordinate = new GeoCoordinate(dlzbjd, dlzbwd);
        geoCoordinateMap.put(ywId, coordinate);
        addGeoAll(executeType, geoCoordinateMap);
    }

    //返回某个位置的坐标信息
    public GeoCoordinate geopos(String key, String member) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        try {
            List<GeoCoordinate> geoCoordinates = shardedJedis.geopos(key, member);
            if (geoCoordinates != null && geoCoordinates.size() > 0) {
                return geoCoordinates.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //即时释放jedis
            redisDataSource.closeJedis(shardedJedis);
        }
        return null;
    }

    public Double geodist(String key, String member1, String member2) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        try {
            return shardedJedis.geodist(key, member1, member2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //即时释放jedis
            redisDataSource.closeJedis(shardedJedis);
        }
        return 0.00;
    }

    /**
     * redis坐标删除接口实现
     *
     * @param key
     * @title
     * @date 2017年8月14日
     * @author niuchuang
     */
    public void delGeo(String key) {
        if (StringUtils.isBlank(key)) {
            return;
        }
        log.info("进入企业坐标删除:" + key);
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        try {
            List<GeoRadiusResponse> responseList = shardedJedis.georadius(key, 113.377826, 23.132361, 500000000d, GeoUnit.M, GeoRadiusParam.geoRadiusParam().withCoord().withDist().sortAscending());
            if (responseList != null && responseList.size() > 0) {
                for (GeoRadiusResponse geoRadiusResponse : responseList) {
                    String member = geoRadiusResponse.getMemberByString();
                    shardedJedis.zrem(key, member);
                    //logger.info(key+"坐标删除成功:"+JSON.toJSONString(member));
                }
            } else {
                log.info("没有数据，无需删除");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //即时释放jedis
            redisDataSource.closeJedis(shardedJedis);
        }
        log.info("离开企业坐标删除:" + key);
    }
}