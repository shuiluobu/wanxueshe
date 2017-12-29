package com.wxs.mapper.customer;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.wxs.entity.customer.TTeacher;
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
 * @since 2017-09-21
 */
public interface TTeacherMapper extends BaseMapper<TTeacher> {
    //使用@Param注解后，可以在mapper映射文件中不用 parameterType参数
    Map selectTeacherById(@Param("tid") Long tid);

    @Select("SELECT COUNT(s.studentId) from t_class_course c,t_student_course s  where c.id=s.coursesId  and c.teacherId=#{teacherId} ")
    @ResultType(int.class)
    int getTeacherStudentCount(@Param("teacherId") Long teacherId);

    //根据用户给Id 获取老师信息
    @Select(" select * from t_teacher where userId = #{userId}")
    @ResultMap("BaseResultMap")
    public TTeacher getByUserId(@Param("userId") Long userId);

    @Select(" SELECT DISTINCT t.id teacherId,t.teacherName" +
           " from t_teacher t,t_class_course c,t_course_category cy where " +
           " t.id=c.teacherId and c.courseCateId=cy.id and courseCateId = #{courseCateId}")
    @ResultType(Map.class)
    public List<Map<String,Object>> getTeacherNameListByCourseId(@Param("courseCateId") Long courseCateId);
    //根据机构Id 和教师名称或姓名  获取 老师
    @Select(" select * from t_teacher where organizationId = #{organId} " +
            "and ( teacherName like concat('%',#{name},'%') or realName like concat('%',#{name},'%') ) ")
    List<TTeacher> searchByName(@Param("organId") Long organId,@Param("name") String name);
}