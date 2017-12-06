package com.wxs.service.course.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.entity.course.TCourseCategory;
import com.wxs.entity.organ.TOrganization;
import com.wxs.mapper.course.TCourseCategoryMapper;
import com.wxs.service.course.ITCourseCategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-09-29
 */
@Service
public class TCourseCategoryServiceImpl extends ServiceImpl<TCourseCategoryMapper, TCourseCategory> implements ITCourseCategoryService {

    @Autowired
    private TCourseCategoryMapper courseCategoryMapper;


    @Override
    public List<TCourseCategory> pageData(TCourseCategory courseCategory) {
        return courseCategoryMapper.pageData(courseCategory);
    }

    @Override
    public List<TCourseCategory> getCourseListByOrgan(Long organId) {
        EntityWrapper<TCourseCategory> ew = new EntityWrapper<TCourseCategory>();
        ew.eq("organId",organId);
        return courseCategoryMapper.selectList(ew);
    }

    @Override
    public List<TCourseCategory> getCourseListByType(String categoryType) {
        EntityWrapper<TCourseCategory> ew = new EntityWrapper<TCourseCategory>();
        ew.eq("categoryType",categoryType);
        return courseCategoryMapper.selectList(ew);
    }

    @Override
    public List<TCourseCategory> getAllCategoryByTeacher(Long teacherId) {
        return courseCategoryMapper.getAllCategoryByTeacher(teacherId);
    }


    /**
     * 老师教过的课程
     * @param teacherId
     * @return
     */
    public List<Map<String,Object>> getTeacherCourseList(Long teacherId){
        return  courseCategoryMapper.getTeacherCourseList(teacherId);
    }


    @Override
    public List<TCourseCategory> getNearByCategorys(double latitude, double longitude) {
        //用sql查找5公里范围内的
        return courseCategoryMapper.getNearByCategorys(latitude, longitude, 5);

    }
}
