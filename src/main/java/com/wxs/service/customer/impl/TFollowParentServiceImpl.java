package com.wxs.service.customer.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Maps;
import com.wxs.entity.course.TStudentClass;
import com.wxs.entity.customer.TFollowParent;
import com.wxs.entity.customer.TStudent;
import com.wxs.front.controller.StudentContorller;
import com.wxs.mapper.course.TStudentClassMapper;
import com.wxs.mapper.customer.TFollowParentMapper;
import com.wxs.mapper.customer.TStudentMapper;
import com.wxs.service.customer.ITFollowParentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@Service
public class TFollowParentServiceImpl extends ServiceImpl<TFollowParentMapper, TFollowParent> implements ITFollowParentService {

}
