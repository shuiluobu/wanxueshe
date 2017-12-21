package com.wxs.service.customer.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.entity.customer.TFriend;
import com.wxs.entity.customer.TParent;
import com.wxs.mapper.course.TStudentClassMapper;
import com.wxs.mapper.customer.TFriendMapper;
import com.wxs.mapper.customer.TStudentMapper;
import com.wxs.service.customer.ITFriendService;
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
 * @since 2017-12-06
 */
@Service
public class TFriendServiceImpl extends ServiceImpl<TFriendMapper, TFriend> implements ITFriendService {
    @Autowired
    private TStudentClassMapper studentClassMapper;
    @Autowired
    private TStudentMapper studentMapper;
    @Autowired
    private TFriendMapper friendMapper;

    @Override
    public List<Map<String, Object>> getUserFriends(Long userId) {
        List<Map<String,Object>>  list = friendMapper.selectMaps(new EntityWrapper().where("mUserId={0}",userId).orderBy("createTime desc"));

        list.stream().forEach(tFriend -> {
            Long fUserId = Long.parseLong(tFriend.get("fUserId").toString());
            TParent parent = new TParent().selectOne(new EntityWrapper().where("userId={0}",fUserId));
            tFriend.put("studentCount",studentMapper.getParentStudentCount(parent.getId()));
            tFriend.put("courseCount",studentClassMapper.getParentCourseCount(parent.getId()));
        });
        return list;
    }

    @Override
    public Map<String, Object> addFriend(Long userId, Long toUserId) {
        return null;
    }
}
