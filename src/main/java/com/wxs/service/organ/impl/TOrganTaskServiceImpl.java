package com.wxs.service.organ.impl;

import com.wxs.entity.organ.TOrganTask;
import com.wxs.mapper.organ.TOrganTaskMapper;
import com.wxs.service.organ.ITOrganTaskService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyh
 * @since 2017-12-08
 */
@Service
public class TOrganTaskServiceImpl extends ServiceImpl<TOrganTaskMapper, TOrganTask> implements ITOrganTaskService {

    @Autowired
    private TOrganTaskMapper organTaskMapper;
    @Override
    public List<TOrganTask> getAllByAgendaId(Long agendaId,Integer type,List<Integer> statuss) {
        return organTaskMapper.getAllByAgendaId(agendaId,type,statuss);
    }

    @Override
    public TOrganTask getDetailByTaskId(Long taskId) {
        return organTaskMapper.getDetailByTaskId(taskId);
    }
}
