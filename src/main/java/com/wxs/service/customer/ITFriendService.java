package com.wxs.service.customer;


import com.baomidou.mybatisplus.service.IService;
import com.wxs.entity.customer.TFriend;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-12-06
 */
public interface ITFriendService extends IService<TFriend> {
    public List<Map<String,Object>> getUserFriends(Long userId);
    public Map<String,Object> addFriend(Long userId,Long toUserId);
	
}
