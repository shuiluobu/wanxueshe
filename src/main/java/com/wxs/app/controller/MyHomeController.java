package com.wxs.app.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Maps;
import com.wxs.entity.comment.TDyimg;
import com.wxs.entity.comment.TDynamicmsg;
import com.wxs.entity.customer.TFollowUser;
import com.wxs.entity.customer.TStudent;
import com.wxs.service.comment.ITDynamicmsgService;
import com.wxs.service.customer.ITFrontUserService;
import com.wxs.service.customer.ITParentService;
import com.wxs.service.customer.impl.TStudentServiceImpl;
import com.wxs.service.message.ITRemindMessageService;
import com.wxs.util.Result;
import net.bytebuddy.asm.Advice;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.wxs.core.util.BaseUtil;
import org.wxs.core.util.OsppyUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/24 0024.
 * 个人首页
 */
@RestController
@RequestMapping("app/homePage")
public class MyHomeController extends BaseWxController {


    @RequestMapping(value = "/myRemind")
    public Result myRemind(@RequestParam(value = "sessionId", required = true) String sessionId) {
        //我的提醒
        Long userId = 1L; //之后需要从session中获取
        return Result.of(remindMessageService.getRemindMsgByFromUid(userId));
    }

    @RequestMapping(value = "/myFriend")
    public Result myFriend(@RequestParam(value = "sessionId", required = true) String sessionId) {
        //我的好友列表
        Long userId = 1L; //之后需要从session中获取
        return Result.of(followUserService.getMyFriendInfos(userId));
    }

    /**
     * 我的屏蔽
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/myShield")
    public Result myShield(@RequestParam(value = "sessionId", required = true) String sessionId) {
        //我的好友列表
        Long userId = 1L; //之后需要从session中获取
        return Result.of(followUserService.getMyShieldInfos(userId));
    }

    @RequestMapping(value = "/myStudents")
    public Result myStudents(@RequestParam(value = "sessionId", required = true) String sessionId) {
        //我的学员
        Long userId = 0L;
        return Result.of(studentService.getStudentOfUser(userId));
    }

    @RequestMapping(value = "/myClassWorks")
    public Result myClassWorks(@RequestParam(value = "sessionId", required = true) String sessionId) {
        //我的学员
        Long userId = 0L;
        return Result.of(classWorkService.getMyClassWorks(userId));
    }



    @RequestMapping(value = "/myGrowth")
    public Result myGrowth(@RequestParam(value = "sessionId", required = true) String sessionId,
                           @RequestParam(value = "studentIds", required = false) List<Long> studentIds) throws IOException {
        Long userId = 1L; //登录人ID
        if (studentIds == null) {
            EntityWrapper wrapper = new EntityWrapper();
            wrapper.eq("userId", userId);
            List<TStudent> students = studentService.selectList(wrapper);
            students.stream().forEach(student -> {
                studentIds.add(student.getId());
            });
        }
        return Result.of(dynamicmsgService.getMyStudentDynamicmList(studentIds));
    }

    @RequestMapping(value = "/myFollowDynamic")
    public Result myFollowDynamic(@RequestParam(value = "sessionId", required = true) String sessionId) throws IOException {
        Long userId = 1L; //登录人ID
        return Result.of(dynamicmsgService.getFollowDynamicmList(userId));
    }


    @RequestMapping(value = "/saveMyGrowth")
    public Result saveMyGrowth(@RequestParam(value = "sessionId", required = true) String sessionId,
                               @RequestParam MultipartFile[] imageOrVideos,
                               HttpServletRequest request) throws IOException {
        //保存我的作业
        Long userId = 0L;
        String mediaType = request.getParameter("mediaType"); //文件类型，IMG 图片，VIDEO 小视频
        List<String> mediaUrls = getImageOrVideoUrls(imageOrVideos);
        String content = request.getParameter("content");
        Integer power = Integer.parseInt(request.getParameter("power")); //是否公开
        Long studentId = Long.parseLong(request.getParameter("studentId"));
        Long workId = Long.parseLong(request.getParameter("workId"));
        TDynamicmsg dynamic = new TDynamicmsg();
        dynamic.setPower(power); //权限
        dynamic.setContent(content);
        dynamic.setStudentId(studentId);
        dynamic.setUserId(userId); //用户
        return Result.of(studentService.saveMygrowth(mediaUrls, mediaType, dynamic, workId));
    }

    @RequestMapping(value = "/sendComment")
    public Result sendComment(@RequestParam(value = "sessionId", required = true) String sessionId,
                              @RequestParam String content, @RequestParam Long dynamicId) {
        //我的学员
        Long userId = 0L;
        return Result.of(dynamicmsgService.saveComment(userId, dynamicId, content));
    }

    @RequestMapping(value = "/saveStudent")
    public Result saveStudent(@RequestParam(value = "sessionId", required = true) String sessionId,
                              @RequestParam(required = false, defaultValue = "") MultipartFile file,
                              @RequestParam(required = true, value = "studentName", defaultValue = "") String studentName,
                              @RequestParam(required = true, value = "sex", defaultValue = "0") int sex) {
        try {
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.indexOf(".") + 1);
            String headImgUrl = imgUploadPath + "userStudentImg/" + BaseUtil.uuid() + "." + suffix;
            File newFile = new File(headImgUrl);
            if (!newFile.exists() || !newFile.isDirectory()) {
                newFile.mkdirs();//会创建所有的目录
            }
            file.transferTo(new File(headImgUrl));
            //我的学员
            Long userId = 0L;
            TStudent student = new TStudent();
            student.setRealName(studentName);
            student.setHeadImg(headImgUrl);
            student.setSex(sex);
            student.setUserId(userId);
            return Result.of(studentService.saveStudent(student));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("保存失败");
    }

    @RequestMapping(value = "/delStudentByUserId")
    public Result delStudentByUserId(@RequestParam(value = "sessionId", required = true) String sessionId,
                                     @RequestParam(required = true, value = "studentId", defaultValue = "") Long studentId) {
        return Result.of(studentService.delStudent(studentId, userId));
    }

    @RequestMapping(value = "/editMyself")
    public Result editMyself(@RequestParam(value = "sessionId", required = true) String sessionId,
                             @RequestParam(required = false, value = "nickName", defaultValue = "") String nickName,
                             @RequestParam(required = false, defaultValue = "") MultipartFile file,
                             @RequestParam(required = true, value = "sex", defaultValue = "0") int sex,
                             @RequestParam(required = true, value = "mobilePhone", defaultValue = "") String mobilePhone) {
        //我的学员
        Long userId = 0L;

        try {
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.indexOf(".") + 1);
            String headImgUrl = imgUploadPath + "userLogoImg/" + userId + file.getName() + "." + suffix;
            File newFile = new File(headImgUrl);
            if (!newFile.exists() || !newFile.isDirectory()) {
                newFile.mkdirs();//会创建所有的目录
            }
            file.transferTo(new File(headImgUrl));
            return Result.of(frontUserService.editUserInfoByMySelf(userId, nickName, headImgUrl, sex, mobilePhone));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.error("上传头像出错了");
    }


    @RequestMapping(value = "/sendAddFriendReq")
    public Result sendAddFriendReq(@RequestParam(value = "sessionId", required = true) String sessionId,
                                   @RequestParam(required = true, value = "friendId", defaultValue = "") Long friendId) {

        //添加好友的请求
        Long userId = 1L;
        followUserService.sendAddFriendReq(userId, friendId);
        return Result.of("发送请求成功");
    }

    @RequestMapping(value = "/addFriend")
    public Result addFriend(@RequestParam(value = "sessionId", required = true) String sessionId,
                            @RequestParam(required = true, value = "friendId", defaultValue = "") Long friendId,
                            @RequestParam(required = true, value = "isAgree", defaultValue = "") Integer isAgree) {
        Long userId = 1L;
        if (isAgree == 1) {
            //表示同意
            return Result.of(followUserService.addUserFriend(userId, friendId));
        } else {
            //表示不同意
        }
        //同意请求后，添加好友
        return null;
    }

    @RequestMapping(value = "/delFriend")
    public Result delFriend(@RequestParam(value = "sessionId", required = true) String sessionId,
                            @RequestParam(required = true, value = "friendId", defaultValue = "") Long friendId) {
        Long userId = 1L;
        //删除好友
        return Result.of(followUserService.updateFollowUser(userId, friendId, "30"));
    }

    @RequestMapping(value = "/shieldFriend")
    public Result shieldFriend(@RequestParam(value = "sessionId", required = true) String sessionId,
                               @RequestParam(required = true, value = "friendId", defaultValue = "") Long friendId) {
        Long userId = 1L;
        //屏蔽好友
        return Result.of(followUserService.updateFollowUser(userId, friendId, "20"));
    }


}
