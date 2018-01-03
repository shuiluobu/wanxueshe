package com.wxs.service.organ;

import com.wxs.entity.organ.TOrganParent;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyh
 * @since 2018-01-03
 */
public interface ITOrganParentService extends IService<TOrganParent> {

    //根据学生Id 获取 其 所有家长
    List<TOrganParent> getAlByStuId(Long studentId);
	
}
