package com.wxs.service.comment.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.entity.comment.TLike;
import com.wxs.mapper.comment.TLikeMapper;
import com.wxs.service.comment.ITLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/12/18.
 */
@Service
public class TLikeServiceImpl  extends ServiceImpl<TLikeMapper,TLike> implements ITLikeService {
    @Autowired
    private TLikeMapper likeMapper;

    @Override
    public TLike getOneByDUId(Long dynamicId, Long userId) {
        return likeMapper.getOneByDUId(dynamicId,userId);
    }

    @Override
    public List<TLike> getAllByDynamicId(Long dynamicId) {
        return likeMapper.getAllByDynamicId(dynamicId);
    }
}
