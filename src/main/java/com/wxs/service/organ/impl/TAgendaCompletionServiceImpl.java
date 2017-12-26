package com.wxs.service.organ.impl;

import com.wxs.entity.organ.TAgendaCompletion;
import com.wxs.mapper.organ.TAgendaCompletionMapper;
import com.wxs.service.organ.ITAgendaCompletionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyh
 * @since 2017-12-26
 */
@Service
public class TAgendaCompletionServiceImpl extends ServiceImpl<TAgendaCompletionMapper, TAgendaCompletion> implements ITAgendaCompletionService {

    @Autowired
    private TAgendaCompletionMapper agendaCompletionMapper;

    @Override
    public TAgendaCompletion getByAgendaId(Long agendaId) {
        return agendaCompletionMapper.getByAgendaId(agendaId);
    }
}
