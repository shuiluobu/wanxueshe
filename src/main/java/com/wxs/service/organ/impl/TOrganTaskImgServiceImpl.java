package com.wxs.service.organ.impl;

import com.wxs.entity.organ.TOrganTaskImg;
import com.wxs.mapper.organ.TOrganTaskImgMapper;
import com.wxs.service.organ.ITOrganTaskImgService;
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
 * @since 2017-12-14
 */
@Service
public class TOrganTaskImgServiceImpl extends ServiceImpl<TOrganTaskImgMapper, TOrganTaskImg> implements ITOrganTaskImgService {

    @Autowired
    private TOrganTaskImgMapper organTaskImgMapper;

    @Override
    public List<TOrganTaskImg> getAllByTaskId(long taskId) {
        return organTaskImgMapper.getAllByTaskId(taskId);
    }
}
