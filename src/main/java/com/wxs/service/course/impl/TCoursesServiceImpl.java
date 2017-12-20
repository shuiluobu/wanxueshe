package com.wxs.service.course.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.xml.internal.rngom.parse.host.Base;
import com.wxs.entity.course.*;
import com.wxs.entity.message.TRemindMessage;
import com.wxs.entity.organ.TOrganization;
import com.wxs.mapper.course.TClassLessonMapper;
import com.wxs.mapper.course.TCoursesMapper;
import com.wxs.mapper.course.TStudentClassMapper;
import com.wxs.mapper.course.TStudentLessonesMapper;
import com.wxs.mapper.customer.TFllowCourseMapper;
import com.wxs.mapper.customer.TTeacherMapper;
import com.wxs.mapper.organ.TOrganizationMapper;
import com.wxs.service.common.IDictionaryService;
import com.wxs.service.common.ISequenceService;
import com.wxs.service.course.ITCoursesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.service.customer.ITParentService;
import net.bytebuddy.asm.Advice;
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
public class TCoursesServiceImpl extends ServiceImpl<TCoursesMapper, TCourse> implements ITCoursesService {

    @Autowired
    private TCoursesMapper coursesMapper; //课程基本信息
    @Autowired
    private TClassLessonMapper classLessonMapper; //课时基本信息
    @Autowired
    private TStudentLessonesMapper studentLessonesMapper; //学生课时关联mapper
    @Autowired
    private TFllowCourseMapper fllowCourseMapper;
    @Autowired
    private ITParentService parentService;
    @Autowired
    private TStudentClassMapper studentClassMapper;
    @Autowired
    private TTeacherMapper teacherMapper;
    @Autowired
    public IDictionaryService dictionaryService;
    @Autowired
    public ISequenceService sequenceService;

    @Override
    public List<TCourse> pageData(TCourse course) {
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
            organMap.put("leval", organization.getLeval()); //等级
            organMap.put("organId", organization.getId());
            result.put("organ", organMap);
            result.put("coursePlans", coursesMapper.getCoursePlans(category.getId())); //课时计划
            result.put("canQty", category.getCanQty()); //课时
            result.put("backgroundImg", category.getBackgroundImg()); //背景图
            result.put("coverImg", category.getCover()); //封面头像图
            result.put("cateoryType", category.getCategoryType()); //类型
            result.put("courseRemark", category.getCourseRemark()); //简介
            result.put("fllowCount", fllowCourseMapper.getFllowCountOfCourseId(coursesId)); //关注数
            result.put("areadyStudCount", category.getAlreadyStudySum());
            result.put("teacherList", teacherMapper.getTeacherNameListByCourseId(coursesId)); //教师信息
            TStudentClass stuClass = new TStudentClass(); //参数传递
            stuClass.setCourseCateId(category.getId());
            stuClass.setUserId(userId);
            int ifIn = studentClassMapper.getClassStudentCountByParam(stuClass);
            result.put("isPartake", ifIn > 0 ? true : false); //是否参与
            if (ifIn > 0) {
                List<Map<String, Object>> studentClassMapList = studentClassMapper.getMyCourses(ImmutableMap.of("userId", userId));
                StringBuffer studentNames = new StringBuffer();
                studentClassMapList.stream().forEach(map -> {
                    studentNames.append(map.get("studentName").toString());
                });
                TCourse course = new TCourse().selectById(stuClass.getCoursesId());
                result.put("smallRemark", "我的上课时间：" + BaseUtil.toChinaDate(course.getBeginTime()) + "-" + BaseUtil.toChinaDate(course.getEndTime())
                        + " 我参与的学员：" + StringUtils.split(studentNames.toString(), ","));
            } else {
                result.put("smallRemark", "");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getCourseFllowUserList(Long coursesId, Long loginUserId) {
        TCourse course = new TCourse().selectById(coursesId);
        List<Long> userIds = fllowCourseMapper.getFllowUserIdsOfCourseId(course.getCourseCateId());
        return parentService.getFllowUsers(userIds, loginUserId);

    }

    @Override
    public Map<String, Object> getLessesonByCourse(Long courseId, Long userId) {
        TCourse course = new TCourse().selectById(courseId);
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
    @Transactional
    public Map<String, Object> addCourseByApp(Long userId, Map param) {
        String courseCode = sequenceService.getCourseCode(userId);//code
        String courseType = param.get("types").toString();
        String courseName = param.get("courseName").toString();
        String organName = param.get("organName").toString();
        String way = param.get("way").toString(); //周期或者单次
        String beginDays = "";
        String endDays = "";
        Integer remindMin = Integer.parseInt(param.get("remindTime").toString()); //提前积分中提醒
        String remindWay = param.get("remindWay").toString();
        String courseAddress = param.get("courseAddress").toString();
        String teacherName = param.get("teacherName").toString();
        String permissions = param.get("permissions").toString();
        String courseDetails = param.get("courseDetails").toString();
        String courseDates = param.get("courseDates").toString();
        String students[] = StringUtils.split(param.get("students").toString(),","); //学员
        List<Map<String, Object>> lessonList = BaseUtil.parseJson(courseDates, List.class);
        if (way.equals("0")) {
            //单次
            Map smap = lessonList.get(0);
            beginDays = smap.get("day").toString() + " " + smap.get("beginTime").toString();
            Map emap = lessonList.get(lessonList.size() - 1); //最后一个
            endDays = emap.get("day").toString() + " " + emap.get("endTime").toString();
        } else {
            //周期
            beginDays = param.get("beginDays") == null ? "" : param.get("beginDays").toString();
            endDays = param.get("endDays") == null ? "" : param.get("endDays").toString();
        }
        TCourse classCourse = new TCourse();
        classCourse.setCourseType(courseType); //类型
        classCourse.setCourseName(courseName);
        classCourse.setCourseCode(courseCode);
        classCourse.setBeginTime(BaseUtil.toDate(beginDays));
        classCourse.setEndTime(BaseUtil.toDate(endDays));
        classCourse.setOrganName(organName);
        classCourse.setCourseCateId(0L); //表示没有关联的机构课程
        classCourse.setOrganizationId(0L); //表示暂时没有机构
        classCourse.setTeacherName(teacherName); //授课老师
        classCourse.setCourseRemark(courseDetails);
        classCourse.insert();//保存课程
        for(String studentId : students){
            TStudentClass studentClass = new TStudentClass();
            studentClass.setUserId(userId);
            studentClass.setCoursesId(classCourse.getId());
            studentClass.setCreateTime(new Date());
            studentClass.setIsEnd(0); //未完成
            studentClass.setStudentId(Long.parseLong(studentId));
            studentClass.insert();
        }

        int i = 1;
        for (Map lessonMap : lessonList) {
            List<String> lessonDays = Lists.newArrayList();
            if (lessonMap.get("day") != null) {
                lessonDays.add(lessonMap.get("day").toString());
            } else {
                //如果是按每周，则需要便利都星期几上课
                LinkedHashMap<String, String> map = getWeekDateOfCycle(beginDays, endDays);
                String[] weeks = StringUtils.split(param.get("week").toString() + ",");
                for (String key : map.keySet()) {
                    String value = map.get(key);
                    for (String week : weeks) {
                        if (value.equals(week)) {
                            lessonDays.add(key);
                        }
                    }
                }
            }

            for (String day : lessonDays) {
                String beginTime = day + param.get("beginTime").toString();
                String endTime = day + param.get("endTime").toString();
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
                remindMessage.setRemindTime( new Date(classLesson.getBeginTime().getTime() - remindMin * 60 * 1000)); //提醒时间
                remindMessage.setUserId(userId);
                remindMessage.setFromUserId(0L);
                remindMessage.setRemindMedia(remindWay); //提醒媒介
                remindMessage.setTile(classLesson.getLessonName()+"提醒");
                remindMessage.setMessageContent(BaseUtil.toJson(classLesson));
                remindMessage.setReadStatus(0);
                remindMessage.setJmsType("queue");
                remindMessage.setStatus(0);
                remindMessage.insert(); //保存提醒
            }
        }

        return null;
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
}
