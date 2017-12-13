package com.wxs.mapper.task;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.task.TClassTask;
import org.apache.ibatis.annotations.Param;
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

    @Select("SELECT k.id taskId, " +
            "content," +
            "DATE_FORMAT(k.endTime,'%Y-%m-%d') dayTime," +
            "DATE_FORMAT(k.endTime,'%H:%i') hourTime," +
            "DATE_FORMAT(k.createTime,'%Y-%m-%d %H:%i:%s') createTime," +
            "c.status,c.teacherId,c.courseId,c.organId organ FROM t_class_task k,t_class c " +
            "WHERE k.classId=c.id  " +
            "AND k.id=#{taskId}")
    @ResultType(Map.class)
    public Map<String,Object> getClassTask(@Param("taskId") Long taskId);

}