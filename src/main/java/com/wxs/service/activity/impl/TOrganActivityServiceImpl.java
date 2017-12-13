package com.wxs.service.activity.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.entity.activity.TOrganActivity;
import com.wxs.mapper.activity.TOrganActivityMapper;
import com.wxs.service.activity.ITOrganActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-09-29
 */
@Service
public class TOrganActivityServiceImpl extends ServiceImpl<TOrganActivityMapper, TOrganActivity> implements ITOrganActivityService {

    @Autowired
    private  TOrganActivityMapper organActivityMapper;
    @Override
    public List<TOrganActivity> getActivityOfOrgan(Long organId,Integer page) {
        return organActivityMapper.getActivityOfOrgan(organId,page-1);
    }


}
