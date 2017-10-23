package com.wxs.mapper.activity;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.activity.TOrganActivity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author skyer
 * @since 2017-09-29
 */
public interface TOrganActivityMapper extends BaseMapper<TOrganActivity> {
    @Select("SELECT * FROM t_organ_activity WHERE organId =#{organId} ")
    @ResultMap("BaseResultMap")
    public List<TOrganActivity> getActivityOfOrgan(@Param("organId") Long organId);

}