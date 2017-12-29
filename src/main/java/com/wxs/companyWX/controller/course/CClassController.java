package com.wxs.companyWX.controller.course;

import com.wxs.service.course.ITClassService;
import com.wxs.util.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxs.core.util.BaseUtil;

/**
 * Created by Administrator on 2017/12/28.
 */
@RestController
@RequestMapping("/cClass")
public class CClassController {
    private static Logger log = Logger.getLogger(CClassController.class);
    @Autowired
    private ITClassService classService;
    /**
     * @Description : 根据 班级名称，机构Id,类型(我的班级,不限-所属机构的),用户Id  搜索 班级
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 10:01 2017/12/28
     * @Params : [name, organId, type, userId]
     **/
    @RequestMapping("/searchByName")
    public Result searchByName(String name, Long organId,Integer type,Long userId){
        try{
            return Result.of(classService.searchByName(name,organId,type,userId));
        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return null;
    }
}
