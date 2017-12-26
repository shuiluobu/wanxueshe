package com.wxs.app.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wxs.entity.customer.TStudent;
import com.wxs.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 个人首页
 * Created by Administrator on 2017/12/22 0022.
 */
@RestController
@RequestMapping("app/person")
public class PersonPageController extends BaseWxController {
    @RequestMapping(value = "/view")
    public Result view(@RequestParam(value = "sessionId", required = true) String sessionId,
                       @RequestParam(value = "userId", required = false) Long userId) {
        Long loginUserId = 1L;
        return Result.of(parentService.getParentOutline(userId, loginUserId));
    }

    @RequestMapping(value = "/follow")
    public Result follow(@RequestParam(value = "sessionId", required = true) String sessionId,
                         @RequestParam(value = "userId", required = false) Long userId) {
        return Result.of(parentService.getMyFollow(userId));
    }

    @RequestMapping(value = "/course")
    public Result myCourse(@RequestParam(value = "sessionId", required = true) String sessionId,
                           @RequestParam(value = "userId", required = true) Long userId,
                           @RequestParam(value = "isAll", required = false,defaultValue = "0") Integer isAll) {
        //我的课程
        userId = 1L; //之后需要从session中获取
        return Result.of(studentService.getMyCourses(userId,isAll));
    }

    @RequestMapping(value = "/dynamic")
    public Result dynamic(@RequestParam(value = "sessionId", required = true) String sessionId,
                          @RequestParam(value = "userId", required = true) Long userId
    ) {
        Long loginUserId = 1L;
        return Result.of(dynamicmsgService.getOtherParentUserDynamicmList(loginUserId, userId));
    }
}
