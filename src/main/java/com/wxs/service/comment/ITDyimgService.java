package com.wxs.service.comment;

import com.wxs.entity.comment.TDyimg;
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
public interface ITDyimgService extends IService<TDyimg> {

    //根据动态Id  dynamicID 获取  其下 所有动态图片
    public List<TDyimg> getAllByDynamicId(Long dynamicId);
}
