package com.wxs.mapper.dynamic;

import com.wxs.entity.comment.TDynamic;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
public interface TDynamicMapper extends BaseMapper<TDynamic> {
    public List<Map<String, Object>> getDynamicmsgByParam(Map<String, Object> param);
    public List<Map<String,Object>> getFriendDynamicmsgByUserId(@Param("userId") Long userId);

    public List<Map<String, Object>> getNearByDynamicms(@Param("studentIds") List<Long> studentIds,@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("range") double range);
    public List<Map<String,Object>> getNewestDynamicmByOrganId(@Param("organId") Long organId);

}