package com.wxs.app.controller;

import com.google.common.collect.Lists;
import com.wxs.util.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
        List<Object> list = Lists.newArrayList();
        return Result.of(list);
    }
    @RequestMapping(value = "/nearInformation")
    public Result nearInformation(@RequestParam double latitude,@RequestParam double longitude){
        //根据经纬度查找附近的课程
        List<Object> list = Lists.newArrayList();
        return Result.of(list);
    }


}
