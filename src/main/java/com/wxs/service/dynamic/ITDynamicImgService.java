package com.wxs.service.dynamic;

import com.baomidou.mybatisplus.service.IService;
import com.wxs.entity.comment.TDynamicImg;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
public interface ITDynamicImgService extends IService<TDynamicImg> {

    //根据动态Id  dynamicID 获取  其下 所有动态图片
    public List<TDynamicImg> getAllByDynamicId(Long dynamicId);
}
