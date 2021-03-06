package com.wxs.app.controller;

import com.google.common.collect.Maps;
import com.wxs.entity.customer.TTeacher;
import com.wxs.entity.customer.TWxUser;
import com.wxs.service.course.ITClassLessonService;
import com.wxs.service.course.impl.TClassLessonServiceImpl;
import com.wxs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/23 0023.
 * 课时管理
 */
@RestController
@RequestMapping("app/clession")
public class MyLessionController extends BaseWxController{

    @RequestMapping(value = "/view/{lessionId}")
    public Result view(@RequestParam(value = "sessionId" ,required = true) String sessionId,
                       @PathVariable("lessionId") Long lessionId) {
        //展示课时详情
        Long userId = 0L;
        return Result.of(classLessonService.getOneClassLession(lessionId,userId));
    }

    @RequestMapping(value = "/mySchedule")
    public Result mySchedule(@RequestParam(value = "sessionId" ,required = true) String sessionId){
        TWxUser wxUser = wxService.session2User(sessionId);
        Long userId = 1L; //我的日程
        Map<String,Object> result = Maps.newHashMap();
        result.put("toDay",classLessonService.getTodayCourseLesson(userId));
        result.put("nextDay",classLessonService.getNextDayCourseLesson(userId));
        return Result.of(result);
    }

    @RequestMapping(value = "/nextDayCourse")
    public Result nextDayCourse(@RequestParam(value = "sessionId" ,required = true) String sessionId){
        Long userId = 0L; //接下来一周的课程
        return Result.of(classLessonService.getNextDayCourseLesson(userId));
    }

    @RequestMapping(value = "/viewCourseByTime")
    public Result viewCourseByTime(@RequestParam(value = "sessionId" ,required = true) String sessionId,
                                   @RequestParam("oneDay") String oneDay){
        Long userId = 0L; //按日期显示课程
        return Result.of(classLessonService.getCourseByTime(oneDay,userId));
    }
}
