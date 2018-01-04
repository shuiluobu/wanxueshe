package com.wxs.mapper.task;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.task.TStudentWork;
import com.wxs.enu.EnumClassworkCompletion;
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
 * @since 2017-11-24
 */
public interface TStudentWorkMapper extends BaseMapper<TStudentWork> {
    //获取某机构 某学生 的 特定时间内  的 作业
    @Select("select e.id taskId,a.workName,concat(b.courseName,'-',l.lessonName) lessonName, " +
            "date_format(a.beginTime,'%Y-%m-%d %H:%i:%S') beginTime,date_format(a.endTime,'%Y-%m-%d %H:%i:%S') endTime " +
            "from t_student_work t " +
            "inner join t_class_work a on a.id = t.workId " +
            "inner join t_class_course b on b.id = a.courseId " +
            "inner join t_class_lesson l on l.id = a.lessonId " +
            "left join t_organ_task e on e.businessId = a.id and e.studentId = t.studentId " +
            "where t.studentId = #{studentId} and e.type = 3 " +
            "and t.completion = #{completion} " +
            "and b.organizationId = #{organId} " +
            "and a.beginTime > #{startTime}" +
            "and a.endTime < #{endTime} ")
    @ResultType(java.util.Map.class)
    List<Map> studentWork(@Param("studentId") Long studentId, @Param("organId")Long organId,
                          @Param("startTime")String startTime, @Param("endTime")String endTime,@Param("completion") String completion);
}