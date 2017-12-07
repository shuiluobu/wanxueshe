package com.wxs.service.customer.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wxs.entity.customer.TTeacher;
import com.wxs.entity.organ.TOrganization;
import com.wxs.mapper.customer.TFollowUserMapper;
import com.wxs.mapper.customer.TTeacherMapper;
import com.wxs.service.customer.ITParentService;
import com.wxs.service.customer.ITTeacherService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxs.core.util.BaseUtil;

import java.util.List;
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
    @Autowired
    private TFollowUserMapper followUserMapper;
    @Autowired
    private ITParentService parentService;

    public Optional<Map> getTeacharInfoById(Long tId) {
        Map map = teacherMapper.selectTeacherById(tId);
        return Optional.of(map);
    }

    public Map<String, Object> getTeacherOutline(Long teacherId, Long userId) throws Exception {
        Map<String, Object> result = Maps.newHashMap();
        TTeacher teacher = teacherMapper.selectById(teacherId);
        TOrganization organization = new TOrganization().selectById(teacher.getOrganizationId());
        teacher.setOrganization(organization);
        result = BaseUtil.convertBeanToMap(teacher);
        int fllowCount = followUserMapper.getFllowTeacherByCount(teacherId);
        int studentCount = teacherMapper.getTeacherStudentCount(teacherId);
        result.put("fllowCount", fllowCount);//关注人数
        result.put("studentCount", studentCount);//教过的学员
        if (userId != null) {
            result.put("ifFllow", followUserMapper.getOneFllowTeacherByUser(userId, teacherId) == null ? false : true);
        }
        return result;
    }

    @Override
    public List<Map<String,Object>> getOrganFllowUserList(Long organId){
        List<Long> userIds = followUserMapper.getFllowUserIdsOfTeacherId(organId);
        return parentService.getFllowUsers(userIds);
    }


}
