package com.wxs.service.customer.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wxs.entity.customer.*;
import com.wxs.mapper.course.TStudentClassMapper;
import com.wxs.mapper.customer.*;
import com.wxs.mapper.organ.TFollowOrganMapper;
import com.wxs.service.course.ITCoursesService;
import com.wxs.service.customer.ITParentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.service.customer.ITTeacherService;
import com.wxs.service.organ.ITOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@Service
public class TParentServiceImpl extends ServiceImpl<TParentMapper, TParent> implements ITParentService {
    @Autowired
    public ITOrganizationService organizationService;
    @Autowired
    private ITTeacherService teacherService;
    @Autowired
    private ITCoursesService coursesService;
    @Autowired
    private TStudentMapper studentMapper;
    @Autowired
    private TStudentClassMapper studentClassMapper;
    @Autowired
    public TFollowUserMapper followUserMapper;
    @Autowired
    public TFollowOrganMapper followOrganMapper;
    @Autowired
    public TFollowTeacherMapper followTeacherMapper;
    @Autowired
    public TFollowCourseMapper followCourseMapper;

    private static String MY_FOLLOW_ORGAN = "MY_FOLLOW_ORGAN";
    private static String MY_FOLLOW_TEACHER = "MY_FOLLOW_TEACHER";
    private static String MY_FOLLOW_COURSE = "MY_FOLLOW_COURSE";

    public Map<String, List> getMyFollow(Long userId) {
        //我的关注分为：我关注的机构，我关注的课程，我关注的老师
        List organs = organizationService.getFollowOrganInfoByUserId(userId); //我关注的机构
        List teachers = teacherService.getFollowTeachInfoByUserId(userId); //我关注的老师
        List courses = coursesService.getFollowCourseInfoByUserId(userId);//我关注的课程
        return ImmutableMap.of(MY_FOLLOW_ORGAN, organs, MY_FOLLOW_TEACHER, teachers, MY_FOLLOW_COURSE, courses);
    }
    @Override
    public Map<String, Object> getParentOutline(Long userId, Long loginUserId) {
        Map<String, Object> resultMap = Maps.newHashMap();
        Integer studentCount = studentMapper.getParentStudentCount(userId); //学生个数
        Integer courseCount = studentClassMapper.getParentCourseCount(userId); //我的课程数量
        TFrontUser user = new TFrontUser();
        if (userId == null || userId==loginUserId) {
            user = user.selectById(loginUserId);//表示查自己
            resultMap.put("isFriend", 3);
        } else {
            user = user.selectById(userId);
            resultMap.put("isFriend",0); //默认不是好友
        }
        resultMap.put("followCount",getParentFollowCount(userId));
        resultMap.put("studentCount",studentCount);
        resultMap.put("courseCount",courseCount);
        TFollowUser followUser = new TFollowUser().selectOne("userId={0} and fuserId={1}", loginUserId, userId);
        if (followUser != null) {
            resultMap.put("userName", followUser.getMemoName());
            resultMap.put("isFriend",1);
        } else {
            resultMap.put("userName", user.getUserName());
            resultMap.put("isFriend",0);
        }
        resultMap.put("headImg",user.getHeadImg());
        resultMap.put("userId", user.getId());
        return resultMap;
    }

    @Override
    public List<Map<String, Object>> getFllowUsers(List<Long> userIds, Long loginUserId) {
        List<Map<String, Object>> resultList = Lists.newArrayList();
        userIds.stream().forEach(friendUserId -> {
            Map<String, Object> map = Maps.newHashMap();
            TParent parent = new TParent().selectOne(new EntityWrapper().where("userId={0}", friendUserId));
            map.put("userId", friendUserId);
            map.put("realName", parent.getRealName());
            map.put("studentCount", studentMapper.getParentStudentCount(friendUserId));
            map.put("courseCount", studentClassMapper.getParentCourseCount(parent.getId()));
            Integer isFriednCount = followUserMapper.getIsFriednCount(loginUserId, friendUserId);
            if (isFriednCount > 0) {
                map.put("isFriend", 1);//1：表示已经是好友
            } else {
                map.put("isFriend", 0); //是否已经加好友
            }
            resultList.add(map);
        });
        return resultList;
    }

    private int getParentFollowCount(Long userId){
        int organCount = followOrganMapper.getFollowOrganCountByUser(userId); //我关注的机构
        int teacherCount = followTeacherMapper.getFollowTeachCounterByUserId(userId); //我关注的老师
        int courseCount = followCourseMapper.getFollowCourseCountByUserId(userId);//我关注的课程
        Integer followCount = organCount + teacherCount + courseCount;
        return followCount;
    }
}
