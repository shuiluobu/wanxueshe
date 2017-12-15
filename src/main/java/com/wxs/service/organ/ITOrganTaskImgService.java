package com.wxs.service.organ;

import com.wxs.entity.organ.TOrganTaskImg;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyh
 * @since 2017-12-14
 */
public interface ITOrganTaskImgService extends IService<TOrganTaskImg> {

    //根据taskId获取其下所有图片
    List<TOrganTaskImg> getAllByTaskId(long taskId);
	
}
