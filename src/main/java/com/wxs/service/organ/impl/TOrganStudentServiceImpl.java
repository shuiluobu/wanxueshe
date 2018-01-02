package com.wxs.service.organ.impl;

import com.wxs.entity.customer.TTeacher;
import com.wxs.entity.organ.TOrganStudent;
import com.wxs.mapper.organ.TOrganStudentMapper;
import com.wxs.service.organ.ITOrganStudentService;
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
 * @since 2017-12-29
 */
@Service
public class TOrganStudentServiceImpl extends ServiceImpl<TOrganStudentMapper, TOrganStudent> implements ITOrganStudentService {

    @Autowired
    private TOrganStudentMapper organStudentMapper;

    @Override
    public List<TTeacher> searchAdvisorByName(Long organId, String name) {
        return organStudentMapper.searchAdvisorByName(organId,name);
    }
}
