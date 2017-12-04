package com.wxs.cache;

import redis.clients.jedis.GeoCoordinate;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/29 0029.
 */
public interface IGeoRedisService {
    public void addGeoAll(String executeType, Map<String, GeoCoordinate> geoCoordinateMap);

    public void addGeo(String executeType, String ywId, double dlzbjd, double dlzbwd) ;

    //返回某个位置的坐标信息
    public GeoCoordinate geopos(String key, String member) ;

    public Double geodist(String key, String member1, String member2) ;

    /**
     * redis坐标删除接口实现
     *
     * @param key
     * @title
     * @date 2017年8月14日
     * @author niuchuang
     */
    public void delGeo(String key) ;
}
