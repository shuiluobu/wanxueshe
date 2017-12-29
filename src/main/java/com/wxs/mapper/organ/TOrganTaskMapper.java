package com.wxs.mapper.organ;

import com.wxs.entity.organ.TOrganTask;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wyh
 * @since 2017-12-08
 */
public interface TOrganTaskMapper extends BaseMapper<TOrganTask> {

    //根据 待办Id ,类型和完成状态 获取 其他所有的 任务
    public List<TOrganTask> getAllByAgendaId(@Param("agendaId") Long agendaId,@Param("type") Integer type,@Param("statuss")List<Integer> statuss);
    //根绝 taskId 获取其详情
    @Select("select t.*,a.teacherName,a.headImg teacherHeadImg,b.realName studentName,b.headImg studentHeadImg, " +
            "l.courseName,e.lessonName,q.organName " +
            "from t_organ_task t " +
            "left join t_teacher a on a.id = t.teacherId " +
            "left join t_student b on b.id = t.studentId " +
            "left join t_class_course l on l.id = t.courseId " +
            "left join t_class_lesson e on e.id = t.lessonId " +
            "left join t_organization q on q.id = a.organizationId " +
            "where t.id = #{taskId} ")
    @ResultMap("BaseResultMap")
    public TOrganTask getDetailByTaskId(@Param("taskId") Long taskId);
    //根据 待办Id，学生Id,类型 获取单个任务
    @Select(" select * from t_organ_task where agendaId = #{agendaId} and studentId = #{studentId} and type = #{type}")
    @ResultMap("BaseResultMap")
    public TOrganTask getOneByASId(@Param("agendaId")Long agendaId,@Param("studentId")Long studentId,@Param("type")Integer type);
    //根据 代办Id  agendaId  获取 其下所有学生的作业提交情况
    @Select("select t.studentId,t.businessId,b.completion classworkHandInStatus,l.createTime classworkHandInTime " +
            "from t_organ_task t " +
            "left join t_class_work a on a.id = t.businessId " +
            "left join t_student_work b on b.workId = a.id and b.studentId = t.studentId " +
            "left join t_dynamic l on l.id = b.dynamicId " +
            "where agendaId = #{agendaId} and type = 3 ")
    @ResultMap("BaseResultMap")
    public List<TOrganTask> getClassworkCompletions(@Param("agendaId") Long agendaId);

}