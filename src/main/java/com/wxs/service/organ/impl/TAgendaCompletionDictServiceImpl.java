package com.wxs.service.organ.impl;

import com.wxs.cache.ICache;
import com.wxs.entity.organ.TAgendaCompletionDict;
import com.wxs.mapper.organ.TAgendaCompletionDictMapper;
import com.wxs.service.organ.ITAgendaCompletionDictService;
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
 * @since 2017-12-26
 */
@Service
public class TAgendaCompletionDictServiceImpl extends ServiceImpl<TAgendaCompletionDictMapper, TAgendaCompletionDict> implements ITAgendaCompletionDictService {

    @Autowired
    private TAgendaCompletionDictMapper agendaCompletionDictMapper;
    @Autowired
    private ICache cache;

    @Override
    public List<TAgendaCompletionDict> getAll() {
        Object o = cache.getCache("agendaCompletionDic");
        if(o != null){
            return (List<TAgendaCompletionDict>)o;
        }else{
            List<TAgendaCompletionDict> list = agendaCompletionDictMapper.getAll();
            cache.putCache("agendaCompletionDic",list);
            return list;

        }
    }
}
