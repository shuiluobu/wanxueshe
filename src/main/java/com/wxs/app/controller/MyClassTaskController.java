package com.wxs.app.controller;

import com.wxs.service.task.ITStudentTaskService;
import com.wxs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/11/24 0024.
 * 我的课堂作业
 */
@RestController
@RequestMapping("app/mytask")
public class MyClassTaskController {
    @Autowired
    private ITStudentTaskService studentTaskService;

    /**
     * 获取下发给我的作业详情
     * @param studentTaskId
     * @return
     */
    @RequestMapping(value = "/view/{studentTaskId}")
    public Result view(@PathVariable("studentTaskId") Long studentTaskId) {
        //展示课时详情
        return Result.of(studentTaskService.getStudentTaskDetail(studentTaskId));
    }
}
