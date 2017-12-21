package com.wxs.service.customer.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Maps;
import com.wxs.cache.ICache;
import com.wxs.entity.customer.TFriend;
import com.wxs.entity.customer.TFrontUser;
import com.wxs.entity.customer.TParent;
import com.wxs.entity.customer.TWxUser;
import com.wxs.mapper.course.TStudentClassMapper;
import com.wxs.mapper.customer.TFriendMapper;
import com.wxs.mapper.customer.TFrontUserMapper;
import com.wxs.mapper.customer.TStudentMapper;
import com.wxs.service.customer.ITFrontUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wxs.core.util.BaseUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户登录表 服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@Service
public class TFrontUserServiceImpl extends ServiceImpl<TFrontUserMapper, TFrontUser> implements ITFrontUserService {

    @Autowired
    private ICache cache;

    @Value("${wxuser.key}")
    private String wxUserKey;


    @Override
    @Transactional
    public TWxUser saveUserByWx(String wxUserInfo, String sessionKey) {
        Map<String, Object> userInfo = BaseUtil.parseJson(wxUserInfo, Map.class);
        System.out.println(wxUserInfo);
        String unionId = userInfo.get("openId").toString();
        TWxUser wxUser = (TWxUser) cache.getCache(wxUserKey + unionId);
        if (wxUser == null) {
            wxUser = new TWxUser().selectById(unionId);
            if (wxUser != null) {
                cache.putCache(wxUserKey + unionId, wxUser);
            } else {
                TFrontUser user = new TFrontUser();
                user.setStatus(0);
                user.setCity(userInfo.get("city").toString());
                user.setNickName(userInfo.get("nickName").toString());
                user.setCreateTime(new Date());
                this.insert(user); //保存用户
                wxUser = new TWxUser();
                wxUser.setOpenId(userInfo.get("openId").toString());
                wxUser.setUnionId(unionId);
                wxUser.setUserId(user.getId());
                wxUser.setHeadImg(userInfo.get("avatarUrl").toString()); //头像
                wxUser.setGender(userInfo.get("gender").toString()); //性别
                wxUser.setStatus(0);
                wxUser.insert();
                cache.putCache(wxUserKey + unionId, wxUser);
            }
        }
        return wxUser;
    }



    @Override
    public Map<String, Object> editUserInfoByMySelf(Long userId, String nickName, String headImg, int gener, String mobilePhone) {
        Map<String, Object> result = Maps.newHashMap();
        TFrontUser user = baseMapper.selectById(userId);
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("nickName", nickName);
        wrapper.eq("mobilePhone", mobilePhone);
        wrapper.eq("headImg", headImg);
        baseMapper.update(user, wrapper);
        result.put("success", true);
        result.put("message", "保存成功");
        return result;
    }

}
