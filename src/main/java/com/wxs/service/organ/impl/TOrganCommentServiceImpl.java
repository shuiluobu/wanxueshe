package com.wxs.service.organ.impl;

import com.wxs.entity.organ.TOrganComment;
import com.wxs.mapper.organ.TOrganCommentMapper;
import com.wxs.service.organ.ITOrganCommentService;
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
 * @since 2017-12-08
 */
@Service
public class TOrganCommentServiceImpl extends ServiceImpl<TOrganCommentMapper, TOrganComment> implements ITOrganCommentService {

    @Autowired
    private TOrganCommentMapper organCommentMapper;
    @Override
    public List<TOrganComment> getAllById(Long itemId,Integer type) {
        return organCommentMapper.getAllById(itemId,type);
    }
}
