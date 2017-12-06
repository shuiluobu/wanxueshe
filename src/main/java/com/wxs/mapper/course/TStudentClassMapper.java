package com.wxs.mapper.course;

import com.wxs.entity.course.TStudentClass;
import com.baomidou.mybatisplus.mapper.BaseMapper;
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
 * @since 2017-09-23
 */
public interface TStudentClassMapper extends BaseMapper<TStudentClass> {
    //获取个人的课程集合
    public String getCoursesIds(Map<String,Object> map);

    public List<Map<String,Object>> getMyCourses(Long userId,Integer isEnd);

    @Select("select count(1) from t_student_class c,t_student s where c.studentId=s.id and c.organizationId=#{organId} ")
    @ResultType(int.class)
    public int getOrganStudentCount(Long organId);

    /**
     * 获取一个学生有多少个课
     * @param studentId
     * @return
     */
    @Select("select count(1) from t_student_class c where c.studentId=#{studentId} ")
    @ResultType(int.class)
    public int getStudentCourseCount(Long studentId);

    @Select("select count(c.coursesId) from t_student_class c,t_student s where c.studentId=s.id and s.parentId=#{parentId} ")
    @ResultType(int.class)
    public int getParentCourseCount(Long parentId); //获取每个家长下所有学员总共课程

}