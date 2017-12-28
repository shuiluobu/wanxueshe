package com.wxs.app.controller;

import com.google.common.collect.Lists;
import com.wxs.entity.course.TCourseCategory;
import com.wxs.mapper.organ.TOrganizationMapper;
import com.wxs.service.course.ITCourseCategoryService;
import com.wxs.service.organ.ITOrganizationService;
import com.wxs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/27 0027.
 * 我的发现
 */
@RestController
@RequestMapping("app/myDiscovery")
public class MyDiscoveryController extends BaseWxController {

    @RequestMapping(value = "/view")
    public Result view(@RequestParam(value = "sessionId", required = true) String sessionId) {
        List<Object> list = Lists.newArrayList();
        return Result.of(list);
    }

    @RequestMapping(value = "/nearInformation")
    public Result nearInformation(@RequestParam(value = "sessionId", required = true) String sessionId,
                                  @RequestParam double latitude, @RequestParam double longitude) {
        //根据经纬度查找附近的课程
        List<TCourseCategory> list = courseCategoryService.getNearByCategorys(latitude, longitude);
        return Result.of(list);
    }

    @RequestMapping(value = "/nearOrgan")
    public Result nearOrgan(@RequestParam(value = "sessionId", required = true) String sessionId,
                            @RequestParam double latitude, @RequestParam double longitude) {
        //根据经纬度查找附近机构
        return Result.of(organizationService.getNearOrgans(latitude, longitude));
    }

    @RequestMapping(value = "/nearDynamics")
    public Result nearDynamics(@RequestParam(value = "sessionId", required = true) String sessionId,
                               @RequestParam double latitude, @RequestParam double longitude) {
        //根据经纬度查找附近机构
        Long loginUserId = 1L;
        return Result.of(dynamicService.getNearByDynamicms(loginUserId, latitude, longitude));
    }
    @RequestMapping(value = "/findCourse")
    public Result nearOrgan(@RequestParam(value = "sessionId", required = true) String sessionId,
                            @RequestParam(required = false, value = "subjectType", defaultValue = "") String subjectType,
                            @RequestParam(required = false, value = "searchName", defaultValue = "") String searchName) {
        //根据经纬度查找附近机构
        return Result.of(courseCategoryService.searchCourseListForDiscovery(subjectType, searchName));
    }


}
