package com.wxs.service.course.impl;

import com.wxs.entity.course.TStudentCourse;
import com.wxs.mapper.course.TStudentCourseMapper;
import com.wxs.service.course.ITStudentCourseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-09-23
 */
@Service
public class TStudentCourseServiceImpl extends ServiceImpl<TStudentCourseMapper, TStudentCourse> implements ITStudentCourseService {

    @Autowired
    private TStudentCourseMapper studentCourseMapper;
    @Override
    public List<TStudentCourse> getAllByOStudentId(Long oStudentId) {
        return studentCourseMapper.getAllByOStudentId(oStudentId);
    }
}
