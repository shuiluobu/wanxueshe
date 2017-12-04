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
public class TClassLessonServiceImpl extends ServiceImpl<TClassLessonMapper, TClassLesson> implements ITClassLessonService {
    @Autowired
    private TCoursesMapper coursesMapper;
    @Autowired
    private TStudentMapper studentMapper;

    @Override
    public Map<String, Object> getOneClassLession(Long lessionId, Long userId) {
        Map<String, Object> result = Maps.newHashMap();
        try {
            TClassLesson lesson = this.selectById(lessionId);
            Map<String, Object> course = coursesMapper.selectMap(lesson.getCourseId());

            result = BaseUtil.convertBeanToMap(lesson);
            result.put("beginTime", BaseUtil.toString(lesson.getBeginTime(), "HH:mm:ss"));
            result.put("endTime", BaseUtil.toString(lesson.getEndTime(), "HH:mm:ss"));
            result.put("day", BaseUtil.toString(lesson.getEndTime(), "yyyy-MM-dd"));
            result.put("lessonSeq", lesson.getLessonSeq() + "/" + course.get("canQty")); //课时序号
            result.put("courseName", course.get("courseName"));
            result.put("teacherName", course.get("teacherName"));
            result.put("organName", course.get("organName"));
            if (userId != null) {
                //如果是登录状态
                List<Map<String, Object>> students = studentMapper.getStudentByCourse(lesson.getCourseId(), userId);
                result.put("students", students);
            }

            return result;
        } catch (Exception e) {

        }
        return result;
    }
}
