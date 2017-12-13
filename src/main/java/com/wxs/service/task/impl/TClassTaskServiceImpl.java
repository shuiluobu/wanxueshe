package com.wxs.service.task.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.ImmutableMap;
import com.wxs.entity.comment.TDyimg;
import com.wxs.entity.comment.TDynamicmsg;
import com.wxs.entity.course.TClass;
import com.wxs.entity.course.TCourse;
import com.wxs.entity.customer.TTeacher;
import com.wxs.entity.organ.TOrganization;
import com.wxs.entity.task.TClassTask;
import com.wxs.entity.task.TStudentTask;
import com.wxs.mapper.task.TClassTaskMapper;
import com.wxs.service.comment.ITDynamicmsgService;
import com.wxs.service.task.ITClassTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSuspensionNotSupportedException;
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
public class TClassTaskServiceImpl extends ServiceImpl<TClassTaskMapper, TClassTask> implements ITClassTaskService {
    @Autowired
    private TClassTaskMapper classTaskMapper;
    @Autowired
    private ITDynamicmsgService dynamicmsgService;

    public Map<String, Object> getClassTaskOutline(Long taskId) {
        Map<String, Object> classTask = classTaskMapper.getClassTask(taskId);
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
    public Map<String, Object> saveStudentWork(List<TDyimg> dyimgs, TDynamicmsg dynamic, Long workId) {
        try {
            TClassTask classTask = new TClassTask().selectById(workId);
            TClass tClass = new TClass().selectById(classTask.getClassId());
            dynamic.setClassId(classTask.getClassId()); //班级
            dynamic.setClassLessonId(classTask.getLeessonId()); //课节
            dynamic.setOrganId(tClass.getOrganId()); //机构
            dynamic.setCourseId(tClass.getCourseId()); //课程
            dynamic.setDynamicType("ZUOYE");//类型为作业
            dynamic.setStatus(0);
            dynamic.insert(); //保存动态
            TStudentTask studentTask = new TStudentTask();
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


}
