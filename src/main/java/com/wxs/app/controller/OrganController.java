package com.wxs.app.controller;

import com.wxs.service.activity.ITOrganActivityService;
import com.wxs.service.course.ITCourseCategoryService;
import com.wxs.service.organ.ITOrganizationService;
import com.wxs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class OrganController {

    @Autowired
    private ITOrganizationService organizationService;

    @Autowired
    private ITCourseCategoryService courseCategoryService;

    @Autowired
    private ITOrganActivityService organActivityService;

    @RequestMapping(value = "/{organId}")
    public Result organOne(@PathVariable("organId") Long organId){
        //机构基本信息
        Long userId = 0L;
        return Result.of(organizationService.getOrganOutline(organId,userId));
    }

    @RequestMapping(value = "/course/{organId}")
    public Result courseOfOrgan(@PathVariable("organId") Long organId){
        //机构的课程信息
        return Result.of(courseCategoryService.getOrganCourseList(organId));
    }

    @RequestMapping(value = "/activity/{organId}")
    public Result activityOfOrgan(@PathVariable("organId") Long organId){
        //机构的活动信息
        return Result.of(organActivityService.getActivityOfOrgan(organId));
    }

}
