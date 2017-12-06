package com.wxs.service.customer.impl;

import com.wxs.cache.ICache;
import com.wxs.entity.customer.TFrontUser;
import com.wxs.entity.customer.TWxUser;
import com.wxs.mapper.customer.TFrontUserMapper;
import com.wxs.service.customer.ITFrontUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wxs.core.util.BaseUtil;

import java.util.Date;
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

    @Override
    @Transactional
    public TWxUser getUserByWx(String wxUserInfo, String sessionId) {
        Map<String, Object> userInfo = BaseUtil.parseJson(wxUserInfo, Map.class);
        String unionId = userInfo.get("unionId").toString();
        TWxUser wxUser = (TWxUser) cache.getCache(sessionId);
        if (wxUser == null) {
            wxUser = new TWxUser().selectById(unionId);
            if (wxUser != null) {
                cache.putCache(sessionId, wxUser);
            } else {
                TFrontUser user = new TFrontUser();
                user.setStatus(0);
                user.setCity(userInfo.get("city").toString());
                user.setNickName(userInfo.get("nickName").toString());
                user.setCreateTime(new Date());
                this.insert(user); //保存用户
                wxUser.setOpenId(userInfo.get("openId").toString());
                wxUser.setUnionID(unionId);
                wxUser.setUserId(user.getId());
                wxUser.setHeadImg(userInfo.get("avatarUrl").toString()); //头像
                wxUser.setGender(userInfo.get("gender").toString()); //性别
                wxUser.setStatus(0);
                wxUser.insert();
                cache.putCache(sessionId, wxUser);
            }
        }
        return wxUser;
    }
}
