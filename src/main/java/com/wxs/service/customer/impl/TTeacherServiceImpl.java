package com.wxs.service.customer.impl;

import com.wxs.entity.customer.TTeacher;
import com.wxs.mapper.customer.TTeacherMapper;
import com.wxs.service.customer.ITTeacherService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@Service
public class TTeacherServiceImpl extends ServiceImpl<TTeacherMapper, TTeacher> implements ITTeacherService {
    @Autowired
    private TTeacherMapper teacherMapper; //课程基本信息

    public Optional<Map> getTeacharInfoById(Long tId) {
        Map map = teacherMapper.selectTeacherById(tId);
        return Optional.of(map);
    }

}
