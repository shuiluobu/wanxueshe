package com.wxs.mapper.task;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.task.TClassWork;
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
public interface TClassWorkMapper extends BaseMapper<TClassWork> {

    @Select("SELECT k.id workId,k.workName, " +
            "k.dynamicId," +
            "DATE_FORMAT(k.endTime,'%Y-%m-%d') dayTime," +
            "DATE_FORMAT(k.endTime,'%H:%i') hourTime," +
            "DATE_FORMAT(k.createTime,'%Y-%m-%d %H:%i:%s') createTime," +
            "c.status,c.teacherId,k.courseId,c.organizationId organ FROM t_class_work k," +
            "t_class_course c " +
            "WHERE k.courseId = c.id  " +
            "AND k.id=#{workId}")
    @ResultType(Map.class)
    public Map<String,Object> getClassWork(@Param("workId") Long workId);
    @Select("select w.workName," +
            "completion," +
            "w.id as workId," +
            "DATE_FORMAT(w.endTime,'%Y-%m-%d %H:%i') endTime " +
            "from t_class_work w,t_student_work s " +
            " where s.workId=w.id and s.userId=#{userId}")
    @ResultType(Map.class)
    public List<Map<String,Object>> getMyClassWorkInfo(@Param("userId") Long userId);

    @Select("SELECT s.realName from t_student s,t_student_work w " +
            "where s.id=w.studentId and w.id=#{workId} and w.userId=#{userId}")
    @ResultType(String.class)
    public List<String> getStudentNameByWorkId(@Param("workId") Long workId,@Param("userId") Long userId);
}