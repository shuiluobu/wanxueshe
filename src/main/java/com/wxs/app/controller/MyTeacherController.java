package com.wxs.app.controller;

import com.wxs.entity.comment.TDynamicmsg;
import com.wxs.entity.customer.TTeacher;
import com.wxs.entity.organ.TOrganization;
import com.wxs.service.comment.ITDynamicmsgService;
import com.wxs.service.course.ITCourseCategoryService;
import com.wxs.service.customer.ITTeacherService;
import com.wxs.service.organ.ITOrganizationService;
import com.wxs.service.task.ITClassTaskService;
import com.wxs.service.task.ITStudentTaskService;
import com.wxs.util.Result;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/11/22 0022.
 */
@RestController
@RequestMapping("/app/teacher")
public class MyTeacherController {
    @Autowired
    private ITTeacherService teacherService;
    @Autowired
    private ITOrganizationService organizationService;
    @Autowired
    private ITDynamicmsgService dynamicmsgService;
    @Autowired
    private ITCourseCategoryService courseCategoryService;
    @Autowired
    private ITClassTaskService classTaskService;

    /**
     * 根据Id获取教师信息
     *
     * @param teacherId
     * @return
     */
    @RequestMapping(value = "/view/{teacherId}")
    public Result view(@PathVariable("teacherId") Long teacherId) {
        //教师基本信息
        TTeacher teacher = teacherService.selectById(teacherId);
        TOrganization organ = organizationService.selectById(teacher.getOrganizationId());
        teacher.setOrganization(organ);
        return Result.of(teacher);
    }

    @RequestMapping(value = "dynamicList/{teacherId}")
    public Result dynamicList(@PathVariable("teacherId") Long teacherId) {
        //教师动态基本信息
        TTeacher teacher = teacherService.selectById(teacherId);
        return Result.of(dynamicmsgService.getDynamicmsgListByUserId(teacher.getUserId()));
    }

    @RequestMapping(value = "courseList/{teacherId}")
    public Result courseList(@PathVariable("teacherId") Long teacherId) {
        //根据教师查询，教师所教的课程列表
        TTeacher teacher = teacherService.selectById(teacherId);
        return Result.of(courseCategoryService.getTeacherCourseList(teacher.getUserId()));
    }

    @RequestMapping(value = "classTask/{taskId}")
    public Result classTask(@PathVariable("taskId") Long taskId) {
        //根据taskId获取作业详情
        return Result.of(classTaskService.getClassTaskMap(taskId));
    }
}
