package com.wxs.service.comment.impl;

import com.wxs.entity.comment.TDyimg;
import com.wxs.mapper.comment.TDyimgMapper;
import com.wxs.service.comment.ITDyimgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@Service
public class TDyimgServiceImpl extends ServiceImpl<TDyimgMapper, TDyimg> implements ITDyimgService {

    @Autowired
    private TDyimgMapper dyimgMapper;
    @Override
    public List<TDyimg> getAllByDynamicId(Long dynamicId) {
        return dyimgMapper.getAllByDynamicId(dynamicId);
    }
}
