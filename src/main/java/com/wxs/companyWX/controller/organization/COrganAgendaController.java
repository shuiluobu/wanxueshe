package com.wxs.companyWX.controller.organization;

import com.wxs.entity.organ.TOrganAgenda;
import com.wxs.entity.organ.TOrganTask;
import com.wxs.service.customer.ITTeacherService;
import com.wxs.service.organ.ITOrganAgendaService;
import com.wxs.service.organ.ITOrganTaskService;
import com.wxs.util.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxs.core.util.BaseUtil;

import java.util.*;

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
    @Autowired
    private ITOrganTaskService organTaskService;

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
            //教务待办 - 获取其下的 四个任务 的 完成情况
            if(organAgenda.getType() == 1){
                Map detailsMap = getDoneDetails(agendaId);
                //签到 统计
                resultMap.put("signInDetail",detailsMap.get("signInDetail"));
                resultMap.put("firstSignInTime",detailsMap.get("firstSignInTime") == null ? "未签到":detailsMap.get("firstSignInTime"));
                //课堂点评 统计
                resultMap.put("classCommentDetail",detailsMap.get("classCommentDetail"));
                resultMap.put("firstClassCommentTime",detailsMap.get("firstClassCommentTime") == null ? "未点评":detailsMap.get("firstClassCommentTime"));
                //任务发布 统计
                resultMap.put("taskReleaseDetail",detailsMap.get("taskReleaseDetail"));
                resultMap.put("firstTaskReleaseTime",detailsMap.get("firstTaskReleaseTime") == null ? "未发布":detailsMap.get("firstTaskReleaseTime"));
                //任务点评 统计
                resultMap.put("taskCommentDetail",detailsMap.get("taskCommentDetail"));
                resultMap.put("firstTaskCommentTime",detailsMap.get("firstTaskCommentTime") == null ? "未点评":detailsMap.get("firstTaskCommentTime"));

            }
            Calendar calendar = Calendar.getInstance();
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


    /**
     * 获取 教务-待办  4个子任务的 完成情况
     * @param agendaId
     * @return
     */
    private Map<String,Object> getDoneDetails(Long agendaId){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            List<TOrganTask> tempList = null;
            Integer tempTotalCount = null;
            Integer tempDoneCount = null;
            for(int i=1;i<5;i++){
                tempList = organTaskService.getAllByAgendaId(agendaId,i);
                tempTotalCount = tempList.size();
                tempDoneCount = 0;
                for(int j=0;j<tempTotalCount;j++){
                    if(tempList.get(j).getStatus() == 1){
                        tempDoneCount++;
                    }
                }
                //算出完成情况
                String tempDetail = null;
                if(tempDoneCount == 0){
                    if(i == 1)
                        tempDetail = "未签到";
                    if(i == 2 || i == 4)
                        tempDetail = "未点评";
                    if(i == 3)
                        tempDetail = "未发布";
                }else{
                    if(i == 1){
                        tempDetail = tempDoneCount+"人已签到";
                        resultMap.put("firstSignInTime",getFirstDoneTime(tempList));//首次签到时间
                    }
                    if(i == 2){
                        tempDetail = tempDoneCount+"人已点评";
                        resultMap.put("firstClassCommentTime",getFirstDoneTime(tempList));//首次课堂点评时间
                    }
                    if(i == 3){
                        tempDetail = tempDoneCount+"人已发布";
                        resultMap.put("firstTaskReleaseTime",getFirstDoneTime(tempList));//首次发布时间
                    }
                    if(i == 4){
                        tempDetail = tempDoneCount+"人已点评";
                        resultMap.put("firstTaskCommentTime",getFirstDoneTime(tempList));//首次作业点评时间
                    }
                }
                if(i == 1)
                    resultMap.put("signInDetail",tempDetail);
                if(i == 2)
                    resultMap.put("classCommentDetail",tempDetail);
                if(i == 3)
                    resultMap.put("taskReleaseDetail",tempDetail);
                if(i == 4)
                    resultMap.put("taskCommentDetail",tempDetail);
            }
        }catch (Exception e){
            log.error(BaseUtil.getExceptionStackTrace(e));
            e.printStackTrace();
        }

        return resultMap;
    }

    //获取首次完成时间
    private String getFirstDoneTime(List<TOrganTask> list){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getDoneTime() != null){
                return BaseUtil.toString(list.get(i).getDoneTime(),"yyyy-MM-dd HH:mm:ss");
            }
        }
        return null;
    }
}
