package com.wxs.mapper.organ;

import com.wxs.entity.organ.TOrganTask;
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
 * @since 2017-12-08
 */
public interface TOrganTaskMapper extends BaseMapper<TOrganTask> {

    //根据 待办Id ,类型和完成状态 获取 其他所有的 任务
    public List<TOrganTask> getAllByAgendaId(@Param("agendaId") Long agendaId,@Param("type") Integer type,@Param("statuss")List<Integer> statuss);

}