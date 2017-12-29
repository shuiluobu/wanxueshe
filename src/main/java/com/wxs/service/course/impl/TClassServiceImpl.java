package com.wxs.service.course.impl;

import com.wxs.entity.course.TClass;
import com.wxs.mapper.course.TClassMapper;
import com.wxs.service.course.ITClassService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyh
 * @since 2017-09-21
 */
@Service
public class TClassServiceImpl extends ServiceImpl<TClassMapper, TClass> implements ITClassService {

    @Autowired
    private TClassMapper classMapper;
    @Override
    public List<TClass> pageData(TClass tClass) {
        return  classMapper.pageData(tClass);
    }

    @Override
    public TClass getByCourseId(Long courseId) {
        return classMapper.getByCourseId(courseId);
    }

    @Override
    public List<TClass> searchByName(String name, Long organId, Integer type, Long userId) {
        return classMapper.searchByName(name,organId,type,userId);
    }
}
