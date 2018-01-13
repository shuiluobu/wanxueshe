package com.wxs.service.task.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.wxs.entity.comment.TDynamic;
import com.wxs.entity.comment.TDynamicImg;
import com.wxs.entity.comment.TDynamicVideo;
import com.wxs.entity.course.TClassCourse;
import com.wxs.entity.customer.TStudent;
import com.wxs.entity.customer.TTeacher;
import com.wxs.entity.organ.TOrganization;
import com.wxs.entity.task.TClassWork;
import com.wxs.entity.task.TStudentWork;
import com.wxs.enu.EnuDynamicTypeCode;
import com.wxs.enu.EnumClassworkCompletion;
import com.wxs.mapper.task.TClassWorkMapper;
import com.wxs.service.dynamic.ITDynamicService;
import com.wxs.service.common.IDictionaryService;
import com.wxs.service.task.ITClassWorkService;
import org.apache.commons.lang.StringUtils;
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
 * @since 2017-11-24
 */
@Service
public class TClassWorkServiceImpl extends ServiceImpl<TClassWorkMapper, TClassWork> implements ITClassWorkService {
    @Autowired
    private TClassWorkMapper classWorkMapper;
    @Autowired
    private ITDynamicService dynamicService;
    @Autowired
    public IDictionaryService dictionaryService;

    /**
     * 获取用户作业的基本信息，主要是我的功能里的《我的作业》用
     *
     * @param userId
     * @return
     */
    @Override
    public List<Map<String, Object>> getClassWorkInfosByUserId(Long userId) {
        List<Map<String, Object>> list = classWorkMapper.getMyClassWorkInfo(userId);
        Map<String, String> completionMap = dictionaryService.getWorkcompletionStatus();
        list.stream().forEach(map -> {
            String completion = map.get("completion") == null ? "NOT_HAND_IN" : map.get("completion").toString();
            map.put("completion", completionMap.get(completion) == null ? "未提交" : completionMap.get(completion));
        });
        return list;
    }

    public Map<String, Object> getClassWorkOutline(Long sWorkId, Long userId) {
        TStudentWork studentWork = new TStudentWork().selectById(sWorkId);
        Long workId = studentWork.getWorkId();
        TStudent student = new TStudent().selectById(studentWork.getStudentId());
        Map<String, Object> classTask = classWorkMapper.getClassWork(workId);
        classTask.put("sWorkId", sWorkId); //自己学生作业对应的主键ID
        Long teacherId = Long.parseLong(classTask.get("teacherId").toString());
        classTask.put("teacherName", new TTeacher().selectById(teacherId).getTeacherName());
        Long organId = Long.parseLong(classTask.get("organ").toString());
        Long dynamicId = Long.parseLong(classTask.get("dynamicId").toString());
        classTask.put("workContent", dynamicService.queryDynamicOfWork(dynamicId));
        classTask.put("studentName", student.getRealName());
        classTask.put("studentId", student.getId());
        TOrganization organ = new TOrganization().selectById(organId); //todo 从缓存中获取
        classTask.put("organ", ImmutableMap.of("organId", organ.getId(), "organName", organ.getOrganName(), "leval", organ.getLeval() == 1 ? "已认证" : ""));
        TClassCourse course = new TClassCourse().selectById(Long.parseLong(classTask.get("courseId").toString()));
        classTask.put("courseName", course.getCourseName());
        classTask.put("completion", studentWork.getCompletion() == null ? "NOT_HAND_IN" : studentWork.getCompletion());//完成情况
        return classTask;
    }
//    @Override
//    public List<Map<String,Object>> getMyClassWorks(Long userId){
//        List<Map<String,Object>> resultList = Lists.newArrayList();
//        EntityWrapper wrapper = new EntityWrapper();
//        wrapper.eq("userId",userId);
//        wrapper.orderBy("createTime",false);
//        List<TStudentWork> studentWorks = studentWorkMapper.selectList(wrapper);
//        for(TStudentWork studentWork : studentWorks) {
//            resultList.add(getClassWorkOutline(studentWork.getWorkId()));
//        }
//        return resultList;
//    }

    @Override
    @Transactional
    public Map<String, Object> saveStudentWork(List<String> mediaUrls, String mediaType, TDynamic dynamic, Long sWorkId) {
        Map<String, Object> result = Maps.newHashMap();
        try {
            TStudentWork studentWork = new TStudentWork().selectById(sWorkId);
            TClassWork classWork = new TClassWork().selectById(studentWork.getWorkId());
            TClassCourse course = new TClassCourse().selectById(classWork.getCourseId());
            //提交作业的内容，是一种动态
            dynamic.setClassLessonId(classWork.getLessonId()); //课节
            dynamic.setStudentId(studentWork.getStudentId());
            dynamic.setOrganId(course.getOrganizationId()); //机构
            dynamic.setCourseId(classWork.getCourseId()); //课程
            dynamic.setClassLessonId(classWork.getLessonId());
            dynamic.setDynamicType(EnuDynamicTypeCode.DYNAMIC_TYPE_MYWORK.getTypeCode());//类型为作业
            dynamic.setStatus(0);
            dynamic.insert(); //保存动态
            // 更新作业
            studentWork.setDynamicId(dynamic.getId());
            studentWork.setStudentId(dynamic.getStudentId());
            studentWork.setCreateTime(new Date());
            studentWork.setCompletion(EnumClassworkCompletion.SUBMITTED.getTypeCode()); //已提交作业
            studentWork.updateById();//保存作业
            if (mediaUrls != null && mediaUrls.size() > 0) {
                if (mediaType.equals("IMG")) {
                    for (String mediaUrl : mediaUrls) {
                        TDynamicImg dyimg = new TDynamicImg();
                        dyimg.setDynamicId(dynamic.getId());
                        dyimg.setStatus(0);
                        dyimg.setCreateTime(new Date());
                        dyimg.setOriginalImgUrl(mediaUrl);
                        dyimg.setThumbImgUrl(mediaUrl);
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
            }
            result.put("success", true);
            result.put("dynamicId", dynamic.getId());
            result.put("workId", classWork.getId());
            result.put("sworkId", studentWork.getId());
            System.out.println("返回结果:" + BaseUtil.toJson(result));
            return result;
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            result.put("dynamicId", 0);
            e.printStackTrace();
        }
        return result;
    }


}
