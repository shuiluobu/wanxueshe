package com.wxs.service.customer.impl;

import com.google.common.collect.Lists;
import com.wxs.entity.customer.TTeacher;
import com.wxs.mapper.customer.TTeacherMapper;
import com.wxs.service.customer.ITTeacherService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

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

    public List<Map<String, Object>> getClassByTeacher(Long tId) {
        List<Map<String, Object>> result = Lists.newArrayList();
        teacherMapper.getClassByTeacher(tId).stream().forEach(item -> {
            item.put("courseType", ""); //这里之后要处理课程类型
            result.add(item);
        });
        return result;
    }

}
