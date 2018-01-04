package com.wxs.companyWX.controller.task;

import com.wxs.enu.EnumClassworkCompletion;
import com.wxs.service.task.ITStudentWorkService;
import com.wxs.util.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxs.core.util.BaseUtil;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/4.
 */
@RestController
@RequestMapping("/cStudentWork")
public class CStudentWorkController {

    private static Logger log = Logger.getLogger(CStudentWorkController.class);

    @Autowired
    private ITStudentWorkService studentWorkService;

    @RequestMapping("/studentWork")
    public Result studentWork(Long studentId,Long organId){
        Map resultMap = new HashMap();
        try{
            Calendar calendar = Calendar.getInstance();
            //今日待办
            String startTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 00:00:00";
            String endTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 23:59:59";
            resultMap.put("todays",studentWorkService.studentWork(studentId,organId,startTime,endTime, EnumClassworkCompletion.NOT_HAND_IN.getTypeCode()));
            //明日待办
            calendar.add(Calendar.DAY_OF_MONTH,1);
            startTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 00:00:00";
            endTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 23:59:59";
            resultMap.put("todays",studentWorkService.studentWork(studentId,organId,startTime,endTime,EnumClassworkCompletion.NOT_HAND_IN.getTypeCode()));
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }

        return Result.of(resultMap);
    }
}
