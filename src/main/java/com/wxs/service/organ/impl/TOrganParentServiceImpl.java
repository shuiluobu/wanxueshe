package com.wxs.service.organ.impl;

import com.wxs.entity.organ.TOrganParent;
import com.wxs.mapper.organ.TOrganParentMapper;
import com.wxs.service.organ.ITOrganParentService;
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
 * @since 2018-01-03
 */
@Service
public class TOrganParentServiceImpl extends ServiceImpl<TOrganParentMapper, TOrganParent> implements ITOrganParentService {

    @Autowired
    private TOrganParentMapper organParentMapper;

    @Override
    public List<TOrganParent> getAlByStuId(Long studentId) {
        return organParentMapper.getAlByStuId(studentId);
    }
}
