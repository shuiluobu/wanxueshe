package com.wxs.service.course.impl;

import com.wxs.entity.comment.TDyimg;
import com.wxs.entity.comment.TDynamicmsg;
import com.wxs.entity.course.TClass;
import com.wxs.entity.course.TStudentClass;
import com.wxs.entity.task.TClassTask;
import com.wxs.entity.task.TStudentTask;
import com.wxs.mapper.course.TStudentClassMapper;
import com.wxs.service.comment.ITDynamicmsgService;
import com.wxs.service.course.ITStudentCourseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wxs.core.util.BaseUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-09-23
 */
@Service
public class TStudentCourseServiceImpl extends ServiceImpl<TStudentClassMapper, TStudentClass> implements ITStudentCourseService {

}
