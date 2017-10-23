package com.wxs.app.controller;

import com.wxs.service.course.ITCoursesService;
import com.wxs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wxs.core.controller.BaseController;

/**
 * Created by Administrator on 2017/9/23 0023.
 */
@RestController
@RequestMapping("app/myCourse")
public class MyCourseController extends BaseController {

    @Autowired
    private ITCoursesService coursesService;

    @RequestMapping(value = "course/{coursesId}")
    public Result outline(@PathVariable("coursesId") Long coursesId){
       return Result.of(coursesService.getMyCourseInfo(coursesId));
    }

    @RequestMapping(value = "lessones/{coursesId}")
    public Result lessones(@PathVariable("coursesId") Long coursesId,
                           @RequestParam(value ="studentId", required = false, defaultValue = "") Long studentId){
        return Result.of(coursesService.getLessesonByCourse(coursesId,studentId));
    }


}
