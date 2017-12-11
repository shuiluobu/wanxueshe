package com.wxs.companyWX.controller.organization;

import com.wxs.entity.organ.TOrganAgenda;
import com.wxs.service.customer.ITTeacherService;
import com.wxs.service.organ.ITOrganAgendaService;
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
 * 待办
 * Created by wyh on 2017/12/5.
 */
@RestController
@RequestMapping("/cOrganAgenda")
public class COrganAgendaController {

    private static Logger log = Logger.getLogger(COrganAgendaController.class);

    @Autowired
    private ITOrganAgendaService organAgendaService;
    @Autowired
    private ITTeacherService teacherService;

    /**
     * 获取 我的今日和明日待办
     * @param userId
     * @return
     */

    @RequestMapping("/myOrganAgenda")
    public Result myOrganAgenda(Long userId){
        Map resultMap = new HashMap();
        try{
            Calendar calendar = Calendar.getInstance();
            //今日待办
            String startTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 00:00:00";
            String endTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 23:59:59";
            resultMap.put("todays",organAgendaService.myOrganAgenda(userId,startTime,endTime));
            //明日待办
            calendar.add(Calendar.DAY_OF_MONTH,1);
            startTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 00:00:00";
            endTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 23:59:59";
            resultMap.put("tomorrows",organAgendaService.myOrganAgenda(userId,startTime,endTime));
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }

        return Result.of(resultMap);
    }

    /**
     * 获取 用户所属机构的 今日和明日待办
     * @param userId
     * @return
     */

    @RequestMapping("/organAgenda")
    public Result organAgenda(Long userId){
        Map resultMap = new HashMap();
        try{
            Calendar calendar = Calendar.getInstance();
            //今日待办
            String startTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 00:00:00";
            String endTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 23:59:59";
            resultMap.put("todays",organAgendaService.organAgenda(userId,startTime,endTime));
            //明日待办
            calendar.add(Calendar.DAY_OF_MONTH,1);
            startTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 00:00:00";
            endTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 23:59:59";
            resultMap.put("tomorrows",organAgendaService.organAgenda(userId,startTime,endTime));
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }

        return Result.of(resultMap);
    }

    /**
     * 根据 用户名  搜索 待办
     * @param userName
     * @return
     */

    @RequestMapping("/getAgendaByUserName")
    public Result getAgendaByUserName(String userName){
        Map resultMap = new HashMap();
        try{
            Calendar calendar = Calendar.getInstance();
            //今日待办
            String startTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 00:00:00";
            String endTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 23:59:59";
            resultMap.put("todays",organAgendaService.getAgendaByUserName(userName,startTime,endTime));
            //明日待办
            calendar.add(Calendar.DAY_OF_MONTH,1);
            startTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 00:00:00";
            endTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 23:59:59";
            resultMap.put("tomorrows",organAgendaService.getAgendaByUserName(userName,startTime,endTime));
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }

        return Result.of(resultMap);
    }

    /**
     * 获取待办详情
     * @param agendaId
     * @return
     */

    @RequestMapping("/OrganAgendaDetail")
    public Result OrganAgendaDetail(Long agendaId){
        Map resultMap = new HashMap();
        try{
            //获取该待办
            TOrganAgenda organAgenda = organAgendaService.selectById(agendaId);
            organAgenda.setUserName(teacherService.selectById(organAgenda.getUserId()).getTeacherName());
            resultMap.put("agendaDetail",organAgenda);
            //教务待办 - 获取其下的 四个任务
            if(organAgenda.getType() == 1){

            }
            Calendar calendar = Calendar.getInstance();
            //今日待办
            String startTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 00:00:00";
            String endTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 23:59:59";
//            resultMap.put("todays",organAgendaService.getAgendaByUserName(userName,startTime,endTime));
            //明日待办
            calendar.add(Calendar.DAY_OF_MONTH,1);
            startTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 00:00:00";
            endTime = BaseUtil.toString(calendar.getTime(),"yyyy-MM-dd") +" 23:59:59";
//            resultMap.put("tomorrows",organAgendaService.getAgendaByUserName(userName,startTime,endTime));
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }

        return Result.of(resultMap);
    }

    /**
     * 完成待办
     * @param agendaId
     * @return
     */
    @RequestMapping("/doneOrganAgenda")
    public Result doneOrganAgenda(Long agendaId){
        try{
            TOrganAgenda organAgenda =  organAgendaService.selectById(agendaId);
            organAgenda.setStatus(1);
            organAgendaService.updateById(organAgenda);
            return Result.of("完成待办成功!");
        }catch (Exception e){
            e.printStackTrace();
            log.error(BaseUtil.getExceptionStackTrace(e));
        }
        return null;
    }
}
