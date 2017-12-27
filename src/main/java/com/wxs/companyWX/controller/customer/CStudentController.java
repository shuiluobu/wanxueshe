package com.wxs.companyWX.controller.customer;

import com.wxs.service.customer.ITStudentService;
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
@RequestMapping("/cStudent")
public class CStudentController {

    private static Logger log = Logger.getLogger(CStudentController.class);
    @Autowired
    private ITStudentService studentService;


    /**
     * @Description : 根据学生姓名  搜索学生
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 16:08 2017/12/25
     * @Params : [name,organId]
     **/
    @RequestMapping("/searchByName")
    public Result searchByName(String name,Long organId){

        try{
            return Result.of(studentService.searchByName(name,organId));
        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description : 根据 教育机构Id 获取其下 所有学员
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 16:10 2017/12/27
     * @Params : [organId]
     **/
    @RequestMapping("/getAllByOrganId")
    public Result getAllByOrganId(Long organId){

        try{
            return Result.of(studentService.getAllByOrganId(organId));
        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return null;
    }
}
