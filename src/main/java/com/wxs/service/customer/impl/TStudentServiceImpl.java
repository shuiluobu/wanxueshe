package com.wxs.service.customer.impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.wxs.entity.customer.TStudent;
import com.wxs.mapper.course.TStudentClassMapper;
import com.wxs.mapper.customer.TFllowCourseMapper;
import com.wxs.mapper.customer.TFollowUserMapper;
import com.wxs.mapper.customer.TStudentMapper;
import com.wxs.mapper.organ.TFllowOrganMapper;
import com.wxs.service.customer.ITStudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class TStudentServiceImpl extends ServiceImpl<TStudentMapper, TStudent> implements ITStudentService {

    private TFllowOrganMapper fllowOrganMapper;
    private TFollowUserMapper followUserMapper;
    private TFllowCourseMapper fllowCourseMapper;
    private TStudentClassMapper studentClassMapper; //我的课程

    private static String MY_FOLLOW_ORGAN = "我关注的机构";
    private static String MY_FOLLOW_TEACHER = "我关注的老师";
    private static String MY_FOLLOW_COURSE = "我关注的课程";

    public Map<String, List> getMyFollow(Long userId) {
        //我的关注分为：我关注的机构，我关注的课程，我关注的老师
        List organs = fllowOrganMapper.getFllowOrganByUser(userId); //我关注的机构
        List teachers = followUserMapper.getFllowTeacherByUser(userId); //我关注的老师
        List courses = fllowCourseMapper.getFllowCoursesByUser(userId);//我关注的课程
        return ImmutableMap.of(MY_FOLLOW_ORGAN, organs, MY_FOLLOW_TEACHER, teachers, MY_FOLLOW_COURSE, courses);
    }

    /**
     * 个人首页，课程，我的课程
     * @param userId
     * @return
     */
    public Map<String, Object> getMyCourses(Long userId) {
        //我的课程
        Map<String,Object> result = Maps.newHashMap();
        result.put("0", isEndMyCourses(userId,0)); //未完成课程
        result.put("1", isEndMyCourses(userId,1)); //已完成课程
        return result;
    }

    public List<Map<String,Object>> isEndMyCourses(Long userId,Integer isEnd){
      return   studentClassMapper.getMyCourses(userId,isEnd);
    }
}
