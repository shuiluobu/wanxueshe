package com.wxs.service.course;

import com.baomidou.mybatisplus.service.IService;
import com.wxs.entity.course.TClassCourses;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
public interface ITCoursesService extends IService<TClassCourses> {
    /**
     * 课程基本信息
     * @param coursesId
     * @return
     */
    public TClassCourses getMyCourseInfo(Long coursesId);

    /**
     * 获取课程的所有课时，如果订阅了该课程，则显示课时状态
     * @param courseId
     * @param studentId
     * @return
     */
    Map<String,Object> getLessesonByCourse(Long courseId, Long studentId);
}
