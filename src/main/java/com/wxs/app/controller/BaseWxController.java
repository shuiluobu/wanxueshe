package com.wxs.app.controller;

import com.wxs.app.service.WxService;
import com.wxs.cache.ICache;
import com.wxs.service.activity.ITOrganActivityService;
import com.wxs.service.comment.ITDynamicmsgService;
import com.wxs.service.common.IDictionaryService;
import com.wxs.service.common.ISequenceService;
import com.wxs.service.course.ITClassLessonService;
import com.wxs.service.course.ITCourseCategoryService;
import com.wxs.service.course.ITCoursesService;
import com.wxs.service.customer.ITFrontUserService;
import com.wxs.service.customer.ITParentService;
import com.wxs.service.customer.ITTeacherService;
import com.wxs.service.customer.impl.TStudentServiceImpl;
import com.wxs.service.message.ITRemindMessageService;
import com.wxs.service.organ.ITOrganizationService;
import com.wxs.service.task.ITClassWorkService;
import com.wxs.service.task.ITStudentWorkService;
import org.springframework.beans.factory.annotation.Autowired;

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
    public ITStudentWorkService studentTaskService;
    @Autowired
    public ITCoursesService coursesService;
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
    public ITDynamicmsgService dynamicmsgService;
    @Autowired
    public ITCourseCategoryService courseCategoryService;
    @Autowired
    public ITClassWorkService classTaskService;
    @Autowired
    public ITOrganizationService organizationService;
    @Autowired
    public ITOrganActivityService organActivityService;

    @Autowired
    public IDictionaryService dictionaryService;
    @Autowired
    public ISequenceService sequenceService;


    public static Long userId = 1L;
    public static Long coursesId = 1L;
    public static Long lessionId = 1L;
    public static Long organId = 1L;
    public static Long teacherId =1L;
}
