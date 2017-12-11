package com.wxs.mapper.course;

import com.wxs.entity.course.TClassLesson;
import com.baomidou.mybatisplus.mapper.BaseMapper;
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
public interface TClassLessonMapper extends BaseMapper<TClassLesson> {
    //分页+混合条件 查询课时
    List<TClassLesson> pageData(TClassLesson classLesson);
    //根据条件查询课时
    List<TClassLesson> selectLessionByParm(TClassLesson classLesson);
    //获取一个课程下面所有课时信息
    List<Map<String,Object>> queryLessionByCourse(@Param("courseId") Long courseId,@Param("studentId") Long studentId);

    @Select(" SELECT l.beginTime,l.endTime,l.lessonSeq,r.canQty,r.courseName,r.organizationId,o.organName\n" +
            " from t_student_class c,t_course r,t_class_lesson l,t_organization o " +
            " where c.coursesId=l.courseId and c.coursesId=r.id and o.id=r.organizationId" +
            " and l.beginTime>#{beginTime} and beginTime<=#{endTime} and c.userId=#{userId}")
    @ResultType(Map.class)
    List<Map<String,Object>> getTodayCourseLesson(@Param("beginTime") String beginTime,@Param("endTIme") String endTIme,@Param("userId") Long userId);


}