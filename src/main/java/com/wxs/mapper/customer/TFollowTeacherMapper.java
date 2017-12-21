package com.wxs.mapper.customer;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.customer.TFollowTeacher;
import com.wxs.entity.customer.TFollowUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author skyer
 * @since 2017-12-21
 */
public interface TFollowTeacherMapper extends BaseMapper<TFollowTeacher> {
    @Select("SELECT t.* FROM t_follow_teacher f,t_teacher t where t.id=f.teacherId and f.userId =#{userId} ")
    @ResultType(Map.class)
    List<Map<String,Object>> getFllowTeacherByUser(@Param("userId") Long userId);

    TFollowTeacher getOneFllowTeacherByUser(@Param("userId") Long userId, @Param("teacherId") Long teacherId);

    @Select("select userId from t_follow_teacher f where f.status=0 and f.id=#{teacherId}")
    @ResultType(Long.class)
    List<Long> getFllowUserIdsOfTeacherId(Long teacherId);

    @Select("SELECT count(1) FROM t_follow_teacher f where f.status=0 and f.id=#{teacherId} ")
    @ResultType(int.class)
    int getFllowTeacherByCount(@Param("teacherId") Long teacherId);
}