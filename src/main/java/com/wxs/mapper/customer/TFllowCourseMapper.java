package com.wxs.mapper.customer;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.customer.TFllowCourse;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 关注的课程 Mapper 接口
 * </p>
 *
 * @author skyer
 * @since 2017-10-25
 */
public interface TFllowCourseMapper extends BaseMapper<TFllowCourse> {

    List<Map<String,Object>> getFllowCoursesByUser(Long userId);

    @Select("select userId from t_fllow_course where status=0 and courseCateId=#{courseId}")
    @ResultType(Long.class)
    List<Long> getFllowUserIdsOfCourseId(Long courseId);
}