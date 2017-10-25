package com.wxs.service.wx.impl;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Administrator on 2017/10/25 0025.
 */
public class WeiXinServiceImpl {
    @Value("${wxapp.sessionHost}")
    private String sessionHost;

    @Value("${wxapp.appId}")
    private String appId;

    @Value("${wxapp.secret}")
    private String secret;

    @Value("${wxapp.grantType}")
    private String grantType;
}
