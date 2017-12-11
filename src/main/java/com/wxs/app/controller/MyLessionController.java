package com.wxs.app.controller;

import com.wxs.entity.customer.TTeacher;
import com.wxs.service.course.ITClassLessonService;
import com.wxs.service.course.impl.TClassLessonServiceImpl;
import com.wxs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/11/23 0023.
 * 课时管理
 */
@RestController
@RequestMapping("clession")
public class MyLessionController {
    @Autowired
    private ITClassLessonService classLessonService;

    @RequestMapping(value = "/view/{lessionId}")
    public Result view(@PathVariable("lessionId") Long lessionId) {
        //展示课时详情
        Long userId = 0L;
        return Result.of(classLessonService.getOneClassLession(lessionId,userId));
    }

    @RequestMapping(value = "/toDayCourse")
    public Result toDayCourse(){
        Long userId = 0L;//今日课程
        return Result.of(classLessonService.getTodayCourseLesson(userId));
    }

    @RequestMapping(value = "/nextDayCourse")
    public Result nextDayCourse(){
        Long userId = 0L; //接下来一周的课程
        return Result.of(classLessonService.getNextDayCourseLesson(userId));
    }

    @RequestMapping(value = "/viewCourseByTime")
    public Result viewCourseByTime(@RequestParam("oneDay") String oneDay){
        Long userId = 0L; //按日期显示课程
        return Result.of(classLessonService.getCourseByTime(oneDay,userId));
    }
}
