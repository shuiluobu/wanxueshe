package com.wxs.service.organ.impl;

import com.wxs.entity.course.TClassLesson;
import com.wxs.entity.customer.TTeacher;
import com.wxs.entity.organ.TOrganStudent;
import com.wxs.mapper.organ.TOrganStudentMapper;
import com.wxs.service.organ.ITOrganStudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyh
 * @since 2017-12-29
 */
@Service
public class TOrganStudentServiceImpl extends ServiceImpl<TOrganStudentMapper, TOrganStudent> implements ITOrganStudentService {

    @Autowired
    private TOrganStudentMapper organStudentMapper;

    @Override
    public List<TTeacher> searchAdvisorByName(Long organId, String name) {
        return organStudentMapper.searchAdvisorByName(organId,name);
    }

    @Override
    public List<TOrganStudent> searchByName(String name, Long organId) {
        return organStudentMapper.searchByName(name,organId);
    }

    @Override
    public List<TOrganStudent> getAllByOrganId(Long organId) {
        return organStudentMapper.getAllByOrganId(organId);
    }

    @Override
    public List<Map<String, Object>> canMULessonStus(Long organId, String studentName) {
        return organStudentMapper.canMULessonStus(organId,studentName);
    }

}
