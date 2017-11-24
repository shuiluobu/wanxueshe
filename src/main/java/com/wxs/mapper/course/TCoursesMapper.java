package com.wxs.mapper.course;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.course.TCourse;
import org.apache.ibatis.annotations.Param;

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
public interface TCoursesMapper extends BaseMapper<TCourse> {

    TCourse selectOneCourseById(@Param("coursesId") Long coursesId);

    List<Map<String,Object>> selectCoursesByIds (List cIdList);

    Map<String,Object> selectMap(Long coursesId);

}