package com.wxs.service.course;

import com.wxs.entity.course.TCourses;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
public interface ITCoursesService extends IService<TCourses> {
    /**
     * 课程基本信息
     * @param coursesId
     * @return
     */
    public TCourses getMyCourseInfo(Long coursesId);

    /**
     * 获取课程的所有课时，如果订阅了该课程，则显示课时状态
     * @param courseId
     * @param studentId
     * @return
     */
    Map<String,Object> getLessesonByCourse(Long courseId, Long studentId);
}
