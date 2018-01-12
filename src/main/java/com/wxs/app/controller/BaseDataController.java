package com.wxs.app.controller;

import com.wxs.service.common.IDictionaryService;
import com.wxs.service.customer.ITStudentService;
import com.wxs.service.organ.ITOrganizationService;
import com.wxs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.wxs.core.util.BaseUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/18 0018.
 * 基础数据
 */
@RestController
@RequestMapping("app/data")
public class BaseDataController extends BaseWxController {
    @Autowired
    public IDictionaryService dictionaryService;
    @Autowired
    public ITOrganizationService organizationService;
    @Autowired
    public ITStudentService studentService;

    @RequestMapping(value = "/subjectTypes")
    public Result querySubjectTypeDicts() {
        return Result.of(dictionaryService.querySubjectTypeDicts());
    }

    /**
     * 根据名称模糊查询机构
     *
     * @return
     */
    @RequestMapping(value = "/organ")
    public Result queryOrganByName(@RequestParam(value = "organName", required = true) String organName) {
        return Result.of(organizationService.queryOrganByLikeName(organName));
    }

    @RequestMapping(value = "/remindMediaType")
    public Result remindMediaType() {
        return Result.of(dictionaryService.getRemindMediaType());
    }

    @RequestMapping(value = "/getDataAuthority")
    public Result getDataAuthority() {
        return Result.of(BaseUtil.getMap2List(dictionaryService.getDataAuthority()));
    }

    @RequestMapping(value = "/studentInfoByUserId")
    public Result studentInfoByUserId(@RequestParam(value = "sessionId", required = true) String sessionId) {
        Long userId = 1L;
        return Result.of(studentService.queryStuInfoByUserId(userId));
    }

    @RequestMapping(value = "/uploadImage")
    public Result uploadImage( @RequestParam MultipartFile imageOrVideo){
        String mediaUrl = getImageOrVideoUrls(imageOrVideo);
        return Result.of(mediaUrl);
    }

}
