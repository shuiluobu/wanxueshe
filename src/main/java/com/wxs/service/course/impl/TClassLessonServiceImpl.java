package com.wxs.service.course.impl;

import com.google.common.collect.Maps;
import com.wxs.entity.course.TClassLesson;
import com.wxs.entity.course.TStudentClass;
import com.wxs.entity.customer.TStudent;
import com.wxs.mapper.course.TClassLessonMapper;
import com.wxs.mapper.course.TCoursesMapper;
import com.wxs.mapper.customer.TStudentMapper;
import com.wxs.service.course.ITClassLessonService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxs.core.util.BaseUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@Service
public class TClassLessonServiceImpl extends ServiceImpl<TClassLessonMapper, TClassLesson> implements ITClassLessonService {
    @Autowired
    private TCoursesMapper coursesMapper;
    @Autowired
    private TClassLessonMapper classLessonMapper;

    @Override
    public List<TClassLesson> pageData(TClassLesson classLesson) {
        return classLessonMapper.pageData(classLesson);
    }

    @Override
    public Map<String, Object> getOneClassLession(Long lessionId) {
        return null;
    }

    @Autowired
    private TStudentMapper studentMapper;

    @Override
    public Map<String, Object> getOneClassLession(Long lessionId, Long userId) {
        Map<String, Object> result = Maps.newHashMap();
        try {
            TClassLesson lesson = this.selectById(lessionId);
            Map<String, Object> course = coursesMapper.selectMap(lesson.getCourseId());
            result.put("realTearcherName",lesson.getRealTearcherName());
            result.put("shouldTearcherName",lesson.getShouldTearcherName());
            result.put("createTime",BaseUtil.toLongDate(lesson.getCreateTime()));
            result.put("content",lesson.getContent());
            result.put("lessonName",lesson.getLessonName());
            result.put("beginTime", BaseUtil.toString(lesson.getBeginTime(), "HH:mm"));
            result.put("endTime", BaseUtil.toString(lesson.getEndTime(), "HH:mm"));
            result.put("day", BaseUtil.toString(lesson.getEndTime(), "yyyy-MM-dd"));
            result.put("lessonSeq", lesson.getLessonSeq() + "/" + course.get("canQty")); //课时序号
            result.put("courseName", course.get("courseName"));
            result.put("teacherName", course.get("teacherName"));
            Map<String,Object> organMap = Maps.newHashMap();
            organMap.put("organName", course.get("organName"));
            organMap.put("organId",course.get("organizationId"));
            organMap.put("leval",course.get("leval"));
            result.put("organ",organMap);
            if (userId != null) {
                //如果是登录状态
                List<Map<String, Object>> students = studentMapper.getStudentByCourse(lesson.getCourseId(), userId);
                result.put("students", students);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public List<Map<String,Object>> getTodayCourseLesson(Long userId){
        String beginTime = BaseUtil.toShortDate(new Date());
        String endTIme = beginTime + " 23:59:59";
        return classLessonMapper.getTodayCourseLesson(beginTime,endTIme,userId);
    }
    @Override
    public List<Map<String,Object>> getNextDayCourseLesson(Long userId){
        //接下来一周的课程
        String beginTime = BaseUtil.toShortDate(new Date()) + " 23:59:59";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_WEEK, 7);
        String endTIme = BaseUtil.toShortDate(cal.getTime());
        return classLessonMapper.getTodayCourseLesson(beginTime,endTIme,userId);
    }

    @Override
    public List<Map<String,Object>> getCourseByTime(String beginTime,Long userId){
        //按日历来显示课程
        String endTIme = beginTime + " 23:59:59";
        return classLessonMapper.getTodayCourseLesson(beginTime,endTIme,userId);
    }


}
