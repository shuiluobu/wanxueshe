package com.wxs.app.controller;

import com.wxs.service.customer.impl.TStudentServiceImpl;
import com.wxs.util.Result;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping(value = "/follow")
    public Result follow() {
        Long userId = 1L; //之后需要从session中获取
        return Result.of(studentService.getMyFollow(userId));
    }

    @RequestMapping(value = "/myCourse")
    public Result myCourse() {
        //我的课程
        Long userId = 1L; //之后需要从session中获取
        return Result.of(studentService.getMyCourse(userId));
    }

    @RequestMapping(value = "/myRemind")
    public Result myRemind() {
        //我的提醒
        return  null;
    }


}
