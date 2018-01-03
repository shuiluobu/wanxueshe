package com.wxs.mapper.organ;

import com.wxs.entity.organ.TStudentImpressTag;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  * 学生的印象标签 Mapper 接口
 * </p>
 *
 * @author wyh
 * @since 2018-01-03
 */
public interface TStudentImpressTagMapper extends BaseMapper<TStudentImpressTag> {

    //获取某机构端 学生 的 所有 印象标签
    @Select(" select * from t_student_impressTag where studentId = #{studentId}")
    @ResultMap("BaseResultMap")
    List<TStudentImpressTag> getAllByStuId(@Param("studentId") Long studentId);

}