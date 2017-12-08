package com.wxs.companyWX.controller.frontUser;

import com.wxs.entity.customer.TFrontUser;
import com.wxs.service.customer.ITFrontUserService;
import com.wxs.service.customer.ITTeacherService;
import com.wxs.util.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxs.core.util.BaseUtil;

/**
 * Created by wyh on 2017/12/7.
 */
@RestController
@RequestMapping("/cFrontUser")
public class CFrontUserController {

    private static Logger log = Logger.getLogger(CFrontUserController.class);

    @Autowired
    private ITFrontUserService frontUserService;
    @Autowired
    private ITTeacherService teacherService;


    /**
     * 获取 用户给信息
     * @param userId
     * @return
     */
    @RequestMapping("/getById")
    public Result myOrganAgenda(Long userId){
        try{
            TFrontUser frontUser = frontUserService.selectById(userId);
            //如果是老师,则 赋值  教师名称
            if(frontUser.getUserType() == 2){
                frontUser.setTeacherName(teacherService.getByUserId(userId).getTeacherName());
            }
             return Result.of();
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }
        return null;
    }


}
