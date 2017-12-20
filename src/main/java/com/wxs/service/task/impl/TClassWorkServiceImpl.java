package com.wxs.service.task.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.ImmutableMap;
import com.wxs.entity.comment.TDyimg;
import com.wxs.entity.comment.TDynamicmsg;
import com.wxs.entity.comment.TDyvideo;
import com.wxs.entity.course.TClass;
import com.wxs.entity.course.TCourse;
import com.wxs.entity.customer.TTeacher;
import com.wxs.entity.organ.TOrganization;
import com.wxs.entity.task.TClassWork;
import com.wxs.entity.task.TStudentWork;
import com.wxs.enu.EnuDynamicTypeCode;
import com.wxs.mapper.task.TClassWorkMapper;
import com.wxs.service.comment.ITDynamicmsgService;
import com.wxs.service.task.ITClassWorkService;
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
    private ITDynamicmsgService dynamicmsgService;

    public Map<String, Object> getClassWorkOutline(Long taskId) {
        Map<String, Object> classTask = classWorkMapper.getClassWork(taskId);
        Long teacherId = Long.parseLong(classTask.get("teacherId").toString());
        classTask.put("teacherName",new TTeacher().selectById(teacherId).getTeacherName());
        Long organId = Long.parseLong(classTask.get("organ").toString());
        TOrganization organ = new TOrganization().selectById(organId); //todo 从缓存中获取
        classTask.put("organ", ImmutableMap.of("organId",organ.getId(),"organName",organ.getOrganName(),"leval",organ.getLeval()));
        TCourse course = new TCourse().selectById(Long.parseLong(classTask.get("courseId").toString()));
        classTask.put("courseName",course.getCourseName());

        return classTask;
    }

    @Override
    @Transactional
    public Map<String, Object> saveStudentWork(List<String> mediaUrls,String mediaType, TDynamicmsg dynamic, Long workId) {
        try {
            TClassWork classWork = new TClassWork().selectById(workId);
            TCourse course = new TCourse().selectById(classWork.getCourseId());
            dynamic.setClassLessonId(classWork.getLessonId()); //课节
            dynamic.setOrganId(course.getOrganizationId()); //机构
            dynamic.setCourseId(classWork.getCourseId()); //课程
            dynamic.setClassLessonId(classWork.getLessonId());
            dynamic.setDynamicType(EnuDynamicTypeCode.DYNAMIC_TYPE_MYWORK.getTypeCode());//类型为作业
            dynamic.setStatus(0);
            dynamic.insert(); //保存动态
            TStudentWork studentWork = new TStudentWork();
            studentWork.setWorkId(workId);
            studentWork.setDynamicId(dynamic.getId());
            studentWork.setStudentId(dynamic.getStudentId());
            studentWork.setCreateTime(new Date());
            studentWork.insert();//保存作业
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


}
