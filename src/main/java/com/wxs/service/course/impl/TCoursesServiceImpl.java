package com.wxs.service.course.impl;

import com.google.common.collect.Maps;
import com.wxs.entity.course.TCourses;
import com.wxs.mapper.course.TClassLessonMapper;
import com.wxs.mapper.course.TCoursesMapper;
import com.wxs.mapper.course.TStudentCourseMapper;
import com.wxs.mapper.course.TStudentLessonesMapper;
import com.wxs.mapper.organ.TOrganizationMapper;
import com.wxs.service.course.ITCoursesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
 * @since 2017-09-21
 */
@Service
public class TCoursesServiceImpl extends ServiceImpl<TCoursesMapper, TCourses> implements ITCoursesService {

    @Autowired
    private TCoursesMapper coursesMapper; //课程基本信息
    @Autowired
    private TClassLessonMapper classLessonMapper; //课时基本信息
    @Autowired
    private TOrganizationMapper organizationMapper; //培训机构mapper
    @Autowired
    private TStudentLessonesMapper studentLessonesMapper; //学生课时关联mapper


    @Override
    public TCourses getMyCourseInfo(Long coursesId) {
        TCourses course = coursesMapper.selectOneCourseById(coursesId);
        course.setOrganization(organizationMapper.selectById(course.getOrganizationId()));
        return course;
    }

    @Override
    public Map<String, Object> getLessesonByCourse(Long courseId, Long studentId) {
        Map<String,Object> resultMap = Maps.newHashMap();
        if (studentId != null) {
            Integer sumQty = 0,finishQty=0;
            List<Map<String, Object>> groupMaps = studentLessonesMapper.groupLessesByStudentId(studentId, courseId);
            for(Map<String,Object> groupMap : groupMaps){
                sumQty = sumQty + Integer.parseInt(groupMap.get("qty").toString());
                if(StringUtils.equals(groupMap.get("condition").toString(),"finish")){
                    finishQty = finishQty + Integer.parseInt(groupMap.get("qty").toString());
                }
            }
            resultMap.put("finishQty",finishQty);
            resultMap.put("sumQty",sumQty);
        }
        resultMap.put("lessones",classLessonMapper.queryLessionByCourse(courseId, studentId));
        return resultMap;
    }

}
