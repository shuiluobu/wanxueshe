package com.wxs.service.dynamic.impl;


import com.wxs.entity.comment.TDynamicImg;
import com.wxs.mapper.dynamic.TDynamicImgMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.service.dynamic.ITDynamicImgService;
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
public class TDynamicImgServiceImpl extends ServiceImpl<TDynamicImgMapper, TDynamicImg> implements ITDynamicImgService {

    @Autowired
    private TDynamicImgMapper dynamicImgMapper;
    @Override
    public List<TDynamicImg> getAllByDynamicId(Long dynamicId) {
        return dynamicImgMapper.getAllByDynamicId(dynamicId);
    }
}
