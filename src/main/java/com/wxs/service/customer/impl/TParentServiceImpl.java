package com.wxs.service.customer.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wxs.entity.customer.TParent;
import com.wxs.entity.customer.TStudent;
import com.wxs.mapper.course.TStudentClassMapper;
import com.wxs.mapper.customer.*;
import com.wxs.mapper.organ.TFollowOrganMapper;
import com.wxs.service.customer.ITParentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@Service
public class TParentServiceImpl extends ServiceImpl<TParentMapper, TParent> implements ITParentService {
    @Autowired
    private TFollowOrganMapper fllowOrganMapper;
    @Autowired
    private TFollowTeacherMapper followTeacherMapper;
    @Autowired
    private TFollowCourseMapper fllowCourseMapper;
    @Autowired
    private TStudentMapper studentMapper;
    @Autowired
    private TStudentClassMapper studentClassMapper;
    @Autowired
    public TFollowUserMapper followUserMapper;

    private static String MY_FOLLOW_ORGAN = "我关注的机构";
    private static String MY_FOLLOW_TEACHER = "我关注的老师";
    private static String MY_FOLLOW_COURSE = "我关注的课程";

    public Map<String, List> getMyFollow(Long userId) {
        //我的关注分为：我关注的机构，我关注的课程，我关注的老师
        List organs = fllowOrganMapper.getFllowOrganByUser(userId); //我关注的机构
        List teachers = followTeacherMapper.getFllowTeacherByUser(userId); //我关注的老师
        List courses = fllowCourseMapper.getFllowCoursesByUser(userId);//我关注的课程
        return ImmutableMap.of(MY_FOLLOW_ORGAN, organs, MY_FOLLOW_TEACHER, teachers, MY_FOLLOW_COURSE, courses);
    }


    @Override
    public List<Map<String, Object>> getFllowUsers(List<Long> userIds,Long loginUserId) {
       List<Map<String,Object>> resultList = Lists.newArrayList();

        userIds.stream().forEach(friendUserId -> {
            Map<String,Object> map = Maps.newHashMap();
            TParent parent = new TParent().selectOne(new EntityWrapper().where("userId={0}",friendUserId));
            map.put("userId",friendUserId);
            map.put("realName",parent.getRealName());
            map.put("studentCount",studentMapper.getParentStudentCount(friendUserId));
            map.put("courseCount",studentClassMapper.getParentCourseCount(parent.getId()));
            Integer isFriednCount = followUserMapper.getIsFriednCount(loginUserId,friendUserId);
            if(isFriednCount>0){
                map.put("isFriend",1);
            } else {
                map.put("isFriend",0); //是否已经加好友
            }
            resultList.add(map);
        });
        return resultList;

    }
}
