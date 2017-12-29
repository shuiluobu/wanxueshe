package com.wxs.mapper.course;

import com.wxs.entity.course.TStudentCourse;
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
public interface TStudentCourseMapper extends BaseMapper<TStudentCourse> {
    //获取个人的课程集合
    public String getCoursesIds(Map<String,Object> map);

    public List<Map<String,Object>> getMyCourses(Map map);

    public int getClassStudentCountByParam(TStudentCourse stuClass);

    /**
     * 获取一个学生有多少个课
     * @param studentId
     * @return
     */
    @Select("select count(1) from t_student_course c where c.studentId=#{studentId} ")
    @ResultType(int.class)
    public int getStudentCourseCount(Long studentId);

    @Select("select count(c.coursesId) from t_student_course c,t_student s where c.studentId=s.id and s.userId=#{userId} ")
    @ResultType(int.class)
    public int getParentCourseCount(Long userId); //获取每个家长下所有学员总共课程

}