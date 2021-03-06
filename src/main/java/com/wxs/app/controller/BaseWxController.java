package com.wxs.app.controller;

import com.wxs.app.service.WxService;
import com.wxs.cache.ICache;
import com.wxs.service.activity.ITOrganActivityService;
import com.wxs.service.dynamic.ITDynamicService;
import com.wxs.service.common.IDictionaryService;
import com.wxs.service.common.ISequenceService;
import com.wxs.service.course.ITClassLessonService;
import com.wxs.service.course.ITCourseCategoryService;
import com.wxs.service.course.ITClassCoursesService;
import com.wxs.service.customer.*;
import com.wxs.service.customer.impl.TStudentServiceImpl;
import com.wxs.service.message.ITRemindMessageService;
import com.wxs.service.organ.ITOrganizationService;
import com.wxs.service.task.ITClassWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.wxs.core.util.BaseUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/12 0012.
 */
public class BaseWxController {
    @Autowired
    public WxService wxService;
    @Autowired
    public ITFrontUserService frontUserService;
    @Autowired
    public ICache cache;
    @Autowired
    public ITClassCoursesService coursesService;
    @Autowired
    public TStudentServiceImpl studentService;
    @Autowired
    public ITParentService parentService;
    @Autowired
    public ITRemindMessageService remindMessageService;
    @Autowired
    public ITClassLessonService classLessonService;
    @Autowired
    public ITTeacherService teacherService;
    @Autowired
    public ITDynamicService dynamicService;
    @Autowired
    public ITCourseCategoryService courseCategoryService;
    @Autowired
    public ITClassWorkService classWorkService;
    @Autowired
    public ITOrganizationService organizationService;
    @Autowired
    public ITOrganActivityService organActivityService;
    @Autowired
    public ITFollowUserService followUserService;
    @Autowired
    public IDictionaryService dictionaryService;
    @Autowired
    public ISequenceService sequenceService;

    @Value("${web.upload-path}")
    public String imgUploadPath;

    public static Long userId = 1L;
    public static Long coursesId = 1L;
    public static Long lessionId = 1L;
    public static Long organId = 1L;
    public static Long teacherId = 1L;

    public String getImageOrVideoUrls(MultipartFile imageOrVideo) {
        String newFileName = null;
        String targetFolderUrl = null;
        String newFileNameUrl = null;
        try {
            // for (MultipartFile imageOrVideo : imageOrVideos) {
            String originalFilename = imageOrVideo.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            newFileName = BaseUtil.uuid() + "." + suffix; //uuid +后缀
            targetFolderUrl = "dynamic" + "/" + BaseUtil.toShortDate(new Date()) + "/";
            newFileNameUrl = imgUploadPath + targetFolderUrl  + newFileName;
            File targetFolder = new File(imgUploadPath +targetFolderUrl); //新图片路径
            if (!targetFolder.exists() || !targetFolder.isDirectory()) {
                targetFolder.mkdirs();
            }
            imageOrVideo.transferTo(new File(newFileNameUrl));  //内存数据读入磁盘
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetFolderUrl  + newFileName;
    }
}
