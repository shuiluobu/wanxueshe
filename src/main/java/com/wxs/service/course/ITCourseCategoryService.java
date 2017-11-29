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

    //分页+混合条件 查询 班级
    public List<TCourseCategory> pageData(TCourseCategory courseCategory);

    public List<TCourseCategory> getAllCategoryOfOrgan(Long organId);
    public List<TCourseCategory> getAllCategoryByTeacher(Long teacherId);
}
