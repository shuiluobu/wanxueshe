package com.wxs.mapper.organ;

import com.wxs.entity.organ.TAgendaCompletion;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wyh
 * @since 2017-12-26
 */
public interface TAgendaCompletionMapper extends BaseMapper<TAgendaCompletion> {

    //根据  待办Id agendaId  获取 其 执行情况
    @Select(" select * from t_agenda_completion where agendaId = #{agendaId}")
    @ResultMap("BaseResultMap")
    TAgendaCompletion getByAgendaId(@Param("agendaId") Long agendaId);

}