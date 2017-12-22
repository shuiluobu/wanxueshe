package com.wxs.service.customer.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.wxs.entity.comment.TDyimg;
import com.wxs.entity.comment.TDynamicmsg;
import com.wxs.entity.comment.TDyvideo;
import com.wxs.entity.course.TCourse;
import com.wxs.entity.customer.TFollowTeacher;
import com.wxs.entity.customer.TParent;
import com.wxs.entity.customer.TStudent;
import com.wxs.entity.task.TClassWork;
import com.wxs.entity.task.TStudentWork;
import com.wxs.enu.EnuDynamicTypeCode;
import com.wxs.mapper.course.TStudentClassMapper;
import com.wxs.mapper.customer.TFollowCourseMapper;
import com.wxs.mapper.customer.TFollowTeacherMapper;
import com.wxs.mapper.customer.TFollowUserMapper;
import com.wxs.mapper.customer.TStudentMapper;
import com.wxs.mapper.organ.TFollowOrganMapper;
import com.wxs.service.comment.ITDynamicmsgService;
import com.wxs.service.customer.ITStudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wxs.core.util.BaseUtil;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
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
    private TStudentClassMapper studentClassMapper; //我的课程
    @Autowired
    private ITDynamicmsgService dynamicmsgService;
    @Autowired
    private TStudentMapper studentMapper;

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

    @Override
    public List<Map<String,Object>> getStudentOfUser(Long userId){
        EntityWrapper<TStudent> wrapper = new EntityWrapper<>();
        wrapper.eq("userId",userId);
        List<TStudent> students =  studentMapper.selectList(wrapper);
        List<Map<String,Object>> studentMapList = new ArrayList<>();
        students.stream().forEach(tStudent -> {
            Map<String,Object> studentMap = Maps.newHashMap();
            studentMap.put("realName",tStudent.getRealName());
            studentMap.put("parentType",tStudent.getParentType());
            studentMap.put("courseCount",studentClassMapper.getStudentCourseCount(tStudent.getId()));
            studentMapList.add(studentMap);
        });
        return studentMapList;
    }

    public List<Map<String, Object>> isEndMyCourses(Long userId, Integer isEnd) {
        return studentClassMapper.getMyCourses(ImmutableMap.of("userId", userId, "isEnd", isEnd));
    }

    @Override
    @Transactional
    public Map<String, Object> saveMygrowth(List<String> mediaUrls,String mediaType, TDynamicmsg dynamic, Long workId) {
        try {
            TClassWork classWork = new TClassWork().selectById(workId);
            TCourse course = new TCourse().selectById(classWork.getCourseId());
            dynamic.setCourseId(classWork.getCourseId()); //班级
            dynamic.setClassLessonId(classWork.getLessonId()); //课节
            dynamic.setOrganId(course.getOrganizationId()); //机构
            dynamic.setCourseId(course.getId()); //课程
            dynamic.setDynamicType(EnuDynamicTypeCode.DYNAMIC_TYPE_MYGROWTH.getTypeCode());//类型为个人成长
            dynamic.setStatus(0);
            dynamic.insert(); //保存动态
            TStudentWork studentTask = new TStudentWork();
            studentTask.setWorkId(workId);
            studentTask.setDynamicId(dynamic.getId());
            studentTask.setStudentId(dynamic.getStudentId());
            studentTask.setCreateTime(new Date());
            studentTask.insert();//保存作业
            if(mediaType.equals("IMG")){
                for (String mediaUrl : mediaUrls) {
                    TDyimg dyimg = new TDyimg();
                    dyimg.setDynamicId(dynamic.getId());
                    dyimg.setStatus(0);
                    dyimg.setCreateTime(new Date());
                    dyimg.setOriginalImgUrl(mediaUrl);
                    dyimg.insert(); //动态图片保存
                }
            } else {
                String mediaUrl = mediaUrls.get(0);
                TDyvideo dyvideo = new TDyvideo();
                dyvideo.setDynamicId(dynamic.getId());
                dyvideo.setCreateTime(new Date());
                dyvideo.setVideoUrl(mediaUrl);
                dyvideo.insert(); //动态小视频
            }
            Map<String, Object> dynMap = BaseUtil.convertBeanToMap(dynamic);
            return dynamicmsgService.buildOneDynamic(dynMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Map<String,Object>> queryStuInfoByUserId(Long userId) {
        return  baseMapper.getStudentInfoOfUser(userId);
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

    @Override
    public Integer delStudent(Long studentId,Long userId){
        TStudent student = new TStudent().selectById(studentId);
        EntityWrapper wrapper=new EntityWrapper();
        wrapper.eq("userId",userId);
     return    baseMapper.update(student,wrapper);
    }


}
