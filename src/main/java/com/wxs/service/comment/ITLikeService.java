package com.wxs.service.comment;

import com.wxs.entity.comment.TLike;
import com.baomidou.mybatisplus.service.IService;

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
}
