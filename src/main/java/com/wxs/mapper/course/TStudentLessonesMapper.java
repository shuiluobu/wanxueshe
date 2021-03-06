package com.wxs.mapper.course;

import com.wxs.entity.course.TStudentLessones;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author skyer
 * @since 2017-09-23
 */
public interface TStudentLessonesMapper extends BaseMapper<TStudentLessones> {

    @Select("SELECT scheduleStatus,count(1) AS qty FROM t_student_lessones WHERE studentId =#{studentId} AND courseId=#{courseId} GROUP BY scheduleStatus")
    public List<Map<String,Object>> groupLessesByStudentId(@Param("studentId") Long studentId,@Param("courseId") Long courseId);

    @Select("SELECT scheduleStatus,count(1) AS qty FROM t_student_lessones WHERE userId =#{userId} AND courseId=#{courseId} GROUP BY scheduleStatus")
    public List<Map<String,Object>> groupLessesByUserId(@Param("userId") Long userId,@Param("courseId") Long courseId);

}