package com.wxs.service.customer.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.entity.customer.TFollowTeacher;
import com.wxs.entity.customer.TParent;
import com.wxs.mapper.course.TStudentClassMapper;
import com.wxs.mapper.customer.TFollowTeacherMapper;
import com.wxs.mapper.customer.TFollowUserMapper;
import com.wxs.mapper.customer.TStudentMapper;
import com.wxs.service.customer.ITFollowTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-12-21
 */
@Service
public class TFollowTeacherServiceImpl extends ServiceImpl<TFollowTeacherMapper, TFollowTeacher> implements ITFollowTeacherService {
    @Autowired
    private TFollowUserMapper followUserMapper;
    @Autowired
    private TStudentMapper studentMapper;
    @Autowired
    private TStudentClassMapper studentClassMapper;

}
