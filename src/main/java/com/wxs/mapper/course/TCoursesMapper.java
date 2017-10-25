package com.wxs.mapper.course;

import com.wxs.entity.course.TCourses;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
public interface TCoursesMapper extends BaseMapper<TCourses> {

    TCourses selectOneCourseById(@Param("coursesId") Long coursesId);

    List<Map<String,Object>> selectCoursesByIds (List cIdList);

}