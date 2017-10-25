package com.wxs.app.controller;

import com.wxs.service.customer.ITTeacherService;
import com.wxs.util.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

/**
 * Created by Administrator on 2017/10/20 0020.
 */
@RestController
@RequestMapping("/app/teacher")
public class TeacherController {

    private ITTeacherService teacherService;

    @RequestMapping(value = "/{tid}")
    public Result teacherOne(@PathVariable("tid") Long tid){
        //老师基本信息
        return Result.of(teacherService.getTeacharInfoById(tid));
    }



}
