package com.wxs.service.customer.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.wxs.entity.comment.TDynamic;
import com.wxs.entity.comment.TDynamicImg;
import com.wxs.entity.comment.TDynamicVideo;
import com.wxs.entity.course.TClassCourse;
import com.wxs.entity.customer.TStudent;
import com.wxs.entity.task.TClassWork;
import com.wxs.entity.task.TStudentWork;
import com.wxs.enu.EnuDynamicTypeCode;
import com.wxs.mapper.course.TStudentCourseMapper;
import com.wxs.mapper.customer.TStudentMapper;
import com.wxs.service.dynamic.ITDynamicService;
import com.wxs.service.common.IDictionaryService;
import com.wxs.service.customer.ITStudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wxs.core.util.BaseUtil;

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
    private TStudentCourseMapper studentCourseMapper; //我的课程
    @Autowired
    private ITDynamicService dynamicmsgService;
    @Autowired
    private TStudentMapper studentMapper;
    @Autowired
    public IDictionaryService dictionaryService;

    /**
     * 个人首页，课程，我的课程
     *
     * @param userId
     * @return
     */
    public Map<String, Object> getMyCourses(Long userId, Integer isAll) {
        //我的课程
        Map<String, Object> result = Maps.newHashMap();
        List endList = new ArrayList();
        if (isAll == 0) {
            endList.add(isEndMyCourses(userId, 0).get(0));
        } else {
            endList = isEndMyCourses(userId, 0);
        }
        result.put("0", endList); //未完成课程
        result.put("1", isEndMyCourses(userId, 1)); //已完成课程
        return result;
    }
    @Override
    public Map<String,Object> getOneStudentInfoById(Long studentId){
        Map<String,Object> studentInfo = Maps.newHashMap();
        Map<String, String> parentTypeMap = dictionaryService.getStudentParentType();
        TStudent student = new TStudent().selectById(studentId);
        studentInfo.put("studentId",student.getId());
        studentInfo.put("realName",student.getRealName());
        studentInfo.put("headImg",student.getHeadImg());
        studentInfo.put("parentType", ImmutableMap.of(student.getParentType(),parentTypeMap.get(student.getParentType().toString())));
        return studentInfo;
    }

    @Override
    public List<Map<String, Object>> getStudentOfUser(Long userId) {
        EntityWrapper<TStudent> wrapper = new EntityWrapper<>();
        wrapper.eq("userId", userId);
        wrapper.eq("status",0);
        List<TStudent> students = studentMapper.selectList(wrapper);
        List<Map<String, Object>> studentMapList = new ArrayList<>();
        Map<String, String> parentTypeMap = dictionaryService.getStudentParentType();
        students.stream().forEach(tStudent -> {
            Map<String, Object> studentMap = Maps.newHashMap();
            studentMap.put("realName", tStudent.getRealName());
            studentMap.put("studentId", tStudent.getId());
            studentMap.put("parentType", parentTypeMap.get(tStudent.getParentType().toString()) == null ? "保密" : parentTypeMap.get(tStudent.getParentType().toString()));
            studentMap.put("courseCount", studentCourseMapper.getStudentCourseCount(tStudent.getId()));
            studentMapList.add(studentMap);
        });
        return studentMapList;
    }

    public List<Map<String, Object>> isEndMyCourses(Long userId, Integer isEnd) {
        List<Map<String,Object>> list = studentCourseMapper.getMyCourses(ImmutableMap.of("userId", userId, "isEnd", isEnd));
        list.stream().forEach(map->{
            if (map.get("subjectType") != null) {
                String subjectType = map.get("subjectType").toString();
                map.put("subjectType", dictionaryService.getSubjectTypeValue(subjectType, "1"));
            } else {
                map.put("subjectType","");
            }
        });
        return list;
    }

    @Override
    @Transactional
    public Map<String, Object> saveMygrowth(List<String> mediaUrls, String mediaType, TDynamic dynamic, Long workId) {
        try {
            TClassWork classWork = new TClassWork().selectById(workId);
            TClassCourse course = new TClassCourse().selectById(classWork.getCourseId());
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
            if (mediaType.equals("IMG")) {
                for (String mediaUrl : mediaUrls) {
                    TDynamicImg dyimg = new TDynamicImg();
                    dyimg.setDynamicId(dynamic.getId());
                    dyimg.setStatus(0);
                    dyimg.setCreateTime(new Date());
                    dyimg.setOriginalImgUrl(mediaUrl);
                    dyimg.insert(); //动态图片保存
                }
            } else {
                String mediaUrl = mediaUrls.get(0);
                TDynamicVideo dyvideo = new TDynamicVideo();
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
    public List<Map<String, Object>> queryStuInfoByUserId(Long userId) {
        return baseMapper.getStudentInfoOfUser(userId);
    }

    @Override
    public Map<String, Object> saveStudent(TStudent student) {
        Map<String, Object> result = Maps.newHashMap();

        if (student.getId()!=null){
            baseMapper.updateById(student);
            result.put("message", "更新成功");
        } else {
            baseMapper.insert(student);
            result.put("message", "保存成功");
        }
        result.put("success", true);

        return result;
    }

    @Override
    public Integer delStudent(Long studentId, Long userId) {
        TStudent student = new TStudent().selectById(studentId);
        student.setStatus(1);
        return baseMapper.updateById(student);
    }

    @Override
    public List<TStudent> searchByName(String name, Long organId) {
        return studentMapper.searchByName(name, organId);
    }

    @Override
    public List<TStudent> getAllByOrganId(Long organId) {
        return studentMapper.getAllByOrganId(organId);
    }


}
