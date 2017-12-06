package com.wxs.service.organ;

import com.baomidou.mybatisplus.service.IService;
import com.wxs.entity.organ.TOrganAgenda;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wyh on 2017/12/5.
 */
public interface ITOrganAgendaService extends IService<TOrganAgenda> {

    //获取用户的待办
    public List<TOrganAgenda> myOrganAgenda(Long userId,String startTime,String endTime);
    //获取用户所属机构所有的待办
    public List<TOrganAgenda> organAgenda(Long userId,String startTime,String endTime);
    //根据用户名  搜索 待办
    public List<TOrganAgenda> getAgendaByUserName(String userName,String startTime,String endTime);
}
