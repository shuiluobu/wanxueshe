package com.wxs.service.course;

import com.wxs.entity.course.TStudentCourse;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-09-23
 */
public interface ITStudentCourseService extends IService<TStudentCourse> {

    //获取 机构端 某学生的 所有课程
    List<TStudentCourse> getAllByOStudentId(Long oStudentId);

}
