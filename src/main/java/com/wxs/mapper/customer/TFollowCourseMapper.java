package com.wxs.mapper.customer;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.customer.TFollowCourse;
import org.apache.ibatis.annotations.Param;
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
public interface TFollowCourseMapper extends BaseMapper<TFollowCourse> {

    List<Map<String,Object>> getFollowCoursesByUser(Long userId);

    @Select("select userId from t_follow_course where status=0 and courseCateId=#{courseCateId}")
    @ResultType(Long.class)
    List<Long> getFllowUserIdsOfCourseId(@Param("courseCateId") Long courseCateId);

    @Select("select count(1) from t_follow_course where status=0 and courseCateId=#{courseCateId}")
    @ResultType(int.class)
    int getFllowCountOfCourseId(@Param("courseId") Long courseId);

    @Select("select count(distinct courseCateId) from t_follow_course where status=0 and userId=#{userId}")
    @ResultType(int.class)
    int getFollowCourseCountByUserId(@Param("userId") Long userId);
}