package com.wxs.mapper.organ;

import com.wxs.entity.organ.TOrganParent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wyh
 * @since 2018-01-03
 */
public interface TOrganParentMapper extends BaseMapper<TOrganParent> {

    //根据学生Id 获取 其 所有家长
    @Select(" select * from t_organ_parent where studentId = #{studentId}")
    @ResultMap("BaseResultMap")
    List<TOrganParent> getAllByStuId(@Param("studentId") Long studentId);
}