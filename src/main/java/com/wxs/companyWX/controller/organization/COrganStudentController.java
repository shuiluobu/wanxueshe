package com.wxs.companyWX.controller.organization;

import com.wxs.service.organ.ITOrganStudentService;
import com.wxs.util.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxs.core.util.BaseUtil;

/**
 * Created by Administrator on 2017/12/29.
 */
@RestController
@RequestMapping("/cOrganStudent")
public class COrganStudentController {

    private static Logger log = Logger.getLogger(COrganStudentController.class);

    @Autowired
    private ITOrganStudentService organStudentService;
    /**
     * @Description : 根据 机构Id  和 课程顾问名字 搜索 该机构的 课程顾问
     * @return com.wxs.util.Result
     * @Author : wyh
     * @Creation Date : 16:08 2017/12/25
     * @Params : [name,organId]
     **/
    @RequestMapping("/searchAdvisorByName")
    public Result searchAdvisorByName(String name, Long organId){
        try{
            return Result.of(organStudentService.searchAdvisorByName(organId,name));
        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }
        return null;
    }
}
