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
 * 前端控制器
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@RestController
@RequestMapping("/app/organ")
public class OrganController extends BaseWxController {

    @RequestMapping(value = "/view/{organId}")
    public Result view(@RequestParam(value = "sessionId", required = true) String sessionId,
                       @PathVariable("organId") Long organId) {
        //机构基本信息
        Long userId = 0L;
        return Result.of(organizationService.getOrganOutline(organId, userId));
    }

    @RequestMapping(value = "/course/{organId}")
    public Result courseOfOrgan(@RequestParam(value = "sessionId", required = true) String sessionId,
                                @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                @PathVariable("organId") Long organId) {
        //机构的课程信息
        return Result.of(courseCategoryService.getCourseListByOrgan(organId, page));
    }

    @RequestMapping(value = "/fllowMe/{organId}")
    public Result fllowMe(@RequestParam(value = "sessionId", required = true) String sessionId,
                          @PathVariable("organId") Long organId) {
        //关注机构的用户列表
        Long userId = 1L;
        return Result.of(organizationService.getOrganFllowUserList(organId, userId));
    }

    @RequestMapping(value = "/activity/{organId}")
    public Result activityOfOrgan(@RequestParam(value = "sessionId", required = true) String sessionId,
                                  @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                  @PathVariable("organId") Long organId) {
        //机构的活动信息
        return Result.of(organActivityService.getActivityOfOrgan(organId, page));
    }

    @RequestMapping(value = "/advice/{adviceId}")
    public Result adviceOfOrgan(@RequestParam(value = "sessionId", required = true) String sessionId,
                                @PathVariable("adviceId") Long adviceId) {
        //机构的通知详情
        TOrganAdvice organAdvice = new TOrganAdvice().selectById(adviceId);
        return Result.of(organAdvice);
    }

    @RequestMapping(value = "/choosePhotos/{organId}")
    public Result choosePhotos(@RequestParam(value = "sessionId", required = false) String sessionId,
                               @PathVariable(value = "organId", required = true) Long organId,
                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "rows", required = false, defaultValue = "6") int rows) {
        return Result.of(organizationService.choicenessPhotos(organId, page, rows));
    }

    @RequestMapping(value = "/newestDynamic/{organId}")
    public Result newestDynamic(@RequestParam(value = "sessionId", required = false) String sessionId,
                                @PathVariable(value = "organId", required = true) Long organId) {
        Long userId = 1L;
        return Result.of(dynamicService.getNewestDynamicByOrganId(userId, organId));
    }


}
