package com.wxs.service.customer.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
 *  服务实现类
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
        List<Map<String,Object>>  list = followUserMapper.getMyFriend(userId);
        list.stream().forEach(friend -> {
            Long fUserId = Long.parseLong(friend.get("fuserId").toString());
            //TParent parent = new TParent().selectOne(new EntityWrapper().where("userId={0}",fUserId));
            friend.put("studentCount",studentMapper.getParentStudentCount(fUserId));
            friend.put("courseCount",studentClassMapper.getParentCourseCount(fUserId));
        });
        return list;
    }
}
