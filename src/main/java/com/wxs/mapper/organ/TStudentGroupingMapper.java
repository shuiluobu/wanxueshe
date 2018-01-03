package com.wxs.mapper.organ;

import com.wxs.entity.organ.TStudentGrouping;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  * 学生分组 Mapper 接口
 * </p>
 *
 * @author wyh
 * @since 2018-01-03
 */
public interface TStudentGroupingMapper extends BaseMapper<TStudentGrouping> {

    //获取 某 学生 所属 的 所有分组
    @Select(" select * from t_student_grouping where studentId = #{studentId}")
    @ResultMap("BaseResultMap")
    List<TStudentGrouping> getAllByStuId(@Param("studentId") Long studentId);
}