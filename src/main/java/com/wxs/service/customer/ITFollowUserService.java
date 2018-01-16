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
    public List<Map<String,Object>> getMyFriendInfos(Long userId);
    public List<Map<String, Object>> getMyShieldInfos(Long userId);
    public List<Long> geFriendIdsByUserId(Long userId);
    Map<String,Object> sendAddFriendReq(Long userId,Long friendId);
    Map<String, Object> addUserFriend(Long userId, Long friendId,Integer isAgree);
    TFollowUser getOneFollowUser(Long userId,Long friendId,String relationType);
    Map<String,Object> updateFollowUser(Long userId,Long friendId,String relationType);
}
