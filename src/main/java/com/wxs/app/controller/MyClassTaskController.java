package com.wxs.app.controller;

import com.wxs.entity.comment.TDyimg;
import com.wxs.entity.comment.TDynamicmsg;
import com.wxs.entity.customer.TStudent;
import com.wxs.entity.task.TStudentTask;
import com.wxs.service.task.ITClassTaskService;
import com.wxs.service.task.ITStudentTaskService;
import com.wxs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.wxs.core.util.BaseUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/24 0024.
 * 我的课堂作业
 */
@RestController
@RequestMapping("app/mytask")
public class MyClassTaskController {
    @Autowired
    private ITStudentTaskService studentTaskService;
    @Autowired
    private ITClassTaskService classTaskService;

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

    @RequestMapping(value = "/saveWork")
    public Result saveWork(@RequestParam MultipartFile[] imageOrVideos,HttpServletRequest request) throws IOException {
        //保存我的作业
        List<TDyimg> dyimgs=new ArrayList<>();
        Long userId =0L;
        for(MultipartFile imageOrVideo:imageOrVideos){
            String originalFilename=imageOrVideo.getOriginalFilename();
            String newFileName=null;
            String pic_path = request.getSession().getServletContext().getRealPath("/upload/callRing");
            //新图片路径
            File targetFile = new File(pic_path, newFileName);
            //内存数据读入磁盘
            imageOrVideo.transferTo(targetFile);

            TDyimg tDyimg = new TDyimg();
            tDyimg.setCreateTime(new Date());
            tDyimg.setOriginalImgUrl(pic_path+"/"+newFileName);
            tDyimg.setStatus(0);
            dyimgs.add(tDyimg); //图片list
        }
        String content = request.getParameter("content");
        Integer power = Integer.parseInt(request.getParameter("power")); //是否公开
        Long studentId = Long.parseLong(request.getParameter("studentId"));
        Long workId = Long.parseLong(request.getParameter("workId"));
        TDynamicmsg dynamic = new TDynamicmsg();
        dynamic.setPower(power); //权限
        dynamic.setContent(content);
        dynamic.setStudentId(studentId);
        dynamic.setUserId(userId); //用户
        return Result.of(classTaskService.saveStudentWork(dyimgs,dynamic,workId));
    }
}
