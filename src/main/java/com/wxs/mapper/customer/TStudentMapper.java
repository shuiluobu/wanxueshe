package com.wxs.mapper.customer;

import com.wxs.entity.customer.TStudent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
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
public interface TStudentMapper extends BaseMapper<TStudent> {

    @Select("SELECT t.realName from t_student t,t_student_class c where t.id=c.studentId and c.userId=#{userId} " +
            "and c.coursesId=#{coursesId} ")
    @ResultType(Map.class)
    public List<Map<String,Object>> getStudentByCourse(Long courseId,Long userId);

    @Select("SELECT count(1) from t_student where c.parentId=#{parentId} ")
    @ResultType(int.class)
    public int getParentStudentCount(Long parentId);

}