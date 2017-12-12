package com.wxs.app.controller;

import com.wxs.entity.course.TCourse;
import com.wxs.entity.customer.TTeacher;
import com.wxs.entity.customer.TWxUser;
import com.wxs.service.comment.ITDynamicmsgService;
import com.wxs.service.course.ITCoursesService;
import com.wxs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wxs.core.controller.BaseController;

import java.util.Map;

/**
 * Created by Administrator on 2017/9/23 0023.
 */
@RestController
@RequestMapping("app/myCourse")
public class MyCourseController extends BaseWxController {

    @RequestMapping(value = "/course/{coursesId}")
    public Result outline(@RequestParam(value = "sessionId", required = true) String sessionId,
                          @PathVariable("coursesId") Long coursesId) {
        TWxUser wxUser = wxService.session2User(sessionId);
        return Result.of(coursesService.getCourseOutlineInfo(coursesId, wxUser.getUserId()));
    }

    @RequestMapping(value = "/lessones/{coursesId}")
    public Result lessones(@RequestParam(value = "sessionId", required = true) String sessionId,
                           @PathVariable("coursesId") Long coursesId,
                           @RequestParam(value = "studentId", required = false, defaultValue = "") Long studentId) {
        Long userId = 1L;
        return Result.of(coursesService.getLessesonByCourse(coursesId, userId));
    }

    @RequestMapping(value = "/dynamicList/{coursesId}")
    public Result dynamicList(@RequestParam(value = "sessionId", required = true) String sessionId,
                              @PathVariable("coursesId") Long coursesId) {
        //课程动态基本信息
        Long userId = 0L;
        return Result.of(dynamicmsgService.getDynamicmListByCourseId(userId, coursesId));
    }

    @RequestMapping(value = "/fllowMe/{courseId}")
    public Result fllowMe(@RequestParam(value = "sessionId", required = true) String sessionId,
                          @PathVariable("courseId") Long courseId) {
        //关注课程的用户列表
        return Result.of(coursesService.getCourseFllowUserList(courseId));
    }

    @RequestMapping(value = "editMyCourse")
    public Result editMyCourse(@RequestParam(value = "sessionId", required = true) String sessionId,
                               @RequestParam Map<String, Object> param) {
        //返回是否保存成功
        return Result.of();
    }

    @RequestMapping(value = "addMyCourse")
    public Result addMyCourse(@RequestParam(value = "sessionId", required = true) String sessionId,
                              @ModelAttribute TCourse course) {
        return null;
    }


}
