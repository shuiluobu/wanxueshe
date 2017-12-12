package com.wxs.app.controller;

import com.wxs.entity.message.TOrganAdvice;
import com.wxs.service.activity.ITOrganActivityService;
import com.wxs.service.course.ITCourseCategoryService;
import com.wxs.service.organ.ITOrganizationService;
import com.wxs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@RestController
@RequestMapping("/app/organ")
public class OrganController extends BaseWxController{

    @RequestMapping(value = "/view/{organId}")
    public Result view(@RequestParam(value = "sessionId" ,required = true) String sessionId,
                       @PathVariable("organId") Long organId){
        //机构基本信息
        Long userId = 0L;
        return Result.of(organizationService.getOrganOutline(organId,userId));
    }

    @RequestMapping(value = "/course/{organId}")
    public Result courseOfOrgan(@RequestParam(value = "sessionId" ,required = true) String sessionId,
                                @PathVariable("organId") Long organId){
        //机构的课程信息
        return Result.of(courseCategoryService.getCourseListByOrgan(organId));
    }

    @RequestMapping(value = "/fllowMe/{organId}")
    public Result fllowMe(@RequestParam(value = "sessionId" ,required = true) String sessionId,
                          @PathVariable("organId") Long organId){
        //关注机构的用户列表
        return Result.of(organizationService.getOrganFllowUserList(organId));
    }

    @RequestMapping(value = "/activity/{organId}")
    public Result activityOfOrgan(@RequestParam(value = "sessionId" ,required = true) String sessionId,
                                  @PathVariable("organId") Long organId){
        //机构的活动信息
        return Result.of(organActivityService.getActivityOfOrgan(organId));
    }

    @RequestMapping(value = "/advice/{adviceId}")
    public Result adviceOfOrgan(@RequestParam(value = "sessionId" ,required = true) String sessionId,
                                @PathVariable("adviceId") Long adviceId){
        //机构的通知详情
        TOrganAdvice organAdvice = new TOrganAdvice().selectById(adviceId);
        return Result.of(organAdvice);
    }



}
