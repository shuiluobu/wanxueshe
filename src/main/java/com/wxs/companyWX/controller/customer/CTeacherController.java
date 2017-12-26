package com.wxs.companyWX.controller.customer;

import com.wxs.service.customer.ITTeacherService;
import com.wxs.util.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxs.core.util.BaseUtil;

/**
 * Created by Administrator on 2017/12/25.
 */
@RestController
@RequestMapping("/cTeacher")
public class CTeacherController {

    private static Logger log = Logger.getLogger(CTeacherController.class);

    @Autowired
    private ITTeacherService teacherService;

    @RequestMapping("/searchByName")
    public Result searchByName(String name, Long organId){
        try{
            return Result.of(teacherService.searchByName(organId,name));
        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return null;
    }
}
