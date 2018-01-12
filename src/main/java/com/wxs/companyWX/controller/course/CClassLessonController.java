package com.wxs.companyWX.controller.course;

import com.wxs.service.course.ITClassLessonService;
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
@RequestMapping("cClassLesson")
public class CClassLessonController {
    private static Logger log = Logger.getLogger(CClassLessonController.class);
    @Autowired
    private ITClassLessonService classLessonService;
    /**
     * @Description : //获取某课程的所有课时以及课时完成情况
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 16:45 2018/1/12
     * @Params : [courseId]
     **/
    @RequestMapping("/allByCourseId")
    public Result allByCourseId(Long courseId){

        try{
            return Result.of(classLessonService.allByCourseId(courseId));
        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return null;
    }
}
