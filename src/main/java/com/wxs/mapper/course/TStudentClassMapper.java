package com.wxs.mapper.course;

import com.wxs.entity.course.TStudentClass;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author skyer
 * @since 2017-09-23
 */
public interface TStudentClassMapper extends BaseMapper<TStudentClass> {
    //获取个人的课程集合
    public String getCoursesIds(Map<String,Object> map);

    public Map<String,Object> getMyCourse(Long userId);

    @Select("select count(1) from t_student_class c,t_student s where c.studentId=s.id and c.organizationId=#{organId} ")
    @ResultType(int.class)
    public int getOrganStudentCount(Long organId);

}