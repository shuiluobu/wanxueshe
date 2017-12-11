package com.wxs.service.organ;

import com.baomidou.mybatisplus.service.IService;
import com.wxs.entity.organ.TOrganComment;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-12-08
 */
public interface ITOrganCommentService extends IService<TOrganComment> {

    //获取 某项 的 全部评论
    public List<TOrganComment> getAllById(Long itemId,Integer type);
	
}
