package com.wxs.mapper.course;

import com.wxs.entity.course.TStudentCourse;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author skyer
 * @since 2017-09-23
 */
public interface TStudentCourseMapper extends BaseMapper<TStudentCourse> {
    //获取个人的课程集合
    public String getCoursesIds(Map<String,Object> map);
}