package com.wxs.app.controller;


import com.wxs.entity.comment.TDynamic;
import com.wxs.util.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/11/24 0024.
 * 我的课堂作业
 */
@RestController
@RequestMapping("app/mywork")
public class MyClassWorkController extends BaseWxController {

    /**
     * 获取下发给我的作业详情
     *
     * @param workId
     * @return
     */
    @RequestMapping(value = "/view/{workId}")
    public Result view(@RequestParam(value = "sessionId", required = true) String sessionId,
                       @PathVariable("workId") Long workId) {
        //展示作业详情
        Long userId =1L;
        return Result.of(classWorkService.getClassWorkOutline(workId,userId));
    }

    @RequestMapping(value = "/batchMyWorks")
    public Result batchMyWorks(@RequestParam(value = "sessionId", required = true) String sessionId,
                               @PathVariable("workId") Long workId) {
        //展示我的所有作业
        Long userId = 1L;
        return Result.of(classWorkService.getClassWorkInfosByUserId(userId));
    }


    @RequestMapping(value = "/saveWork")
    public Result saveWork(@RequestParam(value = "sessionId", required = true) String sessionId,
                          HttpServletRequest request) throws IOException {
        //保存我的作业

        Long userId = 0L;
        String mediaType = request.getParameter("mediaType"); //文件类型，IMG 图片，VIDEO 小视频
        List<String> mediaUrls = null;
        String content = request.getParameter("content");
        Integer power = Integer.parseInt(request.getParameter("power")); //是否公开
        Long studentId = Long.parseLong(request.getParameter("studentId"));
        Long workId = Long.parseLong(request.getParameter("workId"));
        TDynamic dynamic = new TDynamic();
        dynamic.setPower(power); //权限
        dynamic.setContent(content);
        dynamic.setStudentId(studentId);
        dynamic.setUserId(userId); //用户
        return Result.of(classWorkService.saveStudentWork(mediaUrls, mediaType, dynamic, workId));
    }
}
