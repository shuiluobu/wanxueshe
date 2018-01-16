package com.wxs.service.course.impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wxs.entity.course.*;
import com.wxs.entity.customer.TFrontUser;
import com.wxs.entity.message.TRemindMessage;
import com.wxs.entity.organ.TOrganization;
import com.wxs.mapper.course.TClassLessonMapper;
import com.wxs.mapper.course.TClassCoursesMapper;
import com.wxs.mapper.course.TStudentCourseMapper;
import com.wxs.mapper.course.TStudentLessonesMapper;
import com.wxs.mapper.customer.TFollowCourseMapper;
import com.wxs.mapper.customer.TTeacherMapper;
import com.wxs.service.common.IDictionaryService;
import com.wxs.service.common.ISequenceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.service.course.ITClassCoursesService;
import com.wxs.service.customer.ITParentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wxs.core.util.BaseUtil;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@Service
public class TClassCoursesServiceImpl extends ServiceImpl<TClassCoursesMapper, TClassCourse> implements ITClassCoursesService {

    @Autowired
    private TClassCoursesMapper coursesMapper; //课程基本信息
    @Autowired
    private TClassLessonMapper classLessonMapper; //课时基本信息
    @Autowired
    private TStudentLessonesMapper studentLessonesMapper; //学生课时关联mapper
    @Autowired
    private TFollowCourseMapper fllowCourseMapper;
    @Autowired
    private ITParentService parentService;
    @Autowired
    private TStudentCourseMapper studentCourseMapper;
    @Autowired
    private TTeacherMapper teacherMapper;
    @Autowired
    public IDictionaryService dictionaryService;
    @Autowired
    public ISequenceService sequenceService;

    @Override
    public List<TClassCourse> pageData(TClassCourse course) {
        return coursesMapper.pageData(course);
    }

    @Override
    public Map<String, Object> getCourseOutlineInfo(Long coursesId, Long userId) {
        TCourseCategory category = new TCourseCategory().selectById(coursesId); //大课程信息
        try {
            Map<String, Object> result = Maps.newHashMap();
            TOrganization organization = new TOrganization().selectById(category.getOrganId());
            Map<String, Object> organMap = Maps.newHashMap();
            organMap.put("organName", organization.getOrganName());
            organMap.put("leval", organization.getLeval() == 1 ? "已认证" : ""); //等级
            organMap.put("organId", organization.getId());
            result.put("courseName", category.getCourseCategoryName());
            result.put("organ", organMap);
            result.put("leval","");
            result.put("coursePlans", coursesMapper.getCoursePlans(category.getId())); //课时计划
            result.put("canQty", category.getCanQty()); //课时
            result.put("coverImg", category.getCover()); //封面头像图
            result.put("subjectType", category.getSubjectType()); //学科分类
            result.put("courseType", category.getCourseType()); //课程类型，如试听课
            result.put("courseRemark", category.getCourseRemark()); //简介
            result.put("fllowCount", fllowCourseMapper.getFllowCountOfCourseId(coursesId)); //关注数
            result.put("areadyStudCount", category.getAlreadyStudySum());
            result.put("teacherList", teacherMapper.getTeacherNameListByCourseId(coursesId)); //教师信息
            TStudentCourse stuClass = new TStudentCourse().selectOne("courseCateId={0} and userId={1}", category.getId(), userId); //参数传递

            result.put("isPartake", stuClass != null ? true : false); //是否参与
            if (stuClass != null) {
                List<Map<String, Object>> studentClassMapList = studentCourseMapper.getMyCourses(ImmutableMap.of("userId", userId));
                StringBuffer studentNames = new StringBuffer();
                studentClassMapList.stream().forEach(map -> {
                    studentNames.append(map.get("realName").toString());
                });
                TClassCourse course = new TClassCourse().selectById(stuClass.getCoursesId());
                result.put("joinTime", stuClass.getCreateTime()); //参加时间
                result.put("smallRemark", "我的上课时间：" + BaseUtil.toChinaDate(course.getBeginTime()) + "-" + BaseUtil.toChinaDate(course.getEndTime())
                        + " 我参与的学员：" + StringUtils.split(studentNames.toString(), ","));
            } else {
                result.put("smallRemark", "");
                TFrontUser createUser = new TFrontUser().selectById(category.getCreateUserId());
                if (createUser != null) {
                    result.put("createUserName", createUser.getUserName());
                    result.put("createUserId", category.getCreateUserId());
                }

            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getCourseFllowUserList(Long coursesId, Long loginUserId) {
        TClassCourse course = new TClassCourse().selectById(coursesId);
        List<Long> userIds = fllowCourseMapper.getFllowUserIdsOfCourseId(course.getCourseCateId());
        return parentService.getFllowUsers(userIds, loginUserId);

    }

    @Override
    public Map<String, Object> getLessesonByCourse(Long courseId, Long userId) {
        TClassCourse course = new TClassCourse().selectById(courseId);
        Map<String, Object> resultMap = Maps.newHashMap();
        if (userId != null) {
            Integer sumQty = 0, finishQty = 0;
            List<Map<String, Object>> groupMaps = studentLessonesMapper.groupLessesByUserId(userId, courseId);
            for (Map<String, Object> groupMap : groupMaps) {
                sumQty = sumQty + Integer.parseInt(groupMap.get("qty").toString());
                if (StringUtils.equals(groupMap.get("scheduleStatus").toString(), "finish")) {
                    finishQty = finishQty + Integer.parseInt(groupMap.get("qty").toString());
                }
            }
            resultMap.put("finishQty", finishQty);
            resultMap.put("remainQty", sumQty - finishQty);
            resultMap.put("sumQty", sumQty);
        }
        List<Map<String, Object>> lessones = classLessonMapper.queryLessionByCourse(courseId, null, userId);
        lessones.stream().forEach(map -> {
            map.put("dayTime", map.get("dayTime").toString() + " " + map.get("beginTime").toString() + "-" + map.get("endTime").toString());
            TStudentLessones stuLesson = new TStudentLessones().selectOne("courseId={0} and userId={1}", courseId, userId);
            if (stuLesson != null) {
                String statusValue = dictionaryService.getLessonStudyStatus().get(stuLesson.getScheduleStatus().toString());
                map.put("scheduleStatus", BaseUtil.getKeyValueMap(stuLesson.getScheduleStatus(), statusValue)); //todo 后期从字段表里取值
            }
            map.put("canQty", course.getCanQty()); //总课时
        });
        resultMap.put("lessones", lessones);
        return resultMap;
    }

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public Map<String, Object> addCourseByApp(Long userId, Map param) {
        Map<String,Object> result = Maps.newHashMap();
        String subjectType = param.get("types").toString();
        String courseName = param.get("courseName").toString();

        String organName = param.get("organName").toString();
        String way = param.get("way").toString(); //周期或者单次
        Integer remindMin = Integer.parseInt(param.get("remindTime").toString()); //提前积分中提醒
        String remindWay = param.get("remindWay").toString();
        String courseAddress = param.get("courseAddress").toString();
        String teacherName = param.get("teacherName").toString();
        String permissions = param.get("permissions").toString();
        String courseDetails = param.get("courseDetails").toString();
        String courseDates = param.get("courseDates").toString();
        String students[] = StringUtils.split(param.get("students").toString(), ","); //学员
        Long organId = param.get("organId") != null ? Long.parseLong(param.get("organId").toString()) : null;
        List<Map<String, Object>> lessonList = BaseUtil.parseJson(courseDates, List.class);
        TOrganization organ = null;
        if (organId != null) {
            organ = new TOrganization().selectById(organId);
        }
        if (organ == null) {
            organ = new TOrganization();
            organ.setOrganCode(sequenceService.getOrganCode(userId, "C")); //有c端产生的机构
            organ.setAddress(courseAddress);
            organ.insert();
            organId = organ.getId();
        }

        TCourseCategory courseCategory = new TCourseCategory();
        courseCategory.setCanQty(lessonList.size()); //总课时
        courseCategory.setAlreadyStudySum(students.length); //已经上课学生
        courseCategory.setCourseCategoryName(courseName);
        courseCategory.setCourseCategoryCode(sequenceService.getCourseCode(userId, "C")); //有c端用户产生
        courseCategory.setCreateUserId(userId);
        courseCategory.setCourseRemark(courseDetails);
        courseCategory.setSubjectType(subjectType);
        courseCategory.setOrganId(organId);
        courseCategory.insert();//保存创建的大课程

        TClassCourse classCourse = new TClassCourse();
        classCourse.setCanQty(courseCategory.getCanQty()); //冗余
        classCourse.setCourseName(courseCategory.getCourseCategoryName()); //课程名称 冗余
        classCourse.setCourseCateId(courseCategory.getId());
        classCourse.setTeacherName(teacherName);
        classCourse.setOrganizationId(null); //自己创建的不关联机构
        classCourse.setTeacherId(null); //自己创建的不关联老师
        for (String studentId : students) {
            TStudentCourse studentClass = new TStudentCourse();
            studentClass.setUserId(userId);
            studentClass.setCoursesId(courseCategory.getId());
            studentClass.setCreateTime(new Date());
            studentClass.setIsEnd(0); //未完成
            studentClass.setStudentId(Long.parseLong(studentId));
            studentClass.insert();
        }

        int i = 1;
        List<Map<String,String>> lessonDays = Lists.newArrayList();
        if(way.equals("0")){
            //单次
            String day = param.get("day").toString();
            String beginTime = day + " " + param.get("beginTime").toString();
            String endTime = day + " " +param.get("endTime").toString();
            lessonDays.add(ImmutableMap.of("beginTime",beginTime,"endTime",endTime));
        } else {
            //周期
            for (Map lessonMap : lessonList) {

                String beginTime = lessonMap.get("startTime").toString()+":00";
                String endTime = lessonMap.get("endTime").toString()+":00";
                if (lessonMap.get("day") != null && !"".equals(lessonMap.get("day").toString())) {
                    String day = lessonMap.get("day").toString();
                    lessonDays.add(ImmutableMap.of("beginTime",day + " " + beginTime,"endTime",day+" " +endTime));
                } else {
                    String beginDays = lessonMap.get("beginDays").toString()+" 00:00:00";
                    String endDays = lessonMap.get("endDays").toString()+" 00:00:00";
                    String weeks = lessonMap.get("weeks").toString();
                    //如果是按每周，则需要便利都星期几上课
                    LinkedHashMap<String, String> map = getWeekDateOfCycle(beginDays, endDays);

                    String[] weekArr = StringUtils.split(weeks,",");
                    for (String key : map.keySet()) {
                        String value = map.get(key);
                        for (String week : weekArr) {
                            if (value.equals(week)) {
                                lessonDays.add(ImmutableMap.of("beginTime",key + " " + beginTime,"endTime",key+" " +endTime));
                            }
                        }
                    }
                }
            }
        }

        for (Map<String,String> lessonTime : lessonDays) {
            String beginTime = lessonTime.get("beginTime").toString();
            String endTime = lessonTime.get("endTime").toString();
            TClassLesson classLesson = new TClassLesson();
            classLesson.setBeginTime(BaseUtil.toDate(beginTime));
            classLesson.setEndTime(BaseUtil.toDate(endTime));
            classLesson.setCourseId(classCourse.getId());
            classLesson.setLessonName(courseName + i);
            classLesson.setLessonSeq(i);
            classLesson.setContent("");
            classLesson.setStatus(0);
            classLesson.insert(); //保存课时
            i++;
            TRemindMessage remindMessage = new TRemindMessage();
            remindMessage.setCreateTime(new Date());
            remindMessage.setRemindTime(new Date(classLesson.getBeginTime().getTime() - remindMin * 60 * 1000)); //提醒时间
            remindMessage.setUserId(userId);
            remindMessage.setFromUserId(0L);
            remindMessage.setRemindMedia(remindWay); //提醒媒介
            remindMessage.setTile(classLesson.getLessonName() + "提醒");
            remindMessage.setMessageContent(BaseUtil.toJson(classLesson));
            remindMessage.setReadStatus(0);
            remindMessage.setJmsType("queue");
            remindMessage.setStatus(0);
            remindMessage.insert(); //保存提醒
        }
        result.put("success",true);
        result.put("message","保存课程成功");
        return result;
    }

    @Override
    public List<Map<String, Object>> getFollowCourseInfoByUserId(Long userId) {
        List<Map<String, Object>> mapList = fllowCourseMapper.getFollowCoursesByUser(userId);
        mapList.stream().forEach(bean -> {
            if (bean.get("subjectType") != null) {
                String subjectType = bean.get("subjectType").toString();
                bean.put("subjectType", dictionaryService.getSubjectTypeValue(subjectType, "1"));
            } else {
                bean.put("subjectType","");
            }
        });

        return mapList;
    }

    @Override
    public List<Map<String, Object>> stuCourseDoneInfo(Long courseId) {
        return coursesMapper.stuCourseDoneInfo(courseId);
    }

    public static LinkedHashMap<String, String> getWeekDateOfCycle(String beginDate, String endDate) {
        int days = (int) (BaseUtil.toDate(endDate).getTime() - BaseUtil.toDate(beginDate).getTime()) / (1000 * 60 * 60 * 24) + 1;
        Calendar startTime = Calendar.getInstance();
        startTime.clear();
        startTime.setTime(BaseUtil.toDate(beginDate));
        LinkedHashMap<String, String> weekDayMap = Maps.newLinkedHashMap();
        for (int i = 0; i < days; i++) {
            weekDayMap.put(BaseUtil.toShortDate(startTime.getTime()), BaseUtil.getWeekOfDate(startTime.getTime()));
            startTime.add(Calendar.DAY_OF_YEAR, 1);
        }
        return weekDayMap;
    }

//    @Override
//    public List<Map<String, Object>> getUserCourseInfo(Long organId, Integer page, Integer endType) {
//        EntityWrapper<TClassCourse> ew = new EntityWrapper<TClassCourse>();
//        String nowDate = BaseUtil.toLongDate(new Date());
//        ew.eq("organId", organId);
//        if (endType.equals("0")) {
//            //表示未完成
//            ew.and("endTime>{0}", nowDate);
//        } else if (endType.equals("1")) {
//            //表示已完成
//            ew.and("endTime<={0}", nowDate);
//        }
//        RowBounds rowBounds = new RowBounds(page - 1, 10);
//        List<TClassCourse> list = coursesMapper.selectPage(rowBounds, ew);
//        List<Map<String, Object>> mapList = Lists.newArrayList();
//        list.stream().forEach(bean -> {
//            TCourseCategory bigCourse = new TCourseCategory().selectById(bean.getCourseCateId());
//            Map<String, Object> map = Maps.newHashMap();
//            map.put("courseName", bean.getCourseName());
//            map.put("subjectType", dictionaryService.getCourseTypeValue(bean.getCourseType(), "2"));
//            map.put("canQty", bean.getCanQty());
//            map.put("alreadyStudySum", bigCourse.getAlreadyStudySum());
//            map.put("cover", bigCourse.getCover() == null ? "" : bigCourse.getCover());//封面图片
//            mapList.add(map);
//        });
//        return mapList;
//    }
}
