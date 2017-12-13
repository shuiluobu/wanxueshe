package com.wxs.mapper.course;

import com.wxs.entity.course.TCourseCategory;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author skyer
 * @since 2017-09-29
 */
public interface TCourseCategoryMapper extends BaseMapper<TCourseCategory> {

    //分页+混合条件 查询 班级
    public List<TCourseCategory> pageData(TCourseCategory courseCategory);

    public List<Map<String,Object>> getCourseListByParam(@Param("organId") Long organId,@Param("categoryType") String categoryType);

    @Select("SELECT distinct y.courseCategoryName courseName,y.canQty,y.alreadyStudySum,y.courseType " +
            "FROM t_course_category y,t_course c,t_class s where y.id=c.courseCateId " +
            "and c.id=s.courseId and s.teacherId=#{teacherId}")
    @ResultType(Map.class)
    public List<Map<String,Object>> getTeacherCourseList(@Param("teacherId") Long teacherId);



    @Select("SELECT y.* from t_course_category y " +
            "INNER JOIN t_course c on y.id=c.courseCateId\n" +
            "INNER JOIN t_class s on s.id=c.classId where s.teacherId =#{teacherId} "
    )
    @ResultType(TCourseCategory.class)
    public List<TCourseCategory> getAllCategoryByTeacher(@Param("teacherId") Long teacherId);

    @Select("SELECT c.* from t_course_category c,t_organization o where c.organId=o.id " +
            " and (" +
            "    acos(" +
            "     sin((#{latitude}*3.1415)/180) * sin((latitude*3.1415)/180) + \n" +
            "     cos((#{latitude}*3.1415)/180) * cos((latitude*3.1415)/180) * cos((#{longitude}*3.1415)/180 - (longitude*3.1415)/180)\n" +
            "     )*6370.996 " +
            "     )<=#{range}")
    @ResultType(TCourseCategory.class)
    public List<TCourseCategory> getNearByCategorys(@Param("latitude") double latitude, @Param("longitude") double longitude,@Param("range") double range);


}