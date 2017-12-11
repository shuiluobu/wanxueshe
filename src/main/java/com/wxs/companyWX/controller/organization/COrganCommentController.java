package com.wxs.companyWX.controller.organization;

import com.wxs.entity.organ.TOrganComment;
import com.wxs.service.customer.ITTeacherService;
import com.wxs.service.organ.ITOrganCommentService;
import com.wxs.util.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxs.core.util.BaseUtil;

import java.util.Date;

/**
 * Created by Administrator on 2017/12/8.
 */
@RestController
@RequestMapping("/cOrganComment")
public class COrganCommentController {

    private static Logger log = Logger.getLogger(COrganCommentController.class);

    @Autowired
    private ITOrganCommentService organCommentService;
    @Autowired
    private ITTeacherService teacherService;

    /**
     * @Description : 获取某项下的所有评论
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 11:21 2017/12/8
     * @Params : [itemId, type]
     **/
    @RequestMapping("/getAllById")
    public Result getAllById(Long itemId,Integer type){
        try{
            return Result.of(organCommentService.getAllById(itemId,type));
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }
        return null;
    }

    //添加评论
    @RequestMapping("/addComment")
    public Result addComment(TOrganComment organComment){
        try{
            organComment.setCreateTime(new Date());
            organCommentService.insert(organComment);
            String returnMsg = "评论成功!";
            //@的人的 教师名称
            if(organComment.getToUserId() != null){
                returnMsg = "回复评论成功!";
            }
            return Result.of(returnMsg);
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }
        return null;
    }
}


