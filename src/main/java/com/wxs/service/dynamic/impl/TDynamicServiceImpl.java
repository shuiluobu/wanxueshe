package com.wxs.service.dynamic.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wxs.entity.comment.*;
import com.wxs.entity.customer.TFollowUser;
import com.wxs.entity.customer.TStudent;
import com.wxs.entity.organ.TOrganization;
import com.wxs.mapper.dynamic.*;
import com.wxs.mapper.customer.TFollowTeacherMapper;
import com.wxs.mapper.customer.TStudentMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.service.common.IDictionaryService;
import com.wxs.service.customer.ITFollowUserService;
import com.wxs.service.dynamic.ITDynamicService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@Service
public class TDynamicServiceImpl extends ServiceImpl<TDynamicMapper, TDynamic> implements ITDynamicService {
    @Autowired
    private TDynamicMapper dynamicMapper;
    @Autowired
    private TDynamicImgMapper dynamicImgMapper;
    @Autowired
    private TDynamicVideoMapper dynamicVideoMapper;
    @Autowired
    private TLikeMapper likeMapper;
    @Autowired
    private TDynamicCommentMapper dynamicCommentMapper;
    @Autowired
    public TStudentMapper studentMapper;
    @Autowired
    public IDictionaryService dictionaryService;
    @Autowired
    public TFollowTeacherMapper followTeacherMapper;
    @Autowired
    public ITFollowUserService followUserService;

    @Override
    public List<Map<String, Object>> getDynamicmListByCourseId(Long loginUserId, Long couserId) {
        String power = "0,1"; //权限管理
        List<Map<String, Object>> dynamicMsgs = dynamicMapper.getDynamicmsgByParam(ImmutableMap.of("courseCateId", couserId, "power", power));
        return buildDynamicList(loginUserId, dynamicMsgs);
    }

    @Override
    public List<Map<String, Object>> getDynamicmListByTeacherId(Long loginUserId, Long teacherUserId) {
        String power = "0,1"; //权限管理
        List<Map<String, Object>> dynamicMsgs = dynamicMapper.getDynamicmsgByParam(ImmutableMap.of("userId", teacherUserId, "power", power));
        return buildDynamicList(loginUserId, dynamicMsgs);
    }
    @Override
    public Map<String,Object> getNewestDynamicByOrganId(Long loginUserId,Long organId){
        List<Map<String,Object>> dynamics= dynamicMapper.getNewestDynamicmByOrganId(organId);
        return buildDynamicList(loginUserId, dynamics).get(0);
    }

    /**
     * 我自己的学生的动态
     *
     * @param studentIds
     * @return
     */
    @Override
    public List<Map<String, Object>> getMyStudentDynamicmList(List<Long> studentIds) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("studentIds", studentIds);
        List<Map<String, Object>> dynamicMsgs = dynamicMapper.getDynamicmsgByParam(param);
        return buildDynamicList(null, dynamicMsgs);
    }

    /**
     * 别人学生的动态
     * @param loginUserId
     * @param userId
     * @return
     */
    @Override
    public List<Map<String, Object>> getOtherParentUserDynamicmList(Long loginUserId,Long userId) {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("userId", userId);
        List<Long> studentIds = Lists.newArrayList();
        List<TStudent> students = studentMapper.selectList(wrapper);
        students.stream().forEach(student -> {
            studentIds.add(student.getId());
        });
        Map<String, Object> param = Maps.newHashMap();
        param.put("studentIds", studentIds);
        List<Map<String, Object>> dynamicMsgs = dynamicMapper.getDynamicmsgByParam(param);
        return buildDynamicList(loginUserId, dynamicMsgs);
    }

    /**
     * 我关注的动态，主要是我关注老师的动态
     * @param loginUserId
     * @return
     */
    @Override
    public List<Map<String,Object>> getFollowDynamicmList(Long loginUserId){
        //我关注的老师
        List<Long> userIds = followTeacherMapper.getFllowUserIdsOfTeacherId(loginUserId);
        Map<String, Object> param = Maps.newHashMap();
        param.put("userIds", userIds);
        param.put("power","0,1");
        List<Map<String, Object>> dynamicMsgs = dynamicMapper.getDynamicmsgByParam(param);
        //我关注的好友
        userIds = followUserService.geFriendIdsByUserId(loginUserId);
        param.put("userIds",userIds);
        dynamicMsgs.addAll(dynamicMapper.getDynamicmsgByParam(param));
        return buildDynamicList(loginUserId, dynamicMsgs);
    }
    @Override
    public List<Map<String,Object>> getNearByDynamicms(Long loginUserId,double latitude,double longitude){
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("userId", loginUserId);
        List<Long> studentIds = Lists.newArrayList();
        List<TStudent> students = studentMapper.selectList(wrapper);
        students.stream().forEach(student -> {
            studentIds.add(student.getId());
        });
        List<Map<String, Object>> dynamicMsgs = dynamicMapper.getNearByDynamicms(studentIds,latitude,longitude,5); //5km之内的
        return buildDynamicList(null, dynamicMsgs);
    }

    /**
     * 我的好友的动态
     *
     * @param loginUserId
     * @return
     */
    @Override
    public List<Map<String, Object>> getMyFriendDynamicmList(Long loginUserId) {
        List<Map<String, Object>> dynamicMsgs = dynamicMapper.getFriendDynamicmsgByUserId(loginUserId);
        return buildDynamicList(loginUserId, dynamicMsgs);
    }

    public List<Map<String, Object>> buildDynamicList(Long loginUserId, List<Map<String, Object>> dynamicMsgs) {
        List<Map<String, Object>> dynamiList = Lists.newArrayList();
        dynamicMsgs.stream().forEach(dyn -> {
            String studentName = dyn.get("realName")==null?"":dyn.get("realName").toString();
            String dynamicType = dyn.get("dynamicType")==null?"":dyn.get("dynamicType").toString();
            dyn.put("dynamicType",studentName + dictionaryService.getDynamicType().get(dynamicType)); //后去从字段中取值
            if (loginUserId != null && loginUserId != 0) {
                Long dynUserId = Long.parseLong(dyn.get("userId").toString());
                String power = dyn.get("power").toString(); //动态权限0：公开，1：仅好友 2：仅自己
                EntityWrapper wrapper = new EntityWrapper();
                wrapper.eq("userId", loginUserId); //登录人
                wrapper.eq("fuserId", dynUserId); //关系人
                TFollowUser followUser = new TFollowUser().selectOne(wrapper);
                switch (power) {
                    case "0":
                        if (followUser == null) {
                            dynamiList.add(dyn); //公开的
                        } else {
                            if (!followUser.getRelationType().equals("30")) {
                                //如果没把关系人屏蔽了
                                dynamiList.add(dyn);
                            }
                        }
                        break;
                    case "1":
                        // 仅朋友
                        if (followUser.getRelationType().equals("20")) {
                            //如果没把关系人屏蔽了
                            dynamiList.add(dyn);
                        }
                        break;
                    case "2":
                        //仅自己，别人都看不到
                        break;
                }
                if (followUser != null && StringUtils.isEmpty(followUser.getMemoName())) {
                    dyn.put("nickName", followUser.getMemoName()); //如果不为空，则取备注上的名称
                }
            } else {
                dynamiList.add(dyn);
            }
        });
        dynamiList.stream().forEach(dyn -> {
            buildOneDynamic(dyn);
        });
        return dynamiList;
    }
    @Override
    public Map<String,Object> buildOneDynamic(Map<String,Object> dyn){
        Long organId = Long.parseLong(dyn.get("organId").toString());
        Map<String,Object> organMap = Maps.newHashMap();
        organMap.put("organId",organId);
        organMap.put("organName",new TOrganization().selectById(organId).getOrganName());
        dyn.remove("organId");
        dyn.put("organ",organMap);
        Long dynamicId = Long.parseLong(dyn.get("id").toString());
        List<TDynamicImg> dyimgs = dynamicImgMapper.selectList(new EntityWrapper().eq("dynamicId", dynamicId));
        dyn.put("dyImgs", dyimgs); //图集
        TDynamicVideo dyvideo = new TDynamicVideo();
        dyvideo.setDynamicId(dynamicId);
        dyn.put("dyVideo", dynamicVideoMapper.selectOne(dyvideo)==null?"": dynamicVideoMapper.selectOne(dyvideo)); //视频
        List<TLike> likes = likeMapper.selectByMap(ImmutableMap.of("dynamicId", dynamicId));
        dyn.put("likes", likes);
        List<TDynamicComment> comments = dynamicCommentMapper.selectByMap(ImmutableMap.of("dynamicId", dynamicId));
        dyn.put("comments", comments);
        return dyn;
    }

    @Override
    public Boolean saveComment(Long userId,Long dynamicId,String content){
        TDynamicComment comment = new TDynamicComment();
        comment.setContent(content);
        comment.setDynamicId(dynamicId);
        comment.setCreateTime(new Date());
        comment.setFromUserId(userId);
        TDynamic dynamic = new TDynamic().selectById(dynamicId);
        comment.setToUserId(dynamic.getUserId());
        comment.setStatus(0);
        return  comment.insert();
    }
    @Override
    public Map<String,Object> queryDynamicOfWork(Long dynamicId){
        Map<String,Object> contentMap = Maps.newHashMap();
        TDynamic dynamic = new TDynamic().selectById(dynamicId);
        contentMap.put("text",dynamic.getContent());
        contentMap.put("images",dynamicImgMapper.getImgsByDynamicId(dynamicId));
        return contentMap;
    }
}
