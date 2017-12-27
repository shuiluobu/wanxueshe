package com.wxs.mapper.customer;

import com.wxs.entity.customer.TStudent;
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
public interface TStudentMapper extends BaseMapper<TStudent> {

    @Select("SELECT Group_concat(t.realName) studentName from t_student t,t_student_class c where t.id=c.studentId and c.userId=#{userId} " +
            "and c.coursesId=#{coursesId} ")
    @ResultType(String.class)
    public String getStudentByCourse(@Param("coursesId") Long coursesId,@Param("userId") Long userId);

    @Select("SELECT count(1) from t_student where userId=#{userId} ")
    @ResultType(int.class)
    public int getParentStudentCount(@Param("userId") Long userId);

    @Select("SELECT id studentId,realName,headImg FROM t_student WHERE userId = #{userId}")
    @ResultType(Map.class)
    public List<Map<String,Object>> getStudentInfoOfUser(@Param("userId") Long userId);
    //根据 学生名字 搜索 学生
    @Select(" select a.* " +
            "from t_student_class t " +
            "left join t_student a on a.id = t.studentId " +
            "where t.organizationId = #{organId} " +
            "and a.realName like concat('%',#{name},'%')")
    @ResultMap("BaseResultMap")
    List<TStudent> searchByName(@Param("name")String name,@Param("organId") Long organId);

    //根据 教育机构Id  获取其下 所有学生
    @Select(" select a.* " +
            "from t_student_class t " +
            "left join t_student a on a.id = t.studentId " +
            "where t.organizationId = #{organId} ")
    @ResultMap("BaseResultMap")
    List<TStudent> getAllByOrganId(Long organId);

}