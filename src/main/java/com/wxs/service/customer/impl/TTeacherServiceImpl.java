package com.wxs.service.customer.impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wxs.entity.customer.TFollowTeacher;
import com.wxs.entity.customer.TFollowUser;
import com.wxs.entity.customer.TTeacher;
import com.wxs.entity.organ.TOrganization;
import com.wxs.mapper.customer.TFollowTeacherMapper;
import com.wxs.mapper.customer.TFollowUserMapper;
import com.wxs.mapper.customer.TTeacherMapper;
import com.wxs.service.customer.ITParentService;
import com.wxs.service.customer.ITTeacherService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxs.core.util.BaseUtil;

import java.util.Date;
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
    private TFollowTeacherMapper followTeacherMapper;
    @Autowired
    private ITParentService parentService;

    public Optional<Map> getTeacharInfoById(Long tId) {
        Map map = teacherMapper.selectTeacherById(tId);
        return Optional.of(map);
    }
    @Override
    public Map<String, Object> getTeacherOutline(Long teacherId, Long userId)  {
        Map<String, Object> result = Maps.newHashMap();
        TTeacher teacher = teacherMapper.selectById(teacherId);
        TOrganization organization = new TOrganization().selectById(teacher.getOrganizationId());
        result.put("teacherId",teacher.getId());
        result.put("teacherName",teacher.getTeacherName());
        result.put("leval",teacher.getLeval()==1?"已认证":"");//教师等级，是否认证
        result.put("organ", ImmutableMap.of("organName",organization.getOrganName(),"organId",organization.getId()));
        int fllowCount = followTeacherMapper.getFllowTeacherByCount(teacherId);
        int studentCount = teacherMapper.getTeacherStudentCount(teacherId);
        result.put("fllowCount", fllowCount);//关注人数
        result.put("studentCount", studentCount);//教过的学员
        if (userId != null) {
            TFollowTeacher followTeacher =  followTeacherMapper.getOneFllowTeacherByUser(userId, teacherId);
            result.put("ifFllow", followTeacher == null ? false : true);
        }
        return result;
    }

    @Override
    public List<Map<String,Object>> getOrganFllowUserList(Long organId,Long loginUserId){
        List<Long> userIds = followTeacherMapper.getFllowUserIdsOfTeacherId(loginUserId);
        return parentService.getFllowUsers(userIds,loginUserId);
    }
    @Override
    public List<Map<String,Object>> getFollowTeachInfoByUserId(Long userId){
        List<Map<String,Object>> list = followTeacherMapper.getFollowTeacherByUser(userId);
        list.stream().forEach(bean->{
            String leval = bean.get("leval")==null?"0":bean.get("leval").toString();
            bean.put("leval",leval.equals("1")?"已认证":"未认证");
        });
        return list;
    }

    @Override
    public Map<String,Object> followTeacher(Long userId,Long teacherId){
        Map<String,Object> result = Maps.newHashMap();
        TFollowTeacher followTeacher = new TFollowTeacher().selectOne("teacherId={0}  and userId={1}",teacherId,userId);
        if(followTeacher!=null){
            if(followTeacher.getStatus()!=null && followTeacher.getStatus()==1){
                followTeacher.setUpdateTime(new Date());
                followTeacher.setStatus(0);
                followTeacher.updateById();
            }
            result.put("success",true);
            result.put("message","已经关注该老师");
        } else  {
            followTeacher = new TFollowTeacher();
            followTeacher.setCreateTime(new Date());
            followTeacher.setStatus(0);
            followTeacher.setUserId(userId);
            followTeacher.setTeacherId(teacherId);
            followTeacher.insert();
            result.put("success",true);
            result.put("message","关注成功");
        }
       return result;
    }
    public Map<String,Object> unFollowTeacher(Long userId,Long teacherId){
        Map<String,Object> result = Maps.newHashMap();
        TFollowTeacher followTeacher = new TFollowTeacher().selectOne("teacherId={0} and status=0 and userId={1}",teacherId,userId);
        if(followTeacher!=null){
            if(followTeacher.getStatus()!=null && followTeacher.getStatus()==1){
                followTeacher.setUpdateTime(new Date());
                followTeacher.setStatus(1);
                followTeacher.updateById();
            }
            result.put("success",true);
            result.put("message","取消关注成功");
        } else  {
            result.put("success",false);
            result.put("message","取消关注失败");
        }
        return result;
    }

    @Override
    public TTeacher getByUserId(Long userId) {
        return teacherMapper.getByUserId(userId);
    }

    @Override
    public List<TTeacher> searchByName(Long organId, String name) {
        return teacherMapper.searchByName(organId,name);
    }


}
