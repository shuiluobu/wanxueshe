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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/11/22 0022.
 */
@RestController
@RequestMapping("/app/teacher")
public class MyTeacherController extends BaseWxController{


    /**
     * 根据Id获取教师信息
     *
     * @param teacherId
     * @return
     */
    @RequestMapping(value = "/view/{teacherId}")
    public Result view(@RequestParam(value = "sessionId" ,required = true) String sessionId,
                       @PathVariable("teacherId") Long teacherId) {
        //教师基本信息
        Long userId = 1L;
        return Result.of(teacherService.getTeacherOutline(teacherId,userId));
    }

    @RequestMapping(value = "dynamicList/{teacherId}")
    public Result dynamicList(@RequestParam(value = "sessionId" ,required = true) String sessionId,
                              @PathVariable("teacherId") Long teacherId) {
        //教师动态基本信息
        TTeacher teacher = teacherService.selectById(teacherId);
        Long userId = 0L;
        return Result.of(dynamicmsgService.getDynamicmListByTeacherId(userId,teacherId));
    }

    @RequestMapping(value = "courseList/{teacherId}")
    public Result courseList(@RequestParam(value = "sessionId" ,required = true) String sessionId,
                             @PathVariable("teacherId") Long teacherId) {
        //根据教师查询，教师所教的课程列表
        TTeacher teacher = teacherService.selectById(teacherId);
        return Result.of(courseCategoryService.getTeacherCourseList(teacher.getUserId()));
    }

    @RequestMapping(value = "classTask/{taskId}")
    public Result classTask(@RequestParam(value = "sessionId" ,required = true) String sessionId,
                            @PathVariable("taskId") Long taskId) {
        //根据taskId获取作业详情
        return Result.of(classTaskService.getClassTaskMap(taskId));
    }

    @RequestMapping(value = "/fllowMe/{teacherId}")
    public Result fllowMe(@RequestParam(value = "sessionId" ,required = true) String sessionId,
                          @PathVariable("teacherId") Long teacherId){
        //关注机构的用户列表
        Long userId =1L;
        return Result.of(teacherService.getOrganFllowUserList(teacherId,userId));
    }
}
