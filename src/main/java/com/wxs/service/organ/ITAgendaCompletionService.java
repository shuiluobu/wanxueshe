package com.wxs.service.organ;

import com.wxs.entity.organ.TAgendaCompletion;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyh
 * @since 2017-12-26
 */
public interface ITAgendaCompletionService extends IService<TAgendaCompletion> {

    //根据  待办Id agendaId  获取 其 执行情况
    TAgendaCompletion getByAgendaId(Long agendaId);
}
