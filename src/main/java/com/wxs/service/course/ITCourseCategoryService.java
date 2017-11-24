package com.wxs.service.course;

import com.wxs.entity.course.TCourseCategory;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-09-29
 */
public interface ITCourseCategoryService extends IService<TCourseCategory> {
    public List<TCourseCategory> getAllCategoryOfOrgan(Long organId);
    public List<TCourseCategory> getAllCategoryByTeacher(Long teacherId);
}
