package com.wxs.mapper.task;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.task.TClassTask;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author skyer
 * @since 2017-11-24
 */
public interface TClassTaskMapper extends BaseMapper<TClassTask> {

    @Select("SELECT k.*,s.courseName,o.organName,t.teacherName from t_class_task k,t_class c,t_teacher t,t_course s,t_organization o  \n" +
            "where k.classId=c.id and c.teacherId=t.id " +
            "and c.courseId=s.id and c.organId=o.id" +
            "and k.id=#{taskId}")
    @ResultType(Map.class)
    public Map<String,Object> getClassTask(Long taskId);

}