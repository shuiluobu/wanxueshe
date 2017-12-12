package com.wxs.mapper.course;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.course.TCourse;
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
 * @since 2017-09-21
 */
public interface TCoursesMapper extends BaseMapper<TCourse> {

    //分页+混合条件 查询课程
    List<TCourse> pageData(TCourse course);

    List<Map<String,Object>> selectCoursesByIds (List cIdList);

    Map<String,Object> selectMap(Long coursesId);

    @Select("select count(1) from t_course where organizationId=#{organId}")
    @ResultType(int.class)
    public int getOrganCourseCount(@Param("organId") Long organId);


}