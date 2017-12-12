package com.wxs.app.service;

import com.wxs.app.bean.WxAuth;
import com.wxs.cache.ICache;
import com.wxs.entity.customer.TFrontUser;
import com.wxs.entity.customer.TWxUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxs.core.util.BaseUtil;
import org.wxs.core.util.HttpRequest;

import java.util.Map;

@Service
public class WxService {
    @Autowired
    private WxAuth wxAuth;
    @Autowired
    private ICache cache;

    /**
     * 根据小程序登录返回的code获取openid和session_key
     *
     * @param wxCode
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getWxSession(String wxCode) {
        StringBuffer sb = new StringBuffer();
        sb.append("appid=").append(wxAuth.getAppId());
        sb.append("&secret=").append(wxAuth.getSecret());
        sb.append("&js_code=").append(wxCode);
        sb.append("&grant_type=").append(wxAuth.getGrantType());
        String res = HttpRequest.sendGet(wxAuth.getSessionHost(), sb.toString());
        if (res == null || res.equals("")) {
            return null;
        }
        return BaseUtil.parseJson(res, Map.class);
    }

    /**
     * 缓存微信openId和session_key
     *
     * @param wxOpenId     微信用户唯一标识
     * @param wxSessionKey 微信服务器会话密钥
     * @param expires      会话有效期, 以秒为单位, 例如2592000代表会话有效期为30天
     * @return
     */
    public String create3rdSession(String wxOpenId, String wxSessionKey, int expires) {
        String thirdSessionKey = RandomStringUtils.randomAlphanumeric(64);
        StringBuffer sb = new StringBuffer();
        sb.append(wxSessionKey).append("#").append(wxOpenId);
        cache.putCache(thirdSessionKey, sb.toString(), expires);
        return thirdSessionKey;
    }

    @Autowired
    public TWxUser session2User(String sessionId) {
        Object wxSessionObj = cache.getCache(sessionId);
        if (wxSessionObj != null) {
            TWxUser user = cache.getCache(StringUtils.split((String) wxSessionObj, "#")[0]);
            return user;
        } else {
            //设置的session失效了，需要重新授权
            return null;
        }

    }
}
