package com.wxs.app.controller;


import com.wxs.entity.comment.TDynamic;
import com.wxs.entity.task.TStudentWork;
import com.wxs.util.Result;
import freemarker.template.utility.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
                           @RequestParam(value = "mediaUrls", required = true)  List<String> mediaUrls,
                           HttpServletRequest request) throws IOException {
        //保存我的作业

        Long userId = 1L;
        String mediaType = request.getParameter("mediaType"); //文件类型，IMG 图片，VIDEO 小视频

        String content = request.getParameter("content");
        Integer power = Integer.parseInt(request.getParameter("power")); //是否公开
        Long sWorkId = Long.parseLong(request.getParameter("sworkId"));
        TDynamic dynamic = new TDynamic();
        dynamic.setPower(power); //权限
        dynamic.setContent(content);
        dynamic.setUserId(userId); //用户
        return Result.of(classWorkService.saveStudentWork(mediaUrls, mediaType, dynamic, sWorkId));
    }

    @RequestMapping(value = "/submitedWorkDetail")
    public Result submitedWorkDetail(@RequestParam(value = "sessionId", required = true) String sessionId,
                               @RequestParam(value = "dynamicId", required = false) String dynamicId

    ) {
        //我的学员
        Long userId = 1L;
        List<Map<String,Object>> list = null;
        if(dynamicId!=null){
            list =   dynamicService.getDynamicDetailOfWork(userId,dynamicId);
        } else {
            List<TStudentWork>  studentWorks = new TStudentWork().selectList("userId={0}",userId);
            List<String> dynamicIdList = new ArrayList<>();
            studentWorks.stream().forEach(studentWork ->{
                dynamicIdList.add(studentWork.getDynamicId().toString());
            });
            list =   dynamicService.getDynamicDetailOfWork(userId, StringUtils.join(dynamicIdList,","));
        }
        return Result.of(list);
    }

}
