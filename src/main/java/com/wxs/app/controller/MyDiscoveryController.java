package com.wxs.app.controller;

import com.wxs.util.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/27 0027.
 * 我的发现
 */
@RestController
@RequestMapping("app/myDiscovery")
public class MyDiscoveryController {
    @RequestMapping(value = "/view")
    public Result view() {

      return null;
    }
}
