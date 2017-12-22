package com.wxs.app.controller;

import com.wxs.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 个人首页
 * Created by Administrator on 2017/12/22 0022.
 */
@RestController
@RequestMapping("app/person")
public class PersonPageController extends BaseWxController{

    @RequestMapping(value = "/follow")
    public Result follow(@RequestParam(value = "sessionId", required = true) String sessionId,
                         @RequestParam(value = "userId",required = true) Long userId) {
        return Result.of(parentService.getMyFollow(userId));
    }

    @RequestMapping(value = "/course")
    public Result myCourse(@RequestParam(value = "sessionId", required = true) String sessionId,
                           @RequestParam(value = "userId",required = true) Long userId) {
        //我的课程
        userId = 1L; //之后需要从session中获取
        return Result.of(studentService.getMyCourses(userId));
    }

    @RequestMapping(value = "/dynamic")
    public Result dynamic(@RequestParam(value = "sessionId", required = true) String sessionId, @RequestParam Long studentId) {
        //我的动态记录
        Long userId = 1L; //之后需要从session中获取
        return Result.of(dynamicmsgService.getStudentDynamicmList(studentId));
    }
}
