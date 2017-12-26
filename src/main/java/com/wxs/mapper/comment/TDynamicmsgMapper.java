package com.wxs.mapper.comment;

import com.wxs.entity.comment.TDynamicmsg;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.course.TCourseCategory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
public interface TDynamicmsgMapper extends BaseMapper<TDynamicmsg> {
    public List<Map<String, Object>> getDynamicmsgByParam(Map<String, Object> param);
    public List<Map<String,Object>> getFriendDynamicmsgByUserId(Long userId);

    public List<Map<String, Object>> getNearByDynamicms(@Param("studentIds") List<Long> studentIds,@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("range") double range);


}