package com.wxs.mapper.course;

import com.wxs.entity.course.TClassLesson;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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
public interface TClassLessonMapper extends BaseMapper<TClassLesson> {
    //根据条件查询课时
    List<TClassLesson> selectLessionByParm(TClassLesson classLesson);
    //获取一个课程下面所有课时信息
    List<Map<String,Object>> queryLessionByCourse(@Param("courseId") Long courseId,@Param("studentId") Long studentId);
}