package com.wxs.mapper.organ;

import com.wxs.entity.customer.TTeacher;
import com.wxs.entity.organ.TOrganStudent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wyh
 * @since 2017-12-29
 */
public interface TOrganStudentMapper extends BaseMapper<TOrganStudent> {

    //根据 机构Id  和 课程顾问名字 搜索 该机构的 课程顾问
    List<TTeacher> searchAdvisorByName(@Param("organId") Long organId, @Param("name")String name);

}