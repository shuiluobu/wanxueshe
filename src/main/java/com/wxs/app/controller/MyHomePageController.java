package com.wxs.app.controller;

import com.wxs.entity.comment.TDyimg;
import com.wxs.entity.comment.TDynamicmsg;
import com.wxs.service.comment.ITDynamicmsgService;
import com.wxs.service.customer.ITFrontUserService;
import com.wxs.service.customer.ITParentService;
import com.wxs.service.customer.impl.TStudentServiceImpl;
import com.wxs.service.message.ITRemindMessageService;
import com.wxs.util.Result;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/24 0024.
 * 个人首页
 */
@RestController
@RequestMapping("app/homePage")
public class MyHomePageController extends BaseWxController{
    @RequestMapping(value = "/follow")
    public Result follow(@RequestParam(value = "sessionId" ,required = true) String sessionId) {
        Long userId = 1L; //之后需要从session中获取
        return Result.of(studentService.getMyFollow(userId));
    }

    @RequestMapping(value = "/myCourse")
    public Result myCourse(@RequestParam(value = "sessionId" ,required = true) String sessionId) {
        //我的课程
        Long userId = 1L; //之后需要从session中获取
        return Result.of(studentService.getMyCourses(userId));
    }

    @RequestMapping(value = "/myRemind")
    public Result myRemind(@RequestParam(value = "sessionId" ,required = true) String sessionId) {
        //我的提醒
        Long userId = 1L; //之后需要从session中获取
        return Result.of(remindMessageService.getRemindMsgByFromUid(userId));
    }

    @RequestMapping(value = "/myFriend")
    public Result myFriend(@RequestParam(value = "sessionId" ,required = true) String sessionId) {
        //我的好友列表
        Long userId = 1L; //之后需要从session中获取
        return Result.of(frontUserService.getUserFriends(userId));
    }

    @RequestMapping(value = "/myDynamic")
    public Result myDynamic(@RequestParam(value = "sessionId" ,required = true) String sessionId, @RequestParam Long studentId) {
        //我的动态记录
        Long userId = 1L; //之后需要从session中获取
        return Result.of(dynamicmsgService.getDynamicmListByMySelfId(userId, studentId));
    }

    @RequestMapping(value = "/myStudents")
    public Result myStudents(@RequestParam(value = "sessionId" ,required = true) String sessionId) {
        //我的学员
        Long parentId = 0L;
        return Result.of(parentService.getStudentByParent(parentId));
    }

    @RequestMapping(value = "/saveMygrowth")
    public Result saveMygrowth(@RequestParam(value = "sessionId" ,required = true) String sessionId,
                               @RequestParam MultipartFile[] imageOrVideos,
                               HttpServletRequest request) throws IOException {
        //保存我的作业
        List<TDyimg> dyimgs = new ArrayList<>();
        Long userId = 0L;
        for (MultipartFile imageOrVideo : imageOrVideos) {
            String originalFilename = imageOrVideo.getOriginalFilename();
            String newFileName = null;
            String pic_path = request.getSession().getServletContext().getRealPath("/upload/callRing");
            //新图片路径
            File targetFile = new File(pic_path, newFileName);
            //内存数据读入磁盘
            imageOrVideo.transferTo(targetFile);

            TDyimg tDyimg = new TDyimg();
            tDyimg.setCreateTime(new Date());
            tDyimg.setOriginalImgUrl(pic_path + "/" + newFileName);
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
        return Result.of(studentService.saveMygrowth(dyimgs, dynamic, workId));
    }

    @RequestMapping(value = "/sendComment")
    public Result sendComment(@RequestParam(value = "sessionId" ,required = true) String sessionId,
                              @RequestParam String content, @RequestParam Long dynamicId) {
        //我的学员
        Long userId = 0L;
        return Result.of(dynamicmsgService.saveComment(userId, dynamicId, content));
    }


}
