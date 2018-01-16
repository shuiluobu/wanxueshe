package com.wxs.app.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Maps;
import com.wxs.entity.comment.TDynamic;
import com.wxs.entity.customer.TFrontUser;
import com.wxs.entity.customer.TStudent;
import com.wxs.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.wxs.core.util.BaseUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/24 0024.
 * 个人首页
 */
@RestController
@RequestMapping("app/myHome")
public class MyHomeController extends BaseWxController {
    @RequestMapping(value = "/view")
    public Result view(@RequestParam(value = "sessionId", required = true) String sessionId) {
        //我的提醒

        return Result.of(parentService.getParentOutline(null, userId));
    }


    @RequestMapping(value = "/reminds")
    public Result reminds(@RequestParam(value = "sessionId", required = true) String sessionId) {
        //我的提醒

        return Result.of(remindMessageService.getRemindMsgByFromUid(userId));
    }

    @RequestMapping(value = "/friends")
    public Result friends(@RequestParam(value = "sessionId", required = true) String sessionId) {
        //我的好友列表

        return Result.of(followUserService.getMyFriendInfos(userId));
    }

    /**
     * 我的屏蔽
     *
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/shields")
    public Result shields(@RequestParam(value = "sessionId", required = true) String sessionId) {
        //我的好友列表

        return Result.of(followUserService.getMyShieldInfos(userId));
    }

    @RequestMapping(value = "/students")
    public Result students(@RequestParam(value = "sessionId", required = true) String sessionId) {
        //我的学员
        Long userId = 1L;
        return Result.of(studentService.getStudentOfUser(userId));
    }

    @RequestMapping(value = "/getOneStudent")
    public Result getOneStudent(@RequestParam(value = "sessionId", required = true) String sessionId,
                                @RequestParam(value = "studentId", required = true) Long studentId) {
        //我的学员
        Long userId = 1L;
        return Result.of(studentService.getOneStudentInfoById(studentId));
    }

    @RequestMapping(value = "/getMySelfInfo")
    public Result getMySelfInfo(@RequestParam(value = "sessionId", required = true) String sessionId) {
        //我的学员
        Long userId = 1L;
        Map<String, Object> myselfMap = Maps.newHashMap();
        TFrontUser user = new TFrontUser().selectById(userId);
        myselfMap.put("userId", userId);
        myselfMap.put("sex", user.getSex() == null ? 1 : user.getSex());
        myselfMap.put("nickName", user.getNickName());
        myselfMap.put("headImg", user.getHeadImg());
        myselfMap.put("mobilePhone", user.getMobilePhone());
        return Result.of(myselfMap);
    }

    @RequestMapping(value = "/classWorks")
    public Result classWorks(@RequestParam(value = "sessionId", required = true) String sessionId) {
        //我的学员
        Long userId = 1L;
        return Result.of(classWorkService.getClassWorkInfosByUserId(userId));
    }

    @RequestMapping(value = "/growthRecord")
    public Result growthRecord(@RequestParam(value = "sessionId", required = true) String sessionId,
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
        return Result.of(dynamicService.getMyStudentDynamicmList(studentIds));
    }

    @RequestMapping(value = "/followDynamic")
    public Result followDynamic(@RequestParam(value = "sessionId", required = true) String sessionId) throws IOException {
        Long userId = 1L; //登录人ID
        return Result.of(dynamicService.getFollowDynamicmList(userId));
    }


    @RequestMapping(value = "/saveMyGrowth")
    public Result saveMyGrowth(@RequestParam(value = "sessionId", required = true) String sessionId,
                               @RequestParam(value = "mediaUrls", required = false) List<String> mediaUrls,
                               HttpServletRequest request) throws IOException {
        //保存我的作业
        Long userId = 1L;
        String mediaType = request.getParameter("mediaType"); //文件类型，IMG 图片，VIDEO 小视频

        String content = request.getParameter("content");
        Integer power = Integer.parseInt(request.getParameter("power")); //是否公开
        Long studentId = Long.parseLong(request.getParameter("studentId"));
        TDynamic dynamic = new TDynamic();
        dynamic.setPower(power); //权限
        dynamic.setContent(content);
        dynamic.setStudentId(studentId);
        dynamic.setUserId(userId); //用户
        return Result.of(studentService.saveMygrowth(mediaUrls, mediaType, dynamic));
    }

    @RequestMapping(value = "/sendComment")
    public Result sendComment(@RequestParam(value = "sessionId", required = true) String sessionId,
                              @RequestParam String content, @RequestParam Long dynamicId) {
        //我的学员
        Long userId = 1L;
        return Result.of(dynamicService.saveComment(userId, dynamicId, content));
    }


    @RequestMapping(value = "/saveStudent")
    public Result saveStudent(@RequestParam(value = "sessionId", required = true) String sessionId,
                              @RequestParam(required = false, defaultValue = "") MultipartFile file,
                              @RequestParam(required = true, value = "studentName", defaultValue = "") String studentName,
                              @RequestParam(required = false, value = "studentId", defaultValue = "") Long studentId,
                              @RequestParam(required = false, value = "parentType", defaultValue = "1") Integer parentType) {
        try {
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String targetFileUrl = imgUploadPath + "userStudentImg/";
            String headImgUrl = targetFileUrl + BaseUtil.uuid() + "." + suffix;
            File newFile = new File(targetFileUrl);
            if (!newFile.exists() || !newFile.isDirectory()) {
                newFile.mkdirs();//会创建所有的目录
            }
            file.transferTo(new File(headImgUrl));
            //我的学员
            Long userId = 1L;
            TStudent student = new TStudent();
            if (studentId != null) {
                student.setId(studentId);
            }
            student.setRealName(studentName);
            student.setHeadImg(headImgUrl.replace(imgUploadPath, ""));
            student.setUserId(userId);
            student.setParentType(parentType);
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

    @RequestMapping(value = "/saveMyself")
    public Result saveMyself(@RequestParam(value = "sessionId", required = true) String sessionId,
                             @RequestParam(required = false, value = "nickName", defaultValue = "") String nickName,
                             @RequestParam(required = false, defaultValue = "") MultipartFile file,
                             @RequestParam(required = true, value = "sex", defaultValue = "1") int sex,
                             @RequestParam(required = true, value = "mobilePhone", defaultValue = "") String mobilePhone) {
        //我的学员


        try {
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String targeFileUrl = imgUploadPath + "userLogoImg/";
            String headImgUrl = targeFileUrl + userId + file.getName() + "." + suffix;
            File targetFolderFile = new File(targeFileUrl);
            if (!targetFolderFile.exists() || !targetFolderFile.isDirectory()) {
                targetFolderFile.mkdirs();//会创建所有的目录
            }
            file.transferTo(new File(headImgUrl));
            return Result.of(frontUserService.editUserInfoByMySelf(userId, nickName, headImgUrl.replace(imgUploadPath, ""), sex, mobilePhone));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.error("上传头像出错了");
    }


    @RequestMapping(value = "/sendAddFriendReq")
    public Result sendAddFriendReq(@RequestParam(value = "sessionId", required = true) String sessionId,
                                   @RequestParam(required = true, value = "friendId", defaultValue = "") Long friendId) {

        //添加好友的请求

        return Result.of(followUserService.sendAddFriendReq(userId, friendId));
    }

    @RequestMapping(value = "/addFriend")
    public Result addFriend(@RequestParam(value = "sessionId", required = true) String sessionId,
                            @RequestParam(required = true, value = "friendId", defaultValue = "") Long friendId,
                            @RequestParam(required = true, value = "isAgree", defaultValue = "") Integer isAgree) {

        if (isAgree == 1 || isAgree == 2) {
            //表示同意
            return Result.of(followUserService.addUserFriend(userId, friendId, isAgree));
        } else {
            //表示不同意
            Map<String, Object> result = Maps.newHashMap();
            result.put("message", "对方不同意添加好友");
            result.put("success", false);
            return Result.of(result);
        }
        //同意请求后，添加好友

    }

    @RequestMapping(value = "/delFriend")
    public Result delFriend(@RequestParam(value = "sessionId", required = true) String sessionId,
                            @RequestParam(required = true, value = "friendId", defaultValue = "") Long friendId) {

        //删除好友
        return Result.of(followUserService.updateFollowUser(userId, friendId, "30"));
    }

    @RequestMapping(value = "/shieldFriend")
    public Result shieldFriend(@RequestParam(value = "sessionId", required = true) String sessionId,
                               @RequestParam(required = true, value = "friendId", defaultValue = "") Long friendId) {


        return Result.of(followUserService.updateFollowUser(userId, friendId, "20"));
    }


}
