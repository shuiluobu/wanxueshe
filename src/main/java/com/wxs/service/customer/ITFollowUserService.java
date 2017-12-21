package com.wxs.service.customer;


import com.baomidou.mybatisplus.service.IService;
import com.wxs.entity.customer.TFollowTeacher;
import com.wxs.entity.customer.TFollowUser;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-12-21
 */
public interface ITFollowUserService extends IService<TFollowUser> {
    public List<Map<String,Object>> getUserFriends(Long userId);
}
