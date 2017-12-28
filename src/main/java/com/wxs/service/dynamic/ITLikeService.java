package com.wxs.service.dynamic;

import com.wxs.entity.comment.TLike;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
public interface ITLikeService extends IService<TLike> {

    //根据 动态Id和用户Id  获取一条赞
    public TLike getOneByDUId(Long dynamicId,Long userId);
    //根据动态Id  获取其下 所有点赞
    public List<TLike> getAllByDynamicId(Long dynamicId);
}
