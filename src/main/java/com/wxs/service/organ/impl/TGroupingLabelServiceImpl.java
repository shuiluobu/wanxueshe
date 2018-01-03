package com.wxs.service.organ.impl;

import com.wxs.entity.organ.TGroupingLabel;
import com.wxs.mapper.organ.TGroupingLabelMapper;
import com.wxs.service.organ.ITGroupingLabelService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生分组标签表 服务实现类
 * </p>
 *
 * @author wyh
 * @since 2018-01-03
 */
@Service
public class TGroupingLabelServiceImpl extends ServiceImpl<TGroupingLabelMapper, TGroupingLabel> implements ITGroupingLabelService {

    @Autowired
    private TGroupingLabelMapper groupingLabelMapper;
	
}
