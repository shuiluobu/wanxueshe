package com.wxs.companyWX.controller.frontUser;

import com.wxs.service.customer.ITFrontUserService;
import com.wxs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wyh on 2017/12/7.
 */
@RestController
@RequestMapping("/cFrontUser")
public class CFrontUserController {

    @Autowired
    private ITFrontUserService frontUserService;


    /**
     * 获取 用户给信息
     * @param userId
     * @return
     */
    @RequestMapping("/getById")
    public Result myOrganAgenda(Long userId){
        try{
             return Result.of(frontUserService.selectById(userId));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
