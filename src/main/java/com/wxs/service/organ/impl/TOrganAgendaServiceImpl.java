package com.wxs.service.organ.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.entity.organ.TOrganAgenda;
import com.wxs.mapper.organ.TOrganAgendaMapper;
import com.wxs.service.organ.ITOrganAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/12/5.
 */
@Service
public class TOrganAgendaServiceImpl extends ServiceImpl<TOrganAgendaMapper,TOrganAgenda> implements ITOrganAgendaService {

    @Autowired
    private TOrganAgendaMapper organAgendaMapper;

    @Override
    public List<TOrganAgenda> myOrganAgenda(Long userId,String startTime,String endTime) {
        return organAgendaMapper.myOrganAgenda(userId,startTime,endTime);
    }

    @Override
    public List<TOrganAgenda> organAgenda(Long userId, String startTime, String endTime) {
        return organAgendaMapper.organAgenda(userId,startTime,endTime);
    }

    @Override
    public List<TOrganAgenda> getAgendaByUserName(String userName, String startTime, String endTime) {
        return organAgendaMapper.getAgendaByUserName(userName,startTime,endTime);
    }
}
