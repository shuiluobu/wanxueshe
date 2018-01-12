package com.wxs.mapper.course;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.course.TClassCourse;
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
public interface TClassCoursesMapper extends BaseMapper<TClassCourse> {

    //分页+混合条件 查询课程
    List<TClassCourse> pageData(TClassCourse course);

    Map<String,Object> selectMap(Long coursesId);

    @Select("select count(1) from t_class_course where organizationId=#{organId}")
    @ResultType(int.class)
    public int getOrganCourseCount(@Param("organId") Long organId);



    /**
     * 课时计划
     * @param courseCateId
     * @return
     */
    @Select("select DATE_FORMAT(beginTime,'%Y年%m月%d日') beginTime,DATE_FORMAT(endTime,'%Y年%m月%d日') endTime from t_class_course where courseCateId=#{courseCateId}")
    @ResultType(Map.class)
    public List<Map<String,Object>> getCoursePlans(@Param("courseCateId") Long courseCateId);
    //获取某课程 所有学生的 完成情况
    @Select("select t.studentId,count(*) total,  " +
            "count(case when  (t.scheduleStatus = 1 ) then t.studentId  end ) done,a.studentName,a.headImg  " +
            "from t_student_lessones t  " +
            "left join t_organ_student a on a.id = t.studentId  " +
            "where t.courseId = #{courseId}  " +
            "group by t.studentId")
    @ResultType(Map.class)
    List<Map<String,Object>> stuCourseDoneInfo (@Param("courseId") Long courseId);


}