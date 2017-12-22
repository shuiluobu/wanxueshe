package com.wxs.service.customer.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.wxs.entity.customer.TFollowTeacher;
import com.wxs.entity.customer.TFollowUser;
import com.wxs.entity.customer.TParent;
import com.wxs.mapper.course.TStudentClassMapper;
import com.wxs.mapper.customer.TFollowTeacherMapper;
import com.wxs.mapper.customer.TFollowUserMapper;
import com.wxs.mapper.customer.TStudentMapper;
import com.wxs.service.customer.ITFollowTeacherService;
import com.wxs.service.customer.ITFollowUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-12-21
 */
@Service
public class TFollowUserServiceImpl extends ServiceImpl<TFollowUserMapper, TFollowUser> implements ITFollowUserService {
    @Autowired
    private TFollowUserMapper followUserMapper;
    @Autowired
    private TStudentMapper studentMapper;
    @Autowired
    private TStudentClassMapper studentClassMapper;

    @Override
    public List<Map<String, Object>> getUserFriends(Long userId) {
        List<Map<String, Object>> list = followUserMapper.getMyFriend(userId);
        list.stream().forEach(friend -> {
            Long fUserId = Long.parseLong(friend.get("fuserId").toString());
            //TParent parent = new TParent().selectOne(new EntityWrapper().where("userId={0}",fUserId));
            friend.put("studentCount", studentMapper.getParentStudentCount(fUserId));
            friend.put("courseCount", studentClassMapper.getParentCourseCount(fUserId));
        });
        return list;
    }

    @Override
    public TFollowUser getOneFollowUser(Long userId, Long friendId, String relationType) {
        TFollowUser friend = new TFollowUser();
        friend.setUserId(userId);
        friend.setFuserId(friendId);
        if(relationType!=null){
            friend.setRelationType(relationType);
        }
        friend = followUserMapper.selectOne(friend);
        return friend;
    }

    @Override
    public Map<String,Object> sendAddFriendReq(Long userId, Long friendId) {
        Map<String,Object> result = Maps.newHashMap();
        TFollowUser followUser = getOneFollowUser(userId, friendId, "20");
        if (followUser != null) {
            //不发送加好友的信息给对方，对方已经屏蔽了
            result.put("success",false);
            result.put("message","您已被该用户屏蔽，无法添加好友");
        } else {
            //发送加朋友的消息给对方
            result.put("success",true);
            result.put("message","您已请求已发送");
        }
        return result;
    }
    @Override
    public Map<String, Object> addUserFriend(Long userId, Long friendId) {
        Map<String, Object> result = Maps.newHashMap();
        //20表示朋友
        TFollowUser friend = getOneFollowUser(userId,friendId,null);
        if(friend!=null){
            if(!friend.getRelationType().equals("20")){
                result.put("message", "添加好友成功");
                result.put("success",false);
            } else {
                friend.setRelationType("10");
                followUserMapper.updateById(friend);
            }
        } else {
             friend = new TFollowUser();
             friend.setUserId(userId);
             friend.setFuserId(friendId);
             friend.setRelationType("10");
             friend.insert();
        }
        result.put("message", "添加好友成功");
        result.put("success",true);
        return result;
    }
    @Override
    public Map<String,Object> updateFollowUser(Long userId,Long friendId,String relationType){
        Map<String, Object> result = Maps.newHashMap();
        TFollowUser followUser = new TFollowUser();
        followUser.setUserId(userId);
        followUser.setFuserId(friendId);
        followUser.setRelationType("30");
        Integer flag = followUserMapper.updateById(followUser);
        if (flag==0) {
            result.put("success", false);
            result.put("message", "操作失败");
        } else {
            result.put("success", true);
            result.put("message", "操作成功");
        }
        return result;
    }

}
