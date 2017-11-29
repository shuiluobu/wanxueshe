package com.wxs.mapper.course;

import com.wxs.entity.course.TCourseCategory;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
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
 * @since 2017-09-29
 */
public interface TCourseCategoryMapper extends BaseMapper<TCourseCategory> {

    //分页+混合条件 查询 班级
    public List<TCourseCategory> pageData(TCourseCategory courseCategory);

    @Select("SELECT * FROM t_course_category WHERE organId =#{organId} ")
    @ResultType(TCourseCategory.class)
    public List<TCourseCategory> getAllCategoryOfOrgan(@Param("organId") Long organId);

    @Select("SELECT * from t_course_category y  INNER JOIN t_class_courses c on y.id=c.courseCateId\n" +
            "INNER JOIN t_class s on s.id=c.classId where s.teacherId =#{teacherId} ")
    @ResultType(TCourseCategory.class)
    public List<TCourseCategory> getAllCategoryByTeacher(@Param("teacherId") Long teacherId );


}