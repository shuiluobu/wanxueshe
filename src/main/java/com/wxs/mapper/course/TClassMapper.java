package com.wxs.mapper.course;

import com.wxs.entity.course.TClass;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wyh
 * @since 2017-09-21
 */
public interface TClassMapper extends BaseMapper<TClass> {

    //分页+混合条件 查询 班级
    public List<TClass> pageData(TClass tClass);
    //根据课程Id 获取班级
    @Select(" select * from t_class where courseId = #{courseId}")
    @ResultMap("BaseResultMap")
    public TClass getByCourseId(@Param("courseId") Long courseId);

}