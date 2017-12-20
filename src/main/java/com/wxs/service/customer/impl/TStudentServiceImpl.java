package com.wxs.service.customer.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.wxs.entity.comment.TDyimg;
import com.wxs.entity.comment.TDynamicmsg;
import com.wxs.entity.course.TClass;
import com.wxs.entity.course.TCourse;
import com.wxs.entity.customer.TParent;
import com.wxs.entity.customer.TStudent;
import com.wxs.entity.task.TClassWork;
import com.wxs.entity.task.TStudentWork;
import com.wxs.mapper.course.TStudentClassMapper;
import com.wxs.mapper.customer.TFllowCourseMapper;
import com.wxs.mapper.customer.TFollowUserMapper;
import com.wxs.mapper.customer.TStudentMapper;
import com.wxs.mapper.organ.TFllowOrganMapper;
import com.wxs.service.comment.ITDynamicmsgService;
import com.wxs.service.customer.ITStudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wxs.core.util.BaseUtil;

import java.util.Date;
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

    @Autowired
    private TFllowOrganMapper fllowOrganMapper;
    @Autowired
    private TFollowUserMapper followUserMapper;
    @Autowired
    private TFllowCourseMapper fllowCourseMapper;
    @Autowired
    private TStudentClassMapper studentClassMapper; //我的课程
    @Autowired
    private ITDynamicmsgService dynamicmsgService;


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
     *
     * @param userId
     * @return
     */
    public Map<String, Object> getMyCourses(Long userId) {
        //我的课程
        Map<String, Object> result = Maps.newHashMap();
        result.put("0", isEndMyCourses(userId, 0)); //未完成课程
        result.put("1", isEndMyCourses(userId, 1)); //已完成课程
        return result;
    }

    public List<Map<String, Object>> isEndMyCourses(Long userId, Integer isEnd) {
        return studentClassMapper.getMyCourses(ImmutableMap.of("userId", userId, "isEnd", isEnd));
    }

    @Override
    @Transactional
    public Map<String, Object> saveMygrowth(List<TDyimg> dyimgs, TDynamicmsg dynamic, Long workId) {
        try {
            TClassWork classWork = new TClassWork().selectById(workId);
            TCourse course = new TCourse().selectById(classWork.getCourseId());
            dynamic.setCourseId(classWork.getCourseId()); //班级
            dynamic.setClassLessonId(classWork.getLeessonId()); //课节
            dynamic.setOrganId(course.getOrganizationId()); //机构
            dynamic.setCourseId(course.getId()); //课程
            dynamic.setDynamicType("GERENCZ");//类型为个人成长
            dynamic.setStatus(0);
            dynamic.insert(); //保存动态
            TStudentWork studentTask = new TStudentWork();
            studentTask.setWorkId(workId);
            studentTask.setDynamicId(dynamic.getId());
            studentTask.setStudentId(dynamic.getStudentId());
            studentTask.setCreateTime(new Date());
            studentTask.insert();//保存作业
            for (TDyimg dyimg : dyimgs) {
                dyimg.setDynamicId(dynamic.getId());
                dyimg.insert(); //动态图片保存
            }

            Map<String, Object> dynMap = BaseUtil.convertBeanToMap(dynamic);
            return dynamicmsgService.buildOneDynamic(dynMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Map<Long, String> queryStudentByUserId(Long userId) {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("userId", userId);
        List<TStudent> students = baseMapper.selectList(wrapper);
        Map<Long, String> result = Maps.newHashMap();
        students.stream().forEach(tStudent -> {
            result.put(tStudent.getId(),tStudent.getRealName());
            //BaseUtil.getKeyValueMap(result, tStudent.getId(), tStudent.getRealName());
        });
        return result;
    }
    @Override
    public Map<String,Object> saveStudent(TStudent student){
        Map<String,Object> result = Maps.newHashMap();
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("userId",student.getUserId());
        TParent parent = new TParent().selectOne(wrapper);
        student.setParentId(parent.getId());
        baseMapper.insert(student);
        result.put("success",true);
        result.put("message","保存成功");
        return result;
    }


}
