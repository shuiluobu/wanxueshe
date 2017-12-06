package com.wxs.mapper.organ;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.organ.TOrganAgenda;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by wyh on 2017/12/5.
 */
public interface TOrganAgendaMapper extends BaseMapper<TOrganAgenda> {
    //获取用户的待办
    @Select(" select t.*,a.teacherName as userName from t_organ_agenda t " +
            "left join t_teacher a on a.id = t.userId " +
            "where t.userId = #{userId} " +
            "and t.startTime > #{startTime} and t.startTime < #{endTime}")
    @ResultMap("BaseResultMap")
    public List<TOrganAgenda> myOrganAgenda(@Param("userId") Long userId,@Param("startTime")String startTime,@Param("endTime")String endTime);

    //获取用户所属机构所有的待办
    @Select(" select t.*,a.teacherName as userName from t_organ_agenda t " +
            "left join t_teacher a on a.id = t.userId " +
            "where t.organId = (select organId from t_teacher where id = #{userId}) " +
            "and t.startTime > #{startTime} and t.startTime < #{endTime}")
    @ResultMap("BaseResultMap")
    public List<TOrganAgenda> organAgenda(@Param("userId") Long userId,@Param("startTime")String startTime,@Param("endTime")String endTime);

    //根据用户名  搜索 待办
    @Select(" select t.*,a.teacherName as userName  from t_organ_agenda t " +
            "inner join t_teacher a on a.id = t.userId " +
            "where ( a.teacherName like concat('%',#{userName},'%') or a.realName like  concat('%',#{userName},'%') ) "+
            "and t.startTime > #{startTime} and t.startTime < #{endTime}")
    @ResultMap("BaseResultMap")
    public List<TOrganAgenda> getAgendaByUserName(@Param("userName") String userName,@Param("startTime")String startTime,@Param("endTime")String endTime);
}
