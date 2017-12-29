package com.wxs.mapper.organ;

import com.wxs.entity.customer.TTeacher;
import com.wxs.entity.organ.TOrganStudent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
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
    @Select(" select t.* from t_teacher t " +
            "inner join t_organ_student a on a.advisorTeacherId = t.id " +
            "where a.organId = #{organId} " +
            "and ( t.teacherName like concat('%',#{name},'%') or  t.realName like concat('%',#{name},'%'))")
    List<TTeacher> searchAdvisorByName(Long organId, String name);

}