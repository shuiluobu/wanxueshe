package com.wxs.service.course;

import com.wxs.entity.course.TCourseCategory;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

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
    public List<Map<String,Object>> getCourseListByOrgan(Long organId,Integer offset);
    public List<TCourseCategory> getAllCategoryByTeacher(Long teacherId);
    public List<Map<String,Object>> searchCourseListForDiscovery(String categoryType,String searchName);
    public List<Map<String,Object>> getTeacherCourseList(Long teacherId);
    public List<TCourseCategory> getNearByCategorys(double latitude,double  longitude);
}
