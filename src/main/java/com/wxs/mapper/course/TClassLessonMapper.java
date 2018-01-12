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
    List<Map<String,Object>> queryLessionByCourse(@Param("courseId") Long courseId,@Param("studentId") Long studentId,@Param("userId") Long userId);

    @Select(" SELECT l.courseId, DATE_FORMAT(l.beginTime,'%m-%d') dayTime," +
            " DATE_FORMAT(l.beginTime,'%h:%i') beginTime," +
            " DATE_FORMAT(l.endTime,'%h:%i') endTime," +
            " l.lessonSeq,r.canQty,r.courseName,o.organName,r.subjectType " +
            " from t_student_course c,t_class_course r,t_class_lesson l,t_organization o " +
            " where c.coursesId=l.courseId and c.coursesId=r.id and o.id=c.organizationId" +
            " and l.beginTime>#{beginTime} and l.beginTime<=#{endTime} and c.userId=#{userId}")
    @ResultType(Map.class)
    List<Map<String,Object>> getTodayCourseLesson(@Param("beginTime") String beginTime,@Param("endTime") String endTime,@Param("userId") Long userId);
    //获取某课程的所有课时以及课时完成情况
    @Select("select t.*,a.status ifDone  " +
            "from t_class_lesson t  " +
            "left join t_organ_agenda a on a.lessonId = t.id and a.courseId = t.courseId  " +
            "where t.courseId = #{courseId} order by t.lessonSeq desc")
    List<TClassLesson> allByCourseId(@Param("courseId") Long courseId );

}