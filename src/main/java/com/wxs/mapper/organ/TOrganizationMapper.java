package com.wxs.mapper.organ;

import com.wxs.entity.course.TCourseCategory;
import com.wxs.entity.organ.TOrganization;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
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
public interface TOrganizationMapper extends BaseMapper<TOrganization> {

    @Select("SELECT o.* from t_organization o where 1=1 " +
            " and (" +
            "    acos(" +
            "     sin((#{latitude}*3.1415)/180) * sin((latitude*3.1415)/180) + \n" +
            "     cos((#{latitude}*3.1415)/180) * cos((latitude*3.1415)/180) * cos((#{longitude}*3.1415)/180 - (longitude*3.1415)/180)\n" +
            "     )*6370.996 " +
            "     )<=#{range}")
    @ResultType(TOrganization.class)
    public List<TOrganization> getNearOrgans(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("range") double range);

    public List<Map<String,Object>> queryOrganByLikeName(@Param("organName") String organName);

    @Select("SELECT id organId FROM t_organization WHERE organName LIKE '%${organName}%' ")
    @ResultType(Long.class)
    public List<Long> queryOrganIdByLikeName(@Param("organName") String organName);
}