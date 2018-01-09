package com.wxs.mapper.record;

import com.wxs.entity.record.TBusinessRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 机构不同业务的操作记录表 Mapper 接口
 * </p>
 *
 * @author wyh
 * @since 2018-01-05
 */
public interface TBusinessRecordMapper extends BaseMapper<TBusinessRecord> {

    //获取某学生的 所有 相关 操作记录
    @Select(" select t.*,a.teacherName,b.studentName " +
            "from t_business_record t " +
            "left join t_teacher a on a.id = t.teacherId " +
            "left join t_organ_student b on b.id = t.studentId " +
            "where t.studentId = #{studentId} " +
            "order by t.createTime desc")
    @ResultMap("BaseResultMap")
    List<TBusinessRecord> getAllByStuId(@Param("studentId") Long studentId);

    //根据 签到任务Id 获取 任务所属 课程与课时
    @Select(" select concat(a.courseName,'-',b.lessonName) clName " +
            "from t_organ_task t " +
            "left join t_class_course a on a.id = t.courseId " +
            "left join t_class_lesson b on b.id = t.lessonId " +
            "where t.id = #{taskId}")
    Map<String,String> signInCourseLesson(@Param("taskId") Long taskId);
}