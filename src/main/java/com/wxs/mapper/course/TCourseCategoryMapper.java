package com.wxs.mapper.course;

import com.wxs.entity.course.TCourseCategory;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author skyer
 * @since 2017-09-29
 */
public interface TCourseCategoryMapper extends BaseMapper<TCourseCategory> {

    @Select("SELECT * FROM t_course_category WHERE organId =#{organId} ")
    @ResultMap("BaseResultMap")
    public List<TCourseCategory> getAllCategoryOfOrgan(@Param("organId") Long organId);
}