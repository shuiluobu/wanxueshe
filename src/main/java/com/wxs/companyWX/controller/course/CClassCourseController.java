package com.wxs.companyWX.controller.course;

import com.wxs.service.course.ITClassCoursesService;
import com.wxs.util.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxs.core.util.BaseUtil;

/**
 * Created by Administrator on 2018/1/12.
 */
@RestController
@RequestMapping("/cClassCourse")
public class CClassCourseController {
    private static Logger log = Logger.getLogger(CClassCourseController.class);
    @Autowired
    private ITClassCoursesService classCoursesService;

    @RequestMapping("/stuCourseDoneInfo")
    public Result stuCourseDoneInfo(Long courseId){

        try{
            return Result.of(classCoursesService.stuCourseDoneInfo(courseId));
        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return null;
    }
}
