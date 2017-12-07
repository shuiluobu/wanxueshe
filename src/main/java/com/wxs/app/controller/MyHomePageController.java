package com.wxs.app.controller;

import com.wxs.service.comment.ITDynamicmsgService;
import com.wxs.service.customer.ITFrontUserService;
import com.wxs.service.customer.ITParentService;
import com.wxs.service.customer.impl.TStudentServiceImpl;
import com.wxs.service.message.ITRemindMessageService;
import com.wxs.util.Result;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/10/24 0024.
 * 个人首页
 */
@RestController
@RequestMapping("app/homePage")
public class MyHomePageController {
    @Autowired
    private TStudentServiceImpl studentService;
    @Autowired
    private ITParentService parentService;
    @Autowired
    private ITRemindMessageService remindMessageService;
    @Autowired
    private ITFrontUserService frontUserService;
    @Autowired
    private ITDynamicmsgService dynamicmsgService;

    @RequestMapping(value = "/follow/{sessionId}")
    public Result follow(String sessionId) {
        Long userId = 1L; //之后需要从session中获取
        return Result.of(studentService.getMyFollow(userId));
    }

    @RequestMapping(value = "/myCourse/{sessionId}")
    public Result myCourse(String sessionId) {
        //我的课程
        Long userId = 1L; //之后需要从session中获取
        return Result.of(studentService.getMyCourses(userId));
    }

    @RequestMapping(value = "/myRemind/{sessionId}")
    public Result myRemind(String sessionId) {
        //我的提醒
        Long userId = 1L; //之后需要从session中获取
        return Result.of(remindMessageService.getRemindMsgByFromUid(userId));
    }

    @RequestMapping(value = "/myFriend/{sessionId}")
    public Result myFriend(String sessionId) {
        //我的好友列表
        Long userId = 1L; //之后需要从session中获取
        return Result.of(frontUserService.getUserFriends(userId));
    }

    @RequestMapping(value = "/myDynamic")
    public Result myDynamic(@RequestParam String sessionId,@RequestParam Long studentId) {
        //我的动态记录
        Long userId = 1L; //之后需要从session中获取
        return Result.of(dynamicmsgService.getDynamicmListByMySelfId(userId,studentId));
    }

    @RequestMapping(value = "/myStudents/{sessionId}")
    public Result myStudents(String sessionId) {
        //我的学员
        Long parentId = 0L;
        return Result.of(parentService.getStudentByParent(parentId));
    }


}
