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
    //根据 班级名称，机构Id,类型(我的班级,不限-所属机构的),用户Id  搜索 班级
    List<TClass> searchByName(@Param("name") String name, @Param("organId")Long organId,@Param("type")Integer type,@Param("userId")Long userId);
    //获取某教师的 所有班级
    List<TClass> searchClass(@Param("teacherId") Long teacherId,@Param("organId")Long organId,
                             @Param("className")String className,@Param("classType")String classType);
    //班级详情信息
    @Select("select t.*,a.courseName,b.realName teacherName,l.organName,l.leval organLevel " +
            "from t_class t " +
            "left join t_class_course a on a.id = t.courseId " +
            "left join t_teacher b on b.id = t.teacherId " +
            "left join t_organization l on l.id = t.organId " +
            "where t.id= #{classId}")
    TClass classInfo(@Param("classId") Long classId);



}