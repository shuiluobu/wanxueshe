package com.wxs.mapper.organ;

import com.wxs.entity.organ.TAgendaCompletionDict;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wyh
 * @since 2017-12-26
 */
public interface TAgendaCompletionDictMapper extends BaseMapper<TAgendaCompletionDict> {

    //获取所有的待办完成情况  字典
    @Select("select * from t_agenda_completion_dict")
    @ResultMap("BaseResultMap")
    List<TAgendaCompletionDict> getAll();

}