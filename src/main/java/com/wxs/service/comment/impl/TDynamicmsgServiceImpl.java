package com.wxs.service.comment.impl;

import com.google.common.collect.ImmutableMap;
import com.wxs.entity.comment.TDyimg;
import com.wxs.entity.comment.TDynamicmsg;
import com.wxs.entity.comment.TDyvideo;
import com.wxs.entity.comment.TLike;
import com.wxs.entity.customer.TFrontUser;
import com.wxs.mapper.comment.TDyimgMapper;
import com.wxs.mapper.comment.TDynamicmsgMapper;
import com.wxs.mapper.comment.TDyvideoMapper;
import com.wxs.mapper.comment.TLikeMapper;
import com.wxs.service.comment.ITDynamicmsgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
public class TDynamicmsgServiceImpl extends ServiceImpl<TDynamicmsgMapper, TDynamicmsg> implements ITDynamicmsgService {
    @Autowired
    private TDynamicmsgMapper dynamicmsgMapper;
    @Autowired
    private TDyimgMapper imgMapper;
    @Autowired
    private TDyvideoMapper videMapper;
    @Autowired
    private TLikeMapper likeMapper;

    public List<Map<String, Object>> getDynamicmsgListByUserId(Long userId) {
        TFrontUser user = new TFrontUser();
        user.setUserName("张老师");//后期需要改为查询数据库
        List<Map<String, Object>> dynamicMsgs = dynamicmsgMapper.getDynamicmsgByParam(ImmutableMap.of("userId", user.getId()));
        dynamicMsgs.stream().forEach(dyn -> {
            dyn.put("nickName", user.getUserName());
            String imgUrlIds = dyn.get("imgUrlIds") == null ? "0" : dyn.get("imgUrlIds").toString();
            List<TDyimg> dyimgs = imgMapper.selectBatchIds(Arrays.asList(StringUtils.split(imgUrlIds, ",")));
            dyn.put("dyImgs", dyimgs); //图集
            if (dyn.get("videoId") != null) {
                TDyvideo dyvideo = videMapper.selectById(Long.parseLong(dyn.get("videoId").toString()));
                dyn.put("dyVideo", dyvideo); //视频
            }
            List<TLike> likes = likeMapper.selectByMap(ImmutableMap.of("dynamicId", dyn.get("id").toString()));
            dyn.put("likes", likes);
        });
        return dynamicMsgs;
    }
}
