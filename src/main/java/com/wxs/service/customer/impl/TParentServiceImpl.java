package com.wxs.service.customer.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wxs.entity.customer.TFollowUser;
import com.wxs.entity.customer.TParent;
import com.wxs.entity.customer.TStudent;
import com.wxs.mapper.course.TStudentClassMapper;
import com.wxs.mapper.customer.TFollowParentMapper;
import com.wxs.mapper.customer.TParentMapper;
import com.wxs.mapper.customer.TStudentMapper;
import com.wxs.service.customer.ITParentService;
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
public class TParentServiceImpl extends ServiceImpl<TParentMapper, TParent> implements ITParentService {

    @Autowired
    private TStudentMapper studentMapper;

    @Autowired
    private TStudentClassMapper studentClassMapper;

    @Autowired
    private TFollowParentMapper followParentMapper;

    @Override
    public List<Map<String,Object>> getStudentByParent(Long parentId){
        EntityWrapper<TStudent> wrapper = new EntityWrapper<>();
        wrapper.eq("parentId",parentId);
        List<TStudent> students =  studentMapper.selectList(wrapper);
        List<Map<String,Object>> studentMapList = new ArrayList<>();
        students.stream().forEach(tStudent -> {
            Map<String,Object> studentMap = Maps.newHashMap();
            studentMap.put("realName",tStudent.getRealName());
            studentMap.put("parentType",tStudent.getParentType());
            studentMap.put("courseCount",studentClassMapper.getStudentCourseCount(tStudent.getId()));
            studentMapList.add(studentMap);
        });
        return studentMapList;
    }

    @Override
    public List<Map<String, Object>> getFllowUsers(List<Long> userIds,Long loginUserId) {
       List<Map<String,Object>> resultList = Lists.newArrayList();

        userIds.stream().forEach(userId -> {
            Map<String,Object> map = Maps.newHashMap();
            TParent parent = new TParent().selectOne(new EntityWrapper().where("userId={0}",userId));
            map.put("userId",userId);
            map.put("realName",parent.getRealName());
            map.put("studentCount",studentMapper.getParentStudentCount(parent.getId()));
            map.put("courseCount",studentClassMapper.getParentCourseCount(parent.getId()));
            Integer isFriednCount = new TFollowUser().selectCount("userId={0} and fuserId={1}",loginUserId,userId);
            if(isFriednCount>0){
                map.put("isFriend",1);
            } else {
                map.put("isFriend",0); //是否已经加好友
            }
            resultList.add(map);
        });
        return resultList;

    }
}
