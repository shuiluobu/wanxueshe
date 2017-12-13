package com.wxs.service.course.impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.sun.xml.internal.rngom.parse.host.Base;
import com.wxs.entity.course.TCourse;
import com.wxs.entity.course.TCourseCategory;
import com.wxs.entity.course.TStudentClass;
import com.wxs.entity.course.TStudentLessones;
import com.wxs.entity.organ.TOrganization;
import com.wxs.mapper.course.TClassLessonMapper;
import com.wxs.mapper.course.TCoursesMapper;
import com.wxs.mapper.course.TStudentClassMapper;
import com.wxs.mapper.course.TStudentLessonesMapper;
import com.wxs.mapper.customer.TFllowCourseMapper;
import com.wxs.mapper.organ.TOrganizationMapper;
import com.wxs.service.course.ITCoursesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.service.customer.ITParentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxs.core.util.BaseUtil;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<TCourse> pageData(TCourse course) {
        return coursesMapper.pageData(course);
    }

    @Override
    public Map<String, Object> getCourseOutlineInfo(Long coursesId, Long userId) {
        TCourse course = coursesMapper.selectById(coursesId);
        try {
            Map<String, Object> result = Maps.newHashMap();
            TOrganization organization = new TOrganization().selectById(course.getOrganizationId());
            Map<String,Object> organMap = Maps.newHashMap();
            organMap.put("organName",organization.getOrganName());
            organMap.put("leval",organization.getLeval()); //等级
            organMap.put("organId",organization.getId());
            result.put("organ",organMap);
            result.put("beginTime", BaseUtil.toLongDate(course.getBeginTime()));
            result.put("endTime", BaseUtil.toLongDate(course.getEndTime()));
            TCourseCategory category = new TCourseCategory().selectById(course.getCourseCateId());
            result.put("canQty",category.getCanQty()); //课时
            result.put("cateoryType",category.getCategoryType()); //类型
            result.put("courseRemark",category.getCourseRemark()); //简介
            result.put("fllowCount", fllowCourseMapper.getFllowCountOfCourseId(course.getCourseCateId())); //关注数
            result.put("areadyStudCount", category.getAlreadyStudySum());
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
                result.put("smallRemark", "我的上课时间：" + BaseUtil.toChinaDate(course.getBeginTime()) + "-" + BaseUtil.toChinaDate(course.getEndTime())
                        + " 我参与的学员：" + StringUtils.split(studentNames.toString(), ","));
            } else {
                result.put("smallRemark","");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getCourseFllowUserList(Long coursesId,Long loginUserId) {
        TCourse course = new TCourse().selectById(coursesId);
        List<Long> userIds = fllowCourseMapper.getFllowUserIdsOfCourseId(course.getCourseCateId());
        return parentService.getFllowUsers(userIds,loginUserId);

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
            resultMap.put("remainQty",sumQty-finishQty);
            resultMap.put("sumQty", sumQty);
        }
        List<Map<String,Object>> lessones = classLessonMapper.queryLessionByCourse(courseId, null,userId);
        lessones.stream().forEach(map ->{
            map.put("dayTime",map.get("dayTime").toString() + " " + map.get("beginTime").toString()+"-"+map.get("endTime").toString());
            TStudentLessones stuLesson = new TStudentLessones().selectOne("courseId={0} and userId={1}",courseId,userId);
            if(stuLesson!=null){
                map.put("scheduleStatus",stuLesson.getScheduleStatus()); //todo 后期从字段表里取值
            }
            map.put("canQty",course.getCanQty()); //总课时
        });
        resultMap.put("lessones", lessones);
        return resultMap;
    }

}
