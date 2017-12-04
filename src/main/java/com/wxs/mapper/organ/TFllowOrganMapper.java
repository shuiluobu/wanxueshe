package com.wxs.mapper.organ;

import com.wxs.entity.organ.TFllowOrgan;
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
public interface TFllowOrganMapper extends BaseMapper<TFllowOrgan> {
    @Select("SELECT o.* FROM t_fllow_organ f,t_organization o where f.organId=o.id and f.userId =#{userId} ")
    @ResultType(Map.class)
    List<Map<String,Object>> getFllowOrganByUser(@Param("userId") Long userId);

    @Select("SELECT count(1) FROM t_fllow_organ f where  f.organId =#{organId} and status=0")
    @ResultType(int.class)
    public int getOrganFllowCount(Long organId);

    @Select("select * from t_fllow_organ where userId=#{userId} and organId=${organId}")
    @ResultType(TFllowOrgan.class)
    public TFllowOrgan getFllowByUserId(Long userId,Long organId);

}