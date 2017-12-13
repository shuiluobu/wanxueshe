package com.wxs.service.course.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wxs.entity.course.TCourseCategory;
import com.wxs.entity.organ.TOrganization;
import com.wxs.mapper.course.TCourseCategoryMapper;
import com.wxs.service.course.ITCourseCategoryService;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
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
    public List<Map<String,Object>> getCourseListByOrgan(Long organId,Integer page) {
        EntityWrapper<TCourseCategory> ew = new EntityWrapper<TCourseCategory>();
        ew.eq("organId",organId);
        RowBounds rowBounds = new RowBounds(page-1,10);
        List<TCourseCategory> list =  courseCategoryMapper.selectPage(rowBounds,ew);
        List<Map<String,Object>> mapList = Lists.newArrayList();
        list.stream().forEach(bean->{
            Map<String,Object> map = Maps.newHashMap();
            map.put("courseName",bean.getCourseCategoryName());
            map.put("courseType",bean.getCategoryType()==null?"":bean.getCategoryType());
            map.put("canQty",bean.getCanQty());
            map.put("alreadyStudySum",bean.getAlreadyStudySum());
            map.put("cover",bean.getCover()==null?"": bean.getCover());//封面图片
            mapList.add(map);
        });
        return mapList;
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
        List<Map<String,Object>> list =   courseCategoryMapper.getTeacherCourseList(teacherId);
        list.stream().forEach(map->{
            map.put("courseType","科学启蒙-机器人编程"); //todo 后续从字典表中获取
        });
        return list;
    }


    @Override
    public List<TCourseCategory> getNearByCategorys(double latitude, double longitude) {
        //用sql查找5公里范围内的
        return courseCategoryMapper.getNearByCategorys(latitude, longitude, 5);

    }
}
